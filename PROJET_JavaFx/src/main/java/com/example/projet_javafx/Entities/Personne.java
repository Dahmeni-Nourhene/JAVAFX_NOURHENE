package com.example.projet_javafx.Entities;

public class Personne {
    public int id;
    public String nom;
    public String prenom;
    public int age;

    public Personne(int id, String nom,String prenom,int age) {

        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
    }

    @Override
    public String toString() {
        return "Entitys.Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
