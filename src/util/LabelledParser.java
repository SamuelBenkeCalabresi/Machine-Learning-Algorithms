package util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import dataset.LabelledData;

public class LabelledParser {
	public static LabelledData[] parse(String fileDataSet) {
		/* dataSet is a list of DataSet (array of integers) to return */
		ArrayList<LabelledData> dataSet = new ArrayList<>();
		
		/* Start parsing */
		BufferedReader bufferReader = null;
		String line = "";
		
		try {
			bufferReader = new BufferedReader(new FileReader(fileDataSet));
			
			while((line = bufferReader.readLine()) != null) {
//				System.out.println("Raw CSV data: " + line);
				addDataSet(dataSet, line);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dataSet.toArray(new LabelledData[0]);
	}
	
	/* support function from CSV file to new element of ArrayList */
	private static void addDataSet(ArrayList<LabelledData> dataSet, String line) {
		/* data row from matrix */
		int [] data = new int[LabelledData.SIZE];
		/* solution integer between 0/9 as last element in data row */
		int solution = 0;
		/* choose where to split the string line, default as "," */
		String splitBy = ",";
//		int index = 0;

		if(line != null) {
			String [] splitData = line.split(splitBy);
			
			/* foreach number in splitData ie data row, add to ArrayList element */
			for(int i = 0; i < splitData.length; i++) {
				if(!(splitData[i] == null) || !(splitData[i].length() == 0)) {
//					listOfData.add(splitData[i].trim());
					// add to data if it is not the last element // i != splitData.length - 1
					if(i < LabelledData.SIZE) {
						data[i] = Integer.parseInt(splitData[i]);

					}
					// else add to solution
					else {
						solution = Integer.parseInt(splitData[i]);

					}	
				}
			}
//			System.out.println("data line: " + line);
//			System.out.println("solution: " + solution);
		}
		dataSet.add(new LabelledData(data, solution));
//		System.out.println("dataSet: " + dataSet.get(index).getSolution());
//		index ++;
	}
	
	public static void printMatrix(ArrayList<LabelledData> dataSet) {
		for(LabelledData dataElement : dataSet) {
			System.out.print(dataElement.getData().length + " ");
			System.out.println(dataElement.getSolution() + " ");
		}
	}
	
	
}
