<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<% //Integer desiredID = (Integer) request.getSession().getAttribute("clientID"); %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client Activity page</title>
</head>

<center><h1>Welcome Client! You have been successfully logged in</h1> </center>
	<body>
	 <center>
	 	<table border="1" cellpadding="5">
	 	<input type="hidden" name="clientID" value="${req.clientID}" />
	 	<input type="hidden" name="quoteID" value="${res.quoteID}" />
	 	<a href="listquote">List Quotes</a><br><br> 
	 	
	 
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
            <c:forEach var="quotes" items="${listQuote}">
            <c:if test="${quote.clientID eq clientID}">
                <tr style="text-align:center">
                	<td><c:out value="${quote.quoteID}" /></td>
                	<td><c:out value="${quote.contractorID}" /></td>
                	<td><c:out value="${quote.clientID}" /></td>
                    <td><c:out value="${quote.price}" /></td>
                    <td><c:out value="${quote.startTime}" /></td>
                    <td><c:out value="${quote.endTime}" /></td>
                    <td><c:out value="${quote.status}" /></td>
                    <td>
	                    <c:if test="${quote.editable}">
		               	 	<a href="clientresponse?id=${quote.quoteID}""target ="_self" > Respond</a><br/>   
		                	<a href="updatequotestatus?id=${quote.quoteID}&status=agree" target ="_self" > Agree</a><br/>
		                	<a href="updatequotestatus?id=${quote.quoteID}&status=quit" target="_self"> Quit</a>
		                </c:if>
               		</td>
                </tr>
            </c:if>
            
            </c:forEach>
            
 
	 	 <a href="clientquote.jsp"target ="_self" > Quote Request</a><br><br> 
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can show all the transactions or other attributes here like balance, name of the user and others.</p>
		 </center>
	</body>
</html>