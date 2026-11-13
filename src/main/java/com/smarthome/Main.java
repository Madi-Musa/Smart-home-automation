package com.smarthome;

import com.smarthome.adapter.LampAdapter;
import com.smarthome.adapter.LegacyLamp;
import com.smarthome.device.Thermostat;
import com.smarthome.facade.SmartHomeFacade;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Smart Home Automation System");

        String lampId = "lamp-1";
        String thermostatId = "thermostat-1";

        LampAdapter lamp = new LampAdapter(
                lampId, "Bedroom lamp", new LegacyLamp()
        );
        Thermostat thermostat = new Thermostat(
                thermostatId, "Bedroom thermostat"
        );

        SmartHome home = new SmartHome("My Smart Home", List.of(lamp, thermostat));
        SmartHomeFacade facade = new SmartHomeFacade(home);

        facade.turnOn(lampId);
        System.out.println("Before night mode: lamp on = " + lamp.isOn());

        facade.activateNightMode(lampId, thermostatId);

        System.out.println("After night mode: lamp on = " + lamp.isOn());
        System.out.println("Thermostat on = " + thermostat.isOn());
        System.out.println("Temperature = " + thermostat.getTemperature() + "°C");
    }
}