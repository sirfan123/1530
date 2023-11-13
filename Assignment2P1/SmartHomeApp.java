import java.util.ArrayList;
import java.util.List;

class Observer {
    private String name;

    public Observer(String name) {
        this.name = name;
    }

    public void update(Device device) {
        System.out.println(name + " received an update: " + device.getClass().getSimpleName() +
                " is turned " + (device.isOn() ? "on" : "off"));
    }
}

interface Device {
    void turnOn();
    void turnOff();
    boolean isOn();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}

class ConcreteDevice implements Device {
    private String name;
    private boolean isOn;
    private List<Observer> observers = new ArrayList<>();

    public ConcreteDevice(String name) {
        this.name = name;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " is turned on.");
        notifyObservers();
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " is turned off.");
        notifyObservers();
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

class ChangeState implements Device {
    private String name;
    private boolean isOn;
    private List<Observer> observers = new ArrayList<>();

    public ChangeState(String name) {
        this.name = name;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " is turned on.");
        notifyObservers();
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " is turned off.");
        notifyObservers();
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

class Devices implements Device {
    private List<Device> devices = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private boolean isOn = false;

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void turnOn() {
        for (Device device : devices) {
            device.turnOn();
        }
        isOn = true;
        notifyObservers();
    }

    @Override
    public void turnOff() {
        for (Device device : devices) {
            device.turnOff();
        }
        isOn = false;
        notifyObservers();
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

public class SmartHomeApp {
    public static void main(String[] args) {
        ConcreteDevice light1 = new ConcreteDevice("Living Room Light");
        ConcreteDevice light2 = new ConcreteDevice("Kitchen Light");

        ChangeState thermostat = new ChangeState("Thermostat");
        ChangeState securityCamera = new ChangeState("Security Camera");

        Devices livingRoomDevices = new Devices();
        livingRoomDevices.addDevice(light1);
        livingRoomDevices.addDevice(light2);

        Devices allDevices = new Devices();
        allDevices.addDevice(livingRoomDevices);
        allDevices.addDevice(thermostat);
        allDevices.addDevice(securityCamera);

        Observer livingRoomDevicesObserver = new Observer("User");
        Observer allDevicesObserver = new Observer("User");

        livingRoomDevices.addObserver(livingRoomDevicesObserver);
        allDevices.addObserver(allDevicesObserver);

        livingRoomDevices.turnOn();
        allDevices.turnOff();
        livingRoomDevices.turnOn();
    }
}
