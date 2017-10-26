package test.test_restricted_boltzmann_machines;

import classifiers.generative_graphical_models.RestrictedBoltzmannMachine;
import dataset.BinaryData;
import dataset.DiscriminativeData;
import dataset.LabelledData;
import test.ConfusionMatrix;
import util.BinaryParser;
import util.Utils;

import java.util.Arrays;

public class TestDRBMsEnergy1 {

	private static double[][] testDiscriminativeRBMs() throws Exception {

		/* It was used with 50 columns to return an average of the scores
		 * returning it in the main method and the making the average among 
		 * any element in the same row */
		double[][] bunchOfTests= new double[2][1];
		
		/* Use ConfusionMatrix objects for any class to have better understanding 
		 * than the proportion of correct predictions (accuracy) */
		ConfusionMatrix class0 = new ConfusionMatrix(0);
		ConfusionMatrix class1 = new ConfusionMatrix(1);
		ConfusionMatrix class2 = new ConfusionMatrix(2);
		ConfusionMatrix class3 = new ConfusionMatrix(3);
		
		for(int indexOfTest = 0; indexOfTest < bunchOfTests[0].length; indexOfTest++) {
			
			// 1. Initialise dataset /* data as binary/binomial BernoulliRBM */
			BinaryData[] trainingData = BinaryParser.parse("cw2DataSet1.csv");
			BinaryData[] testingData = BinaryParser.parse("cw2DataSet2.csv");
			// Subdivide the training dataset in MiniBatches containing
			// only data of class that we want to train for and learn to recognise
			int[][] batchClass0 = DiscriminativeData.getDiscriminativeSet(trainingData, 0);
			int[][] batchClass1Big = DiscriminativeData.getDiscriminativeSet(trainingData, 1);
			int[][] batchClass1 = Arrays.copyOfRange(batchClass1Big, 0, batchClass1Big.length / 3);
			int[][] batchClass2 = DiscriminativeData.getDiscriminativeSet(trainingData, 2);
			int[][] batchClass3 = DiscriminativeData.getDiscriminativeSet(trainingData, 3);
			int[][] batchClass4 = DiscriminativeData.getDiscriminativeSet(trainingData, 4);
			int[][] batchClass5 = DiscriminativeData.getDiscriminativeSet(trainingData, 5);
			int[][] batchClass6 = DiscriminativeData.getDiscriminativeSet(trainingData, 6);
			int[][] batchClass7 = DiscriminativeData.getDiscriminativeSet(trainingData, 7);
			int[][] batchClass8Big = DiscriminativeData.getDiscriminativeSet(trainingData, 8);
			int[][] batchClass8 = Arrays.copyOfRange(batchClass8Big, 0, batchClass8Big.length / 3);
			int[][] batchClass9Big = DiscriminativeData.getDiscriminativeSet(trainingData, 9);
			int[][] batchClass9 = Arrays.copyOfRange(batchClass9Big, 0, batchClass9Big.length / 2);

			// 2. Initialise RBM
			final int NUMBER_HIDDEN_UNITS = 12;
			final int NUMBER_VISIBLE_UNITS = LabelledData.SIZE;

			// learningRate = 0.01; -> Accuracy on the testing set: 83.355871886121
			// %
			final double learningRate = 0.01;
			final int trainingEpochs = 1;
			final int gibbsSteps = 1; // referred as k in most papers

			RestrictedBoltzmannMachine rbm0 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm1 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm2 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm3 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm4 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm5 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm6 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm7 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm8 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);
			RestrictedBoltzmannMachine rbm9 = new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS, learningRate, gibbsSteps, trainingEpochs);

			// 3. Fit/Train the RBMs
			/* Any of them with a different dataset memorising the class */
			rbm0.fit(batchClass0);
			rbm1.fit(batchClass1);
			rbm2.fit(batchClass2);
			rbm3.fit(batchClass3);
			rbm4.fit(batchClass4);
			rbm5.fit(batchClass5);
			rbm6.fit(batchClass6);
			rbm7.fit(batchClass7);
			rbm8.fit(batchClass8);
			rbm9.fit(batchClass9);

			// 4. test with new input and energy that receives any RBM
			int[][] testData = DiscriminativeData.getUnlabelledDataAsInt(testingData);
			int[] testLabels = new int[testingData.length];
			testLabels = Utils.getDataLabelsAsInt(testingData);
			int counter = 0;

			for (int i = 0; i < testData.length; i++) {

				double energy0 = rbm0.freeEnergy(testData[i]);
				double energy1 = rbm1.freeEnergy(testData[i]);
				double energy2 = rbm2.freeEnergy(testData[i]);
				double energy3 = rbm3.freeEnergy(testData[i]);
				double energy4 = rbm4.freeEnergy(testData[i]);
				double energy5 = rbm5.freeEnergy(testData[i]);
				double energy6 = rbm6.freeEnergy(testData[i]);
				double energy7 = rbm7.freeEnergy(testData[i]);
				double energy8 = rbm8.freeEnergy(testData[i]);
				double energy9 = rbm9.freeEnergy(testData[i]);
				// get least energy and classify as the rbm class where the energy belongs
				int solutionPredicted = predict(energy0, energy1, energy2, energy3, energy4, energy5, energy6, energy7,
						energy8, energy9);

				if (solutionPredicted == testLabels[i]) {
					counter++;
				}
				// else {
				// System.out.println("predicted: " + solutionPredicted + ", real: " + testLabels[i]);
			}

			double accuracyOnTestingSet = (double) counter / testingData.length;
			System.out.println("Testing set size: " + testingData.length);
			System.out.println("Classes predicted right: " + counter);
			System.out.println("Accuracy on the testing set: " + accuracyOnTestingSet * 100 + " %\n");

			bunchOfTests[0][indexOfTest] = accuracyOnTestingSet;
			
			// SECOND TEST
			// 1. Initialise and invert the datasets (now train with last testing data)
			trainingData = BinaryParser.parse("cw2DataSet2.csv");
			testingData = BinaryParser.parse("cw2DataSet1.csv");
			// 2. Use the RBMs already initialised because the dataset does not change
			// 3. Fit with test data
			rbm0.fit(batchClass0);
			rbm1.fit(batchClass1);
			rbm2.fit(batchClass2);
			rbm3.fit(batchClass3);
			rbm4.fit(batchClass4);
			rbm5.fit(batchClass5);
			rbm6.fit(batchClass6);
			rbm7.fit(batchClass7);
			rbm8.fit(batchClass8);
			rbm9.fit(batchClass9);
			// 4. test with new input and energy that receives any RBM
			testData = DiscriminativeData.getUnlabelledDataAsInt(testingData);
			testLabels = new int[testingData.length];
			testLabels = Utils.getDataLabelsAsInt(testingData);
			int counter2 = 0;
			for (int i = 0; i < testData.length; i++) {
				double energy0 = rbm0.freeEnergy(testData[i]);
				double energy1 = rbm1.freeEnergy(testData[i]);
				double energy2 = rbm2.freeEnergy(testData[i]);
				double energy3 = rbm3.freeEnergy(testData[i]);
				double energy4 = rbm4.freeEnergy(testData[i]);
				double energy5 = rbm5.freeEnergy(testData[i]);
				double energy6 = rbm6.freeEnergy(testData[i]);
				double energy7 = rbm7.freeEnergy(testData[i]);
				double energy8 = rbm8.freeEnergy(testData[i]);
				double energy9 = rbm9.freeEnergy(testData[i]);
				// get least energy and classify as the RBM class where the energy belongs
				int solutionPredicted = predict(energy0, energy1, energy2, energy3, energy4, energy5, energy6, energy7,
						energy8, energy9);
				
				/* Use ConfusionMatrix objects for any class to have better understanding 
				 * than the proportion of correct predictions (accuracy) */			
				class0.updateClassConditions(solutionPredicted, testLabels[i]);
				class1.updateClassConditions(solutionPredicted, testLabels[i]);
				class2.updateClassConditions(solutionPredicted, testLabels[i]);
				class3.updateClassConditions(solutionPredicted, testLabels[i]);

				if (solutionPredicted == testLabels[i]) {
					counter2++;
				} else {
					
				}
			}

			double accuracyOnTestingSet2 = (double) counter2 / testingData.length;
			System.out.println("Testing set size: " + testingData.length);
			System.out.println("Classes predicted right: " + counter2);
			System.out.println("Accuracy on the testing set: " + accuracyOnTestingSet2 * 100 + " %\n");
			
			bunchOfTests[0][indexOfTest] = accuracyOnTestingSet2;
			int totalRightLabels = counter2 + counter;
			int fullDatasetSize = testingData.length + trainingData.length;
			
			// finally printing in the console the average between the 2 datasets
			double totalAccuracy = (accuracyOnTestingSet + accuracyOnTestingSet2)/2;
			System.out.println("Testing set size: " + fullDatasetSize);
			System.out.println("Classes predicted right: " + totalRightLabels);
			System.out.println("Accuracy on the testing set: " + totalAccuracy * 100 + " %\n");
			
			class0.printConfusionMatrix();
			class1.printConfusionMatrix();
			class2.printConfusionMatrix();
			class3.printConfusionMatrix();

		}
		
		return bunchOfTests;
		
	}

	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();
		System.out.println("Discriminative RBMs training and testing being performed...\n");

		testDiscriminativeRBMs();

		long endTime = System.currentTimeMillis();
		System.out.println("Took: " + ((endTime - startTime) / 1000) + " seconds.");
	}
	
	public static int predict(double energy0, double energy1, double energy2, double energy3, double energy4,
			double energy5, double energy6, double energy7, double energy8, double energy9) {

		int solutionClass = 0;
		double min = Double.MAX_VALUE;
		double[] energyArray = new double[10];

		energyArray[0] = energy0;
		energyArray[1] = energy1;
		energyArray[2] = energy2;
		energyArray[3] = energy3;
		energyArray[4] = energy4;
		energyArray[5] = energy5;
		energyArray[6] = energy6;
		energyArray[7] = energy7;
		energyArray[8] = energy8;
		energyArray[9] = energy9;

		for (int i = 0; i < energyArray.length; i++) {
			if (min > energyArray[i]) {
				min = energyArray[i];
				solutionClass = i;
			}
		}
		return solutionClass;
	}
}
