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
                locationInput.name = "location" + (i + 1);
                treeContainer.appendChild(locationInput);

                container.appendChild(treeContainer);

                var heightContainer = document.createElement("div");
                var heightLabel = document.createElement("label");
                heightLabel.textContent = "Tree Height:";
                heightContainer.appendChild(heightLabel);

                var heightInput = document.createElement("input");
                heightInput.type = "text";
                heightInput.name = "height" + (i + 1);
                heightContainer.appendChild(heightInput);

                container.appendChild(heightContainer);

                var proximityContainer = document.createElement("div");
                var proximityLabel = document.createElement("label");
                proximityLabel.textContent = "Tree Proximity:";
                proximityContainer.appendChild(proximityLabel);

                var proximityInput = document.createElement("input");
                proximityInput.type = "text";
                proximityInput.name = "proximity" + (i + 1);
                proximityContainer.appendChild(proximityInput);

                container.appendChild(proximityContainer);

                var diameterContainer = document.createElement("div");
                var diameterLabel = document.createElement("label");
                diameterLabel.textContent = "Tree Diameter:";
                diameterContainer.appendChild(diameterLabel);

                var diameterInput = document.createElement("input");
                diameterInput.type = "text";
                diameterInput.name = "diameter" + (i + 1);
                diameterContainer.appendChild(diameterInput);

                container.appendChild(diameterContainer);
                
                var photo1Container = document.createElement("div");
                var photo1Label = document.createElement("label");
                photo1Label.textContent = "Photo1:";
                photo1Container.appendChild(photo1Label);

                var photo1Input = document.createElement("input");
                photo1Input.type = "text";
                photo1Input.name = "photo1" + (i + 1);
                photo1Container.appendChild(photo1Input);

                container.appendChild(photo1Container);
                
                var photo2Container = document.createElement("div");
                var photo2Label = document.createElement("label");
                photo2Label.textContent = "Photo2:";
                photo2Container.appendChild(photo2Label);

                var photo2Input = document.createElement("input");
                photo2Input.type = "text";
                photo2Input.name = "photo2" + (i + 1);
                photo2Container.appendChild(photo2Input);

                container.appendChild(photo2Container);
                
                var photo3Container = document.createElement("div");
                var photo3Label = document.createElement("label");
                photo3Label.textContent = "Photo3:";
                photo3Container.appendChild(photo3Label);

                var photo3Input = document.createElement("input");
                photo3Input.type = "text";
                photo3Input.name = "photo3" + (i + 1);
                photo3Container.appendChild(photo3Input);

                container.appendChild(photo3Container);

                container.appendChild(document.createElement("br"));
            }
        }
    </script>
</head>
<body>
    <form action="request" method="post">
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
