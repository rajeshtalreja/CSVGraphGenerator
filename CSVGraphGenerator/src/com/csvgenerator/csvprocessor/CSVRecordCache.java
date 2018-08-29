package com.csvgenerator.csvprocessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 */

/**
 * @author Rajesh
 *
 */
public class CSVRecordCache {
	
	private static Map<String,List<CSVRecord>> csvCache = new HashMap<>();
	
	public static List<CSVRecord> getCSVRecordList(String ticker){
		
		if(ticker != null){
			return csvCache.get(ticker);
		}
		return null;
	}
	
	public static void put(String ticker , List<CSVRecord> csvRecords){
		csvCache.put(ticker, csvRecords);
	}
	
	public static Set<String> getCacheKeys(){
		return csvCache.keySet();
	}
	
	/**
	 * 
	 */
	public static void clearCache(){
		csvCache.clear();
	}

}
