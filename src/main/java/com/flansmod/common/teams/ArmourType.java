package com.flansmod.common.teams;

import java.util.ArrayList;

import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;

public class ArmourType extends InfoType
{
	public static ArrayList<ArmourType> armours = new ArrayList<ArmourType>();
	
	public int type;
	public double defence;
	public String armourTextureName;
	public float moveSpeedModifier = 1F;
	public float knockbackModifier = 0.8F;
	
	public ArmourType(TypeFile file)
	{
		super(file);
		armours.add(this);
	}

	@Override
	protected void read(String[] split, TypeFile file)
	{
		super.read(split, file);
		try
		{
			if(split[0].equals("Type"))
			{
				if(split[1].equals("Hat") || split[1].equals("Helmet"))
					type = 0;
				if(split[1].equals("Chest") || split[1].equals("Body"))
					type = 1;
				if(split[1].equals("Legs") || split[1].equals("Pants"))
					type = 2;				
				if(split[1].equals("Shoes") || split[1].equals("Boots"))
					type = 3;
			}			
			
			if(split[0].equals("DamageReduction") || split[0].equals("Defence"))
				defence = Double.parseDouble(split[1]);		
			if(split[0].equals("MoveSpeedModifier") || split[0].equals("Slowness"))
				moveSpeedModifier = Float.parseFloat(split[1]);
			if(split[0].equals("KnockbackReduction") || split[0].equals("KnockbackModifier"))
				knockbackModifier = Float.parseFloat(split[1]);
			
			if(split[0].equals("ArmourTexture") || split[0].equals("ArmorTexture"))
			{
				armourTextureName = split[1];
			}
		} catch (Exception e)
		{
			System.out.println("Reading armour file failed.");
			e.printStackTrace();
		}
	}

	public static ArmourType getArmourType(String string) 
	{
		for(ArmourType armour : armours)
		{
			if(armour.shortName.equals(string))
				return armour;
		}
		return null;
	}
}
