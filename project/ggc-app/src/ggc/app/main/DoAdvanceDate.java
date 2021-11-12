package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.*;

import ggc.WarehouseManager;

import ggc.app.exceptions.InvalidDateException;

//FIXME import classes

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("DateToAdvance",Prompt.daysToAdvance());
  }
  @Override
  public final void execute() throws CommandException, InvalidDateException{

    int testInput = integerField("DateToAdvance");


    if(testInput <= 0 ){
      throw new InvalidDateException(testInput);
    }else{
      int warehouseDate = _receiver.getDate();
      int dateOperation = warehouseDate + testInput;
      _receiver.setDate(dateOperation);

    }
  }

}
