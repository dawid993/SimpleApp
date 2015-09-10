<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/css/main.css" />">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="userTable">
		<tr>
			<th>Name</th>
			<th>Surname</th>
			<th>City</th>
			<th>Born Date</th>
			<th>VIP</th>
			<th>Phones</th>
		</tr>
		<c:forEach items="${persons}" var="person">
			<tr>
				<td align="center">${person.name}</td>
				<td align="center">${person.surname}</td>
				<td align="center">${person.city}</td>
				<td align="center">${person.bornDate}</td>
				<td align="center">
					<c:if test="${person.vipStatus == true }">
						<input type="checkbox" name="vipsStatus" checked disabled="disabled"/>
					</c:if>
					<c:if test="${person.vipStatus == false }">
						<input type="checkbox" name="vipsStatus" disabled="disabled" />
					</c:if>
				</td>
				<td>
					<button id="${person.id}">Show</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="s"></div>
	<script>		
			$("button").click(function(){
				var id = $( this).attr("id");
				var dataForRequest = "id="+id;
				
				$.ajax({
					url:"${pageContext.request.contextPath}/app/personphones",
					type:"GET",
					data:dataForRequest,
					async:false,				
					success:function(response){
						var htmlGenerate = "<table class=\"userTable\" <tr><th>Type</th><th>Number</th></tr>";
						
						for(i=0;i<response.length;i++)
						{
							htmlGenerate+="<tr>";
							htmlGenerate+="<td>"+response[i].type+"</td>";
							htmlGenerate+="<td>"+response[i].number+"</td>";
							htmlGenerate+="</tr>";
						}
						
						htmlGenerate+="</table>";
						$("#s").html(htmlGenerate);
						
					}					
				})
			})		
	</script>
</body>
</html>