<%-- 
    Document   : test
    Created on : Mar 12, 2022, 6:52:09 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN Page</title>
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">             
                <form action="MainController" method="POST">
                    <input type="text" id="login" class="fadeIn second" name="userID" placeholder="User ID">
                    <input type="password" id="password" class="fadeIn third" name="password" placeholder="Password">
                    <input type="submit" class="fadeIn fourth" value="Login" name="action">
                </form>

                <!-- Remind Passowrd -->
                <div id="formFooter">
                    <a class="underlineHover" href="create.jsp">Create Account</a>
                </div>

            </div>
        </div>   
    </body>
</html>
