package ggc;



public class ProductSimple extends Product{

    /**
    *Simple Product main constuctor
    *
    *
    */

    public ProductSimple(String id,double price,int stock){
        super(id,price,stock);
    }


        
    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getId() + "|"+ Math.round(getMaxPrice()) 
        + "|" + getStock();
    }
    
    
}