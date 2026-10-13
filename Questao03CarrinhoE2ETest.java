package atividades;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Questão 03 - Teste E2E no SauceDemo.
 * Site: https://www.saucedemo.com/
 *
 * 1. Login com sucesso.
 * 2. Adiciona três produtos diferentes ao carrinho ("Add to cart").
 * 3. Clica em "Remove" de um deles.
 * 4. Valida que o ícone do carrinho (canto superior direito) exibe exatamente "2".
 */
class Questao03CarrinhoE2ETest extends BaseTest {

    private static final String[] PRODUTOS = {
            "sauce-labs-backpack",
            "sauce-labs-bike-light",
            "sauce-labs-onesie"
    };

    @Test
    void carrinhoExibeDoisItensAposRemoverUm() {
        driver.get("https://www.saucedemo.com/");

        // 1. login com sucesso
        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        // 2. adiciona três produtos diferentes
        for (String produto : PRODUTOS) {
            driver.findElement(By.cssSelector("[data-test='add-to-cart-" + produto + "']")).click();
        }

        // 3. remove um deles
        driver.findElement(By.cssSelector("[data-test='remove-" + PRODUTOS[2] + "']")).click();

        // 4. badge do carrinho com exatamente "2"
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']")));
        wait.until(ExpectedConditions.textToBePresentInElement(badge, "2"));
        assertEquals("2", badge.getText());
    }
}
