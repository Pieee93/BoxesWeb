<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/boxes.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css">
        <title>Boxes</title>
    </head>
<div class="w3-panel  w3-card-4">
    <p>Boxes ti offre i migliori set hardware in circolazione, una Box per ogni esigenza!
    Scegli la fascia che preferisci e seleziona la Box più adatta a te!</p>
</div>
        <body> 
     <div class="w3-row-padding w3-center w3-margin-top">
         <c:forEach var="categoria" items="${categoria}">
             <!<!-- da inserire c:if -->
             <a href="box?${categoria.id}">
        <form>
        <div class="w3-third w3-button" >
            <div class="w3-card w3-container" style="min-height:300px" >
                <h3>Fascia ${categoria.name}</h3><br>
                <img src="${initParam.imgboxPath}${categoria.name}.png" alt="img" height="100" width="100">
                
            </div>
        </div>
        </form>
           </a> 
        </c:forEach>   
     </div>     
           
    <div class="w3-row-padding w3-center w3-margin-top  "  >
        <a href="composizioneBox">
            <form>
        <div class="w3-panel  w3-card-4 w3-button" style="width:100%" >
            <div class=" w3-container " style="min-height:250px">
                <h3>Personalizza</h3><br>
                <img src="${initParam.imgboxPath}boxpersonalizzata.png" alt="img" height="100" width="100">
                <h4>Crea una Box personalizzata.</h4>
                <p>Scegli le componenti della tua box</p>
            </div>
        </div>
          </form>
        </a>
    </div>
        </body>              

