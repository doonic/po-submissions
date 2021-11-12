package ggc.status;


import ggc.Partner;

public class normalPartner extends State {

    /** Serial number for serialization. */
    private static final long serialVersionUId = 202109192020L;

    /**
     * 
     * @param Partner
     */
    public normalPartner(Partner partner) {
        super(partner);
    }

    /**
    * Changes the current partner's level to an inferior
    */
    @Override
    public void statusDown(){
        /** does nothing
         * the NORMAL statue is the most inferior
         */

    }

    /**
    * Changes the current partner's level to a higher
    */
    @Override
    public void statusUp(){
        getStatePartner().updateState(
            new selectionPartner(getStatePartner()));
    }

    /**
    * 
    * @return partner's current status
    */
    @Override
    public String toString(){
        return "NORMAL";
    }
    
}

