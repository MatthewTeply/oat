package conanthecivilian.oat.renderer;

import conanthecivilian.oat.entity.EntityOatHumanoid;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.model.ModelBiped;

public class RendererOatHumanoid extends MobRenderer<EntityOatHumanoid> {
	public RendererOatHumanoid(ModelBiped modelBiped) {
		super(modelBiped, 1.0F);
	}
}
