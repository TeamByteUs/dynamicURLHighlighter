

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VocabReader {
	public static void main(String[] args) {
	}

	public static String[] wordsArray;
	static List<String> words = new ArrayList<String>();
	static String token1;

	public static String[] populateArray() {
		Scanner inFile = openFileForReading();
		while (inFile.hasNext()) {
			token1 = readWord(inFile);
			words.add(token1);
			wordsArray = words.toArray(new String[0]);
			Arrays.sort(wordsArray);
		}
		return wordsArray;
	}

	@SuppressWarnings("resource")
	public static Scanner openFileForReading() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("words.txt"));
			scanner = scanner.useDelimiter("\", *\"");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scanner;
	}

	public static String readWord(Scanner inFile) {
		token1 = inFile.next();
		token1 = token1.replace("\"", "").trim();
		return token1;
	}

	public static void printWords() {
		for (String string : wordsArray) {
			System.out.println(string);
		}
	}

}
