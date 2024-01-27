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
package org.spoutcraft.client.io;

import java.util.HashMap;

public class CRCManager {
	private static HashMap<String, Long> fileNameToCRC = new HashMap<String, Long>();

	public static long getCRC(String fileName) {
		Long result = fileNameToCRC.get(fileName);
		if (result == null) {
			return -1;
		}
		return result;
	}

	public static void setCRC(String fileName, long crc) {
		fileNameToCRC.put(fileName, crc);
	}

	public static void clear() {
		fileNameToCRC.clear();
	}
}
