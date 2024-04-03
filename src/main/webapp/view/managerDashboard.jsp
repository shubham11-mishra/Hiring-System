<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Manager Dashboard</title></head>
<body>
	<div class="container">
		<h1 class="text-center m-5">Manager Dashboard</h1>
		<div class="card ">
		<h4 class="text-center m-5">Welcome ${manager.employeeName}</h4>
		<div class="row">
			<div class="col-md-4 rounded border p-5 m-4">
				<a
					href="/candidate/getPendingScreenCandidates/${manager.employeeId}">Pending
					Screen Candidates</a>
			</div>
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/candidate/getLevelOneCandidates/${manager.employeeId}">Level
					1 Pending Candidates</a>
			</div>
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/candidate/getLevelTwoCandidates/${manager.employeeId}">Level
					2 Pending Candidates</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/candidate/finalSelectedCandidates/${manager.employeeId}">Final
					Selected Candidates</a>
			</div>
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/requisition/getAllRequisition/${manager.employeeId}">List
					Of Requisition</a>
			</div>
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/requisition/addRequisition/${manager.employeeId}">Add
					Requisition</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/candidate/rejectedCandidateHistory/${manager.employeeId}">Rejected
					Candidates</a>
			</div>
			<div class="col-md-4 rounded border p-5 m-4">
				<a href="/panelDashboard/${manager.employeeId}">Panel Dashboard</a>
			</div>
			<div class="col-md-4 rounded border p-5 m-4">
			    <a href="/candidate/generateReport/${manager.employeeId}">Generate Reports</a>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
