/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validate;
import javax.servlet.http.HttpServletRequest;

/**
 *Classe che valida l'input al momento del checkout
 * @author pc
 */
public class Validator {

   


     

    /**
     *Metodo che applica validazione form checkout
     * @param nome //
     * @param cognome // 
     * @param email // 
     * @param indirizzo //
     * @param cittprovincia // 
     * @param cap //
     * @param request //
     * @return booleano per segnalare errore
     */
    public boolean validateForm(String nome,
                                String cognome,
                                String email,
                                String indirizzo,
                                String cittprovincia,
                                String cap,
                                HttpServletRequest request) {

        boolean errorFlag = false;
        boolean nomeError;
        boolean cognomeError;
        boolean emailError;
        boolean indirizzoError;
        boolean cittprovinciaError;
        boolean capError;

        if (nome == null
                || nome.equals("")
                || nome.length() > 45) {
            errorFlag = true;
            nomeError = true;
            request.setAttribute("nomeError", nomeError);
        }
        if (cognome == null
                || cognome.equals("")
                || cognome.length() < 2) {
            errorFlag = true;
            cognomeError = true;
            request.setAttribute("cognomeError", cognomeError);
        }
        if (email == null
                || email.equals("")
                || !email.contains("@")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (indirizzo == null
                || indirizzo.equals("")
                || indirizzo.length() > 45) {
            errorFlag = true;
            indirizzoError = true;
            request.setAttribute("indirizzoError", indirizzoError);
        }
        if (cittprovincia == null
                || cittprovincia.equals("")
                || cittprovincia.length() > 45) {
            errorFlag = true;
            cittprovinciaError = true;
            request.setAttribute("cittprovinciaError", cittprovinciaError);
        }
        if (cap == null
                || cap.equals("")
                || cap.length() <1) {
            errorFlag = true;
            capError = true;
            request.setAttribute("capError", capError);
        }

        return errorFlag;
    }

}