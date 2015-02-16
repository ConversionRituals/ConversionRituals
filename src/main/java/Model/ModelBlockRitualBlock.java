package Model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelBlockRitualBlock extends ModelBase
{
	//fields
	ModelRenderer base;
	ModelRenderer pedastal;

	public ModelBlockRitualBlock()
	{
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(0F, 0F, 0F, 16, 8, 16);
		base.setRotationPoint(-8F, 16F, -8F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		pedastal = new ModelRenderer(this, 16, 25);
		pedastal.addBox(0F, 0F, 0F, 8, 4, 8);
		pedastal.setRotationPoint(-4F, 12F, -4F);
		pedastal.setTextureSize(64, 64);
		pedastal.mirror = true;
		setRotation(pedastal, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		pedastal.render(f5);
	}

	public void renderModel(float f){
		base.render(f);
		pedastal.render(f);	  
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
