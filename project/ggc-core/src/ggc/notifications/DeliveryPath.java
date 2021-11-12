package ggc.notifications;

import java.io.Serializable;

public interface DeliveryPath extends Serializable{

    /**
     * Notification DeliveryMode 
     * a notification can be sent by email, sms ,letter and others
     *
     * @param event
     * @param productId
     * @param price
     * @return
     */
    Notification notificationDeliverySystem(String event,
    String productId,double price);

}