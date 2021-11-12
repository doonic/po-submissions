package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;

//FIXME import classes

import ggc.app.exceptions.DuplicatePartnerKeyException;

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

    String key  = stringField("PartnerId");
    if(_receiver.checkPartnerRegistryStatus(key)){
      throw new DuplicatePartnerKeyException(key);
    }else{
        _receiver.registerPartner(key,stringField("PartnerName"),
        stringField("PartnerAddress"));

    }
  }

}
