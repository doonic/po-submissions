package ggc;

import java.io.Serializable;
import ggc.notifications.*;
import java.util.List;
import java.util.ArrayList;



public abstract class Product implements Subject{

    /**  Serial number for serialization. */ 
    private static final long serialVersionUID = 202110242109L;

    /** Product's identifier */
    private String _productId;

    /** Product's price, set by the partner during purchase*/
    private double _price;

    /** Product's highest known price (Archive) */ 
    private double _maxPrice;

    /** Product's minimum known price (Archive) */ 
    private double _minPrice;

    /**Tracks product's total stock 
    takes in account different batches of the same 
    product
    */
    private int _totalKnownStock = 0;

    /** list that holds the Observers */
    private List<Observer> _observers;

    /** current product's notification type */
    private String _notificationType = "";

    /** Product main Constructor
    *@param id
    *@param price 
     */
    public Product(String id, double price,int stock){
        _productId = id;
        _price = price;
        _maxPrice = price;
        _minPrice = price;
        _totalKnownStock = stock;
        _observers = new ArrayList<Observer>();
    
    }

    // Getters 

    /**
     * 
     * @return product's identifier
     */
    public String getProductId(){
        return _productId;
    }

    /**
     * 
     * @return product's price
     */
    public double getPrice(){
        return _price;

    }

    /***
     * 
     * @return product's max known price 
     */
    public double getMaxPrice(){
        return _maxPrice;
    }

    /**
     * 
     * @return product's min known price
     */
    public double getMinPrice(){
        return _minPrice;
    }
    
    /**
     * 
     * @param o , the observer
     * @return true ,if the observer is on the product's observers 
     * list.Otherwise,false.
     * 
     */
    public boolean isProductBeingObserved(Observer o){
        return _observers.contains(o);
    }

    // Setters
    /**
     * Updates product's price
     * and if plausible toggle BARGAIN (notification type) 
     * @param price
     */
    public void setPrice(double price){     
        _price = price;

    }

    /**  Sets the maxPrice */
    public void setMaxPrice(double price){
        _maxPrice = price;
    }

    /**
     * Sets the minimum Price
     * and if plausible toggle BARGAIN (notification type) 
     * @param price
     */
    public void setMinPrice(double price){
        if(Double.compare(getMinPrice(),getMaxPrice()) == 0){
            /** does not, freshly created product */
        }else{
            _minPrice = price;
            _notificationType = "BARGAIN";
            notifyObservers();
        }
    }

    /**
     * Increases the total known stock of a product
     * @param stock
     */
    public void addToProductKnownStock(int stock){
        _totalKnownStock += stock;
        if(_totalKnownStock == stock){
            _notificationType = "NEW";
            notifyObservers();
        }
    }

    /**
     * Decreases the total known stock of a product
     * @param stock
     */
    public void subToProductKnownStock(int stock){
        _totalKnownStock -= stock;
    }
    
    /**
     * 
     * @return product's total known stock
     */
    public int getStock(){
        return _totalKnownStock;
    }

    /**
     * 
     * @return the current notification type e.g NEW BARGAIN
     */
    public String getNotificationType(){
        return _notificationType;
    }

    public abstract boolean canBreakDown();

    /**
     * 
     * @param o , Observer to be registered to the product observer's 
     * list
     */
    @Override
    public void registerObserver(Observer o){
        _observers.add(o);
        
    }
    /**
     * 
     * @param o ,Observer to be removed from the product observer's 
     * list
     */
    @Override
    public void removeObserver(Observer o){
        _observers.remove(o);
    }

    /**
     * Notify all observers of product's state changes
     *      
     */
    @Override
    public void notifyObservers(){
        for(Observer o : _observers) {
            o.update(getNotificationType(),getProductId(),getPrice());
        }
    }


}