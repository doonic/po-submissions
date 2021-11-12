package ggc.status;

import ggc.Partner;

public class elitePartner extends State {

    /** Serial number for serialization. */
    private static final long serialVersionUId = 202109192024L;

    /**
     * 
     * @param Partner
     */
    public elitePartner(Partner partner) {
        super(partner);
    }

    /**
    * Changes the current partner's level to an inferior
    */
    @Override
    public void statusDown(){
        getStatePartner().updateState(
            new selectionPartner(getStatePartner()));

    }

    /**
    * Changes the current partner's level to a higher
    */
    @Override
    public void statusUp(){
      /** does nothing 
       * ELITE state is the highest 
       */
    }

    /**
    * 
    * @return partner's current status
    */
    @Override
    public String toString(){
        return "ELITE";
    }

}

