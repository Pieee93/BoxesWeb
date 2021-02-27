<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




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
    <sql:query var="boxes" dataSource="jdbc/boxesweb">
        SELECT DISTINCT box.tipoDiBox FROM box
    </sql:query>
    
        <header class="w3-container w3-theme w3-padding" id="myHeader">
        <div class="w3-bar w3-theme ">
        <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-right">Carrello</a>
        <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-right">Login</a>
        <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-right">Contattaci</a>   
        </div>
        <div class="w3-center">
            <a href="#" style="text-decoration: none">
            <h1 class="w3-xxxlarge w3-animate-bottom">B<img src="<c:url value='/img/logo/logo.png'/>" alt="img" height="80" width="80">xes</h1>
            </a>
        </div>
       </header>
     <div class="w3-row-padding w3-center w3-margin-top">
         <c:forEach var="box" items="${boxes.rows}">
             <a href="box?${box.tipoDiBox}">
        <form>
        <div class="w3-third w3-button"  >
            <div class="w3-card w3-container" style="min-height:360px">
                <h3>Fascia ${box.tipoDiBox}</h3><br>
                <img src="${initParam.imgboxPath}${box.tipoDiBox}.png" alt="img" height="100" width="100">
                <p>${initParam.imgboxPath}${box.tipoDiBox}.png</p>
                <p>Che la sfida abbia inizio!</p>
            </div>
        </div>
        </form>
           </a> 
        </c:forEach>   
     </div>     
        
</html>
