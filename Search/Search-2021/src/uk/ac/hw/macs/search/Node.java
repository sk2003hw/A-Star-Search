package uk.ac.hw.macs.search;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single node in the search space: it has a value, as well as a set of child nodes with associated
 * costs on the transitions.
 */
public class Node {
	private State value;
	private Set<ChildWithCost> children;
	
	public Node(State value) {
		this.value = value;
		this.children = new HashSet<>();
	}
	
	public boolean addChild(Node child) {
		return children.add(new ChildWithCost(child,1));
	}
	
	public boolean delete(ChildWithCost child) {
		return children.remove(child);
	}
	
	public Set<ChildWithCost> getChildren() {
		return children;
	}
	
	public State getValue() {
		return value;
	}
	
	public boolean isGoal() {
		return value.isGoal();
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	
}
