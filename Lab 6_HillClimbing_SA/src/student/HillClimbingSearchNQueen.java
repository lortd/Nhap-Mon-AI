package student;

public class HillClimbingSearchNQueen extends Node {
	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;

	public Node execute(Node initialState) {
		Node currentNode = initialState;
		Node neighbor = null;
		while (true) {
			neighbor = currentNode.getBestCandidate();
			if (currentNode.getH() > neighbor.getH()) {
				currentNode = neighbor;
				stepClimbed++;
			} else
				return currentNode;
		}
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		int maxRestarts = 100, restarts = 0;
		Node state = initialState.execute(initialState);

		while (state.getH() != 0) {
			restarts++;
			if (restarts >= maxRestarts) {
				randomRestarts = restarts - 1;
				break;
			}
			state.generateBoard();
			state = state.execute(state);
			stepClimbedAfterRandomRestart += stepClimbed;
			stepClimbed = 0;
		}
		return state;
	}
}
