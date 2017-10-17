package members;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub Rybicki
 */
public class Device {

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    private int memberId;
    private int deviceID;
    private String cpu;
    private boolean dedicatedGpu;//if true,
    private String gpu;     //sepcify a gpu - not required.
    
    public Device(int memberId, String cpu, boolean dedicatedGpu, String GPU){
        //this.deviceID=deviceID;
        this.memberId=memberId;
        this.cpu=cpu;
        this.dedicatedGpu=dedicatedGpu;
        if(dedicatedGpu)
            this.gpu=GPU;
        else
            this.dedicatedGpu=false;
    }
    public Device(int deviceID, int memberId, String cpu, boolean dedicatedGpu, String GPU){
        this.deviceID=deviceID;
        this.memberId=memberId;
        this.cpu=cpu;
        this.dedicatedGpu=dedicatedGpu;
        if(dedicatedGpu)
            this.gpu=GPU;
        else
            this.dedicatedGpu=false;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setDedicatedGpu(boolean dedicatedGpu) {
        this.dedicatedGpu = dedicatedGpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public String getCpu() {
        return cpu;
    }

    public boolean isDedicatedGpu() {
        return dedicatedGpu;
    }

    public String getGpu() {
        return gpu;
    }
}
