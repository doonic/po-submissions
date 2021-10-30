package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

import ggc.WarehouseManager;
import ggc.exceptions.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;

import ggc.app.exceptions.FileOpenFailedException;
//FIXME import classes

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  private String _fileToLoad;

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    //FIXME maybe add command fields
  }

  @Override
  public final void execute() throws CommandException {

    _fileToLoad = Form.requestString(Prompt.openFile());
    
    try{
      _receiver.load(_fileToLoad);
    }
    catch(UnavailableFileException e){
      throw new FileOpenFailedException(_fileToLoad);
 
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
    }
  }

}
