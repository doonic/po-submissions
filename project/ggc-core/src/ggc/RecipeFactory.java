package ggc;

import java.io.Serializable;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class RecipeFactory implements Serializable{
    

     /** Serial number for serialization. */
    private static final long serialVersionUID = 202110252046L;


    /** Multiplicative Factor */
    private double _factor;

    /** Price of the derived product */
    private double _priceToCompute;

    /** Products */
    private HashMap<String,Integer> _productsAndAmount 
    = new LinkedHashMap<String,Integer>();

    
    /** PARSING OF PRODUCTS COMPONENTS
    /** Splits each element of the String Array into the following 
     * format productName:amount   
    * [Hydrogen:20][Oxygen:10]*/ 
    private String _individualComponent[];

    /** used to store the following format 
    [Hydrogen][20] */
    private String _productAmount[];

    private String _fieldComponentsAndAmount;


    /** Stores the given receipt (given format) */
    private String _componentsAndAmountGiven;


    /**
    *Initializes a derived product recipe
    *@param factor, multiplicative factor
    *
    *
    */
    public RecipeFactory(double factor,String component){
        _componentsAndAmountGiven = component;
        initComponents(component);
        _factor = factor;
    }

    /**
    * parses and initializes the Treemap of recipes
    *
    *@param component
    *       Components, a string array
    */
    public void initComponents(String component){

        _fieldComponentsAndAmount = component;
        /** Splits each element of the String Array into the 
         * following format productName:amount   
         * [Hydrogen:20][Oxygen:10]*/ 
        _individualComponent = _fieldComponentsAndAmount.split("#",0);

        /** Stores the product name and its associated value */
        for(String el : _individualComponent){
            _productAmount = el.split(":",0);
            _productsAndAmount.put(_productAmount[0]
            ,Integer.parseInt(_productAmount[1]));
        }
    }

    /**
     * 
     * @return multiplicative factor to compute the derived
     *         product price
     */
    public double getFactor(){
        return _factor;
    }

    /**
     * 
     * @return derived product recipe as given when registered
     */
    public String getRecipe(){
        return _componentsAndAmountGiven;
    }

    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getFactor() + "|" + getRecipe();
            
    }

}
    