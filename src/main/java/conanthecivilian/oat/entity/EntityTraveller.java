package conanthecivilian.oat.entity;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;

public class EntityTraveller extends EntityOatHumanoid {
	public boolean isIntroduced = false;

	public EntityTraveller(World world) {
		super(world);
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer) {
		if (isIntroduced) {
			this.speak(entityPlayer, "Greetings " + entityPlayer.username + " it is good to see you again!");

			return true;
		}

		this.speak(entityPlayer, "Greetings, my name is " + this.getName() + " I am a fellow traveller, what is your name?");

		isIntroduced = true;

		return true;
	}
}
