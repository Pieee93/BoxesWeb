<%-- 
    Document   : listaProdotti
    Created on : 16-dic-2020, 17.41.24
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>



    <body>
        <div class="w3-container w3-grey w3-center">
            <h3>Box ${Box.nomeBox}</h3>
            <form action="addToCart" method="post">
                                
                                <input type="hidden"
                                       name="boxId"
                                       value="${Box.id}">
                                <input  class="w3-button w3-black w3-hover-green" 
                                        type="submit"
                                        name="submit" 
                                        value="+ Aggiungi al carrello">
                                </form> <!-- aggiunta box al carrello dalla pagina dei prodotti -->
        </div> 
        <div class="w3-container">
            <ul class="w3-ul w3-card-4 ">
                <c:forEach var="prodotto" items="${Products}" varStatus="theCount"> 

                    <li class="w3-bar">
                        <button onclick="myAccFunc('divIDNo${theCount.count}')" class="w3-padding-16 w3-theme w3-button w3-block w3-left-align">
                            <img src="${initParam.imgprodottiPath}prodotto.png" class="w3-bar-item w3-circle w3-hide-small" style="width:100px">
                            ${prodotto.nomeProdotto}</button>
                        <div  id="divIDNo${theCount.count}" class="w3-hide">
                            <div class="w3-container">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </div>
                        </div>

                    </li>

                </c:forEach>
            </ul>
        </div>
    </body><!-- scipt per apertura accordons(dettagli)-->
    <script>    function myAccFunc(id) {
            var x = document.getElementById(id);
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
            } else {
                x.className = x.className.replace(" w3-show", "");
            }
        }</script>
</html>
