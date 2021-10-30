package ggc;

import java.io.Serializable;


public class Batch implements Serializable{

    /**  Partner's identifier whose batch is bought from*/
    String _partner;


    /** Number of available units of a product */
    int _stock;


    /** Price per unit of a product */
    double _priceUnit;


    /** Product's associated with the batch */
    String _product;


    
    /**
    *Batch main Constructor
    *@param partner
    *@param stock 
    *@param price
    *@param product
    *
    *@throws 
    */
    public Batch(String partner,int stock,double price,String product){
        _partner = partner;
        _stock = stock;
        _priceUnit = price;
        _product = product;
    }


    /** Returns the partner associated with a given batch */
    public String getPartner(){
        return _partner;
    }

    
    /** Returns the number of available units */
    public int getStock(){
        return _stock;
    }


    /** Returns the price per unit  */
    public double getPriceUnit(){
        return _priceUnit;
    }


    /** Returns the product in a given batch */
    public String getProduct(){
        return _product;
    }


    public void setPriceUnit(double value){
        _priceUnit = value;
    }


    public void updateStock(int amount){
        _stock += amount;
    }



    /** @see java.lang.Object#toString() */
    @Override
    public String toString(){
        return getProduct() + "|" + getPartner() + "|" 
        + (int)Math.round(getPriceUnit())  + "|" + getStock();
    }

    
    




    // THINK OF STUF TO IMPLEMENT



}