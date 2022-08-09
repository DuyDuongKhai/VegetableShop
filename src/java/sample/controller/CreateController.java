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
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author Admin
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String roleID = request.getParameter("roleID");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String status = request.getParameter("status");
            String confirm = request.getParameter("confirm");
            boolean check = true;
            UserError userError = new UserError();
            if (userID.length() > 10 || userID.length() < 5) {
                userError.setUserIDError("UserID leght must be [5,10]");
                check = false;
            }
            if (fullName.length() > 50 || fullName.length() < 5) {
                userError.setFullNameError("Full Name leght must be [5,50]");
                check = false;
            }
            if (password.equals(confirm) == false) {
                userError.setPasswordError("Hai password khong giong nhau");
                check = false;
            }
            if (roleID.length() > 5 || roleID.length() < 0) {
                userError.setRoleIDError("roleID leght must be [1,5]");
                check = false;
            }
            if (address.length() > 50 || address.length() < 5) {
                userError.setAddressError("Address leght must be [5,50]");
                check = false;
            }
            if (birthday.length() > 50 || birthday.length() < 5) {
                userError.setBirthdayError("Birthday leght must be [5,50]");
                check = false;
            }
            if (phone.length() > 15 || phone.length() < 5) {
                userError.setPhoneError("Phone leght must be [5,15]");
                check = false;
            }
            if (email.length() > 50 || email.length() < 5) {
                userError.setEmailError("Email leght must be [5,50]");
                check = false;
            }
            if (status.length() > 10 || fullName.length() < 0) {
                userError.setFullNameError("Full Name leght must be [1,10]");
                check = false;
            }
            
            if (check) {
                UserDAO dao = new UserDAO();
                UserDTO user = new UserDTO(userID, fullName, password, roleID, address, birthday, phone, email, status );
                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (checkDuplicate) {
                    userError.setUserIDError("Duplicate UserId: " + userID + " !");
                    request.setAttribute("USER_ERROR", userError);
                } else {
                    boolean checkInsert = dao.insert(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    } else {
                        userError.setMessageError("Can not insert, unknow error!");
                        request.setAttribute("USER_ERROR", userError);
                    }
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
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
