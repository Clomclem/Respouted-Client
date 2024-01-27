/*
 * This file is part of Spoutcraft.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org/>
 * Spoutcraft is licensed under the GNU Lesser General Public License.
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.client;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

import net.minecraft.client.MinecraftClient;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spoutcraft.api.Client;
import org.spoutcraft.api.Spoutcraft;
import org.spoutcraft.api.gui.Keyboard;
import org.spoutcraft.api.gui.RenderDelegate;
import org.spoutcraft.api.gui.WidgetManager;
import org.spoutcraft.api.inventory.MaterialManager;
import org.spoutcraft.api.keyboard.KeyBindingManager;
import org.spoutcraft.api.material.MaterialData;
import org.spoutcraft.api.player.BiomeManager;
import org.spoutcraft.api.player.SkyManager;
import org.spoutcraft.api.property.PropertyObject;
import org.spoutcraft.client.config.Configuration;
import org.spoutcraft.client.config.MipMapUtils;
import org.spoutcraft.client.controls.SimpleKeyBindingManager;
import org.spoutcraft.client.entity.CraftEntity;
import org.spoutcraft.client.gui.MCRenderDelegate;
import org.spoutcraft.client.gui.SimpleKeyManager;
import org.spoutcraft.client.gui.SimpleWidgetManager;
import org.spoutcraft.client.gui.minimap.MinimapConfig;
import org.spoutcraft.client.gui.server.ServerManager;
import org.spoutcraft.client.inventory.SimpleMaterialManager;
import org.spoutcraft.client.io.CRCManager;
import org.spoutcraft.client.io.CustomTextureManager;
import org.spoutcraft.client.io.FileDownloadThread;
import org.spoutcraft.client.io.FileUtil;
import org.spoutcraft.client.io.DownloadAssets;
import org.spoutcraft.client.packet.CustomPacket;
import org.spoutcraft.client.packet.PacketEntityInformation;
import org.spoutcraft.client.packet.PacketManager;
import org.spoutcraft.client.player.ClientPlayer;
import org.spoutcraft.client.player.SimpleBiomeManager;
import org.spoutcraft.client.player.SimpleSkyManager;

public class SpoutClient extends PropertyObject implements Client {
	private static SpoutClient instance = null;
	private static final Thread dataMiningThread = new DataMiningThread();
	private static final String version = "Spoutcraft 1.6.4 / b21";
	public static final String spoutcraftVersion = "1.6.4";
	public static final String spoutcraftBuild = " - b21";
	private final SimpleSkyManager skyManager = new SimpleSkyManager();
	private final PacketManager packetManager = new PacketManager();
	private final BiomeManager biomeManager = new SimpleBiomeManager();
	private final MaterialManager materialManager = new SimpleMaterialManager();
	private final RenderDelegate render = new MCRenderDelegate();
	private final ServerManager serverManager = new ServerManager();
	private final KeyBindingManager bindingManager = new SimpleKeyBindingManager();
	private final Logger log = Logger.getLogger(SpoutClient.class.getName());
	private long tick = 0;
	private long inWorldTicks = 0;
	private Thread clipboardThread = null;
	private long server = -1L;
	public ClientPlayer player = null;
	private boolean cheatsky = false;
	private boolean forcesky = false;
	private boolean showsky = true;
	private boolean cheatclearwater = false;
	private boolean forceclearwater = false;
	private boolean showclearwater = true;
	private boolean cheatstars = false;
	private boolean forcestars = true;
	private boolean showstars = true;
	private boolean cheatweather = false;
	private boolean forceweather = true;
	private boolean showweather = true;
	private boolean time = false;
	private boolean coords = false;
	private boolean entitylabel = false;
	private boolean cheatvoidfog = false;
	private boolean forcevoidfog = true;
	private boolean showvoidfog = true;
	private boolean flySpeed = false;
	private Mode clientMode = Mode.Menu;
	private String addonFolder = MinecraftClient.getInstance().mcDataDir + File.separator + "addons";
	private final WidgetManager widgetManager = new SimpleWidgetManager();
	private final HashMap<String, Boolean> permissions = new HashMap<String, Boolean>();
	private boolean active = false;
	public boolean xrayMode = false;
	public static int[] visibleBlocks = new int[] {133, 132, 131, 120, 129, 122, 120, 119, 118, 117, 116, 115, 111, 103, 100, 151, 152, 153, 154, 155, 158, 99, 98, 96, 95, 89, 85, 74, 73, 72, 71, 70, 66, 65, 59, 57, 56, 54, 52, 51, 50, 48, 46, 44, 43, 42, 41, 40, 39, 34, 33, 30, 29, 28, 27, 26, 25, 22, 21, 18, 17, 16, 15, 14, 11, 10, 9, 8, 5};

	private SpoutClient() {
		instance = this;
		if (!Thread.currentThread().getName().equals("Minecraft main thread")) {
			throw new SecurityException("Main thread name mismatch");
		}
		serverManager.init();
		((SimpleKeyBindingManager)bindingManager).load();
		Log.setVerbose(false);
		Log.setLogSystem(new SilencedLogSystem());
		DownloadAssets.getHttpAssets();
	}

	private class SilencedLogSystem implements LogSystem {
		@Override
		public void debug(String debug) {}
		@Override
		public void error(Throwable t) {}
		@Override
		public void error(String error) {}
		@Override
		public void error(String error, Throwable t) {}
		@Override
		public void info(String info) {}
		@Override
		public void warn(String warn) {}
		@Override
		public void warn(String warn, Throwable t) {}
	}

	static {
		dataMiningThread.start();
		Packet.addIdClassMapping(195, true, true, CustomPacket.class);
		Configuration.read();
		Keyboard.setKeyManager(new SimpleKeyManager());
		FileUtil.migrateOldFiles();
	}

	public static SpoutClient getInstance() {
		if (instance == null) {
			Spoutcraft.setClient(new SpoutClient());
		}
		return instance;
	}

	public static String getClientVersion() {
		return version;
	}

	public static boolean hasAvailableRAM() {
		return Runtime.getRuntime().maxMemory() > 756L;
	}

	public long getServerVersion() {
		return server;
	}
	
	public static String getSpoutcraftVersion() {
		return spoutcraftVersion;
	}
	
	public static String getSpoutcraftBuild() {
		return spoutcraftBuild;
	}

	public SkyManager getSkyManager() {
		return skyManager;
	}

	public PacketManager getPacketManager() {
		return packetManager;
	}

	public ClientPlayer getActivePlayer() {
		return player;
	}

	public BiomeManager getBiomeManager() {
		return biomeManager;
	}

	public MaterialManager getMaterialManager() {
		return materialManager;
	}

	public net.minecraft.world.World getRawWorld() {
		if (getHandle() == null) {
			return null;
		}
		return getHandle().theWorld;
	}

	public boolean isSkyCheat() {
		return cheatsky || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isForceSky() {
		return forcesky || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isShowSky() {
		return showsky || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isClearWaterCheat() {
		return cheatclearwater || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}
	public boolean isForceClearWater() {
		return forceclearwater || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isShowClearWater() {
		return showclearwater || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isStarsCheat() {
		return cheatstars || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isForceStars() {
		return forcestars || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isShowStars() {
		return showstars || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isWeatherCheat() {
		return cheatweather || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isForceWeather() {
		return forceweather || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isShowWeather() {
		return showweather || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isTimeCheat() {
		return time || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isCoordsCheat() {
		return coords || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isEntityLabelCheat() {
		return entitylabel || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isFlySpeedCheat() {
		return flySpeed || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isVoidFogCheat() {
		return cheatvoidfog || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isForceVoidFog() {
		return forcevoidfog || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public boolean isShowVoidFog() {
		return showvoidfog || !getHandle().isMultiplayerWorld() || !isSpoutEnabled();
	}

	public void setVisualCheats(boolean tsky, boolean fsky, boolean ssky, boolean tclearwater, boolean fclearwater, boolean sclearwater, boolean tstars, boolean fstars, boolean sstars, boolean tweather, boolean fweather, boolean sweather, boolean ttime, boolean tcoords, boolean tentitylabel, boolean tvoidfog, boolean fvoidfog, boolean svoidfog, boolean tflyspeed) {
		this.cheatsky = tsky;
		this.forcesky = fsky;
		this.showsky = ssky;
		this.cheatclearwater = tclearwater;
		this.forceclearwater = fclearwater;
		this.showclearwater = sclearwater;
		this.cheatstars = tstars;
		this.forcestars = fstars;
		this.showstars = sstars;
		this.cheatweather = tweather;
		this.forceweather = fweather;
		this.showweather = sweather;
		this.time = ttime;
		this.coords = tcoords;
		this.entitylabel = tentitylabel;
		this.cheatvoidfog = tvoidfog;
		this.forcevoidfog = fvoidfog;
		this.showvoidfog = svoidfog;
		this.flySpeed = tflyspeed;

		if (!isTimeCheat()) {
			Configuration.setTime(0);
		}

		if (cheatweather) {
			Configuration.cheatweather = true;
		} else {
			Configuration.cheatweather = false;
		}

		if (cheatsky) {
			Configuration.cheatsky = true;
		} else {
			Configuration.cheatsky = false;
		}

		if (cheatstars) {
			Configuration.cheatstars = true;
		} else {
			Configuration.cheatstars = false;
		}

		if (cheatvoidfog) {
			Configuration.cheatvoidFog = true;
		} else {
			Configuration.cheatvoidFog = false;
		}

		if (cheatclearwater) {
			Configuration.cheatclearWater = true;
		} else {
			Configuration.cheatclearWater = false;
		}

		if (isForceWeather()) {
			if (isShowWeather()) {
				Configuration.setWeather(true);
			} else {
				Configuration.setWeather(false);
			}
		}

		if (isForceStars()) {
			if (isShowStars()) {
				Configuration.setStars(true);
			} else {
				Configuration.setStars(false);
			}
		}

		if (isForceSky()) {
			if (isShowSky()) {
				Configuration.setSky(true);
			} else {
				Configuration.setSky(false);
			}
		}

		if (isForceClearWater()) {
			if (isShowClearWater()) {
				Configuration.setClearWater(true);
			} else { 
				Configuration.setClearWater(false);
			}
		}

		if (isForceWeather()) {
			if (isShowWeather()) {
				Configuration.setWeather(true);
			} else {
				Configuration.setWeather(false);
			}
		}

		if (isForceVoidFog()) {
			if (isShowVoidFog()) {
				Configuration.setVoidFog(true);
			} else {
				Configuration.setVoidFog(false);
			}
		}
	}

	public boolean isSpoutEnabled() {
		return server >= 0;
	}

	public void setSpoutVersion(long version) {
		server = version;
	}

	public boolean isSpoutActive() {
		return active;
	}

	public void setSpoutActive(boolean active) {
		this.active = active;
	}

	public void onTick() {
		tick++;
		Configuration.onTick();
		getHandle().mcProfiler.startSection("httpdownloads");
		FileDownloadThread.getInstance().onTick();
		getHandle().mcProfiler.endStartSection("packet_decompression");
		PacketDecompressionThread.onTick();
		getHandle().mcProfiler.endStartSection("widgets");
		player.getMainScreen().onTick();
		getHandle().mcProfiler.endStartSection("mipmapping");
		MipMapUtils.onTick();
		getHandle().mcProfiler.endStartSection("special_effects");
		if (MinecraftClient.getInstance().theWorld != null) {
			MinecraftClient.getInstance().theWorld.doColorfulStuff();
			inWorldTicks++;
		}
		getHandle().mcProfiler.endStartSection("entity_info");
		if (isSpoutEnabled()) {
			LinkedList<CraftEntity> processed = new LinkedList<CraftEntity>();
			Iterator<Entity> i = Entity.toProcess.iterator();
			while (i.hasNext()) {
				Entity next = i.next();
				if (next.spoutEnty != null) {
					processed.add((CraftEntity) next.spoutEnty);
				}
			}
			Entity.toProcess.clear();
			if (processed.size() > 0) {
				getPacketManager().sendSpoutPacket(new PacketEntityInformation(processed));
			}
		}
		getHandle().mcProfiler.endSection();
	}

	public long getTick() {
		return tick;
	}

	public long getInWorldTicks() {
		return inWorldTicks;
	}

	public void onWorldExit() {
		FileUtil.deleteTempDirectory();
		CustomTextureManager.resetTextures();
		CRCManager.clear();
		if (clipboardThread != null) {
			clipboardThread.interrupt();
			clipboardThread = null;
		}
		MinecraftClient.getInstance().sndManager.stopMusic();
		PacketDecompressionThread.endThread();
		MaterialData.reset();
		FileDownloadThread.preCacheCompleted.lazySet(0);
		server = -1L;
		inWorldTicks = 0L;
		MaterialData.reset();
		MinimapConfig.getInstance().getServerWaypoints().clear();
	}

	public void onWorldEnter() {
		if (player == null) {
			player = ClientPlayer.getInstance();
			player.setPlayer(getHandle().thePlayer);
			getHandle().thePlayer.spoutEnty = player;
		}
		if (player.getHandle() instanceof EntityClientPlayerMP && isSpoutEnabled()) {
			clipboardThread = new ClipboardThread((EntityClientPlayerMP)player.getHandle());
			clipboardThread.start();
		} else if (clipboardThread != null) {
			clipboardThread.interrupt();
			clipboardThread = null;
		}
		PacketDecompressionThread.startThread();
		MipMapUtils.initializeMipMaps();
		MipMapUtils.update();
		player.getMainScreen().toggleSurvivalHUD(!MinecraftClient.getInstance().playerController.isInCreativeMode());
		inWorldTicks = 0L;
		MinimapConfig.getInstance().getServerWaypoints().clear();
	}

	public static Minecraft getHandle() {		
		return MinecraftClient.getInstance();
	}

	public AbstractClientPlayer getAbstractPlayerFromId(int id) {
		if (getHandle().thePlayer.entityId == id) {
			return getHandle().thePlayer;
		}
		WorldClient world = (WorldClient)getHandle().theWorld;
		Entity e = world.getEntityByID(id);
		if (e instanceof AbstractClientPlayer) {
			return (AbstractClientPlayer) e;
		}
		return null;
	}
	
	public PlayerEntity getPlayerFromId(int id) {
		if (getHandle().thePlayer.entityId == id) {
			return getHandle().thePlayer;
		}
		ClientWorld world = (ClientWorld)getHandle().theWorld;
		Entity e = world.getEntityByID(id);
		if (e instanceof PlayerEntity) {
			return (PlayerEntity) e;
		}
		return null;
	}

	public Entity getEntityFromId(int id) {
		if (getHandle().thePlayer.entityId == id) {
			return getHandle().thePlayer;
		}
		WorldClient world = (WorldClient)getHandle().theWorld;
		return world.getEntityByID(id);
	}

	public Logger getLogger() {
		return log;
	}
	
	public ServerManager getServerManager() {
		return instance.serverManager;
	}

	public Mode getMode() {
		return clientMode;
	}

	public void setMode(Mode clientMode) {
		this.clientMode = clientMode;
	}

	public String getName() {
		return "Spoutcraft";
	}

	public RenderDelegate getRenderDelegate() {
		return render;
	}

	public File getUpdateFolder() {
		return new File(MinecraftClient.getInstance().mcDataDir, "addons" + File.separator + "updates");
	}

	public String getVersion() {
		return version;
	}

	public KeyBindingManager getKeyBindingManager() {
		return bindingManager;
	}

	public File getAddonFolder() {
		return new File(addonFolder);
	}

	public File getAudioCache() {
		return getTemporaryCache();
	}

	public File getTemporaryCache() {
		return FileUtil.getTempDir();
	}

	public File getTextureCache() {
		return getTemporaryCache();
	}

	public File getTexturePackFolder() {
		return FileUtil.getTexturePackDir();
	}
	
	public File getStatsFolder() {
		return FileUtil.getStatsDir();
	}

	public WidgetManager getWidgetManager() {
		return widgetManager;
	}

	@Override
	public boolean hasPermission(String node) {
		Boolean allow = permissions.get(node);
		if (allow != null) {
			return allow;
		} else {
			return true;
		}
	}

	public void setPermission(String node, boolean allow) {
		permissions.put(node, allow);
	}

	public void clearPermissions() {
		permissions.clear();
	}
}
