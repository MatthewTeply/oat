package conanthecivilian.oat.entity;

import com.mojang.nbt.CompoundTag;
import conanthecivilian.oat.Oat;
import conanthecivilian.oat.faction.Faction;
import conanthecivilian.oat.faction.FactionManager;
import net.minecraft.core.entity.monster.EntityHuman;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

public class EntityOatHumanoid extends EntityHuman {
	public enum Gender {
		Male,
		Female
	}

	public String name;
	public Gender gender;
	public Faction faction;

	public EntityOatHumanoid(World world) {
		super(world);

		textureIdentifier = new NamespaceID(Oat.MOD_ID, "oatHumanoid");
		scoreValue = 1000;

		String[] names = {
			"Matthew",
			"Peter",
			"George"
		};

		this.setName(names[random.nextInt(names.length - 1)]);
		this.setGender(Gender.Male);
	}

	public void init() {
		this.setFaction(FactionManager.getFaction(FactionManager.ID_HUMANS));
	}

	public String getEntityTexture() {
		return "/assets/oat/textures/entity/travellers/traveller.png";
	}

	public void setName(String name) {
		this.name = name;

		this.setNickname(this.name);
	}

	public String getName() {
		return name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gender getGender() {
		return gender;
	}

	public void speak(EntityPlayer entityPlayer, String message) {
		entityPlayer.sendMessage("<" + this.getName() + "> " + message);
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putString("Name", this.name);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		this.setName(tag.getString("Name"));
	}
}
