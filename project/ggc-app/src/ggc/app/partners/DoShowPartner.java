package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("PartnerKey",Prompt.partnerKey());

    //FIXME add command fields
  }

  @Override
  public void execute() throws CommandException {

    String testInput = stringField("PartnerKey");
    _display.popup(_receiver.getPartner(testInput));
  }

}
