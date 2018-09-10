/**
 * 
 * @param chartDiv
 */
function ChartControl(chartDiv){
	
	/**
	 * 
	 */
	this.chartDiv = chartDiv;
	
	/**
	 * 
	 */
	this.chartData = null;
	
	/**
	 * 
	 */
	this.xAxis = null;
	
	/**
	 * 
	 */
	this.yAxis = null;
	
	/**
	 * 
	 */
	this.chartTitle = null;
	
	/**
	 * 
	 */
	this.chartType = null;
	
	/**
	 * 
	 */
	this.chartProviderObj = null;
	
	this.categories = null;
	
	/**
	 * 
	 */
	this.renderChart = function(){
		var chartDiv = this.chartDiv;
		var chartType = this.chartType;
		var chartTitle = this.chartTitle;
		var chartSubTitle = this.chartSubTitle;
		var xAxis = this.xAxis;
		var yAxis = this.yAxis;
		var series = this.series;
		var categories = this.categories;
		
		Highcharts.chart(chartDiv , {
		    chart: {
		        type: chartType,
		        zoomType: 'x'
		    },
		    title: {
		        text: chartTitle
		    },
		    subtitle: {
		        text: chartSubTitle ? chartSubTitle : ''
		    },
		    //categories:categories,
		    xAxis: {
		    	/*type: 'datetime',
		    	dateTimeLabelFormats:{
		    	    second: '%H:%M:%S',
		    	    minute: '%H:%M',
		    	    hour: '%H:%M'
		    	}*/
		    	title:{
		    		text : xAxis.title
		    	}
		    },
		    yAxis: {
		        
		        title: {
		            text: yAxis.title
		        }
		    },
		  
		    plotOptions: {
		        /*column: {
		        	dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: true
		        },
		        bar: {
		        	dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
		        },
		        line: {
		        	dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
		        }*/
		    
		    },
		    
		    series: series
		});
		
		
		
		
	}
	/**
	 * 
	 */
	this.refreshChart = function(){
		
	}
	/**
	 * 
	 */
	this.updateData = function(data){
		
	}
	
}
/**
 * 
 */
function ChartXAxis(){
	this.title = null;
	this.categories = [];
}
function ChartYAxis(){
	this.title = null;
}

function ChartSeries(){
	this.name = null;
	this.data = [];
}
