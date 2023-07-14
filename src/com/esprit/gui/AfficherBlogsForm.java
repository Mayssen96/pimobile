/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Blog;
import com.esprit.services.ServiceBlog;
import java.util.List;

/**
 *
 * @author User
 */
public class AfficherBlogsForm extends Form {

    private Form previousForm;
  
    public AfficherBlogsForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    
    
    
    private void OnGui() {
      
          Container contentContainer = new Container(BoxLayout.y());
        
        ServiceBlog sb = new ServiceBlog();
        List<Blog> blogs = sb.afficher();

        for (Blog blog : blogs) {
            String titre = String.valueOf(blog.getTitre());
            String desc = String.valueOf(blog.getDescription());
            String nom = String.valueOf(blog.getNomUtilisateur());
            String labelText = "Utilisateur: " + nom + ", Titre: " + titre + ", Description: " + desc;
            SpanLabel spanLabel = new SpanLabel(labelText);
            contentContainer.add(spanLabel);
        }

        this.add(contentContainer);
    }

        private void addActions() {
       
 
        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            previousForm.showBack();
        });
       
    }
}
