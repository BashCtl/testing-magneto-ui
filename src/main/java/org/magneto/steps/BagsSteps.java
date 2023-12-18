package org.magneto.steps;

import org.magneto.pages.BagsPage;

public class BagsSteps extends BaseSteps {

    private final BagsPage bagsPage;

    public BagsSteps() {
        bagsPage = new BagsPage();
    }

    public BagsSteps addBagToCart(String bagTitle) {
        bagsPage.addProduct(bagTitle);
        return this;
    }

   public String getAlertText(){
        return bagsPage.getAlertText();
   }

}
