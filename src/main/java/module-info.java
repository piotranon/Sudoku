module org.piotranon {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.logging;
    requires org.apache.logging.log4j.core;

    opens org.piotranon to javafx.fxml;
    exports org.piotranon;
}