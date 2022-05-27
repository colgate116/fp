package com.example.my_finances;

public class Transactions {
    boolean income;
    double spending;
    String category;
    public Transactions(boolean income,double spending,String category){
        this.income=income;
        this.spending=spending;
        this.category=category;
    }


    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public double getSpending() {
        return spending;
    }

    public void setSpending(double spending) {
        this.spending = spending;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
