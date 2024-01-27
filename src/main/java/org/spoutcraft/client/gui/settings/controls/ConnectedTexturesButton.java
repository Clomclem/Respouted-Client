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
package org.spoutcraft.client.gui.settings.controls;

import com.prupe.mcpatcher.TexturePackChangeHandler;

import net.minecraft.client.MinecraftClient;

import org.spoutcraft.client.SpoutClient;
import org.spoutcraft.client.config.Configuration;

public class ConnectedTexturesButton extends AutomatedCheckBox {
	public ConnectedTexturesButton() {
		super("Connected Textures");
		setChecked(Configuration.isConnectedTextures());
		setTooltip("Connects textures of identical blocks next to each other for aesthetics.\n\nCertain texture packs may add additional enhancements");
	}

	@Override
	public void onButtonClick() {
		Configuration.setConnectedTextures(!Configuration.isConnectedTextures());
		Configuration.write();
		
		Minecraft game = SpoutClient.getHandle();
		TexturePackChangeHandler.earlyInitialize("com.prupe.mcpatcher.mob.CTMUtils", "reset");
		TexturePackChangeHandler.beforeChange1(true);		
		game.refreshResources();
		TexturePackChangeHandler.afterChange1(true);
		if (MinecraftClient.getInstance().theWorld != null) {
			MinecraftClient.getInstance().renderGlobal.updateAllRenderers();
		}
	}
}
