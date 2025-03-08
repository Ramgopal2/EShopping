<%@page import="com.eshopping.model.CustomerDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Cart</title>
    <style>
        /* Add your styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }
        
        .cart-table {
            width: 100%;
            border-collapse: collapse;
        }

        .cart-table th, .cart-table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        .cart-table th {
            background-color: #f8a5c2;
            color: white;
        }

        .cart-actions button {
            background-color: #f8a5c2;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
        }
        .cart-actions button:hover {
            background-color: #ff9bbf;
        }
        .total {
            font-size: 1.5em;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<form action="/mycart">
<%
    // Get the cart from the session
    List<ProductDetails> cart = (List<ProductDetails>) session.getAttribute("cart");
	CustomerDetails customerDetails = new CustomerDetails();
    DecimalFormat df = new DecimalFormat("###.##");
    double totalAmount = 0.0;
    if (cart == null || cart.isEmpty()) {
%>
    <h2>Your Cart is Empty</h2>
<%
    } else {
%>
    <h2>Your Cart</h2>
    <table class="cart-table">
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Brand</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Discount</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <% 
                for (ProductDetails product : cart) {
                    double totalProductPrice = product.getPrice() * 1 - (product.getDiscount() / 100);
                    totalAmount += totalProductPrice;
            %>
                <tr>
                    <td><%= product.getName() %></td>
                    <td><%= product.getBrand() %></td>
                    <td><%= product.getPrice() %></td>
                    
                    <td>1</td> <!-- Assuming quantity is always 1 for now -->
                    <td><%= product.getDiscount() %>%</td>
                    <td><%= df.format(totalProductPrice) %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <div class="total">
        <strong>Total Amount: </strong><%= df.format(totalAmount) %>
    </div>
    <div class="cart-actions">
    <input type="hidden" value=<%= customerDetails.getId()%> name="customerid">
                                
        <button>Proceed to Checkout</button>
        <button>Clear Cart</button> <!-- Optional, implement logic to clear the cart -->
    </div>
<%
    }
%>
</form>
</body>
</html>
