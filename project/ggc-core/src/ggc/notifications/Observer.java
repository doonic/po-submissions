package ggc.notifications;
import java.io.Serializable;

/** The Observer interface will only be implemented
by objects who want to be notified about changes to
the subject(as of now the only subject is the product).
Partners are observing changes to to the product (The observer)
it'll have to implement the observer interface as well its method
(update()) */

public interface Observer extends Serializable{

    /**
     * Updates the observer to changes on the Observable
     * @param event
     * @param productId
     * @param price
     */
    public void update(String event,String productId,double price);
}