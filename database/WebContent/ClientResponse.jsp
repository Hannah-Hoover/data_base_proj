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
		<form action="clientupdate" method="post">
			<table border="1" cellpadding="5">
			<input type="hidden" name="quoteID" value="${res.quoteID}" />
			
                <tr>
                    <th>Previous Note:</th>
                    <td colspan="2"><c:out value="${res.note}"/></td>
				</tr>
                <tr>
                    <th>Note:</th>
                    <td>
                        <input type="text" name="note" size="45" placeholder="note" required>
                    </td>
                    <td align="center" colspan="5">
						<input type="submit" value="Submit response"/>
					</td>
				</tr>
            </table>
            <hr>
        </form>
         <a href="clientactivitypage.jsp" target="_self">Client dashboard</a>
         <a href="login.jsp"target ="_self" > logout</a><br><br> 
</div>
</body>
</html>