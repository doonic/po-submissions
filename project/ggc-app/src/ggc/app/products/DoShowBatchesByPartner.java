package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import ggc.WarehouseManager;


import ggc.app.exceptions.UnknownPartnerKeyException;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    addStringField("PartnerID",Prompt.partnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    String key = stringField("PartnerID");
    if(!_receiver.checkPartnerRegistryStatus(key)){
      throw new UnknownPartnerKeyException(key);
    }else{
      _display.popup(_receiver.getBatchesByPartner(key));
    }
  }
}