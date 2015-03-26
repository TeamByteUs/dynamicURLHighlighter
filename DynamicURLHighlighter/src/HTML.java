import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import team.misc.ArrayOrganizer;
import team.misc.ArticleSearch;
import team.misc.BinarySearcher;
import team.misc.FileReaderObject;
import team.misc.MarkUpText;
import team.misc.URLContentExtractor;

public class HTML {
	public static void main(String[] args) throws IOException {
		String something = "http://www.huffingtonpost.com/2015/03/14/at-least-10-americans-bei_n_6870406.html";
		HTML html = new HTML();
		System.out.println(html.mark(something)); 
	}

	public String mark(String urlString) throws IOException {

		URLContentExtractor urlce = new URLContentExtractor();
		ArrayList<String> wordList = getWordList();

		ArrayList<String> textArray = new ArrayList<>();
		ArrayList<String> textWordsArray = new ArrayList<>();
		HashMap<String, Integer> articleContains = new HashMap<>();
		ArrayList<String> articleWords = new ArrayList<>();

		URL url = new URL(urlString);
		String text = urlce.read(url);

		Document doc = Jsoup.parse(text, "UTF-8");
		Elements elements = doc.select("p");
		for (Element element : elements) {
			textArray.add(element.text());
		}

		textWordsArray = ArrayOrganizer.createArray(textArray, ".?! ,()[]\"");
		articleContains = BinarySearcher.search(wordList, textWordsArray);

		Set<String> keySet = articleContains.keySet();
		articleWords.addAll(keySet);
		String markedText = MarkUpText.markUp(text, articleWords);		
		return markedText;
	}
	
	private ArrayList<String> getWordList() {
		InputStream inputStream = ArticleSearch.class.getResourceAsStream("/src/main/resources/words.txt");
		FileReaderObject reader = new FileReaderObject(inputStream);
		ArrayList<String> wordList = reader.sanitizeText(" ,\"");
		return wordList;
	}

}
