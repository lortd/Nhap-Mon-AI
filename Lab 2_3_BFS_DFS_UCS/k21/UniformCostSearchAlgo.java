package k21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo{

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				int pc = Double.compare(o1.getPathCost(), o2.getPathCost());
				if (pc != 0) {
					return pc;
				} else {
					return o1.compareTo(o2);
				}				
			}			
		});
		
		List<Node> explored = new ArrayList<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) return currentNode;
			explored.add(currentNode);
			List<Edge> children = currentNode.getChildren();
			for (Edge child : children) {
				Node n = child.getEnd();
				double newPathCost = currentNode.getPathCost() + child.getWeight();
				if (!frontier.contains(n) && !explored.contains(n)) {
					n.setParent(currentNode);
					n.setPathCost(newPathCost);
					frontier.add(n);
				} else if (n.getPathCost()>newPathCost){
					n.setParent(currentNode);
					n.setPathCost(newPathCost);
				}
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
		strartNode.setPathCost(0);
		return execute(strartNode, goal);
	}
}
