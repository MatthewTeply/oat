package conanthecivilian.oat.mixin;

import com.mojang.nbt.CompoundTag;
import conanthecivilian.oat.Oat;
import conanthecivilian.oat.entity.IFactionEntityLiving;
import conanthecivilian.oat.faction.Faction;
import conanthecivilian.oat.faction.FactionManager;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLiving.class)
public abstract class MixinEntityLiving extends Entity implements IFactionEntityLiving {
	@Unique
	public Faction faction;

	public MixinEntityLiving(World world) {
		super(world);
	}

	@Override
	public void init() {
		this.setFaction(FactionManager.getFaction(FactionManager.ID_NEUTRAL));
	}

	@Override
	public Faction getFaction() {
		return faction;
	}

	@Override
	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"), remap = false)
	public void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		int factionId = FactionManager.ID_NEUTRAL;

		if (this.faction != null) {
			factionId = this.faction.id;
		}

		tag.putShort("FactionId", (short)factionId);
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"), remap = false)
	public void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
		this.setFaction(FactionManager.getFaction(tag.getShort("FactionId")));
	}
}

