<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="by.bsuir.project.resource.MessageManager" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/main.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">  
    </head>
<body>     
    <div class="container">                   
        <div class="jumbotron">         
            <p class="lead">
            <form action="admin" method="POST">
                <input type="hidden" name="command" value="adminLogin"/>    
                Username:<input type="text" name="login"><br>
                Password: <input type="password" name="password"><br><br>
                <input type="submit" value="Login">
            </form>     
        </div>
        <div class="footer">
          <p>© App4Epam 2014</p>
        </div>  
   </div>     
</body>
   