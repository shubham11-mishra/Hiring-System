<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidate Details Page</title>
</head>
<body>
    <h2>Candidate Information</h2>
    <p>Candidate Name: ${candidateDetails.candidateName}</p>
    <p>Applied Position: ${candidateDetails.appliedPosition}</p>
    <br />
    <table>
        <tr>
        <c:forEach var="comments" items="${commentsDetails}">
           Current Result :  ${comments.result}
        <br/>
        <br/>
         <p>
                Feedback : ${comments.comment}</p>
        </c:forEach>
        </tr>
    </table>
    <br />
    <p>Final Result: ${candidateDetails.candidateResult}</p>
<br />
</body>
</html>