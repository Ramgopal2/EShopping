<%@page import="java.util.List"%>
<%@page import="com.eshopping.model.ProductDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<script type="text/javascript">
    function updatePriceAndDiscount(quantity, price, discount) {
        // Update the price and discount based on quantity
        document.getElementById("totalPrice").innerHTML = "Total Price: " + (price * quantity);
        document.getElementById("totalDiscount").innerHTML = "Total Discount: " + (discount * quantity);
    }

    function showProductDetails(productId) {
        // Display the product details and allow the user to modify the quantity
        var productDetailsRow = document.getElementById('product-' + productId);
        var name = productDetailsRow.getAttribute('data-name');
        var brand = productDetailsRow.getAttribute('data-brand');
        var price = parseFloat(productDetailsRow.getAttribute('data-price'));
        var discount = parseFloat(productDetailsRow.getAttribute('data-discount'));

        document.getElementById("productName").value = name;
        document.getElementById("productBrand").value = brand;
        document.getElementById("productPrice").value = price;
        document.getElementById("productDiscount").value = discount;
        document.getElementById("productQuantity").value = 1; // Default quantity
        updatePriceAndDiscount(1, price, discount); // Update total price and discount
        document.getElementById("productId").value = productId; // Set the product ID
    }
</script>
</head>
<body>
<form action="/buyproducts" method="get">
    <h3>Product List</h3>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Brand</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Discount</th>
            <th>Action</th>
        </tr>

        <%
        List<ProductDetails> allProductDetails = (List<ProductDetails>) session.getAttribute("allProductDetails");
        for (ProductDetails productDetails : allProductDetails) {
        %>
        <tr id="product-<%= productDetails.getId() %>" 
            data-name="<%= productDetails.getName() %>" 
            data-brand="<%= productDetails.getBrand() %>" 
            data-price="<%= productDetails.getPrice() %>" 
            data-discount="<%= productDetails.getDiscount() %>">
            <td><%= productDetails.getName() %></td>
            <td><%= productDetails.getBrand() %></td>
            <td><%= productDetails.getPrice() %></td>
            <td><%= productDetails.getQuantity() %></td>
            <td><%= productDetails.getDiscount() %></td>
            <td><button type="button" onclick="showProductDetails(<%= productDetails.getId() %>)">View</button></td>
        </tr>
        <% } %>
    </table>

    <h3>Product Details</h3>
    <label for="productName">Name:</label>
    <input type="text" id="productName" name="name" readonly><br><br>
    <label for="productBrand">Brand:</label>
    <input type="text" id="productBrand" name="brand" readonly><br><br>
    <label for="productPrice">Price:</label>
    <input type="text" id="productPrice" name="price" readonly><br><br>
    <label for="productQuantity">Quantity:</label>
    <input type="number" id="productQuantity" name="quantity" value="1" oninput="updatePriceAndDiscount(this.value, parseFloat(document.getElementById('productPrice').value), parseFloat(document.getElementById('productDiscount').value))"><br><br>
    <label for="productDiscount">Discount:</label>
    <input type="text" id="productDiscount" name="discount" readonly><br><br>

    <p id="totalPrice">Total Price: 0</p>
    <p id="totalDiscount">Total Discount: 0</p>

    <input type="hidden" id="productId" name="productid">
    <button type="submit">Buy</button>
</form>
</body>
</html>
