package com.etsn05.grupp2.ViewModels;

import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Connection.ConnectionHandler;
import Model.SensorModel;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestDeviceActivity {
    private DeviceActivity da;
    private View view;

    @Before
    public void set_up() {
        da = new DeviceActivity();
        da.onCreate(null);
        view = new View(null);
    }

    @After
    public void tear_down() {
        da = null;
        view = null;
    }

    @Test
    public void testOnClickGetDevices() {
        da.onClickGetDevices(view);
    }
}