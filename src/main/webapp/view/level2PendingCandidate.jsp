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
  <title>Hiring System-Level Two Candidates</title>
</head>
<body>
	<h1>Level two Pending Candidates</h1>
	<c:choose>
	    <c:when test="${size==0}">
			<div>
				<p >No data available</p>
			</div>
		</c:when>
		<c:otherwise>
		<table class="table table-striped">
		<thead>
			<tr>
				<th>Candidate ID</th>
				<th>Name</th>
				<th>Applied Position</th>
				<th>Result</th>
				<th>Add Feedback</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="levelTwoCandidates" items="${levelTwoCandidates}">
			<tr>
			    <td>${levelTwoCandidates.candidateId}</td>
				<td><a href="/candidate/getCandidate/${levelTwoCandidates.candidateId}">${levelTwoCandidates.candidateName}</a></td>
				<td>${levelTwoCandidates.appliedPosition}</td>
				<td>${levelTwoCandidates.candidateResult}</td>
				<td><a href="/comments/addFeedback/${levelTwoCandidates.candidateId}/${levelTwoCandidates.mangerId}">Add Feedback</a></td>
			</tr>
		</c:forEach>
	    </tbody>
    </table>
    </c:otherwise>
    </c:choose>
</body>
</html>
