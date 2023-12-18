package org.magneto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class HomePage extends BasePage<HomePage> {

    @FindBy(xpath = "//span[text()='Gear']/../span")
    private WebElement gearMenu;

    private String subMenuXpath = "//span[text()='%s']/../..//span[text()='%s']";

    @FindBy(css = "div[data-block='minicart']")
    private WebElement cartIcon;


    public HomePage openPage() {
        driver.get(ENV_URL);
        waitUntilPageLoaded();
        return this;
    }

    public MiniCartPage clickCartIcon() {
        scrollToElement(cartIcon);
        waitUntilClickable(cartIcon).click();
        return new MiniCartPage();
    }

    public BagsPage navigateToGearSubMenu(String subMenu) {
        waitUntilClickable(gearMenu);
        actions.moveToElement(gearMenu).perform();
        WebElement subMenuEl = driver.findElement(By.xpath(String.format(subMenuXpath, "Gear", subMenu)));
        clickElement(subMenuEl);
        return new BagsPage();
    }

}
