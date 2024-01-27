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
package org.spoutcraft.client.special;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class BaseYAMLResource implements YAMLResource {
	private Configuration cached = null;
	private final File localCache;
	public BaseYAMLResource(File file) {
		this.localCache = file;
	}

	public synchronized Configuration getYAML() {
		updateYAML();
		return cached;
	}

	public synchronized boolean updateYAML() {
		if (cached == null) {
			// Setup cached processor
			cached = new Configuration(localCache);
			cached.load();
			return true;
		}
		return false;
	}
}
