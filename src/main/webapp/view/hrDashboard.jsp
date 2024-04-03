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
  <title>Hiring System-HR Dashboard</title>
</head>
<body>
	<div class="container">
	    <h1 class="title">HR Dashboard</h1>
		<h4 class="subtitle">Welcome ${manager.employeeName}</h4>
		<div class="row">
			<div class="col-3">
				<a href="/candidate/notShortlistedCandidates/${manager.employeeId}">List Of Candidates</a>
			</div>
			<div class="col-3">
				<a href="/candidate/shortlistedCandidates/${manager.employeeId}">Shortlisted Candidates</a>
			</div>
			<div class="col-3">
				<a href="/candidate/results/${manager.employeeId}">Result Of Candidates</a>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<a href="/candidate/addCandidate/${manager.employeeId}">Add	Candidate</a>
			</div>
			<div class="col-3">
				<a href="/scheduleInterview/hrInterviewList/${manager.employeeId}">List Of Scheduled Interview</a>
			</div>
			<div class="col-3" style="display: none;"></div>
		</div>
	</div>
	</div>
</body>
</html>
