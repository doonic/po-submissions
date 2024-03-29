package ggc.app.lookups;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.WarehouseManager;
//FIXME import classes

/**
 * Lookup payments by given partner.
 */
public class DoLookupPaymentsByPartner extends Command<WarehouseManager> {

  public DoLookupPaymentsByPartner(WarehouseManager receiver) {
    super(Label.PAID_BY_PARTNER, receiver);
    addStringField("key",Prompt.partnerKey());
  }

  @Override
  public void execute() throws CommandException {
    String key = stringField("key");
    /** test for registered partner */
    if(!_receiver.checkPartnerRegistryStatus(key)) {
        throw new UnknownPartnerKeyException(key);
    }else{
         // _receiver.
      }
    }
}
