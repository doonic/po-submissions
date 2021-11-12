package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
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
    }else{ /** Display a sales */
      _display.popup(_receiver.getSale(key));
    }
  }

}
