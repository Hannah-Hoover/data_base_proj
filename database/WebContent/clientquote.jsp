<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quote request form</title>
</head>
<body>
 <center>	<h1> Welcome to quote request page </h1> </center>
	<div align="center">
		<form action="request" method="post">
			<table border="1" cellpadding="5">
                <tr>
                    <th>Location of Tree:</th>
                    <td colspan="3">
                        <input type="text" name="location" size="45" placeholder="Location of Tree" required>
                    </td>
                </tr>
                 <tr>
                    <th>Height of Tree:</th>
                    <td colspan="3">
                        <input type="text" name="height" size="45" placeholder="Height of Tree" required>
                    </td>
                </tr>
                 <tr>
                    <th>Proximity to house:</th>
                    <td colspan="3">
                        <input type="text" name="proximity" size="45" placeholder="Proximity" required>
                    </td>
                </tr>
                  <tr>
                    <th>Diameter of Tree:</th>
                    <td colspan="3">
                        <input type="text" name="sizeDiameter${i}" size="45" placeholder="Diameter of Tree" required>
                    </td>
                 <tr>
                    <th>Tree Photo 1:</th>
                    <td colspan="3">
                        <input type="text" name="photodata1" size="45" placeholder="Tree photo 1" required>
                    </td>
                </tr>
                   <tr>
                    <th>Tree Photo 2:</th>
                    <td colspan="3">
                        <input type="text" name="photodata2" size="45" placeholder="Tree photo 2" required>
                    </td>
                </tr>
                   <tr>
                    <th>Tree Photo 3:</th>
                    <td colspan="3">
                        <input type="text" name="photodata3" size="45" placeholder="Tree photo 3" required>
                    </td>
                </tr>
                </tr>
                 <tr>
                    <th>Note:</th>
                    <td colspan="3">
                        <input type="text" name="note" size="45" placeholder="note" required>
                    </td>
                </tr>
                <tr>
					<td align="center" colspan="5">
						<input type="submit" value="Submit quote request"/>
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




