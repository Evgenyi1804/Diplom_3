package org.example;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
public class User {
    private String name;
    private String email;
    private String password;

    public String getEmail() {
        return email.toLowerCase();
    }

    }