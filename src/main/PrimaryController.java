package main;

import java.util.ArrayList;

import model.Player;
import action.StartTournamentAction;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import display.Application;

/**
 * Main class of the application
 * Opens the user interface and listens for events
 * @author dgibbs
 *
 */
public class PrimaryController {
	public static void main(String[] args){
		PrimaryController controller = new PrimaryController();
		controller.startApplication();
	}
	
	/**
	 * Runs the TexasHoldem Application
	 */
	private void startApplication(){
		EventBus bus = new EventBus();
		bus.register(this);
		Application app = new Application(bus);
		app.open();
	}
	
	@Subscribe
	/**
	 * Starts the tournament selected by the user
	 * @param action containing the tournament type to initiate
	 */
	public void startTournament(StartTournamentAction action){
		if (action.getType() == StartTournamentAction.USER_TOURNAMENT){
			ArrayList<Player> players = new ArrayList<Player>();
			for (int i = 0; i < 6; ++i) {
				players.add(new Player());
			}
			UserTournament game = new UserTournament(players);
			game.start();
		}
	}
}
