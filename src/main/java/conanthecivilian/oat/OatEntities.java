package conanthecivilian.oat;

import conanthecivilian.oat.entity.EntitySoldier;
import conanthecivilian.oat.entity.EntityTraveller;

public class OatEntities {
	public static void initializeEntities() {
		OatEntityHelper.createHumanoidEntity(
			EntityTraveller.class,
			OatConfig.cfg.getInt("IDs.travellerId"),
			"Traveller"
		);

		OatEntityHelper.createHumanoidEntity(
			EntitySoldier.class,
			OatConfig.cfg.getInt("IDs.soldierId"),
			"Soldier"
		);
	}
}
