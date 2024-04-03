<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Panel Dashboard</title>
</head>
<body>
	<div class="container">
		<h1 class="title">Panel Dashboard</h1>
		<h4 class="subtitle">Welcome ${manager.employeeName}</h4>
		<div class="row">
			<div class="col-3">
				<a href="/scheduleInterview/interviewList/${manager.employeeId}">List
					of Interviews</a>
			</div>
			<div class="col-3">
				<a
					href="/scheduleInterview/interviewsTakenList/${manager.employeeId}">List
					of Taken Interviews</a>
			</div>
		</div>
	</div>
</body>
</html>
