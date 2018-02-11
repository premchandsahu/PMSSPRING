
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<style type="text/css">
  <%@include file="mystyle1.css" %>
</style>

<div id="div2">

<p id="company">third(i)</p>
<p id="slogan">Information. Intelligence. Insight. </p>

<button id="logout" style="float:right" onclick="${username}">Logout</button> 
</div><br>
<div>
<p id ="pms">Performance Management System</p>
</div>

List of Subordinate's form to Review
<body>

<div class="container">
<table class="table table-hover">
<thead>
<tr>
		<th>Sr No.</th>
		<th>Emp Id</th>
		<th>Emp Name</th>
		<th>Designation</th>
		<th>Current Phase</th>
		<th>link</th>
</tr>
</thead>
<c:forEach var="a" items="${sublist}">
<tr>
<td></td>
<td>${a.emp_number}</td>
<td>${a.emp_name}</td>
<td>${a.job_title}</td>
<td>${a.apprphase}</td>
<c:if test="${a.curr_phase_id lt a.phaseid}">
<td>Edit</td>
</c:if>
<c:if test="${a.curr_phase_id eq a.phaseid}">
<td><a href=AppraiseServlet/${a.phaseid}/${a.apprempid}?message=>Review</a></td>
</c:if>
<c:if test="${a.curr_phase_id gt a.phaseid}">
<td><a href=AppraiseServlet/${a.phaseid}/${a.apprempid}?message=>View</a></td>
</c:if>
</tr>
</c:forEach>

</table>
</div>
</html>