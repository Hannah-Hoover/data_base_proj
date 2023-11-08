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
					<th>
						<label for="treeCount">Number of trees in the request:</label>
   			    		<input type="number" id="treeCount" name="treeCount" min="1" required>
   			    		<input type="submit" value="Generate tree forms">
   			    	</th>
				</tr>
			</table>
			<a href="clientactivitypage.jsp" target="_self">Client dashboard</a>
		</form>
	</div>
</body>
</html>

<div align="center" id="formContainer">
    <c:forEach begin="1" end="${treeCount}" var="i">
        <form action="request" method="post">
            <table border="1" cellpadding="5">
                <tr>
                    <th>Location of Tree:</th>
                    <td colspan="3">
                        <input type="text" name="location${i}" size="45" placeholder="Location of Tree" required>
                    </td>
                </tr>
                 <tr>
                    <th>Height of Tree:</th>
                    <td colspan="3">
                        <input type="text" name="height${i}" size="45" placeholder="Height of Tree" required>
                    </td>
                </tr>
                 <tr>
                    <th>Proximity to house:</th>
                    <td colspan="3">
                        <input type="text" name="proximity${i}" size="45" placeholder="Proximity" required>
                    </td>
                </tr>
                 <tr>
                    <th>Tree Photo 1:</th>
                    <td colspan="3">
                        <input type="text" name="photodata1${i}" size="45" placeholder="Tree photo 1" required>
                    </td>
                </tr>
                   <tr>
                    <th>Tree Photo 2:</th>
                    <td colspan="3">
                        <input type="text" name="photodata2${i}" size="45" placeholder="Tree photo 2" required>
                    </td>
                </tr>
                   <tr>
                    <th>Tree Photo 3:</th>
                    <td colspan="3">
                        <input type="text" name="photodata3${i}" size="45" placeholder="Tree photo 3" required>
                    </td>
                </tr>
                   <tr>
                    <th>Diameter of Tree:</th>
                    <td colspan="3">
                        <input type="text" name="diameter${i}" size="45" placeholder="Diameter of Tree" required>
                    </td>
                </tr>
                 <tr>
                    <th>Note:</th>
                    <td colspan="3">
                        <input type="text" name="note${i}" size="45" placeholder="note" required>
                    </td>
                </tr>
            </table>
            <hr>
        </form>
    </c:forEach>
</div>



