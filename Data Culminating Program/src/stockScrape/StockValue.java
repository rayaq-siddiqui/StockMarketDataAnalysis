package stockScrape;

public class StockValue 
{
	// DATA FIELD
	// Strings to represent the date (month, day, year)
	private String
		month,
		day,
		year;
	// Double to represent the following data
	// open, high, low, close, adjusted close, volume
	private Double 
		open,
		high,
		low,
		close,
		adjustedClose,
		volume;
	
	// CONSTRUCTOR
	public StockValue(Object[] data)
	{
		this.month = (String) data[0];
		this.day = (String) data[1];
		this.year = (String) data[2];
		
		for (int i = 3; i <=8; i++)
		{
			String tmpData = (String)data[i];
			
			if (tmpData.contains(","))
			{
				String newData = "";
				String[] brokenData = tmpData.split(",");
				for(int j = 0; j < brokenData.length; j++)
				{
					newData += brokenData[j];
				}
				data[i] = newData;
			}
		}
		
		this.open = Double.valueOf((String)data[3]);
		this.high = Double.parseDouble((String) data[4]);
		this.low = Double.parseDouble((String) data[5]);
		this.close = Double.parseDouble((String) data[6]);
		this.adjustedClose = Double.parseDouble((String) data[7]);
		this.volume = Double.parseDouble((String) data[8]);
	}

	// METHODS
	public String toString()
	{
		String s = "D: " + month+" "+day+" "+year + " O:" + open + 
				" H:" + high + " L:" + low + " C:" + close + 
				" AC:" + adjustedClose + " V:" + volume;
		return s;
	}
	
	public Object getInd(int ind)
	{
		if (ind == 0)
		{
			return (String) (getMonth() + " " + getDay() + " " + getYear());
		}
		else if (ind == 1)
		{
			return (Double) getOpen();
		}
		else if (ind == 2)
		{
			return (Double) getHigh();
		}
		else if (ind == 3)
		{
			return (Double) getLow();
		}
		else if (ind == 4)
		{
			return (Double) getClose();
		}
		else if (ind == 5)
		{
			return (Double) getAdjustedClose();
		}
		else if (ind == 6)
		{
			return (Double) getVolume();
		}
		else
		{
			return "ERROR"; 
		}
	}
	
	// GETTERS AND SETTERS
	/**
	 * @return the month
	 */
	public String getMonth() 
	{
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) 
	{
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public String getDay() 
	{
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) 
	{
		this.day = day;
	}

	/**
	 * @return the year
	 */
	public String getYear() 
	{
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) 
	{
		this.year = year;
	}

	/**
	 * @return the open
	 */
	public Double getOpen() 
	{
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Double open) 
	{
		this.open = open;
	}

	/**
	 * @return the high
	 */
	public Double getHigh() 
	{
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(Double high) 
	{
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public Double getLow() 
	{
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(Double low) 
	{
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public Double getClose() 
	{
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(Double close) 
	{
		this.close = close;
	}

	/**
	 * @return the adjustedClose
	 */
	public Double getAdjustedClose() 
	{
		return adjustedClose;
	}

	/**
	 * @param adjustedClose the adjustedClose to set
	 */
	public void setAdjustedClose(Double adjustedClose) 
	{
		this.adjustedClose = adjustedClose;
	}

	/**
	 * @return the volume
	 */
	public Double getVolume() 
	{
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Double volume) 
	{
		this.volume = volume;
	}
}
