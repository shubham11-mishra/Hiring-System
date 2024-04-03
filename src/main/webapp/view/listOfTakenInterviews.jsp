<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-List Of Taken Interviews</title></head>
<body>
	<h1>List of Taken Interviews By Panel</h1>
	<c:choose>
		<c:when test="${size==0}">
			<tr>
				<td>No data available</td>
			</tr>
		</c:when>
		<c:otherwise>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Candidate ID</th>
						<th>Name</th>
						<th>Result</th>
						<th>Applied Position</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOfInterviewsTaken}" var="interview">
						<tr>
							<td>${interview.candidateId}</td>
							<td><a href="/candidate/getCandidate/${candidate.candidateId}">${interview.candidateName}</a></td>
							<td>${interview.candidateResult}</td>
							<td>${interview.appliedPosition}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>