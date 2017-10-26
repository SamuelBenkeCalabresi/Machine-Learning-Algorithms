package test.test_softmax_regression;

import classifiers.softmax_regression.SoftmaxRegression;
import dataset.LabelledData;

public class TwoFoldTest {
	private final LabelledData[] dataSet1;
	private final LabelledData[] dataSet2;
	private int[] solutions1;
	private int[] solutions2;
	
	public TwoFoldTest(LabelledData[] dataSet1, int[] solutions1, LabelledData[] dataSet2, int[] solutions2) {
		this.dataSet1 = dataSet1;
		this.dataSet2 = dataSet2;
		this.solutions1 = solutions1;
		this.solutions2 =  solutions2;
	}
	
	public TwoFoldTest(LabelledData[] dataSet1,	LabelledData[] dataSet2){
		this.dataSet1 = dataSet1;
		this.dataSet2 = dataSet2;
	}
	
	/* It is used to call both the test and give the the results
	 * of both of them + the total results accuracy */
	public void score() {
		misureAccuracy(dataSet1, solutions1);
		misureAccuracy(dataSet2, solutions2);
	}
	
	private void misureAccuracy(LabelledData[] dataSet, int [] solutions) {
		int counter = 0;

		for(int i = 0; i < solutions.length; i ++) {
			if(solutions[i] == dataSet[i].getSolution())
				counter ++;
		}

		// Change it in a better way
//		System.out.println("hit count: " + counter + " solutions.lenght: " + solutions.length);
//		System.out.println("hit count: " + (double) counter / solutions.length);
		
		double accuracyOnTestingSet = (double) counter / dataSet2.length;
		System.out.println("Testing set size: " + dataSet2.length);
		System.out.println("Classes predicted right: " + counter);
		System.out.println("Accuracy on the testing set: " + accuracyOnTestingSet * 100 + " %");
	}
	
	public void score(SoftmaxRegression softmaxClassifier,SoftmaxRegression softmaxClassifier2, double[][] testingData, 
			double[][] testingData2, boolean checkWrongAnswer) {
		
		
		double score1 = measureScore(softmaxClassifier, testingData, checkWrongAnswer, 1);
		double score2 = measureScore(softmaxClassifier2, testingData2, checkWrongAnswer, 2);
		
		measureTotalAccuracy(score1, score2);

	}
	
	
	public double measureScore(SoftmaxRegression softmaxClassifier, double[][] testingData, boolean checkWrongAnswer, int test) {
		
		int counter = 0;
		
		if(test == 1) {
			for(int i = 0; i < dataSet2.length; i ++) {
				if(dataSet2[i].getSolution() == softmaxClassifier.predict(testingData[i])) {
					counter ++; 
				}
				else if (checkWrongAnswer){
					
					System.out.println("Wrong anwer: ");
					System.out.println("predict solution: " + softmaxClassifier.predict(testingData[i]));
					System.out.println("real solution:    " + dataSet2[i].getSolution() + "\n");
				}
				
			}	
			
			return accuracy(counter, testingData);
		}
		else {
			
			for(int i = 0; i < dataSet1.length; i++) {
				if(dataSet1[i].getSolution() == softmaxClassifier.predict(testingData[i])) {
					counter ++; 
				}
				else if (checkWrongAnswer){
					
					System.out.println("Wrong anwer: ");
					System.out.println("predict solution: " + softmaxClassifier.predict(testingData[i]));
					System.out.println("real solution:    " + dataSet1[i].getSolution() + "\n");
				}
				
			}
			
			return accuracy(counter, testingData);
		}	
	}
	
	private double accuracy(int counter, double[][] testingData) {
		
		double accuracyOnTestingSet = (double) counter / testingData.length;
		System.out.println("Testing set size: " + testingData.length);
		System.out.println("Classes predicted right: " + counter);
		System.out.println("Accuracy on the testing set: " + accuracyOnTestingSet * 100 + " %\n");
		
		return accuracyOnTestingSet;
	}
	
	private void measureTotalAccuracy(double accuracy1, double accuracy2) {
		int classesPredictedRight =  (int) (accuracy1 * dataSet1.length);
		int classesPredictedRight2 = (int) (accuracy2 * dataSet2.length);
		int classesPredicted = classesPredictedRight + classesPredictedRight2;
		double accuracy = accuracy1 + accuracy2;
		int totalSetSize = dataSet2.length + dataSet1.length;
		
		System.out.println("Total test set size: " + totalSetSize);
		System.out.println("Classes predicted right: " + classesPredicted);
		System.out.println("Accuracy on the whole test: " + (accuracy/2) * 100 + " %\n");		
	}
	
}
