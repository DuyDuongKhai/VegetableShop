<%-- 
    Document   : shopping
    Created on : Mar 10, 2022, 3:42:42 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Wish you happy shopping!</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("2")) {
                response.sendRedirect("login.jsp");
            }

        %>
        
        <%    String message = (String) request.getAttribute("SHOPPING_MESSAGE");
            if (message
                    == null) {
                message = "";
            }
        %>
        <h1 class="message"><%=message%></h1></br>
        
                <a href="viewCart.jsp">View Cart</a>
                <a href="MainController?action=Logout">Logout</a>
        <div class="content">
            <%  List<ProductDTO> list = (List<ProductDTO>) session.getAttribute("LIST_PRODUCT");
                if (list != null) {
                    if (!list.isEmpty()) {
            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>CategoryID</th>
                        <th>ImportDate</th>
                        <th>UsingDate</th>
                        <th>Buy</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (ProductDTO product : list) {
                    %>
                <form action="MainController">
                    <tr>
                        <td><%=count++%></td>
                        <td><%=product.getProductID()%></td>
                        <td><%=product.getProductName()%></td>
                        <td><img src="<%=product.getImage()%>" alt="Image" width="100" height="100"></td>
                        <td><%=product.getPrice()%></td>
                        <td><input type="number" min="1"  name="quantity" value="1"/></td>
<!--                        <td><%=product.getQuantity()%></td>-->
                        <td><%=product.getCategoryID()%></td>
                        <td><%=product.getImportDate()%></td>
                        <td><%=product.getUsingDate()%></td>
                       
                        <td>
                            <input type="hidden" name="productID" value="<%=product.getProductID()%>"/>
                            <input type="hidden" name="productName" value="<%=product.getProductName()%>"/>
                            <input type="hidden" name="image" value="<%=product.getImage()%>"/>
                            <input type="hidden" name="price" value="<%=product.getPrice()%>"/>
                            <input type="hidden" name="quantityAvailable" value="<%=product.getQuantity()%>"/>                
                            <input type="hidden" name="categoryID" value="<%=product.getCategoryID()%>"/>
                            <input type="hidden" name="importDate" value="<%=product.getImportDate()%>"/>
                            <input type="hidden" name="usingDate" value="<%=product.getUsingDate()%>"/>
                            <input type="submit"  name="action" value="Add to cart"/>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>   
            <%
                    }
                }
            %> 
        </div>
    </body>
</html>
