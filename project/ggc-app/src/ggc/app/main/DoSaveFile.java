package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import ggc.exceptions.MissingFileAssociationException;

import java.io.FileNotFoundException;
import java.io.IOException;


import ggc.WarehouseManager;
//FIXME import classes

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {
  private String _inputFile;

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
  }

  @Override
  public final void execute() throws CommandException {
    if(_receiver.getFileName() == ""){
      _inputFile = Form.requestString(Prompt.newSaveAs());

    }
    try {
      if(_receiver.getFileName() == ""){
        _receiver.saveAs(_inputFile);
      }
       _receiver.save();

    
    }catch(IOException e){
        e.printStackTrace();
    }catch (MissingFileAssociationException e) {
        e.printStackTrace();
    }
  }
}