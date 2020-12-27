package stockScrape;

/* Stock Scraper
 * Data analyzed off of Yahoo! Finance
 * 
 * Libraries Referenced - very important part of the project
 * Jsoup
 * poi.apache
 * 
 * Created By : 
 * Muhammad Rayaq Siddiqui
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StockScrape 
{
	// declare and initializes Scanner
	public static Scanner scr = new Scanner(System.in);
	
	// initializing String variables (to be global)
	public static String ticker, url;
	
	// month, day, year, open, high, low, close, adjusted close, volume
	// backup array just in case there are projects with the stock class
//	public static Object[][] tableInformation;
	
	// Stock Value Array - OBJECT ORIENTED PROGRAMMING
	public static StockValue[] currentStock;
	
	public static void main(String[] args) throws IOException
	{
		// Finding the URL
		System.out.println("What is the name of the stock ticker (no spaces)?");
		ticker = scr.nextLine().toUpperCase().trim();
		
		url = "https://ca.finance.yahoo.com/quote/" + ticker + "/history?p=" + ticker;
		System.out.println(url);
		
		// read description above function for information regarding function
		initializeValues();

		writeToExcel();
		
		scr.close();
	}
	
	/* writeToExcel()
	 * the function will write the date and stock value to excel
	 */
	@SuppressWarnings("resource")
	public static void writeToExcel() throws FileNotFoundException, IOException
	{
		try
		{
			// create the actual workbook and the sheet to write on
			FileInputStream inputStream = new FileInputStream(new File("StockScrape.xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			
			// applying font
//			Font font = workbook.createFont();
//			font.setFontName("Times New Roman");
//			font.setFontHeight((short)12);
			
			// applying cell style
//			CellStyle cs = workbook.createCellStyle();
//			cs.setAlignment(HorizontalAlignment.CENTER);
//			cs.setFont(font);
			
	//		XSSFWorkbook workbook = new XSSFWorkbook();
	//		XSSFSheet sheet = workbook.createSheet("StockScrape");//.createSheet("StockScrape");
			
			// now a for loop system to print the data out
			for (int i = 1; i < currentStock.length; i++)
			{
				// creating a new row for every new stock date
				Row row = sheet.createRow(i + 1);
				
				for (int j = 0; j < 8; j++)
				{
					// creating a new cell for all of the data
					Cell cell = row.createCell(j + 1);
					
					try 
					{
						int flip = currentStock.length - i - 1;
						if (j != 7 && currentStock[flip].getInd(j) != null)
						{
							System.out.println(currentStock[flip].getInd(j));
							
							// setting the values inside of the cell
							if (currentStock[flip].getInd(j) instanceof String)
							{
								cell.setCellValue((String) currentStock[flip].getInd(j));
							}
							else if (currentStock[flip].getInd(j) instanceof Double)
							{
								cell.setCellValue((Double) currentStock[flip].getInd(j));
							}
						}
						else if (j == 7 && i != 101)
						{
							cell.setCellValue((Integer) i);
						}
						
						// house keeping
						if (i == 1 && j == 7)
						{
							cell = row.createCell(j + 2);
							cell.setCellValue((String) ticker);
						}
						else if (i == 2 && j == 7)
						{
							cell = row.createCell(j + 2);
							cell.setCellValue((String) url);
						}
					}
					catch (Exception e)
					{
						// this is fine to leave blank
					}
				}
			}
			
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream("StockScrape.xlsx");
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/* initializeValues()
	 * essentially initializes all of the values for object tableInformation in the sequence
	 * [month, day, year, open, high, low, close, adjusted close, volume]
	 * this functions main purpose is to clean up the main function
	 */
	public static void initializeValues() throws IOException
	{
		// establish connection
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0").get();
		
		// finding the name and price
		// only grabs the first 102 data points (I think that is max size for the arrays)
		Elements tableRow = doc.getElementsByTag("tr");
		
		// initalize object array
//		tableInformation = new Object[tableRow.size()][7];
		currentStock = new StockValue[tableRow.size()];

		// iterating through collected values
		int i = 0;
		for (Element info : tableRow)
		{
			String currentRow = info.text();
			boolean dividend = false;
			
			if (currentRow.toLowerCase().contains("dividend"))
				dividend = true;
			
			if (!dividend && i != 0)
			{
//				tableInformation[i] = currentRow.split(" ");
				currentStock[i] = new StockValue(currentRow.split(" "));
				System.out.println(currentStock[i].toString());
			}
			
			i++;
		}
	}
}
