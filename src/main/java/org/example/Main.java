package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.setProperty("webdriver.chrome.driver", "E:/Java/chromedriver-win64/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-extensions");

            // Iniciar o navegador
            WebDriver driver = new ChromeDriver();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                // Acessar o site
                driver.get("https://creanet1.creasp.org.br/");

                // Submeter o formulário
                WebElement botaoProfissionais = driver.findElement(By.id("imbBtnProfisionais"));
                botaoProfissionais.click();
                System.out.println("Acessado a home");

                WebElement insereCpf = driver.findElement(By.id("txtCPF"));
                String cpf = "34713501875";

                // Simula a digitação lenta
                for (char c : cpf.toCharArray()) {
                    insereCpf.sendKeys(String.valueOf(c));
                    Thread.sleep(200); // Espera 200ms entre cada caractere
                }

                WebElement insereSenha = driver.findElement(By.id("MainContent_txtSenha"));
                insereSenha.sendKeys("@Jpulcini08");
                System.out.println("Senha inserida");

                WebElement botaoEntrar = driver.findElement(By.id("MainContent_btnLogin"));
                botaoEntrar.click();
                System.out.println("Acessado a home");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                if (wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//a[text()='Serviços ART']"))) == null) {
                    System.out.println("CPF ou senha inválidos");
                    return;
                }

                WebElement servicosArt = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//a[text()='Serviços ART']")));
                servicosArt.click();
                System.out.println("Clicou em serviços ART");

                Thread.sleep(5000);
                WebElement art = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//a[text()='ART']")));
                art.click();
                System.out.println("Clicou em ART");

                WebElement preenchimentoArt = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//a[text()='Preenchimento de ART de Obra/Serviço - NOVA ART']")));
                preenchimentoArt.click();
                System.out.println("Clicou em preenchimento de ART");

                Thread.sleep(5000);
                WebElement abrirNovaArtBeta = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//a[text()='CLIQUE AQUI - PARA ABRIR NOVA ART']")));
                abrirNovaArtBeta.click();
                System.out.println("Clicou em preenchimento de ART");

                WebElement formaRegistro = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.id("registrationForm")));
            //    formaRegistro.click();
                Select selectFormaRegistro = new Select(formaRegistro);
                selectFormaRegistro.selectByValue("103");

                WebElement participacaoTecnica = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.id("technicalParticipation")));
                Select selectParticipacaoTecnica = new Select(participacaoTecnica);
                selectParticipacaoTecnica.selectByValue("61");

                WebElement botaoAvancar = driver.findElement(By.className("Home_buttonForm__ykFhM Home_primaryButton__c9qJ_"));
                botaoAvancar.click();
                System.out.println("Preenchido inicio de nova ART");


                System.out.println("Clicou em preenchimento de ART");



            } catch (InterruptedException | TimeoutException t) {
                continuar = true;
            } finally {
                // Fechar o navegador
                driver.quit();
            }

        }
    }
}