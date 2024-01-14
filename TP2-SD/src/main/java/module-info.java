module ma.enset.tp2sd {
    requires javafx.controls;
    requires javafx.fxml;


    opens ma.enset.tp2sd to javafx.fxml;
    exports ma.enset.tp2sd;
    exports ma.enset.blocking to javafx.graphics;
    exports ma.enset.notblocking to javafx.graphics;
}