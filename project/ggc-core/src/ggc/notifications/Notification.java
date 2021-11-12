package ggc.notifications;

import java.io.Serializable;

public class Notification implements Serializable {


    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111082119L;

    /** Product's identifier */
    private String _uiProduct;

    /** current product's event */
    private String _event;

    /** product's price */
    private double _price;

    /**
     *  notification delivery Mode (default: register 
     * in the Observer (Partner ) */
  
    String _deliveryPath;


    /**
     * 
     * @param productId 
     * @param event
     * @param price
     * @param delivery
     */
    public Notification(String event,String DeliveryPath,
    String productId,double price){

        _event = event;
        _deliveryPath = DeliveryPath;
        _uiProduct = productId;
        _price = price;

    }
    
    /**
     * 
     * @return product's identifier
     */
    public String getProductId(){
        return _uiProduct;
    }

    /**
     * 
     * @return current product's event
     */    
    public String getNotificationEvent(){
        return _event;
    }

    /**
     * 
     * @return product's price (responsible for the notification)
     */
    public double getPrice(){
        return _price;
    }

    /**
     * 
     * @return the chosen delivery path
     */
    public String getDeliveryPath(){
        return _deliveryPath;
    }

     /**
     * 
     * @return notification's string formatting
     */
    @Override
    public String toString() {
        return getDeliveryPath() + getNotificationEvent() + "|" + 
        getProductId()+ "|" + (int)Math.round(getPrice());
    }
}

