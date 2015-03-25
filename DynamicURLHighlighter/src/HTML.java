

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import team.misc.ArrayOrganizer;
import team.misc.BinarySearcher;
import team.misc.Constants;
import team.misc.FileReaderObject;
import team.misc.HtmlOutput;
import team.misc.MarkUpText;
import team.misc.URLContentExtractor;

public class HTML {
	public static void main(String[] args) throws IOException {
		String something = "http://www.huffingtonpost.com/2015/03/14/at-least-10-americans-bei_n_6870406.html";
		System.out.println(mark(something)); 
	}

	public static String mark(String url) throws IOException {

		URLContentExtractor urlce = new URLContentExtractor();

		ArrayList<String> textArray = new ArrayList<>();
		ArrayList<String> textWordsArray = new ArrayList<>();
		HashMap<String, Integer> articleContains = new HashMap<>();
		ArrayList<String> articleWords = new ArrayList<>();
		ArrayList<String> inputWords = new ArrayList<>();
		String userWordInput = null; 
		URL inUrl = new URL(url); 

		String text = urlce.read(inUrl);
//		System.out.println(text);

		Document doc = Jsoup.parse(text, "UTF-8");
		Elements elements = doc.select("p");
		for (Element element : elements) {
			textArray.add(element.text());
		}

		textWordsArray = ArrayOrganizer.createArray(textArray, ".?! ,()[]\"");
//		System.out.println(textWordsArray);
//		BufferedReader wordsReader = new BufferedReader(new InputStreamReader(
//				System.in));
//		userWordInput= wordsReader.readLine();
//		inputWords.add(userWordInput.toString());
		
		
		String[] wordList = VocabReader.populateArray();
		ArrayList<String> wordsArrayList = new ArrayList<String>(Arrays.asList(wordList)); 
		textWordsArray = ArrayOrganizer.createArray(textArray, ".?! ,()[]\"");
		articleContains = BinarySearcher.search(wordsArrayList, textWordsArray);
		
		Set<String> keySet = articleContains.keySet();
		articleWords.addAll(keySet);
		String markedText = MarkUpText.markUp(text, articleWords);
		
		return markedText; 
//
//		Path outPath = Paths.get("merked.html"); 
//		HtmlOutput.htmlOut(markedText, outPath);
	}

}
