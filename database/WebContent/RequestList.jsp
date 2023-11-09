<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Quote Requests</title>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Requests</h2></caption>
            <tr>
				<th>Location</th>
                <th>Height</th>
                <th>Proximity</th>
                <th>SizeDiameter</th>
                <th>Photodata1</th>
                <th>Photodata2</th>
                <th>Photodata3</th>
                <th>note</th>

            </tr>
            <c:forEach var="request" items="${listRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${request.location}" /></td>
                    <td><c:out value="${request.height}" /></td>
                    <td><c:out value="${request.proximity}" /></td>
                    <td><c:out value= "${request.sizeDiameter}" /></td>
                    <td><c:out value="${request.photodata1}" /></td>
                    <td><c:out value="${request.photodata2}" /></td>
                    <td><c:out value="${request.photodata3}"/></td>
                    <td><c:out value="${request.note}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>