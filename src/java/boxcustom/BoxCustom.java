/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxcustom;

import entity.Box;
import entity.Prodotto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *Una classe che crea una box personalizzata: è una collezione di BoxCustomItem
 * Estende la classe Box 
 * È legata alla sessione utente
 * @author pc
 */
public class BoxCustom extends Box {
    List<BoxCustomItem> items;
    int numberOfItems;
    double total;
    
    /**
     *costruttore customizzato
     * @param l serve ad impostare la quantità di prodotti 
     */
    public BoxCustom(Integer l){
        items = new ArrayList<BoxCustomItem>();
        numberOfItems = 0;
        total = 0;
        Random random = new Random();
        int i = random.nextInt(999);
      
             this.setId(i);
             this.setNomeBox("Personalizzata"+i);
             this.setNumeroProdotti(l);
             this.setPunteggioBox(BigDecimal.TEN);
             this.setTipoDiBox("Personalizzata");
             this.setDescrizioneBox("Box Personalizzata");
             
    }
    
    
   
  /**
     * Adds a <code>BoxCustomItem</code> to the <code>BoxCustom</code>'s
     * <code>items</code> list. If item of the specified <code>box</code>
     * already exists in shopping cart list, the quantity of that item is
     * incremented.
     *
     * @param prod the <code>Box</code> that defines the type of box custom item
     * @see BoxCustomItem
     */
    
    public synchronized void addItem(Prodotto prod){
        boolean newItem = true;
        for(BoxCustomItem scItem : items){
            if(scItem.getProdotto().getIdProdotto() == prod.getIdProdotto()){
                newItem = false;
                scItem.incrementQuantity();
             
            }
        }
        if (newItem){
          BoxCustomItem scItem = new BoxCustomItem(prod); 
          
          items.add(scItem);
          scItem.incrementQuantity();
          
        }
    }
    
    /**
     * Updates the <code>BoxCustomItem</code> of the specified
     * <code>box</code> to the specified quantity. If '<code>0</code>'
     * is the given quantity, the <code>BoxCustomItem</code> is removed
     * from the <code>BoxCustom</code>'s <code>items</code> list.
     *
     * @param prod the <code>Box</code> that defines the type of box custom item
     * @param quantity the number which the <code>BoxCustomItem</code> is updated to
     * @see BoxCustomItem
     */
    
    public synchronized void update(Prodotto prod, String quantity){
        short qty = -1;
        
        //cast quantity in short
        qty = Short.parseShort(quantity);
        
        if(qty >= 0){
            BoxCustomItem item = null;
            for(BoxCustomItem scItem :items){
                
                if(scItem.getProdotto().getIdProdotto() == prod.getIdProdotto()){
                    if (qty != 0) {
                        // set item quantity to new value
                        scItem.setQuantity(qty);
                    } else {
                        // if quantity equals 0, save item and break
                        item = scItem;
                        break;
                    }
                }
            }
            if (item != null){
                //togli dal carrello
                items.remove(item);
            }
        }
    }
    /**
     * Returns the list of <code>BoxCustomItems</code>.
     *
     * @return the <code>items</code> list
     * @see BoxCustomItem
     */
    public synchronized List<BoxCustomItem> getItems() {

        return items;
    }
    /**
     * Returns the sum of quantities for all items maintained in shopping cart
     * <code>items</code> list.
     *
     * @return the number of items in shopping cart
     * @see BoxCustomItem
     */
    public synchronized int getNumberOfItems() {

        numberOfItems = 0;

        for (BoxCustomItem scItem : items) {

            numberOfItems += scItem.getQuantity();
        }

        return numberOfItems;
    }
    /**
     * Returns the sum of the box price multiplied by the quantity for all
     * items in box custom list. This is the total cost excluding the surcharge.
     *
     * @return the cost of all items times their quantities
     * @see BoxCustomItem
     */
    public synchronized double getSubtotal() {

        double amount = 0;

        for (BoxCustomItem scItem : items) {

            Prodotto prod = (Prodotto) scItem.getProdotto();
            amount += (scItem.getQuantity() * prod.getPrezzo().doubleValue());
        }

        return amount;
    }
     /**
     * Calculates the total cost of the order. This method adds the subtotal to
     * the designated surcharge and sets the <code>total</code> instance variable
     * with the result.
     *
     * @param surcharge the designated surcharge for all orders
     * @see BoxCustomItem
     */
    public synchronized void calculateTotal(String surcharge) {

        double amount = 0;

        // cast surcharge as double
        double s = Double.parseDouble(surcharge);

        amount = this.getSubtotal();
        amount += s;

        total = amount;
    }

    /**
     * Returns the total cost of the order for the given
     * <code>BoxCustom</code> instance.
     *
     * @return the cost of all items times their quantities plus surcharge
     */
    public synchronized double getTotal() {

        return total;
    }

    /**
     * Empties the shopping cart. All items are removed from the box custom
     * <code>items</code> list, <code>numberOfItems</code> and
     * <code>total</code> are reset to '<code>0</code>'.
     *
     * @see BoxCustomItem
     */
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }

    
}
