package com.example.sauce.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        // Setup WebDriverManager to handle ChromeDriver setup
        WebDriverManager.chromedriver().setup();

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Setting an implicit wait to handle dynamic elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        try {
            // Navigate to the target URL
            System.out.println("Navigating to the login page...");
            driver.get("https://www.saucedemo.com/");
            
            // Entering login credentials
            System.out.println("Entering username...");
            driver.findElement(By.id("user-name")).sendKeys("standard");
            
            System.out.println("Entering password...");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            
            // Clicking the login button
            System.out.println("Attempting to log in...");
            driver.findElement(By.xpath("//input[@type='submit']")).click();

            // Validation: Check for successful login by looking for a specific element
            boolean isLoggedIn = driver.findElements(By.className("inventory_list")).size() > 0;
            if (isLoggedIn) {
                System.out.println("Login successful! The inventory list is displayed.");
            } else {
                System.out.println("Login failed or the inventory list was not found.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the login test:");
            e.printStackTrace(); // Print detailed error for troubleshooting
        } finally {
            // Close the browser window
            System.out.println("Closing the browser...");
            driver.quit(); // Ensures all browser windows and WebDriver sessions are closed
        }
    }
}
