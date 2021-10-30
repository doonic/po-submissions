package ggc;




public class ProductDerived extends Product{

    private RecipeFactory _recipe;


    public ProductDerived(RecipeFactory recipe,double price,String id
    ,int stock){
        super(id,price,stock);
        _recipe = recipe;    
    }
    
    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getId() + "|" + super.getMaxPrice() + "|"
         + super.getStock() +  "|" + _recipe.toString();
        
    }
}



// TO DO: IMPLEMENT METHODS 