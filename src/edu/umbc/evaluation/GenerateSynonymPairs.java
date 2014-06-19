package edu.umbc.evaluation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.umbc.wordSimilarity2.CoOccurModelByArrays;

public class GenerateSynonymPairs {

	public BufferedReader synonymReader;
	String POS;
	public CoOccurModelByArrays model;
	private PrintWriter synonymPairsOut;
    
    public static String dataPath;
    public static String dataPath1;


	public GenerateSynonymPairs(String InfileName, String pos, String modelname) {
        
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

        
		// TODO Auto-generated constructor stub
		try{
			File synonymCollection = new File(InfileName);
			synonymReader = new BufferedReader(new FileReader(synonymCollection));
			POS = pos;
			
			synonymPairsOut = new PrintWriter(new FileWriter(dataPath+"/evaluation/synonymPairs_all_" + POS + ".txt"));
			model = new CoOccurModelByArrays(modelname, false);
			
		} catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
			System.exit(-1);
		}

	}
	
	
	public void process() throws IOException{
		
		String textLine;
		
		while ((textLine = synonymReader.readLine()) != null){

			if (textLine.trim().equals(""))
				continue;
			
			TestEntryWrapper entry = new TestEntryWrapper(textLine);
			
			// if (entry.numberOfSenses > 1) continue;

			for (String synonym: entry.allSynonyms){
				
				if (model.getCoOccurrence(entry.targetWord + "_" + POS, synonym + "_" + POS) > 200){
					
					synonymPairsOut.println(entry.targetWord + "_" + POS + " " + synonym + "_" + POS);
				}
			}

			/*
			if (model.getCoOccurrence(entry.targetWord + "_" + POS, entry.firstSynonym + "_" + POS) > 200){
				
				synonymPairsOut.println(entry.targetWord + "_" + POS + " " + entry.firstSynonym + "_" + POS);
			}
			*/

		
		}
		
		synonymReader.close();
		synonymPairsOut.close();

	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
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

        
		GenerateSynonymPairs run = new GenerateSynonymPairs(dataPath+"/evaluation/advSynonyms700.txt", "RB", 
				dataPath+"/model/Gutenberg2010sfd/Gutenberg2010AllW41");
		
		run.process();
		
		System.out.println("Work completed!");
	}

}
