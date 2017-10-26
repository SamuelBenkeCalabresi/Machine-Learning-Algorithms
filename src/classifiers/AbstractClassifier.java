package classifiers;

import dataset.LabelledData;

/**
 * AbstractClassifier is an abstract class to place in common the 
 * features of the classifier for data sets given.
 * <br>
 * The methods are most common ones used in machine learning frameworks
 * i.e. to train is used fit method (to fit the given data)
 * and to predict the label/class of a new test is used predict method.
 * <br> 
 * This abstract class is used only for non-parametric learning algorithms.
 * <br>
 * This simple classifier has only to implement 
 * a twofold (or any fold) test as testing design .
 * */
public abstract class AbstractClassifier {
	
	protected LabelledData[] trainingDataSet;
	protected LabelledData[] testingDataSet;
	
	/* This should define HyperParameters, however, 
	 * for non-parametric learning algorithms 
	 * this is not necessary therefore the construct
	 * method is used to get the datasets.
	 * */
	public AbstractClassifier(LabelledData[] testingDataSet, LabelledData[] trainingDataSet) {
		this.testingDataSet = testingDataSet;
		this.trainingDataSet = trainingDataSet;
	}
	
	// Fit the training data that the classifier has to learn from
	public abstract void fit();
	
	// Predict class labels for samples in input X
	public abstract void predict();
}
