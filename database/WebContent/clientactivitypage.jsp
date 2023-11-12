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
	 	
	 
		<caption><h2>List of Quotes</h2></caption>
            <tr>
            	<th>ID</th>
				<th>Price</th>
                <th>TimeFrame</th>
                <th>Status</th>
                <th>Latest Note</th>
                <th>Options</th>
                
			
            </tr>
            <c:forEach var="quotes" items="${listQuotes}">
            <c:if test="${quotes.clientID eq clientID}">
                <tr style="text-align:center">
                	<td><c:out value="${quotes.quoteID}" /></td>
                    <td><c:out value="${quotes.price}" /></td>
                    <td><c:out value="${quotes.timeFrame}" /></td>
                    <td><c:out value="${quotes.status}" /></td>
                    <td><c:out value="${quotes.note }"/></td>
                    <td>
	                    <c:if test="${quotes.editable}">
		               	 	<a href="clientresponse?id=${quotes.quoteID}""target ="_self" > Respond</a><br/>   
		                	<a href="updatequotestatus?id=${quotes.quoteID}&status=agree" target ="_self" > Agree</a><br/>
		                	<a href="updatequotestatus?id=${quotes.quoteID}&status=quit" target="_self"> Quit</a>
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