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
	 	 <a href="clientquote.jsp"target ="_self" > quote request</a><br><br> 
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
	<%-- 	 
         <table border="1" cellpadding="5">  
            <caption><h2>List of Requests</h2></caption>
            <tr>
				<th>Email</th>
                <th>Request</th>
                <th>Note</th>
                <th>Address</th>
                <th>Password</th>
                <th>Birthday</th>
                <th>cash_bal($)</th>
                <th>PPS_bal</th>

            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value="${users.cash_bal}"/></td>
                    <td><c:out value="${users.PPS_bal}" /></td>
                </tr>
            </c:forEach>
            --%>
            
            
		     <table border="1" cellpadding="5">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
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
		 
		 </center>
	</body>
</html>