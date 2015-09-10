<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<c:url value="/app/submitForm" var="action"/>
	<form:form method="POST" action="${action}" commandName="person">
		<label>Name</label><br>
		<form:input path="name" /><br>
		<label>Surname</label><br>
		<form:input path="surname" />
		<select name="phones">
			<c:forEach items="${person.phones}" var="phone">
				<option>${phone.number}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>