package com.databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatabaseTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//initiating driver browser
		WebDriverManager.chromedriver().setup();

		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "Ara@1993");

		java.sql.Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from credentials where scenario = 'zerobalancecard'");

		while (rs.next()) {
			WebDriver driver = new ChromeDriver();
			// Maximize the browser window
			driver.manage().window().maximize();

			driver.get("https://login.salesforce.com/");

			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(rs.getString("username"));
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(rs.getString("password"));

		}
	}

}
