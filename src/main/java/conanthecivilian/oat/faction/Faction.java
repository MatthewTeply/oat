package conanthecivilian.oat.faction;

public class Faction {
	public int id;
	public String name;

	public Faction(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
