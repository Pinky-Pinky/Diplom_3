package model;

public class CreatedUser {
    private String email;
    private String password;
    private String name;
    private String accessToken;
    private String refreshToken;

    public CreatedUser() {}

    public CreatedUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }

    // Setters
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
}