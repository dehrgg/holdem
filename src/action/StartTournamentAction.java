package action;

public class StartTournamentAction {
	public static int USER_TOURNAMENT = 1;
	public static int SIMULATED_TOURNAMENT =2;
	
	private int type;
	
	public StartTournamentAction(int tournamentType){
		setType(tournamentType);
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
