package conanthecivilian.oat;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class OatConfig {
	private static final Toml properties = new Toml("Orcs and Travellers' TOML Config");
	public static TomlConfigHandler cfg;

	static {
		properties.addCategory("IDs")
			.addEntry("travellerId", 70)
			.addEntry("soldierId", 71);

		cfg = new TomlConfigHandler(Oat.MOD_ID, properties);
	}
}
