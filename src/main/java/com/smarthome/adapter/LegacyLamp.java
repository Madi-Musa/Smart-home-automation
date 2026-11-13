package com.smarthome.adapter;
public class LegacyLamp {
    private boolean powered;

    public void switchOn() {
        powered = true;
    }

    public void switchOff() {
        powered = false;
    }

    public boolean hasPower() {
        return powered;
    }
}