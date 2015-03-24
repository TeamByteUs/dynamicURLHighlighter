import team.misc.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLMarkupCaller {

	public static void main(String[] args) throws IOException {
		String something = "http://www.foxnews.com/world/2015/03/24/plane-reportedly-crashes-in-french-alps-with-at-least-142-people-on-board/";
		markup(something);
	}

	public static /*String*/void markup(String userUrl) throws IOException {
//		String html = "";
		// String sep = File.separator;
		// ArrayList<String> wordList = CustomWordListPrompter.prompt();
		// ArrayList<String> urlList = CustomUrlPrompter.prompt();
		URLContentExtractor urlce = new URLContentExtractor();
		// for (int i = 0; i < urlList.size(); i++) {
		ArrayList<String> textArray = new ArrayList<>();
		ArrayList<String> textWordsArray = new ArrayList<>();
		HashMap<String, Integer> articleContains = new HashMap<>();
		ArrayList<String> articleWords = new ArrayList<>();
		// URL url = new URL(urlList.get(i));
		URL url = new URL(userUrl);
		String text = urlce.read(url);
		Document doc = Jsoup.parse(text, "UTF-8");
		Elements elements = doc.select("p");
		for (Element element : elements) {
			textArray.add(element.text());
		}
		textWordsArray = ArrayOrganizer.createArray(textArray, ".?! ,()[]\"");

		ArrayList<String> wordList = new ArrayList<>();
		BufferedReader wordsReader = new BufferedReader(new InputStreamReader(
				System.in));
		String userWordInput = wordsReader.readLine();
		ArrayList<String> inputWords = new ArrayList<>();
		inputWords.add(userWordInput.toString());
		wordList = ArrayOrganizer.createArray(inputWords, " ,\"");

		articleContains = BinarySearcher.search(wordList, textWordsArray);
		Set<String> keySet = articleContains.keySet();
		articleWords.addAll(keySet);
		String markedText = MarkUpText.markUp(text, articleWords);

		File file = new File("dosomething.html");
		file = 
		
		Path outPath = Paths.get("dosomething.html"); // TODO
		HtmlOutput.htmlOut(markedText, outPath);
		// }
//		return html;
		Runtime.getRuntime().exec("open" + outPath);
	}
}
