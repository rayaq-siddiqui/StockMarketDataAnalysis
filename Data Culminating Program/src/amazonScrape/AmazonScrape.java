package amazonScrape;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonScrape 
{
	static Scanner scr = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("What would you like to buy off Amazon? (no spaces)");
		String item = scr.nextLine();
		
//		String url = "https://www.amazon.ca/s?k=" + item.trim()+ "&ref=nb_sb_noss";
		String url = "https://www.amazon.ca/s?k=car&ref=nb_sb_noss";
		System.out.println(url);
		// establish connection
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0").get();
		
		// finding the name and price
		Elements prices = doc.select("div.a-row");
//		Elements names = doc.select("div.a-size-base-plus a-color-base a-text-normal");
		
		// iterating through collected values
		
		// String title = element.getElementsByTag("a").first().text();
		for (Element a : prices)
		{
			String currentPrice = a.getElementsByTag("span").text();
//			String currentName = a.getElementsByTag("span").first().text();
//			
//			System.out.println("test " + a.getElementsByTag("span").first().text());
			
			System.out.println(currentPrice);
		}
		
		scr.close();
	}
}
