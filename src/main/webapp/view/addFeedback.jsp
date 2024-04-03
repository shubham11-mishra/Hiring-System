<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Hiring System-Add Feedback</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="../resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Add Feedback</h1>
		<form action="/comments/createComment/${candidateId}/${panelId}" method="post" modelAttribute="comment" enctype="multipart/form-data">
		    <div class="form-group">
            <div class="form-floating">
                 <textarea class="form-control" type="text" placeholder="Leave a comment here" name="comment"  id="floatingTextarea2" style="height: 100px"></textarea>
                 <label for="floatingTextarea2">Comments</label>
            </div>
			</div>
			<div class="form-group">
			<label for="result">Result:</label>
			<select name="result">
            	<option value="L1Selected">L1Selected</option>
            	<option value="L1Rejected">L1Rejected</option>
            	<option value="L2Selected">L2Selected</option>
            	<option value="L2Rejected">L2Rejected</option>
            </select>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>