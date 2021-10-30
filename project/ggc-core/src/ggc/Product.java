package ggc;

import java.io.Serializable;


public abstract class Product implements Serializable{



    /** Serial number for serialization. */
    private static final long serialVersionUID = 202110242109L;

    /** Product's identifier */
    private String _ident;

    /** Product's price, set by the partner during purchase*/
    private double _price;


    /** Product's highest known price (Historial) */
    private double _maxPrice;


    /** Product's minimum known price (Historial) */
    private double _minPrice;


    /** Tracks product's total stock */
    private int _total_stock;


    /** Product main Constructor
    *@param id
    *@param price 
     */

    public Product(String id, double price,int stock){
        _ident = id;
        _price = price;
        _maxPrice = price;
        _minPrice = price;
        _total_stock = stock;
    }

    // to implement in subclasses



    /** Returns product's Identifier */
    public String getId(){
        return _ident;
    }


    /** Returns product's price */
    public double getPrice(){
        return _price;

    }


    /** Returns max known price */
    public double getMaxPrice(){
        return _maxPrice;
    }


    /** Returns min known price */
    public double getMinPrice(){
        return _maxPrice;
    }
    

    /** Updates product's price*/
    public void setPrice(double value){
        
    }

    /** Updates product's total stock*/
    public void updateProductInnerStock(int value){
        _total_stock += value;
    }

    
    /** Returns product's total stock */
    public int getStock(){
        return _total_stock;
    }


}