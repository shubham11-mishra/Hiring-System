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
  <title>Hiring System-Add Employee</title>
</head>
<body>
	<h1>Add Employee</h1>
	<form action="/employee/save" method="post">
		<label>Name</label>
		<input type="text" name="employeeName">
		<label>Password</label>
		<input type="text" name="employeePassword">
		<label>Select Designations</label>
		<select name="roles" multiple="multiple">
		    <c:forEach var="result" items="${listOfRoles}">
		    	<option value="${result.roleId}">${result.designation}</option>
		    </c:forEach>
		</select>
		<label>Manager ID</label>
		<select name="manager">
		<option value=""> </option>
        		    <c:forEach var="result" items="${listOfManagers}">
        		    	<option value="${result.employeeId}">${result.employeeName}</option>
        		    </c:forEach>
        		</select>
		<input class="submit" type="submit" value="Add Requisition">
	</form>
</body>
</html>