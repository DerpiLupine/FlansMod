package co.uk.flansmods.client.model;

import co.uk.flansmods.client.tmt.ModelRendererTurbo;
import co.uk.flansmods.common.guns.EntityMG;
import co.uk.flansmods.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;

public class ModelGun extends ModelBase 
{
	//These first 4 models are static with no animation
	public ModelRendererTurbo[] gunModel = new ModelRendererTurbo[0];
	//These models appear when no attachment exists
	public ModelRendererTurbo[] defaultBarrelModel = new ModelRendererTurbo[0];
	public ModelRendererTurbo[] defaultScopeModel = new ModelRendererTurbo[0];
	public ModelRendererTurbo[] defaultStockModel = new ModelRendererTurbo[0];
	public ModelRendererTurbo[] defaultGripModel = new ModelRendererTurbo[0];
	
	//Animated models follow. 
	public ModelRendererTurbo[] ammoModel = new ModelRendererTurbo[0];
	public ModelRendererTurbo[] slideModel = new ModelRendererTurbo[0];

	
	//These designate the locations of 3D attachment models on the gun
	public Vector3f barrelAttachPoint = new Vector3f();
	public Vector3f scopeAttachPoint = new Vector3f();
	public Vector3f stockAttachPoint = new Vector3f();
	public Vector3f gripAttachPoint = new Vector3f();
	
	//Various animation parameters
	public float gunSlideDistance = 1F / 4F;
	public EnumAnimationType animationType = EnumAnimationType.NONE;
	public float tiltGunTime = 0.25F, unloadClipTime = 0.25F, loadClipTime = 0.25F, untiltGunTime = 0.25F;
	
	public void renderGun(float f)
	{
		render(gunModel, f);
	}
	
	public void renderSlide(float f)
	{
		render(slideModel, f);
	}
	
	public void renderDefaultScope(float f)
	{
		render(defaultScopeModel, f);
	}
	
	public void renderDefaultBarrel(float f)
	{
		render(defaultBarrelModel, f);
	}
	
	public void renderDefaultStock(float f)
	{
		render(defaultStockModel, f);
	}
	
	public void renderDefaultGrip(float f)
	{
		render(defaultGripModel, f);
	}
	
	public void renderAmmo(float f)
	{
		render(ammoModel, f);
	}

	/** For renderering models simply */
	private void render(ModelRendererTurbo[] models, float f)
	{
		for(ModelRendererTurbo model : models)
			if(model != null)
				model.render(f);
	}
	
	/** Flips the model. Generally only for backwards compatibility */
	public void flipAll()
	{
		flip(gunModel);
		flip(defaultBarrelModel);
		flip(defaultScopeModel);
		flip(defaultStockModel);
		flip(ammoModel);
		flip(slideModel);
	}	
	
	protected void flip(ModelRendererTurbo[] model)
	{
		for(ModelRendererTurbo part : model)
		{
			part.doMirror(false, true, true);
			part.setRotationPoint(part.rotationPointX, - part.rotationPointY, - part.rotationPointZ);
		}
	}
	
	/** Translates the model */
	public void translateAll(int x, int y, int z)
	{
		translate(gunModel, x, y, z);
		translate(defaultBarrelModel, x, y, z);
		translate(defaultScopeModel, x, y, z);
		translate(defaultStockModel, x, y, z);
		translate(ammoModel, x, y, z);
		translate(slideModel, x, y, z);
	}
	
	protected void translate(ModelRendererTurbo[] model, int x, int y, int z)
	{
		for(ModelRendererTurbo mod : model)
		{
			mod.rotationPointX += x;
			mod.rotationPointY += y;
			mod.rotationPointZ += z;
		}
	}
}
