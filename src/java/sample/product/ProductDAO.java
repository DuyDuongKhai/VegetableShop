/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class ProductDAO {

    public static final String SEARCH = "SELECT productID, productName, image, price, quantity, categoryID, importDate, usingDate, status FROM tblProduct WHERE productName like ?";
    public static final String GETCATEGORY = "SELECT * FROM tblCategory";
    public static final String ADD = "INSERT INTO tblProduct(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status) VALUES(?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_PRODUCT_ADMIN = "UPDATE tblProduct SET productName=?, image=?, price=?, quantity=?, categoryID=?, importDate=?, usingDate=?, status=? WHERE productID=?";
    public static final String ACTIVE_PRODUCT="SELECT productID, productName, image, price, quantity, categoryID, importDate, usingDate, status FROM tblProduct";
    public static final String ALL_PRODUCT="SELECT * FROM tblProduct";
    private static final String DUPLICATE = "SELECT productID FROM tblProduct WHERE productID=?";
    private static final String INSERT = "INSERT INTO tblProduct(productID, productName,  image, price, quantity, categoryID, importDate, usingDate, status) VALUES (?,?,?,?,?,?,?,?,?)";
   
    public static void main(String[] args) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        list = dao.getListAllProduct();
        System.out.println(list);
    }
    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = Float.parseFloat(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String categoryId = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");  
                    String status= rs.getString("status");
                    list.add(new ProductDTO(productID, productName, image, price, quantity, categoryId, importDate, usingDate, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<CategoryDTO> getCategory() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                stm = conn.prepareStatement(GETCATEGORY);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    list.add(new CategoryDTO(categoryID, categoryName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean addNewProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                stm = conn.prepareStatement(ADD);
                stm.setString(1, product.getProductID());
                stm.setString(2, product.getProductName());
                stm.setString(3, product.getImage());
                stm.setString(4, Float.toString(product.getPrice()));
                stm.setString(5, Integer.toString(product.getQuantity()));
                stm.setString(6, product.getCategoryID());
                stm.setString(7, product.getImportDate());
                stm.setString(8, product.getUsingDate());
                stm.setString(9, product.getStatus());
                check = stm.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                stm = conn.prepareStatement(UPDATE_PRODUCT_ADMIN);
                stm.setString(1, product.getProductName());
                stm.setString(2, product.getImage());
                stm.setString(3, Float.toString(product.getPrice()));
                stm.setString(4, Integer.toString(product.getQuantity()));                
                stm.setString(5, product.getCategoryID());
//                System.out.println(product.getCategoryID());
                stm.setString(6, product.getImportDate());
                stm.setString(7, product.getUsingDate());
                if ("available".equals(product.getStatus())) {
                    stm.setString(8, "True");
                } else if ("notAvailable".equals(product.getStatus())) {
                    stm.setString(8, "False");
                }
                stm.setString(9, product.getProductID());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public List<ProductDTO> getListActiveProduct() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ACTIVE_PRODUCT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    float price = Float.parseFloat(rs.getString("price"));
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");                    
                    String image = rs.getString("image");
                    String categoryId = rs.getString("categoryID");
                    String status = rs.getString("status");
                    if ("True".equals(status)) {
                        list.add(new ProductDTO(productID, productName, image, price, quantity, categoryId, importDate, usingDate, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    public List<ProductDTO> getListAllProduct() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ALL_PRODUCT);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    float price = Float.parseFloat(rs.getString("price"));
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");                    
                    String image = rs.getString("image");
                    String categoryId = rs.getString("categoryID");
                    String status = rs.getString("status");                    
                    list.add(new ProductDTO(productID, productName, image, price, quantity, categoryId, importDate, usingDate, status));
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    public boolean checkDuplicate(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public boolean insert(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(INSERT);
            ptm.setString(1, product.getProductID());
            ptm.setString(2, product.getProductName());
            ptm.setString(3, product.getImage());
            ptm.setFloat(4, product.getPrice());
            ptm.setInt(5, product.getQuantity());
            ptm.setString(6, product.getCategoryID());
            ptm.setString(7, product.getImportDate());
            ptm.setString(8, product.getUsingDate());
            ptm.setString(9, product.getStatus());
            check = ptm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            return check;
        }
    }
    
    public boolean deteleProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "Update tblProduct "
                    + "set status ='False' "
                    + "where productID = ? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, productID);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
   
}
