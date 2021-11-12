package ggc;

public class DerivedProduct extends Product{

    private RecipeFactory _recipe;

    /**
     * derived product constructor
     * @param recipe
     * @param price
     * @param id
     * @param stock
     */
    public DerivedProduct(RecipeFactory recipe,double price,String id
    ,int stock){
        super(id,price,stock);
        _recipe = recipe;    
    }
    
    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getProductId() + "|" + super.getMaxPrice() + "|"
         + super.getStock() +  "|" + _recipe.toString();
        
    }

    @Override
    /**
     * 
     * @return flag, that indicates the associated product type
     */
    public boolean canBreakDown(){
        return true;
    }
}



// TO DO: IMPLEMENT METHODS 