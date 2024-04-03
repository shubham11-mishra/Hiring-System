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
  <title>Hiring System-Final Selected Candidates</title>
</head>
<body>
	<h1>List Of Final Selected Candidates</h1>
	<c:choose>
		<c:when test="${size==0}">
			<tr>
				<td>No data available</td>
			</tr>
		</c:when>
	<c:otherwise>
		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">Candidate Id</th>
					<th scope="col">Candidate Name</th>
					<th scope="col">candidateResult</th>
					<th scope="col">appliedPosition</th>
				</tr>
			</thead>
			<c:forEach var="hello" items="${wow}">
				<tr>
					<td>${hello.candidateId}</td>
					<td><a href="/candidate/getCandidate/${hello.candidateId}">${hello.candidateName}</a></td>
					<td>${hello.candidateResult}</td>
					<td>${hello.appliedPosition}</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
	</c:choose>
</body>
</html>