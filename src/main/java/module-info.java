module com.lucaswarwick02.terrariasaveeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.codec;


    opens com.lucaswarwick02.terrariasaveeditor to javafx.fxml;
    exports com.lucaswarwick02.terrariasaveeditor;
}