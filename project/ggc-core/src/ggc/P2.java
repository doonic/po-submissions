package ggc;

import ggc.status.*;


public class P2 extends PaymentMethod{
    
    /***
     * 
     * @param transaction , to be paid
     */
    @Override
    public void payTransaction(Transaction transaction){
        double basePrice = transaction.getBasePrice();
        int paymentGap = 
        transaction.getPaymentDeadline() - transaction.getPaymentDate();
        

        Partner partner = transaction.getPartnerTransaction();
        String partnerStatus = partner.getPartnerStatus();

        switch(partnerStatus){
            case "ELITE":
                basePrice *= 0.90;
                break;
            case "SELECTION":
                if(paymentGap >= 2){
                    basePrice *= 0.95;
                }
                break;
            default:
                break;  
        }
        transaction.setBasePrice(basePrice);
    }
        
}
