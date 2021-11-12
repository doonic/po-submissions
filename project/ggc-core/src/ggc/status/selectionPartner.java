package ggc.status;


import ggc.Partner;

public class selectionPartner extends State {


    /** Serial number for serialization. */
    private static final long serialVersionUId = 202109192022L;


    /**
     * 
     * @param Partner
     */
    public selectionPartner(Partner partner) {
        super(partner);
    }
    /**
     * Changes the current partner's level to an inferior
    */
    @Override
    public void statusDown(){
        getStatePartner().updateState(
            new normalPartner(getStatePartner()));
    }

    /**
    * Changes the current partner's level to a higher
    */
    @Override
    public void statusUp(){
        getStatePartner().updateState(
            new elitePartner(getStatePartner()));
    }

    /**
    * 
    * @return partner's current status
    */
    /** @see java.lang.Object#toString() */
    @Override
    public String toString(){
        return "SELECTION";

    }
}
