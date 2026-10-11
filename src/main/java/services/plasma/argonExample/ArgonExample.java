package services.plasma.argonExample;

import org.bukkit.plugin.java.JavaPlugin;
import services.plasma.argon.ArgonClient;
import services.plasma.argon.HWID;
import services.plasma.argon.LicenseResult;

public final class ArgonExample extends JavaPlugin {

    @Override
    public void onEnable() {

        ArgonClient client = ArgonClient.builder()
                .baseUrl("http://65.108.76.89:3050") // no trailing slash, would highly recommend using HTTPS in production
                .apiKey("a4cb87cb5f4f8797923950de5a62ee12937cb91bc3191ac4") // PUBLIC_API_KEY from the server's .env
                .publicKey("MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE34w7COn5+vmXJK1a32PvMSQXO2dzAT1koHxZa7W3/ne7GhjLZt0FjuQOkuaeaYOzUVrCY8tRxwuuRkQZlpaqBg==") // from `npm run signing-key`
                .build();

        LicenseResult result = client.check("1K7G8-H1KTR-A8B33-E44AJ-E0UKU", "ExampleProduct", HWID.generate());

        if(!result.isValid()) {
            getLogger().severe("License check failed: " + result.getMessage());
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("Licensed to " + result.getUserId() + ", latest version " + result.getVersion());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
