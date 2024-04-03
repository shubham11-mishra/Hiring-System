<%@page import="java.sql.ResultSet"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-List Of Requisition</title>
</head>
<body>
	<h1>List Of Requisition</h1>
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
						<th scope="col">Job Profile</th>
						<th scope="col">Job Description</th>
						<th scope="col">Project Name</th>
						<th scope="col">Partner Name</th>
						<th scope="col">status</th>
						<th scope="col">AdditionalCondition</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<c:forEach var="hello" items="${wow}">
					<tr>
						<td>${hello.jobProfile}</td>
						<td>${hello.jobDescription}</td>
						<td>${hello.projectName}</td>
						<td>${hello.partnerName}</td>
						<td>${hello.status}</td>
						<td>${hello.additionalCondition}</td>
						<td><c:choose>
								<c:when test="${hello.status == 'Open'}">
									<form method="post"
										action="/requisition/processAction/${managerId}">
										<input type="hidden" name="requisitionId"
											value="${hello.jobId}">
										<button type="submit" name="action"
											value="Freeze_${hello.jobId}">Freeze</button>
										<button type="submit" name="action"
											value="Close_${hello.jobId}">Close</button>
									</form>
								</c:when>
								<c:when test="${hello.status == 'Freeze'}">
									<form method="post"
										action="/requisition/processAction/${managerId}">
										<input type="hidden" name="requisitionId"
											value="${hello.jobId}">
										<button type="submit" name="action"
											value="Open_${hello.jobId}">Open</button>
										<button type="submit" name="action"
											value="Close_${hello.jobId    }">Close</button>
									</form>
								</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>