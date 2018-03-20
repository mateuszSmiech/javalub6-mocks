package com.demo.camera;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class PhotoCameraTest {

    @Test
    public void cameraShouldBeTurnedOnWhenTurnedOn() {
        ImageSensor sensor = mock(ImageSensor.class);
        PhotoCamera camera = new PhotoCamera(sensor);
        camera.turnOn();
        verify(sensor).turnOn();

    }

    @Test
    public void cameraShouldBeTurnedOffWhenTurnedOff() {
        ImageSensor sensor = mock(ImageSensor.class);
        PhotoCamera camera = new PhotoCamera(sensor);
        camera.turnOn();
        camera.turnOff();
        verify(sensor).turnOff();
    }

    @Test
    public void nothingShouldHappenWhenCameraIsTurnedOff() {
       ImageSensor sensor = mock(ImageSensor.class);
       PhotoCamera camera = new PhotoCamera(sensor);
       camera.pressButton();
       verifyZeroInteractions(sensor);
    }

    @Test
    public void dataShouldBeWrittenOnTheCardWhenButtonIsPressed() {
        ImageSensor sensor = mock(ImageSensor.class);
        Card card = mock(Card.class);
        WriteListener writeListener = mock(WriteListener.class);
        PhotoCamera camera = new PhotoCamera(sensor, card, writeListener);
        camera.turnOn();
        byte[] byteTable = {1};
        when(sensor.read()).thenReturn(byteTable);
        camera.pressButton();
        verify(card).write(byteTable);
    }






}