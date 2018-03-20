package com.demo.camera;

public class PhotoCamera {
    private ImageSensor sensor;
    private boolean isWorking;
    private Card card;
    private boolean isSaveCompleted;
    private WriteListener writeCompleted;

    PhotoCamera(ImageSensor sensor) {
        this.sensor = sensor;
    }

    public PhotoCamera(ImageSensor sensor, Card card, WriteListener writeCompleted) {
        this.sensor = sensor;
        this.card = card;
        this.writeCompleted = writeCompleted;
    }

    public PhotoCamera(ImageSensor sensor, Card card) {
        this.sensor = sensor;
        this.card = card;
    }

    public void turnOn() {
        sensor.turnOn();
        isWorking = true;
    }

    public void turnOff() {
        sensor.turnOff();
        isWorking = false;
    }

    public void pressButton() {
        if (isWorking) {
            byte[] sensorData = sensor.read();
            card.write(sensorData);
            writeCompleted.writeCompleted();
        }

    }
}

