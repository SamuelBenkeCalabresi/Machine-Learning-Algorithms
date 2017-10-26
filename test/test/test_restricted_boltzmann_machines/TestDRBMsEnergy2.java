package test.test_restricted_boltzmann_machines;

import java.util.Arrays;
import classifiers.generative_graphical_models.RestrictedBoltzmannMachine;
import dataset.BinaryData;
import dataset.DiscriminativeData;
import dataset.LabelledData;
import util.BinaryParser;
import util.Utils;
/**
 * The Test number 2 of D-RBMs
 * shows just the same thing as the 
 * first one but with a different 
 * energy method. The results are almost
 * the same. Thus, is not using TwoFoldTest
 */
public class TestDRBMsEnergy2 {


	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();
		System.out.println("Discriminative RBMs training and testing being performed...\n");

		testDiscriminativeRBMs();

		long endTime   = System.currentTimeMillis();
		System.out.println("Took: " + ((endTime - startTime) / 1000) + " seconds.");
	}


	private static void testDiscriminativeRBMs() throws Exception {

		// 1. Initialise dataset
		BinaryData [] trainingData = BinaryParser.parse("cw2DataSet1.csv");
		BinaryData[] testingData  = BinaryParser.parse("cw2DataSet2.csv");
		// Subdivide the training dataset in MiniBatches containing 
		// only data of class that we want to train for and learn to recognise
		int[][] batchClass0 = DiscriminativeData.getDiscriminativeSet(trainingData, 0);
		int[][] batchClass1Big = DiscriminativeData.getDiscriminativeSet(trainingData, 1);
		int[][] batchClass1 = Arrays.copyOfRange(batchClass1Big, 0, batchClass1Big.length/3);
		int[][] batchClass2 = DiscriminativeData.getDiscriminativeSet(trainingData, 2);
		int[][] batchClass3 = DiscriminativeData.getDiscriminativeSet(trainingData, 3);
		int[][] batchClass4 = DiscriminativeData.getDiscriminativeSet(trainingData, 4);
		int[][] batchClass5 = DiscriminativeData.getDiscriminativeSet(trainingData, 5);
		int[][] batchClass6 = DiscriminativeData.getDiscriminativeSet(trainingData, 6);
		int[][] batchClass7 = DiscriminativeData.getDiscriminativeSet(trainingData, 7);
		int[][] batchClass8Big = DiscriminativeData.getDiscriminativeSet(trainingData, 8);
		int[][] batchClass8 = Arrays.copyOfRange(batchClass8Big, 0, batchClass8Big.length/3);
		int[][] batchClass9Big = DiscriminativeData.getDiscriminativeSet(trainingData, 9);
		int[][] batchClass9 = Arrays.copyOfRange(batchClass9Big, 0, batchClass9Big.length/2);


		// 2. Initialise RBM
		final int NUMBER_HIDDEN_UNITS = 12;
		final int NUMBER_VISIBLE_UNITS = LabelledData.SIZE;

		double learningRate = 0.01;
		int trainingEpochs = 1;
		int k = 1;

		RestrictedBoltzmannMachine rbm0 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm1 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm2 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm3 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm4 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm5 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm6 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm7 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm8 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);
		RestrictedBoltzmannMachine rbm9 = 
				new RestrictedBoltzmannMachine(NUMBER_VISIBLE_UNITS, NUMBER_HIDDEN_UNITS,
						learningRate, k , trainingEpochs);

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

		for(int i = 0; i < testData.length; i++) {

			int[] testHiddenLayer = new int[NUMBER_HIDDEN_UNITS];
			double energy0 = rbm0.freeEnergy(testData[i], testHiddenLayer);

			int[] testHiddenLayer1 = new int[NUMBER_HIDDEN_UNITS];
			double energy1 = rbm1.freeEnergy(testData[i], testHiddenLayer1);

			int[] testHiddenLayer2 = new int[NUMBER_HIDDEN_UNITS];
			double energy2 = rbm2.freeEnergy(testData[i], testHiddenLayer2);

			int[] testHiddenLayer3 = new int[NUMBER_HIDDEN_UNITS];
			double energy3 = rbm3.freeEnergy(testData[i], testHiddenLayer3);

			int[] testHiddenLayer4 = new int[NUMBER_HIDDEN_UNITS];
			double energy4 = rbm4.freeEnergy(testData[i], testHiddenLayer4);

			int[] testHiddenLayer5 = new int[NUMBER_HIDDEN_UNITS];
			double energy5 = rbm5.freeEnergy(testData[i], testHiddenLayer5);

			int[] testHiddenLayer6 = new int[NUMBER_HIDDEN_UNITS];
			double energy6 = rbm6.freeEnergy(testData[i], testHiddenLayer6);

			int[] testHiddenLayer7 = new int[NUMBER_HIDDEN_UNITS];
			double energy7 = rbm7.freeEnergy(testData[i], testHiddenLayer7);

			int[] testHiddenLayer8 = new int[NUMBER_HIDDEN_UNITS];
			double energy8 = rbm8.freeEnergy(testData[i], testHiddenLayer8);

			int[] testHiddenLayer9 = new int[NUMBER_HIDDEN_UNITS];
			double energy9 = rbm9.freeEnergy(testData[i], testHiddenLayer9);


			// get least energy and classify as the rbm class where the energy belongs
			int solutionPredicted = predict(energy0, energy1, energy2, energy3, energy4,
					energy5, energy6, energy7, energy8, energy9);

			if(solutionPredicted == testLabels[i]) {
				counter++;
			}
			//			else {
			//				System.out.println("predicted: " + solutionPredicted + ", real: " + testLabels[i]);
			//			}
		}

		double accuracyOnTestingSet = (double) counter / testingData.length;
		System.out.println("Testing set size: " + testingData.length);
		System.out.println("Classes predicted right: " + counter);
		System.out.println("Accuracy on the testing set: " + accuracyOnTestingSet * 100 + " %\n");
	}

	public static int predict(double energy0, double energy1, double energy2, double energy3, double energy4,
			double energy5, double energy6, double energy7,  double energy8, double energy9) {

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

		for(int i = 0; i < energyArray.length; i++) {
			if(min > energyArray[i]) {
				min = energyArray[i];
				solutionClass = i;
			}
		}	
		return solutionClass;
	}
}
