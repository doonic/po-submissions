package ggc.notifications;
import java.io.Serializable;

/** Subject interface also known as Observable,
the object whose changes will be notified to 
the observers( as of now, product is the only observer) */
public interface Subject extends Serializable{   

    /**
     * 
     * @param o , the observer to be registered
     * 
     */ 
    public void registerObserver(Observer o);
    
    /**
     * 
     * @param o , the observer to be
     */
    public void removeObserver(Observer o);
    
     /**
     * 
     * Notify all observers
     */
    public void notifyObservers();
}