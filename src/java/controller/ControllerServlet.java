/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import boxcustom.BoxCustom;
import cart.ShoppingCart;
import entity.Box;
import entity.Categoria;
import entity.Prodotto;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import validate.Validator;
import session.BoxFacade;
import session.CategoriaFacade;
import session.OrderManager;
import session.ProdottoFacade;

/**
 *Controller del sito web, si occupa di smistare le richieste e di eseguire le operazioni richieste
 * @author pc
 */
@WebServlet(name = "Controller", loadOnStartup = 1, urlPatterns = {"/box",
    "/addToCart",
    "/viewCart",
    "/updateCart",
    "/checkout",
    "/purchase",
    "/chooseLanguage",
    "/prodotti",
    "/carrello",
    "/registrazione",
    "/composizioneBox",
    "/componi"})
public class ControllerServlet extends HttpServlet {

    private String surcharge = "10";
    BoxCustom boxCustom;
//Dichiarazione di EJB Facade per l'accesso al Database
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private BoxFacade boxFacade;
    @EJB
    private OrderManager orderManager;
    @EJB
    private ProdottoFacade prodottoFacade;

    /**
     *
     * @throws ServletException lancia un'eccezione nel caso in cui non riesca a caricare le categorie
     */
    @Override
    //carica le categorie appena si ha accesso al sito
    public void init() throws ServletException {
        ServletConfig servletConfig = null;

        getServletContext().setAttribute("categoria", categoriaFacade.findAll());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Box selectedBox;
        Collection<Box> boxFasciaSelezionata;

        //se richiesta la pagina BoxList
        if (userPath.equals("/box")) {
            // 
            String categoriaId = request.getQueryString();
            if (categoriaId != null) {

                // get selected category
                Categoria selectedCategoria = categoriaFacade.find(Integer.parseInt(categoriaId));
                request.setAttribute("Categoria", selectedCategoria);

                // get box per Categoria
                boxFasciaSelezionata = selectedCategoria.getBoxCollection();
                // place category products in request scope
                request.setAttribute("boxesFascia", boxFasciaSelezionata);
            }

            //se richiesta la pagina carrello
        } else if (userPath.equals("/viewCart")) {
               //check del parametro clear per svuotare il carrello 
            String clear = request.getParameter("clear");

            if ((clear != null) && clear.equals("true")) {

                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                cart.clear();
            }

            userPath = "/carrello";

            //se è richiesta la pagina checkout  
        } else if (userPath.equals("/checkout")) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

            // calcolo totale
            cart.calculateTotal(surcharge);

            
            //se l'utente vuole cambiare lingua
        } else if (userPath.equals("/chooseLanguage")) {
            // TODO: Implement language request

        } else if (userPath.equals("/prodotti")) {

            // get boxId da request
            String boxId = request.getQueryString();
            if (boxId != null) {
                // get  box selezionata
                selectedBox = boxFacade.find(Integer.parseInt(boxId));
                request.setAttribute("Box", selectedBox);

                // get prodotti per box selezionata
                Collection<Prodotto> productsInsideBox;
                productsInsideBox = selectedBox.getProdottoCollection();
                // mette i prodotti della box nel request scope
                request.setAttribute("Products", productsInsideBox);
            }
        } else if (userPath.equals("/composizioneBox")) {
            //trova tutti i prodotti 
            Collection<Prodotto> tuttiProdotti;
            tuttiProdotti = prodottoFacade.findAll();
            request.setAttribute("Products", tuttiProdotti);

        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        Validator validator = new Validator();
        //se è chiamato aggiungi al carrello 
        if (userPath.equals("/addToCart")) {
            //  se l'utente aggiunge una box per la prima volta al carrello 
            // crea l'oggetto carrello e lo lega alla sessione utente
            if (cart == null) {

                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            // get input utente 
            String boxId = request.getParameter("boxId");

            if (!boxId.isEmpty()) {

                Box box = boxFacade.find(Integer.parseInt(boxId));
                cart.addItem(box);
            }
            //rimanda l'utente alla pagina principale
            String url1 = "index.jsp";
            request.getRequestDispatcher(url1).forward(request, response);

            //se si finisce di comporre la boxCustom
        } else if (userPath.equals("/componi")) {
            if (cart == null) {

                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }

            String id[] = request.getParameterValues("id");
            if (id != null) {
                boxCustom = new BoxCustom(id.length);

                BigDecimal sum = BigDecimal.ZERO;

                for (int i = 0; i < id.length; i++) {
                    Prodotto prod = prodottoFacade.find(Integer.parseInt(id[i]));
                    sum = sum.add(prod.getPrezzo());
                    boxCustom.addItem(prod);
                }
                boxCustom.setPrezzoBox(sum);

                cart.addItem(boxCustom);
            }

            String url1 = "index.jsp";
            request.getRequestDispatcher(url1).forward(request, response);
            //l'utente fa un update del carrello 
        } else if (userPath.equals("/updateCart")) {
            // get input from request
            String boxId = request.getParameter("boxId");
            String quantity = request.getParameter("quantity");

            Box box = boxFacade.find(Integer.parseInt(boxId));
            cart.update(box, quantity);

            userPath = "/carrello";
            //quando si vuole concludere l'acquisto
        } else if (userPath.equals("/purchase")) {
            if (cart != null) {

                // estrae i dati dell'utente
                String nome = request.getParameter("nome");
                String cognome = request.getParameter("cognome");
                String email = request.getParameter("email");
                String indirizzo = request.getParameter("indirizzo");
                String cittprovincia = request.getParameter("cittprovincia");
                String cap = request.getParameter("cap");
                //elimina la boxCustom dal carrello prima di processarla, essendo legata alla sessione utente
                cart.update(boxCustom, "0");
                boolean validationErrorFlag = false;
                //valida i dati utente
                validationErrorFlag = validator.validateForm(nome, cognome, email, indirizzo, cittprovincia, cap, request);

                // se ci sono errori rimanda l'utente al checkout
                if (validationErrorFlag == true) {
                    request.setAttribute("validationErrorFlag", validationErrorFlag);
                    userPath = "/checkout";

                    //altrimenti salva l'ordine nel database
                } else {
                    int orderId = orderManager.placeOrder(nome, cognome, email, indirizzo, cittprovincia, cap, cart);
                    // se l'ordine è processato correttamente si viene reindirizzati alla pagina di conferma
                    if (orderId != 0) {

                        //distrugge il carrello
                        cart = null;

                        // mette fine alla sessione utente
                        session.invalidate();

                        
                        Map orderMap = orderManager.getOrderDetails(orderId);

                        // i dettagli dell'ordine sono messi nello scope della richiesta
                        request.setAttribute("customer", orderMap.get("customer"));
                        request.setAttribute("products", orderMap.get("products"));
                        request.setAttribute("orderRecord", orderMap.get("orderRecord"));
                        request.setAttribute("orderedProducts", orderMap.get("orderedProducts"));

                        userPath = "/confirmation";
                        // altrimenti manda al checkout
                    } else {
                        userPath = "/checkout";
                        request.setAttribute("orderFailureFlag", true);
                    }
                }
            }
        }

        //uso di RequestDispatcher per inviare richieste internamente
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
