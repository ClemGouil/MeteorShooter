module meteorshooter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens meteorshooter to javafx.fxml;
    exports meteorshooter;
}
