/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Blog;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class ServiceBlog implements IService<Blog> {

    private boolean responseResult;
    private List<Blog> blogs;
    private final String URI = Statics.BASE_URL + "/blog/";

    public ServiceBlog() {
        blogs = new ArrayList();
    }

    @Override

    public List<Blog> afficher() {

        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("GET");
        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

                for (Map<String, Object> obj : list) {
                    int id = (int) Float.parseFloat(obj.get("idBlog").toString());
                    int idu = (int) Float.parseFloat(obj.get("idUtilisateur").toString());
                    String titre = obj.get("titre").toString();
                    String prenom = obj.get("Prenom").toString();
                    String nom = obj.get("Nom").toString();
                    String description = obj.get("description").toString();
                    blogs.add(new Blog(id,idu,titre,nom +' ' + prenom , description));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return blogs;

    }

    public boolean ajouter(Blog b) {

        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("titre", b.getTitre());
        request.addArgument("description", b.getDescription());
        request.addArgument("idUtilisateur", String.valueOf(b.getIdUtilisateur()));
        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;

    }

    public boolean modifier(Blog b) {

        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI + b.getIdUtilisateur());
        request.setHttpMethod("PUT");
        request.addArgument("idBlog", String.valueOf(b.getIdBlog()));
        request.addArgument("idUtilisateur",String.valueOf(b.getIdUtilisateur()));
        request.addArgument("titre", b.getTitre());
        request.addArgument("description", b.getDescription());
        request.addArgument("idUtilisateur",String.valueOf(b.getIdUtilisateur()));
     
        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;

    }

    public boolean supprimer(Blog b) {

        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + b.getIdBlog());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return responseResult;

    }

//    public boolean isTitreExists(String titre) {
//        String query = "SELECT COUNT(*) FROM Blog WHERE titre = ?";
//        try ( PreparedStatement statement = cnx.prepareStatement(query)) {
//            statement.setString(1, titre);
//            try ( ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int count = resultSet.getInt(1);
//                    return count > 0;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error checking if titre exists: " + e.getMessage());
//        }
//        return false;
//    }
}
