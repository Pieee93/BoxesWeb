/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entity.Box;

/**
 * La classe che implementa l'item da inserire nel carrello
 *<pre>
 * ShoppingCartItem item = new ShoppingCartItem(box);
 * </pre>
 *
 * @author pc
 */
public class ShoppingCartItem {
    Box box;
    short quantity;
    
    /**
     *serve a impostare uno shoppingcartitem 
     * @param box box
     */
    public ShoppingCartItem(Box box){
        this.box = box;   
    }

    /**
     *Metodo che ritorna la box identificata come item del carrello
     * @return box
     */
    public Box getBox() {
        return box;
    }

    /**
     *Metodo che ritorna la quantità di box del carrello
     * @return ritorna la quantità
     */
    public short getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity imposta la quantità
     */
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    /**
     *
     */
    public void incrementQuantity() {
        quantity++;
    }

    /**
     *
     */
    public void decrementQuantity() {
        quantity--;
    }

    /**
     *
     * @return ritorna l'ammontare 
     */
    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * box.getPrezzoBox().doubleValue());
        return amount;
    }

    
    
}
