package test;

import java.util.ArrayList;

 /** make confusion matrix algorithm
 0. Find the True positive
 predicted class = actual class
 1. Find the False positive
 that is count how many of classes predicted as solutionPredicted
 like solutionPredicted = 1 but instead is 0
 make it for every class
 2. Find the False negative
 that is how many real of that class (like 1) were predicted as 0
 it means when there is an error counts 
 like solutionPredicted = 0 but instead is 1
 3. Find the True negative
 just all the rest of the dataset */

public class ConfusionMatrix{
	
	/* Class to search for */
	private final int classLabel;
	/* Data to store confusion matrix conditions */
	private int truePositive;
	private int trueNegative;
	private int falsePositive;
	private int falseNegative;
	// However this are just number I want to know with which other class is confused
	// In this efficient way it can be possible to know with which numbers the prediction 
	// has gotten confused with
	private ArrayList<Integer> falsePositives;
	private ArrayList<Integer> falseNegatives;
	
	public ConfusionMatrix(int label) {
		this.classLabel = label;
		this.falsePositives = new ArrayList<>();
		this.falseNegatives = new ArrayList<>();
	}
	
	/* Easy algorithm to update condition of given class */
	public void updateClassConditions(int predictedClass, int actualClass) {
		/* if it is not the confusion matrix for this class */
		if(predictedClass != classLabel && actualClass != classLabel) {
			trueNegative++;
			return;
		}
		// In this case I know that are the same classes (like 1 = 1)
		if(predictedClass == actualClass) {
			truePositive++;
		}
		if(predictedClass != actualClass) {
			// 1. Find the False positive
			// like predictedClass = 1 but actualClass = 0
			if(predictedClass == classLabel) {
				// find the confusion with which class happened
				falsePositive++;
				falsePositives.add(actualClass);
				// 2. Find the False negative
				// like predictedClass = 0 but actualClass = 1
			} else if(actualClass == classLabel) {
				// find the confusion with which class happened
				falseNegative++;
				falseNegatives.add(actualClass);
			}
		}
	}
	
	public void printConfusionMatrix() {
		System.out.println("\tConfusion Matrix of class: " + classLabel);
		System.out.println("_____________________________________________\n");
		System.out.print("true positives: " + truePositive + "\t");
		System.out.println("false negatives: " + falsePositive);
		
		System.out.print("false positives: "+ falseNegative + "\t");
		System.out.println("true negatives: " + trueNegative);
		System.out.println("______________________________________________\n");

	}
	
	
	
}
