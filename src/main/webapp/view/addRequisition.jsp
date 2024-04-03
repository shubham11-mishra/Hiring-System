<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Add Requisition</title>
</head>
<body>
	<h1>Add Requisition</h1>
	<form action="/requisition/saveRequisition/${managerID}" method="post">
		<label>JobProfile</label>
		<input type="text" name="jobProfile">
		<label>JobDescription</label>
		<input type="text" name="jobDescription">
		<label>ProjectName</label>
		<input type="text" name="projectName">
		<label>PartnerName</label>
		<input type="text" name="partnerName">
		<label>Add Specific	Panel</label>
		<select	name="specificPanel" >
        	<c:forEach var="result" items="${panels}">
        		<option value="${result.employeeId}">${result.employeeName}</option>
        	</c:forEach>
        </select>
		<label>Timeslot</label>
		<input type="text" name="timeslot">
		<label>NoPanels</label>
		<input type="text" name="noPanels">
		<label>Additional Condition</label>
		<input type="text" name="additionalCondition">
		<input	class="submit" type="submit" value="Add Requisition">
	</form>
</body>
</html>