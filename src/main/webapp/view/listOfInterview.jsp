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
  <title>Hiring System-List Of Interview</title>
</head>
<body>
	<h1>Interview List</h1>
	<c:choose>
	<c:when test="${size==0}">
		<tr>
			<td>No data available</td>
		</tr>
	</c:when>
	<c:otherwise>
		<table class="table table-striped">
			<tr>
				<th scope="col">Candidate ID</th>
				<th scope="col">Name</th>
				<th scope="col">Applied Position</th>
				<th scope="col">Status</th>
                <th scope="col">Resume</th>
                <th scope="col">Action</th>
            </tr>
			<c:forEach items="${candidateList}" var="candidate">
			<tr>
				<td scope="col">${candidate.candidateId}</td>
				<td scope="col"><a href="/candidate/getCandidate/${candidate.candidateId}">${candidate.candidateName}</a></td>
				<td scope="col">${candidate.candidateResult}</td>
				<td scope="col">${candidate.appliedPosition}</td>
				<td scope="col"><a href="${candidate.candidateResume}">Click Here..</a></td>
				<td><a href="/comments/addFeedback/${candidate.candidateId}/${panelId}">Add	Feedback</a></td>
			</tr>
			</c:forEach>
		</table>
	</c:otherwise>
	</c:choose>
</body>
</html>