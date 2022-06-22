package uk.ac.hw.macs.search;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author sk2003 - Class that contains methods to add nodes to a fringe during
 *         A* Search. The states are expanded in the increasing order of the total cost. 
 *         No state can be expanded more than once and when inserting nodes to the fringe, 
 *         ties are resolved so that states with lowest state value are ordered first. 
 */

public class AStarSearchOrder implements SearchOrder {

	/**
	 * Method to add the given node in the given list of fringe nodes at the right
	 * position to ensure the list is sorted in ascending order.
	 * The nodes in the fringe are added in such a way that the node with the lowest cost
	 * is put before the rest of the nodes and this node is expanded first.
	 * 
	 * @param nodes     - list of FringeNode objects to which the node is added
	 * @param nodeToAdd - node to add to the given list of FringeNode objects
	 */
	public void compare(List<FringeNode> nodes, FringeNode nodeToAdd) {
		// to iterate through the given list of nodes
		for (int i = 0; i < nodes.size(); i++) { // - 1
			FringeNode node = nodes.get(i); // to get the element at the ith position in the list
			
			// to check if the fvalue of both the nodes are equal, if they are equal,
			// they are added to the fringe in the increasing order of their value of their state.
			if (node.getFValue() == nodeToAdd.getFValue()) {
				StateImpl state = (StateImpl) node.node.getValue(); // state value of the node
				StateImpl stateToAdd = (StateImpl) nodeToAdd.node.getValue(); // state value of the nodeToAdd
				if (state.getValue() > stateToAdd.getValue()) {
					nodes.remove(nodeToAdd); // nodeToAdd is remove from the list to avoid duplication
					nodes.add(i, nodeToAdd); // nodeToAdd is added at the position of the node (i)
					// if the node has been added to the right position no more checks need to be
					// done as the list is sorted.
					break;
				}
			}
			
			// to check if the f-value of the node is greater than the f-value of the nodeToAdd
			else if (node.getFValue() > nodeToAdd.getFValue()) {
				// to check if the f-value of the node is greater than the f-value of the nodeToAdd
				nodes.remove(nodeToAdd); // nodeToAdd is remove from the list to avoid duplication
				nodes.add(i, nodeToAdd); // nodeToAdd is added at the position of the node (i)
				// if the node has been added to the right position no more checks need to be
				// done as the list is sorted.
				break;
			}
		}
	}

	/**
	 * Method to add elements- children of the given parent node to the
	 * frontier/fringe
	 * 
	 * @param frontier - the frontier/fringe to which the elements are added
	 * @param parent   - the node that is currently visited; the node to expand
	 * @param children - the set of children of the given parent node to be added to
	 *                 the fringe
	 */
	@Override
	public void addToFringe(List<FringeNode> frontier, FringeNode parent, Set<ChildWithCost> children) {
		for (ChildWithCost child : children) { // iterate through all the children of the parent in the children set
			// creating a new FringeNode object to store the details of the child
			FringeNode node = new FringeNode(child.node, parent, child.cost);
			frontier.add(node); // the node is added to the end of the frontier
			// to compare the node with the other elements in the frontier and place it at
			// the right position to have a sorted list
			compare(frontier, node);
		}
	}

}
