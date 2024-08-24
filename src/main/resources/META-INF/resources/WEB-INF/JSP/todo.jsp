<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
	<head>
	<Link href="webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
		<title>Add Todos Page</title>		
	</head>
	<body>
	<div class="container">
		<h1>Enter todo details</h1>
		<form method="post">
			Description: <input type="text" name="description"/>
			<input type="submit" class="btn btn-success"/>
		</form>
	</div>
		<script src="webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.7.1/jquery.min.js"></script>
	</body>
</html>