<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="css/graph.css" rel="stylesheet" />
    <link href="css/jquery-ui.css" rel="stylesheet" />
    <link href="css/jquery.multiselect.css" rel="stylesheet" />
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery.multiselect.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="js/charts/highcharts/exporting.js"></script>
	<script src="js/charts/highcharts/export-data.js"></script>
	<script src="js/charts/ChartControl.js"></script>
	<script src="js/app.js"></script>
</head>
<body>

	<table width='100%'>
		<tr>
			<td style="width:100%">
				<div id='chartDiv' style='width:100%;height:50%;'>
   				</div>
			</td>
		</tr>
		<tr>
			<td>
				Select Ticker :
    			<select name="ticker" id="ticker" onChange="drawChart()">
    				<option value=""></option>
        			<c:forEach items="${tickers}" var="item">
        				<option value="${item}">${item}</option>
        			</c:forEach>
    			</select>
    			<br/>
    			<br/>
    			<br/>
    			Chart Type : &nbsp;&nbsp;&nbsp;
    			<select name="chartType" id="chartType" onChange="drawChart()">
    				<option value="column" selected>Bar Chart</option>
    			</select>
    			<br/>
    			<br/>
    			<br/>
    			<div id="time-range">
					<p>Time Range: <span class="slider-time">9:00</span> - <span class="slider-time2">09:10</span>

					</p>
					<div class="sliders_step1">
						<div id="slider-range"></div>
					</div>
				</div>
			</td>
		</tr>	
	</table>		
   	<script>
   			drawChart();
   	</script>
   	<div id="loadingDiv" style="width:100%;height: 50%;opacity: 0.5;top: 0px;position: absolute;display:none;margin-left:45%;margin-top:10%">
   		<img src='images/loader.gif'/>
   		<br/><div>Loading Chart...</div> 
   	</div>		
</body>
</html>
