/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import java.util.List;
import com.codename1.ui.Form;
import com.esprit.entities.Blog;
import com.esprit.services.ServiceBlog;

/**
 * SearchForm class represents a form for searching blogs.
 * It allows users to enter the name of a blog and displays the details if found.
 */
public class SearchForm extends Form {
    private TextField txtBlogName;
    private ServiceBlog sb;
    private List<Blog> blogs;

    public SearchForm(Form previousForm) {
        super("Rechercher", BoxLayout.y());
        sb = new ServiceBlog();
        blogs = sb.afficher();
        createGUI();
        addActions(previousForm);
    }

    private void createGUI() {
        txtBlogName = new TextField("", "Nom du blog");
        
        this.addAll(new Label("Entrez le nom du blog :"), txtBlogName);
    }

    private void addActions(Form previousForm) {
        Button btnSearch = new Button("Rechercher");
        btnSearch.addActionListener((evt) -> {
            String blogName = txtBlogName.getText();
            Blog blog = searchBlogByName(blogName);

            if (blog != null) {
                // Display the blog content
                Dialog.show("Détails du blog", "Contenu du blog: " + blog.getDescription(), "OK", null);
            } else {
                // Display a message indicating the blog was not found
                Dialog.show("Blog non trouvé", "Le blog n'a pas été trouvé.", "OK", null);
            }

            // Show the previous form
            previousForm.showBack();
        });

        this.add(btnSearch);
    }

    private Blog searchBlogByName(String blogName) {
        // Perform the search in the blog list based on the blog name
        for (Blog blog : blogs) {
            if (blog.getTitre().equalsIgnoreCase(blogName)) {
                return blog;
            }
        }
        return null; // Blog not found
    }
}
