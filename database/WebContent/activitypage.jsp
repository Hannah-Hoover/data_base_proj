<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contractor Activity page</title>
</head>

<center><h1>Welcome David! You have been successfully logged in</h1> </center>
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		  <a href="listquotes">List Quotes</a><br><br> 
		  <a href="listrequests">List Quote Requests</a><br><br>
            
		     <table border="1" cellpadding="5">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
            	<th>ID</th>
				<th>Price</th>
                <th>TimeFrame</th>
                <th>Status</th>

            </tr>
            <c:forEach var="quotes" items="${listQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${quotes.price}" /></td>
                    <td><c:out value="${quotes.timeFrame}" /></td>
                    <td><c:out value="${quotes.status}" /></td>
                </tr>
            </c:forEach>
            
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
                    <td><c:out value="${requests.location}" /></td>
                    <td><c:out value="${requests.height}" /></td>
                    <td><c:out value= "${requests.proximity}" /></td>
                    <td><c:out value="${requests.sizeDiameter}" /></td>
                    <td><c:out value="${requests.photodata1}" /></td>
                    <td><c:out value="${requests.photodata2}"/></td>
                     <td><c:out value="${requests.photodata3}"/></td>
                    <td><c:out value="${requests.note}" /></td>
                     <td>
                        <a href="edit?id=<c:out value='${people.id}' />">Quote</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${people.id}' />">Deny</a>                     
                    </td>
                </tr>
            </c:forEach>
		 </center>
	</body>
</html>
