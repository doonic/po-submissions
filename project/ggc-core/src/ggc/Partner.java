package ggc;

import ggc.notifications.*;
import ggc.status.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

public class Partner implements Observer{

    /** Serial number for serialization.  */ 
    private static final long serialVersionUID = 202111100919L;

    /** Partner's Unique Identifier, key */
    private String _ident;

    /**  Partner's name */
    private String _name;

    /** Partner's Address */
    private String _address;

    /** Partner's current status */
    private State _status;

    /** Partner's business points */
    private double _pointsTracker;

    /** Total partner's purchases */
    private int _acquisitions;

    /** Total partner's sales*/
    private double _sales;

    /** partner's paid transactions */
    private double _paidTransactions;

    /** partner's chosen notification delivery path */
    private DeliveryPath _deliveryPath;

    /** List of warehouse's acquisitions associated to the partner */
    private List<Transaction> _purchases;

    /** List of Warehouse's sales to the partner and its asked product breakdown */
    private List<Transaction> _salesBreakdown;

    /** List of partner's notifications to be read */
    private ArrayDeque<Notification> _notifications;

     /**
     * Partner's general constructor 
     * @param partnerId
     * @param name
     * @param address
     * @param deliveryPath
     */
    public Partner(String partnerId, String name, String address,
    DeliveryPath deliveryPath){
        _ident = partnerId;
        _name = name;
        _address = address;
        _pointsTracker = 0;
        _acquisitions = 0;
        _sales = 0;
        _paidTransactions = 0;
        _deliveryPath = deliveryPath;
        _status = new normalPartner(this);
        _purchases = new ArrayList<Transaction>();
        _salesBreakdown = new ArrayList<Transaction>();
        _notifications = new ArrayDeque<Notification>();

    }

    /**
     * Partner's default constructor 
     * @param partnerId
     * @param name
     * @param address
     */
    public Partner(String partnerId, String name, String address) {
        /** Register the delivery on the Partner */
        this(partnerId,name,address,new ClassicalNotificationDelivery( 
        ));
    }

    /** Getters */

    /**
     * 
     * @return partner's name 
     */
    public String getPartnerName(){
        return _name;
    }

    /**
     * 
     * @return partner's unique identifier
     */
    public String getPartnerId(){
        return _ident;
    }

    /**
     * 
     * @return partner's Address 
     */
    public String getPartnerAddress(){
        return _address;
    }

    /**
     * 
     * @return points
     *         used to operate discounts/prizes operate over the partner
     */
    public double getPartnerPoints(){
        return _pointsTracker;
    }

    /**
     * 
     * @return partner's sales
     */
    public double getSalesPartner(){
        return _sales;
    }


    /**
     * 
     * @return partner's total purchases 
     */
    public double getPurchasesPartner(){
        return _acquisitions;
    }

    /**
     * 
     * @return partner's status
     */
    public String getPartnerStatus(){
        return _status.toString();
    }

    /**
     * 
     * @param state
     */
    public void updateState(State state){
        _status = state;
    }

    /**
     * 
     * set Partner to a higher status
     */
    public void Up(){
        _status.statusUp();
    }

    /**
     * 
     * set Partner to an inferior status
     */
    public void Down(){
        _status.statusDown();
    }

    /**
     * 
     * @return partner's total amount of
     *         paid transactions
     */
    public double getPaidTransaction(){
        return _paidTransactions;

    }

     /**
     * Updates the observer to changes on the Observable
     * (notification)
     * 
     * @param notification
     */
    public void update(String event,String productId,double price){
        _notifications.add(
        _deliveryPath.notificationDeliverySystem(
            event,productId,price));
 
    }

    /**
     * 
     * @return and remove notification at the head of the queue (null 
     *  if the deque is empty)
     */
    public Notification getNotification(){
        return _notifications.poll();
    }


    /**
     * 
     * @return a collection of all warehouse acquisitions transactions
     *  associated to the Partner
     */
    public Collection<Transaction> getPartnerAcquisitionArchive(){
        List<Transaction> archive = new ArrayList<Transaction>();
        archive.addAll(archive);
        return Collections.unmodifiableCollection(archive);

    }

    /** Setters */

    /**
     * Adds the given transaction to the partner's achive
     * 
     * @param transaction 
     */
    public void addPartnerTransactionArchive(
        Transaction transaction,int mode){
            if(mode == 0){
                _purchases.add(transaction);
            }else{
                _salesBreakdown.add(transaction);
            }
    }

    
    /**
     * 
     * @param points ,to be added to the partner's points tracker
     *         
     */
    public void setPartnerPoints(double points){
        _pointsTracker = points;
    }
    
    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getPartnerId() + "|" + getPartnerName() + "|" 
         + getPartnerAddress() + "|" +  getPartnerStatus() + "|"
         + (int)Math.round(getPartnerPoints()) + "|"
        + (int)Math.round(getPurchasesPartner()) + "|" + 
        (int)Math.round(
            getSalesPartner())
         + "|" +  (int)Math.round(getPaidTransaction());
 
    }
}
