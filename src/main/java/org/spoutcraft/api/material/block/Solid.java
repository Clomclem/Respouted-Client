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
package org.spoutcraft.api.material.block;

import org.spoutcraft.api.material.SolidBlock;

public class Solid extends GenericBlock implements SolidBlock{
	private final boolean falling;
	public Solid(String name, int id, int data, boolean falling) {
		super(name, id, data);
		this.falling = falling;
	}

	public Solid(String name, int id, boolean falling) {
		super(name, id, 0);
		this.falling = falling;
	}

	public Solid(String name, int id) {
		super(name, id, 0);
		this.falling = false;
	}

	public Solid(String name, int id, int data) {
		super(name, id, data);
		this.falling = false;
	}

	public boolean isFallingBlock() {
		return falling;
	}
}
