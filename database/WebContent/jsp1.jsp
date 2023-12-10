<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Quote request form</title>
    <script>
        function addTrees() {
            var numTrees = document.getElementById("numTrees").value;
            var container = document.getElementById("dynamicForm");

            container.innerHTML = "";

            for (var i = 0; i < numTrees; i++) {
                var treeContainer = document.createElement("div");
                treeContainer.className = "tree-container";

                var locationLabel = document.createElement("label");
                locationLabel.textContent = "Tree Location:";
                treeContainer.appendChild(locationLabel);

                var locationInput = document.createElement("input");
                locationInput.type = "text";
                locationInput.name = "treeLocation" + (i + 1);
                treeContainer.appendChild(locationInput);

                container.appendChild(treeContainer);

                var heightContainer = document.createElement("div");
                var heightLabel = document.createElement("label");
                heightLabel.textContent = "Tree Height:";
                heightContainer.appendChild(heightLabel);

                var heightInput = document.createElement("input");
                heightInput.type = "text";
                heightInput.name = "treeHeight" + (i + 1);
                heightContainer.appendChild(heightInput);

                container.appendChild(heightContainer);

                var proximityContainer = document.createElement("div");
                var proximityLabel = document.createElement("label");
                proximityLabel.textContent = "Tree Proximity:";
                proximityContainer.appendChild(proximityLabel);

                var proximityInput = document.createElement("input");
                proximityInput.type = "text";
                proximityInput.name = "treeProximity" + (i + 1);
                proximityContainer.appendChild(proximityInput);

                container.appendChild(proximityContainer);

                var diameterContainer = document.createElement("div");
                var diameterLabel = document.createElement("label");
                diameterLabel.textContent = "Tree Diameter:";
                diameterContainer.appendChild(diameterLabel);

                var diameterInput = document.createElement("input");
                diameterInput.type = "text";
                diameterInput.name = "treeDiameter" + (i + 1);
                diameterContainer.appendChild(diameterInput);

                container.appendChild(diameterContainer);

                container.appendChild(document.createElement("br"));
            }
        }
    </script>
</head>
<body>
    <form action="jsp1.jsp" method="post">
        <h2>Quote Request Form</h2>

        <label for="numTrees">Number of Trees:</label>
        <input type="number" id="numTrees" name="numTrees" min="1" required>
        <input type="button" value="Generate Tree Fields" onclick="addTrees()"><br>

        <div id="dynamicForm"></div>

        <input type="submit" value="Submit">
    </form>

    <a href="clientactivitypage.jsp" target="_self">Client dashboard</a>
    <a href="login.jsp" target="_self">Logout</a><br><br> 
</body>
</html>
