<%-- 
    Document   : registrazione
    Created on : 17-dic-2020, 11.58.24
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <body>

        <div class="w3-container w3-grey w3-center">
            <h3>Checkout</h3>
        </div> 

        <div style="float:left; display:block;">
            <hr>
            <h2 class="w3-center">Inserisci i tuoi dati per procedere all'acquisto</h2>

            <div  class="w3-row-padding" ><!-- form submit dei dati utente -->
                <form class="w3-container w3-card-4" action="<c:url value='purchase'/>" method="post">
                    <div class="w3-center" class="w3-half">

                        <h3>Inserisci qui i tuoi dati</h3>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required                           
                                   name="nome"
                                   value="${param.nomeUtente}">
                            <label>Nome</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required   
                                   name="cognome"
                                   value="${param.cognomeUtente}">
                            <label>Cognome</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="email"
                                   value="${param.emailUtente}">
                            <label>Email</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="indirizzo"
                                   value="${param.indirizzo}">
                            <label>Indirizzo</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="cittprovincia"
                                   value="${param.città_provincia}">
                            <label>Città (Provincia)</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="cap"
                                   value="${param.name}">
                            <label>CAP</label>
                        </div>

                        <br>
                        <input  class="w3-button w3-block w3-black w3-hover-green" 
                                type="submit"
                                name="submit" 
                                value="Procedi">

                    </div>
                    <br>
                </form>

            </div>

            <hr>
        </div>
<!-- Breve riepilogo -->
        <div style=”float:left; display:block;>
            <hr>
            <h2 class="w3-center">Le tue Box</h2>
            <div class="w3-responsive w3-card-4">
                <!--tabella-->
                <table class="w3-table w3-striped w3-bordered w3-border">
                    <thead> <!-- Nome campi -->
                        <tr class="w3-theme">
                            <th>Nome Box</th>
                            
                            <th>Quantità</th>
                            <th>Prezzo</th>
                        </tr>
                    </thead>
                    <tbody> <!-- Ciclo -->
                        <c:forEach var="cartItem" items="${cart.items}"> <!-- prende un item -->
                            <c:set var="box" value="${cartItem.box}"/> <!-- assegna item a variabile -->
                            <tr>
                                <td>${box.nomeBox}</td>
                                
                                <td >
                                    ${cartItem.quantity} 
                                </td>
                                <td>
                                    &euro; ${cartItem.total}
                                    <br>
                                    <span class="smallText">( &euro; ${box.prezzoBox} / Box )</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <table class="w3-table w3-striped w3-bordered">
                    <thead> <!-- Nome campi -->
                        <tr class="w3-theme">
                            
                            
                            <th>Numero totale di Box:</th>
                            <th>Il totale è:</th>
                        </tr>
                    </thead>
                    <tr> 
                        <td><h3>${cart.getNumberOfItems()}</h3></td>
                        <td><h3>${cart.getTotal()} &euro;</h3></td>
                    </tr>
            </div>
        </div>
    </body>
</html>
