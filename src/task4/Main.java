package task4;

import java.lang.Thread.State;

//TASK:
//Develop an algorithm, using the strategies which were previously implemented (Threads and mechanisms of concurrence
//control )in order to solve the lower linear triangular system Lx = b where Lx = b is a lower triangular matrix. The 
//Its algorithm needs to use two threads: one to calculate the value of (that is to solve the system) and the other to	
//calculate the summation.

public class Main {

	public static void main(String[] args) {
		int[][] lowerArray = {
				{2, 0, 0},
				{1, 4, 0},
				{1, 1, 1}
		};
		int[] vectorB = {2, -3, 1};
		int[] vectorX = new int[3];
		int sum;
		
		ThreadSum threadSum = new ThreadSum(lowerArray, vectorB);
		
		try {
			threadSum.start();
		
			for(int i = 0; i < vectorX.length; i++) {
				threadSum.notice();
				while((threadSum.getState() != State.WAITING) && (threadSum.getState() != State.TERMINATED))
					Thread.sleep(1000);
				sum = threadSum.getSum();
				System.out.println(vectorX[i]);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}