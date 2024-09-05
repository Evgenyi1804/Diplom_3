package org.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.User;

public class UserCreate {
    public User getUser() {

        return new User(getName(), getEmail(), getValidPassword());
    }

    public String getName() {

        return RandomStringUtils.randomAlphabetic(10);
    }

    public String getEmail() {
        return String.format("%s@%s.ru", RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(4));
    }

    public String getValidPassword() {

        return RandomStringUtils.randomAlphanumeric(8);
    }

    public String getInvalidPassword() {

        return RandomStringUtils.randomAlphanumeric(5);
    }
}