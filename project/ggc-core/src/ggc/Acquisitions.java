package ggc;

import ggc.status.*;

public class Acquisitions extends Transaction{  

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111052305L;

    // ARE PAID IMMEDIATELY

    /**
     * Default Constructor, Acquisition Transaction
     * 
     * @param partner
     * @param product
     * @param paymentDeadline
     * @param amount
     */
    public Acquisitions(
        Partner partner,Product product,int id,int amount,
        double price){
            super(partner,product,id,amount,price);

    }


    @Override
    /**
     * flag that indicates whether or not the current transaction is 
     * payable by a Partner
     * 
     * @return false
     */
    public boolean isTransactionPayableByPartner(){
        return false;
    } 

    @Override
    /**
     * 
     * @return integer
     */
    public  int getPaymentDeadline(){
        /** does nothing */
        return 0;
    }
    

}