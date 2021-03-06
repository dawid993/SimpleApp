<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/css/main.css" />">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<title>Person Viewer</title>
</head>
<body>
	<c:url value="/app/persons/1" var="firstPage" />
	<c:url value="/app/persons/${currentPage-1}" var="prePage" />
	<c:url value="/app/persons/${currentPage+1}" var="nextPage" />
	<c:url value="/app/persons/${totalPages}" var="lastPage" />
	
	<h1>Persons</h1>
	<h2>Basic information</h2>
	<div id="paging" class="pagination-div">
		<ul class="pagination">
			<c:choose>
				<c:when test="${currentPage gt 1}">
					<li><a href="${firstPage}">&lt;&lt;</a></li>
					<li><a href="${prePage}">&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a href="#">&lt;&lt;</a></li>
					<li class="disabled"><a href="#">&lt;</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${begin}" end="${end}">
				<c:url var="pageUrl" value="/app/persons/${i}" />			
				<c:choose>
					<c:when test="${currentPage eq i}">
						<li class="active"><a href="${pageUrl}">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentPage lt totalPages}">
					<li><a href="${nextPage}">&gt;</a></li>
					<li><a href="${lastPage}">&gt;&gt;</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a href="#">&gt;</a></li>
					<li class="disabled"><a href="#">&gt;&gt;</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
	<div id="person-list" class="person-div">
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>City</th>
					<th>Born Date</th>
					<th>VIP</th>
					<th>Phones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons}" var="person">
					<tr>
						<td align="center">${person.name}</td>
						<td align="center">${person.surname}</td>
						<td align="center">${person.city}</td>
						<td align="center">${person.bornDate}</td>
						<td align="center"><c:if test="${person.vipStatus == true }">
								<input type="checkbox" name="vipsStatus" checked
									disabled="disabled" />
							</c:if> <c:if test="${person.vipStatus == false }">
								<input type="checkbox" name="vipsStatus" disabled="disabled" />
							</c:if></td>
						<td>
							<button class="showbutton btn btn-default" id="${person.id}">Show</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="phonediv" style="padding-top: 10px"></div>
	<div id="phoneAddDiv" title="Add new phone" class="phone-dialog-form">
		<form name="phoneform" class="dialog-form">
			<label>Type</label> 
			<select id="phoneType">
				<c:forEach items="${phoneTypes}" var="type" >
					<option value="${type.id}">${type.phoneType}</option>
				</c:forEach>
			</select> 
			<label>Number</label>
			<input type="text" id="number" onchange="validNumber()"/>
		</form>
		<button id="phone-add-submit-button" class="btn btn-default" disabled="disabled">Add</button>
	</div>

	<script>
		var LAST_CLICKED_BUTTON = -1;
		var DIALOG;

		$(function() {
			validNumber();
			$("button.showbutton")
					.click(
							function() {
								var id = $(this).attr("id");
								var dataForRequest = "id=" + id;
								if (id == LAST_CLICKED_BUTTON) {
									$("#phonediv").slideUp();
									LAST_CLICKED_BUTTON = -1;
									return;
								}
								LAST_CLICKED_BUTTON = id;
								$
										.ajax({
											url : "${pageContext.request.contextPath}/app/personphones",
											type : "GET",
											data : dataForRequest,
											async : false,
											success : function(response) {
												generateAJAXHTML(response);
											}
										})
							})

			$("#phone-add-submit-button")
					.click(
							function() {
								$
										.ajax({
											url : "${pageContext.request.contextPath}/app/addPhone",
											type : "POST",
											data : "type=" + $("#phoneType").val()
													+ "&number="
													+ encodeURIComponent($("#number").val())
													+ "&id="
													+ LAST_CLICKED_BUTTON,
											success : function(response) {
												generateAJAXHTML(response);
												$("#type").val("");
												$("#number").val("");
												DIALOG.dialog("close");
											}
										})
							})
							
			DIALOG = $("#phoneAddDiv").dialog({
				autoOpen : false,
				modal : true,
			})
		})

		function showDialog() {
			DIALOG.find("#number").val("");
			DIALOG.find("#number").css("border-bottom-color","red");
			DIALOG.find("#phone-add-submit-button").prop("disabled",true);
			DIALOG.dialog("open");
		}

		function generateAJAXHTML(response) {
			var htmlGenerate = "<h2>Phones</h2><table class=\"table\" <tr><th>Type</th><th>Number</th></tr>";
			for (i = 0; i < response.length; i++) {
				htmlGenerate += "<tr>";
				htmlGenerate += "<td>" + response[i].phoneType.phoneType + "</td>";
				htmlGenerate += "<td>" + response[i].number + "</td>";
				htmlGenerate += "</tr>";
			}
			htmlGenerate += "<tr><td colspan=\"2\" align=\"center\"><button id=\"phoneFormButton\" class=\"btn btn-default\" onclick=\"showDialog()\">Add new phone</button></td></tr>"
			htmlGenerate += "</table>";
			$("#phonediv").html(htmlGenerate);
			$("#phonediv").slideDown();
		}
		
		function validNumber()
		{
			var number = $("#number");
			number.val(number.val().replace(/\s/g,""));
			
			var regex = new RegExp("^(\\(\\+\\d{2}\\))?(\\d{3})(\\d{4}|\\d{6})$");
			if(regex.test(number.val())){
				number.css("border-bottom-color","green");
				$("#phone-add-submit-button").prop("disabled",false);
				
			}
			else{
				number.css("border-bottom-color","red");
				$("#phone-add-submit-button").prop("disabled",true);
			}
		}		
	</script>
</body>
</html>