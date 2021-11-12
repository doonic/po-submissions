package ggc.notifications;


/** Represents the default delivery path
 * Register in the Observer (partner)
 */
public class ClassicalNotificationDelivery implements DeliveryPath{

    
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202111091410L;

    /**
     * 
     * @param event
     * @param price
     * @param stock
     * @return
     */
    @Override
    public Notification notificationDeliverySystem(String event,
    String productId,double price){
        return new  Notification(event,"",productId,price);

    }
}

