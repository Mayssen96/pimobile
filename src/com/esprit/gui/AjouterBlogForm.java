/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Blog;
import com.esprit.services.ServiceBlog;
import com.esprit.services.ServiceUtilisateur;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class AjouterBlogForm extends Form {

    private TextField tfDescription;
    private TextField tfTitre;
    private ComboBox<String> comboUser;
    private Button btnAjout;
    private Button btnAfficher;
    private Form previousForm;

    String url = Statics.BASE_URL + "/utilisateur"; // Utilisation de l'URL de base
    ConnectionRequest request = new ConnectionRequest();

    public AjouterBlogForm(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        comboUser = new ComboBox<>();
        tfDescription = new TextField(null, "description");
        tfTitre = new TextField(null, "titre");
        ServiceUtilisateur su = new ServiceUtilisateur();

        btnAjout = new Button("Ajouter");
        request.setUrl(url);
        request.setHttpMethod("GET");
       
        request.addResponseListener((NetworkEvent evt) -> {
            byte[] responseData = request.getResponseData();
            if (responseData != null) {
                String response = new String(responseData);
                try {
                    InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                    Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                    if (list != null) {
                        for (Map<String, Object> utilisateur : list) {
                            String nom = (String) utilisateur.get("Nom");
                            String prenom = (String) utilisateur.get("Prenom");
                            String nomComplet = prenom + " " + nom;
                            int id = (int) Float.parseFloat(utilisateur.get("id").toString()); // Conversion en entier
                            comboUser.addItem(nomComplet);
                            comboUser.putClientProperty(nomComplet, id); // Ajouter l'identifiant de l'utilisateur comme propriété du combo box
                        }

                    } else {
                        System.out.println("");
                    }

                    System.out.println(list);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        
//        Component[] components = {tfTitre, tfDescription, comboUser, btnAjout};
//        Container container = new Container();
//        container.setLayout(BoxLayout.y());
//        container.addAll(components);
//
//        this.add(container);
        
        this.addAll(tfDescription, tfTitre,comboUser, btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfTitre.getText().isEmpty() || tfDescription.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ServiceBlog sb = new ServiceBlog();

                String nomComplet = (String) comboUser.getSelectedItem();
                int idUser = (int) comboUser.getClientProperty(nomComplet);

                if (sb.ajouter(new Blog(idUser, tfTitre.getText(), tfDescription.getText()))) {
                    Dialog.show("SUCCESS", "Blog ajoutée !", "OK", null);
                } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }

            }
        });

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }

}
