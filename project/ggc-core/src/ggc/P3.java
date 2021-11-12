package ggc;

import ggc.status.*;



public class P3 extends PaymentMethod{

    /***
     * Take in account penalties
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
                basePrice += basePrice*0.05*paymentGap;
                break;
            case "SELECTION":
                if(paymentGap> 1) {
                    basePrice += basePrice*0.02*paymentGap;
                }
                break;
            case "ELITE":
                basePrice *= 0.95;
                break;
        
            default:
                break;
        }
        transaction.setBasePrice(basePrice);
    }

    
}
