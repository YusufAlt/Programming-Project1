module com.example.rolitgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop; // Dit kan nodig zijn voor basisfunctionaliteit

    exports com.example.rolitgame to javafx.graphics;
    exports com.example.rolitgame.model to javafx.graphics;
    exports com.example.rolitgame.View.about to javafx.graphics;
    exports com.example.rolitgame.View.aantalSpelersSelecter to javafx.graphics;
    exports com.example.rolitgame.View.plaats to javafx.graphics;
    exports com.example.rolitgame.View.naamSelecter to javafx.graphics;
    exports com.example.rolitgame.View.startScherm to javafx.graphics;
    exports com.example.rolitgame.View.winaarscherm to javafx.graphics;
    // Voeg ook andere pakketten toe die je wilt exporteren voor gebruik door andere modules
}
