<%-- 
    Document   : composizioneBox
    Created on : 16-dic-2020, 17.42.48
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<body>
<div class="w3-container w3-grey w3-center">
        <h3>Box ${Box.nomeBox}</h3>
    </div>
   
    <div class="w3-row">
                
                <div class="w3-col w3-container " style="width:15%">                     
                    <p>Aggiungi</p>  
                </div>
                
               
                
                <div class="w3-col w3-container w3-black" style="width:20%">                                
                    <p>Nome </p>
                </div>
                
                <div class="w3-col w3-container w3-black" style="width:20%">                                
                    <p>Punteggio</p>
                </div>
                
                <div class="w3-col w3-container w3-black" style="width:20%">                                
                    <p> Prezzo</p>
                </div>
                
                <div class="w3-col w3-container w3-black "  style="width:25%">
                    <p>Dettagli</p></div>
                    

            </div>
        <br>
<!-- form con checkbox per scelta componenti-->
    <form action="componi" method="post">
        <c:forEach var="prodotto" items="${Products}" varStatus="theCount"> 

            <div class="w3-row w3-border">
                
                <div class="w3-col w3-container " style="width:5%">                     
                    <input class="w3-check" type="checkbox" name="id" value="${prodotto.idProdotto}">   
                </div>
                
                <div class="w3-col w3-container w3-black" style="width:10%">                                
                    <img src="${initParam.imgprodottiPath}prodotto.png" class="w3-bar-item w3-circle w3-hide-small" style="width:100px">
                </div>
                
                <div class="w3-col w3-container " style="width:20%">                                
                    <p>  ${prodotto.nomeProdotto}</p>
                </div>
                
                <div class="w3-col w3-container " style="width:20%">                                
                    <p>${prodotto.punteggio}</p>
                </div>
                
                <div class="w3-col w3-container " style="width:20%">                                
                    <p> ${prodotto.prezzo} &euro;</p>
                </div>
                
                <div class="w3-col w3-container w3-black w3-button"  onclick="myAccFunc('divIDNo${theCount.count}')" style="width:25%">
                    <p>Dettagli</p></div>
                    

            </div>
            <div  id="divIDNo${theCount.count}" class="w3-hide">
                <div class="w3-container">
                    <p>${prodotto.descrizioneProdotto}  </p>                       
                </div>
            </div>
<br>
        </c:forEach>



        <div id="stick">


            <input  class="w3-button w3-xlarge w3-topbar w3-bottombar w3-border-green w3-black w3-hover-green w3-block" 
                    type="submit"

                    value="Fine">
        </div>
    </form>
</body><!-- scipt per apertura accordons(dettagli)-->
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
