<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quote Request form</title>
</head>
<body>
    <h2>Each tree will have it's own form</h2>
    <label for="formCount">Number of trees in request:</label>
    <input type="number" id="formCount" name="formCount" min="1" required>
    <button onclick="generateForms()">Generate Forms</button>
    <div id="formContainer"></div>
    
    <script>
        function generateForms() {
            const formCount = document.getElementById('formCount').value;
            const formContainer = document.getElementById('formContainer');
            formContainer.innerHTML = ''; // Clear previous forms

            for (let i = 1; i <= formCount; i++) {
                const form = document.createElement('form');
                form.action = '/submit';
                form.method = 'post';
                form.enctype = 'multipart/form-data';

                form.innerHTML = `
                    <label for="location${i}">Location of tree:</label>
                    <input type="text" id="location${i}" name="location${i}" required><br><br>
                    
                    
                    <label for="height${i}">Tree height (m):</label>
                    <input type="number" id="height${i}" name="height${i}" required><br><br>

                    <label for="proximity${i}">Proximity to house (m):</label>
                    <input type="number" id="proximity${i}" name="proximity${i}" required><br><br>

                    <label for="diameter${i}">Tree diameter (m):</label>
                    <input type="number" id="diameter${i}" name="diameter${i}" required><br><br>
                    
          
                    <label for="note${i}">Note:</label>
                    <textarea id="note${i}" name="note${i}" rows="4" cols="50" required></textarea><br><br>
                    
          
                    <label for="photo${i}_1">Photo 1:</label>
                    <input type="file" id="photo${i}_1" name="photo${i}_1" accept="image/*" required><br><br>
                    
                    <label for="photo${i}_2">Photo 2:</label>
                    <input type="file" id="photo${i}_2" name="photo${i}_2" accept="image/*" required><br><br>
                    
              
                    
                    <hr>
                `;
                formContainer.appendChild(form);
            }
        }
    </script>
</body>
</html>
