/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.Cart;
import sample.product.ProductDTO;

/**
 *
 * @author Admin
 */
public class UpdateCartController extends HttpServlet {

    public static final String ERROR = "viewCart.jsp";
    public static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productQuantityAvailable = Integer.parseInt(request.getParameter("productQuantityAvailable"));
            String productID = request.getParameter("productID");
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            if (productQuantity > productQuantityAvailable) {
                String message = "The number of available products is not enough!!!Available is " + productQuantityAvailable;
                request.setAttribute("SHOPPING_MESSAGE", message);
            } else {
                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("CART");
                ProductDTO product = null;
                if (cart != null) {
                    for (ProductDTO dto : cart.getCart().values()) {
                        if (dto.getProductID().equals(productID)) {
                            String productName = dto.getProductName();
                            float price = dto.getPrice();
                            String categoryID = dto.getCategoryID();
                            String image = dto.getImage();
                            String status= dto.getStatus();
                            product = new ProductDTO(productID, productName, image, price, productQuantity, categoryID, "", "", status);
                            product.setQuantityAv(productQuantityAvailable);
                            break;
                        }
                    }
                    cart.update(productID, product);
                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at UpdateCartController " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
