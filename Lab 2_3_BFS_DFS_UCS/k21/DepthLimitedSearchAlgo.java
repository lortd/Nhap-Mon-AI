package k21;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthLimitedSearchAlgo {	
	public Node execute(Node root, String goal, int limitedDepth) {
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if (currentNode.getLabel().equals(goal)) return currentNode;
			explored.add(currentNode);
			if (currentNode.getDepth()<limitedDepth) {
				List<Node> children = currentNode.getChildrenNodes();
				for (int i = children.size()-1; i >= 0; i--) {
					if (!frontier.contains(children.get(i)) && !explored.contains(children.get(i))) {
						children.get(i).setParent(currentNode);
						children.get(i).setDepth(currentNode.getDepth() + 1);
						frontier.add(children.get(i));						
					}
				}
			}
		}
		return null;
	}
}
