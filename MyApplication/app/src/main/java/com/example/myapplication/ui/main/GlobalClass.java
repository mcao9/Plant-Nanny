package com.example.myapplication.ui.main;

import android.app.Application;

public class GlobalClass extends Application {

    private int soil;
    private int light;
    private int rain;

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = soil;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }
}
