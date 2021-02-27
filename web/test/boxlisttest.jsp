<%-- 
    Document   : BoxList
    Created on : 16-dic-2020, 17.41.00
    Author     : pc
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/boxes.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
        <title>Boxes</title>
    </head>
    <sql:query var="box" dataSource="jdbc/boxesweb">
        SELECT * FROM boxesweb.box 
        WHERE box.tipoDiBox='base'
    </sql:query>   

    <body>


        <div class="w3-row-padding w3-center w3-margin-top">
            <c:forEach var="box" items="${box.rows}">
                <a href ="BoxList.jsp">
                    <form  >

                        <div class="w3-third w3-button"  >
                            <div class="w3-card w3-container" style="min-height:360px">
                                <h3>${box.nomeBox}</h3><br>
                                <img src="${initParam.imgboxPath}boxbase.png" alt="img" height="100" width="100">
                                <p>${box.descrizioneBox}</p>
                                <p>Che la sfida abbia inizio!</p>
                            </div>
                        </div>
                    </form >
                </a>
            </c:forEach>
        </div>
        <!-- Pagination -->
        <div class="w3-center w3-padding-32">
            <div class="w3-bar">
                <a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>
                <a href="#" class="w3-bar-item w3-black w3-button">1</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>
            </div>
        </div>

        <hr id="about">

    </body>
</html>
