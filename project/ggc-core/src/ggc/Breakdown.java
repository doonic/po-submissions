package ggc;

import ggc.status.*;

public class Breakdown extends Transaction{

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111052318L;

    public Breakdown(
        Partner partner,Product product,int id,int amount){
            super(partner,product,id,amount);

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
    public int getPaymentDeadline(){
        return 0;
    }

    
}
