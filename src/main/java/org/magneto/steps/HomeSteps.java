package org.magneto.steps;

public class HomeSteps extends BaseSteps {

    public HomeSteps openPage() {
        homePage.openPage();
        return this;
    }

    public BagsSteps navigateToBags(){
        homePage.navigateToGearSubMenu("Bags");
        return new BagsSteps();
    }
}
