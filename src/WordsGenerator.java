
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


public class WordsGenerator {

	//String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890,.;:()[]{}!?<>@#$%^&*";
	String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
	String words;
	Random random = new Random();
	static final int WORDLENGTH = 5;
	int charIndex;

	public WordsGenerator() {

	}

	public String getWords(int numWords) {
		
		words = "";

		for (int i=0; i<numWords; i++) {

			// formar una palabra
			for (int x=0; x<WORDLENGTH; x++) {

				charIndex = random.nextInt(chars.length()-1); // asegurar que no sea 0
				charIndex++; // charIndex puede tener de 1 a el length de char

				words += (chars.subSequence(charIndex, (charIndex+1)));
			}

			words += " "; // fin de una palabra
		}
		// fin de la linea
		return words;
	}

}