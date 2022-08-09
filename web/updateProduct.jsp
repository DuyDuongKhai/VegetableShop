<%-- 
    Document   : updateProduct
    Created on : Mar 12, 2022, 4:23:27 AM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.product.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product Page</title>
    </head>
    <body>
        <h1>Edit Product Here!</h1>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("1")) {
                response.sendRedirect("login.jsp");
            }
            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if (productError == null) {
                productError = new ProductError();
            }
            List<CategoryDTO> list = (List<CategoryDTO>) session.getAttribute("LIST_CATE");
        %>
        <form action="MainController">
            Product ID:  <input type="text" name="productID"  required="" value="<%=request.getParameter("productID")%>" readonly=""></br></br>
            Product Name: <input type="text" name="productName"  required="" value="<%=request.getParameter("productName")%>"></br>
            <%=productError.getProductNameError()%></br>
            Product Image:  <input type="text" name="image"  required="" value="<%=request.getParameter("image")%>"></br></br>
            Product Price:<input type="number" name="price" required="" value="<%=request.getParameter("price")%>"></br>
            <%=productError.getProductPriceError()%></br>
            Product Quantity: <input type="number" name="quantity"  value="<%=request.getParameter("quantity")%>"></br></br>
            
            Product CategoryID:
            <select class="status" name="categoryID">
                <option value="<%=request.getParameter("categoryID")%>" selected=""><%=request.getParameter("categoryID")%></option>
                <%
                    if (list != null) {
                        if (!list.isEmpty()) {
                            for (CategoryDTO category : list) {
                %>
                <option value="<%=category.getCategoryID()%>"><%=category.getCategoryID()%></option>
                <%                                    }
                        }
                    }
                %>
            </select></br></br>

            <input type="hidden" name="importDate" value="<%=request.getParameter("importDate")%>"> </br>  

            <input type="hidden" name="usingDate" value="<%=request.getParameter("usingDate")%>">   </br>
            Status:<select class="status" name="status" >  

                <% if ("True".equals(request.getParameter("status"))) {
                %>
                <option value="available" selected="">Available</option>
                <option value="notAvailable">Non-Available</option>  
                <%
                } else if ("False".equals(request.getParameter("status"))) {
                %>
                <option value="available">Available</option>
                <option value="notAvailable" selected="">Non-Available</option>  
                <%
                } else {
                %>
                <option value="available">Available</option>
                <option value="notAvailable">Non-Available</option> 
                <%
                    }
                %>
            </select></br>


            <input class="button_checkOut" type="submit" name="action" value="ConfirmEdit">
            </body>
            </html>
