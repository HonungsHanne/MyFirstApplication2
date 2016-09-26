package Model;

/**
 * Created by dee on 2016-09-26.
 */

public class DeviceModel {
    public String id;
    public String name;
    public String deviceAdress;
    public String status;

    public DeviceModel(String deviceID, String name, String address,String status){
        this.id=deviceID;
        this.name=name;
        this.deviceAdress=address;
        this.status=status;
    }
    public String id() {
        return id;
    }

    public void setDeviceId(String deviceID) {
        this.id = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceAdress() {
        return deviceAdress;
    }

    public void setAddress(String address) {
        this.deviceAdress = address;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString(){
        return id + " " + name + " " + deviceAdress;
    }
}
