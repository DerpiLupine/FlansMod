package com.flansmod.common.driveables;

import java.util.ArrayList;

import com.flansmod.client.model.ModelVehicle;
import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.types.TypeFile;
import com.flansmod.common.vector.Vector3f;

public class VehicleType extends DriveableType
{
	/** Movement modifiers */
	public float turnLeftModifier = 1F, turnRightModifier = 1F;
	/** If true, this will crush any living entity under the wheels */
	public boolean squashMobs = false;
	/** The origin of the tank turret */
	public Vector3f barrelPosition;
	/** The wheel radius for onGround checks */
	public float wheelStepHeight = 1.0F;
	/** If this is true, the vehicle will drive from all wheels */
	public boolean fourWheelDrive = false;
	/** If true, then wheels will rotate as the vehicle drives */
	public boolean rotateWheels = false;
	/** Tank movement system. Uses track collision box for thrust, rather than the wheels */
	public boolean tank = false;
	/** Wheel positions */
	public Vector3f[] wheelPositions = new Vector3f[4];
	/** Strength of springs connecting car to wheels */
	public float wheelSpringStrength = 0.5F;
	
	/** Shoot delays */
	public int vehicleShootDelay, vehicleShellDelay;
	/** Aesthetic door variable */
    public boolean hasDoor = false;

	public static ArrayList<VehicleType> types = new ArrayList<VehicleType>();
	
    public VehicleType(TypeFile file)
    {
		super(file);
		types.add(this);
    }
    
    @Override
	protected void postRead(TypeFile file)
    {
    	super.postRead(file);
    }
	
    @Override
	protected void read(String[] split, TypeFile file)
	{
		super.read(split, file);
		try
		{					
			//Movement modifiers
			if(split[0].equals("TurnLeftSpeed"))
				turnLeftModifier = Float.parseFloat(split[1]);
			if(split[0].equals("TurnRightSpeed"))
				turnRightModifier = Float.parseFloat(split[1]);
			if(split[0].equals("SquashMobs"))
				squashMobs = Boolean.parseBoolean(split[1].toLowerCase());
            if(split[0].equals("FourWheelDrive"))
            	fourWheelDrive = Boolean.parseBoolean(split[1].toLowerCase());
            if(split[0].equals("Tank") || split[0].equals("TankMode"))
            	tank = Boolean.parseBoolean(split[1].toLowerCase());
            
            //Wheels
            if(split[0].equals("Wheel") || split[0].equals("WheelPosition"))
            	wheelPositions[Integer.parseInt(split[1])] = new Vector3f(Float.parseFloat(split[2]) / 16F, Float.parseFloat(split[3]) / 16F, Float.parseFloat(split[4]) / 16F);
            if(split[0].equals("WheelRadius") || split[0].equals("WheelStepHeight"))
            	wheelStepHeight = Float.parseFloat(split[1]);            
            if(split[0].equals("WheelSpringStrength") || split[0].equals("SpringStrength"))
                wheelSpringStrength = Float.parseFloat(split[1]);
            
            //Visuals
            if(split[0].equals("HasDoor"))
                hasDoor = Boolean.parseBoolean(split[1].toLowerCase());
            if(split[0].equals("RotateWheels"))
            	rotateWheels = Boolean.parseBoolean(split[1].toLowerCase());

			//Armaments
			if(split[0].equals("BarrelPosition"))
			{
				barrelPosition = new Vector3f(Integer.parseInt(split[1]) / 16F, Integer.parseInt(split[2]) / 16F, Integer.parseInt(split[3]) / 16F);
			}
			if(split[0].equals("ShootDelay"))
				vehicleShootDelay = Integer.parseInt(split[1]);
			if(split[0].equals("ShellDelay"))
				vehicleShellDelay = Integer.parseInt(split[1]);

			//Sound
			if(split[0].equals("ShootSound"))
			{
				shootMainSound = split[1];
				FlansMod.proxy.loadSound(contentPack, "driveables", split[1]);
			}
			if(split[0].equals("ShellSound"))
			{
				shootSecondarySound = split[1];
				FlansMod.proxy.loadSound(contentPack, "driveables", split[1]);
			}
		}
		catch (Exception e)
		{
		}
	}
    
    @Override
    public boolean isValidAmmo(BulletType bulletType)
    {
    	return super.isValidAmmo(bulletType) && bulletType.isShell;
    }
	
	public static VehicleType getVehicle(String find)
	{
		for(VehicleType type : types)
		{
			if(type.shortName.equals(find))
				return type;
		}
		return null;
	}
	
	/** To be overriden by subtypes for model reloading */
	public void reloadModel()
	{
		model = FlansMod.proxy.loadModel(modelString, shortName, ModelVehicle.class);
	}
}