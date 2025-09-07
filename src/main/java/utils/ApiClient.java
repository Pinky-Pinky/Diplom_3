package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.CreatedUser;
import model.UserRequest;
import model.LoginRequest;

import java.time.Instant;

public class ApiClient {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    public CreatedUser createRandomUser() {
        // Генерируем уникальный email с временной меткой
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        String email = "pinky-" + timestamp + "@yandex.ru";
        String password = "Password123!"; // Более надёжный пароль
        String name = "Pinky";

        UserRequest userRequest = new UserRequest(email, password, name);

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(userRequest)
                .post(BASE_URL + "/auth/register");

        // Логируем ответ API
        System.out.println("API Response (create user): " + response.statusCode() + " - " + response.body().asString());

        if (response.statusCode() == 403 && response.body().asString().contains("User already exists")) {
            // Если пользователь уже существует, пытаемся войти
            return loginUser(email, password);
        } else if (response.statusCode() != 200) {
            throw new RuntimeException("Ошибка при создании пользователя: " + response.statusCode() + " - " + response.body().asString());
        }

        CreatedUser createdUser = new CreatedUser();
        createdUser.setEmail(response.jsonPath().getString("user.email"));
        createdUser.setName(response.jsonPath().getString("user.name"));
        createdUser.setPassword(password); // Пароль не возвращается API, используем исходный
        String accessToken = response.jsonPath().getString("accessToken");
        String refreshToken = response.jsonPath().getString("refreshToken");
        createdUser.setAccessToken(accessToken != null ? accessToken.replace("Bearer ", "") : null);
        createdUser.setRefreshToken(refreshToken);

        if (createdUser.getEmail() == null || createdUser.getName() == null) {
            throw new RuntimeException("API не вернул email или name: " + response.body().asString());
        }

        return createdUser;
    }

    private CreatedUser loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(loginRequest)
                .post(BASE_URL + "/auth/login");

        // Логируем ответ API
        System.out.println("API Response (login user): " + response.statusCode() + " - " + response.body().asString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Ошибка при входе пользователя: " + response.statusCode() + " - " + response.body().asString());
        }

        CreatedUser createdUser = new CreatedUser();
        createdUser.setEmail(response.jsonPath().getString("user.email"));
        createdUser.setName(response.jsonPath().getString("user.name"));
        createdUser.setPassword(password); // Пароль не возвращается API, используем исходный
        String accessToken = response.jsonPath().getString("accessToken");
        String refreshToken = response.jsonPath().getString("refreshToken");
        createdUser.setAccessToken(accessToken != null ? accessToken.replace("Bearer ", "") : null);
        createdUser.setRefreshToken(refreshToken);

        if (createdUser.getEmail() == null || createdUser.getName() == null) {
            throw new RuntimeException("API не вернул email или name при входе: " + response.body().asString());
        }

        return createdUser;
    }

    public void deleteUser(String accessToken) {
        if (accessToken == null) {
            return;
        }

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .delete(BASE_URL + "/auth/user");

        // Логируем ответ API
        System.out.println("API Response (delete user): " + response.statusCode() + " - " + response.body().asString());

        if (response.statusCode() != 202 && response.statusCode() != 200) {
            throw new RuntimeException("Ошибка при удалении пользователя: " + response.statusCode() + " - " + response.body().asString());
        }
    }
}