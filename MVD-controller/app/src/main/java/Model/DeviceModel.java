package Model;

/**
 * Created by dee on 2016-09-26.
 */

public class DeviceModel {
    public int id;
    public String name;
    public int deviceAdress;
    public boolean status;

    public DeviceModel(){

    }
    public int id() {
        return id;
    }

    public void setDeviceId(int deviceID) {
        this.id = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeviceAdress() {
        return deviceAdress;
    }

    public void setAddress(int address) {
        this.deviceAdress = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
