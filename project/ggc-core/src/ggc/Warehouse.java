package ggc;

import ggc.notifications.*;
import ggc.status.*;
import ggc.exceptions.*;

import java.io.Serializable;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUId = 202109192006L;

  /** Initial value of the Warehouse Date */
  private int _date = 0;

  /** Available Balance */
  private double _availableBalance = 0;

  /** Book Balance */
  private double _bookBalance = 0;

  /** Transaction Identifier, shared between all types of transactions*/
  private int _transactionId = 0;


  /** Stores Batches */
  private TreeMap<String,ArrayList<Batch>> _batches =
   new TreeMap<String,ArrayList<Batch>>(String.CASE_INSENSITIVE_ORDER);

  /** Stores Products */
  private Map<String,Product> _products 
  = new TreeMap<String,Product>(String.CASE_INSENSITIVE_ORDER);

  /** Stores Partners */
  private Map<String,Partner> _partners
  = new TreeMap<String,Partner>(String.CASE_INSENSITIVE_ORDER);


  /** Stores Transactions */
  private Map<Integer,Transaction> _transactions =
  new TreeMap<Integer,Transaction>();

  /**
   *
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException,
  BadEntryException,DuplicatePartnerKeyExceptionMain
  /*FIXME maybe other exceptions */ {

    BufferedReader reader = new BufferedReader(new FileReader(txtfile));
    String line;

    while((line = reader.readLine())!= null){
      String[] fields = line.split("\\|");
      registerFromFields(fields);
    }
    reader.close();
  }

  void registerFromFields(String[] fields) throws BadEntryException,
  DuplicatePartnerKeyExceptionMain{

    String commandOperation = fields[0];

    switch(commandOperation){
      case "PARTNER":
        registerPartner(fields[1],fields[2],fields[3]);
        break;
      case "BATCH_S":
        double priceParse = Double.parseDouble(fields[3]);
        int stockParse = Integer.parseInt(fields[4]);
        registerSimpleBatch(fields[1],fields[2],priceParse,stockParse);
        break;
      case "BATCH_M":
        double priceParseD = Double.parseDouble(fields[3]);
        int stockParseD = Integer.parseInt(fields[4]);
        double factorParseD = Double.parseDouble(fields[5]);

        registerDerivedBatch(fields[1],fields[2],priceParseD,
        stockParseD,factorParseD,fields[6]);

      default: throw new BadEntryException(commandOperation);

    } 
  }


/*_________________________Date_Module______________________________*/

  /**
   * 
   * @return warehouse date
   */
  public int getDateW(){
    return _date;
  }

  /**
   * 
   * @param newdate
   * 
   */
  public void setDateW(int newdate){
      _date = newdate ; 
  }

/*__________________________________________________________________*/


/*_________________________Accounting_Module________________________*/

  /**
   * 
   * @return warehouse Book Balance
   */
  public double getBookBalance(){
    return _bookBalance;
  }

  /**
   * 
   * @return warehouse Available Balance
   */
  public double getAvailableBalance(){
    return _availableBalance;
  }

   /**
   * 
   * @return warehouse Available Balance(display formatting)
   */
  public int getAvailableBalanceDisplay(){
    return (int)Math.round(_availableBalance);


  }

  /**
   * 
   * @return warehouse Book Balance
   */
  public int getBookBalanceDisplay(){
    return (int)Math.round(_bookBalance);
  }

  /**
/*__________________________________________________________________*/

/*_________________________Notification_Module______________________*/

  /**
   * Subscribe all partners to be observers of a given product
   * 
   * @param product , product to be observed
   */
  public void subscribePartnersToProductNotification(Product product){
    List<Partner> partnersToSubscribe = new ArrayList<Partner>();
    partnersToSubscribe.addAll(_partners.values());
    for(Partner p: partnersToSubscribe){
      product.registerObserver(p);
    }
  }

  /**
   * Given a product and partner id, if the product is on the partner
   * notifications subscription (list) it'll be removed, otherwise, 
   * added
   * 
   * @param productId
   * @param partnerId
   * @return current notification status (on or off)
   */
  public boolean toggleProductPartnerNotification(String productId,
  String partnerId){
    
    /** Obtains the partner since it implements the Observer not 
     * its identifer */
    Observer observer = getPartner(partnerId);
    Product product =  getProduct(productId);
    boolean observing = product.isProductBeingObserved(observer);
    if(observing){
      product.removeObserver(observer);
    }else{
      product.registerObserver(observer);
    }

    return !observing;

  }


/*__________________________________________________________________*/
 
/*________________________Partner_Module____________________________*/


  /**
  * Registers a Partner 
  *
  * @param partnerId Partner's Identifier
  * @param name Partner's name
  * @param address Partner's address
  * 
  * 
  **/

  public void registerPartner(String partnerId, String name, 
  String address){

      /** Registers, Adds partner's info to the Warehouse */
      _partners.put(partnerId,new Partner(partnerId,name,address));
    
  }



  /**
  * Returns all partners as an unmodifiable collection.
  * 
  * @return a collection with all partners.
  */
  public Collection<Partner> partnersAll() {
    return Collections.unmodifiableCollection(_partners.values());
  }


    /**
  * Returns a partner given its unique Identifier
  *
  *
  * @param partnerId
  * @return a partner .
  */

  public Partner getPartner(String partnerId){
    return _partners.get(partnerId);
  }
/*__________________________________________________________________*/

/*________________________Product_Module____________________________*/



  /**
   * Adds a given batch to the Warehouse
   * @param batch
   * @param productId
   */
  public void setBatchesToWarehouse(Batch batch,String productId){

    /** Checks if the product is known */
    if(checkProductRegistryStatus(productId)){
      _batches.get(productId).add(batch);
    }else{
      _batches.put(productId, new ArrayList<Batch>());
      _batches.get(productId).add(batch);
    }

  }

    /**
   * 
   * @param productId
   * @return a Product
   */
  public Product getProduct(String productId){
    return _products.get(productId);

  }


  /**
   * 
   * @param productId , product's identifier
   * @param stock ,product's stock
   * @param price, product's price
   */

  public void updateProductArchive(String productId,int stock,Double 
  price){

      // updates the total known stock of the product */
      getProduct(productId).addToProductKnownStock(stock);
      // updates max price, min price , total known stock
      int cmpPrice = Double.compare(getProduct(productId).getPrice(),price);
      if(cmpPrice < 0){
        getProduct(productId).setMaxPrice(price);
      }else if(cmpPrice > 0){
        getProduct(productId).setMinPrice(price);
      }

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

    /** Since derived products are made of other derived or
    simple products, my initial approach was to first create 
    the product recipe 
    then add it to the derived product itself 
    Checks if there's already a batch with the given product Id in 
    the Warehouse and updates its total known stock

    Verifies whether or not the product already exists in the 
    warehouse */
    boolean productRegistryStatus = false;

    if(checkProductRegistryStatus(productId)){
      productRegistryStatus = true;
    }

    /** creates a new Recipe */
    RecipeFactory _newRecipe = new RecipeFactory(factor,components);
    /** creates a new batch  */
    Batch complexBatch = new Batch(partnerId, stock, price,productId);

    /** Since products are bought,sold,fabricated  in batches 
    follows that */
    setBatchesToWarehouse(complexBatch, productId);
   

    if(!productRegistryStatus){
      DerivedProduct product = new DerivedProduct(_newRecipe,
      price,productId,stock);

      /** when a product is freshly registered its maxprice is equal to
      its min */
      product.setMaxPrice(price);
      product.setMinPrice(price);
      /** registers the product in the warehouse */ 
      _products.put(productId,product); 
     /** Subscribe all partners to product notification */
      subscribePartnersToProductNotification(product);

    }else{
      /**  updates max price, min price , total known stock
      updates the total known stock of the product */
      updateProductArchive(productId,stock,price);

    }
  }


  /**
  * @param productId product's Identifier
  * @param partnetId partner's Identifier
  * @param price product's price per unit
  * @param stock available stock
  *
  */
  public void registerSimpleBatch(String productId, String partnerId
  ,double price,int stock){

    // initially a product is yet to be registered
    boolean productRegistryStatus = false;

    if(checkProductRegistryStatus(productId)){
      productRegistryStatus = true;
      
    }
    Batch simpleBatch = new Batch(partnerId, stock, price,productId);
    setBatchesToWarehouse(simpleBatch, productId);

    if(!productRegistryStatus){
      SimpleProduct product = new SimpleProduct(productId, price, stock);
      product.setMaxPrice(price);
      product.setMinPrice(price);
      _products.put(productId,product); 
      subscribePartnersToProductNotification(product);

    }else{
      updateProductArchive(productId,stock,price);
    }
  }
      
  /**
   * 
   * @return List<Batch> , a list containing batches already sorted
   */
  public List<Batch> batchSortingUtil(){

    List<Batch> temporary_final = new ArrayList<Batch>();

    for (Map.Entry <String,ArrayList<Batch>> element: _batches.entrySet()) {
      ArrayList<Batch> values = element.getValue();
      values.sort(Batch.partnerAllComparator);
      temporary_final.addAll(values);

    }
    return temporary_final;
  }

  
  /**
  * Returns all batches as an unmodifiable collection.
  * 
  *@return a collection with all batches (sorted)
  */
  public Collection<Batch> batchesAll() {
    List<Batch> sortedBatches = new ArrayList<Batch>();
    sortedBatches.addAll(batchSortingUtil());
    return Collections.unmodifiableCollection(sortedBatches);
  }

  /**
   * 
   * @return a collection with all batches of a given product
   */
  public Collection<Batch> batchesByProduct(String productId){
    List<Batch> batchesProduct = new ArrayList<Batch>();
    batchesProduct.addAll(_batches.get(productId));
    return Collections.unmodifiableCollection(batchesProduct);
     
  }

    /**
   * 
   * @return a collection with all batches of a given product
   */
  public Collection<Batch> batchesByPartner(String partnerId){
    List<Batch> batchesPartner = new ArrayList<Batch>();
    batchesPartner.addAll(batchSortingUtil());
    return Collections.unmodifiableCollection(
      batchesPartner.stream().filter(batch -> (
        batch.getPartnerId().compareToIgnoreCase(
          partnerId))== 0).collect(Collectors.toList()));
     
  }

  /**
  * Returns all products as an unmodifiable collection.
  * 
  *@return a collection with all products.
  */
  public Collection<Product> productAll() {
    return Collections.unmodifiableCollection(_products.values());
  }
/*__________________________________________________________________*/


/*______________________LookUp_Module_______________________________*/

  /**
   * 
   * @return a collection with all batches whose price is less than
   * the given
   */
  public Collection<Batch> batchesLessPrice(Double price){
    List<Batch> batchesPrice = new ArrayList<Batch>();
    batchesPrice.addAll(batchSortingUtil());
    return Collections.unmodifiableCollection(
      batchesPrice.stream().filter(b-> (
        b.getpriceU() < price)).collect(Collectors.toList()));
  }


/*__________________________________________________________________*/


/*______________________Transaction_Module__________________________*/



/**
* 
* @return a sale Transaction
* 
*/
public Transaction getSale(int id){
  return _transactions.get(id);
}



/**
* @return true if the given key is of a registered transaction
*/
public boolean isTransactionRegistered(int key){
  return _transactions.containsKey(key);

}

public boolean isTransactionPayable(int key){
  Transaction transaction =  _transactions.get(key);
  return transaction.isTransactionPayableByPartner();
}

/**
* @return transaction's id
*/
public int getTransactionId(){
  return _transactionId;
}
/**
 * Register a sale Transaction
 * 
 * @param partnerId
 * @param productId
 * @param paymentDeadline
 * @param amount
 */
public void registerSaleTransaction(
  String partnerId,String productId,int paymentDeadline,int amount){


    double price = 0;
    int stockTracker = amount;

    Partner partner = getPartner(partnerId);
    Product product = getProduct(productId);
    
    List<Batch> productList = new ArrayList<Batch>();
    productList.addAll(_batches.get(productId));

    /** This process repeats until the desire amount of product 
     * is obtained from the batches
     */
   
    for(Batch b: productList){
      stockTracker -= b.getStock();
      if(stockTracker <= 0){
        price += (stockTracker + b.getStock())*b.getpriceU();
        break;
      }
      else{
        price += b.getpriceU()*b.getStock();
        b.removeStock(b.getStock());
      }
    }

    /** Register the sale */
    Transaction sale = new Sale(
      partner,product,_transactionId,paymentDeadline,amount,price);
    
    /** Register the sale on the partner's archive */
    partner.addPartnerTransactionArchive(sale,1);
    /** Stores the current transaction on the list of all 
    transactions */
    _transactions.put(_transactionId,sale);
    //** sets sale base price */
    sale.setBasePrice(price);

    /** Updates total know stock of the product */
    product.subToProductKnownStock(amount);

     /** Increments transaction's id */
     _transactionId++;

     /** Updates the book balance of the Warehouse */
     _bookBalance += sale.getBasePrice();


}

/***
 *  Register an acquisition Transaction
 * @param partnerId
 * @param productId
 * @param paymentDeadline
 * @param amount
 */
public void registerAcquisitionTransaction(
  String partnerId,String productId,double price,int amount){

    /** Obtains the partner and product
    * associated with the transaction */
    Partner partner = getPartner(partnerId);
    Product product = getProduct(productId);

    /** Register the purchase */
    Transaction purchase = new Acquisitions(
      partner,product,_transactionId,amount,price);

    /** Stores the current transaction on the list of all 
    transactions */
    _transactions.put(getTransactionId(),purchase);


    /**Updates the total know stock of the acquired product */
    product.addToProductKnownStock(amount);

    /** 0 for purchase else sales/breakdown  */
    partner.addPartnerTransactionArchive(purchase,0);

    /**Verifies whether or not the product is derived */
    if(product.canBreakDown()){
      Batch complexBatch = new Batch(partnerId,amount, price,productId);
      setBatchesToWarehouse(complexBatch, productId);
    }else{
      Batch simpleBatch = new Batch(partnerId,amount, price,productId);
      setBatchesToWarehouse(simpleBatch, productId);
    }
    product.addToProductKnownStock(amount);

    /** Increments transaction's id */
    _transactionId++;

     /** Removes balance from the warehouse */
    _availableBalance -= purchase.getBasePrice();
    _bookBalance -= purchase.getBasePrice();
    
  }

/**
 * Register a breakdown Transaction
 * 
 * @param partnerId
 * @param productId
 * @param amount
 */
public void RegisterBreakDownTransaction(
  String partnerId,String productId,int amount){
    // TO IMPLEMENT

}

/**
 * Receive transaction payment (sales)
 * 
 * @param transactionId
 */
public void receivePaymentTransaction(int transactionId){
  // TO IMPLEMENT
}



/*__________________________________________________________________*/


/*______________________Points_Module_______________________________*/

/**
 * Adds points to the partner's points tracker
 * and changes partner's state when certain
 * conditions are met
 * 
 * @param transaction
 */
public void addPoints(Transaction transaction){
  String partnerId = transaction.getPartnerIdTransaction();

  /** Obtains the partner */
  Partner partner = getPartner(partnerId);
  double pointsTracker = partner.getPartnerPoints();
  double basePrice = transaction.getBasePrice();

  pointsTracker += basePrice* 10;
  partner.setPartnerPoints(pointsTracker);

  switch(partner.getPartnerStatus()){
    case "NORMAL":
      if(pointsTracker >= 2000){
        partner.Up();
      }
      if(pointsTracker >= 25000){
        partner.Up();
      }
      break;
    case "SELECTION":
      if(pointsTracker >= 25000) {
        partner.Up();
      }
      break;
    default:
      break;
  }

}

/**
 * Adds points to the partner's points tracker
 * and changes partner's state when certain
 * conditions are met
 * 
 * @param transaction
 */
public void removePoints(Transaction transaction){
  String partnerId = transaction.getPartnerIdTransaction();

  Partner partner = getPartner(partnerId);
  double pointsTracker = partner.getPartnerPoints();
  int paymentGap = transaction.getPaymentDate() - 
  transaction.getPaymentDeadline();
  
  switch(partner.getPartnerStatus()){
    case "ELITE":
      if(paymentGap > 15){
        pointsTracker *= 0.25;
        partner.setPartnerPoints(pointsTracker);
        partner.Down();;
      }
      /** Does nothing */
      break;

    case "SELECTION":
      if(paymentGap > 2){
        pointsTracker *= 0.10;
        partner.setPartnerPoints(pointsTracker);
        partner.Down();;
        }
        break;
    default:
      break;
    }

}

/*__________________________________________________________________*/

/*______________________Verifiers_Module____________________________*/

/**
 * 
 * @param productId products Identifier 
 * @param partnerId partner's Identifier
 * @param price     price associated with the batch
 * @return boolean value
 *         (true) if there's already a batch with the given format
 *         ,that is, same productId,partnerId, price as the given,
 *          otherwise, false
 *       
 */
public boolean checkIfBatchExists(String productId,String partnerId,
double price){
  return false;
}


/**
 * 
 * @param productId
 * @return true if there's already a product with the given Id 
 * in the warehouse
 */
public boolean checkProductRegistryStatus(String productId){
  return _products.containsKey(productId);
}

/**
  * 
  * @param partnerId 
  * @return true if the given partnerId is already registered in the
  * warehourse,otherwise,false.
  */
public boolean checkPartnerRegistryStatus(String partnerId){
    return _partners.containsKey(partnerId);
}


/*__________________________________________________________________*/


}