package ggc;

import java.io.Serializable;
import java.util.Comparator;

public class Batch implements Serializable{

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111052008L;

    /**  Partner's identifier whose batch is bought from*/
    String _partner;

    /** Number of available units of a product */
    int _stock;

    /** Price per unit of a product */
    double _priceU;

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
        _priceU = price;
        _product = product;
    }

    /**
     * 
     * @return partner's id, partner whose batch was bought 
     */
    public String getPartnerId(){
        return _partner;
    }
    
    /**
     * 
     * @return stock, total number of product's unit
     */
    public int getStock(){
        return _stock;
    }

    /**
     * 
     * @return price of each unit Prouct(Batch)
     */
    public double getpriceU(){
        return _priceU;
    }

    /**
     * 
     * @return product's id 
     */
    public String getProductId(){
        return _product;
    }

    /**
     * 
     * @param value
     */
    public void setpriceU(double value){
        _priceU = value;
    }

    /**
     * 
     * @param amount being added
     */
    public void updateStock(int amount){
        _stock += amount;
    }

    /**
     * 
     * @param amount being removed
     */
    public void removeStock(int amount){
        _stock -= amount;
    }
    
    /** @see java.lang.Object#toString() */
    @Override
    public String toString(){
        return getProductId() + "|" + getPartnerId() + "|" 
        + (int)Math.round(getpriceU())  + "|" + getStock();
    }
    
    /**
     * Batches default Comparator
     */
    public static Comparator<Batch> partnerAllComparator = 
    new Comparator<Batch>() {
        public int compare(Batch a, Batch b){
            int partnerCmp = a.getPartnerId().compareToIgnoreCase
            (b.getPartnerId());
            int priceCmp = Double.compare(a.getpriceU(),
            b.getpriceU());
            int stockCmp = Integer.compare(a.getStock(), b.getStock());
            if((partnerCmp == 0) & (priceCmp == 0) ){
                return stockCmp;
            }else if((partnerCmp == 0)){
                return priceCmp;
            }
            if(partnerCmp == 0){
                return priceCmp;
            }
            return partnerCmp;
        }        
    };
    
}