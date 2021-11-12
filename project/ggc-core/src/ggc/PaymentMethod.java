package ggc;

import ggc.status.*;
import java.io.Serializable;

/**
 * There's 4 different periods associated to each of the 
 * product status, from P1 to P4
 */
public abstract class  PaymentMethod implements Serializable{ 

    /**
     * 
     * @param transaction , to be paid
     */
    public abstract void payTransaction(Transaction transaction);

}