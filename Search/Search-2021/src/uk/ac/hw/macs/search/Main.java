package uk.ac.hw.macs.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author sk2003 - Class to represent the given grids and run the A* Search
 *         Algorithm on them
 *
 */

public class Main {

	/**
	 * Method to calculate the heuristic values of the states in the grid using
	 * Manhattan heuristics. The nodes have been represented as a grid to calculate
	 * the heuristic values of each of the states.
	 * 
	 * @param grid - 2-D array of nodes representing the states in grids in the
	 *             specification with states whose heuristic values are to be
	 *             calculated
	 */
	private static void getHeuristic(Node[][] grid) {
		int row = grid.length; // represents the number of rows in the grid
		int col = 0; // initial value of the number of columns in the grid
		int goal_x = -1; // represents the x (row) value of the goal state
		int goal_y = -1; // represents the y (column) value of the goal state

		// To iterate through the given grid to get the coordinates/indices of the goal state
		for (int i = 0; i < row; i++) { // to iterate through the rows/elements in the grid
			col = grid[i].length; // to get the number of columns in the grid
			for (int j = 0; j < col; j++) { // to iterate through the columns/elements in the elements of the grid
				if ((grid[i][j] != null) && (grid[i][j].isGoal())) { // to check if the node at the position is not null and is the goal node
					// to get the indices of the goal node
					goal_x = i;
					goal_y = j;
				}
			}
			
		}
		

		// To iterate through the given grid to calculate the heuristic value (number of steps/tiles/moves to reach goal from current node)
		// of each node using Manhattan heuristics.
		for (int i = 0; i < row; i++) { // to iterate through the rows/elements in the grid
			for (int j = 0; j < col; j++) { // to iterate through the columns/elements in the elements of the grid
				// The heuristic value is calculated by adding the difference of the row/x/i
				// indices of the current node and the goal node and the difference of the column/y/j 
				// indices of the current node and the goal node.
				if (grid[i][j] != null) { // the calculation is done only when the node is not null
					int heuristic = Math.abs(i - goal_x) + Math.abs(j - goal_y);
					StateImpl state = (StateImpl) grid[i][j].getValue(); // to get the value of the state associated with the node
					state.setHeuristic(heuristic); // to set the value of the heuristic of the state to the heuristic value calculated
				}
			}
		}
	}

	/**
	 * Represents the first given grid. The grid has been represented as states and
	 * nodes with connections and also as a 2-D array to aid in heuristic
	 * calculation.
	 * 
	 * @return root - root node of the grid
	 */
	private static Node grid1() {
		
		// Declaring the states of each of the nodes with unique values to identify each state easily.
		// The default value of the the type of state is set to non-goal and the goal state of the goal state is mentioned explicitly.
		// The value of the nodes are given by their position in the grid (0-99).		

		StateImpl [][] states = new StateImpl[10][];
		
		int n=0;
		for(int r=0; r<10; r++) {
			states[r] = new StateImpl[10];
			for(int c=0; c<10; c++) {
				String name = "(" + c + "," + r + ")";
				states[r][c] = new StateImpl(n,name);
				n++;
			}
		}
		
		states[9][9].setGoal(true);		

		Node root = new Node(states[0][0]);
		Node goal = new Node(states[9][9]);
		ArrayList <Node> nodes = new ArrayList<Node>();
		//nodes.add(0, root);
		
		int k=0;
		for(int r=0; r<10; r++) {
			for(int c=0; c<10; c++) {
				//if((r!=0 && c!=0) || (r!=9 && c!=9)) {
					nodes.add(k, new Node(states[r][c]));
					k++;
				//}
			}
		}
		
		//nodes.add(99,goal);

		Node [][] grid = new Node[10][];
		int j=0;
		for(int r=0; r<10; r++) {
			grid[r] = new Node[10];
			for(int c=0; c<10; c++) {
				grid[r][c] = nodes.get(j);
				j++;
			}
			
		}
		// for obstacles
		grid[7][9] = null;
		grid[7][8] = null;
		grid[7][7] = null;
		grid[8][7] = null;
		
		
		// Adding bidirectional connections between relevant nodes by adding each as the other's child.

		root.addChild(nodes.get(1)); root.addChild(nodes.get(10)); root.addChild(nodes.get(11));
		nodes.get(1).addChild(nodes.get(0)); nodes.get(1).addChild(nodes.get(2)); nodes.get(1).addChild(nodes.get(10)); nodes.get(1).addChild(nodes.get(11)); nodes.get(1).addChild(nodes.get(12));
		nodes.get(2).addChild(nodes.get(1)); nodes.get(2).addChild(nodes.get(3)); nodes.get(2).addChild(nodes.get(11)); nodes.get(2).addChild(nodes.get(12)); nodes.get(2).addChild(nodes.get(13));
		nodes.get(3).addChild(nodes.get(2)); nodes.get(3).addChild(nodes.get(4)); nodes.get(3).addChild(nodes.get(12)); nodes.get(3).addChild(nodes.get(13)); nodes.get(3).addChild(nodes.get(14));
		nodes.get(4).addChild(nodes.get(3)); nodes.get(4).addChild(nodes.get(5)); nodes.get(4).addChild(nodes.get(13)); nodes.get(4).addChild(nodes.get(14)); nodes.get(4).addChild(nodes.get(15));
		nodes.get(5).addChild(nodes.get(4)); nodes.get(5).addChild(nodes.get(6)); nodes.get(5).addChild(nodes.get(14)); nodes.get(5).addChild(nodes.get(15)); nodes.get(5).addChild(nodes.get(16));
		nodes.get(6).addChild(nodes.get(5)); nodes.get(6).addChild(nodes.get(7)); nodes.get(6).addChild(nodes.get(15)); nodes.get(6).addChild(nodes.get(16)); nodes.get(6).addChild(nodes.get(17));
		nodes.get(7).addChild(nodes.get(6)); nodes.get(7).addChild(nodes.get(8)); nodes.get(7).addChild(nodes.get(16)); nodes.get(7).addChild(nodes.get(17)); nodes.get(7).addChild(nodes.get(18));
		nodes.get(8).addChild(nodes.get(7)); nodes.get(8).addChild(nodes.get(9)); nodes.get(8).addChild(nodes.get(17)); nodes.get(8).addChild(nodes.get(18)); nodes.get(8).addChild(nodes.get(19));  
		nodes.get(9).addChild(nodes.get(8)); nodes.get(9).addChild(nodes.get(18)); nodes.get(9).addChild(nodes.get(19));
		
		nodes.get(10).addChild(nodes.get(0)); nodes.get(10).addChild(nodes.get(1)); nodes.get(10).addChild(nodes.get(11)); nodes.get(10).addChild(nodes.get(20)); nodes.get(10).addChild(nodes.get(21));
		nodes.get(11).addChild(nodes.get(0)); nodes.get(11).addChild(nodes.get(1)); nodes.get(11).addChild(nodes.get(2)); nodes.get(11).addChild(nodes.get(10)); nodes.get(11).addChild(nodes.get(12)); nodes.get(11).addChild(nodes.get(20)); nodes.get(11).addChild(nodes.get(21)); nodes.get(11).addChild(nodes.get(22));
		nodes.get(12).addChild(nodes.get(1)); nodes.get(12).addChild(nodes.get(2)); nodes.get(12).addChild(nodes.get(3)); nodes.get(12).addChild(nodes.get(11)); nodes.get(12).addChild(nodes.get(13)); nodes.get(12).addChild(nodes.get(21)); nodes.get(12).addChild(nodes.get(22)); nodes.get(12).addChild(nodes.get(23));
		nodes.get(13).addChild(nodes.get(2)); nodes.get(13).addChild(nodes.get(3)); nodes.get(13).addChild(nodes.get(4)); nodes.get(13).addChild(nodes.get(12)); nodes.get(13).addChild(nodes.get(14)); nodes.get(13).addChild(nodes.get(22)); nodes.get(13).addChild(nodes.get(23)); nodes.get(13).addChild(nodes.get(24));
		nodes.get(14).addChild(nodes.get(3)); nodes.get(14).addChild(nodes.get(4)); nodes.get(14).addChild(nodes.get(5)); nodes.get(14).addChild(nodes.get(13)); nodes.get(14).addChild(nodes.get(15)); nodes.get(14).addChild(nodes.get(23)); nodes.get(14).addChild(nodes.get(24)); nodes.get(14).addChild(nodes.get(25)); 
		nodes.get(15).addChild(nodes.get(4)); nodes.get(15).addChild(nodes.get(5)); nodes.get(15).addChild(nodes.get(6)); nodes.get(15).addChild(nodes.get(14)); nodes.get(15).addChild(nodes.get(16)); nodes.get(15).addChild(nodes.get(24)); nodes.get(15).addChild(nodes.get(25)); nodes.get(15).addChild(nodes.get(26)); 
		nodes.get(16).addChild(nodes.get(5)); nodes.get(16).addChild(nodes.get(6)); nodes.get(16).addChild(nodes.get(7)); nodes.get(16).addChild(nodes.get(15)); nodes.get(16).addChild(nodes.get(17)); nodes.get(16).addChild(nodes.get(25)); nodes.get(16).addChild(nodes.get(26)); nodes.get(16).addChild(nodes.get(27));
		nodes.get(17).addChild(nodes.get(6)); nodes.get(17).addChild(nodes.get(7)); nodes.get(17).addChild(nodes.get(8)); nodes.get(17).addChild(nodes.get(16)); nodes.get(17).addChild(nodes.get(18)); nodes.get(17).addChild(nodes.get(26)); nodes.get(17).addChild(nodes.get(27)); nodes.get(17).addChild(nodes.get(28));
		nodes.get(18).addChild(nodes.get(7)); nodes.get(18).addChild(nodes.get(8)); nodes.get(18).addChild(nodes.get(9)); nodes.get(18).addChild(nodes.get(17)); nodes.get(18).addChild(nodes.get(19)); nodes.get(18).addChild(nodes.get(27)); nodes.get(18).addChild(nodes.get(28)); nodes.get(18).addChild(nodes.get(29));
		nodes.get(19).addChild(nodes.get(8)); nodes.get(19).addChild(nodes.get(9)); nodes.get(19).addChild(nodes.get(18)); nodes.get(19).addChild(nodes.get(28)); nodes.get(19).addChild(nodes.get(29));
		
		nodes.get(20).addChild(nodes.get(10)); nodes.get(20).addChild(nodes.get(11)); nodes.get(20).addChild(nodes.get(21)); nodes.get(20).addChild(nodes.get(30)); nodes.get(20).addChild(nodes.get(31));
		nodes.get(21).addChild(nodes.get(10)); nodes.get(21).addChild(nodes.get(11)); nodes.get(21).addChild(nodes.get(12)); nodes.get(21).addChild(nodes.get(20)); nodes.get(21).addChild(nodes.get(22)); nodes.get(21).addChild(nodes.get(30)); nodes.get(21).addChild(nodes.get(31)); nodes.get(21).addChild(nodes.get(32));
		nodes.get(22).addChild(nodes.get(11)); nodes.get(22).addChild(nodes.get(12)); nodes.get(22).addChild(nodes.get(13)); nodes.get(22).addChild(nodes.get(21)); nodes.get(22).addChild(nodes.get(23)); nodes.get(22).addChild(nodes.get(31)); nodes.get(22).addChild(nodes.get(32)); nodes.get(22).addChild(nodes.get(33));
		nodes.get(23).addChild(nodes.get(12)); nodes.get(23).addChild(nodes.get(13)); nodes.get(23).addChild(nodes.get(14)); nodes.get(23).addChild(nodes.get(22)); nodes.get(23).addChild(nodes.get(24)); nodes.get(23).addChild(nodes.get(32)); nodes.get(23).addChild(nodes.get(33)); nodes.get(23).addChild(nodes.get(34));
		nodes.get(24).addChild(nodes.get(13)); nodes.get(24).addChild(nodes.get(14)); nodes.get(24).addChild(nodes.get(15)); nodes.get(24).addChild(nodes.get(23)); nodes.get(24).addChild(nodes.get(25)); nodes.get(24).addChild(nodes.get(33)); nodes.get(24).addChild(nodes.get(34)); nodes.get(24).addChild(nodes.get(35));
		nodes.get(25).addChild(nodes.get(14)); nodes.get(25).addChild(nodes.get(15)); nodes.get(25).addChild(nodes.get(16)); nodes.get(25).addChild(nodes.get(24)); nodes.get(25).addChild(nodes.get(26)); nodes.get(25).addChild(nodes.get(34)); nodes.get(25).addChild(nodes.get(35)); nodes.get(25).addChild(nodes.get(36));
		nodes.get(26).addChild(nodes.get(15)); nodes.get(26).addChild(nodes.get(16)); nodes.get(26).addChild(nodes.get(17)); nodes.get(26).addChild(nodes.get(25)); nodes.get(26).addChild(nodes.get(27)); nodes.get(26).addChild(nodes.get(35)); nodes.get(26).addChild(nodes.get(36)); nodes.get(26).addChild(nodes.get(37));
		nodes.get(27).addChild(nodes.get(16)); nodes.get(27).addChild(nodes.get(17)); nodes.get(27).addChild(nodes.get(18)); nodes.get(27).addChild(nodes.get(26)); nodes.get(27).addChild(nodes.get(28)); nodes.get(27).addChild(nodes.get(36)); nodes.get(27).addChild(nodes.get(37)); nodes.get(27).addChild(nodes.get(38));
		nodes.get(28).addChild(nodes.get(17)); nodes.get(28).addChild(nodes.get(18)); nodes.get(28).addChild(nodes.get(19)); nodes.get(28).addChild(nodes.get(27)); nodes.get(28).addChild(nodes.get(29)); nodes.get(28).addChild(nodes.get(37)); nodes.get(28).addChild(nodes.get(38)); nodes.get(28).addChild(nodes.get(39));
		nodes.get(29).addChild(nodes.get(18)); nodes.get(29).addChild(nodes.get(19)); nodes.get(29).addChild(nodes.get(28)); nodes.get(29).addChild(nodes.get(38)); nodes.get(29).addChild(nodes.get(39)); 
		
		nodes.get(30).addChild(nodes.get(20)); nodes.get(30).addChild(nodes.get(21)); nodes.get(30).addChild(nodes.get(31)); nodes.get(30).addChild(nodes.get(40)); nodes.get(30).addChild(nodes.get(41));
		nodes.get(31).addChild(nodes.get(20)); nodes.get(31).addChild(nodes.get(21)); nodes.get(31).addChild(nodes.get(22)); nodes.get(31).addChild(nodes.get(30)); nodes.get(31).addChild(nodes.get(32)); nodes.get(31).addChild(nodes.get(40)); nodes.get(31).addChild(nodes.get(41)); nodes.get(31).addChild(nodes.get(42));
		nodes.get(32).addChild(nodes.get(21)); nodes.get(32).addChild(nodes.get(22)); nodes.get(32).addChild(nodes.get(23)); nodes.get(32).addChild(nodes.get(31)); nodes.get(32).addChild(nodes.get(33)); nodes.get(32).addChild(nodes.get(41)); nodes.get(32).addChild(nodes.get(42)); nodes.get(32).addChild(nodes.get(43));
		nodes.get(33).addChild(nodes.get(22)); nodes.get(33).addChild(nodes.get(23)); nodes.get(33).addChild(nodes.get(24)); nodes.get(33).addChild(nodes.get(32)); nodes.get(33).addChild(nodes.get(34)); nodes.get(33).addChild(nodes.get(42)); nodes.get(33).addChild(nodes.get(43)); nodes.get(33).addChild(nodes.get(44));
		nodes.get(34).addChild(nodes.get(23)); nodes.get(34).addChild(nodes.get(24)); nodes.get(34).addChild(nodes.get(25)); nodes.get(34).addChild(nodes.get(33)); nodes.get(34).addChild(nodes.get(35)); nodes.get(34).addChild(nodes.get(43)); nodes.get(34).addChild(nodes.get(44)); nodes.get(34).addChild(nodes.get(45));
		nodes.get(35).addChild(nodes.get(24)); nodes.get(35).addChild(nodes.get(25)); nodes.get(35).addChild(nodes.get(26)); nodes.get(35).addChild(nodes.get(34)); nodes.get(35).addChild(nodes.get(36)); nodes.get(35).addChild(nodes.get(44)); nodes.get(35).addChild(nodes.get(45)); nodes.get(35).addChild(nodes.get(46));
		nodes.get(36).addChild(nodes.get(25)); nodes.get(36).addChild(nodes.get(26)); nodes.get(36).addChild(nodes.get(27)); nodes.get(36).addChild(nodes.get(35)); nodes.get(36).addChild(nodes.get(37)); nodes.get(36).addChild(nodes.get(45)); nodes.get(36).addChild(nodes.get(46)); nodes.get(36).addChild(nodes.get(47));
		nodes.get(37).addChild(nodes.get(26)); nodes.get(37).addChild(nodes.get(27)); nodes.get(37).addChild(nodes.get(28)); nodes.get(37).addChild(nodes.get(36)); nodes.get(37).addChild(nodes.get(38)); nodes.get(37).addChild(nodes.get(46)); nodes.get(37).addChild(nodes.get(47)); nodes.get(37).addChild(nodes.get(48));
		nodes.get(38).addChild(nodes.get(27)); nodes.get(38).addChild(nodes.get(28)); nodes.get(38).addChild(nodes.get(29)); nodes.get(38).addChild(nodes.get(37)); nodes.get(38).addChild(nodes.get(39)); nodes.get(38).addChild(nodes.get(47)); nodes.get(38).addChild(nodes.get(48)); nodes.get(38).addChild(nodes.get(49));
		nodes.get(39).addChild(nodes.get(28)); nodes.get(39).addChild(nodes.get(29)); nodes.get(39).addChild(nodes.get(38)); nodes.get(39).addChild(nodes.get(48)); nodes.get(39).addChild(nodes.get(49));
		
		nodes.get(40).addChild(nodes.get(30)); nodes.get(40).addChild(nodes.get(31)); nodes.get(40).addChild(nodes.get(41)); nodes.get(40).addChild(nodes.get(50)); nodes.get(40).addChild(nodes.get(51));
		nodes.get(41).addChild(nodes.get(30)); nodes.get(41).addChild(nodes.get(31)); nodes.get(41).addChild(nodes.get(32)); nodes.get(41).addChild(nodes.get(40)); nodes.get(41).addChild(nodes.get(42)); nodes.get(41).addChild(nodes.get(50)); nodes.get(41).addChild(nodes.get(51)); nodes.get(41).addChild(nodes.get(52));
		nodes.get(42).addChild(nodes.get(31)); nodes.get(42).addChild(nodes.get(32)); nodes.get(42).addChild(nodes.get(33)); nodes.get(42).addChild(nodes.get(41)); nodes.get(42).addChild(nodes.get(43)); nodes.get(42).addChild(nodes.get(51)); nodes.get(42).addChild(nodes.get(52)); nodes.get(42).addChild(nodes.get(53));
		nodes.get(43).addChild(nodes.get(32)); nodes.get(43).addChild(nodes.get(33)); nodes.get(43).addChild(nodes.get(34)); nodes.get(43).addChild(nodes.get(42)); nodes.get(43).addChild(nodes.get(44)); nodes.get(43).addChild(nodes.get(52)); nodes.get(43).addChild(nodes.get(53)); nodes.get(43).addChild(nodes.get(54));
		nodes.get(44).addChild(nodes.get(33)); nodes.get(44).addChild(nodes.get(34)); nodes.get(44).addChild(nodes.get(35)); nodes.get(44).addChild(nodes.get(43)); nodes.get(44).addChild(nodes.get(45)); nodes.get(44).addChild(nodes.get(53)); nodes.get(44).addChild(nodes.get(54)); nodes.get(44).addChild(nodes.get(55));
		nodes.get(45).addChild(nodes.get(34)); nodes.get(45).addChild(nodes.get(35)); nodes.get(45).addChild(nodes.get(36)); nodes.get(45).addChild(nodes.get(44)); nodes.get(45).addChild(nodes.get(46)); nodes.get(45).addChild(nodes.get(54)); nodes.get(45).addChild(nodes.get(55)); nodes.get(45).addChild(nodes.get(56));
		nodes.get(46).addChild(nodes.get(35)); nodes.get(46).addChild(nodes.get(36)); nodes.get(46).addChild(nodes.get(37)); nodes.get(46).addChild(nodes.get(45)); nodes.get(46).addChild(nodes.get(47)); nodes.get(46).addChild(nodes.get(55)); nodes.get(46).addChild(nodes.get(56)); nodes.get(46).addChild(nodes.get(57));
		nodes.get(47).addChild(nodes.get(36)); nodes.get(47).addChild(nodes.get(37)); nodes.get(47).addChild(nodes.get(38)); nodes.get(47).addChild(nodes.get(46)); nodes.get(47).addChild(nodes.get(48)); nodes.get(47).addChild(nodes.get(56)); nodes.get(47).addChild(nodes.get(57)); nodes.get(47).addChild(nodes.get(58));
		nodes.get(48).addChild(nodes.get(37)); nodes.get(48).addChild(nodes.get(39)); nodes.get(48).addChild(nodes.get(39)); nodes.get(48).addChild(nodes.get(47)); nodes.get(48).addChild(nodes.get(49)); nodes.get(48).addChild(nodes.get(57)); nodes.get(48).addChild(nodes.get(58)); nodes.get(48).addChild(nodes.get(59));
		nodes.get(49).addChild(nodes.get(38)); nodes.get(49).addChild(nodes.get(39)); nodes.get(49).addChild(nodes.get(48)); nodes.get(49).addChild(nodes.get(58)); nodes.get(49).addChild(nodes.get(59));
		
		nodes.get(50).addChild(nodes.get(40)); nodes.get(50).addChild(nodes.get(41)); nodes.get(50).addChild(nodes.get(51)); nodes.get(50).addChild(nodes.get(60)); nodes.get(50).addChild(nodes.get(61));
		nodes.get(51).addChild(nodes.get(40)); nodes.get(51).addChild(nodes.get(41)); nodes.get(51).addChild(nodes.get(42)); nodes.get(51).addChild(nodes.get(50)); nodes.get(51).addChild(nodes.get(52)); nodes.get(51).addChild(nodes.get(60)); nodes.get(51).addChild(nodes.get(61)); nodes.get(51).addChild(nodes.get(62));
		nodes.get(52).addChild(nodes.get(41)); nodes.get(52).addChild(nodes.get(42)); nodes.get(52).addChild(nodes.get(43)); nodes.get(52).addChild(nodes.get(51)); nodes.get(52).addChild(nodes.get(53)); nodes.get(52).addChild(nodes.get(61)); nodes.get(52).addChild(nodes.get(62)); nodes.get(52).addChild(nodes.get(63));
		nodes.get(53).addChild(nodes.get(42)); nodes.get(53).addChild(nodes.get(43)); nodes.get(53).addChild(nodes.get(44)); nodes.get(53).addChild(nodes.get(52)); nodes.get(53).addChild(nodes.get(54)); nodes.get(53).addChild(nodes.get(62)); nodes.get(53).addChild(nodes.get(63)); nodes.get(53).addChild(nodes.get(64));
		nodes.get(54).addChild(nodes.get(43)); nodes.get(54).addChild(nodes.get(44)); nodes.get(54).addChild(nodes.get(45)); nodes.get(54).addChild(nodes.get(53)); nodes.get(54).addChild(nodes.get(55)); nodes.get(54).addChild(nodes.get(63)); nodes.get(54).addChild(nodes.get(64)); nodes.get(54).addChild(nodes.get(65));
		nodes.get(55).addChild(nodes.get(44)); nodes.get(55).addChild(nodes.get(45)); nodes.get(55).addChild(nodes.get(46)); nodes.get(55).addChild(nodes.get(54)); nodes.get(55).addChild(nodes.get(56)); nodes.get(55).addChild(nodes.get(64)); nodes.get(55).addChild(nodes.get(65)); nodes.get(55).addChild(nodes.get(66));
		nodes.get(56).addChild(nodes.get(45)); nodes.get(56).addChild(nodes.get(46)); nodes.get(56).addChild(nodes.get(47)); nodes.get(56).addChild(nodes.get(55)); nodes.get(56).addChild(nodes.get(57)); nodes.get(56).addChild(nodes.get(65)); nodes.get(56).addChild(nodes.get(66)); nodes.get(56).addChild(nodes.get(67));
		nodes.get(57).addChild(nodes.get(46)); nodes.get(57).addChild(nodes.get(47)); nodes.get(57).addChild(nodes.get(48)); nodes.get(57).addChild(nodes.get(56)); nodes.get(57).addChild(nodes.get(58)); nodes.get(57).addChild(nodes.get(66)); nodes.get(57).addChild(nodes.get(67)); nodes.get(57).addChild(nodes.get(68));
		nodes.get(58).addChild(nodes.get(47)); nodes.get(58).addChild(nodes.get(48)); nodes.get(58).addChild(nodes.get(49)); nodes.get(58).addChild(nodes.get(57)); nodes.get(58).addChild(nodes.get(59)); nodes.get(58).addChild(nodes.get(67)); nodes.get(58).addChild(nodes.get(68)); nodes.get(58).addChild(nodes.get(69));
		nodes.get(59).addChild(nodes.get(48)); nodes.get(59).addChild(nodes.get(49)); nodes.get(59).addChild(nodes.get(58)); nodes.get(59).addChild(nodes.get(68)); nodes.get(59).addChild(nodes.get(69));
		
		nodes.get(60).addChild(nodes.get(50)); nodes.get(60).addChild(nodes.get(51)); nodes.get(60).addChild(nodes.get(61)); nodes.get(60).addChild(nodes.get(70)); nodes.get(60).addChild(nodes.get(71));
		nodes.get(61).addChild(nodes.get(50)); nodes.get(61).addChild(nodes.get(51)); nodes.get(61).addChild(nodes.get(52)); nodes.get(61).addChild(nodes.get(60)); nodes.get(61).addChild(nodes.get(62)); nodes.get(61).addChild(nodes.get(70)); nodes.get(61).addChild(nodes.get(71)); nodes.get(61).addChild(nodes.get(72));
		nodes.get(62).addChild(nodes.get(51)); nodes.get(62).addChild(nodes.get(52)); nodes.get(62).addChild(nodes.get(53)); nodes.get(62).addChild(nodes.get(61)); nodes.get(62).addChild(nodes.get(63)); nodes.get(62).addChild(nodes.get(71)); nodes.get(62).addChild(nodes.get(72)); nodes.get(62).addChild(nodes.get(73));
		nodes.get(63).addChild(nodes.get(52)); nodes.get(63).addChild(nodes.get(53)); nodes.get(63).addChild(nodes.get(54)); nodes.get(63).addChild(nodes.get(62)); nodes.get(63).addChild(nodes.get(64)); nodes.get(63).addChild(nodes.get(72)); nodes.get(63).addChild(nodes.get(73)); nodes.get(63).addChild(nodes.get(74));
		nodes.get(64).addChild(nodes.get(53)); nodes.get(64).addChild(nodes.get(54)); nodes.get(64).addChild(nodes.get(55)); nodes.get(64).addChild(nodes.get(63)); nodes.get(64).addChild(nodes.get(65)); nodes.get(64).addChild(nodes.get(73)); nodes.get(64).addChild(nodes.get(74)); nodes.get(64).addChild(nodes.get(75));
		nodes.get(65).addChild(nodes.get(54)); nodes.get(65).addChild(nodes.get(55)); nodes.get(65).addChild(nodes.get(56)); nodes.get(65).addChild(nodes.get(64)); nodes.get(65).addChild(nodes.get(66)); nodes.get(65).addChild(nodes.get(74)); nodes.get(65).addChild(nodes.get(75)); nodes.get(65).addChild(nodes.get(76));
		nodes.get(66).addChild(nodes.get(55)); nodes.get(66).addChild(nodes.get(56)); nodes.get(66).addChild(nodes.get(57)); nodes.get(66).addChild(nodes.get(65)); nodes.get(66).addChild(nodes.get(67)); nodes.get(66).addChild(nodes.get(75)); nodes.get(66).addChild(nodes.get(76)); //nodes.get(66).addChild(nodes.get(77));
		nodes.get(67).addChild(nodes.get(56)); nodes.get(67).addChild(nodes.get(57)); nodes.get(67).addChild(nodes.get(58)); nodes.get(67).addChild(nodes.get(66)); nodes.get(67).addChild(nodes.get(68)); nodes.get(67).addChild(nodes.get(76)); //nodes.get(67).addChild(nodes.get(77)); nodes.get(67).addChild(nodes.get(78));
		nodes.get(68).addChild(nodes.get(57)); nodes.get(68).addChild(nodes.get(58)); nodes.get(68).addChild(nodes.get(59)); nodes.get(68).addChild(nodes.get(67)); nodes.get(68).addChild(nodes.get(69)); //nodes.get(68).addChild(nodes.get(77)); nodes.get(68).addChild(nodes.get(78)); nodes.get(68).addChild(nodes.get(79));
		nodes.get(69).addChild(nodes.get(58)); nodes.get(69).addChild(nodes.get(59)); nodes.get(69).addChild(nodes.get(68)); 
		
		nodes.get(70).addChild(nodes.get(60)); nodes.get(70).addChild(nodes.get(61)); nodes.get(70).addChild(nodes.get(71)); nodes.get(70).addChild(nodes.get(80)); nodes.get(70).addChild(nodes.get(81));
		nodes.get(71).addChild(nodes.get(60)); nodes.get(71).addChild(nodes.get(61)); nodes.get(71).addChild(nodes.get(62)); nodes.get(71).addChild(nodes.get(70)); nodes.get(71).addChild(nodes.get(72)); nodes.get(71).addChild(nodes.get(80)); nodes.get(71).addChild(nodes.get(81)); nodes.get(71).addChild(nodes.get(82));
		nodes.get(72).addChild(nodes.get(61)); nodes.get(72).addChild(nodes.get(62)); nodes.get(72).addChild(nodes.get(63)); nodes.get(72).addChild(nodes.get(71)); nodes.get(72).addChild(nodes.get(73)); nodes.get(72).addChild(nodes.get(81)); nodes.get(72).addChild(nodes.get(82)); nodes.get(72).addChild(nodes.get(83));
		nodes.get(73).addChild(nodes.get(62)); nodes.get(73).addChild(nodes.get(63)); nodes.get(73).addChild(nodes.get(64)); nodes.get(73).addChild(nodes.get(72)); nodes.get(73).addChild(nodes.get(74)); nodes.get(73).addChild(nodes.get(82)); nodes.get(73).addChild(nodes.get(83)); nodes.get(73).addChild(nodes.get(84));
		nodes.get(74).addChild(nodes.get(63)); nodes.get(74).addChild(nodes.get(64)); nodes.get(74).addChild(nodes.get(65)); nodes.get(74).addChild(nodes.get(73)); nodes.get(74).addChild(nodes.get(75)); nodes.get(74).addChild(nodes.get(83)); nodes.get(74).addChild(nodes.get(84)); nodes.get(74).addChild(nodes.get(85));
		nodes.get(75).addChild(nodes.get(64)); nodes.get(75).addChild(nodes.get(65)); nodes.get(75).addChild(nodes.get(66)); nodes.get(75).addChild(nodes.get(74)); nodes.get(75).addChild(nodes.get(76)); nodes.get(75).addChild(nodes.get(84)); nodes.get(75).addChild(nodes.get(85)); nodes.get(75).addChild(nodes.get(86));
		nodes.get(76).addChild(nodes.get(65)); nodes.get(76).addChild(nodes.get(66)); nodes.get(76).addChild(nodes.get(67)); nodes.get(76).addChild(nodes.get(75)); //nodes.get(76).addChild(nodes.get(77)); 
		nodes.get(76).addChild(nodes.get(85)); nodes.get(76).addChild(nodes.get(86)); //nodes.get(76).addChild(nodes.get(87));
//		nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99)); nodes.get(77).addChild(nodes.get(99));
//		nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99)); nodes.get(78).addChild(nodes.get(99));
//		nodes.get(79).addChild(nodes.get(99)); nodes.get(79).addChild(nodes.get(99)); nodes.get(79).addChild(nodes.get(99)); nodes.get(79).addChild(nodes.get(99)); nodes.get(79).addChild(nodes.get(99));
		
		nodes.get(80).addChild(nodes.get(70)); nodes.get(80).addChild(nodes.get(71)); nodes.get(80).addChild(nodes.get(81)); nodes.get(80).addChild(nodes.get(90)); nodes.get(80).addChild(nodes.get(91));
		nodes.get(81).addChild(nodes.get(70)); nodes.get(81).addChild(nodes.get(71)); nodes.get(81).addChild(nodes.get(72)); nodes.get(81).addChild(nodes.get(80)); nodes.get(81).addChild(nodes.get(82)); nodes.get(81).addChild(nodes.get(90)); nodes.get(81).addChild(nodes.get(91)); nodes.get(81).addChild(nodes.get(92));
		nodes.get(82).addChild(nodes.get(71)); nodes.get(82).addChild(nodes.get(72)); nodes.get(82).addChild(nodes.get(73)); nodes.get(82).addChild(nodes.get(81)); nodes.get(82).addChild(nodes.get(83)); nodes.get(82).addChild(nodes.get(91)); nodes.get(82).addChild(nodes.get(92)); nodes.get(82).addChild(nodes.get(93));
		nodes.get(83).addChild(nodes.get(72)); nodes.get(83).addChild(nodes.get(73)); nodes.get(83).addChild(nodes.get(74)); nodes.get(83).addChild(nodes.get(82)); nodes.get(83).addChild(nodes.get(84)); nodes.get(83).addChild(nodes.get(92)); nodes.get(83).addChild(nodes.get(93)); nodes.get(83).addChild(nodes.get(94));
		nodes.get(84).addChild(nodes.get(73)); nodes.get(84).addChild(nodes.get(74)); nodes.get(84).addChild(nodes.get(75)); nodes.get(84).addChild(nodes.get(83)); nodes.get(84).addChild(nodes.get(85)); nodes.get(84).addChild(nodes.get(93)); nodes.get(84).addChild(nodes.get(94)); nodes.get(84).addChild(nodes.get(95));
		nodes.get(85).addChild(nodes.get(74)); nodes.get(85).addChild(nodes.get(75)); nodes.get(85).addChild(nodes.get(76)); nodes.get(85).addChild(nodes.get(84)); nodes.get(85).addChild(nodes.get(86)); nodes.get(85).addChild(nodes.get(94)); nodes.get(85).addChild(nodes.get(95)); nodes.get(85).addChild(nodes.get(96));
		nodes.get(86).addChild(nodes.get(75)); nodes.get(86).addChild(nodes.get(76)); //nodes.get(86).addChild(nodes.get(77));
		nodes.get(86).addChild(nodes.get(85)); //nodes.get(86).addChild(nodes.get(87));
		nodes.get(86).addChild(nodes.get(95)); nodes.get(86).addChild(nodes.get(96)); nodes.get(86).addChild(nodes.get(97));
		//nodes.get(87).addChild(nodes.get(76)); nodes.get(87).addChild(nodes.get(77)); nodes.get(87).addChild(nodes.get(78)); 
		//nodes.get(87).addChild(nodes.get(86)); nodes.get(87).addChild(nodes.get(88)); nodes.get(87).addChild(nodes.get(96)); nodes.get(87).addChild(nodes.get(97)); nodes.get(87).addChild(nodes.get(98));
		//nodes.get(88).addChild(nodes.get(77)); nodes.get(88).addChild(nodes.get(78)); nodes.get(88).addChild(nodes.get(79)); nodes.get(88).addChild(nodes.get(87)); 
		nodes.get(88).addChild(nodes.get(89)); nodes.get(88).addChild(nodes.get(97)); nodes.get(88).addChild(nodes.get(98)); nodes.get(88).addChild(nodes.get(99));
		nodes.get(89).addChild(nodes.get(88)); nodes.get(89).addChild(nodes.get(98)); nodes.get(89).addChild(nodes.get(99));
		
		nodes.get(90).addChild(nodes.get(80)); nodes.get(90).addChild(nodes.get(81)); nodes.get(90).addChild(nodes.get(91));
		nodes.get(91).addChild(nodes.get(80)); nodes.get(91).addChild(nodes.get(81)); nodes.get(91).addChild(nodes.get(82)); nodes.get(91).addChild(nodes.get(90)); nodes.get(91).addChild(nodes.get(92));
		nodes.get(92).addChild(nodes.get(81)); nodes.get(92).addChild(nodes.get(82)); nodes.get(92).addChild(nodes.get(83)); nodes.get(92).addChild(nodes.get(91)); nodes.get(92).addChild(nodes.get(93));
		nodes.get(93).addChild(nodes.get(82)); nodes.get(93).addChild(nodes.get(83)); nodes.get(93).addChild(nodes.get(84)); nodes.get(93).addChild(nodes.get(92)); nodes.get(93).addChild(nodes.get(94));
		nodes.get(94).addChild(nodes.get(83)); nodes.get(94).addChild(nodes.get(84)); nodes.get(94).addChild(nodes.get(85)); nodes.get(94).addChild(nodes.get(93)); nodes.get(94).addChild(nodes.get(95));
		nodes.get(95).addChild(nodes.get(84)); nodes.get(95).addChild(nodes.get(85)); nodes.get(95).addChild(nodes.get(86)); nodes.get(95).addChild(nodes.get(94)); nodes.get(95).addChild(nodes.get(96));
		nodes.get(96).addChild(nodes.get(85)); nodes.get(96).addChild(nodes.get(86)); //nodes.get(96).addChild(nodes.get(87)); 
		nodes.get(96).addChild(nodes.get(95)); nodes.get(96).addChild(nodes.get(97));
		nodes.get(97).addChild(nodes.get(86)); //nodes.get(97).addChild(nodes.get(87));
		nodes.get(97).addChild(nodes.get(88)); nodes.get(97).addChild(nodes.get(96)); nodes.get(97).addChild(nodes.get(98));
		//nodes.get(98).addChild(nodes.get(87)); 
		nodes.get(98).addChild(nodes.get(88)); nodes.get(98).addChild(nodes.get(89)); nodes.get(98).addChild(nodes.get(97)); nodes.get(98).addChild(nodes.get(99));
		goal.addChild(nodes.get(98)); goal.addChild(nodes.get(89)); goal.addChild(nodes.get(88));

		getHeuristic(grid); // to calculate the heuristics of each of the nodes in the grid
		return root; // return the root node of the grid
	}

	/**
	 * Represents a random grid. The grid has been represented as states and
	 * nodes with connections and also as a 2-D array to aid in heuristic
	 * calculation.
	 * 
	 * @return root - root node of the grid
	 */
	private static Node grid2() {
		
		// Declaring the states of each of the nodes with unique values to identify each state easily.
		// The default value of the the type of state is set to non-goal and the goal state of the goal state is mentioned explicitly.
		// The value of the nodes are given by their position in the grid (0-99).		

		StateImpl [][] states = new StateImpl[10][];
		
		int n=0;
		for(int r=0; r<10; r++) {
			states[r] = new StateImpl[10];
			for(int c=0; c<10; c++) {
				String name = "(" + c + "," + r + ")";
				states[r][c] = new StateImpl(n,name);
				n++;
			}
		}
		
		states[9][9].setGoal(true);		

		Node root = new Node(states[0][0]);
		Node goal = new Node(states[9][9]);
		ArrayList <Node> nodes = new ArrayList<Node>();
		
		int k=0;
		for(int r=0; r<10; r++) {
			for(int c=0; c<10; c++) {
				//if((r!=0 && c!=0) || (r!=9 && c!=9)) {
					nodes.add(k, new Node(states[r][c]));
					k++;
				//}
			}
		}
		

		Node [][] grid = new Node[10][];
		int j=0;
		for(int r=0; r<10; r++) {
			grid[r] = new Node[10];
			for(int c=0; c<10; c++) {
				grid[r][c] = nodes.get(j);
				j++;
			}
			
		}
		
		// for obstacles

		Random rand = new Random();

		// Obtain a number between [0 - 49].
		int i=0;
		ArrayList<Integer> num = new ArrayList<>();
		
		while(i<20) {
			int r = rand.nextInt(9)+1;
			int c = rand.nextInt(9);
			if(grid[r][c] != null) {
				int s = (r*10) + c;
				num.add(s);
				grid[r][c]=null;
				i++;
			}			
		}
		
		// Adding bidirectional connections between relevant nodes by adding each as the other's child.
		root.addChild(nodes.get(1)); root.addChild(nodes.get(10)); root.addChild(nodes.get(11));
		nodes.get(1).addChild(nodes.get(0)); nodes.get(1).addChild(nodes.get(2)); nodes.get(1).addChild(nodes.get(10)); nodes.get(1).addChild(nodes.get(11)); nodes.get(1).addChild(nodes.get(12));
		nodes.get(2).addChild(nodes.get(1)); nodes.get(2).addChild(nodes.get(3)); nodes.get(2).addChild(nodes.get(11)); nodes.get(2).addChild(nodes.get(12)); nodes.get(2).addChild(nodes.get(13));
		nodes.get(3).addChild(nodes.get(2)); nodes.get(3).addChild(nodes.get(4)); nodes.get(3).addChild(nodes.get(12)); nodes.get(3).addChild(nodes.get(13)); nodes.get(3).addChild(nodes.get(14));
		nodes.get(4).addChild(nodes.get(3)); nodes.get(4).addChild(nodes.get(5)); nodes.get(4).addChild(nodes.get(13)); nodes.get(4).addChild(nodes.get(14)); nodes.get(4).addChild(nodes.get(15));
		nodes.get(5).addChild(nodes.get(4)); nodes.get(5).addChild(nodes.get(6)); nodes.get(5).addChild(nodes.get(14)); nodes.get(5).addChild(nodes.get(15)); nodes.get(5).addChild(nodes.get(16));
		nodes.get(6).addChild(nodes.get(5)); nodes.get(6).addChild(nodes.get(7)); nodes.get(6).addChild(nodes.get(15)); nodes.get(6).addChild(nodes.get(16)); nodes.get(6).addChild(nodes.get(17));
		nodes.get(7).addChild(nodes.get(6)); nodes.get(7).addChild(nodes.get(8)); nodes.get(7).addChild(nodes.get(16)); nodes.get(7).addChild(nodes.get(17)); nodes.get(7).addChild(nodes.get(18));
		nodes.get(8).addChild(nodes.get(7)); nodes.get(8).addChild(nodes.get(9)); nodes.get(8).addChild(nodes.get(17)); nodes.get(8).addChild(nodes.get(18)); nodes.get(8).addChild(nodes.get(19));  
		nodes.get(9).addChild(nodes.get(8)); nodes.get(9).addChild(nodes.get(18)); nodes.get(9).addChild(nodes.get(19));
		
		nodes.get(10).addChild(nodes.get(0)); nodes.get(10).addChild(nodes.get(1)); nodes.get(10).addChild(nodes.get(11)); nodes.get(10).addChild(nodes.get(20)); nodes.get(10).addChild(nodes.get(21));
		nodes.get(11).addChild(nodes.get(0)); nodes.get(11).addChild(nodes.get(1)); nodes.get(11).addChild(nodes.get(2)); nodes.get(11).addChild(nodes.get(10)); nodes.get(11).addChild(nodes.get(12)); nodes.get(11).addChild(nodes.get(20)); nodes.get(11).addChild(nodes.get(21)); nodes.get(11).addChild(nodes.get(22));
		nodes.get(12).addChild(nodes.get(1)); nodes.get(12).addChild(nodes.get(2)); nodes.get(12).addChild(nodes.get(3)); nodes.get(12).addChild(nodes.get(11)); nodes.get(12).addChild(nodes.get(13)); nodes.get(12).addChild(nodes.get(21)); nodes.get(12).addChild(nodes.get(22)); nodes.get(12).addChild(nodes.get(23));
		nodes.get(13).addChild(nodes.get(2)); nodes.get(13).addChild(nodes.get(3)); nodes.get(13).addChild(nodes.get(4)); nodes.get(13).addChild(nodes.get(12)); nodes.get(13).addChild(nodes.get(14)); nodes.get(13).addChild(nodes.get(22)); nodes.get(13).addChild(nodes.get(23)); nodes.get(13).addChild(nodes.get(24));
		nodes.get(14).addChild(nodes.get(3)); nodes.get(14).addChild(nodes.get(4)); nodes.get(14).addChild(nodes.get(5)); nodes.get(14).addChild(nodes.get(13)); nodes.get(14).addChild(nodes.get(15)); nodes.get(14).addChild(nodes.get(23)); nodes.get(14).addChild(nodes.get(24)); nodes.get(14).addChild(nodes.get(25)); 
		nodes.get(15).addChild(nodes.get(4)); nodes.get(15).addChild(nodes.get(5)); nodes.get(15).addChild(nodes.get(6)); nodes.get(15).addChild(nodes.get(14)); nodes.get(15).addChild(nodes.get(16)); nodes.get(15).addChild(nodes.get(24)); nodes.get(15).addChild(nodes.get(25)); nodes.get(15).addChild(nodes.get(26)); 
		nodes.get(16).addChild(nodes.get(5)); nodes.get(16).addChild(nodes.get(6)); nodes.get(16).addChild(nodes.get(7)); nodes.get(16).addChild(nodes.get(15)); nodes.get(16).addChild(nodes.get(17)); nodes.get(16).addChild(nodes.get(25)); nodes.get(16).addChild(nodes.get(26)); nodes.get(16).addChild(nodes.get(27));
		nodes.get(17).addChild(nodes.get(6)); nodes.get(17).addChild(nodes.get(7)); nodes.get(17).addChild(nodes.get(8)); nodes.get(17).addChild(nodes.get(16)); nodes.get(17).addChild(nodes.get(18)); nodes.get(17).addChild(nodes.get(26)); nodes.get(17).addChild(nodes.get(27)); nodes.get(17).addChild(nodes.get(28));
		nodes.get(18).addChild(nodes.get(7)); nodes.get(18).addChild(nodes.get(8)); nodes.get(18).addChild(nodes.get(9)); nodes.get(18).addChild(nodes.get(17)); nodes.get(18).addChild(nodes.get(19)); nodes.get(18).addChild(nodes.get(27)); nodes.get(18).addChild(nodes.get(28)); nodes.get(18).addChild(nodes.get(29));
		nodes.get(19).addChild(nodes.get(8)); nodes.get(19).addChild(nodes.get(9)); nodes.get(19).addChild(nodes.get(18)); nodes.get(19).addChild(nodes.get(28)); nodes.get(19).addChild(nodes.get(29));
		
		nodes.get(20).addChild(nodes.get(10)); nodes.get(20).addChild(nodes.get(11)); nodes.get(20).addChild(nodes.get(21)); nodes.get(20).addChild(nodes.get(30)); nodes.get(20).addChild(nodes.get(31));
		nodes.get(21).addChild(nodes.get(10)); nodes.get(21).addChild(nodes.get(11)); nodes.get(21).addChild(nodes.get(12)); nodes.get(21).addChild(nodes.get(20)); nodes.get(21).addChild(nodes.get(22)); nodes.get(21).addChild(nodes.get(30)); nodes.get(21).addChild(nodes.get(31)); nodes.get(21).addChild(nodes.get(32));
		nodes.get(22).addChild(nodes.get(11)); nodes.get(22).addChild(nodes.get(12)); nodes.get(22).addChild(nodes.get(13)); nodes.get(22).addChild(nodes.get(21)); nodes.get(22).addChild(nodes.get(23)); nodes.get(22).addChild(nodes.get(31)); nodes.get(22).addChild(nodes.get(32)); nodes.get(22).addChild(nodes.get(33));
		nodes.get(23).addChild(nodes.get(12)); nodes.get(23).addChild(nodes.get(13)); nodes.get(23).addChild(nodes.get(14)); nodes.get(23).addChild(nodes.get(22)); nodes.get(23).addChild(nodes.get(24)); nodes.get(23).addChild(nodes.get(32)); nodes.get(23).addChild(nodes.get(33)); nodes.get(23).addChild(nodes.get(34));
		nodes.get(24).addChild(nodes.get(13)); nodes.get(24).addChild(nodes.get(14)); nodes.get(24).addChild(nodes.get(15)); nodes.get(24).addChild(nodes.get(23)); nodes.get(24).addChild(nodes.get(25)); nodes.get(24).addChild(nodes.get(33)); nodes.get(24).addChild(nodes.get(34)); nodes.get(24).addChild(nodes.get(35));
		nodes.get(25).addChild(nodes.get(14)); nodes.get(25).addChild(nodes.get(15)); nodes.get(25).addChild(nodes.get(16)); nodes.get(25).addChild(nodes.get(24)); nodes.get(25).addChild(nodes.get(26)); nodes.get(25).addChild(nodes.get(34)); nodes.get(25).addChild(nodes.get(35)); nodes.get(25).addChild(nodes.get(36));
		nodes.get(26).addChild(nodes.get(15)); nodes.get(26).addChild(nodes.get(16)); nodes.get(26).addChild(nodes.get(17)); nodes.get(26).addChild(nodes.get(25)); nodes.get(26).addChild(nodes.get(27)); nodes.get(26).addChild(nodes.get(35)); nodes.get(26).addChild(nodes.get(36)); nodes.get(26).addChild(nodes.get(37));
		nodes.get(27).addChild(nodes.get(16)); nodes.get(27).addChild(nodes.get(17)); nodes.get(27).addChild(nodes.get(18)); nodes.get(27).addChild(nodes.get(26)); nodes.get(27).addChild(nodes.get(28)); nodes.get(27).addChild(nodes.get(36)); nodes.get(27).addChild(nodes.get(37)); nodes.get(27).addChild(nodes.get(38));
		nodes.get(28).addChild(nodes.get(17)); nodes.get(28).addChild(nodes.get(18)); nodes.get(28).addChild(nodes.get(19)); nodes.get(28).addChild(nodes.get(27)); nodes.get(28).addChild(nodes.get(29)); nodes.get(28).addChild(nodes.get(37)); nodes.get(28).addChild(nodes.get(38)); nodes.get(28).addChild(nodes.get(39));
		nodes.get(29).addChild(nodes.get(18)); nodes.get(29).addChild(nodes.get(19)); nodes.get(29).addChild(nodes.get(28)); nodes.get(29).addChild(nodes.get(38)); nodes.get(29).addChild(nodes.get(39)); 
		
		nodes.get(30).addChild(nodes.get(20)); nodes.get(30).addChild(nodes.get(21)); nodes.get(30).addChild(nodes.get(31)); nodes.get(30).addChild(nodes.get(40)); nodes.get(30).addChild(nodes.get(41));
		nodes.get(31).addChild(nodes.get(20)); nodes.get(31).addChild(nodes.get(21)); nodes.get(31).addChild(nodes.get(22)); nodes.get(31).addChild(nodes.get(30)); nodes.get(31).addChild(nodes.get(32)); nodes.get(31).addChild(nodes.get(40)); nodes.get(31).addChild(nodes.get(41)); nodes.get(31).addChild(nodes.get(42));
		nodes.get(32).addChild(nodes.get(21)); nodes.get(32).addChild(nodes.get(22)); nodes.get(32).addChild(nodes.get(23)); nodes.get(32).addChild(nodes.get(31)); nodes.get(32).addChild(nodes.get(33)); nodes.get(32).addChild(nodes.get(41)); nodes.get(32).addChild(nodes.get(42)); nodes.get(32).addChild(nodes.get(43));
		nodes.get(33).addChild(nodes.get(22)); nodes.get(33).addChild(nodes.get(23)); nodes.get(33).addChild(nodes.get(24)); nodes.get(33).addChild(nodes.get(32)); nodes.get(33).addChild(nodes.get(34)); nodes.get(33).addChild(nodes.get(42)); nodes.get(33).addChild(nodes.get(43)); nodes.get(33).addChild(nodes.get(44));
		nodes.get(34).addChild(nodes.get(23)); nodes.get(34).addChild(nodes.get(24)); nodes.get(34).addChild(nodes.get(25)); nodes.get(34).addChild(nodes.get(33)); nodes.get(34).addChild(nodes.get(35)); nodes.get(34).addChild(nodes.get(43)); nodes.get(34).addChild(nodes.get(44)); nodes.get(34).addChild(nodes.get(45));
		nodes.get(35).addChild(nodes.get(24)); nodes.get(35).addChild(nodes.get(25)); nodes.get(35).addChild(nodes.get(26)); nodes.get(35).addChild(nodes.get(34)); nodes.get(35).addChild(nodes.get(36)); nodes.get(35).addChild(nodes.get(44)); nodes.get(35).addChild(nodes.get(45)); nodes.get(35).addChild(nodes.get(46));
		nodes.get(36).addChild(nodes.get(25)); nodes.get(36).addChild(nodes.get(26)); nodes.get(36).addChild(nodes.get(27)); nodes.get(36).addChild(nodes.get(35)); nodes.get(36).addChild(nodes.get(37)); nodes.get(36).addChild(nodes.get(45)); nodes.get(36).addChild(nodes.get(46)); nodes.get(36).addChild(nodes.get(47));
		nodes.get(37).addChild(nodes.get(26)); nodes.get(37).addChild(nodes.get(27)); nodes.get(37).addChild(nodes.get(28)); nodes.get(37).addChild(nodes.get(36)); nodes.get(37).addChild(nodes.get(38)); nodes.get(37).addChild(nodes.get(46)); nodes.get(37).addChild(nodes.get(47)); nodes.get(37).addChild(nodes.get(48));
		nodes.get(38).addChild(nodes.get(27)); nodes.get(38).addChild(nodes.get(28)); nodes.get(38).addChild(nodes.get(29)); nodes.get(38).addChild(nodes.get(37)); nodes.get(38).addChild(nodes.get(39)); nodes.get(38).addChild(nodes.get(47)); nodes.get(38).addChild(nodes.get(48)); nodes.get(38).addChild(nodes.get(49));
		nodes.get(39).addChild(nodes.get(28)); nodes.get(39).addChild(nodes.get(29)); nodes.get(39).addChild(nodes.get(38)); nodes.get(39).addChild(nodes.get(48)); nodes.get(39).addChild(nodes.get(49));
		
		nodes.get(40).addChild(nodes.get(30)); nodes.get(40).addChild(nodes.get(31)); nodes.get(40).addChild(nodes.get(41)); nodes.get(40).addChild(nodes.get(50)); nodes.get(40).addChild(nodes.get(51));
		nodes.get(41).addChild(nodes.get(30)); nodes.get(41).addChild(nodes.get(31)); nodes.get(41).addChild(nodes.get(32)); nodes.get(41).addChild(nodes.get(40)); nodes.get(41).addChild(nodes.get(42)); nodes.get(41).addChild(nodes.get(50)); nodes.get(41).addChild(nodes.get(51)); nodes.get(41).addChild(nodes.get(52));
		nodes.get(42).addChild(nodes.get(31)); nodes.get(42).addChild(nodes.get(32)); nodes.get(42).addChild(nodes.get(33)); nodes.get(42).addChild(nodes.get(41)); nodes.get(42).addChild(nodes.get(43)); nodes.get(42).addChild(nodes.get(51)); nodes.get(42).addChild(nodes.get(52)); nodes.get(42).addChild(nodes.get(53));
		nodes.get(43).addChild(nodes.get(32)); nodes.get(43).addChild(nodes.get(33)); nodes.get(43).addChild(nodes.get(34)); nodes.get(43).addChild(nodes.get(42)); nodes.get(43).addChild(nodes.get(44)); nodes.get(43).addChild(nodes.get(52)); nodes.get(43).addChild(nodes.get(53)); nodes.get(43).addChild(nodes.get(54));
		nodes.get(44).addChild(nodes.get(33)); nodes.get(44).addChild(nodes.get(34)); nodes.get(44).addChild(nodes.get(35)); nodes.get(44).addChild(nodes.get(43)); nodes.get(44).addChild(nodes.get(45)); nodes.get(44).addChild(nodes.get(53)); nodes.get(44).addChild(nodes.get(54)); nodes.get(44).addChild(nodes.get(55));
		nodes.get(45).addChild(nodes.get(34)); nodes.get(45).addChild(nodes.get(35)); nodes.get(45).addChild(nodes.get(36)); nodes.get(45).addChild(nodes.get(44)); nodes.get(45).addChild(nodes.get(46)); nodes.get(45).addChild(nodes.get(54)); nodes.get(45).addChild(nodes.get(55)); nodes.get(45).addChild(nodes.get(56));
		nodes.get(46).addChild(nodes.get(35)); nodes.get(46).addChild(nodes.get(36)); nodes.get(46).addChild(nodes.get(37)); nodes.get(46).addChild(nodes.get(45)); nodes.get(46).addChild(nodes.get(47)); nodes.get(46).addChild(nodes.get(55)); nodes.get(46).addChild(nodes.get(56)); nodes.get(46).addChild(nodes.get(57));
		nodes.get(47).addChild(nodes.get(36)); nodes.get(47).addChild(nodes.get(37)); nodes.get(47).addChild(nodes.get(38)); nodes.get(47).addChild(nodes.get(46)); nodes.get(47).addChild(nodes.get(48)); nodes.get(47).addChild(nodes.get(56)); nodes.get(47).addChild(nodes.get(57)); nodes.get(47).addChild(nodes.get(58));
		nodes.get(48).addChild(nodes.get(37)); nodes.get(48).addChild(nodes.get(39)); nodes.get(48).addChild(nodes.get(39)); nodes.get(48).addChild(nodes.get(47)); nodes.get(48).addChild(nodes.get(49)); nodes.get(48).addChild(nodes.get(57)); nodes.get(48).addChild(nodes.get(58)); nodes.get(48).addChild(nodes.get(59));
		nodes.get(49).addChild(nodes.get(38)); nodes.get(49).addChild(nodes.get(39)); nodes.get(49).addChild(nodes.get(48)); nodes.get(49).addChild(nodes.get(58)); nodes.get(49).addChild(nodes.get(59));
		
		nodes.get(50).addChild(nodes.get(40)); nodes.get(50).addChild(nodes.get(41)); nodes.get(50).addChild(nodes.get(51)); nodes.get(50).addChild(nodes.get(60)); nodes.get(50).addChild(nodes.get(61));
		nodes.get(51).addChild(nodes.get(40)); nodes.get(51).addChild(nodes.get(41)); nodes.get(51).addChild(nodes.get(42)); nodes.get(51).addChild(nodes.get(50)); nodes.get(51).addChild(nodes.get(52)); nodes.get(51).addChild(nodes.get(60)); nodes.get(51).addChild(nodes.get(61)); nodes.get(51).addChild(nodes.get(62));
		nodes.get(52).addChild(nodes.get(41)); nodes.get(52).addChild(nodes.get(42)); nodes.get(52).addChild(nodes.get(43)); nodes.get(52).addChild(nodes.get(51)); nodes.get(52).addChild(nodes.get(53)); nodes.get(52).addChild(nodes.get(61)); nodes.get(52).addChild(nodes.get(62)); nodes.get(52).addChild(nodes.get(63));
		nodes.get(53).addChild(nodes.get(42)); nodes.get(53).addChild(nodes.get(43)); nodes.get(53).addChild(nodes.get(44)); nodes.get(53).addChild(nodes.get(52)); nodes.get(53).addChild(nodes.get(54)); nodes.get(53).addChild(nodes.get(62)); nodes.get(53).addChild(nodes.get(63)); nodes.get(53).addChild(nodes.get(64));
		nodes.get(54).addChild(nodes.get(43)); nodes.get(54).addChild(nodes.get(44)); nodes.get(54).addChild(nodes.get(45)); nodes.get(54).addChild(nodes.get(53)); nodes.get(54).addChild(nodes.get(55)); nodes.get(54).addChild(nodes.get(63)); nodes.get(54).addChild(nodes.get(64)); nodes.get(54).addChild(nodes.get(65));
		nodes.get(55).addChild(nodes.get(44)); nodes.get(55).addChild(nodes.get(45)); nodes.get(55).addChild(nodes.get(46)); nodes.get(55).addChild(nodes.get(54)); nodes.get(55).addChild(nodes.get(56)); nodes.get(55).addChild(nodes.get(64)); nodes.get(55).addChild(nodes.get(65)); nodes.get(55).addChild(nodes.get(66));
		nodes.get(56).addChild(nodes.get(45)); nodes.get(56).addChild(nodes.get(46)); nodes.get(56).addChild(nodes.get(47)); nodes.get(56).addChild(nodes.get(55)); nodes.get(56).addChild(nodes.get(57)); nodes.get(56).addChild(nodes.get(65)); nodes.get(56).addChild(nodes.get(66)); nodes.get(56).addChild(nodes.get(67));
		nodes.get(57).addChild(nodes.get(46)); nodes.get(57).addChild(nodes.get(47)); nodes.get(57).addChild(nodes.get(48)); nodes.get(57).addChild(nodes.get(56)); nodes.get(57).addChild(nodes.get(58)); nodes.get(57).addChild(nodes.get(66)); nodes.get(57).addChild(nodes.get(67)); nodes.get(57).addChild(nodes.get(68));
		nodes.get(58).addChild(nodes.get(47)); nodes.get(58).addChild(nodes.get(48)); nodes.get(58).addChild(nodes.get(49)); nodes.get(58).addChild(nodes.get(57)); nodes.get(58).addChild(nodes.get(59)); nodes.get(58).addChild(nodes.get(67)); nodes.get(58).addChild(nodes.get(68)); nodes.get(58).addChild(nodes.get(69));
		nodes.get(59).addChild(nodes.get(48)); nodes.get(59).addChild(nodes.get(49)); nodes.get(59).addChild(nodes.get(58)); nodes.get(59).addChild(nodes.get(68)); nodes.get(59).addChild(nodes.get(69));
		
		nodes.get(60).addChild(nodes.get(50)); nodes.get(60).addChild(nodes.get(51)); nodes.get(60).addChild(nodes.get(61)); nodes.get(60).addChild(nodes.get(70)); nodes.get(60).addChild(nodes.get(71));
		nodes.get(61).addChild(nodes.get(50)); nodes.get(61).addChild(nodes.get(51)); nodes.get(61).addChild(nodes.get(52)); nodes.get(61).addChild(nodes.get(60)); nodes.get(61).addChild(nodes.get(62)); nodes.get(61).addChild(nodes.get(70)); nodes.get(61).addChild(nodes.get(71)); nodes.get(61).addChild(nodes.get(72));
		nodes.get(62).addChild(nodes.get(51)); nodes.get(62).addChild(nodes.get(52)); nodes.get(62).addChild(nodes.get(53)); nodes.get(62).addChild(nodes.get(61)); nodes.get(62).addChild(nodes.get(63)); nodes.get(62).addChild(nodes.get(71)); nodes.get(62).addChild(nodes.get(72)); nodes.get(62).addChild(nodes.get(73));
		nodes.get(63).addChild(nodes.get(52)); nodes.get(63).addChild(nodes.get(53)); nodes.get(63).addChild(nodes.get(54)); nodes.get(63).addChild(nodes.get(62)); nodes.get(63).addChild(nodes.get(64)); nodes.get(63).addChild(nodes.get(72)); nodes.get(63).addChild(nodes.get(73)); nodes.get(63).addChild(nodes.get(74));
		nodes.get(64).addChild(nodes.get(53)); nodes.get(64).addChild(nodes.get(54)); nodes.get(64).addChild(nodes.get(55)); nodes.get(64).addChild(nodes.get(63)); nodes.get(64).addChild(nodes.get(65)); nodes.get(64).addChild(nodes.get(73)); nodes.get(64).addChild(nodes.get(74)); nodes.get(64).addChild(nodes.get(75));
		nodes.get(65).addChild(nodes.get(54)); nodes.get(65).addChild(nodes.get(55)); nodes.get(65).addChild(nodes.get(56)); nodes.get(65).addChild(nodes.get(64)); nodes.get(65).addChild(nodes.get(66)); nodes.get(65).addChild(nodes.get(74)); nodes.get(65).addChild(nodes.get(75)); nodes.get(65).addChild(nodes.get(76));
		nodes.get(66).addChild(nodes.get(55)); nodes.get(66).addChild(nodes.get(56)); nodes.get(66).addChild(nodes.get(57)); nodes.get(66).addChild(nodes.get(65)); nodes.get(66).addChild(nodes.get(67)); nodes.get(66).addChild(nodes.get(75)); nodes.get(66).addChild(nodes.get(76)); nodes.get(66).addChild(nodes.get(77));
		nodes.get(67).addChild(nodes.get(56)); nodes.get(67).addChild(nodes.get(57)); nodes.get(67).addChild(nodes.get(58)); nodes.get(67).addChild(nodes.get(66)); nodes.get(67).addChild(nodes.get(68)); nodes.get(67).addChild(nodes.get(76)); nodes.get(67).addChild(nodes.get(77)); nodes.get(67).addChild(nodes.get(78));
		nodes.get(68).addChild(nodes.get(57)); nodes.get(68).addChild(nodes.get(58)); nodes.get(68).addChild(nodes.get(59)); nodes.get(68).addChild(nodes.get(67)); nodes.get(68).addChild(nodes.get(69)); nodes.get(68).addChild(nodes.get(77)); nodes.get(68).addChild(nodes.get(78)); nodes.get(68).addChild(nodes.get(79));
		nodes.get(69).addChild(nodes.get(58)); nodes.get(69).addChild(nodes.get(59)); nodes.get(69).addChild(nodes.get(68)); nodes.get(69).addChild(nodes.get(78)); nodes.get(69).addChild(nodes.get(79));
		
		nodes.get(70).addChild(nodes.get(60)); nodes.get(70).addChild(nodes.get(61)); nodes.get(70).addChild(nodes.get(71)); nodes.get(70).addChild(nodes.get(80)); nodes.get(70).addChild(nodes.get(81));
		nodes.get(71).addChild(nodes.get(60)); nodes.get(71).addChild(nodes.get(61)); nodes.get(71).addChild(nodes.get(62)); nodes.get(71).addChild(nodes.get(70)); nodes.get(71).addChild(nodes.get(72)); nodes.get(71).addChild(nodes.get(80)); nodes.get(71).addChild(nodes.get(81)); nodes.get(71).addChild(nodes.get(82));
		nodes.get(72).addChild(nodes.get(61)); nodes.get(72).addChild(nodes.get(62)); nodes.get(72).addChild(nodes.get(63)); nodes.get(72).addChild(nodes.get(71)); nodes.get(72).addChild(nodes.get(73)); nodes.get(72).addChild(nodes.get(81)); nodes.get(72).addChild(nodes.get(82)); nodes.get(72).addChild(nodes.get(83));
		nodes.get(73).addChild(nodes.get(62)); nodes.get(73).addChild(nodes.get(63)); nodes.get(73).addChild(nodes.get(64)); nodes.get(73).addChild(nodes.get(72)); nodes.get(73).addChild(nodes.get(74)); nodes.get(73).addChild(nodes.get(82)); nodes.get(73).addChild(nodes.get(83)); nodes.get(73).addChild(nodes.get(84));
		nodes.get(74).addChild(nodes.get(63)); nodes.get(74).addChild(nodes.get(64)); nodes.get(74).addChild(nodes.get(65)); nodes.get(74).addChild(nodes.get(73)); nodes.get(74).addChild(nodes.get(75)); nodes.get(74).addChild(nodes.get(83)); nodes.get(74).addChild(nodes.get(84)); nodes.get(74).addChild(nodes.get(85));
		nodes.get(75).addChild(nodes.get(64)); nodes.get(75).addChild(nodes.get(65)); nodes.get(75).addChild(nodes.get(66)); nodes.get(75).addChild(nodes.get(74)); nodes.get(75).addChild(nodes.get(76)); nodes.get(75).addChild(nodes.get(84)); nodes.get(75).addChild(nodes.get(85)); nodes.get(75).addChild(nodes.get(86));
		nodes.get(76).addChild(nodes.get(65)); nodes.get(76).addChild(nodes.get(66)); nodes.get(76).addChild(nodes.get(67)); nodes.get(76).addChild(nodes.get(75)); nodes.get(76).addChild(nodes.get(77)); nodes.get(76).addChild(nodes.get(85)); nodes.get(76).addChild(nodes.get(86)); nodes.get(76).addChild(nodes.get(87)); 
		nodes.get(77).addChild(nodes.get(66)); nodes.get(77).addChild(nodes.get(67)); nodes.get(77).addChild(nodes.get(68)); nodes.get(77).addChild(nodes.get(76)); nodes.get(77).addChild(nodes.get(78)); nodes.get(77).addChild(nodes.get(86)); nodes.get(77).addChild(nodes.get(87)); nodes.get(77).addChild(nodes.get(88));
		nodes.get(78).addChild(nodes.get(67)); nodes.get(78).addChild(nodes.get(68)); nodes.get(78).addChild(nodes.get(69)); nodes.get(78).addChild(nodes.get(77)); nodes.get(78).addChild(nodes.get(79)); nodes.get(78).addChild(nodes.get(87)); nodes.get(78).addChild(nodes.get(88)); nodes.get(78).addChild(nodes.get(89));
		nodes.get(79).addChild(nodes.get(68)); nodes.get(79).addChild(nodes.get(69)); nodes.get(79).addChild(nodes.get(78)); nodes.get(79).addChild(nodes.get(88)); nodes.get(79).addChild(nodes.get(89));
		
		nodes.get(80).addChild(nodes.get(70)); nodes.get(80).addChild(nodes.get(71)); nodes.get(80).addChild(nodes.get(81)); nodes.get(80).addChild(nodes.get(90)); nodes.get(80).addChild(nodes.get(91));
		nodes.get(81).addChild(nodes.get(70)); nodes.get(81).addChild(nodes.get(71)); nodes.get(81).addChild(nodes.get(72)); nodes.get(81).addChild(nodes.get(80)); nodes.get(81).addChild(nodes.get(82)); nodes.get(81).addChild(nodes.get(90)); nodes.get(81).addChild(nodes.get(91)); nodes.get(81).addChild(nodes.get(92));
		nodes.get(82).addChild(nodes.get(71)); nodes.get(82).addChild(nodes.get(72)); nodes.get(82).addChild(nodes.get(73)); nodes.get(82).addChild(nodes.get(81)); nodes.get(82).addChild(nodes.get(83)); nodes.get(82).addChild(nodes.get(91)); nodes.get(82).addChild(nodes.get(92)); nodes.get(82).addChild(nodes.get(93));
		nodes.get(83).addChild(nodes.get(72)); nodes.get(83).addChild(nodes.get(73)); nodes.get(83).addChild(nodes.get(74)); nodes.get(83).addChild(nodes.get(82)); nodes.get(83).addChild(nodes.get(84)); nodes.get(83).addChild(nodes.get(92)); nodes.get(83).addChild(nodes.get(93)); nodes.get(83).addChild(nodes.get(94));
		nodes.get(84).addChild(nodes.get(73)); nodes.get(84).addChild(nodes.get(74)); nodes.get(84).addChild(nodes.get(75)); nodes.get(84).addChild(nodes.get(83)); nodes.get(84).addChild(nodes.get(85)); nodes.get(84).addChild(nodes.get(93)); nodes.get(84).addChild(nodes.get(94)); nodes.get(84).addChild(nodes.get(95));
		nodes.get(85).addChild(nodes.get(74)); nodes.get(85).addChild(nodes.get(75)); nodes.get(85).addChild(nodes.get(76)); nodes.get(85).addChild(nodes.get(84)); nodes.get(85).addChild(nodes.get(86)); nodes.get(85).addChild(nodes.get(94)); nodes.get(85).addChild(nodes.get(95)); nodes.get(85).addChild(nodes.get(96));
		nodes.get(86).addChild(nodes.get(75)); nodes.get(86).addChild(nodes.get(76)); nodes.get(86).addChild(nodes.get(77)); nodes.get(86).addChild(nodes.get(85)); nodes.get(86).addChild(nodes.get(87)); nodes.get(86).addChild(nodes.get(95)); nodes.get(86).addChild(nodes.get(96)); nodes.get(86).addChild(nodes.get(97));
		nodes.get(87).addChild(nodes.get(76)); nodes.get(87).addChild(nodes.get(77)); nodes.get(87).addChild(nodes.get(78)); nodes.get(87).addChild(nodes.get(86)); nodes.get(87).addChild(nodes.get(88)); nodes.get(87).addChild(nodes.get(96)); nodes.get(87).addChild(nodes.get(97)); nodes.get(87).addChild(nodes.get(98));
		nodes.get(88).addChild(nodes.get(77)); nodes.get(88).addChild(nodes.get(78)); nodes.get(88).addChild(nodes.get(79)); nodes.get(88).addChild(nodes.get(87)); nodes.get(88).addChild(nodes.get(89)); nodes.get(88).addChild(nodes.get(97)); nodes.get(88).addChild(nodes.get(98)); nodes.get(88).addChild(nodes.get(99));
		nodes.get(89).addChild(nodes.get(78)); nodes.get(89).addChild(nodes.get(79)); nodes.get(89).addChild(nodes.get(88)); nodes.get(89).addChild(nodes.get(98)); nodes.get(89).addChild(nodes.get(99));
		
		nodes.get(90).addChild(nodes.get(80)); nodes.get(90).addChild(nodes.get(81)); nodes.get(90).addChild(nodes.get(91));
		nodes.get(91).addChild(nodes.get(80)); nodes.get(91).addChild(nodes.get(81)); nodes.get(91).addChild(nodes.get(82)); nodes.get(91).addChild(nodes.get(90)); nodes.get(91).addChild(nodes.get(92));
		nodes.get(92).addChild(nodes.get(81)); nodes.get(92).addChild(nodes.get(82)); nodes.get(92).addChild(nodes.get(83)); nodes.get(92).addChild(nodes.get(91)); nodes.get(92).addChild(nodes.get(93));
		nodes.get(93).addChild(nodes.get(82)); nodes.get(93).addChild(nodes.get(83)); nodes.get(93).addChild(nodes.get(84)); nodes.get(93).addChild(nodes.get(92)); nodes.get(93).addChild(nodes.get(94));
		nodes.get(94).addChild(nodes.get(83)); nodes.get(94).addChild(nodes.get(84)); nodes.get(94).addChild(nodes.get(85)); nodes.get(94).addChild(nodes.get(93)); nodes.get(94).addChild(nodes.get(95));
		nodes.get(95).addChild(nodes.get(84)); nodes.get(95).addChild(nodes.get(85)); nodes.get(95).addChild(nodes.get(86)); nodes.get(95).addChild(nodes.get(94)); nodes.get(95).addChild(nodes.get(96));
		nodes.get(96).addChild(nodes.get(85)); nodes.get(96).addChild(nodes.get(86)); nodes.get(96).addChild(nodes.get(87)); nodes.get(96).addChild(nodes.get(95)); nodes.get(96).addChild(nodes.get(97));
		nodes.get(97).addChild(nodes.get(86)); nodes.get(97).addChild(nodes.get(87)); nodes.get(97).addChild(nodes.get(88)); nodes.get(97).addChild(nodes.get(96)); nodes.get(97).addChild(nodes.get(98));
		nodes.get(98).addChild(nodes.get(87)); nodes.get(98).addChild(nodes.get(88)); nodes.get(98).addChild(nodes.get(89)); nodes.get(98).addChild(nodes.get(97)); nodes.get(98).addChild(nodes.get(99));
		goal.addChild(nodes.get(98)); goal.addChild(nodes.get(89)); goal.addChild(nodes.get(88));

		
		for(int i1=0; i1<nodes.size(); i1++) {
			Iterator<ChildWithCost> cit = nodes.get(i1).getChildren().iterator();			
			while(cit.hasNext()) {
				ChildWithCost c = cit.next();
				if(num.contains(c.node.getValue().getValue()))
					cit.remove();
			}
		}
		
				
		getHeuristic(grid); // to calculate the heuristics of each of the nodes in the grid
		return root; // return the root node of the grid
	}
	

	public static void main(String[] args) {
		// To run the A* search function on the two grid problems described above
		Node root1 = grid1(); // to get the root node from the first grid problem
		Node root2 = grid2();
		
		SearchOrder order = new AStarSearchOrder(); // the search algorithm to use
		SearchProblem problem = new SearchProblem(order); // SearchProblem object to perform A* Search on the grids
		// the output has been captured and pasted in output.txt file in the zip file.
		System.out.println("*******************************************************************************");
		problem.doSearch(root1); // to perform A* search on grid-1 with root-1
		System.out.println("*******************************************************************************");
		problem.doSearch(root2);
		System.out.println("*******************************************************************************");
	}

}