<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/css/form-style.css" />">
<body>
	<c:url value="/app/bills/generateBill" var="action"/>
	<form:form method="POST" action="${action}" commandName="powerUsage" class="bill-form">
	<h1>Month Power Usage</h1>
		<label>
			<span>Person</span>
			<select name="selectedPerson">
				<c:forEach items="${persons}" var="person">
					<option value="${person.id}">${person.name.concat(" ").concat(person.surname)}</option>
				</c:forEach>
			</select>
		</label>
		<label>
			<span>Used power(KWh)</span>
			<form:input path="usageKWh"/>
		</label>
		<label>
			<span>From</span>
			<form:input  path="fromDate"/>
		</label>
		<label>
			<span>To</span>
			<form:input path="toDate"/>
		</label>
		<form:button>Add</form:button>
	</form:form>
</body>
</html>