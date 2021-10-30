package ggc;

import java.io.Serializable;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


import ggc.exceptions.*;



// FIXME import classes (cannot import from pt.tecnico or ggc.app)

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;

  /** Initial value of the Warehouse Date */
  private int _Date = 0;

  /** Available Balance */
  private double _availableBalance = 0;

  /** Book Balance */
  private double _bookBalance = 0;

  /** Transaction Identifier, shared between all types of transactions*/
  private int _transactionId = 0;


  /** Stores Batches */
  private ArrayList<Batch> _batches = new ArrayList<Batch>();

  /** Stores Products */
  private Map<String,Product> _products 
  = new TreeMap<String,Product>(String.CASE_INSENSITIVE_ORDER);

  /** Stores Partners */
  private Map<String,Partner> _partners
  = new TreeMap<String,Partner>(String.CASE_INSENSITIVE_ORDER);



  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods


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


/*_________________________DATE_MODULE______________________________*/

  /**
  * Returns the current Warehouse Date 
   */
  public int getDateW(){
    return _Date;
  }


  /**
  * Advances Warehouse Date
  *@param numDaysToAdvance, numDaysToAdvance to set the system date
  *
  *@throws InvalidDateException
  *      valid numDaysToAdvance > 0
  *
  */
  public void setDateW(int newdate){

      _Date = newdate ; 
  }


/*------------------------------------------------------------------*/


/*________________________PARTNER_MODULE____________________________*/


  /**
  * Registers a Partner 
  *
  *@param idPartner Partner's identifier
  *
  *@param name Partner's name
  *
  *@param address Partner's address
  *
  *@throws DuplicatePartnerKeyException if there's already a partner 
  * with the given idPartner in the Warehouse
  **/

  public void registerPartner(String idPartner, String name, 
  String address) throws DuplicatePartnerKeyExceptionMain{

    /** Checks if the partner's identifier is already registered 
     *in the Warehouse */
    if(_partners.containsKey(idPartner)){
      throw new DuplicatePartnerKeyExceptionMain(idPartner);
    }
    else{
      /** Registers, Adds partner's info to the Warehouse */
      Partner _partner = new Partner(idPartner,name,address);
      _partners.put(idPartner, _partner);
    }
  }


  /**
  * Returns all partners as an unmodifiable collection.
  * 
  *@return a collection with all partners.
  */
  public Collection<Partner> partnersAll() {
    return Collections.unmodifiableCollection(_partners.values());
  }


    /**
  * Returns a partner given its unique identifier
  *
  *
  *@param idPartner
  *@return a partner .
  */

  public Partner getPartner(String idPartner){
    return _partners.get(idPartner);
  }
/*------------------------------------------------------------------*/




/*------------------------PRODUCT_MODULE----------------------------*/

  /**
  *  // FIX ME ADD JAVA DOC
  *
  *
  *
  */
  public void registerDerivedBatch(String idProduct,String idPartner
  ,double price,int stock,double factor,String components) 
  /** FIX ME THINK OF EXCEPTIONS */{

    /** Since derived products are made of other derived or
     *  simple products, my initial approach was to first create 
     * the product recipe 
     *then add to the derived product itself  */

      /**Checks if there's already a batch with the given product id in 
      * the Warehouse and updates the total known stock of the product
      */
    if(searchBatch(idProduct)){
      _products.get(idProduct).updateProductInnerStock(stock);
    } 

    RecipeFactory _newRecipe = new RecipeFactory(factor,components);

    ProductDerived product = new ProductDerived(_newRecipe, price, 
    idProduct,stock);

    Batch batchDerived = new Batch(idPartner, stock, price,idProduct);


    /** Stores the product */
    _products.put(idProduct,product);

    /** Since products are bought,sold,produced in batches */
    _batches.add(batchDerived);
  }


  /**
  *  // FIX ME ADD JAVA DOC
  *
  *
  *
  */
  public void registerSimpleBatch(String idProduct, String idPartner
  ,double price,int stock) /** FIX ME THINK OF EXCEPTIONS */{

    /** Since batches are made of products, my approach was to first 
     * register the product in the warehouse
     * and then link it to a batch */
    

    /** Updates total known stock of a product */
    if(searchBatch(idProduct)){
      _products.get(idProduct).updateProductInnerStock(stock);
    } 
    Product _productRegister = new ProductSimple(idProduct,price,stock);
    
    /** Stores the product  */
    _products.put(_productRegister.getId(),_productRegister);

    _batches.add(new Batch(idPartner,stock,price
    ,_productRegister.getId()));


  }



  /**
  * Returns all products as an unmodifiable collection.
  * 
  *@return a collection with all products.
  */
  public Collection<Product> productAll() {
    return Collections.unmodifiableCollection(_products.values());
  }


  /**
  * Returns all batches as an unmodifiable collection.
  * 
  *@return a collection with all products.
  */
  public Collection<Batch> batchesAll() {

    return Collections.unmodifiableCollection(_batches);
  
  }

  /**
  *  // FIX ME ADD JAVA DOC
  *
  *
  *
  */
  /** Searches for the first batch given a product identifier */
  public boolean searchBatch(String idProduct){
    for(Batch b : _batches){
      if(b._product.toLowerCase().equals(idProduct.toLowerCase())){
        return true;
      }

    }
    return false;
  }
/*------------------------------------------------------------------*/



 
}