
<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <%
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null) {
            loginUser = new UserDTO();
        }
        String search = request.getParameter("Search");
        if (search == null) {
            search = "";
        }
    %>
    <body>
        <h1>Your user information:</h1>

        User ID:<%= loginUser.getUserID()%></br>
        Full Name: <%= loginUser.getFullName()%></br>
        Role ID: <%= loginUser.getRoleID()%></br>
        Password: <%= "****"%></br>
        Address: <%= loginUser.getAddress()%><br>
        Birthday: <%= loginUser.getBirthday()%><br>
        Phone: <%= loginUser.getPhone()%><br>
        Email: <%= loginUser.getEmail()%><br>
        Status: <%= loginUser.getStatus()%><br>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>

        <form action="MainController" >
            Search<input type="text" name="Search"  value="<%= search%>" placeholder="input your text"/>
            <input type="submit" name="action"  value="Search"/>
        </form>
        <a href="MainController?action=home">Happy shopping</a></br>
        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
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
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ProductDTO product : listProduct) {
                %>

                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= product.getProductID()%> 
                    </td>
                    <td>
                        <%= product.getProductName()%>"
                    </td>
                    <td><img src="<%=product.getImage()%>" alt="Image" width="100" height="100"></td>
<!--                        <a href="<%= product.getImage()%>" target="_blank">link</a>--> 
                    <td>
                        <%= product.getPrice()%>"
                    </td>
                    <td>
                        <%= product.getQuantity()%>
                    </td>
                    <td>
                        <%= product.getCategoryID()%>
                    </td>
                    <td>
                        <%= product.getImportDate()%>
                    <td>
                        <%= product.getUsingDate()%>" 
                    </td>

                    <%
                        }
                    %>
                </tr>



            </tbody>
        </table>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <%=error%>


        <%
                }
            }
        %>        

    </body>
</html>