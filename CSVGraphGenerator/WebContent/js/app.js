$(document).ready(function(){
	
	$("#slider-range").slider({
		range: true,
		min: 0,
		max: 1439,
		step: 5,
		values: [540, 560],
		slide: function (e, ui) {
			var hours1 = Math.floor(ui.values[0] / 60);
			var minutes1 = ui.values[0] - (hours1 * 60);
			if (hours1.length == 1) hours1 = '0' + hours1;
			if (minutes1.length == 1) minutes1 = '0' + minutes1;
			if (minutes1 == 0) minutes1 = '00';
			if (hours1 >= 12) {
				if (hours1 == 12) {
					hours1 = hours1;
					minutes1 = minutes1 //+ " PM";
				} else {
					//hours1 = hours1 - 12;
					minutes1 = minutes1 //+ " PM";
				}
			} else {
				hours1 = hours1;
				minutes1 = minutes1 //+ " AM";
			}
			
			$('.slider-time').html(hours1 + ':' + minutes1);
			var time1 = hours1 + ':' + minutes1;

			var hours2 = Math.floor(ui.values[1] / 60);
			var minutes2 = ui.values[1] - (hours2 * 60);

			if (hours2.length == 1) hours2 = '0' + hours2;
			if (minutes2.length == 1) minutes2 = '0' + minutes2;
			if (minutes2 == 0) minutes2 = '00';
			if (hours2 >= 12) {
				if (hours2 == 12) {
					hours2 = hours2;
					minutes2 = minutes2 //+ " PM";
				} else if (hours2 == 24) {
					hours2 = 00;
					minutes2 = "59";
				} else {
					//hours2 = hours2 - 12;
					minutes2 = minutes2 //+ " PM";
				}
			} else {
				hours2 = hours2;
				minutes2 = minutes2 //+ " AM";
			}
			var time2 = hours2 + ':' + minutes2
			$('.slider-time2').html(hours2 + ':' + minutes2);
			var ticker = $("#ticker").val();
			var chartType = $("#chartType").val();
			getChartData(ticker,time1,time2,chartType);
		}
	});

	$("#ticker").prop("selectedIndex", 1);
	drawChart();
});

function drawChart(){
	var startTime = $('.slider-time').text().trim(); // for slider with single knob or lower value of range
	var endTime = $('.slider-time2').text().trim();  // for highest value of range
	var ticker = $("#ticker").val();
	var chartType = $("#chartType").val();
	getChartData(ticker,startTime,endTime,chartType);
}

function getChartData(ticker,startTime,endTime,chartType){
	//var ticker = $("#ticker").val();
	//var startTime = $("#timepickerStart").val();
	//var endTime = $("#timepickerEnd").val();
	$("#loadingDiv").show();
	$("#chartDiv").css({'visibility':'hidden'});
	$.ajax(
		{
			url: "getChartData.htm", 
			mrthod:'post',
			data:'ticker='+ticker+"&startTime="+startTime+"&endTime="+endTime,
			success: function(result){
				
				setTimeout(function(){ 
					var chartControl = new ChartControl('chartDiv');
					chartControl.chartType = chartType;
					chartControl.chartTitle = 'Ticker vs Trades Size';
					var xAxis = new ChartXAxis();
					xAxis.title = 'Ticker';
					chartControl.xAxis = xAxis;
					var yAxis = new ChartYAxis();
					yAxis.title = 'Trades Size';
					chartControl.yAxis = yAxis;
					var series = new ChartSeries();
					series.name = 'MCT';
					series.data = [];
					var arr = [];
					eval(result)
					chartControl.series = csvResult;
					chartControl.renderChart();
					$("#loadingDiv").hide();
					$("#chartDiv").css({'visibility':'visible'});
				}, 1000);
				
				
				
				
			}
		}
	);
}