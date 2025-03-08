<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.eshopping.DAO.ProductDAOImpl"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 20px auto;
        }
        .header {
            text-align: center;
            padding: 20px;
            background-color: #4CAF50;
            color: white;
            border-radius: 8px;
        }
        .search-form {
            text-align: center;
            margin: 20px 0;
        }
        .search-form input[type="search"] {
            padding: 10px;
            width: 50%;
            margin-right: 10px;
            border: 2px solid #ccc;
            border-radius: 5px;
        }
        .search-form button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .search-form button:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        table, th, td {
            border: 2px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        td {
            background-color: #f9f9f9;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 6px 12px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background-color: #45a049;
        }
        .update-fields {
            display: none;
            margin-top: 10px;
            background-color: #f0f0f0;
            padding: 10px;
            border-radius: 5px;
        }
        .update-fields input {
            padding: 6px;
            margin-bottom: 10px;
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .update-fields button {
            background-color: #2196F3;
        }
    </style>
    <script>
        // JavaScript function to toggle visibility of the update fields
        function toggleUpdateFields(productId) {
            var updateFields = document.getElementById("updateFields-" + productId);
            var button = document.getElementById("updateButton-" + productId);
            if (updateFields.style.display === "none") {
                updateFields.style.display = "block";
                button.innerHTML = "Hide Update Fields";
            } else {
                updateFields.style.display = "none";
                button.innerHTML = "Update";
            }
        }
    </script>
</head>
<body>

<%
ProductDAO productDAO = new ProductDAOImpl();
List<ProductDetails> allProductDetails = productDAO.getAllProductDetails();
%>

<div class="container">
    <div class="header">
        <h1>Product Management</h1>
    </div>

    <div class="search-form">
        <form action="">
            <input type="search" placeholder="Search By Name, Brand, Category">
            <button>Search</button>
        </form>
    </div>

    <div class="search-form">
        <a href="AddProducts.html"><button>Add Products</button></a>
    </div>

    <table>
        <tr>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Product Brand</th>
            <th>Product Discount</th>
            <th>Product Category</th>
            <th>Product Quantity</th>
            <th>Action</th>
        </tr>

        <% for(ProductDetails productDetails : allProductDetails) { %>
            <tr>
                <td><%= productDetails.getName() %></td>
                <td><%= productDetails.getPrice() %></td>
                <td><%= productDetails.getBrand() %></td>
                <td><%= productDetails.getDiscount() %></td>
                <td><%= productDetails.getCategory() %></td>
                <td><%= productDetails.getQuantity() %></td>
                <td>
                    <!-- Button to toggle the visibility of update fields -->
                    <button id="updateButton-<%= productDetails.getId() %>" onclick="toggleUpdateFields(<%= productDetails.getId() %>)">Update</button>

                    <!-- Hidden input fields initially -->
                    <div id="updateFields-<%= productDetails.getId() %>" class="update-fields">
                        <form action="updateproductdetails" method="get">
                            <input placeholder="Enter the new price" name="price" type="text">
                            <input placeholder="Enter the new discount" name="discount" type="text">
                            <input placeholder="Enter the new quantity" name="quantity" type="text">
                            <input type="hidden" name="productId" value="<%= productDetails.getId() %>">
                            <button type="submit">Update</button>
                        </form>
                    </div>
                </td>
                <td>
                    <form method="post" action="DeleteProductServlet">
                        <input type="hidden" name="productId" value="<%= productDetails.getId() %>">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        <% } %>
    </table>
</div>

</body>
</html>
