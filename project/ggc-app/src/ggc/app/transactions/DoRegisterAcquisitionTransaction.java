package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.*;
import ggc.WarehouseManager;
import ggc.app.exceptions.UnknownPartnerKeyException;

//FIXME import classes

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("PartnerKey",Prompt.partnerKey());
    addStringField("ProductKey",Prompt.productKey());
  }

  @Override
  public final void execute() throws CommandException {
    String partner = stringField("PartnerKey");
    String product = stringField("ProductKey");

   
    if(!_receiver.checkPartnerRegistryStatus(partner)){
      throw new UnknownPartnerKeyException(partner);

      
    }else if(_receiver.checkProductRegistryStatus(product)){  
      addIntegerField("Amount",Prompt.amount());
      addRealField("Price",Prompt.price());

      _receiver.registerAcquisitionKnownOrSimpleProduct(
        partner,product,realField("Price"),integerField("Amount"));     
    }else{
       /**The product is not known in the Warehouse */
       boolean query = Form.confirm(Prompt.addRecipe());
       if(query){
         /** We want to buy a derived Product */
         addIntegerField("NumberOfComponents",Prompt.numberOfComponents());
         addRealField("Alpha",Prompt.alpha());
         addIntegerField("Amount",Prompt.amount());
         addRealField("Price",Prompt.price());
         
        
         
         int n = integerField("NumberOfComponents");
         int iterate = 0;
         double alpha = realField("Alpha");
         String recipe = "";
         String sep = "#";
         String sepInner = ":";

         while(iterate < n){
           addStringField("ProductKey",Prompt.productKey());
           addIntegerField("Amount",Prompt.amount());

           recipe += stringField("ProductKey")+sepInner+String.valueOf(integerField("Amount"));
           while(iterate <= n-1){
             recipe += sep;

           }
           iterate++;
         }

         // now we can register a derived product as usual
         _receiver.registerAcquisitionDerivedProduct(
            partner,realField("Price"),integerField("Amount")
            ,alpha,recipe);
         
       }else{
         /** We want to buy a simple Product
         register it as we normally would */
        _receiver.registerAcquisitionKnownOrSimpleProduct(
        partner,product,realField("Price"),integerField("Amount")); 
         
       }
      }

    }

}
