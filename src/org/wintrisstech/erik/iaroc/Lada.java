package org.wintrisstech.erik.iaroc;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.sensors.UltraSonicSensors;

/**
 * A Lada is an implementation of the IRobotCreateInterface, inspired by Vic's
 * awesome API. It is entirely event driven.
 * Version 140323A...mods by Vic
 * @author Erik
 */
public class Lada extends IRobotCreateAdapter {

    private final Dashboard dashboard;
    public UltraSonicSensors sonar;
    
    //our constants
    public int SPEED = 500;

    /**
     * Constructs a Lada, an amazing machine!
     *
     * @param ioio the IOIO instance that the Lada can use to communicate with
     * other peripherals such as sensors
     * @param create an implementation of an iRobot
     * @param dashboard the Dashboard instance that is connected to the Lada
     * @throws ConnectionLostException
     */
    public Lada(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
        //song(0, new int[]{58, 10});
    }

    public void initialize() throws ConnectionLostException {
        dashboard.log("===========Start===========");
        readSensors(SENSORS_GROUP_ID6);
        dashboard.log("iAndroid2014 version 140319B");
        dashboard.log("Battery Charge = " + getBatteryCharge()
                + ", 3,000 = Full charge");
        go(2000);
    }

    /**
     * This method is called repeatedly
     *
     * @throws ConnectionLostException
     */
    public void loop() throws ConnectionLostException {
    	//driveDirect(100,  100);
//        try {
//            //sonar.read();
//        } catch (InterruptedException ex) {
//        }
        //dashboard.log("L: " + sonar.getLeftDistance() + " F: " + sonar.getFrontDistance() + " R: " + sonar.getRightDistance());
    }
    public void go(int millimeters) throws ConnectionLostException{
    	int distanceGone = 0;
    	//for(int i = 0; i < 10000; i++){
    		while(distanceGone < millimeters){
    	readSensors(SENSORS_GROUP_ID6);
    	distanceGone += getDistance();
    	driveDirect(SPEED, SPEED);
    	dashboard.log(distanceGone + "");
    	//}
    		}
    		stop();
    
    }
    public void stop() throws ConnectionLostException{
    	drive(0, 0);
    }
}
