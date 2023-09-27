/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


public class Load {
     int number;
     int idNumber,productId;
     String loadingDate,customerName,workerName,loadedAmount,remainingAmount;

    public Load(int idNumber , String loadingDate, String customerName, String workerName, String loadedAmount, String remainingAmount,int productId) {
        this.idNumber = idNumber;
        this.productId = productId;
        this.loadingDate = loadingDate;
        this.customerName = customerName;
        this.workerName = workerName;
        this.loadedAmount = loadedAmount;
        this.remainingAmount = remainingAmount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getLoadedAmount() {
        return loadedAmount;
    }

    public void setLoadedAmount(String loadedAmount) {
        this.loadedAmount = loadedAmount;
    }

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
     
}
