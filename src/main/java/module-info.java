module com.example.fx_5_charactergallery {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fx_5_charactergallery to javafx.fxml;
    exports com.example.fx_5_charactergallery;
}