package org.dimigo.vaiohazard.Device;

/**
 * Created by YuTack on 2015-11-14.
 */
public class Components {
    public int skrew;
    public float thermalGrease;
    public int mainBoard;
    public int CPU;
    public int ram;
    public int SATA;
    public int liquidDisplay;
    public int hardDisk;
    public int battery;

    public Components(int skrew, float thermalGrease, int mainBoard, int CPU, int ram, int SATA, int liquidDisplay, int hardDisk, int battery) {
        this.skrew = skrew;
        this.thermalGrease = thermalGrease;
        this.mainBoard = mainBoard;
        this.CPU = CPU;
        this.ram = ram;
        this.SATA = SATA;
        this.liquidDisplay = liquidDisplay;
        this.hardDisk = hardDisk;
        this.battery = battery;
    }

    public Components() {

    }

    public void addComponets(Components components) {
        this.skrew += components.skrew;
        this.thermalGrease += components.thermalGrease;
        this.mainBoard += components.mainBoard;
        this.CPU += components.CPU;
        this.ram += components.ram;
        this.SATA += components.SATA;
        this.liquidDisplay += components.liquidDisplay;
        this.hardDisk += components.hardDisk;
        this.battery += components.battery;
    }
}
