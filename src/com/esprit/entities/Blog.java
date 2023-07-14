/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author User
 */
public class Blog {

    private int idBlog;
    private int idUtilisateur;
    private String titre;
    private String nomUtilisateur;
    private String description;

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public Blog(int idBlog, int idUtilisateur, String titre, String nomUtilisateur, String description) {
        this.idBlog = idBlog;
        this.idUtilisateur = idUtilisateur;
        this.titre = titre;
        this.nomUtilisateur = nomUtilisateur;
        this.description = description;
    }

    public Blog(int idBlog, int idUtilisateur, String titre, String description) {
        this.idBlog = idBlog;
        this.idUtilisateur = idUtilisateur;
        this.titre = titre;
        this.description = description;
    }

    public Blog(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Blog(int idUtilisateur, String titre, String description) {
        this.idUtilisateur = idUtilisateur;
        this.titre = titre;
        this.description = description;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" + "idBlog=" + idBlog + ", idUtilisateur=" + idUtilisateur + ", titre=" + titre + ", nomUtilisateur=" + nomUtilisateur + ", description=" + description + '}';
    }

}
