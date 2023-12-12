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
<input type= name="quoteID" value="${res.quoteID}" />
<center><h1>Welcome David! You have been successfully logged in</h1> </center>
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br>
		 <a href="contractorquote.jsp">Create quote</a><br><br> 
		  <a href="listquote">List Quotes</a><br><br> 
		
		     <table border="1" cellpadding="5">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
            	<th>quoteID</th>
            	<th>contractorID</th>
            	<th>clientID</th>
				<th>Price</th>
                <th>startTime</th>
                <th>endTime</th>
                <th>status</th>
                <th>Actions</th>
            
                

            </tr>
            <c:forEach var="quote" items="${listQuote}">
                <tr style="text-align:center">
                	<td><c:out value="${quote.quoteID}" /></td>
                	<td><c:out value="${quote.contractorID}" /></td>
                	<td><c:out value="${quote.clientID}" /></td>
                    <td><c:out value="${quote.price}" /></td>
                    <td><c:out value="${quote.startTime}" /></td>
                    <td><c:out value="${quote.endTime}" /></td>
                    <td><c:out value="${quote.status}" /></td>
           
           <td>
                <c:if test="${quote.status eq 'pending'}">
                    <a href="contractorresponse?quoteID=${quote.quoteID}">Respond</a>
                </c:if>
           </td>
               
                </tr>
            </c:forEach>
		 </center>
	</body>
</html>