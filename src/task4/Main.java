package task4;

import java.lang.Thread.State;

//TASK:
//Develop an algorithm, using the strategies which were previously implemented (Threads and mechanisms of concurrence
//control )in order to solve the lower linear triangular system Lx = b where Lx = b is a lower triangular matrix. The 
//Its algorithm needs to use two threads: one to calculate the value of (that is to solve the system) and the other to	
//calculate the summation.

public class Main {

	public static void main(String[] args) {
		float[][] lowerArray = {
				{2, 0, 0},
				{1, 4, 0},
				{1, 1, 1}
		};
		float[] vectorB = {2, -3, 1};
		
		ThreadSum threadSum = new ThreadSum(lowerArray);
		ThreadVector threadVector = new ThreadVector(lowerArray, vectorB);
		
		try {
			threadSum.start();
			threadVector.start();
			
			Thread.sleep(3000);
		
			System.out.println("Vetor X:");
			for(int i = 0; i < vectorB.length; i++) {
				threadSum.notice();
				while((threadSum.getState() != State.WAITING) && (threadSum.getState() != State.TERMINATED))
					Thread.sleep(1000);
				threadVector.setSum(threadSum.getSum());
				
				threadVector.notice();
				while((threadVector.getState() != State.WAITING) && (threadVector.getState() != State.TERMINATED))
					Thread.sleep(1000);
				threadSum.setVectorX(threadVector.getVectorX());
				
				
				System.out.println("( " + threadVector.getVectorX()[i] + " )");
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
