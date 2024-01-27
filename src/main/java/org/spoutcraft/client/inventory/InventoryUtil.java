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
package org.spoutcraft.client.inventory;

import net.minecraft.client.MinecraftClient;
import net.minecraft.src.Container;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Packet101CloseWindow;
import net.minecraft.src.Slot;

public class InventoryUtil {
	public static void replaceItem(int id, int damage) {
		int slot = -1;
		InventoryPlayer inventory = MinecraftClient.getInstance().thePlayer.inventory;
		for (int i = 0; i < inventory.mainInventory.length; i++) {
			if (inventory.mainInventory[i] != null && i != inventory.currentItem) {
				if (inventory.mainInventory[i].itemID == id && (damage == -1 || (damage == inventory.mainInventory[i].getItemDamage()))) {
					if (!MinecraftClient.getInstance().isMultiplayerWorld()) {
						inventory.mainInventory[inventory.currentItem].stackSize = inventory.mainInventory[i].stackSize;
						inventory.mainInventory[inventory.currentItem].setItemDamage(inventory.mainInventory[i].getItemDamage());
						inventory.mainInventory[i] = null;
					}
					slot = i;
					break;
				}
			}
		}
		if (MinecraftClient.getInstance().isMultiplayerWorld() && slot > -1) {
			int window = MinecraftClient.getInstance().thePlayer.openContainer.windowId;
			ItemStack replacement = inventory.mainInventory[slot].copy();

			MinecraftClient.getInstance().playerController.windowClick(window, slot < 9 ? slot + 36 : slot, 0, 0, MinecraftClient.getInstance().thePlayer);
			MinecraftClient.getInstance().playerController.windowClick(window, inventory.currentItem + 36, 0, 0, MinecraftClient.getInstance().thePlayer);
			((EntityClientPlayerMP)MinecraftClient.getInstance().thePlayer).sendQueue.addToSendQueue(new Packet101CloseWindow(window));
			((EntityClientPlayerMP)MinecraftClient.getInstance().thePlayer).sendQueue.queued = true;
			((EntityClientPlayerMP)MinecraftClient.getInstance().thePlayer).sendQueue.packetQueueTime = System.currentTimeMillis() + 30L;

			ItemStack current = inventory.mainInventory[inventory.currentItem];
			if (replacement != null && current != null) {
				current.stackSize = replacement.stackSize;
				current.setItemDamage(replacement.getItemDamage());
			}
			inventory.mainInventory[slot] = null;
		}
	}

	public static Slot getSlotFromPosition(int pos, Container inventorySlots) {
		for (int i = 0; i < inventorySlots.inventorySlots.size(); i++) {
			if (inventorySlots.inventorySlots.get(i) != null) {
				if (((Slot)inventorySlots.inventorySlots.get(i)).slotIndex == pos) {
					return ((Slot)inventorySlots.inventorySlots.get(i));
				}
			}
		}
		return null;
	}
}
