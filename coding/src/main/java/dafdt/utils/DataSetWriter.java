package dafdt.utils;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.NominalAttributeInfoExposer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSetWriter {



	private final String line = System.lineSeparator();
	public void csvWriter(String filename, double[][] data) {
		try {
			FileWriter writer = new FileWriter(new File(filename + "_generated" + ".csv"));
			writeData(writer, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public String arffWriter(String filename, double[][] data, String[] attributeNames) {

		double totalInstances = 0;
		String filepathresult = "";
		File arffFile = null;

		try {
			FileWriter writer = new FileWriter(new File(filename + ".arff"));			
			writer.write("@relation " + " generated" + line);

			for(int i = 0; i < attributeNames.length; i++) {
				writer.write("@attribute " + attributeNames[i] + " numeric" + line);
			}

			writer.write("@attribute class {0,1}" + line );
			writer.write(line);
			writer.write("@data"+ line);

			writeData(writer, data);
			writer.close();

			arffFile = new File(filename + ".arff");
			filepathresult = arffFile.getPath();
//			streamer.experiment.matlab.plotDS(Experiment.readDataSetToArray(arffFile.getPath()), filename + "_generated_plot"+streamer.getStreamID()+".png", streamer.getStreamTitle());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepathresult;
	}
	public String arffWriter(String filename, ArrayList<String[]> data, ArrayList<Attribute> metadatalist) {

		double totalInstances = 0;
		String filepathresult = "";
		File arffFile = null;

		try {
			FileWriter writer = new FileWriter(new File(filename + "_generated" + ".arff"));			
			writer.write("@relation last_generated" + line);
			
			for(Attribute att : metadatalist) {
				if(att.isNumeric()) {
					writer.write("@attribute " + att.name() + " numeric" + line);
				}
				else {
					ArrayList<Object> values = NominalAttributeInfoExposer.get_m_Values(att.getNominalAttributeInfo());
					StringBuffer nominalValues = new StringBuffer();
					for(Object v :  values){
						nominalValues.append(String.valueOf(v));
						nominalValues.append(",");
					}
					String nominallist = nominalValues.replace(nominalValues.lastIndexOf(","),nominalValues.lastIndexOf(",")+1, "").toString();
					writer.write("@attribute " + att.name() + " {"+ nominallist  + "}"+ line);
				}
			}

			
			writer.write(line);
			writer.write("@data"+ line);

			writeData(writer, data);

			arffFile = new File(filename + "_generated" + ".arff");
			filepathresult = arffFile.getPath();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepathresult;
	}

	public String arffWriter(String filename, ArrayList<String[]> data, String[] attributeNames) {

		double totalInstances = 0;
		String filepathresult = "";
		File arffFile = null;

		try {
			FileWriter writer = new FileWriter(new File(filename + "_generated" + ".arff"));			
			writer.write("@relation last_generated" + line);

			for(int i = 0; i < attributeNames.length; i++) {
				if(!attributeNames[i].equals("day")) {
					writer.write("@attribute " + attributeNames[i] + " numeric" + line);
					
				}
				else {
					writer.write("@attribute " + attributeNames[i] + " {1,2,3,4,5,6,7}" + line);
				}
			}

			writer.write("@attribute class {DOWN,UP}" + line );
			writer.write(line);
			writer.write("@data"+ line);

			writeData(writer, data);

			arffFile = new File(filename + "_generated" + ".arff");
			filepathresult = arffFile.getPath();
			//streamer.experiment.matlab.plotDS(Experiment.readDataSetToArray(arffFile.getPath()), filename + "_generated_plot"+streamer.getStreamID()+".png", streamer.getStreamTitle());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filepathresult;
	}

	private void writeData(FileWriter writer, double[][] data) throws IOException {

		if(data == null)return;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				//writer.write(data[i][j]);

				//writer.write(Double.toString(data[i][j]));

				if(j<(data[i].length - 1)) {

					if(new Double(data[i][j]).isNaN()) {
						writer.write("?");
					}else {
						writer.write(Double.toString(data[i][j]));
					}					
					writer.write(",");
				}
				else {					
					writer.write(Long.toString(Math.round(data[i][j])));
				}
			}
			writer.write(line);				
		}		
		writer.close();
	}
	private void writeData(FileWriter writer, ArrayList<String[]> data) throws IOException {
		
		for(String[] instance: data) {
			for (int i = 0; i < instance.length; i++) {
				if(i<(instance.length)-1) {
					writer.write(instance[i]);
					writer.write(",");
				}
				else {
					writer.write(Long.toString(Math.round(Double.parseDouble(instance[i]))));
				}
				
			}
			writer.write(line);
		}

		writer.close();
	}

	public static double[][] readDataSetToArray(Instances instances) {
		Instances dataset = null;
		double[][] data = null;
		dataset = instances;
		data = new double[dataset.size()][dataset.numAttributes()];

		for (Instance instance : dataset) {
			data[dataset.indexOf(instance)] = instance.toDoubleArray();
		}

		return data;
	}
}
