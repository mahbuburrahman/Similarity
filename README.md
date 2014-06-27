Similarity
==========
The Similarity project is used for building the co-occurrence model.Necessary setting is given below.

Setting Classpath:
	Location of the source code in your computer

Example:
	export CLASSPATH=$CLASSPATH:/Users/Mahbub/Desktop/GitHub/Similarity/src/ 


Configuring the paths of model and corpus directory: 
	Edit the config.txt file in the main directory. 
	First line is for the location of model directory
	Second line is for the location of corpus directory


Command to compile:
	javac -O -encoding UTF8 -d .  *.java


Command to run the program:
	java -Xmx2g -cp . edu.umbc.wordSimilarity2.WindowScanByStanfordPOS 0 "US" "USA"
	
	Command line arguments are  the size of context window, the two input word for the purpose of debugging. 



	
