package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.Display;

import ggc.WarehouseManager;


/**
 * Show current date.
 */
class DoDisplayDate extends Command<WarehouseManager> {
  
  DoDisplayDate(WarehouseManager receiver) {
    super(Label.SHOW_DATE, receiver);
  }


  @Override
  public final void execute() throws CommandException {
    int warehouseDate = _receiver.getDate();
    _display.popup(Message.currentDate(warehouseDate));

  }

}
