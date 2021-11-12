package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    addStringField("key",Prompt.productKey());
  }

  @Override
  public final void execute() throws CommandException {
    String key = stringField("key");
    if(!_receiver.checkProductRegistryStatus(key)){
      throw new UnknownProductKeyException(key);
    }else{
      _display.popup(_receiver.getBatchesByProduct(key));
    }
  }
}
