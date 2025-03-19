    package com.example.rolitgame.View.winaarscherm;

    import com.example.rolitgame.model.ModelSpel;
    import com.example.rolitgame.klassen.Kleur;
    import com.example.rolitgame.klassen.HighScore;

    import java.util.Map;

    public class WinnaarPresenter {

        private WinnaarView winnaarView;
        private ModelSpel model;
        private HighScore highScore;

        public WinnaarPresenter(WinnaarView winnaarView, ModelSpel model) {
            this.winnaarView = winnaarView;
            this.model = model;
            this.highScore = new HighScore();
            model.toonWinnaar(winnaarView,highScore);
        }




    }
