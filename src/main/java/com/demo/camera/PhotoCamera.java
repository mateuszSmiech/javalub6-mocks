package com.demo.camera;

public class PhotoCamera implements WriteListener {
    private ImageSensor sensor;
    private boolean isWorking;
    private Card card;
    private boolean isSaveCompleted;


    PhotoCamera(ImageSensor sensor) {
        this.sensor = sensor;
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
        if(!isSaveCompleted) {
            sensor.turnOn();
        } else {
            sensor.turnOff();
            isWorking = false;
        }
    }

    public void pressButton() {
        if (isWorking) {
                byte[] sensorData = sensor.read();
                card.write(sensorData);
        }
    }

    @Override
    public void writeCompleted() {
        isSaveCompleted = true;

//        if (isSaveInProgress) {
//            isSaveCompleted = false;
//        } else {
//            isSaveCompleted = true;
//        }
//        if (!isSaveInProgress) {
//            isSaveCompleted = true;
//        }
    }
}

