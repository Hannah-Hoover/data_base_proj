<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <h1>Received Data from First JSP</h1>
    <%
        String input1 = request.getParameter("response");
      
    %>
    <p>Input 1: <%= input1 %></p>
  
    <!-- Display other form data here -->
</body>
</html>