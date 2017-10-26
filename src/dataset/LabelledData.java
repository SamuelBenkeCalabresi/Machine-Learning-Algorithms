package dataset;
import util.LabelledParser;

public class LabelledData extends AbstractDataset {
	/** 
	 * This class represents the data to train and test any of 
	 * the machine learning algorithms available.
	 * <br>
	 * The data is from "Optical Recognition of Handwritten Digits Data Set" and described as follows:
	 * 32x32 bitmaps are divided into non-overlapping blocks of 4x4 and the number of on pixels 
	 * are counted in each block. 
	 * This generates an input matrix of 8x8 where each element is an integer in the range 0..16. 
	 * This reduces dimensionality and gives invariance to small distortions.
	 * <br> 
	 * This data is already normalised, ready to be classified with algorithms
	 * e.g. Euclidean Distance
	 * */
	
	/* The possible solution the data is classified in */
	private final int solution;
	
	/* Size of the row */
	public static final int SIZE = 64;
	
	/**/
	public static final int SOLUTION_MAX_VALUE = 9;
	/**/
	public static final int SOLUTION_LENGTH = 10;

	public LabelledData(int [] data, int solution) {
		super(data);
		this.solution = solution;
	}

	public int[] getData() {
		return data;
	}

	public int getSolution() {
		return solution;
	}
	
	/* To convert the data from int (standard) to double */
	public double[] getDataAsDouble() {
		double[] dataDouble = new double[SIZE];
		for (int i = 0; i < data.length; ++i) {
			dataDouble[i] = data[i];
		}
		return dataDouble;
	}
	
	/* To convert the solution in an array of binary values (double)
	 * equal to the number of classes/labels
	 * like one-hot encoding, giving 1 to the right solution
	 * and zero to all the other ones */
	public double[] returnBinarySolutions(int solution) {
		double[] binarySolution = new double[SOLUTION_LENGTH];
				
		for(int i = 0; i < binarySolution.length; i++) {
			/* if the indexed element is == solution number, set that index element to 1, all zeros the others */
			if(i != solution) {
				binarySolution[i] = 0;
			}
			else {
				binarySolution[i] = 1;
			}
		}
		return binarySolution;
	}
	
	/* To convert the solution in an array of binary values (int)
	 * equal to the number of classes/labels
	 * like one-hot encoding, giving 1 to the right solution
	 * and zero to all the other ones */
	public int[] returnBinarySolutionsAsInt(int solution) {
		int[] binarySolution = new int[SOLUTION_LENGTH];
				
		for(int i = 0; i < binarySolution.length; i++) {
			/* if the indexed element is == solution number, set that index element to 1, all zeros the others */
			if(i != solution) {
				binarySolution[i] = 0;
			}
			else {
				binarySolution[i] = 1;
			}
		}
		return binarySolution;
	}
	
	@Override
	public String toString() {
		
		String output = " ";
		for(int o : data) {
			output += o + ",";
		}
		output += "\n";
		output += "\tsolution: " + solution + "\n";
		return output;
	}
	
}
