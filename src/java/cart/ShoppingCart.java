/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entity.Box;
import java.util.*;

/**
 *La classe che implementa il carrello, come li sta di ShoppingCartItems 
 *È unica per utente e dura solo per la sessione
 * @author pc
 */
public class ShoppingCart {
    
    List<ShoppingCartItem> items;
    int numberOfItems;
    double total;
    
    /**
     *Costruttore
     */
    public ShoppingCart(){
        items = new ArrayList<ShoppingCartItem>();
        numberOfItems = 0;
        total = 0;
    }
  /**
     * Adds a <code>ShoppingCartItem</code> to the <code>ShoppingCart</code>'s
     * <code>items</code> list. If item of the specified <code>box</code>
     * already exists in shopping cart list, the quantity of that item is
     * incremented.
     *
     * @param box the <code>Box</code> that defines the type of shopping cart item
     * @see ShoppingCartItem
     */
    
    public synchronized void addItem(Box box){
        boolean newItem = true;
        for(ShoppingCartItem scItem : items){
            if(scItem.getBox().getId() == box.getId()){
                newItem = false;
                scItem.incrementQuantity();
             
            }
        }
        if (newItem){
          ShoppingCartItem scItem = new ShoppingCartItem(box); 
          
          items.add(scItem);
          scItem.incrementQuantity();
          
        }
        
    }
    
    /**
     * Updates the <code>ShoppingCartItem</code> of the specified
     * <code>box</code> to the specified quantity. If '<code>0</code>'
     * is the given quantity, the <code>ShoppingCartItem</code> is removed
     * from the <code>ShoppingCart</code>'s <code>items</code> list.
     *
     * @param box the <code>Box</code> that defines the type of shopping cart item
     * @param quantity the number which the <code>ShoppingCartItem</code> is updated to
     * @see ShoppingCartItem
     */
    
    public synchronized void update(Box box, String quantity){
        short qty = -1;
        
        
        qty = Short.parseShort(quantity);
        
        if(qty >= 0){
            ShoppingCartItem item = null;
            for(ShoppingCartItem scItem :items){
                
                if(scItem.getBox().getId() == box.getId()){
                    if (qty != 0) {
                        // la quantità è settata al nuovo valore
                        scItem.setQuantity(qty);
                    } else {
                       // se la quantità equivale a 0 salva l'item ed esce
                        item = scItem;
                        break;
                    }
                }
            }
            if (item != null){
                //toglie item dal carrello
                items.remove(item);
            }
        }
    }
    /**
     * Returns the list of <code>ShoppingCartItems</code>.
     *
     * @return the <code>items</code> list
     * @see ShoppingCartItem
     */
    public synchronized List<ShoppingCartItem> getItems() {

        return items;
    }
    
    /**
     *metodo ad hoc per cancellare una box dal carrello
     * @param quantity quantità
     * @param id della box
     */
    public synchronized void DeleteBox(String quantity,int id){
       short qty = -1;
        
      
        qty = Short.parseShort(quantity);
        
        if(qty >= 0){
            ShoppingCartItem item = null;
            for(ShoppingCartItem scItem :items){
                
                if(scItem.getBox().getId() == id){
                   
            }
            if (item != null){
                //togli dal carrello
                items.remove(item);
            }
        }
    }
    }
    /**
     * Returns the sum of quantities for all items maintained in shopping cart
     * <code>items</code> list.
     *
     * @return the number of items in shopping cart
     * @see ShoppingCartItem
     */
    public synchronized int getNumberOfItems() {

        numberOfItems = 0;

        for (ShoppingCartItem scItem : items) {

            numberOfItems += scItem.getQuantity();
        }

        return numberOfItems;
    }
    /**
     * Returns the sum of the product price multiplied by the quantity for all
     * items in shopping cart list. This is the total cost excluding the surcharge.
     *
     * @return the cost of all items times their quantities
     * @see ShoppingCartItem
     */
    public synchronized double getSubtotal() {

        double amount = 0;

        for (ShoppingCartItem scItem : items) {

            Box box = (Box) scItem.getBox();
            amount += (scItem.getQuantity() * box.getPrezzoBox().doubleValue());
        }

        return amount;
    }
     /**
     * Calculates the total cost of the order. This method adds the subtotal to
     * the designated surcharge and sets the <code>total</code> instance variable
     * with the result.
     *
     * @param surcharge the designated surcharge for all orders
     * @see ShoppingCartItem
     */
    public synchronized void calculateTotal(String surcharge) {

        double amount = 0;

        // per calcolare un eventuale sovrapprezzo (spedizione)
        double s = Double.parseDouble(surcharge);

        amount = this.getSubtotal();
        amount += s;

        total = amount;
    }

    /**
     * Returns the total cost of the order for the given
     * <code>ShoppingCart</code> instance.
     *
     * @return the cost of all items times their quantities plus surcharge
     */
    public synchronized double getTotal() {

        return total;
    }

    /**
     * Empties the shopping cart. All items are removed from the shopping cart
     * <code>items</code> list, <code>numberOfItems</code> and
     * <code>total</code> are reset to '<code>0</code>'.
     *
     * @see ShoppingCartItem
     */
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }

}
