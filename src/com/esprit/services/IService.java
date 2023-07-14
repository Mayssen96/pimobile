/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Blog;
import java.util.List;
import javafx.collections.ObservableList;


/**Personne
 *
 * @author User
 */
public interface IService <T>{
    public boolean ajouter(T p);
    public boolean modifier(T p);
    public boolean supprimer(T p);
    public List <T> afficher();
}