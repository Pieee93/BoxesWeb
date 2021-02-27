<%-- 
    Document   : carrello
    Created on : 16-dic-2020, 17.40.38
    Author     : pc
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


    <div class="w3-container">
        <div class="w3-center">
            <h2>Carrello</h2>
            <!-- Testo informativo -->
            <c:choose> 
                <c:when test="${cart.numberOfItems > 1}">
                    <p class="w3-large">Il tuo carrello contiene ${cart.numberOfItems} Box.</p>
                </c:when>
                <c:when test="${cart.numberOfItems == 1}">
                    <p class="w3-large">Il tuo carrello contiene una sola Box.</p>
                </c:when>
                <c:otherwise>
                    <p class="w3-large">Il tuo carrello è vuoto</p>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <br>
    <c:if test="${!empty cart && cart.numberOfItems != 0}">
        <!-- tabella -->
        <div class="w3-responsive w3-card-4">

            <table class="w3-table w3-striped w3-bordered">
                <thead> <!-- Nome campi -->
                    <tr class="w3-theme">
                        <th>Nome Box</th>
                        <th>Prezzo</th>
                        <th>Quantità</th>
                    </tr>
                </thead>
                    <tbody> <!-- Ciclo -->
                    <c:forEach var="cartItem" items="${cart.items}"> <!-- prende un item -->
                        <c:set var="box" value="${cartItem.box}"/> <!-- assegna item a variabile -->
                        <tr>
                            <td>${box.nomeBox}</td>
                            <td>
                                &euro; ${cartItem.total}
                                <br>
                                <span class="smallText">( &euro; ${box.prezzoBox} /Box )</span>
                            </td>
                            <td>
                                <c:if test="${ box.tipoDiBox != 'Personalizzata'}"  >
                                <form action="updateCart" method="post">
                                    <input type="hidden"
                                           name="boxId"
                                           value="${box.id}" >
                                    <input type="text"
                                           maxlength="2"
                                           size="2"
                                           value="${cartItem.quantity}"
                                           name="quantity"
                                           style="margin:5px">
                                    <input type="submit"
                                           name="submit"
                                           value="update">
                                </form>
                                           </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>

        </div>
    </c:if>                      
                <div class="w3-center">
                    <br>
                    <c:if test="${!empty cart && cart.numberOfItems != 0}">
                        <a href="viewCart?clear=true" class="w3-button w3-theme">Svuota il carrello</a>
                    </c:if>
                    <c:set var="value">
                        <c:choose>
                            <c:when test="${!empty selectedCategoria}">
                                box
                            </c:when>
                            <c:otherwise>
                                index.jsp
                            </c:otherwise>
                        </c:choose>
                    </c:set>
                    <a href="${value}" class="w3-button w3-theme">Continua con lo Shopping</a>
                    <c:if test="${!empty cart && cart.numberOfItems != 0}">
                        <a href="checkout" class="w3-button w3-theme-d3">Checkout</a>
                    </c:if>
                </div>
            </body>
        </html>
