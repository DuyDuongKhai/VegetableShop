
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("1")) {
                response.sendRedirect("login.jsp");
            }

        %>
        Welcome: <h1> <%= loginUser.getFullName()%>  </h1>


        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
            <input type="submit" name="action" value="Add Products"/>
        </form>
        <%  List<ProductDTO> list = (List<ProductDTO>) session.getAttribute("LIST_ALL_PRODUCT");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ProductID</th>
                    <th>ProductName</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Speed</th>
                    <th>Status</th>                   
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
                    <td><%=product.getQuantity()%></td>
                    <td><%=product.getCategoryID()%></td>                   
                    <td><%=product.getImportDate()%></td>
                    <td><%=product.getUsingDate()%></td>
                    <td><%=product.getStatus()%></td>

                    <td>
                        <input type="hidden" name="productID" value="<%=product.getProductID()%>"/>
                        <input type="hidden" name="productName" value="<%=product.getProductName()%>"/>
                        <input type="hidden" name="image" value="<%=product.getImage()%>"/>
                        <input type="hidden" name="price" value="<%=product.getPrice()%>"/>
                        <input type="hidden" name="quantity value="<%=product.getQuantity()%>"/>
                        <input type="hidden" name="categoryID" value="<%=product.getCategoryID()%>"/>
                        <input type="hidden" name="importDate" value="<%=product.getImportDate()%>"/>
                        <input type="hidden" name="usingDate" value="<%=product.getUsingDate()%>"/>
                        <input type="hidden" name="status" value="<%=product.getStatus()%>"/>
                        <input type="submit"  name="action" value="Update Products"/>
                    </td>
                    <td>
                        <form action="MainController">
                            <input type="hidden" name="productID" value="<%=product.getProductID()%>"/>
                            <input type="submit"  name="action" value="Remove Products"/>
                        </form>

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





    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%=error%>



</body>
</html>