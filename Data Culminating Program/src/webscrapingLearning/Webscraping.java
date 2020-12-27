package webscrapingLearning;

// MUHAMMAD RAYAQ SIDDIQUI
// Reference : https://www.youtube.com/watch?v=0s8O7jfy3c0

/* External JARs
 * Right click "Exploring Webscraping" and go to properties
 * in Java Build Path/ libraries add the external jar jsoup
 * https://jsoup.org/download
 * download all three
 * check referenced libraries
 */

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Webscraping 
{
	public static void main(String[] args)
	{
		try
		{
			// Connecting to the URL
			String url = "https://www.imdb.com/search/title/?groups=top_250&sort=user_rating";
			
			Document doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();
			
			// this is the specific element on the website
			// if class - div.(...)
			// if id - div.#(...)      ??
			Elements elements = doc.select("div.lister-item-content");
			
			// iterating through elements
			int count = 0;
			for (Element element : elements)
			{
				count++;
				
				/* <div class="lister-item-content">
				 * child <a> Text </a>
				 */
				String title = element.getElementsByTag("a").first().text();
				System.out.println(count + ": " + title);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
