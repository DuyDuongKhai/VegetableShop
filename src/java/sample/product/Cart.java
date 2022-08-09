/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Cart {

    private Map<String, ProductDTO> cart;

    public Cart() {
    }

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public List<ProductDTO> getList() {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductDTO prt : this.cart.values()) {
            list.add(prt);
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    public void add(ProductDTO prt) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(prt.getProductID())) {
            int currentQuantity = this.cart.get(prt.getProductID()).getQuantity();
            prt.setQuantity(currentQuantity + prt.getQuantity());
        }
        cart.put(prt.getProductID(), prt);
    }

    public void delete(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(String id, ProductDTO newPrt) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, newPrt);
        }
    }
}
