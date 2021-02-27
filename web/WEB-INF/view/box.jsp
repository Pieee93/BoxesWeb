
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    
    <div class="w3-container w3-grey w3-center">
            <h3>Fascia ${Categoria.name}</h3>
        </div> 
        <div class="w3-row-padding w3-center w3-margin-top">
            <c:forEach var="box" items="${boxesFascia}"> <!-- ciclo elementi per Fascia -->
                <a href ="prodotti?${box.id}"> <!-- riferimento alla pagina prodotti -->

                    <div>
                        <div class="w3-third w3-button"  >
                            <div class="w3-card w3-container" style="min-height:300px">
                                <h3>${box.nomeBox}</h3><br>
                                <img src="${initParam.imgboxPath}${box.nomeBox}.png" alt="img" height="100" width="100">
                                
                                <p>${box.descrizioneBox}</p>
                                <h4>Punteggio:${box.punteggioBox}</h4>
                                <h3>${box.prezzoBox}€</h3>
                                <!-- form per aggiungere Box al carrello -->
                                <form action="addToCart" method="post">
                                
                                <input type="hidden"
                                       name="boxId"
                                       value="${box.id}">
                                <input  class="w3-button w3-black w3-hover-green" 
                                        type="submit"
                                        name="submit" 
                                        value="+ Aggiungi al carrello">
                                </form> 
                                <br>
                            </div>

                        </div>

                    </div>  
                </a>
            </c:forEach>
        </div>
        <!-- Pagination -->
        <div class="w3-center w3-padding-32">
            <div class="w3-bar"><!-- paginazione foo -->
                <a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>
                <a href="#" class="w3-bar-item w3-black w3-button">1</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>
            </div>
        </div>

    </body>
</html>


