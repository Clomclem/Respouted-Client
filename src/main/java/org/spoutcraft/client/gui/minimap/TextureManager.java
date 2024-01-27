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
package org.spoutcraft.client.gui.minimap;

import net.minecraft.client.MinecraftClient;

import org.spoutcraft.client.io.CustomTextureManager;
import org.spoutcraft.client.io.FileUtil;

public class TextureManager {
	private int glRoundmap = 0;
	private int glMMArrow = 0;
	private int glWaypoint = 0;
	private int glMarker = 0;
	private int glMinimap = 0;
	private int glWhiteRoundmap = 0;
	private int glWhiteMinimap = 0;

	/**
	 * @param zanMinimap
	 */
	public TextureManager() {}

	public void reset() {
		glRoundmap = 0;
		glMMArrow = 0;
		glWaypoint = 0;
		glMarker = 0;
		glMinimap = 0;
		glWhiteRoundmap = 0;
		glWhiteMinimap = 0;
	}

	public void loadRoundmap() {
		if (glRoundmap == 0) {
			glRoundmap = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/roundmap.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glRoundmap);
	}

	public void loadMMArrow() {
		if (glMMArrow == 0) {
			glMMArrow = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/arrow.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glMMArrow);
	}

	public void loadWaypoint() {
		if (glWaypoint == 0) {
			glWaypoint = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/waypoint.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glWaypoint);
	}

	public void loadMarker() {
		if (glMarker == 0) {
			glMarker = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/marker.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glMinimap);
	}

	public void loadMinimap() {
		if (glMinimap == 0) {
			glMinimap = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/squaremap.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glMinimap);
	}

	public void loadWhiteMinimap() {
		if (glWhiteMinimap == 0) {
			glWhiteMinimap = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/squaremap_white.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glWhiteMinimap);
	}

	public void loadWhiteRoundmap() {
		if (glWhiteRoundmap == 0) {
			glWhiteRoundmap = CustomTextureManager.getTextureFromPath(FileUtil.getAssetsDir().getPath()+"/minimap/roundmap_white.png").getTextureID();
		}
		MinecraftClient.getInstance().renderEngine.bindTexture(glWhiteRoundmap);
	}
}
