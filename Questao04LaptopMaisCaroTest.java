package atividades;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Questão 04 - Laptop mais caro no Demoblaze.
 * Site: https://www.demoblaze.com/
 *
 * Clica na categoria "Laptops" do menu lateral, aguarda o carregamento dos
 * produtos, extrai nome e preço do laptop mais caro e valida que o preço é > $0.
 */
class Questao04LaptopMaisCaroTest extends BaseTest {

    private static final By CARDS = By.cssSelector("#tbodyid .card-block");

    @Test
    void extraiLaptopMaisCaro() {
        driver.get("https://www.demoblaze.com/");

        // espera a carga inicial (Phones) e guarda um card para detectar a troca de lista
        WebElement cardAntigo = wait.until(ExpectedConditions.visibilityOfElementLocated(CARDS));

        driver.findElement(By.xpath("//a[@id='itemc' and normalize-space()='Laptops']")).click();

        // a lista é recarregada via AJAX: espera o card antigo sumir e os novos aparecerem
        wait.until(ExpectedConditions.stalenessOf(cardAntigo));
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CARDS));

        String nomeMaisCaro = "";
        double maiorPreco = 0;

        for (WebElement card : cards) {
            String nome = card.findElement(By.cssSelector(".card-title a")).getText().trim();
            String textoPreco = card.findElement(By.tagName("h5")).getText();   // ex.: "$790"
            double preco = Double.parseDouble(textoPreco.replaceAll("[^\\d.]", ""));

            if (preco > maiorPreco) {
                maiorPreco = preco;
                nomeMaisCaro = nome;
            }
        }

        System.out.printf("Laptop mais caro: %s - $%.2f%n", nomeMaisCaro, maiorPreco);

        assertTrue(maiorPreco > 0, "O preço do laptop mais caro deve ser maior que $0");
    }
}
