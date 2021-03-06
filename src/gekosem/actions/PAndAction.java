package gekosem.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import gekosem.Gekosem;
import gui.commands.Invoker;
import gui.model.Slot;
import gui.observer.NotificationObserver;
import gui.view.MainView;

public class PAndAction extends AbstractAction{
 
	private Slot slot;
	
	public PAndAction(Slot slot) {
		super();
		this.slot = slot;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		slot.getSelectionModels().removeAllFromSelectionList();
		slot.notifyObservers(NotificationObserver.ADD, null);
		Gekosem.getStateManager().setAndState();
		//Invoker.getInstance().executeCommand(new changeAndState());
	}

}
