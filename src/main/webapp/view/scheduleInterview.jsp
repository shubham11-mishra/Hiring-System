<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Schedule Interview</title>
</head>
<body>
	<h1>Schedule Interview</h1>
	<h3>Notes :- </h3>
	<p>requisitionDetails
    <p>1.Interview Time Slot In Between ${requisitionDetails.timeslot}.</p>
    <p>2.There Will Be ${requisitionDetails.noPanels} for Every Interview</p>
    <p>3.${requisitionDetails.specificPanelName} is/her Required for Every Interview</p>

	<form action="/scheduleInterview/addInterview" method="post">
		<input type="hidden" name="humanResource" value="${hrId}" /> <input
			type="hidden" name="candidate" value="${candidateId}" /> </br> <label>Select
			Interview Date & Time</label> <input type="datetime-local"
			name="interviewTime" /> </br> <label>Select Panels</label> <select
			name="panels" multiple="multiple">
			<c:forEach var="result" items="${listOfPanels}">
				<option value="${result.employeeId}">${result.employeeName}</option>
			</c:forEach>
		</select> <input type="hidden" name="manager" value="${managerId}" /> </br> <label>Select
			Level of Interview</label> <select name="levelOfInterview">
			<option value="L1Scheduled">L1Scheduled</option>
			<option value="L1ReScheduled">L1ReScheduled</option>
			<option value="L2Scheduled">L2Scheduled</option>
			<option value="L2ReScheduled">L2ReScheduled</option>
		</select> </br> <input class="submit" type="submit" value="Schedule Interview">
	</form>
</body>
</html>