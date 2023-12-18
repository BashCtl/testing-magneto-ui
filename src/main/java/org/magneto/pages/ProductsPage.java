package org.magneto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage<ProductsPage>{


    private String productByNameXpath = "//strong[contains(.,'%s')]";
    private String addBtnProductXpath = "//strong[contains(.,'%s')]/../*[@class='product-item-inner']//button";

    @FindBy(css = "div[role='alert']")
    private WebElement alertMsg;



    public ProductsPage addProduct(String productTitle){
        WebElement product = waitUntilRefreshedVisibility(By.xpath(String.format(productByNameXpath, productTitle)));
        scrollToElement(product);
        actions.moveToElement(product).perform();
        WebElement addButton = waitUntilClickable(By.xpath(String.format(addBtnProductXpath, productTitle)));
        addButton.click();
        waitUntilClickable(addButton);
        return this;
    }

    public String getAlertText(){
        waitUntilElementVisible(alertMsg);
        return alertMsg.getText();
    }
}
