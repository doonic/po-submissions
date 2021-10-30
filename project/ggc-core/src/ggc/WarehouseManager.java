package ggc;


// import exceptions
import java.io.IOException;
import java.io.FileNotFoundException;
import ggc.exceptions.MissingFileAssociationException;
import ggc.exceptions.ImportFileException;
import ggc.exceptions.UnavailableFileException;
import ggc.exceptions.BadEntryException;
import ggc.exceptions.DuplicatePartnerKeyExceptionMain;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

/** Façade for access. */
public class WarehouseManager{

  /** Name of file storing current store. */
  private String _filename = "";

  /** The warehouse itself. */
  private Warehouse _warehouse = new Warehouse();

  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods

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

    //FIXME implement serialization method

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
      DuplicatePartnerKeyExceptionMain e) /*FIXME maybe other exceptions */ {
	    throw new ImportFileException(textfile); 
    }
  }



  public String getFileName(){
    return _filename;
  }


/*________________________________DATE_MODULE_______________________*/

/**
 *
 *@return current warehouse date
 */
public int getDate(){
  return _warehouse.getDateW();
}


/**
*Advances Warehouse Date
*
*@param value, value to add to the current Warehouse Date
*
*/
public void setDate(int value){
  _warehouse.setDateW(value);
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
    _warehouse.registerPartner(idPartner, name, address);
  }


  /**
  * Returns all partners as an unmodifiable collection.
  * 
  *@return a collection with all partners.
  */
  public Collection<Partner> getPartnersAll() {

    return _warehouse.partnersAll();
  }


  /**
  * Returns a Partner
  * 
  *@return partner whose identifier matches the given
  */
  public Partner getPartner(String idPartner){
    return _warehouse.getPartner(idPartner);
  }


/*------------------------------------------------------------------*/


/*________________________PRODUCT_MODULE____________________________*/


public void registerDerivedBatch(String idProduct,String idPartner
  ,double price,int stock,double factor,String components){

    /** Registers a Derived Product (Batch) */
  _warehouse.registerDerivedBatch(idProduct, idPartner, price, stock,
   factor, components);
}

public void registerSimpleBatch(String idProduct, String idPartner
  ,double price,int stock) /** FIX ME THINK OF EXCEPTIONS */{

    /** Registers a Simple Product (Batch)*/
   _warehouse.registerSimpleBatch(idProduct, idPartner, price
  ,stock);

}
  /**
  * Returns all products as an unmodifiable collection.
  * 
  *@return a collection with all products.
  */
  public Collection<Product> getProducts() {

    return _warehouse.productAll();
  }


  /**
  * Returns all batches as an unmodifiable collection.
  * 
  *@return a collection with all batches.
  */
  public Collection<Batch> getBatches(){
    return _warehouse.batchesAll();
  }


























/*------------------------------------------------------------------*/

}
