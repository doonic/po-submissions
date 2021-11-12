package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnavailableProductException;
import ggc.app.exceptions.UnknownPartnerKeyException;
import ggc.app.exceptions.UnknownProductKeyException;

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    addStringField("PartnerKey",Prompt.partnerKey());
    addStringField("ProductKey",Prompt.productKey());
    addIntegerField("Deadline",Prompt.paymentDeadline());
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("PartnerKey");
    String product = stringField("ProductKey");
    int deadline = integerField("Deadline");
  
    int productKnownStock = _receiver.getProductStock(product);
    if(!_receiver.checkPartnerRegistryStatus(partner)){
      throw new UnknownPartnerKeyException(partner);
    }
    else if(!_receiver.checkProductRegistryStatus(product)){
      throw new UnknownProductKeyException(product);
    }
    //else if(productKnownStock< amount){
      //throw new UnavailableProductException(
       //product, amount,productKnownStock);
    //}else{
      //< // FIX THIS
    //}
  }

}
