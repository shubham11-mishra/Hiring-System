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
<title>Hiring System-Add Candidate</title>
</head>
<body>
	<form:form action="/candidate/saveCandidate/${hrId}" method="post"
	    modelAttribute="candidateRequest" enctype="multipart/form-data">
		<h1>Add Candidate</h1>
		<label for="candidateName">Candidate Name:</label>
		<input name="candidateName" type="text"	placeholder="Enter Category Name" />
		<label for="requisitionName">Requisition :</label>
        <select name="requisitionName" >
		    <c:forEach var="result" items="${listOfReq}">
			    <option value="${result.jobId}">${result.jobProfile}</option>
		    </c:forEach>
		</select>
		<h4>Select a Resume to upload:</h4>
		<input type="file" name="candidateResume" size="50" />
		<button type="submit">Add Candidate</button>
	</form:form>
</body>
</html>