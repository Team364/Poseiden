package com.team364.frc2019;

import static com.team364.frc2019.RobotMap.*;

public class Conversions{

    public static double toCounts(double units){
        return units * ENCODERTICKS / 360.0;
    }

    public static double toDegrees(double units){
        return units * (360 / ENCODERTICKS);
    }

    public static double modulate360(double units){
        return units %= 360;
    }
    public static int modulateEncoder(int units){
        return units %= ENCODERTICKS;
    }

    /**
     * @return encoder counts for the drive wheel to reach so that angle desired is acheived whilst
     * in X-drive formation
     */
    public static double degreeToCounts(double units){
        return units / 360 * WHEELBASE * ENCODERTICKS / WHEELDIAMETER;
    }
    
    
	public static double deadband(double input) {
		if (Math.abs(input) < STICKDEADBAND) return 0;
		return input;
    }
        
	public static double Cdeadband(double input, double deadband) {
		if (Math.abs(input) < deadband) return 0;
		return input;
    }

    public static double constraint(double input, double constraint) {
		if (Math.abs(input) > 1){
            if(input > 0){
                return constraint;
            }
            return -constraint;
        }
        return input;
    }

    public static double rotationDeadband(double input){
        if (Math.abs(input) < STICKDEADBAND) return 0;
        if(input > 0){
            return 1;
        }
        else{
            return -1;
        }
    }
}