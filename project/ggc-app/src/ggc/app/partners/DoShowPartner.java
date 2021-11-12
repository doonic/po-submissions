package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("PartnerKey",Prompt.partnerKey());

  }

  @Override
  public void execute() throws CommandException{
    
    String key = stringField("PartnerKey");
      if(!_receiver.checkPartnerRegistryStatus(key)){
        throw new UnknownPartnerKeyException(key);
      }else{
         _display.popup(_receiver.getPartner(key));
      }
  }

}
