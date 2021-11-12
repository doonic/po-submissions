package ggc;

import ggc.Warehouse;

import ggc.status.*;

public class P4 extends PaymentMethod{

    /***
     * penalties only
     * @param transaction , to be paid
     */
    @Override
    public void payTransaction(Transaction transaction){
        double basePrice = transaction.getBasePrice();
        int paymentGap = 
        transaction.getPaymentDate() - transaction.getPaymentDeadline();
       
        Partner partner = transaction.getPartnerTransaction();
        String partnerStatus = partner.getPartnerStatus();

        switch(partnerStatus){
            case "NORMAL":
                basePrice += basePrice*0.1*paymentGap;
                break;
            case "SELECTION":
                if(paymentGap> 1) {
                    basePrice += basePrice*0.05*paymentGap;
                }
                break;        
            default:
                break;
        }
        transaction.setBasePrice(basePrice);
    }
    
}
