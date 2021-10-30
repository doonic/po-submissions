package ggc;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Partner implements Serializable{

    /** Partner's Unique Identifier, key */
    private String _ident;

    /** Partner's name */
    private String _name;

    /** Partner's Address */
    private String _address;

    /** Partner's current status */
    private PartnerStatus _status = new PartnerStatusNormal();

    /** Partner's business points */
    private int _pointsTracker = 0;

    /** Tracks partner's purchases */
    private int _purchasesAmount = 0;

    /** Tracks partner's sales*/
    private int _salesAmount = 0;

    
    /** Tracks partner's paid transactions */
    private int _transactionPaid = 0;

    /** List of partner's purchases from the Warehouse */
    private List<Purchases> __purchases = new ArrayList<Purchases>();

    // TO IMPLEMENT


    /** List of partner's Sales and Breakdown of products */


    // TO IMPLEMENT 

    // client constructors

    public Partner(String ident, String name, String address) {
        _ident = ident;
        _name = name;
        _address = address;
    }





    /** Getters and Setters*/

    /** Returns partner's name */
    public String getPartnerName(){
        return _name;
    }


    /** Returns partner's unique identifier */
    public String getId(){
        return _ident;
    }

    /** Returns partner's Address */
    public String getAddress(){
        return _address;
    }


    /** Returns partner's points, the number of points is used to operate
    offers and prizes over the partner */
    public int getPartnerPoints(){
        return _pointsTracker;
    }

    /** Returns partner's sales number */
    public int getSalesPartner(){
        return _salesAmount;
    }


    /** Returns partner's purchases number*/
    public int getPurchasesPartner(){
        return _purchasesAmount;
    }

    /** Returns parner's status */
    public String getStatusName(){
        return _status.toString();
    }
    /** Updates partner's status */
    public void changeStatus(PartnerStatus status){
        _status = status;
    }


    /** Returns partner's total amount of
     * paid transactions */
    public int getPaidTransaction(){
        return _transactionPaid;

    }


    /** @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return getId() + "|" + getPartnerName() + "|" 
         + getAddress() + "|" + getStatusName() + "|"
         + getPartnerPoints() + "|"
        + getPurchasesPartner() + "|" + getSalesPartner()
         + "|" +  getPaidTransaction();
 
    }
}


// TO IMPLEMENT
/** List of partner's Sales and Breakdown of products */
