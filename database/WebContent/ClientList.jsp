<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Clients</h2></caption>
            <tr>
				<th>Client ID</th>
                <t>Password</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Credit Card</th>

            </tr>
            <c:forEach var="client" items="${listClient}">
                <tr style="text-align:center">
                    <td><c:out value="${client.clientID}" /></td>
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.password}" /></td>
                    <td><c:out value="${client.firstName}" /></td>
                    <td><c:out value="${client.lastName}" /></td>
                    <td><c:out value= "${client.address}" /></td>
                    <td><c:out value="${client.creditcard}" /></td>
                    <td><c:out value="${client.phone}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div> 

</body>
</html>