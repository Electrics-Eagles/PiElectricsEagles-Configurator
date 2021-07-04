module PiElectricsEagles.PiD.Configurator.main {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires  com.fazecast.jSerialComm;
    requires com.jfoenix;
    requires java.desktop;
    requires javafx.fxml;
    requires eu.hansolo.tilesfx;
    exports  api ;
    opens api to javafx.fxml,javafx.graphics;

}