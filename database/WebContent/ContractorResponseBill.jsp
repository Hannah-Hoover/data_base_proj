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
		<form action="updatequote" method="post">
			<table border="1" cellpadding="5">
			<input type="hidden" name="billID"  value="${bil.billID}" />

                <tr>
                    <th>Price:</th>
                    <td colspan="3">
                        <input type="text" name="price" size="45" placeholder="Price" required value="${res.price }">
                    </td>
                </tr>
                 <tr>
                    <th>Start Time:</th>
                    <td colspan="3">
                        <input type="text" name="startTime" size="45" placeholder="Start Time" required value="${res.startTime }">
                    </td>
                </tr>
                 <tr>
                    <th>End Time:</th>
                    <td colspan="3">
                        <input type="text" name="endTime" size="45" placeholder="End Time"required value="${res.endTime }">
                    </td>
                </tr>
                 <tr>
                    <th>Status:</th>
                    <td colspan="3">
                        <input type="text" name="status" size="45" placeholder="Status" required value="${res.status }">
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="5">
						<input type="submit" value="updateQuote"/>
					</td>
				</tr>
            </table>
            <hr>
        </form>

            
        	<form action="billMessage" method="post">

        		<input type="hidden" name="billID"  value="${bil.billID}" />

                 <tr>
                    <th>Note:</th>
                    <td colspan="3">
                       
                        <input type="text" name="note" size="45" placeholder="Message">
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="5">
						<input type="submit" value="billeMessage"/>
					</td>
				</tr>
        </form>
          <a href="activitypage.jsp" target="_self">Contractor dashboard<br></a>
                <a href="login.jsp"target ="_self" > logout</a><br><br> 
                
                
</div>
</body>
</html>