<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Hiring System-Login</title>
</head>
<c:url value="/login" var="loginUrl" />
<body>
	<div class="container">
		<h1 class="text-center m-5">Hiring Dashboard</h1>
		<div class="card d-flex justify-content-center align-items-center">
		<div class="card-body col-sm-6" >
			<c:if test="${param.error != null}">
                <p class="text-danger">Invalid User name and password.</p>
            </c:if>
            <c:if test="${param.logout != null}">
            	<p class="text-danger">You have been logged out.</p>
            </c:if>
			<form action="${loginUrl}" method="post">
				<h2 class="product-info text-center">Login</h2>
				<div class="mb-3">
				    <label class="form-label" for="username">Username</label>
				    <input type="text" class="form-control" id="username" name="username" />
				 </div>
				 <div class="mb-3">
			        <label class="form-label" for="password">Password</label>
				    <input type="password" class="form-control" id="password" name="password" />
                 </div>
				<button type="submit" class="btn btn-primary">Log in</button>
			</form>
			<div class="mt-3">
            	<a href="/employee/createRole">Create Role</a>
            	<a href="/employee/save">Create Employee</a>
            </div>
			</div>
		</div>
	</div>
</body>