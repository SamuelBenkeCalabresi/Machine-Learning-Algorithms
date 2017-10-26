package classifiers.generative_graphical_models;

import java.util.Random;

/**
 * Generative Graphical Model made in an advanced way to: get all
 * hyperParameters in construct get energy function get Gibbs sampling function
 * be able to use it without the help of a SoftmaxLayer
 */
public class RestrictedBoltzmannMachine {
	/* HyperParameters */
	/* Neural network architecture attributes */
	private int[] visibleLayer;
	private int[] hiddenLayer;
	private double[][] weights;
	/* Bias as vectors of visibleLayer and hiddenLayer */
	private double[] biasOfVisibleLayer;
	private double[] biasOfHiddenLayer;
	/* Numerical Meta-Parameters */
	private double learningRate;
	private int epochs;
	private int gibbsSteps;
	/* Attributes can be useful to have */
	private double energy;
	private Random randomUniformlyDistributed = new Random();
	private int datasetSize;

	/* Construct defines the Meta-Parameters */
	public RestrictedBoltzmannMachine(int numberOfVisibleUnits, int numberOfHiddenUnits, double learningRate, int k,
			int epochs) {

		this.learningRate = learningRate;
		this.epochs = epochs;
		this.gibbsSteps = k;

		visibleLayer = new int[numberOfVisibleUnits];
		hiddenLayer = new int[numberOfHiddenUnits];
		weights = initWeights();

		biasOfVisibleLayer = new double[visibleLayer.length];
		biasOfHiddenLayer = new double[hiddenLayer.length];
	}

	/*
	 * Free energy as defined in
	 * http://www.csri.utoronto.ca/~hinton/absps/guideTR.pdf
	 */
	public double freeEnergy(int[] vector) {
		double sumOverVisibleUnits = 0;
		double sumOverHiddenUnits = 0;
		double sumOverWeights = 0;

		for (int indexVisibleL = 0; indexVisibleL < vector.length; indexVisibleL++) {
			sumOverVisibleUnits += vector[indexVisibleL] * biasOfVisibleLayer[indexVisibleL];

			for (int indexHiddenL = 0; indexHiddenL < hiddenLayer.length; indexHiddenL++) {
				sumOverWeights += vector[indexVisibleL] * weights[indexHiddenL][indexVisibleL];
				sumOverHiddenUnits += Math.log(1 / +Math.exp(sumOverWeights));
			}
		}
		energy = -sumOverVisibleUnits - sumOverHiddenUnits;
		return energy;
	}

	/*
	 * Free energy as standard in Hopfield networks, as defined in
	 * http://www.csri.utoronto.ca/~hinton/absps/guideTR.pdf
	 */
	public double freeEnergy(int[] visibleLayer, int[] hiddenLayer) {
		double sumOverVisibleUnits = 0;
		double sumOverHiddenUnits = 0;
		double sumOverWeights = 0;

		for (int indexVisibleL = 0; indexVisibleL < visibleLayer.length; indexVisibleL++) {
			sumOverVisibleUnits += visibleLayer[indexVisibleL] * biasOfVisibleLayer[indexVisibleL];

			for (int indexHiddenL = 0; indexHiddenL < hiddenLayer.length; indexHiddenL++) {
				sumOverHiddenUnits += hiddenLayer[indexHiddenL] * biasOfHiddenLayer[indexHiddenL];
				sumOverWeights += visibleLayer[indexVisibleL] * hiddenLayer[indexHiddenL]
						* weights[indexHiddenL][indexVisibleL];
			}
		}
		energy = -sumOverVisibleUnits - sumOverHiddenUnits - sumOverWeights;
		return energy;
	}

	/*
	 * Fit the given training dataset. In this case is unsupervised learning and
	 * the RBM work as feature detector/generative model
	 */
	/* Training data of binomial units */
	public void fit(int[][] trainingData) {
		datasetSize = trainingData.length;

		for (int epoch = 0; epoch < epochs; epoch++) {
			for (int indexDataset = 0; indexDataset < datasetSize; indexDataset++) {
				contrastiveDivergence(trainingData[indexDataset]);
			}
		}
	}

	private void contrastiveDivergence(int[] trainingData) {

		// phases of contrastive divergence
		// as described from Montreal Lisa lab
		double[] positiveHiddenActivities = new double[hiddenLayer.length];
		int[] positiveHiddenSample = new int[hiddenLayer.length];

		double[] negativeVisibleActivities = new double[visibleLayer.length];
		int[] negativeVisibleSample = new int[visibleLayer.length];

		double[] negativeHiddenActivities = new double[hiddenLayer.length];
		int[] negativeHiddenSamples = new int[hiddenLayer.length];

		samplingHiddenLayer(trainingData, positiveHiddenActivities, positiveHiddenSample);

		// Perform number of GibbsSampling equal to GibbsSteps
		for (int gibbsStepIndex = 0; gibbsStepIndex < gibbsSteps; gibbsStepIndex++) {
			gibbsSampling(positiveHiddenSample, negativeVisibleActivities, negativeVisibleSample,
					negativeHiddenActivities, negativeHiddenSamples);
		}

		// Now update the weights
		updateWeights(positiveHiddenActivities, trainingData, negativeHiddenActivities, negativeVisibleSample,
				positiveHiddenSample);
	}

	/* Update the weights following the Montreal Lisa lab instructions */
	private void updateWeights(double[] positiveHiddenActivities, int[] trainingData, double[] negativeHiddenActivities,
			int[] negativeVisibleSample, int[] positiveHiddenSample) {

		for (int indexHiddenL = 0; indexHiddenL < hiddenLayer.length; indexHiddenL++) {
			for (int indexVisibleL = 0; indexVisibleL < visibleLayer.length; indexVisibleL++) {

				weights[indexHiddenL][indexVisibleL] += learningRate
						* ((positiveHiddenActivities[indexHiddenL] * trainingData[indexVisibleL])
								- (negativeHiddenActivities[indexHiddenL] * negativeVisibleSample[indexVisibleL]))
						/ datasetSize;

				biasOfVisibleLayer[indexVisibleL] += learningRate
						* (trainingData[indexVisibleL] - negativeVisibleSample[indexVisibleL]) / datasetSize;

			}
			biasOfHiddenLayer[indexHiddenL] += learningRate
					* (positiveHiddenSample[indexHiddenL] * negativeHiddenActivities[indexHiddenL]) / datasetSize;
		}
	}

	/*
	 * One call to gibbsSampling make: one sample from hiddenLayer to the
	 * visible one and then vice versa
	 */
	private void gibbsSampling(int[] positiveHiddenSample, double[] negativeVisibleActivities,
			int[] negativeVisibleSample, double[] negativeHiddenActivities, int[] negativeHiddenSamples) {

		samplingVisibleLayer(positiveHiddenSample, negativeVisibleActivities, negativeVisibleSample);
		samplingHiddenLayer(negativeVisibleSample, negativeHiddenActivities, negativeHiddenSamples);
	}

	/*
	 * samplingVisibleLayer gets new binomial states of the visible layer: 1.
	 * multiplying correspondent weights, training data, biases 2. making
	 * sigmoid of such data 3. firing the states with a random threshold 4.
	 * returning new states (visibleBinomialSample)
	 */
	private void samplingVisibleLayer(int[] trainingData, double[] visibleActivity, int[] visibleBinomialSample) {

		for (int indexVisibleL = 0; indexVisibleL < visibleLayer.length; indexVisibleL++) {
			visibleActivity[indexVisibleL] = 0;
			for (int indexHiddenL = 0; indexHiddenL < hiddenLayer.length; indexHiddenL++) {
				visibleActivity[indexVisibleL] += weights[indexHiddenL][indexVisibleL] * trainingData[indexHiddenL];
			}
			visibleActivity[indexVisibleL] += biasOfVisibleLayer[indexVisibleL];
			sigmoid(visibleActivity[indexVisibleL]);

			visibleBinomialSample[indexVisibleL] = fireBinomialState(visibleActivity[indexVisibleL]);
		}
	}

	/*
	 * samplingHiddenLayer gets new binomial states of the hidden layer: 1.
	 * multiplying correspondent weights, training data, biases 2. making
	 * sigmoid of such data 3. firing the states with a random threshold 4.
	 * returning new states (hiddenBinomialSample)
	 */
	private void samplingHiddenLayer(int[] trainingData, double[] hiddenActivity, int[] hiddenBinomialSample) {

		for (int indexHiddenL = 0; indexHiddenL < hiddenLayer.length; indexHiddenL++) {
			hiddenActivity[indexHiddenL] = 0;
			for (int indexVisibleL = 0; indexVisibleL < visibleLayer.length; indexVisibleL++) {
				hiddenActivity[indexHiddenL] += weights[indexHiddenL][indexVisibleL] * trainingData[indexVisibleL];
			}
			hiddenActivity[indexHiddenL] += biasOfHiddenLayer[indexHiddenL];
			sigmoid(hiddenActivity[indexHiddenL]);

			hiddenBinomialSample[indexHiddenL] = fireBinomialState(hiddenActivity[indexHiddenL]);
		}
	}

	/*
	 * It returns 1 if activity is greater than a random number between 0/1 it
	 * returns 0 otherwise.
	 */
	private int fireBinomialState(double activity) {
		double random;
		random = randomUniformlyDistributed.nextDouble();

		if (activity >= random) {
			return 1;
		} else {
			return 0;
		}
	}

	/* Standard sigmoid function */
	private double sigmoid(double input) {
		return 1.0 / (1 + Math.exp(-input));
	}

	/*
	 * It does not initialise the weights with any number (all to 0) even though
	 * this can be done testing empirically on dataset to improve accuracy.
	 */
	private double[][] initWeights() {
		double[][] weights = new double[hiddenLayer.length][visibleLayer.length];
		return weights;
	}
}
