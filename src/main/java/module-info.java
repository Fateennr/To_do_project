module com.example.todo_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.todo_project to javafx.fxml;
    exports com.example.todo_project;
}