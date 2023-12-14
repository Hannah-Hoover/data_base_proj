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
<body>
    <input type="name" name="quoteID" value="${res.quoteID}" />
    <center>
        <h1>Welcome David! You have been successfully logged in</h1>
    </center>
    <center>
        <a href="login.jsp" target="_self">Logout</a><br><br>
        <a href="contractorquote.jsp">Create quote</a><br><br>
        <a href="listquote">List Quotes</a><br><br>
        <a href="listorders">List Orders of work</a><br><br>
        <a href="listbills">List Bills</a><br><br>

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
                    <!-- Populate Quote table rows here -->
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
                            <c:if test="${quote.editable}">
                                <a href="updatequotecontractor?quoteID=${quote.quoteID}&status=quit" target="_self">Quit</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        
        <table border="1" cellpadding="5">
            <caption><h2>List of Orders</h2></caption>
            <tr>
                <th>quoteID</th>
                <th>Price</th>
                <th>startTime</th>
                <th>endTime</th>
                <th>status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="order" items="${listOrders}">
                <tr style="text-align:center">
                    <td><c:out value="${order.quoteID}" /></td>
                    <td><c:out value="${order.price}" /></td>
                    <td><c:out value="${order.schedulestart}" /></td>
                    <td><c:out value="${order.scheduleend}" /></td>
                    <td><c:out value="${order.status}" /></td>
                    <td>
                        <c:if test="${order.status eq 'incomplete'}">
                            <a href="updateorderstatus?orderID=${order.orderID}&status=complete" target="_self">Complete</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>


	        <table border="1" cellpadding="5">
            <caption><h2>List of Bills</h2></caption>
            <tr>
            	<th>BillID</th>
                <th>orderID</th>
                <th>Generated</th>
                <th>Accepted</th>
                <th>Price</th>
                <th>Discount</th>
                <th>Balance</th>
                <th>status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="bill" items="${listBills}">
                <tr style="text-align:center">
              		<td><c:out value="${bill.billID}" /></td>
                    <td><c:out value="${bill.orderID}" /></td>
                    <td><c:out value="${bill.current}" /></td>
                    <td><c:out value="${bill.accepted}" /></td>
                    <td><c:out value="${bill.price}" /></td>
                    <td><c:out value="${bill.discount}" /></td>
                    <td><c:out value="${bill.balance}" /></td>
                    <td><c:out value="${bill.status}" /></td>
                    <td>
                        <c:if test="${bill.status eq 'pending'}">
                            <a href="updatebillstatus?billID=${bill.billID}&status=accepted" target="_self">Accepted</a>
                            <a href="contractorresponsebill?billID=${bill.billID}">Respond</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>
</body>
</html>
