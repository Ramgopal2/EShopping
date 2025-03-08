<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f0f0;
            padding: 20px;
            color: #333;
        }

        h2 {
            color: #ff7e5f;
        }

        .product-details-form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: auto;
        }

        .product-details-form input {
            margin-bottom: 10px;
        }

        .product-details-form p {
            font-size: 1.2em;
        }

        button {
            background-color: #ff7e5f;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #feb47b;
        }
    </style>
    <script type="text/javascript">
        function updatePriceAndDiscount(quantity, price, discount) {
            // Update the price and discount based on quantity
            document.getElementById("totalPrice").innerHTML = "Total Price: " + (price * quantity);
            document.getElementById("totalDiscount").innerHTML = "Total Discount: " + (discount * quantity);
        }
    </script>
</head>
<body>
<%
    String name = request.getParameter("name");
    String brand = request.getParameter("brand");
    float price = Float.parseFloat(request.getParameter("price"));
    float discount = Float.parseFloat(request.getParameter("discount"));
    String productId = request.getParameter("productid");
%>

<div class="product-details-form">
    <h2>Product Details</h2>
    <form action="/buyproducts" method="get">
        <!-- Hidden fields to pass product information -->
        <input type="hidden" name="productid" value="<%= productId %>">
        <input type="hidden" name="name" value="<%= name %>">
        <input type="hidden" name="brand" value="<%= brand %>">
        <input type="hidden" name="price" value="<%= price %>">
        <input type="hidden" name="discount" value="<%= discount %>">
        
        <!-- Display product name, brand, price, and allow quantity change -->
        <label for="productName">Name:</label>
        <input type="text" id="productName" name="name" value="<%= name %>" readonly><br><br>
        
        <label for="productBrand">Brand:</label>
        <input type="text" id="productBrand" name="brand" value="<%= brand %>" readonly><br><br>
        
        <label for="productPrice">Price:</label>
        <input type="text" id="productPrice" name="price" value="<%= price %>" readonly><br><br>
        
        <label for="productQuantity">Quantity:</label>
        <input type="number" id="productQuantity" name="quantity" value="1" min="1" onchange="updatePriceAndDiscount(this.value, <%= price %>, <%= discount %>)"><br><br>
        
        <p id="totalPrice">Total Price: <%= price %></p>
        <p id="totalDiscount">Total Discount: <%= discount %></p>

        <button type="submit">Buy</button>
    </form>
</div>

<script type="text/javascript">
    // Initialize total price and discount
    updatePriceAndDiscount(1, <%= price %>, <%= discount %>);
</script>
</body>
</html>
