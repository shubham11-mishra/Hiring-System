<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Hiring System-Generate Report</title>
</head>
<body>
	<form:form action="/candidate/generateReport/${managerID}" modelAttribute="product" method="post">
		<h1>Generate Report ${product.startDate}</h1>
		<label for="startDate">Start Date : </label>
		<form:input type="date" id="datetime" path="startDate" name="startDate"  />
		<label for="endDate">End Date : </label>
        <form:input name="endDate" path="endDate" type="date"  />
		<label for="requisitionId">Requisition Name and Project Name: </label>
        <form:select path="requisitionId" name="requisitionId">
		    <c:forEach var="result" items="${requisitionList}">
			    <form:option value="${result.jobId}">${result.jobProfile}  ${result.projectName}</form:option>
		    </c:forEach>
		</form:select>
		<button type="submit">Generate Report</button>
	</form:form>
</body>
</html>