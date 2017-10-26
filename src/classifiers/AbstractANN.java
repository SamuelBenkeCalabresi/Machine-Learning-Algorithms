package classifiers;

import dataset.LabelledData;

/**
 * 
 * AbstractANN is an abstract class for neural network models.
 * <br>
 * It contains the most important and common hyper-parameters in 
 * artificial neural networks architectures. 
 * <br>
 * 
 */
public abstract class AbstractANN {
	
	/* Define HyperParameters */
	/* Define constants */
	/* number of units in visible layer, as default MNIST is 28 x 28 = 784 */
	protected final int SIZE_INPUT_LAYER;
	
	/* number of output units, as default for discrimination problem to classify numbers = 10 */
	protected final int SIZE_OUTPUT_LAYER;

	/* Weights matrix of synapses between neurons */
	protected double[][] weights;
	/* Bias vector */
	protected double[] bias;
	/* Learning rate */
	protected double learningRate;
	/* Number of epochs */ 
	protected int epochs;
	/* MiniBatch size */
	protected int miniBatch;
	
	/* Training data and testing data. 
	 * They are not hyper-parameters.
	 * They are not necessary to the model */
	protected LabelledData[] trainingDataSet;
	protected LabelledData[] testingDataSet;
	
	/* Construct has to initialise the HyperParameters */
	public AbstractANN(int sizeInputLayer, int sizeOutputLayer, double learningRate, int epochs) {
		this.SIZE_INPUT_LAYER = sizeInputLayer;
		this.SIZE_OUTPUT_LAYER = sizeOutputLayer;
		this.learningRate = learningRate;
		this.epochs = epochs;
	}
	
	/* Standard fit method uses Design Matrix and Target/Label data structures */
	public void fit(double[][] trainingData, int[][] trainingLabels) {
	}	
	
	/* Standard predict method predicts Target/Label new test input */
	public abstract int predict(double[] testingData);
	
	/* Initialise the weights of the network
	 * depending from machine learning architecture */
	public abstract void initLayers();
	
}
