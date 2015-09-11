<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/css/main.css" />">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

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
					<button class="showbutton" id="${person.id}">Show</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="phonediv" style="padding-top: 10px"></div>
	<div id="phoneAddDiv" title="Add new phone">
		<form name="phoneform" class="dialog-form">
		<label>Type</label>
			<input type="text" id="type"/>
		<label>Number</label>
			<input type="text" id="number"/>			
		</form>
		<button id="phone-add-submit-button">Add</button>
	</div>
	
	<script>	
	var LAST_CLICKED_BUTTON = -1;
	var DIALOG;
	
	$(function(){	
			$("button.showbutton").click(function(){
				var id = $( this).attr("id");
				var dataForRequest = "id="+id;				
				if(id == LAST_CLICKED_BUTTON){
					$("#phonediv").slideUp();
					LAST_CLICKED_BUTTON = -1;
					return;
				}				
				LAST_CLICKED_BUTTON = id;				
				$.ajax({
					url:"${pageContext.request.contextPath}/app/personphones",
					type:"GET",
					data:dataForRequest,
					async:false,				
					success:function(response){
						generateAJAXHTML(response);			
					}					
				})
			})	
			
			$("#phone-add-submit-button").click(function(){				
				$.ajax({
					url:"${pageContext.request.contextPath}/app/addPhone",
					type:"POST",
					data:"type="+$("#type").val()+"&number="+$("#number").val()+"&id="+LAST_CLICKED_BUTTON,
					success:function(response){
						generateAJAXHTML(response);
						$("#type").val("");
						$("#number").val("");
						DIALOG.dialog("close");
					}
				})					
			})
	})
	
	function showDialog(){
		DIALOG.dialog("open");	
	}
	
	function generateAJAXHTML(response)
	{
		var htmlGenerate = "<table class=\"userTable\" <tr><th>Type</th><th>Number</th></tr>";						
		for(i=0;i<response.length;i++){
			htmlGenerate+="<tr>";
			htmlGenerate+="<td>"+response[i].type+"</td>";
			htmlGenerate+="<td>"+response[i].number+"</td>";
			htmlGenerate+="</tr>";
		}						
		htmlGenerate+="<tr><td colspan=\"2\" align=\"center\"><button id=\"phoneFormButton\" onclick=\"showDialog()\">Add new phone</button></td></tr>"
		htmlGenerate+="</table>";
		$("#phonediv").html(htmlGenerate);
		$("#phonediv").slideDown();			
	}
	
	DIALOG = $("#phoneAddDiv").dialog({
	      autoOpen: false,	 
	      modal:true,
	})
	</script>
</body>
</html>