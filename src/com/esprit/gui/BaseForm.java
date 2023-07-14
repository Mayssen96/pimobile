/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.db.Database;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.Layout;
import com.esprit.services.ServiceBlog;
import java.io.IOException;

/**
 *
 * @author User
 */
public class BaseForm extends Form {

    protected Database db;

    public BaseForm(String title, Layout l) {
        super(title, l);
        addActions();
      ServiceBlog sb = new ServiceBlog();

    }

    private void addActions() {
        getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            new HomeForm().show();
        });
        getToolbar().addMaterialCommandToSideMenu("Add", FontImage.MATERIAL_ADD, e -> {
            new AjouterBlogForm(this).show();
        });
      
    }

}
