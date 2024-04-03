<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
 <meta charset="utf-8">
 <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Error Page</title>
<style>
table td {
	vertical-align: top;
	border: solid 1px #888;
	padding: 10px;
}
</style>
</head>
<body>
	<h1>My Error Page</h1>
	<table>
		<tr>
			<td>Date</td>
			<td>${timestamp}</td>
		</tr>
		<tr>
			<td>Error</td>
			<td>${error}</td>
		</tr>
		<tr>
			<td>Status</td>
			<td>${status}</td>
		</tr>
		<tr>
			<td>Message</td>
			<td>${message}</td>
		</tr>
		<tr>
			<td>Exception</td>
			<td>${exception}</td>
		</tr>
		<tr>
			<td>Trace</td>
			<td><pre>${trace}</pre></td>
		</tr>
	</table>
</body>
</html>