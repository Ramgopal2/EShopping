<%@page import="java.util.List"%>
<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="com.eshopping.model.CustomerDetails"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Product Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #ff7e5f, #feb47b);
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1 {
            font-size: 2.5em;
            color: #ffffff;
            margin-bottom: 20px;
        }

        h3 {
            font-family: cursive;
            color: #ff7e5f;
        }

        .container {
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            text-align: left;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #ff7e5f;
            color: white;
        }

        button, input[type="submit"] {
            background-color: #ff7e5f;
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #feb47b;
        }

        .cart-button {
            margin-bottom: 20px;
        }

        .cart-button button {
            font-size: 1.2em;
            margin-right: 10px;
        }

        .cart-count {
            font-size: 1.2em;
            color: red;
        }
    </style>
</head>
<body>
<%
    CustomerDetails customerDetails = (CustomerDetails) session.getAttribute("customerdetail");
    Integer cartcount = (Integer) session.getAttribute("cartcount");
    List<ProductDetails> allProductDetails = (List<ProductDetails>) session.getAttribute("allProductDetails");
%>

<div class="container">
    <center>
        <h1>All Product Details</h1>
        <h3><%= customerDetails.getName() %></h3>
        <div class="cart-button">
        <form action="DisplayCartDetails.jsp">
        
            <button>My Cart</button>
            <sup class="cart-count"><%= cartcount %></sup>
            </form>
        </div>

        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Brand</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (ProductDetails productDetails : allProductDetails) { %>
                    <tr>
                        <td><%= productDetails.getName() %></td>
                        <td><%= productDetails.getPrice() %></td>
                        <td><%= productDetails.getBrand() %></td>
                        <td><%= productDetails.getCategory() %></td>
                        <td><%= productDetails.getQuantity() %></td>
                        <td>
                            <form action="addtocart" method="get" style="display:inline-block;">
                                <input type="hidden" value="<%= productDetails.getId() %>" name="productid">
                                <input type="hidden" value="<%= customerDetails.getId() %>" name="customerid">
                                <input type="submit" value="Add to Cart">
                            </form>
                            <!-- Buy button redirects to productDetails.jsp with product info -->
                            <form action="productDetails.jsp" method="get" style="display:inline-block;">
                                <input type="hidden" value="<%= productDetails.getId() %>" name="productid">
                                <input type="hidden" value="<%= productDetails.getPrice() %>" name="price">
                                <input type="hidden" value="<%= productDetails.getDiscount() %>" name="discount">
                                <input type="hidden" value="<%= productDetails.getName() %>" name="name">
                                <input type="hidden" value="<%= productDetails.getBrand() %>" name="brand">
                                <input type="hidden" value="<%= productDetails.getCategory() %>" name="category">
                                <input type="submit" value="Buy">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </center>
</div>
</body>
</html>
