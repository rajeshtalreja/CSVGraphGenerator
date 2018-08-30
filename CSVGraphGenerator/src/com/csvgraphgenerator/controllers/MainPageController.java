/**
 * 
 */
package com.csvgraphgenerator.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.csvgenerator.csvprocessor.CSVProcessor;
import com.csvgenerator.csvprocessor.CSVRecord;
import com.csvgenerator.csvprocessor.CSVRecordCache;
import com.google.gson.Gson;

/**
 * @author Rajesh
 *
 */
@Controller
public class MainPageController {
	
	/**
	 * This is the controller to load the Main Page and will show the Home Page
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	String getIdByValue(HttpServletRequest req, HttpServletResponse res) {
		return "HomePage";
	}
	

	@RequestMapping(value = "/processCSV.htm")
	public String submit(@RequestParam("csvfile") MultipartFile file,
			MultipartHttpServletRequest req, ModelMap modelMap) {

		//if method is GET then no need process the CSV
		if(req.getRequestMethod() == HttpMethod.GET){
			return "AccessDenied";
		}
		
		if(file == null || file.getOriginalFilename().indexOf(".csv") == -1){
			return "InvalidInputFile";
		}
		
		CSVProcessor csvProcessor = null;
		try {
			csvProcessor = new CSVProcessor(file.getInputStream());
			long startTime = System.currentTimeMillis();
			csvProcessor.buildCache();
			long endTime = System.currentTimeMillis();
			System.out.println((endTime - startTime)/1000 + " seconds.");
		} catch (IOException e) {
			System.out.println("Could not load the file");
		}
		
		//Get All the distinct Keys
		Set<String> tickers = CSVRecordCache.getCacheKeys();
		modelMap.addAttribute("tickers", tickers);
		return "Graph";
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getChartData.htm", method = RequestMethod.GET)
	public String getChartData(HttpServletRequest req, HttpServletResponse res){
		//Ticker from the UI
		String ticker = req.getParameter("ticker");
		
		//Start time
		String startTime = req.getParameter("startTime");
		
		//End time
		String endTime = req.getParameter("endTime");
		
		String[] startTimeSplit = startTime.split(":");
		String[] endTimeSplit = endTime.split(":");
		
		//Start time in seconds
		Long startTimeInSeconds = Integer.parseInt(startTimeSplit[0]) * 60L * 60L + Integer.parseInt(startTimeSplit[1]) * 60L ;//+ Integer.parseInt(startTimeSplit[2]);
		Long endTimeInSeconds = Integer.parseInt(endTimeSplit[0]) * 60L * 60L + Integer.parseInt(endTimeSplit[1]) * 60L ;//+ Integer.parseInt(endTimeSplit[2]);
		
		List<CSVRecord> records = CSVRecordCache.getCSVRecordList(ticker);
		
		if(records == null || records.size() == 0){
			return "var csvResult = [];";
		}
		records = records.stream().filter(r->{
			
			double time = r.getTime().getHr() * 60d * 60d  + r.getTime().getMin() * 60d + r.getTime().getSec();
			
			if(time >= startTimeInSeconds.doubleValue() && time <= endTimeInSeconds.doubleValue()){
				return true;
			}
			return false;
		}).collect(Collectors.toList());

		Collections.sort(records);
		
		String toReturn = "var csvResult =  [ { 'name' : '" + ticker + "', 'data' : [";
		Map<String,List<CSVRecord>> map = new TreeMap<>();
		for(CSVRecord rec : records){
			List<CSVRecord> csvRecs = map.get(rec.getTime().getHr() + "" + rec.getTime().getMin());
			if(csvRecs == null){
				csvRecs = new ArrayList<>();
			}
			csvRecs.add(rec);
			map.put(rec.getTime().getHr() + "_" + rec.getTime().getMin(), csvRecs);
		}
		
		Iterator<String> itr = map.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			List<CSVRecord> list = map.get(key);
			double val = 0.0;
			for(CSVRecord i : list){
				val += i.getSize();
			}
			toReturn += " [ Date.UTC(1970,1,1, " + key.split("_")[0] + ","+key.split("_")[1] + "," + 0+") , "+val +"] ,";
		}
		
		toReturn += "] } ]";
		Gson gson = new Gson();
		return toReturn;
	}

}
