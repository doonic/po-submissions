package ggc;


// import exceptions
import java.io.IOException;
import java.io.FileNotFoundException;
import ggc.exceptions.MissingFileAssociationException;
import ggc.exceptions.ImportFileException;
import ggc.exceptions.UnavailableFileException;
import ggc.exceptions.BadEntryException;
import ggc.exceptions.DuplicatePartnerKeyExceptionMain;

import ggc.notifications.*;
import ggc.status.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;


/** Façade for access. */
public class WarehouseManager{

  /** Name of file storing current store. */
  private String _filename = "";

  /** The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  /**
   *@throws IOException
   *@throws FileNotFoundException
   *@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, 
  MissingFileAssociationException {

    ObjectOutputStream out = new ObjectOutputStream(
    new BufferedOutputStream(new FileOutputStream(_filename)));
    out.writeObject(_warehouse);
    out.close();
  }

  /**
   *@param filename
   *@throws MissingFileAssociationException
   *@throws IOException
   *@throws FileNotFoundException
   */
  public void saveAs(String filename)
   throws MissingFileAssociationException, FileNotFoundException, 
   IOException {

    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException ,
   ClassNotFoundException{

    try{
      ObjectInputStream in = new ObjectInputStream(
      new BufferedInputStream(new  FileInputStream(filename)));
      _warehouse = (Warehouse)in.readObject();
      _filename = filename;
      in.close();

    }catch(IOException e){
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
	    _warehouse.importFile(textfile);

    } catch (IOException | BadEntryException| 
      DuplicatePartnerKeyExceptionMain e){
	    throw new ImportFileException(textfile); 
    }
  }

  public String getFileName(){
    return _filename;
  }


/*______________________Date_Module_________________________________*/

  /**
  *
  * @return current warehouse date
  */
  public int getDate(){
    return _warehouse.getDateW();
  }


  /**
  *Advances Warehouse Date
  *
  * @param value, value to add to the current Warehouse Date
  *
  */
  public void setDate(int value){
    _warehouse.setDateW(value);
  }


/*__________________________________________________________________*/

/*_________________________Accounting_Module________________________*/

   /**
   * 
   * @return warehouse Available Balance(display formatting)
   */
  public int showAvailableBalance(){
    return _warehouse.getAvailableBalanceDisplay();


  }

  /**
   * 
   * @return warehouse Book Balance (display formatting)
   */
  public int showBookBalance(){
    return _warehouse.getBookBalanceDisplay();
  }

  /**
/*__________________________________________________________________*/

/*________________________Partner_Module____________________________*/
  /**
  * Registers a Partner 
  *
  * @param partnerId Partner's identifier
  * @param name Partner's name
  * @param address Partner's address
  *  
  * 
  **/

  public void registerPartner(String partnerId, String name, 
  String address){
    _warehouse.registerPartner(partnerId ,name, address);
  }


  /**
  * Returns all partners as an unmodifiable collection.
  * 
  * @return a collection with all partners.
  */
  public Collection<Partner> getAllPartners() {
    return _warehouse.partnersAll();
  }


  /**
  * 
  *@return partner whose identifier matches the given
  */
  public Partner getPartner(String partnerId){
    return _warehouse.getPartner(partnerId);
  }


/*__________________________________________________________________*/

/*________________________Product_Module____________________________*/

/**
 * 
 * @param productId
 * @return total known stock of the given product
 */
public int getProductStock(String productId){
  
  return _warehouse.getProduct(productId).getStock();
}

/**
* Register a derived product Batch 
*
* @param productId product's Identifier
* @param partnetId partner's Identifier
* @param price product's price per unit
* @param stock available stock
* @param factor multiplicative factor, used to compute the price
* @param components components, different products(simple or complex)
                   which combined produces the derived product
*/
public void registerDerivedBatch(String productId,String partnerId
  ,double price,int stock,double factor,String components){

    /** Registers a Derived Product (Batch) */
  _warehouse.registerDerivedBatch(productId, partnerId, price, stock,
   factor, components);
}

public void registerSimpleBatch(String productId, String partnerId
  ,double price,int stock) /** FIX ME THINK OF EXCEPTIONS */{

    /** Registers a Simple Product (Batch)*/
   _warehouse.registerSimpleBatch(productId, partnerId, price
  ,stock);

}
  /**
  * Returns all products as an unmodifiable collection.
  * 
  *@return a collection with all products.
  */
  public Collection<Product> getAllProducts() {

    return _warehouse.productAll();
  }

  /**
  * Returns all batches as an unmodifiable collection.
  * 
  *@return a collection with all batches.
  */
  public Collection<Batch> getAllBatches(){
    
    return _warehouse.batchesAll();
  }

   /**
   * @param productId , product's identifier
   * @return a collection with all batches of a given product
   */
  public Collection<Batch> getBatchesByProduct(String productId){

    return _warehouse.batchesByProduct(productId);
  }

  /**
   * @param partnerId , partner's identifier
   * @return a collection with all batches whose partner is the given
   */
  public Collection<Batch> getBatchesByPartner(String productId) 
  {
    return _warehouse.batchesByPartner(productId);
  }

/*______________________LookUp_Module_______________________________*/

  /**
   * @param priceLimit , price
   * @return a collection with all batches whose price is less
   * than the given 
   */
  public Collection<Batch> getBatchesLookUpPrice(double price) 
  {
    return _warehouse.batchesLessPrice(price);
  }

  

/*__________________________________________________________________*/


/*______________________Transaction_Module__________________________*/

public Transaction getSale(int id){
  return _warehouse.getSale(id);
}

/**
* @return true if the given key is of a registered transaction
*/
public boolean isTransactionAccepted(int key){
  return _warehouse.isTransactionRegistered(key);
}

/**
* @return true if the given transaction is payable by partners
*/
public boolean isPayable(int key){
  return _warehouse.isTransactionPayable(key);
}

/**
 * Register a Sale Transaction
 * 
 * @param partnerId
 * @param productId
 * @param paymentDeadline
 * @param amount
 */
public void registerSale(String partnerId,
String productId,int paymentDeadline,int amount){
  _warehouse.registerSaleTransaction(
    partnerId,productId,paymentDeadline,amount);

}

/**
 * Register an Acquisition Transaction
 * 
 * @param partnerId
 * @param productId
 * @param paymentDeadline
 * @param amount
 */
public void registerAcquisitionKnownOrSimpleProduct(
  String partnerId,String productId,double price,int amount){
    _warehouse.registerAcquisitionTransaction(
    partnerId,productId,price,amount);

}

public void registerAcquisitionDerivedProduct(
String partnerId,double price,int stock,double factor,String components){
  // TO IMPLEMENT
}

/**
 * Register a Breakdown Transaction
 * 
 * @param partnerId
 * @param productId
 * @param amount
 */
public void RegisterBreakDown(
  String partnerId,String productId,int amount){
    _warehouse.RegisterBreakDownTransaction(
      partnerId,productId,amount);
}


/**
 * Receive transaction payment (sales)
 * 
 * @param transactionId
 */
public void receivePayment(int transactionId){
  _warehouse.receivePaymentTransaction(transactionId);
}




/*__________________________________________________________________*/



/*______________________Verifiers_Module____________________________*/
  /**
   * 
   * @param partnerId
   * @return true if the given partnerId is already registered in the
   * warehourse,otherwise,false.
   */
  public boolean checkPartnerRegistryStatus(String partnerId){
    return _warehouse.checkPartnerRegistryStatus(partnerId);
  }


  /**
   * 
   * @param productId
   * @return true if the given partnerId is already registered in the
   * warehourse,otherwise,false.
   */
  public boolean checkProductRegistryStatus(String productId){
    return _warehouse.checkProductRegistryStatus(productId);
  }


/*__________________________________________________________________*/


  /**
   * Given a product and partner id, if the product is on the partner
   * notifications subscription (list) it'll be removed, otherwise, added
   * 
   * @param productId
   * @param partnerId
   * @return current notification status (on or off)
   */
  public boolean toggleNotifications(String productId, 
  String partnerId) {
    return _warehouse.toggleProductPartnerNotification(productId,
    partnerId);
  }

/*__________________________________________________________________*/


}
