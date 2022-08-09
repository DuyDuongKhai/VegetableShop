<%-- 
    Document   : addProduct
    Created on : Mar 11, 2022, 3:40:18 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD PRODUCT Page</title>
    </head>
    <body>
        <h1>ADD NEW PRODUCT!</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("1")) {
                response.sendRedirect("login.jsp");
            }

        %>
        
        <%
            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if (productError == null) {
                productError = new ProductError();
            }
        %>
        <form action="MainController" method="POST">
            Product ID <input type="text" name="productID" required=""/>
            <%= productError.getProductIDError()%><br>
            Product Name <input type="text" name="productName" required=""/>
            <%= productError.getProductNameError()%><br>
            Imgae <input type="text" name="image" required=""/>
            <%= productError.getProductImageError()%><br>
            Price <input type="text" name="price" required=""/>
            <%= productError.getProductPriceError()%><br>
            Quantity <input type="text" name="quantity" required=""/>
            <%= productError.getProductQuantityError()%><br>
            Category ID <input type="text" name="categoryID" required=""/>
            <%= productError.getCategoryError()%><br>
            ImportDate <input type="text" name="importDate" required=""/>
            <%= productError.getImportDateError()%><br>
            UsingDate <input type="text" name="usingDate" required=""/>
            <%= productError.getUsingDateError()%><br>
            Status <input type="text" name="status" required=""/>
            <%= productError.getStatusError()%><br>
            <input class="button" type="submit" name="action" value="Add Products"/>
            <input class="button" type="reset" value="Reset"/>
            <%= productError.getMessageError()%>
        </form>
    </body>
</html>
