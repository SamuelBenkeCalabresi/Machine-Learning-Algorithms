package classifiers.nearest_neighbors;

import classifiers.AbstractClassifier;
import dataset.LabelledData;

public class NearestNeighbors extends AbstractClassifier {
	/*
	 * This attribute is just used to return the whole predicted solution even
	 * though predict in standard classifier should return only one class label
	 * for only one test input
	 */
	private int[] solutions = new int[testingDataSet.length];

	/*
	 * Construct of non-parametric learning algorithm does not have any
	 * Hyper-Parameter to initialise in the construct
	 */
	public NearestNeighbors(LabelledData[] testingDataSet, LabelledData[] trainingDataSet) {
		super(testingDataSet, trainingDataSet);
	}

	/*
	 * It should call the train procedure but in the case of KNN it is together
	 * in the predict label
	 */
	@Override
	public void fit() {
		predict();
	}

	/*
	 * Predict method predicts all the new class labels of the testing set in
	 * input. It just counts the distance from test point to training ones
	 */
	@Override
	public void predict() {
		int[] solutions = new int[testingDataSet.length];

		for (int indexTestData = 0; indexTestData < testingDataSet.length; indexTestData++) {
			LabelledData closestDigit = null;
			double closestDistance = Double.MIN_VALUE;

			for (int indexTrainData = 0; indexTrainData < trainingDataSet.length; indexTrainData++) {
				double currentDistance = measureEuclideanDistance(testingDataSet[indexTestData],
						trainingDataSet[indexTrainData]);

				// if currentDistance is closer than closestDistance change the
				// distance and the element
				if (closestDistance == Double.MIN_VALUE || currentDistance < closestDistance) {
					closestDigit = trainingDataSet[indexTrainData];
					closestDistance = currentDistance;
				}

				solutions[indexTestData] = closestDigit.getSolution();
			}
		}
		this.setSolutions(solutions);
	}

	/*
	 * misureEuclideanDistance is the function to concretely measure the
	 * distance using the Euclidean Distance method other function might be
	 * applied.
	 */
	private double measureEuclideanDistance(LabelledData labelledTestingData, LabelledData labelledTrainingData) {

		double sum = 0;
		int[] testingData = labelledTestingData.getData();
		int[] trainingData = labelledTrainingData.getData();

		for (int indexTestData = 0; indexTestData < testingData.length; indexTestData++) {
			sum += Math.pow(testingData[indexTestData] - trainingData[indexTestData], 2);
		}
		return Math.sqrt(sum);
	}

	public int[] getSolutions() {
		return solutions;
	}

	public void setSolutions(int[] solutions) {
		this.solutions = solutions;
	}

}
