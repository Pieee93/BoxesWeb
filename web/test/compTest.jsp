<%-- 
    Document   : compTest
    Created on : 2-gen-2021, 10.04.28
    Author     : pc
--%>

<%-- 
    Document   : composizioneBox
    Created on : 16-dic-2020, 17.42.48
    Author     : pc
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<body>
    <div class="w3-container w3-grey w3-center">
        <h3>Box ${Box.nomeBox}</h3>
    </div>

    <form action="componi" method="post">
        <c:forEach var="prodotto" items="${Products}" varStatus="theCount"> 

            <div class="w3-row">
                //primo
                <div class="w3-col w3-container " style="width:5%">                     
                    <input class="w3-check" type="checkbox" name="id" value="${prodotto.idProdotto}">   
                </div>
                //secondo
                <div class="w3-col w3-container w3-red" style="width:10%">                                
                    <img src="img_avatar2.png" class="w3-bar-item w3-circle w3-hide-small" style="width:100px">
                </div>
                //terzo
                <div class="w3-col w3-container w3-red" style="width:20%">                                
                    ${prodotto.nomeProdotto}
                </div>
                //quarto
                <div class="w3-col w3-container w3-red" style="width:20%">                                
                    ${prodotto.punteggio}
                </div>
                //quinto
                <div class="w3-col w3-container w3-red" style="width:20%">                                
                    ${prodotto.prezzo}
                </div>
                //sesto
                <div class="w3-col w3-container w3-black w3-button"  onclick="myAccFunc('divIDNo${theCount.count}')" style="width:25%">Dettagli</div>

            </div>
            <div  id="divIDNo${theCount.count}" class="w3-hide">
                <div class="w3-container">
                    <p>$ {prodotto.descrizioneProdotto}  </p>                       
                </div>
            </div>

        </c:forEach>



        <div id="stick">


            <input  class="w3-button w3-black w3-hover-green" 
                    type="submit"

                    value="Procedi">
        </div>
    </form>
</body>
<script>    function myAccFunc(id) {
        var x = document.getElementById(id);
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }</script>
</body>
</html>

