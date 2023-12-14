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
		  <a href="listbig">Big Clients</a><br><br> 
      <a href="listeasy">Easy Clients</a><br><br> 
      <a href="listsingle">One Tree Clients</a><br><br> 
      <a href="listprospective">Prospective Clients</a><br><br> 
      <a href="listhighest">Highest Tree</a><br><br> 
      <a href="listoverdue">Overdue Clients</a><br><br> 
      <a href="listbad">Bad Clients</a><br><br> 
      <a href="listgood">Good Clients</a><br><br> 
      <a href="liststats">Statistics</a><br><br>
		
		     <table border="1" cellpadding="5">
            <caption><h2>Big Clients</h2></caption>
            <tr>
            	<th>Client</th>
            </tr>
            <c:forEach var="big" items="${listBig}">
                <tr style="text-align:center">
                	<td><c:out value="${big.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>
      
      <table border="1" cellpadding="5">
            <caption><h2>Easy Clients</h2></caption>
            <tr>
            	<th>Client</th>
            </tr>
            <c:forEach var="easy" items="${listeasy}">
                <tr style="text-align:center">
                	<td><c:out value="${easy.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>

      
      <table border="1" cellpadding="5">
    <caption><h2>One Tree Quotes</h2></caption>
    <tr>
        <th>One Tree Quotes</th>
    </tr>
    <c:forEach var="quoteID" items="${listSingle}">
        <tr style="text-align:center">
            <td><c:out value="${quoteID.quoteID}" /></td>
        </tr>
    </c:forEach>
</table>

       <table border="1" cellpadding="5">
            <caption><h2>Prospective Clients</h2></caption>
            <tr>
            	<th>Client</th>
            </tr>
            <c:forEach var="pro" items="${listProspective}">
                <tr style="text-align:center">
                	<td><c:out value="${pro.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>


 <table border="1" cellpadding="5">
            <caption><h2>Highest Tree</h2></caption>
            <tr>
            	<th>Tree</th>
            </tr>
            <c:forEach var="high" items="${listhighest}">
                <tr style="text-align:center">
                	<td><c:out value="${high.treeId}" /></td>
                </tr>
            </c:forEach>
      </table>

       <table border="1" cellpadding="5">
            <caption><h2>Overdue Clients</h2></caption>
            <tr>
            	<th>Client</th>
            </tr>
            <c:forEach var="over" items="${listoverdue}">
                <tr style="text-align:center">
                	<td><c:out value="${over.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>

 <table border="1" cellpadding="5">
            <caption><h2>Bad Clients</h2></caption>
            <tr>
            	<th>Client</th>
            </tr>
            <c:forEach var="bad" items="${listbad}">
                <tr style="text-align:center">
                	<td><c:out value="${bad.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>


 <table border="1" cellpadding="5">
            <caption><h2>Good Clients</h2></caption>
            <tr>
            	<th>Client</th>
            </tr>
            <c:forEach var="good" items="${listgood}">
                <tr style="text-align:center">
                	<td><c:out value="${good.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>

 <table border="1" cellpadding="5">
            <caption><h2>Easy Clients</h2></caption>
            <tr>
            	<th>Client</th>
              <th>Trees</th>
              <th>Date</th>
              <th>Due</th>
              <th>Paid</th>
            </tr>
            <c:forEach var="easy" items="${listbig}">
                <tr style="text-align:center">
                	<td><c:out value="${easy.clientID}" /></td>
                </tr>
            </c:forEach>
      </table>
      
		 </center>
	</body>
</html>
