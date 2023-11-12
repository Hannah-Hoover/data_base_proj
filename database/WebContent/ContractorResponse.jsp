<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<form action="contractorupdate" method="post">
			<table border="1" cellpadding="5">
			<input type="hidden" name="quoteID" value="${res.quoteID}" />
                <tr>
                    <th>Price:</th>
                    <td colspan="3">
                        <input type="text" name="price" size="45" placeholder="Price" ${res.editable?"":"disabled"} required value="${res.price }">
                    </td>
                </tr>
                 <tr>
                    <th>TimeFrame:</th>
                    <td colspan="3">
                        <input type="text" name="timeFrame" size="45" placeholder="Timeframe" ${res.editable?"":"disabled"} required value="${res.timeFrame }">
                    </td>
                </tr>
                 <tr>
                    <th>Status:</th>
                    <td colspan="3">
                        <input type="text" name="status" size="45" placeholder="Status" ${res.editable?"":"disabled"} required value="${res.status }">
                    </td>
                </tr>
                <tr>
                    <th>Previous Note:</th>
                    <td colspan="2"><c:out value="${res.note}"/></td>
				</tr>
                <tr>
                    <th>Note:</th>
                    <td>
                        <input type="text" name="note" size="45" placeholder="note" required ${res.editable?"":"disabled"}>
                    </td>
                    <td align="center" colspan="5">
                    	<c:if test="${res.editable }">
							<input type="submit" value="Submit response"/>
						</c:if>
						<c:if test="${res.editable eq false }">
							<a href="contractorpage">Back to Requests</a>
						</c:if>
					</td>
				</tr>
            </table>
            <hr>
        </form>
         <a href="activitypage.jsp" target="_self">Contractor dashboard</a>
         <a href="login.jsp"target ="_self" > logout</a><br><br> 
</div>
</body>
</html>