package conanthecivilian.oat.faction;

public class FactionManager {
	public static int ID_NEUTRAL = 1;
	public static int ID_MONSTERS = 2;
	public static int ID_HUMANS = 3;

	public static Faction getFaction(int id) {
		switch (id) {
			case 1:
				return new FactionNeutral();
			case 2:
				return new FactionMonster();
			case 3:
				return new FactionHuman();
		}

		return new FactionNeutral();
	}
}
