package com.example.my_finances;

public class Card {
    String tittle;
    boolean favourite;
    String annotation;
    double score;

    public Card(String tittle,String annotation,boolean favourite,double score){
        this.tittle=tittle;
        this.annotation=annotation;
        this.favourite=favourite;
        this.score=score;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}

