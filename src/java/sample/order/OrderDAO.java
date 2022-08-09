/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sample.product.Cart;
import sample.product.ProductDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    private static final String CREATE_ORDER = "INSERT INTO [dbo].[tblOrder]\n"
            + "           ([orderID]\n"
            + "           ,[orderDate]\n"
            + "           ,[total]\n"
            + "           ,[userID]\n"
            + "           ,[status])\n"
            + "     VALUES\n"
            + "           (?,?,?,?,?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO [dbo].[tblOrderDetail]\n"
            + "           ([detailID]\n"
            + "           ,[price]\n"
            + "           ,[quantity]\n"
            + "           ,[orderID]\n"
            + "           ,[productID])\n"
            + "     VALUES\n"
            + "           (?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?\n"
            + "           ,?)";
    private static final String GET_ORDER_ID = "SELECT MAX(orderID) AS [orderID] FROM tblOrder";
    private static final String GET_PRODUCT_QUANTITY_CURRENT = "SELECT quantity FROM tblProduct WHERE productID =?";
    private static final String UPDATE_PRODUCT_QUANTITY = "UPDATE [tblProduct]\n"
            + "SET [quantity] = ?-?\n"
            + "WHERE [productID] = ?";

    public boolean createOrder(String userID, double total, Cart cart) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            Date nowDate = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String strNowDate = formater.format(nowDate);
            int status = 0;
            int count = 0;
            ptm = conn.prepareStatement("select min([orderID]) from [tblOrder]");
            rs = ptm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            count++;
            ptm = null;
            ptm = conn.prepareStatement(CREATE_ORDER);
            ptm.setInt(1, count);
            ptm.setString(2, strNowDate);
            ptm.setDouble(3, total);
            ptm.setString(4, userID);
            ptm.setInt(5, status);
            check = ptm.executeUpdate() > 0;
            if (check) {
                ptm = null;
                rs = null;
                count = 0;
                ptm = conn.prepareStatement("select min([detailID]) from [tblOrderDetail]");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
                count++;
                ptm = null;
                rs = null;
                ptm = conn.prepareStatement(GET_ORDER_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    for (ProductDTO prod : cart.getList()) {
                        ptm = null;
                        ptm = conn.prepareStatement(CREATE_ORDER_DETAIL);
                        ptm.setInt(1, count);
                        ptm.setDouble(2, prod.getPrice());
                        ptm.setInt(3, prod.getQuantity());
                        ptm.setInt(4, orderID);
                        ptm.setString(5, prod.getProductID());
                        check = ptm.executeUpdate() > 0;
                        if (check) {
                            int currentQuanity = 0;
                            ptm = null;
                            ptm = conn.prepareStatement(GET_PRODUCT_QUANTITY_CURRENT);
                            ptm.setString(1, prod.getProductID());
                            rs = ptm.executeQuery();
                            if (rs.next()) {
                                currentQuanity = rs.getInt("quantity");
                                ptm = null;
                                ptm = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY);
                                ptm.setInt(1, currentQuanity);
                                ptm.setInt(2, prod.getQuantity());
                                ptm.setString(3, prod.getProductID());
                                check = ptm.executeUpdate() > 0;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
