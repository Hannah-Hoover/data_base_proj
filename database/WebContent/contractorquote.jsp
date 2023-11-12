<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quote creation form</title>
</head>
<body>
 <center>	<h1> Welcome to quote creation page </h1> </center>
	<div align="center">
		<form action="quote" method="post">
			<table border="1" cellpadding="5">
			<input type="hidden" name="requestID" value="${req.requestID}" />
			<input type="hidden" name="clientID" value="${req.clientID}" />
                <tr>
                    <th>Price:</th>
                    <td colspan="3">
                        <input type="text" name="price" size="45" placeholder="Price" required>
                    </td>
                </tr>
                 <tr>
                    <th>TimeFrame:</th>
                    <td colspan="3">
                        <input type="text" name="timeFrame" size="45" placeholder="Timeframe" required>
                    </td>
                </tr>
                 <tr>
                    <th>Status:</th>
                    <td colspan="3">
                        <input type="text" name="status" size="45" placeholder="Status" required>
                    </td>
                </tr>
                  <tr>
                    <th>Note:</th>
                    <td colspan="3">
                        <input type="text" name="note" size="45" placeholder="Note" required>
                    </td>
                </tr>
  				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Submit quote"/>
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