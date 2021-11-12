package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;
import ggc.WarehouseManager;


/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);
    addStringField("ProductKey",Prompt.productKey());
    addStringField("PartnerKey",Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String productKey = stringField("ProductKey");
    String partnerKey = stringField("PartnerKey");

    if(!_receiver.checkProductRegistryStatus(productKey)){
      throw new UnknownProductKeyException(productKey);
    }
    
    if(!_receiver.checkPartnerRegistryStatus(partnerKey)){
      throw new UnknownPartnerKeyException(partnerKey);
    }
  }

}
