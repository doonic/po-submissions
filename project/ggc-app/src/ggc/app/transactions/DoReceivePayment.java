package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownTransactionKeyException;
//FIXME import classes

/**
 * Receive payment for sale transaction.
 */
public class DoReceivePayment extends Command<WarehouseManager> {

  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    addIntegerField("Id",Prompt.transactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    int key = integerField("Id");
    if(!_receiver.isTransactionAccepted(key)){
      throw new UnknownTransactionKeyException(key);
      /** Checks for Sale Transaction */
    }else if(!_receiver.isPayable(key)){
      /**does nothing */
    }else{ /** Pays the Transaction*/

    }
  }

}
