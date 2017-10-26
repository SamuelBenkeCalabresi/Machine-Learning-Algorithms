package test.test_nearest_neighbors;

import classifiers.nearest_neighbors.NearestNeighbors;
import dataset.LabelledData;
import util.LabelledParser;

public class TestNearestNeighbors {

	private static void testNearestNeighbors() {
		
		// 1. get data transformed in LabelledData
		LabelledData[] trainingDataSet = LabelledParser.parse("cw2DataSet1.csv");
		LabelledData[] testingDataSet = LabelledParser.parse("cw2DataSet2.csv");

		// 2. create the classifiers using the data
		NearestNeighbors NNClassifier = new NearestNeighbors(trainingDataSet, testingDataSet);
		NearestNeighbors NNClassifier2 = new NearestNeighbors(testingDataSet, trainingDataSet);
		
		NNClassifier.fit();
		NNClassifier2.fit();

		// 3. make testing with a TwoFoldTest helper object for any type of
		// classifier using the data
		TwoFoldTest twoFoldTest = new TwoFoldTest(trainingDataSet, NNClassifier.getSolutions(), testingDataSet,
				NNClassifier2.getSolutions());

		// 4. show the accuracy of KNN
		twoFoldTest.score();
	}

	public static void main(String[] args) {
		testNearestNeighbors();
	}
}
