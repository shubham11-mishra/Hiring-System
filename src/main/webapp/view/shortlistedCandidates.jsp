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
  <title>Hiring System-Shortlisted Candidates</title>
</head>
<body>
	<h1>All Shortlisted Candidates</h1>
	<table class="table table-striped">
		<c:choose>
			<c:when test="${size==0}">
				<tr>
					<td>No data available</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th>No</th>
					<th>Candidate Name:</th>
					<th>Job Position:</th>
					<th>Status</th>
					<th>Resume</th>
					<th>Schedule Interview</th>
				</tr>
				<c:forEach var="candidate" items="${shortListedCandidates}">
					<tr>
						<td>${candidate.candidateId}</td>
						<td><a href="/candidate/getCandidate/${candidate.candidateId}">${candidate.candidateName}</a></td>
						<td>${candidate.appliedPosition}</td>
						<td>${candidate.candidateResult}</td>
						<td>${candidate.candidateResume}</td>
						<td><a
							href="/scheduleInterview/saveInterview/${hrId}/${candidate.mangerId}/${candidate.candidateId}">Click
								here...
								</a></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
