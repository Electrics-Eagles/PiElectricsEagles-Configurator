package api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Api {


    public static String[] perpare_Values() {
        String roll = "sudo pid-config roll " + Controller.p_roll_val.toString() + " "+ Controller.i_roll_val.toString()+" "+Controller.d_roll_val.toString()+" 400";
        String pitch = "sudo pid-config pitch " + Controller.p_roll_val.toString() + " "+ Controller.i_roll_val.toString()+" "+Controller.d_roll_val.toString()+" 400";
        String  yaw = "sudo pid-config yaw " + Controller.p_yaw_val.toString() + " "+ Controller.i_yaw_val.toString()+" "+Controller.d_yaw_val.toString()+" 400";
        String[] data={roll,pitch,yaw};
        return data;
    }

    public static void move_config() {
     String move_config="sudo mv pi_drone.log  "+Controller.p_roll_val.toString()+" \r\n";
        Controller.arduino.serialWrite(move_config);
    }

    public static void kill_proccess() {
        String kill="killall pielectricseagles \r\n";
        Controller.arduino.serialWrite(kill);
    }


    public static void rerun() {
        String kill="sudo pielectricseagles & \r\n";
        Controller.arduino.serialWrite(kill);
    }


    public static void send_Value() throws InterruptedException {

        for (int a=0; a< perpare_Values().length; a++){
            Controller.arduino.serialWrite(perpare_Values()[a]+"\r\n");
            Thread.sleep(1000);


        }





    }


    public static void save_to_dtb() throws IOException {
        File file = new File("database.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(Controller.p_roll_val.toString()+";"+Controller.i_roll_val.toString()+";"+Controller.d_roll_val+";"+Controller.better+";"+Controller.rating+"\n");
        fr.close();

    }




}
