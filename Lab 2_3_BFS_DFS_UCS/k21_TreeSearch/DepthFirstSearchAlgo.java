package k21_TreeSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if(currentNode.getLabel().equals(goal)) return currentNode;
			explored.add(currentNode);
			List<Node> children = currentNode.getChildrenNodes();
			for (int i = children.size()-1; i >= 0; i--) {
				children.get(i).setParent(currentNode);
				Node n = new Node(children.get(i).getLabel());
				n.setParent(children.get(i).getParent());
				for (Node node : children.get(i).getChildrenNodes()) {
					n.addEdge(node);
				}
				frontier.add(n);
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		if (root.getLabel().equals(goal)) return root;
		if (start.equals(goal)) return new Node(goal);
		Node strartNode = execute(root, start);
		if (strartNode == null) return null;
		strartNode.setParent(null);
		return execute(strartNode, goal);
	}

}
