package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Blog;
import com.esprit.services.ServiceBlog;

public class UpdateBlogForm extends Form {
 Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");
    private Blog blog;
    private TextField tfTitre;
    private TextField tfDescription;
    private int idBlog ;
    private int idu ;
    public UpdateBlogForm(Blog blog) {
        super("Modifier le blog", BoxLayout.y());
        this.blog = blog;
        createGUI();
        addActions();
    }

    private void createGUI() {
        tfTitre = new TextField(blog.getTitre(), "Titre");
        tfDescription = new TextField(blog.getDescription(), "Description");
        idBlog = blog.getIdBlog();
        idu = blog.getIdUtilisateur();
       

        add(new Label("Modifier le blog:"));
        add(tfTitre);
        add(tfDescription);
        add(saveButton);
        add(cancelButton);
    }

    private void addActions() {
        saveButton.addActionListener(e -> {
            String titre = tfTitre.getText();
            String description = tfDescription.getText();
            
            // Update the blog with the new values
            blog.setTitre(titre);
            blog.setDescription(description);
            blog.setIdBlog(idBlog);
            blog.setIdUtilisateur(idu);
            
            
            ServiceBlog sb = new ServiceBlog();
            boolean updateResult = sb.modifier(blog);
            if (updateResult) {
                Dialog.show("Succès", "Le blog a été mis à jour avec succès.", "OK", null);
                // Refresh the home form to reflect the changes
                HomeForm homeForm = new HomeForm();
                homeForm.show();
            } else {
                Dialog.show("Erreur", "Échec de la mise à jour du blog.", "OK", null);
            }
        });

        cancelButton.addActionListener(e -> {
            // Simply go back to the home form without making any changes
            HomeForm homeForm = new HomeForm();
            homeForm.show();
        });
    }
}
