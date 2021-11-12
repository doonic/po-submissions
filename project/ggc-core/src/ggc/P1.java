package ggc;

import ggc.status.*;

public class P1 extends PaymentMethod{

    /**
     * 10 % of discount (independent of partner's status)
     * @param transaction
     */
    @Override
    public void payTransaction(Transaction transaction){
        double basePrice = transaction.getBasePrice();
        
        basePrice *= 0.90;
        transaction.setBasePrice(basePrice);

    }
}
