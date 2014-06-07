package display;

import javax.swing.JFrame;

import action.StartTournamentAction;

import com.google.common.eventbus.EventBus;

/**
 * Not yet implemented - intended to be a user menu / launch pad for the application
 * @author dgibbs
 *
 */
public class Application extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private EventBus bus;

	public Application(EventBus b){
		this.bus = b;
		bus.register(this);
	}
	
	/**
	 * Intended to open the main window of the user interface
	 * Currently, starts a user tournament due to unimplemented functionality
	 */
	public void open(){
//		pack();
//		setLocationRelativeTo(null);
//		setVisible(true);
		bus.post(new StartTournamentAction(StartTournamentAction.USER_TOURNAMENT));
	}
}
