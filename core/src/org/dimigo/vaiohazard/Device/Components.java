package org.dimigo.vaiohazard.Device;

import java.util.*;

/**
 * Created by YuTack on 2015-11-14.
 */
public class Components {
    private Map<Component, Integer> map;

    public enum Component{
        skrew,
        thermalGrease,
        mainBoard,
        CPU,
        RAM,
        SATA,
        liquidDisplay,
        hardDisk,
        battery;

        private static final List<Component> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        public static List<Component> getList() { return VALUES; }
    }

    public final static String[] deviceStrings = new String[] {
        "MainBoard",
            "CPU",
            "RAM",
            "SATA",
            "LiquidDisplay",
            "HardDisk",
            "Battery"
    };

    public Components(int skrew, int thermalGrease, int mainBoard, int CPU, int ram, int SATA, int liquidDisplay, int hardDisk, int battery) {
        map = new HashMap<Component, Integer>();

        map.put(Component.skrew, skrew);
        map.put(Component.thermalGrease, thermalGrease);
        map.put(Component.mainBoard, mainBoard);
        map.put(Component.CPU, CPU);
        map.put(Component.RAM, ram);
        map.put(Component.SATA, SATA);
        map.put(Component.liquidDisplay, liquidDisplay);
        map.put(Component.hardDisk, hardDisk);
        map.put(Component.battery, battery);

    }

    public Components() {
        map = new HashMap<Component, Integer>();

        for(Component component : Component.getList()) {
            map.put(component, 0);
        }
    }

    public void addComponets(Components components) {
        for(Components.Component component : Component.getList()) {
            map.put(component, map.get(component)+components.get(component));
        }
    }

    public int getSkrew() { return map.get(Component.skrew); }

    public float getThermalGrease() {
        return map.get(Component.thermalGrease);
    }

    public int getMainBoard() {
        return map.get(Component.mainBoard);
    }

    public int getCPU() {
        return map.get(Component.CPU);
    }

    public int getRam() {
        return map.get(Component.RAM);
    }

    public int getSATA() {
        return map.get(Component.SATA);
    }

    public int getLiquidDisplay() {
        return map.get(Component.liquidDisplay);
    }

    public int getHardDisk() {
        return map.get(Component.hardDisk);
    }

    public int getBattery() {
        return map.get(Component.battery);
    }

    public int get(Components.Component component) {
        return map.get(component);
    }

}
