package atividades;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Questão 05 - Robô de scraping no SauceDemo.
 * Site: https://www.saucedemo.com/
 *
 * Faz login (standard_user / secret_sauce), extrai nome e preço de todos os
 * produtos da página inicial e exibe no console (e salva em ofertas.txt)
 * apenas os que custam menos de $20.00.
 */
class Questao05OfertasAbaixoDe20Test extends BaseTest {

    @Test
    void listaProdutosAbaixoDe20() throws IOException {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

        List<WebElement> itens = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("[data-test='inventory-item']")));

        List<String> linhas = new ArrayList<>();
        boolean todosAbaixoDe20 = true;

        System.out.println("Produtos com preço menor que $20.00:");
        for (WebElement item : itens) {
            String nome = item.findElement(By.cssSelector("[data-test='inventory-item-name']")).getText();
            String textoPreco = item.findElement(By.cssSelector("[data-test='inventory-item-price']")).getText();
            double preco = Double.parseDouble(textoPreco.replaceAll("[^\\d.]", ""));

            if (preco < 20.00) {
                System.out.printf("- %s: $%.2f%n", nome, preco);
                linhas.add(String.format("%s;%.2f", nome, preco));
                todosAbaixoDe20 &= preco < 20.00;
            }
        }

        Files.write(Path.of("ofertas.txt"), linhas, StandardCharsets.UTF_8);

        assertTrue(todosAbaixoDe20);
    }
}
