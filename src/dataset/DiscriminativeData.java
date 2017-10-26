package dataset;

import java.util.ArrayList;

import util.BinaryParser;

/**
 * Divide training data in mini-batches to any different label
 */
public class DiscriminativeData {
	/* Define constants */
	public static final int NUMBER_OF_CLASSES = 10;

	public static int[][] getDiscriminativeSet(BinaryData[] trainingData, int setClass) {
		// about 260 inputs of any class
		// understand how to generalise the data
		int batchSize = 285;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			// Get the first 260 inputs as class chosen
			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 266) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 19) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}

		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}
	
	public static int[][] getUnlabelledDataAsInt(BinaryData[] trainingDataSet) {
		int[][] unlabelledDataSet = new int[trainingDataSet.length][BinaryData.SIZE];
		for (int i = 0; i < trainingDataSet.length; i++) {
			unlabelledDataSet[i] = trainingDataSet[i].getData();
		}
		return unlabelledDataSet;
	}

	/*
	 * count the number of training data of specific classes 
	 * count0: 269 count1: 290 count2: 280 count3: 275 count4: 293 count5: 285
	 * count6: 274 count7: 292 count8: 266 count9: 286
	 */
	public static int[][] getBatchClass0(BinaryData[] trainingData) {
		int setClass = 0;
		int batchSize = 289;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 269) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 20) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}

		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass1(BinaryData[] trainingData) {
		int setClass = 1;
		int batchSize = 310;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 290) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 20) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}

		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass2(BinaryData[] trainingData) {
		int setClass = 2;
		int batchSize = 295;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			// Get the first 260 inputs as class chosen
			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 280) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 15) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass3(BinaryData[] trainingData) {
		// about 260 inputs of any class
		// understand how to generalise the data
		int setClass = 3;
		int batchSize = 290;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 275) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 15) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass4(BinaryData[] trainingData) {
		int setClass = 4;
		int batchSize = 313;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			// Get the first 260 inputs as class chosen
			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 293) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 20) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}


	public static int[][] getBatchClass5(BinaryData[] trainingData) {
		int setClass = 5;
		int batchSize = 300;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			// Get the first 260 inputs as class chosen
			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 285) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 15) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass6(BinaryData[] trainingData) {
		int setClass = 6;
		int batchSize = 290;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 274) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 16) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}


	public static int[][] getBatchClass7(BinaryData[] trainingData) {
		int setClass = 7;
		int batchSize = 312;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 292) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 20) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass8(BinaryData[] trainingData) {
		int setClass = 8;
		int batchSize = 280;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 266) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 14) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}

	public static int[][] getBatchClass9(BinaryData[] trainingData) {
		// about 260 inputs of any class
		// understand how to generalise the data
		int setClass = 9;
		int batchSize = 300;
		int countDifferentElements = 0;
		int countClassElement = 0;
		BinaryData[] discriminativeTrainingData = new BinaryData[batchSize];
		int totalCount = 0;

		// Search for class chosen
		for (int i = 0; i < trainingData.length; i++) {

			if (totalCount == batchSize) {
				return getUnlabelledDataAsInt(discriminativeTrainingData);
			} else if ((int) trainingData[i].getSolution() == setClass && countClassElement < 286) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countClassElement++;
				totalCount++;
			} else if (countDifferentElements < 14) {
				discriminativeTrainingData[totalCount] = trainingData[i];

				countDifferentElements++;
				totalCount++;
			}
		}
		return getUnlabelledDataAsInt(discriminativeTrainingData);
	}
}
