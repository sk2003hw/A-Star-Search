package uk.ac.hw.macs.search;

/**
 * 
 * @author sk2003- Class representing a state in the grid, implements the State
 *         interface
 *
 */
public class StateImpl implements State {

	private int value; // represents the value of the state (not the cost)
	private String name;
	private boolean goal; // to check if the given state is the goal state
	private int heuristic; // represents the heuristic value (number of moves to reach the goal from the given state)

	/**
	 * Parameterised constructor to create an object of StateImpl class
	 * 
	 * @param value - the value associated with the state
	 * @param goal  - indicates if the state is the goal
	 * 
	 */
	public StateImpl(int value, String name, boolean goal) {
		this.value = value;
		this.name = name;
		this.goal = goal;
		this.heuristic = 10000; // value is almost infinite (i.e. not a possible value for the heuristic), does not exist yet
	}

	/**
	 * Parameterised constructor to create an object of StateImpl class for non-goal
	 * states
	 * 
	 * @param value - the value associated with the state
	 * 
	 */
	public StateImpl(int value, String name) {
		this(value, name, false);
	}

	/**
	 * Setter method to set the value of the heuristic
	 * 
	 * @param heuristic - the heuristic to set
	 */
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	/**
	 * Getter method to get the value of the heuristic.
	 * 
	 * @return heuristic - the value of the heuristic
	 */
	@Override
	public int getHeuristic() {
		return this.heuristic;
	}

	/**
	 * Getter method to get the value of the state.
	 * 
	 * @return value - the value of the state
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Getter method to get the name of the state.
	 * 
	 * @return value - the name of the state
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param goal- the goal to set
	 */
	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	/**
	 * To check if the given state is the goal state
	 * 
	 * @return goal - if the state is the goal state
	 */
	@Override
	public boolean isGoal() {
		return this.goal;
	}

	/**
	 * To output the string representation of the field in an object of StateImpl
	 * class.
	 * 
	 * @return string representation of a StateImpl object
	 */
	@Override
	public String toString() {
		return name;
	}


}
