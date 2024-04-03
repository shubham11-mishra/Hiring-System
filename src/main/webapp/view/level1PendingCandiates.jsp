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
  <title>Hiring System-Level One Candidates</title>
</head>
<body>
	<h1>Level One Pending Candidates</h1>
	<h3>List of Candidates</h3>
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
					<th>Sr No.</th>
					<th>Name</th>
					<th>Applied Position</th>
					<th>Result</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${levelOneCandidatesList}" var="candidate" varStatus="loop">
					<tr>
						<td>${loop.index + 1}</td>
						<td><a href="/candidate/getCandidate/${candidate.candidateId}">${candidate.candidateName}</a></td>
						<td>${candidate.appliedPosition}</td>
						<td>${candidate.candidateResult}</td>
						<td><a href="/comments/addFeedback/${candidate.candidateId}/${candidate.mangerId}">Add Feedback</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
	</c:choose>
</body>
</html>