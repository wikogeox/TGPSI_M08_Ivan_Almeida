module com.school.javafxblanc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.school.javafxblanc to javafx.fxml;
    exports com.school.javafxblanc;
}