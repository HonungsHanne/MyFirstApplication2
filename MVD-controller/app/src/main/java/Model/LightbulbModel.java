package Model;

/**
 * Created by Kevin on 2016-09-27.
 */

public class LightbulbModel extends DeviceModel {
    public String red = "0";
    public String green = "0";
    public String blue = "0";
    public String white = "0";
    public String color = "#00000000";
    ////String deviceId;
    //String name;
    //String address;
    //String status;

    public LightbulbModel(String deviceId, String name, String address, String status) {
        super(deviceId,name,address,status);
       // this.deviceId = deviceId;
       /// this.name = name;
        //this.address = address;
        //this.status = status;
    }
}
