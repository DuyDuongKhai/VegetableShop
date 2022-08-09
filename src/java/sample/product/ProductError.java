/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

/**
 *
 * @author Admin
 */
public class ProductError {
    private String productIDError;
    private String productNameError;
    private float productPriceError;
    private int productQuantityError;
    private String productImageError;    
    private String categoryError;
    private String statusError;
    private String messageError;
    private String importDateError;
    private String usingDateError;

    public ProductError() {
    }

    public ProductError(String productIDError, String productNameError, float productPriceError, int productQuantityError, String productImageError, String categoryError, String statusError, String messageError, String importDateError, String usingDateError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.productPriceError = productPriceError;
        this.productQuantityError = productQuantityError;
        this.productImageError = productImageError;
        this.categoryError = categoryError;
        this.statusError = statusError;
        this.messageError = messageError;
        this.importDateError = importDateError;
        this.usingDateError = usingDateError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public String getUsingDateError() {
        return usingDateError;
    }

    public void setUsingDateError(String usingDateError) {
        this.usingDateError = usingDateError;
    }



    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public float getProductPriceError() {
        return productPriceError;
    }

    public void setProductPriceError(float productPriceError) {
        this.productPriceError = productPriceError;
    }

    public int getProductQuantityError() {
        return productQuantityError;
    }

    public void setProductQuantityError(int productQuantityError) {
        this.productQuantityError = productQuantityError;
    }

    public String getProductImageError() {
        return productImageError;
    }

    public void setProductImageError(String productImageError) {
        this.productImageError = productImageError;
    }

    public String getCategoryError() {
        return categoryError;
    }

    public void setCategoryError(String categoryError) {
        this.categoryError = categoryError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    
}
