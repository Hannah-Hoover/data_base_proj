<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quote Requests</title>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Quote Requests</h2></caption>
            <tr>
				<th>Id</th>
                <th>Location</th>
                <th>Height</th>
                <th>Proximity</th>
                <th>SizeDiameter</th>
                <th>Photodata1</th>
                <th>Photodata2</th>
                <th>Photodata3</th>
                <th>Note</th>

            </tr>
            <c:forEach var="requests" items="${listRequest}">
                <tr style="text-align:center">
                    <td><c:out value="${requests.id}" /></td>
                    <td><c:out value="${requests.location}" /></td>
                    <td><c:out value="${requests.height}" /></td>
                    <td><c:out value= "${requests.proximity}" /></td>
                    <td><c:out value="${requests.sizeDiameter}" /></td>
                    <td><c:out value="${requests.photodata1}" /></td>
                    <td><c:out value="${requests.photodata2}"/></td>
                     <td><c:out value="${requests.photodata3}"/></td>
                    <td><c:out value="${requests.note}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>