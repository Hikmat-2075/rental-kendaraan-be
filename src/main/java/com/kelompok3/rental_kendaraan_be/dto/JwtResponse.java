package com.kelompok3.rental_kendaraan_be.dto;

public class JwtResponse {

    private String token;
    private String type = "Bearer";  // Default token type adalah "Bearer"
    
    // Constructor
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter dan Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
