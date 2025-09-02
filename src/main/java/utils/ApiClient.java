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

    public static class CreatedUser {
        public String email;
        public String password;
        public String name;
        public String accessToken;   // может содержать префикс "Bearer "
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
        JsonObject payload = new JsonObject();
        payload.addProperty("email", email);
        payload.addProperty("password", password);
        payload.addProperty("name", name);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Нормальные коды: 200 или 202 (в зависимости от API)
        if (response.statusCode() != 200 && response.statusCode() != 202) {
            throw new RuntimeException("Create user failed: " + response.statusCode() + " body: " + response.body());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        String accessToken = json.has("accessToken") ? json.get("accessToken").getAsString() : null;
        String refreshToken = json.has("refreshToken") ? json.get("refreshToken").getAsString() : null;

        CreatedUser cu = new CreatedUser();
        cu.email = email;
        cu.password = password;
        cu.name = name;
        cu.accessToken = accessToken;
        cu.refreshToken = refreshToken;
        return cu;
    }

    /**
     * Удаляет пользователя по accessToken.
     * accessToken должен содержать префикс "Bearer " если API требует этого.
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
        JsonObject payload = new JsonObject();
        payload.addProperty("email", email);
        payload.addProperty("password", password);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/auth/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Login failed: " + response.statusCode() + " body: " + response.body());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        if (json.has("accessToken")) {
            return json.get("accessToken").getAsString();
        } else {
            return null;
        }
    }
}