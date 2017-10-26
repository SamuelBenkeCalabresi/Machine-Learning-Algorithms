package util;

import dataset.BinaryData;
import dataset.LabelledData;

public class Utils {
	/* The following methods are just helper methods
	 * that could be inside the Main test class */
	public static double[][] getDataSetAsDouble(LabelledData[]  dataSet) {	
		double[][] dataAsDouble = new double[dataSet.length][LabelledData.SIZE];

		for(int i = 0; i < dataAsDouble.length; i++) {
			dataAsDouble[i] = dataSet[i].getDataAsDouble();
		}
		return dataAsDouble;
	}
	
	/* Get data labels Y as binary solution integers */
	public static int[][] getDataLabelsAsInt(LabelledData[] dataSet) {
		int[][] trainingLabels = new int[dataSet.length][LabelledData.SOLUTION_LENGTH];
		
		for(int i = 0; i < trainingLabels.length; i++) {
			trainingLabels[i] = dataSet[i].returnBinarySolutionsAsInt(dataSet[i].getSolution());
		}
		return trainingLabels;
	}
	
	/* Get data labels Y as binary solution integers */
	public static int[] getDataLabelsAsInt(BinaryData[] dataSet) {
		int[] trainingLabels = new int[dataSet.length];
		
		for(int i = 0; i < trainingLabels.length; i++) {
			trainingLabels[i] = (int) dataSet[i].getSolution();
		}
		return trainingLabels;
	}
	
	public static double[][] getLabelledData(BinaryData [] binaryDataSet) {
		double [][] labelledDataSet = new double [binaryDataSet.length][BinaryData.SIZE_LABELLED_DATA];
		for(int i = 0; i < binaryDataSet.length; i++) {
			labelledDataSet[i] = binaryDataSet[i].getLabelledData();
	    }
		return labelledDataSet;
	}
	

}
