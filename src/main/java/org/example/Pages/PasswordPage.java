package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordPage {
    private final WebDriver driver;
    private final By loginButton = By.linkText("Войти");

    public PasswordPage(WebDriver driver) {

        this.driver = driver;
    }

    public void clickLoginButton() {

        driver.findElement(loginButton).click();
    }
}
