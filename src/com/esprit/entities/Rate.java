/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author User
 */
public class Rate {

    private int utilisateur;
    private int idRating;
    private int idBlog;
    private int rating;
    private String nomUtilisateur;
    private String titreBlog;

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getTitreBlog() {
        return titreBlog;
    }

    public void setTitreBlog(String titreBlog) {
        this.titreBlog = titreBlog;
    }

    public Rate(int utilisateur, int idBlog, int rating) {
        this.utilisateur = utilisateur;
        this.idBlog = idBlog;
        this.rating = rating;
    }

    public Rate(int idRating, int utilisateur, int idBlog, int rating) {
        this.utilisateur = utilisateur;
        this.idRating = idRating;
        this.idBlog = idBlog;
        this.rating = rating;
    }

    public Rate(int idBlog, int rating) {
        this.idBlog = idBlog;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rate{" + "utilisateur=" + utilisateur + ", idRating=" + idRating + ", idBlog=" + idBlog + ", rating=" + rating + ", nomUtilisateur=" + nomUtilisateur + ", titreBlog=" + titreBlog + '}';
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public Rate(int rating, String nomUtilisateur, String titreBlog) {
        this.rating = rating;
        this.nomUtilisateur = nomUtilisateur;
        this.titreBlog = titreBlog;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
