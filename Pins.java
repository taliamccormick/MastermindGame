package pins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;

public class Pins {
	static private int numPins = 4;
	static private String pinColours[] = {"Red", "Blue", "Yellow", "Green"};
	static public HashMap<String, Color> colourOfPins = new HashMap<>();
	static{
		colourOfPins.put ("Red", Color.RED);
		colourOfPins.put ("Blue", Color.BLUE);
		colourOfPins.put ("Yellow", Color.YELLOW);
		colourOfPins.put ("Green", Color.GREEN);
	}
	
	String[] pins;
	
	public Pins(){
		this.pins = new String[numPins];
		for (int i = 0; i < numPins; i++){
			pins[i] = "";
		}
	}
	
	public static int randInt (int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static Pins generatePins (){
		Pins pin = new Pins();
		for (int i = 0; i < numPins; i++){
			pin.pins[i] = pinColours[randInt(0,numPins-1)];
		}
		return pin;
	}
	
	public static int getNumPins(){
		return numPins;
	}
	
	public static String[] getPinColours(){
		return pinColours;
	}
	
	/*public static int[] evaluatePins(Pins guessedPins, Pins generatedPins){
		//Initializes vars for the number of pins that are correct, are the right colour, and are wrong
		int numRight = 0, numRightColour = 0, numWrong = 0;

		//Initializes vars to count the number of each colour of pin that is not correct
		HashMap<String, Integer> numColourGuessed = new HashMap<>();
		HashMap<String, Integer> numColourGenerated = new HashMap<>();
		for (int i = 0; i < Pins.getNumPins(); i++){
			numColourGuessed.put(Pins.getPinColours()[i], 0);
			numColourGenerated.put(Pins.getPinColours()[i], 0);
		}

		//Counts how many are correct & the number of each colour that are incorrect
		for (int i = 0; i < Pins.getNumPins(); i++){
			if (guessedPins.pins[i] == generatedPins.pins[i]) numRight++;
			else {
				numColourGuessed.put(guessedPins.pins[i], numColourGuessed.get(guessedPins.pins[i]) + 1);
				numColourGenerated.put(generatedPins.pins[i], numColourGenerated.get(generatedPins.pins[i]) + 1);
			}
		}

		//Counts the number that are the right colour but wrong place
		for( String k: numColourGuessed.keySet() ){
			numRightColour = numRightColour + Math.min(numColourGuessed.get(k), numColourGenerated.get(k));
		}

		//FInds the number that are wrong
		numWrong = Pins.getNumPins() - numRight - numRightColour;

		//Returns an array of three numbers (completely right, right colour, and completely wrong)
		int[] a = {numRight, numRightColour, numWrong}; 
		return a;
	}*/
	
	public int[] evaluatePins(Pins generatedPins){
		//Initializes vars for the number of pins that are correct, are the right colour, and are wrong
		int numRight = 0, numRightColour = 0, numWrong = 0;

		//Initializes vars to count the number of each colour of pin that is not correct
		HashMap<String, Integer> numColourGuessed = new HashMap<>();
		HashMap<String, Integer> numColourGenerated = new HashMap<>();
		for (int i = 0; i < Pins.getNumPins(); i++){
			numColourGuessed.put(Pins.getPinColours()[i], 0);
			numColourGenerated.put(Pins.getPinColours()[i], 0);
		}

		//Counts how many are correct & the number of each colour that are incorrect
		for (int i = 0; i < Pins.getNumPins(); i++){
			if (this.pins[i] == generatedPins.pins[i]) numRight++;
			else {
				numColourGuessed.put(this.pins[i], numColourGuessed.get(this.pins[i]) + 1);
				numColourGenerated.put(generatedPins.pins[i], numColourGenerated.get(generatedPins.pins[i]) + 1);
			}
		}

		//Counts the number that are the right colour but wrong place
		for( String k: numColourGuessed.keySet() ){
			numRightColour = numRightColour + Math.min(numColourGuessed.get(k), numColourGenerated.get(k));
		}

		//FInds the number that are wrong
		numWrong = Pins.getNumPins() - numRight - numRightColour;

		//Returns an array of three numbers (completely right, right colour, and completely wrong)
		int[] a = {numRight, numRightColour, numWrong}; 
		return a;
	}
}
