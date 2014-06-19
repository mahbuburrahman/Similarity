package edu.umbc.wordSimilarity2.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.FileReader;


import edu.umbc.wordSimilarity2.CoOccurModelByArrays;


public class Transform {

	public CoOccurModelByArrays model;
    
    public static String dataPath;
    public static String dataPath1;


	public Transform(String originalModelName) {
		// TODO Auto-generated constructor stub
		model = new CoOccurModelByArrays(originalModelName, false);
	}

	public int SaveTranformedModel(String transformedModelName){
		
		try{
			FileWriter fileOfCoOccurModel = new FileWriter(transformedModelName + ".matrix");
			PrintWriter outOfCoOccurModel = new PrintWriter(new BufferedWriter(fileOfCoOccurModel, 4000000));
			
			outOfCoOccurModel.println(model.sizeOfVocabulary + " " + model.sizeOfVocabulary);
			
			for (int i = 0; i < model.sizeOfVocabulary; i++){
				for (int j = 0; j < model.sizeOfVocabulary; j++){
					outOfCoOccurModel.print(Math.log(model.wordMatrix[i][j] + 1));
					
					if (j != model.sizeOfVocabulary -1)
						outOfCoOccurModel.print(" ");
				}
				outOfCoOccurModel.println();
			}
			
			
			outOfCoOccurModel.close();
		
		} catch (Exception e){
			System.out.println(e.getMessage());
			return -1;
		}
		
		return 0;		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
        /* Read Path for model and data files */
        try{
            //InputStream input = new FileInputStream("../../../../../config.properties");
            BufferedReader br = new BufferedReader(new FileReader("../../../../config.txt"));
            dataPath = br.readLine();
            dataPath1 = br.readLine();
            //System.out.println("Yes"+dataPath);
        }
        catch (Exception e) {
            //System.out.println("No");
        }
        /* End */
        


		/*
		ModelUtility utility;
		
		for (int i=16; i<18; i++){
			
			utility = new ModelUtility(dataPath+"/model/Gutenberg2010sfd/Gutenberg2010AllW" + (i+1), dataPath+"/model/Gutenberg2010sfd/Gutenberg2010AllW" + i);
			utility.SaveDifferenceAsModel(dataPath+"/model/Gutenberg2010sfd/distribution/Gutenberg2010AllAt" + i);
			utility = null;
			System.gc();
			System.out.println(i + " is done.");
		}
		*/

		//Transform utility = new Transform(dataPath+"/model/webbase2012sfd/webbase2012AllW2");
		//utility.SaveTranformedModel(dataPath+"/model/SVD/webbase2012AllW2");

		//Transform utility = new Transform(dataPath+"/model/combine/gutn_ukwac_AllW5");
		//utility.SaveTranformedModel(dataPath+"/model/SVD/gutn_ukwac_AllW5");

		//Transform utility = new Transform(dataPath+"/model/combine/gutn_ukwac_part_AllW2");
		//utility.SaveTranformedModel(dataPath+"/model/SVD/gutn_ukwac_part_AllW2");

		Transform utility = new Transform(dataPath+"/model/Gutenberg2010sfd/Gutenberg2010AllW3");
		utility.SaveTranformedModel(dataPath+"/model/SVD/Gutenberg2010AllW3");

		//Transform utility = new Transform(dataPath+"/model/Wikipedia2006sfd/Wikipedia2006AllW5");
		//utility.SaveTranformedModel(dataPath+"/model/SVD/Wikipedia2006AllW5");

		//Transform utility = new Transform(dataPath+"/model/combine/Gutenberg2010_Wikipedia2006_AllW5");
		//utility.SaveTranformedModel(dataPath+"/model/SVD/Guten2010_Wiki2006_AllW5");

		System.out.println("Congratulation! Task Finished.");

		//System.out.println("Max value is " + utility.model1.getMaxValue());
		//System.out.println("Min value is " + utility.model1.getMinValue());

	}

}
