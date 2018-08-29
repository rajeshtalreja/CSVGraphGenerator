/**
 * 
 */
package com.csvgenerator.csvprocessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Rajesh
 *
 */
public class CSVProcessor {
	
	/**
	 * 
	 */
	private InputStream is;

	/**
	 * 
	 * @param is
	 */
	public CSVProcessor(InputStream is) {
		CSVRecordCache.clearCache();
		this.is = is;
	}
	
	private Function<String, CSVRecord> mapToItem = (line) -> {
		String[] csvRecord = line.split(",");

		String ticker = csvRecord[1];
		TradeTime time = new TradeTime();
		time.createTradeTime(csvRecord[0]);
		Double price = Double.parseDouble(csvRecord[2]);
		Integer size = Integer.parseInt(csvRecord[3]);
		CSVRecord record = new CSVRecord();
		record.setTicker(ticker);
		record.setTime(time);
		record.setSize(size);
		record.setPrice(price);

		List<CSVRecord> csvRecordList = CSVRecordCache
				.getCSVRecordList(ticker);
		if (csvRecordList == null) {
			csvRecordList = new ArrayList<>();
		}
		csvRecordList.add(record);
		CSVRecordCache.put(ticker, csvRecordList);
		return record;
	};


	/**
	 * 
	 */
	public void buildCache() {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			InputStreamReader reader = new InputStreamReader(is);
			br = new BufferedReader(reader);
			// skip the header of the csv
		    Set<CSVRecord> tickers = br.lines().skip(1).map(mapToItem).collect(Collectors.toSet());
		    br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
