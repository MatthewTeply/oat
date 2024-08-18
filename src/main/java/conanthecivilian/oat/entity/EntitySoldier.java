package conanthecivilian.oat.entity;

import conanthecivilian.oat.OatEntityHelper;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class EntitySoldier extends EntityOatHumanoid {
	private static final ItemStack defaultHeldItem;

	public EntitySoldier(World world) {
		super(world);

		this.setName("Soldier");

		this.mobDrops.add(new WeightedRandomLootObject(Item.toolSwordIron.getDefaultStack(), 0, 1));
	}

	@Override
	public String getEntityTexture() {
		return "/assets/oat/textures/entity/soldiers/soldier.png";
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer) {
		this.speak(entityPlayer, "Move along, citizen.");

		return true;
	}

	@Override
	public Entity findPlayerToAttack() {
		Entity entityMonster = OatEntityHelper.getClosestEntityToEntity(
			this,
			16.0
		);

		if (entityMonster != null && this.canEntityBeSeen(entityMonster)) {
			return entityMonster;
		}

		return null;
	}

	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}

	static {
		defaultHeldItem = new ItemStack(Item.toolSwordIron, 1);
	}
}
