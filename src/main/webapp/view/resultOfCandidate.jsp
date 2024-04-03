<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Result Of Candidates</title>
</head>
<body>
	<h1>Result of candidates</h1>
	<c:choose>
		<c:when test="${size==0}">
			<tr>
				<td>No data available</td>
			</tr>
		</c:when>
		<c:otherwise>
			<table class="table table-striped">
				<tr>
					<th>No</th>
					<th>Candidate Name:</th>
					<th>Applied Position:</th>
					<th>Status</th>
				</tr>
				<c:forEach var="result" items="${resultOfCandidates}">
					<tr>
						<td>${result.candidateId}</td>
						<td><a href="/candidate/getCandidate/${result.candidateId}">${result.candidateName}</a></td>
						<td>${result.appliedPosition}</td>
						<td>${result.candidateResult}</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
