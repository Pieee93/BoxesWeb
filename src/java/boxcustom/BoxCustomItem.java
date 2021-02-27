/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxcustom;

import entity.Prodotto;

/**
 *Una classe che rappresenta un Item di una Box Custom
 * È utilizzata per creare una lista di item nella classe BoxCustom
 * 
 * @author pc
 */
public class BoxCustomItem {
    Prodotto prod;
    short quantity;
    
    /**
     *Inizializza un prodotto come prodotto nella box custom
     * @param prod prodotto da inserire nella box custom
     */
    public BoxCustomItem(Prodotto prod){
        this.prod = prod;   
    }

    /**
     *Ritorna contenuto nella box custom
     * @return ritorna un prodotto 
     */
    public Prodotto getProdotto() {
        return prod;
    }

    /**
     *Ritorna la quantità del prodotto inserito come BoxCustomItem
     * @return ritora la quantità del prodotto
     */
    public short getQuantity() {
        return quantity;
    }

    /**
     *Imposta la quantità del prodotto inserito come BoxCustomItem
     * @param quantity imposta la quantità del singolo prodotto
     */
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    /**
     *Incrementa la quantità del prodotto
     */
    public void incrementQuantity() {
        quantity++;
    }

    /**
     *Decrementa la quantità del prodotto
     */
    public void decrementQuantity() {
        quantity--;
    }

    /**
     *Calcola il costo del prodotto per il numero di volte che è dentro la BoxCustom
     * @return ritorna il prezzo 
     */
    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * prod.getPrezzo().doubleValue());
        return amount;
    }

    
    
    
}
