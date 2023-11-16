package student;

public class TestDrive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queen q1 = new Queen(4, 0);
		Queen q2 = new Queen(5, 1);
		Queen q3 = new Queen(6, 2);
		Queen q4 = new Queen(3, 3);
		Queen q5 = new Queen(4, 4);
		Queen q6 = new Queen(5, 5);
		Queen q7 = new Queen(6, 6);
		Queen q8 = new Queen(5, 7);
		Queen[] state = new Queen[8];
		state[0] = q1;
		state[1] = q2;
		state[2] = q3;
		state[3] = q4;
		state[4] = q5;
		state[5] = q6;
		state[6] = q7;
		state[7] = q8;
		Node n = new Node(state);

		System.out.println("*Hill-Climbing Search(traditional):");
		System.out.println("Initial State:");
		System.out.println("Initial Heuristic: " + n.getH());
		n.displayBoard();
		Node reHC = n.execute(n);
		System.out.println("\nBest Solution:");
		System.out.println("Heuristic: " + reHC.getH());
		reHC.displayBoard();

		System.out.println();
		System.out.println("*Hill-Climbing With Random Restart:");
		System.out.println("Initial State:");
		System.out.println("Initial Heuristic: " + n.getH());
		n.displayBoard();
		Node reHCwrs = n.executeHillClimbingWithRandomRestart(n);
		System.out.println("\nBest Solution:");
		System.out.println("Heuristic: " + reHCwrs.getH());
		reHCwrs.displayBoard();

		System.out.println("====== Additional Task ======");
		System.out.println("*Hill-Climbing Search(traditional):");
		System.out.println("Initial State:");
		System.out.println("Initial Heuristic: " + n.getH());
		n.displayBoard();
		Node reHCa = n.execute(n);
		System.out.println("\nBest Solution:");
		System.out.println("Heuristic: " + reHCa.getH());
		reHCa.displayBoard();

		System.out.println();
		System.out.println("*Hill-Climbing With Random Restart:");
		System.out.println("Initial State:");
		System.out.println("Initial Heuristic: " + n.getH());
		n.displayBoard();
		Node reHCwrsa = n.executeHillClimbingWithRandomRestart(n);
		System.out.println("\nBest Solution:");
		System.out.println("Heuristic: " + reHCwrsa.getH());
		reHCwrsa.displayBoard();

		System.out.println("*Simulated Annealing Search(traditional):");
		System.out.println("Initial State:");
		System.out.println("Initial Heuristic: " + n.getH());
		n.displayBoard();
		Node reSA = n.executeSA(n);
		System.out.println("\nBest Solution:");
		System.out.println("Heuristic: " + reSA.getH());
		reSA.displayBoard();

	}

}
