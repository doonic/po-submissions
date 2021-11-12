package ggc;



public class SimpleProduct extends Product{

    /** Serial number for serialization. */
    private static final long serialVersionUId = 202109192316L;

    /**
     * Default Constructor
     * @param id
     * @param price
     * @param stock
     */
    public SimpleProduct(String id,double price,int stock){
        super(id,price,stock);
    }


        
    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getProductId() + "|"+ Math.round(getMaxPrice()) 
        + "|" + getStock();
    }

     /**
     * 
     * @return flag, that indicates the associated product type
     */
    @Override
    public boolean canBreakDown(){
        return false;
    }
    
    
}