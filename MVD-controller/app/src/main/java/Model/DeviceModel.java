package Model;

/**
 * Created by dee on 2016-09-26.
 */

public class DeviceModel {
    public int deviceID;
    public String name;
    public int address;
    public boolean status;

    public DeviceModel(int deviceID, String name, int address,boolean status){
        this.deviceID=deviceID;
        this.name=name;
        this.address=address;
        this.status=status;
    }
}
