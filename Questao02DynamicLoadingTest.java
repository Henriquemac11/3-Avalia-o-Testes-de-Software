package atividades;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Questão 02 - Dynamic Loading.
 * Site: https://the-internet.herokuapp.com/dynamic_loading/1
 *
 * Clica em "Start", aguarda a barra "Loading..." desaparecer e valida
 * que o texto final "Hello World!" é exibido.
 */
class Questao02DynamicLoadingTest extends BaseTest {

    @Test
    void exibeHelloWorldAposLoading() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.cssSelector("#start button")).click();

        // garante que o loading apareceu e, depois, que desapareceu
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

        WebElement texto = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        assertEquals("Hello World!", texto.getText());
    }
}
