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
package org.spoutcraft.client.packet;

import java.io.IOException;
import java.util.UUID;

import org.spoutcraft.api.gui.Control;
import org.spoutcraft.api.gui.InGameHUD;
import org.spoutcraft.api.gui.PopupScreen;
import org.spoutcraft.api.gui.Widget;
import org.spoutcraft.api.io.SpoutInputStream;
import org.spoutcraft.api.io.SpoutOutputStream;
import org.spoutcraft.client.SpoutClient;

public class PacketFocusUpdate implements SpoutPacket {
	private Control control;
	private boolean focus;
	private UUID widgetId;

	public PacketFocusUpdate() {
	}

	public PacketFocusUpdate(Control control, boolean focus) {
		this.control = control;
		this.focus = focus;
	}

	public void readData(SpoutInputStream input) throws IOException {
		widgetId = new UUID(input.readLong(), input.readLong());
		focus = input.readBoolean();
	}

	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeLong(control.getId().getMostSignificantBits());
		output.writeLong(control.getId().getLeastSignificantBits());
		output.writeBoolean(focus);
	}

	public void run(int playerId) {
		InGameHUD screen = SpoutClient.getInstance().getActivePlayer().getMainScreen();
		PopupScreen popup = screen.getActivePopup();
		if (popup != null) {
			Widget w = popup.getWidget(widgetId);
			if (w != null && w instanceof Control) {
				((Control)w).setFocus(focus);
			}
		}
	}

	public void failure(int playerId) {}

	public PacketType getPacketType() {
		return PacketType.PacketFocusUpdate;
	}

	public int getVersion() {
		return 0;
	}
}
