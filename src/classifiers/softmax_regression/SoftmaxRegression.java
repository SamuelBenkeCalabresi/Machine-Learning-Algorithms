package classifiers.softmax_regression;

import classifiers.AbstractANN;
import dataset.LabelledData;
import util.Utils;
/** 
 * The SoftmaxRegression class implements 
 * the Softmax model. 
 * <br>
 * It is a supervised probabilistic model that returns with 
 * predict function, a vector of any class label
 * giving to each label a probability that sum to 1
 * <br>
 * Code it is all commented in the methods */
public class SoftmaxRegression extends AbstractANN {

	/* Hard-coded size of dataset
	 * it is not necessary for the algorithm. */
	int datasetSize = 2810;
	/* The constructor has to */
	/* Define the HyperParameters of SoftmaxRegression network */
	public SoftmaxRegression(int sizeInputLayer, int sizeOutputLayer, double learningRate, int epochs) {
		super(sizeInputLayer, sizeOutputLayer, learningRate, epochs);
		
		weights = new double[SIZE_OUTPUT_LAYER][SIZE_INPUT_LAYER];
		bias =  new double [SIZE_OUTPUT_LAYER];
	}
	
	/**
	 * 
	 * @param testingData 
	 *            The testing data set type of double because 
	 */
	@Override
	public int predict(double[] testingData) {
		double[] predictedLabel = new double[testingData.length];
		
		for(int i = 0; i < SIZE_OUTPUT_LAYER; i++) {
			predictedLabel[i] = 0;
			
			for(int j = 0; j < SIZE_INPUT_LAYER; j++) {
				// Here we're doing the stuff with the weights learned in the train
				predictedLabel[i] += weights[i][j] * testingData[j];
			}
			// Here we're doing the stuff with the bias learned in the train
			predictedLabel[i] += bias[i]; 
		}
		softmax(predictedLabel);
		int solution = argmax(predictedLabel);
		return solution;	
	}
	
	/**
	 * As standard: get X, Y training data
	 * 
	 * @param trainingData 
	 *     
	 * @param trainingLabels        
	 */
	@Override
	public void fit(double[][] trainingData, int[][] trainingLabels) {

 		for(int epoch = 0; epoch < epochs; epoch ++) {
			for(int i = 0; i < trainingData.length; i++) {
				train(trainingData[i], trainingLabels[i]);
			}
		}
	}

	/**
	 * The training method get only
	 * one input vector at the time from the fit method
	 * (the only way to train the classifier)
	 * 
	 * @param trainingData 
	 * 
	 * @param trainingLabel
	 * 
	 * */
	/* training SoftmaxRegression as defined in:
	 * http://ufldl.stanford.edu/tutorial/supervised/SoftmaxRegression/ */
	private double[] train(double[] trainingData, int[] trainingLabel) {
		
		// softmax probabilities
		double[] softmaxProbabilities = new double[SIZE_OUTPUT_LAYER];
		
		// initialise softmaxProbabilities
		for(int i = 0; i < SIZE_OUTPUT_LAYER; i++) {
			softmaxProbabilities[i] = 0;
			
			// for any unit in the input vector
			for(int j = 0; j < SIZE_INPUT_LAYER; j++) {
				// We define softmaxProbabilities[i] =  multiplication of weight and trainingData value
				softmaxProbabilities[i] += weights[i][j] * trainingData[j];
			}
			// We add the bias, as default = 0
			softmaxProbabilities[i] += bias[i];
		}
		// We then call the softmax activation function;
		softmax(softmaxProbabilities);
		
		// We then perform the weights update
		return updateWeights(trainingData, trainingLabel, softmaxProbabilities);
	}
	
	/**
	 *  updateWeights function concretely
	 *  updates the weights of the network:
	 *  1. multiplying delta for training data 
	 *  2. updating the bias vector
	 *  3. returning delta inside the train function */
	private double[] updateWeights(double[] trainingData, int[] trainingLabel, double[] softmaxProbabilities) {
		// Delta of target Y -> Delta of difference between predicted target and real target
		double[] delta = new double[SIZE_OUTPUT_LAYER];
		
		// Update the weights!
		for(int indexOutput = 0; indexOutput < SIZE_OUTPUT_LAYER; indexOutput++) {
			// very important step to understand: 
			// we define delta as the difference between the real label and the softmax probability
			delta[indexOutput] = trainingLabel[indexOutput] - softmaxProbabilities[indexOutput];
			
			for(int indexInput = 0; indexInput < SIZE_INPUT_LAYER; indexInput++) {
				weights[indexOutput][indexInput] += learningRate * delta[indexOutput] * trainingData[indexInput] / datasetSize;
			}
			
			// Update the bias unit!
			bias[indexOutput] = learningRate * (delta[indexOutput] / datasetSize);
		}	
		return delta;
	}
	
	/* activation function applied to any vector */
	private void softmax(double[] outputProbabilities) {
		double sum = 0;
        double max = 0;
        
        // get the max in inputX
        for (int indexOutput = 0; indexOutput < SIZE_OUTPUT_LAYER ; indexOutput++) {
            if (max < outputProbabilities[indexOutput]) {
                max = outputProbabilities[indexOutput];  
            }
        }

		/* For each class/label in the classification task */
		for (int indexOutput = 0; indexOutput < SIZE_OUTPUT_LAYER; indexOutput++) {
			// I should multiply this with weights
			outputProbabilities[indexOutput] = Math.exp(outputProbabilities[indexOutput] - max);
			// This should be the divisor in softmax function
            sum += outputProbabilities[indexOutput];
        }
		
		for (int indexOutput = 0; indexOutput < SIZE_OUTPUT_LAYER; indexOutput++) {
			outputProbabilities[indexOutput] /= sum;
	    }		
	}
	
	/* function to one-hot encode the probabilities from softmax */
	private int argmax(double[] output) {
		int classOfMaximumProbability = 0;
		double max = 0;
		
		for(int indexOutput = 0; indexOutput < output.length; indexOutput++) {
			if(max < output[indexOutput]) {
				max = output[indexOutput];
				classOfMaximumProbability = indexOutput;
			}
		}	
		return classOfMaximumProbability;
	}
	
	@Override
	public void initLayers() {
		// initialise the weights as all zeros
		for(int indexRow = 0; indexRow < weights.length; indexRow++) {
			for(int indexCol = 0;  indexCol < weights[0].length; indexCol++) {
				weights[indexRow][indexCol] = 0;
			}
		}
		// initialise bias vector as all zeros
		for(int indexBias = 0; indexBias < bias.length; indexBias++) {
			bias[indexBias] = 0;
		}	
	}
	
	/**
	 * @deprecated
	 * I could use the LabelledData[] to retrieve data and labels but it's not the standard
	 * and it is more generalisable using as input training data and training labels
	 */
	public void fit(LabelledData[] trainingDataSet) {
		this.trainingDataSet = trainingDataSet;
		
		// I train the SoftmaxRegression classifier with 2 matrices of training data and and correspondent class labels
		double[][] trainingData = new double[trainingDataSet.length][LabelledData.SIZE];
		int[][] trainingLabels = new int[trainingDataSet.length][LabelledData.SOLUTION_LENGTH];
		
		trainingData = Utils.getDataSetAsDouble(trainingDataSet);
		trainingLabels = Utils.getDataLabelsAsInt(trainingDataSet);
		
		// I can add the epochs and matrices in the train or leave them in fit
 		for(int epoch = 0; epoch < epochs; epoch ++) {
			for(int indexTrainData = 0; indexTrainData < trainingDataSet.length; indexTrainData++) {
				train(trainingData[indexTrainData], trainingLabels[indexTrainData]);
			}
		}
	}



	
}
