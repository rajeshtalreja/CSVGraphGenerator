<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="css/graph.css" rel="stylesheet" />
    <link href="css/jquery-ui.css" rel="stylesheet" />
    <link href="css/jquery.multiselect.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/jquery.multiselect.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="js/charts/highcharts/exporting.js"></script>
	<script src="js/charts/highcharts/export-data.js"></script>
	<script src="js/charts/ChartControl.js"></script>
	<script src="js/app.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</head>
<body>

	<table width='100%'>
		<tr>
			<td>
				&lt; &nbsp;<a href="home.htm">Back</a>
			</td>
		</tr>
		<tr>
			<td style="width:100%">
				<div id='chartDiv' style='width:100%;height:400px;'>
   				</div>
			</td>
		</tr>
		<tr>
			<td>
				Select Ticker :
    			<select name="ticker" id="ticker" onChange="drawChart()" style="width:100px;">
    				<option value=""></option>
        			<c:forEach items="${tickers}" var="item">
        				<option value="${item}">${item}</option>
        			</c:forEach>
    			</select>
    			<br/>
    			<br/>
    			Chart Type : &nbsp;&nbsp;&nbsp;
    			<select name="chartType" id="chartType" onChange="drawChart()" style="width:150px;">
    				<option value="bar" selected>Bar Chart</option>
    				<option value="column">Column Chart</option>
    				<option value="line">Line Chart</option>
    			</select>
    			<br/>
    			<br/>
    			<div>
					<p>Time Range: &nbsp;&nbsp;&nbsp;<input type="text" value="9:30:00:01" id="startTime" onChange="drawChart();" />&nbsp; to &nbsp;<input type="text" value="9:35:00:01" id="endTime" onChange="drawChart();" /> 

					</p>
				</div>
				<div>
					Increment : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="increment" value="0.01" onChange="drawChart();"/>
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
