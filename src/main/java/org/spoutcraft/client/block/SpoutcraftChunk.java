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
package org.spoutcraft.client.block;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.minecraft.client.MinecraftClient;

import org.spoutcraft.api.material.CustomBlock;
import org.spoutcraft.api.material.MaterialData;

public class SpoutcraftChunk{
	public static final Set<SpoutcraftChunk> loadedChunks = new HashSet<SpoutcraftChunk>();

	private WeakReference<net.minecraft.world.chunk.Chunk> weakChunk;
	private net.minecraft.world.World world;
	private int x;
	private int z;
	private short[] customBlockData = null;
	private byte[] customBlockRotations = null;
	public SpoutcraftChunk(net.minecraft.world.chunk.Chunk chunk) {
		this.weakChunk = new WeakReference<net.minecraft.world.chunk.Chunk>(chunk);
		world = chunk.world;
		x = chunk.chunkX;
		z = chunk.chunkZ;
	}

	public net.minecraft.world.chunk.Chunk getHandle() {
		net.minecraft.world.chunk.Chunk c = weakChunk.get();
		if (c == null) {
			c = world.getChunk(x, z);
			weakChunk = new WeakReference<net.minecraft.world.chunk.Chunk>(c);
		}
		return c;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public short getCustomBlockId(int x, int y, int z) {
		if (customBlockData != null) {
			int key = ((x & 0xF) << 12) | ((z & 0xF) << 8) | (y & 0xFF);
			return customBlockData[key];
		}
		return 0;
	}

	public short setCustomBlockId(int x, int y, int z, short id) {
		if (customBlockData == null) {
			customBlockData = new short[16*16*256];
		}
		if (id < 0) id = 0;
		int key = ((x & 0xF) << 12) | ((z & 0xF) << 8) | (y & 0xFF);
		short old = customBlockData[key];
		customBlockData[key] = id;
		MinecraftClient.getInstance().world.markBlockForRenderUpdate(x, y, z); // Goddam mappings
		return old;
	}

	public short[] getCustomBlockIds() {
		return customBlockData;
	}

	public void setCustomBlockIds(short[] ids) {
		customBlockData = ids;
		MinecraftClient.getInstance().world.markBlockRangeForRenderUpdate(x * 16, 0, z * 16, x * 16 + 15, 255, z * 16 + 15);
	}

	public CustomBlock setCustomBlockId(int x, int y, int z, CustomBlock block) {
		if (block == null) {
			throw new NullPointerException("Custom Block can not be null!");
		}
		short old = setCustomBlockId(x, y, z, (short) block.getCustomId());
		return MaterialData.getCustomBlock(old);
	}

	public byte getCustomBlockData(int x, int y, int z) {
		if (customBlockRotations != null) {
			int key = ((x & 0xF) << 12) | ((z & 0xF) << 8) | (y & 0xFF);
			return customBlockRotations[key];
		}
		return 0;
	}

	public byte setCustomBlockData(int x, int y, int z, byte rot) {
		if (customBlockRotations == null) {
			customBlockRotations = new byte[16*16*256];
		}
		if (rot < 0) rot = 0;
		int key = ((x & 0xF) << 12) | ((z & 0xF) << 8) | (y & 0xFF);
		byte old = customBlockRotations[key];
		customBlockRotations[key] = rot;
		MinecraftClient.getInstance().world.markBlockForRenderUpdate(x, y, z);
		return old;
	}

	public byte[] getCustomBlockData() {
		return customBlockRotations;
	}

	public void setCustomBlockData(byte[] data) {
		customBlockRotations = data;
		MinecraftClient.getInstance().world.markBlockRangeForRenderUpdate(x * 16, 0, z * 16, x * 16 + 15, 255, z * 16 + 15);
	}

	public CustomBlock setCustomBlockId(int x, int y, int z, CustomBlock block, byte data) {
		if (block == null) {
			throw new NullPointerException("Custom Block can not be null!");
		}
		short old = setCustomBlockId(x, y, z, (short) block.getCustomId());
		setCustomBlockData(x, y, z, data);
		return MaterialData.getCustomBlock(old);
	}

	public int hashCode() {
		return new HashCodeBuilder().append(x).append(z).hashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof SpoutcraftChunk) {
			SpoutcraftChunk other = (SpoutcraftChunk)obj;
			return x == other.x && z == other.z;
		}
		return false;
	}

	public String toString() {
		return "SpoutcraftChunk (" + x + ", " + z + ") ";
	}
}
