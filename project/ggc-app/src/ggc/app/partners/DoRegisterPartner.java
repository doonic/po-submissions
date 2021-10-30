package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;

//FIXME import classes

import ggc.exceptions.DuplicatePartnerKeyExceptionMain;
import ggc.app.exceptions.*;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);
    addStringField("PartnerId",Prompt.partnerKey());
    addStringField("PartnerName",Prompt.partnerName());
    addStringField("PartnerAddress",Prompt.partnerAddress());
  }

  @Override
  public void execute() throws CommandException{
    try {
      _receiver.registerPartner(stringField("PartnerId")
      ,stringField("PartnerName"),stringField("PartnerAddress"));
    } catch ( DuplicatePartnerKeyExceptionMain e){
      throw new DuplicatePartnerKeyException(e.getPartnerKey());
    }
  }

}
