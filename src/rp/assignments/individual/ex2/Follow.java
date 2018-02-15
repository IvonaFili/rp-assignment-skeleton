package rp.assignments.individual.ex2;

import lejos.robotics.RangeFinder;
import lejos.util.Delay;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

public class Follow implements StoppableRunnable {

    private DifferentialDriveRobot robot;
    private RangeFinderDescription desc;
    private RangeFinder ranger;
    private float maxDistance;
    private boolean move;


    public Follow(DifferentialDriveRobot _robot, RangeFinderDescription _desc, RangeFinder _ranger, Float _maxDistance) {
    robot = _robot;
    desc = _desc;
    ranger = _ranger;
    maxDistance = _maxDistance;
    move = true;
    }
    
    @Override
    public void run() {
        float maxTravelSpeed = (float)robot.getDifferentialPilot().getMaxTravelSpeed();
    float maxRange = maxDistance;
    float setpoint = (maxRange);
    double delay = 0.5;

    float input;
    float output=0, error;
    float kp = (float) 0.4;
    float ki = (float) 0;
    float kd = (float) 0.3;
    float iError = 0;
    float dErrror = 0;
    float last_error = 0;
    
    robot.getDifferentialPilot().forward();
    while(move) {

    input = ranger.getRange();

    error = input - setpoint;
    iError += error*delay;
    dErrror = (float) ((error-last_error)/delay);
    output += kp*error/delay+iError*ki/delay;
    output = Math.min(output, maxTravelSpeed);
    output = Math.max(output, 0);
    last_error=error;

    robot.getDifferentialPilot().setTravelSpeed(output);
    Delay.msDelay((long) (delay));
    }
    robot.getDifferentialPilot().stop();
    
    }
    @Override
	public void stop() {
    	move= false;
    

    }

		
	}