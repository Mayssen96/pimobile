package com.esprit.services;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.esprit.entities.Rate;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceRate implements IService<Rate> {

    private final String URI = Statics.BASE_URL + "/rate/";

    private boolean responseResult;
    private List<Rate> rates;

    public ServiceRate() {
        rates = new ArrayList<>();
    }

    public boolean ajouter(Rate r) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");
                for (Map<String, Object> obj : list) {
                    int rate = (int) Float.parseFloat(obj.get("rate").toString());
                    int idu = (int) Float.parseFloat(obj.get("utilisateur").toString());
                    int idb = (int) Float.parseFloat(obj.get("idBlog").toString());

                    rates.add(new Rate(idu, idb, rate));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        
       return responseResult;    
    }
  
    public Button createRateButton(Rate rate) {
        Button rateButton = new Button("Rate");
        rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ajouter(rate);
            }
        });
        return rateButton;
    }

    @Override
    public boolean modifier(Rate p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean supprimer(Rate p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Rate> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
