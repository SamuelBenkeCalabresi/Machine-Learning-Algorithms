package test.test_nearest_neighbors;
import dataset.LabelledData;
import test.ConfusionMatrix;

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
	
	public void score() {
		double accuracySet1 = measureAccuracy(dataSet1, solutions1);
		double accuracySet2 = measureAccuracy(dataSet2, solutions2);
		
		measureTotalAccuracy(accuracySet1, accuracySet2);
	}
	
	private double measureAccuracy(LabelledData[] dataSet, int [] solutions) {
		/* Use ConfusionMatrix objects for any class to have better understanding 
		 * than the proportion of correct predictions (accuracy) */
		/* Now are just commented to don't return them */
//		ConfusionMatrix class0 = new ConfusionMatrix(0);
//		ConfusionMatrix class1 = new ConfusionMatrix(1);
//		ConfusionMatrix class2 = new ConfusionMatrix(2);
//		ConfusionMatrix class3 = new ConfusionMatrix(3);
//		ConfusionMatrix class4 = new ConfusionMatrix(4);
//		ConfusionMatrix class5 = new ConfusionMatrix(5);
//		ConfusionMatrix class6 = new ConfusionMatrix(6);
//		ConfusionMatrix class7 = new ConfusionMatrix(7);
//		ConfusionMatrix class8 = new ConfusionMatrix(8);
//		ConfusionMatrix class9 = new ConfusionMatrix(9);
		int counter = 0;

		for(int i = 0; i < solutions.length; i ++) {
//			class0.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class1.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class2.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class3.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class4.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class5.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class6.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class7.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class8.updateClassConditions(solutions[i], dataSet[i].getSolution());
//			class9.updateClassConditions(solutions[i], dataSet[i].getSolution());
			
			if(solutions[i] == dataSet[i].getSolution())
				counter ++;
		}		
		
//		class0.printConfusionMatrix();
//		class1.printConfusionMatrix();
//		class2.printConfusionMatrix();
//		class3.printConfusionMatrix();
//		class4.printConfusionMatrix();
//		class5.printConfusionMatrix();
//		class6.printConfusionMatrix();
//		class7.printConfusionMatrix();
//		class8.printConfusionMatrix();
//		class9.printConfusionMatrix();
		
		double accuracyOnTestingSet = (double) counter / dataSet2.length;
		System.out.println("Testing set size: " + dataSet2.length);
		System.out.println("Classes predicted right: " + counter);
		System.out.println("Accuracy on the testing set: " + accuracyOnTestingSet * 100 + " %");
		
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
		System.out.println("Accuracy on the whole test: " + (accuracy/2) * 100 + " %");		
	}

	
}