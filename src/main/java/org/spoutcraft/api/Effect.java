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
package org.spoutcraft.api;

/**
 * A list of effects that the server is able to send to players.
 */
public enum Effect {
	BOW_FIRE(1002),
	CLICK1(1001),
	CLICK2(1000),
	DOOR_TOGGLE(1003),
	EXTINGUISH(1004),
	RECORD_PLAY(1005),
	SMOKE(2000),
	STEP_SOUND(2001);

	private final int id;

	Effect(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}
