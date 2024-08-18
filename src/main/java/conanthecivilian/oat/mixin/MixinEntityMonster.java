package conanthecivilian.oat.mixin;

import com.mojang.nbt.CompoundTag;
import conanthecivilian.oat.OatEntityHelper;
import conanthecivilian.oat.entity.IFactionEntityLiving;
import conanthecivilian.oat.faction.Faction;
import conanthecivilian.oat.faction.FactionManager;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityPathfinder;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityMonster.class)
public class MixinEntityMonster extends EntityPathfinder implements IFactionEntityLiving {
	@Unique
	public Faction faction;

	public MixinEntityMonster(World world) {
		super(world);
	}

	public void init() {
		this.setFaction(FactionManager.getFaction(FactionManager.ID_MONSTERS));
	}

	@Override
	public Entity findPlayerToAttack() {
		EntityPlayer entityplayer = this.world.getClosestPlayerToEntity(this, 16.0);
		Entity entityHuman = OatEntityHelper.getClosestEntityToEntity(
			this,
			16.0
		);

		if (entityplayer != null && this.canEntityBeSeen(entityplayer) && entityplayer.getGamemode().areMobsHostile()) {
			return entityplayer;
		}

		if (entityHuman != null && this.canEntityBeSeen(entityHuman)) {
			return entityHuman;
		}

		return null;
	}

	@Override
	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	@Override
	public Faction getFaction() {
		return this.faction;
	}

	public void readAdditionalSaveData(CompoundTag tag) { super.addAdditionalSaveData(tag); }
}
