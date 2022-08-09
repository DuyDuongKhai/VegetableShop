/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "addProduct.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            float price = Float.parseFloat(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            String status = request.getParameter("status");
            boolean check = true;
            ProductError userError = new ProductError();
            if (productID.length() > 10 || productID.length() < 0) {
                userError.setProductIDError("UserID leght must be [5,10]");
                check = false;
            }
            if (productName.length() > 50 || productName.length() < 0) {
                userError.setProductNameError("Full Name leght must be [5,50]");
                check = false;
            }
            if (image.length() > 250 || image.length() < 0) {
                userError.setProductImageError("roleID leght must be [1,5]");
                check = false;
            }
//            if (price<0) {
//                userError.setProductPriceError("Address leght must be [5,50]");
//                check = false;
//            }
//            if (quantity<0) {
//                userError.setProductQuantityError("Birthday leght must be [5,50]");
//                check = false;
//            }
            if (categoryID.length() > 15 || categoryID.length() < 0) {
                userError.setCategoryError("Phone leght must be [5,15]");
                check = false;
            }
            if (importDate.length() > 50 || importDate.length() < 0) {
                userError.setImportDateError("Email leght must be [5,50]");
                check = false;
            }
            if (usingDate.length() > 10 || usingDate.length() < 0) {
                userError.setUsingDateError("Full Name leght must be [1,10]");
                check = false;
            }if (status.length() > 10 || status.length() < 0) {
                userError.setStatusError("Full Name leght must be [1,10]");
                check = false;
            }
            
            if (check) {
                ProductDAO dao = new ProductDAO();
                ProductDTO user = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate,status );
                boolean checkDuplicate = dao.checkDuplicate(productID);
                if (checkDuplicate) {
                    userError.setProductIDError("Duplicate productID: " + productID + " !");
                    request.setAttribute("PRODUCT_ERROR", userError);
                } else {
                    boolean checkInsert = dao.insert(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    } else {
                        userError.setMessageError("Can not insert, unknow error!");
                        request.setAttribute("PRODUCT_ERROR", userError);
                    }
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
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
