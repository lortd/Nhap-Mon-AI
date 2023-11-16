package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	public static final int N = 8;
	private Queen[] state;

	public Node() {
		// generateBoard();
		state = new Queen[N];
	}

	public Node(Queen[] state) {
		this.state = new Queen[N];
		for (int i = 0; i < state.length; i++) {
			this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
		}
	}

	public Node(Node n) {
		this.state = new Queen[N];
		for (int i = 0; i < N; i++) {
			Queen qi = n.state[i];
			this.state[i] = new Queen(qi.getRow(), qi.getColumn());
		}
	}

	public void generateBoard() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			state[i] = new Queen(random.nextInt(N), i);
		}
	}

	public int getH() {
		int heuristic = 0;
		for (int i = 0; i < state.length - 1; i++) {
			for (int j = 1; j < state.length; j++) {
				if (state[i].isConflict(state[j])) {
					heuristic++;
				}
			}
		}
		return heuristic;
	}

	public List<Node> generateAllCandidates() {
		List<Node> result = new ArrayList<Node>();
		for (int i = 0; i < N; i++) {
			Node tmp = new Node(this.state);
			tmp.state[i].move();
			result.add(tmp);
		}
		return result;
	}

	public Node selectNextRandomCandidate() {
		Node re = new Node(this.state);
		Random r = new Random();
		int i = r.nextInt(N);
		int row = r.nextInt(N);
		re.state[i].setRow(row);
		return re;
	}

	public void displayBoard() {
		int[][] board = new int[N][N];
		// set queen position on the board
		for (int i = 0; i < N; i++) {
			board[state[i].getRow()][state[i].getColumn()] = 1;
		}
		// print board
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1) {
					System.out.print("Q" + " ");
				} else {
					System.out.print("-" + " ");
				}
			}
			System.out.println();
		}
	}

	public Node execute(Node initialState) {
		Node currentNode = initialState;
		Node neighbor = null;
		while (true) {
			neighbor = currentNode.getBestCandidate();
			if (currentNode.getH() > neighbor.getH())
				currentNode = neighbor;
			else
				return currentNode;
		}
	}

	public Node getBestCandidate() {
		List<Node> candidates = generateAllCandidates();
		Node bestCandidate = candidates.get(0);

		for (int i = 1; i < candidates.size(); i++) {
			Node candidate = candidates.get(i);
			if (candidate.getH() < bestCandidate.getH()) {
				bestCandidate = candidate;
			}
		}
		return bestCandidate;
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		int maxRestarts = 100, restarts = 0;
		Node state = initialState.execute(initialState);

		while (state.getH() != 0) {
			restarts++;
			if (restarts >= maxRestarts) {
				break;
			}
			state.generateBoard();
			state = state.execute(state);
		}
		return state;
	}

	public Node executeSA(Node initialState) {
		Node current = initialState;
		double temperature = 1000;
		double coolingRate = 0.99;

		while (temperature > 0.1) {
			Node next = current.selectNextRandomCandidate();

			int currentHeuristic = current.getH();
			int nextHeuristic = next.getH();

			if (acceptNeighbor(currentHeuristic, nextHeuristic, temperature)) {
				current = next;
			}

			temperature *= coolingRate;
		}

		if (current.getH() == 0) {
			return current;
		}
		return current;
	}

	private boolean acceptNeighbor(int currentHeuristic, int nextHeuristic, double temperature) {
		double acceptanceProbability = Math.exp((currentHeuristic - nextHeuristic) / temperature);
		if (nextHeuristic < currentHeuristic || Math.random() < acceptanceProbability)
			return true;
		else
			return false;
	}
}
