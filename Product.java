/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

/**
 *
 * @author Firooz
 */
public class Product {

    int idNumber, productAmount;
    int number;
    String name, weight, evacuationDate, checkNumber;

    public Product(int idNumber, String name, String weight, String evacuationDate, String checkNumber, int productAmount) {
        this.idNumber = idNumber;
        this.productAmount = productAmount;
        this.name = name;
        this.weight = weight;
        this.evacuationDate = evacuationDate;
        this.checkNumber = checkNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEvacuationDate() {
        return evacuationDate;
    }

    public void setEvacuationDate(String evacuationDate) {
        this.evacuationDate = evacuationDate;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

}
