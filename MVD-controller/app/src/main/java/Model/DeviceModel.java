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
    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
