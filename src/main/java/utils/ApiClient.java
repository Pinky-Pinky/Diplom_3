package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class ApiClient {

    private final HttpClient httpClient;
    private final Gson gson;
    private final String baseUrl = "https://stellarburgers.nomoreparties.site/api";

    public ApiClient() {
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }

    // DTO для создания пользователя
    public static class UserRequest {
        String email;
        String password;
        String name;

        public UserRequest(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }
    }

    // DTO для логина
    public static class LoginRequest {
        String email;
        String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    // DTO для ответа
    public static class CreatedUser {
        public String email;
        public String password;
        public String name;
        public String accessToken;
        public String refreshToken;
    }

    /**
     * Создаёт уникального пользователя (email уникален) и возвращает объект CreatedUser
     */
    public CreatedUser createUserRandom() throws IOException, InterruptedException {
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String email = "ui_" + random + "@mail.test";
        String password = "Passw0rd!" + random;
        String name = "UiUser" + random;
        return createUser(email, password, name);
    }

    /**
     * Создаёт пользователя через API и возвращает CreatedUser с токенами (если они есть в ответе)
     */
    public CreatedUser createUser(String email, String password, String name) throws IOException, InterruptedException {
        UserRequest user = new UserRequest(email, password, name);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 202) {
            throw new RuntimeException("Create user failed: " + response.statusCode() + " body: " + response.body());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        CreatedUser cu = new CreatedUser();
        cu.email = email;
        cu.password = password;
        cu.name = name;
        cu.accessToken = json.has("accessToken") ? json.get("accessToken").getAsString() : null;
        cu.refreshToken = json.has("refreshToken") ? json.get("refreshToken").getAsString() : null;
        return cu;
    }

    /**
     * Удаляет пользователя по accessToken.
     */
    public void deleteUser(String accessToken) throws IOException, InterruptedException {
        if (accessToken == null || accessToken.isEmpty()) return;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/user"))
                .header("Content-Type", "application/json")
                .header("Authorization", accessToken)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200 && response.statusCode() != 202) {
            System.err.println("Warning: delete user returned " + response.statusCode() + " body: " + response.body());
        }
    }

    /**
     * Логинит пользователя и возвращает accessToken (строку как в ответе, возможно с "Bearer ").
     */
    public String loginAndGetAccessToken(String email, String password) throws IOException, InterruptedException {
        LoginRequest login = new LoginRequest(email, password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(login)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Login failed: " + response.statusCode() + " body: " + response.body());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        return json.has("accessToken") ? json.get("accessToken").getAsString() : null;
    }
}
