package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *Classe che implementa la logica business
 * Filtro che si occupa di verificare se la sessione è scaduta
 * @author pc
 */

@WebFilter(servletNames = {"Controller"})
public class SessionTimeoutFilter implements Filter {

    /**
     *Metodo che applica un controllo alla sessione
     * @param request richiesta 
     * @param response risposta 
     * @param chain concatenazione
     * @throws IOException lancia eccezione i/o
     * @throws ServletException lancia eccezione Servlet
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession(false);

        // se la sessione è scaduta rimanda alla home
        if (session == null) {
            try {
                req.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     *Metodo che inizializza il filtro
     * @param filterConfig serve ad implemetare l'interfaccia del filtro 
     * @throws ServletException lancia eccezione all'occorrenza
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     */
    @Override
    public void destroy() {}

}