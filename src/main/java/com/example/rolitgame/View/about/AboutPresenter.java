package com.example.rolitgame.View.about;

import com.example.rolitgame.model.ModelSpel;

public class AboutPresenter {
    private AboutView aboutView;
    private ModelSpel model;

    public AboutPresenter(AboutView aboutView, ModelSpel model) {
        this.aboutView = aboutView;
        this.model = model;
        addEventHandlers();
    }

    private void addEventHandlers() {
        aboutView.getOkeBtn().setOnAction(actionEvent -> {
            aboutView.getScene().getWindow().hide();
        });
    }
}
