<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Pagina admin-->
<!-- Lista di operazioni sulla sinistra che rimandano al file AdminServlet-->
<div  class="alignLeft">
    <p><a href="<c:url value='viewCustomers'/>">view all customers</a></p>
    <p><a href="<c:url value='viewOrders'/>">View All ordini</a></p>
    <p><a href="<c:url value='viewBox'/>">view all Box</a></p>
     <p><a href="<c:url value='viewProdotti'/>">view all Prodotti</a></p>
      <p><a href="<c:url value='crUtente'/>">Add Utente</a></p>
      <p><a href="<c:url value='crBox'/>">Add Box</a></p>
      <p><a href="<c:url value='crProd'/>">Add Prodotto</a></p>
    <p><a href="<c:url value='logout'/>">log out</a></p>
</div>

<%-- customerList è richiesta --%>
<c:if test="${!empty customerList}">

    <div class="w3-responsive w3-card-4">

            <table class="w3-table w3-striped w3-bordered">
                <thead> <!-- Nome campi -->
                    <tr class="w3-theme">
                        <th>ID Utente</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Cap</th>
                    </tr>
                </thead>
                    <tbody> <!-- Ciclo -->
                    <c:forEach var="customer" items="${customerList}"> <!-- prende un item -->
                        <tr>
                            <td><a href="customerRecord?${customer.idUtente}" class="noDecoration">${customer.idUtente}</a></td>
                            <td>
                               <a href="customerRecord?${customer.idUtente}" class="noDecoration">${customer.nomeUtente}</a>
                            </td>
                            <td>
                                <a href="customerRecord?${customer.idUtente}" class="noDecoration">${customer.emailUtente}</a>
                            </td>
                            <td><a href="customerRecord?${customer.idUtente}" class="noDecoration">${customer.cap}</a></td>
                            <td>
                                <td>
                                <a href="delUtente?${customer.idUtente}" class="w3-button w3-theme">Cancella</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>

        </div>

</c:if>

<%-- se richiesta orderList --%>
<c:if test="${!empty orderList}">


    <div class="w3-responsive w3-card-4">

            <table class="w3-table w3-striped w3-bordered">
                <thead> <!-- Nome campi -->
                    <tr class="w3-theme">
                        <th>ID Ordine</th>
                        <th>Numero Conferma</th>
                        <th>Ammontare</th>
                        <th>Data</th>
                    </tr>
                </thead>
                    <tbody> <!-- Ciclo -->
                    <c:forEach var="order" items="${orderList}"> 
                        <tr>
                            <td><a href="orderRecord?${order.idOrdine}" class="noDecoration">${order.idOrdine}</a></td>
                            <td><a href="orderRecord?${order.idOrdine}" class="noDecoration">${order.numeroConfermaOridne}</a></td>
                            <td><a href="customerRecord?${customer.idOrdine}" class="noDecoration">${order.ammonatre}</a></td>
                            <td><a href="customerRecord?${customer.idOrdine}" class="noDecoration">${order.dataCreazione}</a></td>

                            
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>

        </div>

</c:if>
<%--se richiesta boxList--%>
<c:if test="${!empty boxList}">

    <div class="w3-responsive w3-card-4">

            <table class="w3-table w3-striped w3-bordered">
                <thead> <!-- Nome campi -->
                    <tr class="w3-theme">
                        <th>ID Box</th>
                        <th>Nome</th>
                        <th>Tipo</th>
                        
                        <th>Punteggio</th>
                        <th>Prezzo</th>
                        <th></th>
                    </tr>
                </thead>
                    <tbody> <!-- Ciclo -->
                    <c:forEach var="box" items="${boxList}"> <!-- prende un item -->
                        <tr>
                            <td>${box.id}</td>
                            <td>
                               ${box.nomeBox}
                            </td>
                            <td>
                               ${box.tipoDiBox}
                            </td>
                            <td>${box.punteggioBox}
                            
                                <td>
                                ${box.prezzoBox}
                            </td>
                            <td>
                                <a href="delBox?${box.id}" class="w3-button w3-theme">Cancella</a>
                            </td>
                            
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>

        </div>

</c:if>
<%--se richiesta prodList--%>
<c:if test="${!empty prodList}">

    <div class="w3-responsive w3-card-4">

            <table class="w3-table w3-striped w3-bordered">
                <thead> <!-- Nome campi -->
                    <tr class="w3-theme">
                        <th>ID Prodotto</th>
                        <th>Nome</th>
                        
                        
                        <th>Punteggio</th>
                        <th>Prezzo</th>
                    </tr>
                </thead>
                    <tbody> <!-- Ciclo -->
                    <c:forEach var="prod" items="${prodList}"> <!-- prende un item -->
                        <tr>
                            <td> ${prod.idProdotto}</td>
                            <td>
                               ${prod.nomeProdotto}
                            </td>
                            <td>
                                ${prod.punteggio}
                            </td>
                            <td><a href="customerRecord?${prod.idProdotto}" class="noDecoration">${prod.prezzo}</a></td>
                            <td>
                            <a href="delProd?${prod.idProdotto}" class="w3-button w3-theme">Cancella</a>
                           </td>
                            
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>

        </div>

</c:if>
<%--creazione nuovo utente--%>
<c:if test="${!empty utente}">

            <hr>
            <h2 class="w3-center">Inserisci i dati dell'utente</h2>

            <div  class="w3-row-padding" >
                <form class="w3-container w3-card-4" action="<c:url value='addUtente'/>" method="post">
                    <div class="w3-center ">

                        
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
        
  

</c:if>
            <%--creazione nuova box--%>
<c:if test="${!empty box}">

            <hr>
            <h2 class="w3-center">Inserisci i dati della Box da aggiungere</h2>

            <div  class="w3-row-padding" >
                <form class="w3-container w3-card-4" action="<c:url value='addBox'/>" method="post">
                    <div class="w3-center ">

                        <div class="w3-section">      
                            <input class="w3-input" type="text" required                           
                                   name="id"
                                   value="${param.id}">
                            <label>ID</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required                           
                                   name="nomeBox"
                                   value="${param.nomeBox}">
                            <label>Nome</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required   
                                   name="tipoDiBox"
                                   value="${param.tipoDiBox}">
                            <label>Tipo</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="numeroProdotti"
                                   value="${param.numeroProdotti}">
                            <label>Numero Prodotti</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="punteggioBox"
                                   value="${param.punteggioBox}">
                            <label>Punteggio</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="descrizioneBox"
                                   value="${param.descrizioneBox}">
                            <label>Descrizione</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="prezzoBox"
                                   value="${param.prezzoBox}">
                            <label>Prezzo</label>
                        </div>
                             <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="categoriaId"
                                   value="${param.categoria_id}">
                            <label>Categoria</label>
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
        
  

</c:if>
<%--creazione nuovo prodotto--%>
<c:if test="${!empty prod}">
         <hr>
            <h2 class="w3-center">Inserisci i dati del Prodotto da aggiungere</h2>

            <div  class="w3-row-padding" >
                <form class="w3-container w3-card-4" action="<c:url value='addProd'/>" method="post">
                    <div class="w3-center ">

                        <div class="w3-section">      
                            <input class="w3-input" type="text" required                           
                                   name="nomeProdotto"
                                   value="${param.nomeProdotto}">
                            <label>Nome</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required                           
                                   name="punteggio"
                                   value="${param.punteggio}">
                            <label>Punteggio</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required   
                                   name="prezzo"
                                   value="${param.prezzo}">
                            <label>Prezzo</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="descrizioneProdotto"
                                   value="${param.descrizioneProdotto}">
                            <label>Descrizione</label>
                        </div>
                        <div class="w3-section">      
                            <input class="w3-input" type="text" required

                                   name="Box_idBox"
                                   value="${param.Box_idBox}">
                            <label>ID Box</label>
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
        
  

</c:if>
