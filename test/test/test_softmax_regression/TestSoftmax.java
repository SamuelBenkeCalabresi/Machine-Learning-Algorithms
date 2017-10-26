package test.test_softmax_regression;

import static util.Utils.getDataLabelsAsInt;

import classifiers.softmax_regression.SoftmaxRegression;
import dataset.LabelledData;
import util.LabelledParser;

public class TestSoftmax {
	
	private static void testSoftmax() {
		
		// 1. get data as LabelledData instances
		LabelledData[] trainingDataSet = LabelledParser.parse("cw2DataSet1.csv");
		LabelledData[] testingDataSet  = LabelledParser.parse("cw2DataSet2.csv");
		
		// I train the SoftmaxRegression classifier with a matrix of training data and and correspondent class label
		double[][] trainingData = new double[trainingDataSet.length][LabelledData.SIZE];
		int[][] trainingLabels =  new int[trainingDataSet.length][LabelledData.SOLUTION_LENGTH];
		
		double[][] testingData = new double[testingDataSet.length][LabelledData.SIZE];
		int[][] testingLabels = new int[testingDataSet.length][LabelledData.SOLUTION_LENGTH];
		
		trainingLabels = getDataLabelsAsInt(trainingDataSet);
		testingLabels = getDataLabelsAsInt(testingDataSet);
		
		for(int i = 0; i < trainingData.length; i++) {
			trainingData[i] = trainingDataSet[i].getDataAsDouble();
			testingData[i] = testingDataSet[i].getDataAsDouble();
		}
		
		// 2. Instance of SoftmaxRegression to fit with data training
		/* Learning Rate test on UCI data set */
		// Test 0.5 =  2679 right
		// Test 0.25 = 2684 right
		// Test 0.15 = 2687 right
		// Test 0.1 =  2683 right
		// Test 0.08 = 2684 right 
		// Test 0.01 = 2679 right again
		double learningRate = 0.1;
		/* number of epochs test on UCI data set*/
		// Test 300 = 2684
		// Test 500 = 2687
		// Test 700 = 2684
		int numberOfEpochs = 250;

		SoftmaxRegression softmaxClassifier = new SoftmaxRegression(LabelledData.SIZE, LabelledData.SOLUTION_LENGTH,
				learningRate, numberOfEpochs);
		SoftmaxRegression softmaxClassifier2 = new SoftmaxRegression(LabelledData.SIZE, LabelledData.SOLUTION_LENGTH,
				learningRate, numberOfEpochs);
		// 3. Fit the training data 
	    // This is the way without using LabelledData instance
		 softmaxClassifier.fit(trainingData, trainingLabels);
		 softmaxClassifier2.fit(testingData, testingLabels);
		
		// 4. Test with a two fold test instance
		TwoFoldTest twoFoldTest = new TwoFoldTest(trainingDataSet, testingDataSet);		
		twoFoldTest.score(softmaxClassifier, softmaxClassifier2, testingData, trainingData, false);	
	}

	public static void main(String [] args) {

		long startTime = System.currentTimeMillis();
		System.out.println("Two fold test is being performed...\n");
		
		testSoftmax();
		
		long endTime   = System.currentTimeMillis();
		System.out.println("Took: " + ((endTime - startTime) / 1000) + " seconds.");
	}	
}