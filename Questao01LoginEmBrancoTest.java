package atividades;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Questão 01 - Mensagens de erro com campos em branco.
 * Site: https://the-internet.herokuapp.com/login
 *
 * Deixa usuário e senha em branco, clica em "Login" e valida:
 * 1. a mensagem contém "Your username is invalid!";
 * 2. o <div id="flash"> possui a classe CSS "error".
 */
class Questao01LoginEmBrancoTest extends BaseTest {

    @Test
    void loginEmBrancoExibeMensagemDeErro() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement flash = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        // 1. texto da mensagem
        assertTrue(flash.getText().contains("Your username is invalid!"),
                "A mensagem deveria conter 'Your username is invalid!', mas foi: " + flash.getText());

        // 2. classe CSS "error"
        String classes = flash.getDomAttribute("class");
        assertTrue(Arrays.asList(classes.trim().split("\\s+")).contains("error"),
                "O #flash deveria ter a classe 'error', mas tem: " + classes);
    }
}
