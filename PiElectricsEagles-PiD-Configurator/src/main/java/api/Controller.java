package api; /**
 * Sample Skeleton for 'main.fxml' api.Controller Class
 */

import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXCheckBox;

import java.io.IOException;
import java.math.BigDecimal;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import eu.hansolo.tilesfx.addons.HappinessIndicator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {
    public static BigDecimal p_roll_val=  new BigDecimal("0.0");
    public static BigDecimal i_roll_val =  new BigDecimal("0.0");
    public static BigDecimal d_roll_val=  new BigDecimal("0.0");
    public static BigDecimal p_yaw_val =  new BigDecimal("0.0");
    public static BigDecimal i_yaw_val=  new BigDecimal("0.0");
    public static BigDecimal d_yaw_val =  new BigDecimal("0.0");
    public static BigDecimal diff= new BigDecimal("0.01");
    public  static  Arduino  arduino;
    public static  boolean better=false;
    public static String rating;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private Circle status;

    @FXML // fx:id="background"
    private AnchorPane background; // Value injected by FXMLLoader

    @FXML // fx:id="p_roll"
    private TextField p_roll; // Value injected by FXMLLoader

    @FXML // fx:id="i_roll"
    private TextField i_roll; // Value injected by FXMLLoader

    @FXML // fx:id="d_roll"
    private TextField d_roll; // Value injected by FXMLLoader

    @FXML // fx:id="p_yaw"
    private TextField p_yaw; // Value injected by FXMLLoader

    @FXML // fx:id="i_yaw"
    private TextField i_yaw; // Value injected by FXMLLoader

    @FXML // fx:id="d_yaw"
    private TextField d_yaw; // Value injected by FXMLLoader

    @FXML // fx:id="level"
    private HappinessIndicator level; // Value injected by FXMLLoader

    @FXML // fx:id="slider"
    private JFXSlider slider; // Value injected by FXMLLoader

    @FXML // fx:id="checkbox"
    private JFXCheckBox checkbox; // Value injected by FXMLLoader

    @FXML // fx:id="comport"
    private JFXComboBox<String> comport; // Value injected by FXMLLoader



    @FXML
    private JFXComboBox<String> baudrate;

    @FXML
    void change_theme(MouseEvent event) {
        System.out.println("Theme changed");
        //checkbox.fire();
        checkbox.setCheckedColor(Color.rgb(247, 51, 120));
        if(checkbox.isSelected()){
            Main.scene.getStylesheets().add("light.css");
        }
        else {
            Main.scene.getStylesheets().add("dark.css");
        }
    }
    @FXML
    void add_d_roll(MouseEvent event) {
        d_roll_val=d_roll_val.add(diff);
        d_roll.setText(String.valueOf(d_roll_val));
    }

    @FXML
    void add_d_yaw(MouseEvent event) {
        d_yaw_val=d_yaw_val.add(diff);
        d_yaw.setText(String.valueOf(d_yaw_val));
    }

    @FXML
    void add_i_roll(MouseEvent event) {
        i_roll_val=i_roll_val.add(diff);
        i_roll.setText(String.valueOf(i_roll_val));
    }

    @FXML
    void connect(MouseEvent event) {

        arduino = new Arduino(comport.getValue(),Integer.parseInt(baudrate.getValue()));
        arduino.openConnection();
        status.setFill(Color.GREEN);
        System.out.println(arduino.getPortDescription());
    }
    @FXML
    void changed(MouseEvent event) {
        rating= String.valueOf(slider.getValue());
        if(slider.getValue() > 30){
            level.setHappiness(HappinessIndicator.Happiness.NEUTRAL);
        }
        if(slider.getValue() > 60) {
            level.setHappiness(HappinessIndicator.Happiness.HAPPY);

        }
        if(slider.getValue() < 30){
            level.setHappiness(HappinessIndicator.Happiness.UNHAPPY);
        }
        System.out.println(slider.getValue());
        level.setValue(slider.getValue()/100);

    }

    @FXML
    void add_i_yaw(MouseEvent event) {
        i_yaw_val=i_yaw_val.add(diff);
        i_yaw.setText(String.valueOf(i_yaw_val));
    }
    @FXML
    void subtract_d_roll(MouseEvent event) {
        d_roll_val=d_roll_val.subtract(diff);
        d_roll.setText(String.valueOf(d_roll_val));
    }

    @FXML
    void add_p_roll(MouseEvent event) {
        p_roll_val=p_roll_val.add(diff);
        p_roll.setText(String.valueOf(p_roll_val));
    }

    @FXML
    void add_p_yaw(MouseEvent event) {
        p_yaw_val=p_yaw_val.add(diff);
        p_yaw.setText(String.valueOf(p_yaw_val));
    }

    @FXML
    void no(MouseEvent event) throws IOException {
        better=false;
        Api.save_to_dtb();
    }

    @FXML
    void subtract_d_yaw(MouseEvent event) {
        d_yaw_val=d_yaw_val.subtract(diff);
        d_yaw.setText(String.valueOf(d_yaw_val));
    }

    @FXML
    void subtract_i_roll(MouseEvent event) {
        i_roll_val=i_roll_val.subtract(diff);
        i_roll.setText(String.valueOf(i_roll_val));
    }

    @FXML
    void subtract_i_yaw(MouseEvent event) {
        i_yaw_val=i_yaw_val.subtract(diff);
        i_yaw.setText(String.valueOf(i_yaw_val));
    }

    @FXML
    void subtract_p_yaw(MouseEvent event) {
        p_yaw_val=p_yaw_val.subtract(diff);
        p_yaw.setText(String.valueOf(p_yaw_val));
    }

    @FXML
    void sutract_p_roll(MouseEvent event) {
        p_roll_val=p_roll_val.subtract(diff);
        p_roll.setText(String.valueOf(p_roll_val));
    }

    @FXML
    void yes(MouseEvent event) throws IOException {
        better=true;
        Api.save_to_dtb();
    }

    @FXML
    void exit(MouseEvent event) {
    System.exit(1);
    }

    @FXML
    void next(MouseEvent event) throws InterruptedException {
        Api.send_Value();
        Thread.sleep(200);
        Api.move_config();
        Thread.sleep(200);
        Api.kill_proccess();
        Thread.sleep(200);
        Api.rerun();


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        status.setFill(javafx.scene.paint.Color.RED);

        SerialPort[] comPort = SerialPort.getCommPorts();
        System.out.println(":)");

        for(int i = 0; i < comPort.length; i++){
            System.out.println(comPort[i]);
            comport.getItems().add(comPort[i].getSystemPortName());
        }
        for (int i=0; i<100; i++){
            baudrate.getItems().add(String.valueOf(i*1200));
        }
        slider.setValue(0.0);
        level.setHappiness(HappinessIndicator.Happiness.UNHAPPY);
        level.setValue(slider.getValue());

        background.setId("background_ui");
        p_roll.setId("text");
        i_roll.setId("text");
        d_roll.setId("text");
        p_yaw.setId("text");
        i_yaw.setId("text");
        d_yaw.setId("text");
        assert background != null : "fx:id=\"background\" was not injected: check your FXML file 'main.fxml'.";
        assert p_roll != null : "fx:id=\"p_roll\" was not injected: check your FXML file 'main.fxml'.";
        assert i_roll != null : "fx:id=\"i_roll\" was not injected: check your FXML file 'main.fxml'.";
        assert d_roll != null : "fx:id=\"d_roll\" was not injected: check your FXML file 'main.fxml'.";
        assert p_yaw != null : "fx:id=\"p_yaw\" was not injected: check your FXML file 'main.fxml'.";
        assert i_yaw != null : "fx:id=\"i_yaw\" was not injected: check your FXML file 'main.fxml'.";
        assert d_yaw != null : "fx:id=\"d_yaw\" was not injected: check your FXML file 'main.fxml'.";
        assert level != null : "fx:id=\"level\" was not injected: check your FXML file 'main.fxml'.";
        assert slider != null : "fx:id=\"slider\" was not injected: check your FXML file 'main.fxml'.";
        assert checkbox != null : "fx:id=\"checkbox\" was not injected: check your FXML file 'main.fxml'.";

    }
}
