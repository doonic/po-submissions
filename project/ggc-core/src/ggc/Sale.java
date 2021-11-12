package ggc;

import ggc.status.*;

public class Sale extends Transaction{

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111052308L;

    /** Sale paymentDeadline */
    private int _deadLine;

    /** Price */
    private double _price;
    
    /**
     * Default Constructor Sales Transaction
     * @param partner
     * @param product
     * @param id
     * @param paymentDeadline
     * @param amount
     */
    public Sale(Partner partner,Product product,int id,
    int paymentDeadline,int amount,double price){
        super(partner,product,id,paymentDeadline,amount,price);
        _deadLine = paymentDeadline;
        _price = price;

    }

 

     
    public double getToPay(){
        return _price;

    }

    public int getPaymentDeadline(){
        return _deadLine;
    }


    /**
     * 
     * @return true
     */
     @Override
    public boolean isTransactionPayableByPartner(){
        return true;
    } 


    @Override
    public String toString(){
        String res = "VENDA" + "|" + getIdTransaction() + "|" +
        getPartnerIdTransaction() + "|" + getProductIdTransaction() +
         "|"  + getStockTransaction() + "|" + 
         (int)Math.round(getBasePrice()) + "|" + getToPay()
          + "|" + getPaymentDeadline();

        return isTransactionPaid() ? res += "|" + getPaymentDate() : 
        res;
      
    }


}