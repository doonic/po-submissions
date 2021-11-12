package ggc;

import ggc.status.*;
import java.io.Serializable;

public abstract class Transaction implements Serializable{

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111051208L;

    /** Transaction id */
    private int _idTransaction;

    /** Transaction's payament date */
    private int _paymentDate;

    /** Base price (before discounts), total value of a transaction*/
    private double _basePrice;

    /**flag that indicates whether or not a transaction 
     * was paid
     */
    private boolean _paid;

    /** Partner associated to the transaction */
    private Partner _partner;

    /** Product associated to the transaction */
    private Product _product;

    
    /** Amount of product being sold/bought to/from the partner */
    private int _stock;

    
    /**
     * Transaction  Main Constructor
     * 
     * @param partner
     * @param product
     * @param id
     * @param paymentDeadline
     * @param amount
     * @param price
     */
    public Transaction(Partner partner,Product product,int id,
    int paymentDeadline,int amount,double price){
        this(partner,product,id,amount,price);
        _paymentDate = 0; 
        _paid = false;

    }   

    /**
     * Transaction Main Constructor
     * 
     * @param partner
     * @param product
     * @param id
     * @param paymentDeadline
     * @param amount
     */
    public Transaction(Partner partner,Product product,int id,
    int amount,double price){
        _partner = partner;
        _product = product;
        _idTransaction = id;
        _paid = false;
        _stock = amount;
        _basePrice = amount*price;

    }   

    /**
     * 
     * @param partnerId
     * @param productId
     * @param id
     * @param amount
     * 
     */
    public Transaction(Partner partner,Product product,int id,
    int amount){
        this(partner, product, id, amount,0);
    }



    /** Getters */
    
    /**
     * @return transaction's id
     */
    public int getIdTransaction(){
        return _idTransaction;
    }

    /**
     * 
     * @return product
     */
    public Product getProductTransaction(){
        return _product;
    }

    /**
     * @return partner 
     */
    public Partner getPartnerTransaction(){
        return _partner;
    }
    
    
    public String getPartnerIdTransaction(){
        return _partner.getPartnerId();
    }

    public String getProductIdTransaction(){
        return _product.getProductId();
    }
    /**
     * 
     * @return transaction's paymentDate;
     */
    public int getPaymentDate(){
        return _paymentDate;
    }
    
    /**
     * 
     * @return  base price (value of transaction without taking in 
     * account discount/penalty)
     */
    public double getBasePrice(){
        return _basePrice;
    }

    /**
     * 
     * @return stock (flow of products) associated to the transaction
     */
    public int getStockTransaction(){
        return _stock;

    }

    /**
     * 
     * @return true if a transaction is already paid,otherwise false
     */
    public boolean isTransactionPaid(){
        return _paid;
    }

    
    public abstract int getPaymentDeadline();


    /** Setters */

    /**
     * 
     * @param date
     */
    public void setPaymentDate(int date){
        _paymentDate = date;
    }

    /**
     * 
     * @param value
     */
    public void setBasePrice(double value){
        _basePrice = value;
    }


    public abstract boolean isTransactionPayableByPartner();


}