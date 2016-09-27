package Model;

/**
 * Created by Kevin on 2016-09-27.
 */

public class LightbulbModel extends DeviceModel {
    String red = "0";
    String green = "0";
    String blue = "0";
    String white = "0";
    String[] color = new String[4];
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
