package ggc;

import java.io.Serializable;

public abstract class Transaction implements Serializable{

    // TO DO:
    //  think of parameters
    // --> final delivery

    /** Transaction Identifier */
    private int _id;

    /** All Transactions have payment date */
    private int _paymentDate;





    public void setPaymentDate(int date){
        _paymentDate = date;
    }


}