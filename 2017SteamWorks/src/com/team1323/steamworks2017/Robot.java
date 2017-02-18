
package com.team1323.steamworks2017;

import ControlSystem.FSM;
import ControlSystem.RoboSystem;
import IO.TeleController;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends SampleRobot {
	private RoboSystem robot;
	private TeleController controllers;
	private FSM fsm;
	final String defaultAuto = "off";
	final String one_gear    = "one_gear";
	SendableChooser autoSelect;
	public static enum AUTO{
    	OFF,ONE_GEAR
    }
    public Robot() {
    	autoSelect = new SendableChooser();
        autoSelect.addDefault("Off", defaultAuto);
        autoSelect.addObject("One_Gear", one_gear);
        SmartDashboard.putData("Auto Select", autoSelect);  
        robot = RoboSystem.getInstance();
        controllers = TeleController.getInstance();
        fsm = FSM.getInstance();      
    }
    public void autonomous() {
    	String autoSelected = (String) autoSelect.getSelected();
    	robot.intake._pidgey.SetFusedHeading(0.0);
    	robot.dt.setHeading(0.0);
    	switch(autoSelected){
    	case one_gear:
    		executeAuto(AUTO.ONE_GEAR);
    		break;
		case defaultAuto:
			executeAuto(AUTO.ONE_GEAR);
			break;
    	}
    }
    private double time(){return System.currentTimeMillis();}
    private void timedMotion(double seconds, double dx, double dy, double da){
    	double timeout = time();
    	while(timeout+(1000*seconds) > time() && isAutonomous()){
    		robot.dt.sendInput(dx, dy, da, 0, false, false, false);
    	}
    	timeout = time();
    	while(timeout+500 > time() && isAutonomous()){
    		robot.dt.sendInput(0, 0, 0, 0, true, true, false);
    	}
    }
    public void executeAuto(AUTO autoSelect){
    	    	
    	switch(autoSelect){
    	case ONE_GEAR:
    		/**/
 //   		robot.dt.setHeading(0);
    		timedMotion(Math.PI - 0.1415926535897932384626433832795 - 0.1, 0, 0.375, 0);		//	timedMotion(1.85, 0, 0.5, 0);
//    		robot.dt.setHeading(0);
    		timedMotion(Math.PI - 0.1415926535897932384626433832795 - 0.1, 0, -0.375, 0);		//timedMotion(1.8, 0, -0.5, 0);
//    		robot.dt.setHeading(0);
    		/*/
    		double timeout = System.currentTimeMillis();
    		while(timeout+3000 > System.currentTimeMillis() && isAutonomous()){
    			robot.dt.sendInput(0.0, 0.35, 0, 0, false, false, false);
    		}	
    		timeout = System.currentTimeMillis();
    		while(timeout+1000 > System.currentTimeMillis()){
    			robot.dt.sendInput(0, 0, 0, 0, true, true, false);
    		}/**/
    		/*/
    		timeout = System.currentTimeMillis();
    		while(timeout+2000 > System.currentTimeMillis()){
    			robot.dt.sendInput(0.0, -0.5, 0, 0, false, true, false);
    		}
    		timeout = System.currentTimeMillis();
    		while(timeout+1000 > System.currentTimeMillis()){
    			robot.dt.sendInput(0, 0, 0, 0, true, true, false);
    		}
    		timeout = System.currentTimeMillis();
    		while(timeout+2000 > System.currentTimeMillis()){
    			robot.dt.sendInput(.35, 0.0, 0, 0, false, false, false);
    		}
    		timeout = System.currentTimeMillis();
    		while(timeout+1000 > System.currentTimeMillis()){
    			robot.dt.sendInput(0, 0, 0, 0, false, true, false);
    		}/**/
    		break;
    		
    	case OFF:
    		
    		break;
    	default:
    		
    		break;
    }
    }
    
    public void operatorControl() {
    	robot.intake._pidgey.SetFusedHeading(0.0);
    	robot.dt.setHeading(0);
        while (isOperatorControl() && isEnabled()) {        	
        	controllers.update();
        	robot.intake.pigeonUpdate();
            Timer.delay(0.01);		//10ms Loop Rate
        }
    }
}
