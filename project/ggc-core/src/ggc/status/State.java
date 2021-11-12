package ggc.status;

import java.io.Serializable;
import ggc.Partner;

public abstract class State implements Serializable {
  

  /** Serial number for serialization. */
  private static final long serialVersionUId = 202109192018L;
  /** Partner associated to the current status */
  private Partner _partner;

  public State(Partner partner){
    _partner = partner;
  }

  /**
  * Changes the current partner's level to an inferior
  */
  public abstract void statusDown();

  /**
  * Changes the current partner's level to a higher
  */
  public abstract void statusUp();

  /**
   * 
   * @return partner
   */
  public Partner getStatePartner(){
    return _partner;
  }


 

}
