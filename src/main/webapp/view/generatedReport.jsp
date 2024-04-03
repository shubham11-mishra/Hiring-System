<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Panel Dashboard</title>
</head>
<body>
	<div class="container">
		<h1 class="title">${Job.jobProfile} || ${Job.projectName} Report</h1>
		<h3>From ${generateReport.startDate} to ${generateReport.endDate}</h3>
        <h1></h1>
		<c:if test="${profileAddedCandidates > 0}">
           <h5>Profiles Added :- ${profileAddedCandidates}</h5>
        </c:if>
        <c:if test="${shortlistedCandidates > 0}">
           <h5>Shortlisted Candidates :- ${shortlistedCandidates}</h5>
        </c:if>
        <c:if test="${notShortlistedCandidates > 0}">
           <h5>Not Shortlisted Candidates :- ${notShortlistedCandidates}</h5>
        </c:if>
        <c:if test="${l1ScheduledCandidates > 0}">
           <h5>L1 Scheduled Interview Candidates :- ${l1ScheduledCandidates}</h5>
        </c:if>
        <c:if test="${l1RescheduleCandidates > 0}">
           <h5>L1 Re-Scheduled Interview Candidates :- ${l1RescheduleCandidates}</h5>
        </c:if>
        <c:if test="${l1SelectedCandidates > 0}">
           <h5>L1 Selected Candidates :- ${l1SelectedCandidates}</h5>
        </c:if>
        <c:if test="${l1RejectedCandidates > 0}">
           <h5>L1 Rejected Candidates :- ${l1RejectedCandidates}</h5>
        </c:if>
        <c:if test="${l2ScheduledCandidates > 0}">
            <h5>L2 Scheduled Interview Candidates :- ${l2ScheduledCandidates}</h5>
        </c:if>
        <c:if test="${l2RescheduleCandidates > 0}">
            <h5>L2 Re-Scheduled Interview Candidates :- ${l2RescheduleCandidates}</h5>
        </c:if>
        <c:if test="${l2SelectedCandidates > 0}">
            <h5>L2 Selected Candidates :- ${l2SelectedCandidates}</h5>
        </c:if>
        <c:if test="${l2RejectedCandidates > 0}">
            <h5>L2 Rejected Candidates :- ${l2RejectedCandidates}</h5>
        </c:if>
    </div>
</body>
</html>
