/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Box;
import entity.BoxOrdinate;
import entity.BoxOrdinatePK;
import entity.Ordine;
import entity.Prodotto;
import entity.Utente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AdminM;
import session.BoxFacade;
import session.BoxOrdinateFacade;
import session.OrderManager;
import session.OrdineFacade;
import session.ProdottoFacade;
import session.UtenteFacade;

/**
 *Il controller delle pagine riservate all'admin
 * Smista le views e le operazioni che possono essere eseguite su utenti, ordini,box, prodotti
 * @author pc
 */
@WebServlet(name = "AdminServlet",
        urlPatterns = {"/admin/",
            "/admin/viewOrders",
            "/admin/viewCustomers",
            "/admin/customerRecord",
            "/admin/orderRecord",
            "/admin/logout",
            "/admin/viewBox",
            "/admin/viewProdotti",
            "/admin/crUtente",
            "/admin/addUtente",
            "/admin/delUtente",
            "/admin/crBox",
            "/admin/addBox",
            "/admin/delBox",
            "/admin/crProd",
            "/admin/addProd",
            "/admin/delProd",
            })
public class AdminServlet extends HttpServlet {

    
    @EJB
    private UtenteFacade utenteFacade;
    @EJB
    private OrdineFacade ordineFacade;
    @EJB
    private BoxFacade boxFacade;
    @EJB
    private BoxOrdinateFacade boxOrdinateFacade;
    @EJB
    private ProdottoFacade prodottoFacade;
    @EJB
    private AdminM admin;

    private String userPath;
    private Utente utente;
    private Ordine ordine;
    private List orderList = new ArrayList();
    private List customerList = new ArrayList();
    private List boxList = new ArrayList();
    private List prodList = new ArrayList();

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

        HttpSession session = request.getSession(true);
        userPath = request.getServletPath();

        // se viewCostumer è richiesta
        if (userPath.equals("/admin/viewCustomers")) {
            customerList = utenteFacade.findAll();
            request.setAttribute("customerList", customerList);
        }

        // se viewOrders è richiesta
        if (userPath.equals("/admin/viewOrders")) {
            orderList = ordineFacade.findAll();
            request.setAttribute("orderList", orderList);
        }
        if (userPath.equals("/admin/viewBox")) {
            boxList = boxFacade.findAll();
            request.setAttribute("boxList", boxList);
        }
        if (userPath.equals("/admin/viewProdotti")) {
            prodList = prodottoFacade.findAll();
            request.setAttribute("prodList", prodList);
        }
        if (userPath.equals("/admin/crUtente")) {
            int utente = 1;
            request.setAttribute("utente", utente);
        }
        if (userPath.equals("/admin/crBox")) {
            int box = 1;
            request.setAttribute("box", box);
        }
        if (userPath.equals("/admin/crProd")) {
            int prod = 1;
            request.setAttribute("prod", prod);
        }
        if (userPath.equals("/admin/delUtente")) {
            String r = request.getQueryString();

            Utente u = utenteFacade.find(Integer.parseInt(r));
            Collection<Ordine> ordini = u.getOrdineCollection();
            if(ordini==null){
            utenteFacade.remove(u);
            }
            
        }
        if (userPath.equals("/admin/delBox")) {
            String r = request.getQueryString();
            Box b = boxFacade.find(Integer.parseInt(r));
            boxFacade.remove(b);

        }
        if (userPath.equals("/admin/delProd")) {
            String r = request.getQueryString();
            Prodotto p = prodottoFacade.find(Integer.parseInt(r));
            prodottoFacade.remove(p);

        }

        // se customerRecord è richiesta
        if (userPath.equals("/admin/customerRecord")) {

            // get id utente dalla richiesta
            String utenteId = request.getQueryString();

            // get dettagli utente
            utente = utenteFacade.find(Integer.parseInt(utenteId));
            request.setAttribute("customerRecord", utente);

            // restituisce i dettagli dell'ordine dell'utente
            ordine = ordineFacade.findByUtente(utente);
            request.setAttribute("order", ordine);
        }

       
      

       
        // uso di RequestDispatcher per inviare richieste internamente
        userPath = "/admin/index.jsp";

        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        processRequest(request, response);
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
        //aggiunta nuovo utente
        if (userPath.equals("/admin/addUtente")) {

            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String email = request.getParameter("email");
            String indirizzo = request.getParameter("indirizzo");
            String cittprovincia = request.getParameter("cittprovincia");
            String cap = request.getParameter("cap");
            Utente ut = admin.addUtente(nome, cognome, email, indirizzo, cittprovincia, cap);

        }//aggiunta nuova Box
        if (userPath.equals("/admin/addBox")) {

            String id = request.getParameter("id");
            String nomeBox = request.getParameter("nomeBox");
            String tipoDiBox = request.getParameter("tipoDiBox");
            String numeroProdotti = request.getParameter("numeroProdotti");
            String punteggioBox = request.getParameter("punteggioBox");
            String descrizioneBox = request.getParameter("descrizioneBox");
            String prezzoBox = request.getParameter("prezzoBox");
            String categoria_id = request.getParameter("categoriaId");
            Box bx = admin.addBox(id, nomeBox, tipoDiBox, numeroProdotti, punteggioBox, descrizioneBox, prezzoBox, categoria_id);

        } //aggiunta nuovo Prodotto
        if (userPath.equals("/admin/addProd")) {

            String nomeProdotto = request.getParameter("nomeProdotto");
            String punteggio = request.getParameter("punteggio");
            String prezzo = request.getParameter("prezzo");
            String descrizioneProdotto = request.getParameter("descrizioneProdotto");
            String Box_idBox = request.getParameter("Box_idBox");
            
            Prodotto prod = admin.addProd(nomeProdotto,punteggio,prezzo,descrizioneProdotto,Box_idBox);

        }
        userPath = "/admin/index.jsp";

        try {
            request.getRequestDispatcher(userPath).forward(request, response);
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
