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

/**
 * Represents a block color.
 */
public class BlockColor {
	/**
	 * Block color array - contains 256 (blocks) * 16 (metadata) color slots.
	 * @see getBlockColor
	 * @see blockColorID
	 */
	private static final BlockColor[] blockColors = new BlockColor[4096];

	/**
	 * turn a block ID and a metadata value into an index in blockColors
	 * @param blockid block ID
	 * @param meta metadata value
	 * @return index in blockColors
	 * @see blockColors
	 * @see getBlockColor
	 */

	public static int blockColorID(int blockid, int meta) {
		return (blockid) | (meta << 8);
	}

	/**
	 * Retrieve the BlockColor object for a block ID and block metadata.
	 * @param blockid block ID
	 * @param meta metadata value
	 * @return BlockColor retrieved
	 */

	public static BlockColor getBlockColor(int blockid, int meta) {
		try {
			BlockColor col = blockColors[blockColorID(blockid, meta)];
			if (col != null) return col;
			col = blockColors[blockColorID(blockid, 0)];
			if (col != null) return col;
			col = blockColors[0];
			if (col != null) return col;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("BlockID: " + blockid + " - Meta: " + meta);
			throw e;
		}
		System.err.println("Unable to find a block color for blockid: " + blockid + " blockmeta: " + meta);
		return new BlockColor(0, 0xff, TintType.NONE);
	}

	/**
	 * Initialize the default colors in the block color array
	 */

	public static void initDefaultColors() {
		for (int i = 0; i < blockColors.length; i++) {
			blockColors[i] = null;
		}
		setDefaultColor(0, 0, 0);
		setDefaultColor(1, 0, -9934744);
		setDefaultColor(2, 0, -12096451);
		setDefaultColor(3, 0, -8825542);
		setDefaultColor(4, 0, -6974059);
		setDefaultColor(5, 0, -4417438);
		setDefaultColor(6, 0, 1816358162);
		setDefaultColor(6, 1, 1412577569);
		setDefaultColor(6, 2, 1819645267);
		setDefaultColor(7, 0, -13421773);
		setDefaultColor(8, 0, -1960157441);
		setDefaultColor(9, 0, -1960157441);
		setDefaultColor(10, 0, -2530028);
		setDefaultColor(11, 0, -2530028);
		setDefaultColor(12, 0, -2238560);
		setDefaultColor(13, 0, -7766146);
		setDefaultColor(14, 0, -7304324);
		setDefaultColor(15, 0, -7830913);
		setDefaultColor(16, 0, -9145485);
		setDefaultColor(17, 0, -10006222);
		setDefaultColor(17, 1, -13358823);
		setDefaultColor(17, 2, -3620193);
		setDefaultColor(18, 0, -1708107227);
		setDefaultColor(18, 1, -1522906074);
		setDefaultColor(18, 2, -1707912909);
		setDefaultColor(18, 3, -1707912909);
		setDefaultColor(18, 4, -1708107227);
		setDefaultColor(18, 5, -1522906074);
		setDefaultColor(18, 6, -1707912909);
		setDefaultColor(18, 7, -1707912909);
		setDefaultColor(19, 0, -1710770);
		setDefaultColor(20, 0, 1090519039);
		setDefaultColor(21, 0, -9998201);
		setDefaultColor(22, 0, -14858330);
		setDefaultColor(23, 0, -10987432);
		setDefaultColor(24, 0, -2370913);
		setDefaultColor(25, 0, -10206158);
		setDefaultColor(26, 0, -6339259);
		setDefaultColor(26, 1, -6339259);
		setDefaultColor(26, 2, -6339259);
		setDefaultColor(26, 3, -6339259);
		setDefaultColor(26, 4, -6339259);
		setDefaultColor(26, 5, -6339259);
		setDefaultColor(26, 6, -6339259);
		setDefaultColor(26, 7, -6339259);
		setDefaultColor(26, 8, -6397599);
		setDefaultColor(26, 9, -6397599);
		setDefaultColor(26, 10, -6397599);
		setDefaultColor(26, 11, -6397599);
		setDefaultColor(26, 12, -6397599);
		setDefaultColor(26, 13, -6397599);
		setDefaultColor(26, 14, -6397599);
		setDefaultColor(26, 15, -6397599);
		setDefaultColor(27, 0, -528457632);
		setDefaultColor(27, 1, -528457632);
		setDefaultColor(27, 2, -528457632);
		setDefaultColor(27, 3, -528457632);
		setDefaultColor(27, 4, -528457632);
		setDefaultColor(27, 5, -528457632);
		setDefaultColor(27, 6, -528457632);
		setDefaultColor(27, 7, -528457632);
		setDefaultColor(27, 8, -523214752);
		setDefaultColor(27, 9, -523214752);
		setDefaultColor(27, 10, -523214752);
		setDefaultColor(27, 11, -523214752);
		setDefaultColor(27, 12, -523214752);
		setDefaultColor(27, 13, -523214752);
		setDefaultColor(27, 14, -523214752);
		setDefaultColor(27, 15, -523214752);
		setDefaultColor(28, 0, -8952744);
		setDefaultColor(29, 0, -9605779);
		setDefaultColor(29, 1, -7499421);
		setDefaultColor(29, 2, -9804194);
		setDefaultColor(29, 3, -9804194);
		setDefaultColor(29, 4, -9804194);
		setDefaultColor(29, 5, -9804194);
		setDefaultColor(29, 8, -9605779);
		setDefaultColor(29, 9, -7499421);
		setDefaultColor(29, 10, -9804194);
		setDefaultColor(29, 11, -9804194);
		setDefaultColor(29, 12, -9804194);
		setDefaultColor(29, 13, -9804194);
		setDefaultColor(30, 0, 1775884761);
		setDefaultColor(31, 0, 1383747097);
		setDefaultColor(31, 1, -1571782606);
		setDefaultColor(31, 2, 1330675762);
		setDefaultColor(32, 0, 1383747097);
		setDefaultColor(33, 0, -9605779);
		setDefaultColor(33, 1, -6717094);
		setDefaultColor(33, 2, -9804194);
		setDefaultColor(33, 3, -9804194);
		setDefaultColor(33, 4, -9804194);
		setDefaultColor(33, 5, -9804194);
		setDefaultColor(33, 8, -9605779);
		setDefaultColor(33, 9, -6717094);
		setDefaultColor(33, 10, -9804194);
		setDefaultColor(33, 11, -9804194);
		setDefaultColor(33, 12, -9804194);
		setDefaultColor(33, 13, -9804194);
		setDefaultColor(34, 0, -6717094);
		setDefaultColor(34, 1, -6717094);
		setDefaultColor(34, 2, -2137423526);
		setDefaultColor(34, 3, -2137423526);
		setDefaultColor(34, 4, -2137423526);
		setDefaultColor(34, 5, -2137423526);
		setDefaultColor(34, 8, -6717094);
		setDefaultColor(34, 9, -7499421);
		setDefaultColor(34, 10, -2137423526);
		setDefaultColor(34, 11, -2137423526);
		setDefaultColor(34, 12, -2137423526);
		setDefaultColor(34, 13, -2137423526);
		setDefaultColor(35, 0, -2236963);
		setDefaultColor(35, 1, -1475018);
		setDefaultColor(35, 2, -4370744);
		setDefaultColor(35, 3, -9991469);
		setDefaultColor(35, 4, -4082660);
		setDefaultColor(35, 5, -12928209);
		setDefaultColor(35, 6, -2588006);
		setDefaultColor(35, 7, -12434878);
		setDefaultColor(35, 8, -6445916);
		setDefaultColor(35, 9, -14191468);
		setDefaultColor(35, 10, -8374846);
		setDefaultColor(35, 11, -14273895);
		setDefaultColor(35, 12, -11193573);
		setDefaultColor(35, 13, -13153256);
		setDefaultColor(35, 14, -6083544);
		setDefaultColor(35, 15, -15067369);
		setDefaultColor(37, 0, -1057883902);
		setDefaultColor(38, 0, -1057552625);
		setDefaultColor(39, 0, -1064211115);
		setDefaultColor(40, 0, -1063643364);
		setDefaultColor(41, 0, -66723);
		setDefaultColor(42, 0, -1447447);
		setDefaultColor(43, 0, -5723992);
		setDefaultColor(43, 1, -1712721);
		setDefaultColor(43, 2, -7046838);
		setDefaultColor(43, 3, -8224126);
		setDefaultColor(43, 4, -6591135);
		setDefaultColor(43, 5, -8750470);
		setDefaultColor(44, 0, -5723992);
		setDefaultColor(44, 1, -1712721);
		setDefaultColor(44, 2, -7046838);
		setDefaultColor(44, 3, -8224126);
		setDefaultColor(44, 4, -6591135);
		setDefaultColor(44, 5, -8750470);
		setDefaultColor(45, 0, -6591135);
		setDefaultColor(46, 0, -2407398);
		setDefaultColor(47, 0, -4943782);
		setDefaultColor(48, 0, -14727393);
		setDefaultColor(49, 0, -15527395);
		setDefaultColor(50, 0, 1627379712);
		setDefaultColor(51, 0, -4171263);
		setDefaultColor(52, 0, -14262393);
		setDefaultColor(53, 0, -4417438);
		setDefaultColor(54, 0, -7378659);
		setDefaultColor(55, 0, 1827466476);
		setDefaultColor(56, 0, -8287089);
		setDefaultColor(57, 0, -10428192);
		setDefaultColor(58, 0, -8038091);
		setDefaultColor(59, 0, 302029071);
		setDefaultColor(59, 1, 957524751);
		setDefaultColor(59, 2, 1444710667);
		setDefaultColor(59, 3, -1708815608);
		setDefaultColor(59, 4, -835813369);
		setDefaultColor(59, 5, -532579833);
		setDefaultColor(59, 6, -531663353);
		setDefaultColor(59, 7, -531208953);
		setDefaultColor(60, 0, -9221331);
		setDefaultColor(60, 1, -9550295);
		setDefaultColor(60, 2, -9879003);
		setDefaultColor(60, 3, -10207967);
		setDefaultColor(60, 4, -10536675);
		setDefaultColor(60, 5, -10865383);
		setDefaultColor(60, 6, -11194347);
		setDefaultColor(60, 7, -11523055);
		setDefaultColor(60, 8, -11786226);
		setDefaultColor(61, 0, -9145228);
		setDefaultColor(62, 0, -8355712);
		setDefaultColor(63, 0, -1598779307);
		setDefaultColor(64, 0, -1064934094);
		setDefaultColor(65, 0, -2139595212);
		setDefaultColor(66, 0, -8951211);
		setDefaultColor(67, 0, -6381922);
		setDefaultColor(68, 0, -1598779307);
		setDefaultColor(69, 0, -1603709901);
		setDefaultColor(70, 0, -7368817);
		setDefaultColor(71, 0, -1061043775);
		setDefaultColor(72, 0, -4417438);
		setDefaultColor(73, 0, -6981535);
		setDefaultColor(74, 0, -6981535);
		setDefaultColor(75, 0, -2141709038);
		setDefaultColor(76, 0, -2136923117);
		setDefaultColor(77, 0, -2139851660);
		setDefaultColor(78, 0, -1314833);
		setDefaultColor(79, 0, -1619219203);
		setDefaultColor(80, 0, -986896);
		setDefaultColor(81, 0, -15695840);
		setDefaultColor(82, 0, -6380624);
		setDefaultColor(83, 0, -7094428);
		setDefaultColor(84, 0, -9811658);
		setDefaultColor(85, 0, -4417438);
		setDefaultColor(86, 0, -4229867);
		setDefaultColor(87, 0, -9751501);
		setDefaultColor(88, 0, -11255757);
		setDefaultColor(89, 0, -4157626);
		setDefaultColor(90, 0, -9231226);
		setDefaultColor(91, 0, -3893474);
		setDefaultColor(92, 0, -1848115);
		setDefaultColor(93, 0, -6843501);
		setDefaultColor(94, 0, -4156525);
		setDefaultColor(95, 0, -7378659);
		setDefaultColor(96, 0, -8495827);
		setDefaultColor(96, 1, -8495827);
		setDefaultColor(96, 2, -8495827);
		setDefaultColor(96, 3, -8495827);
		setDefaultColor(96, 4, 545152301);
		setDefaultColor(96, 5, 545152301);
		setDefaultColor(96, 6, 545152301);
		setDefaultColor(96, 7, 545152301);

		setDefaultColor(97, 0, -9934744);
		setDefaultColor(97, 1, -6974059);
		setDefaultColor(97, 2, -8750470);

		setDefaultColor(98, 0, -8750470);
		setDefaultColor(98, 1, -9275542);
		setDefaultColor(98, 2, -9013642);

		setDefaultColor(99, 0, -3495048);
		setDefaultColor(99, 1, -7509421);
		setDefaultColor(99, 2, -7509421);
		setDefaultColor(99, 3, -7509421);
		setDefaultColor(99, 4, -7509421);
		setDefaultColor(99, 5, -7509421);
		setDefaultColor(99, 6, -7509421);
		setDefaultColor(99, 7, -7509421);
		setDefaultColor(99, 8, -7509421);
		setDefaultColor(99, 9, -7509421);
		setDefaultColor(99, 10, -3495048);

		setDefaultColor(100, 0, -3495048);
		setDefaultColor(100, 1, -4840156);
		setDefaultColor(100, 2, -4840156);
		setDefaultColor(100, 3, -4840156);
		setDefaultColor(100, 4, -4840156);
		setDefaultColor(100, 5, -4840156);
		setDefaultColor(100, 6, -4840156);
		setDefaultColor(100, 7, -4840156);
		setDefaultColor(100, 8, -4840156);
		setDefaultColor(100, 9, -4840156);
		setDefaultColor(100, 10, -3495048);

		setDefaultColor(101, 0, -2140312470);
		setDefaultColor(102, 0, 1627389951);
		setDefaultColor(103, 0, -6842076);
		setDefaultColor(104, 0, 1073780992);
		setDefaultColor(104, 1, 1209242626);
		setDefaultColor(104, 2, 1344704516);
		setDefaultColor(104, 3, 1480166151);
		setDefaultColor(104, 4, 1615693321);
		setDefaultColor(104, 5, 1751154956);
		setDefaultColor(104, 6, 1886616590);
		setDefaultColor(104, 7, 2022144016);
		setDefaultColor(105, 0, 1073780992);
		setDefaultColor(105, 1, 1209242626);
		setDefaultColor(105, 2, 1344704516);
		setDefaultColor(105, 3, 1480166151);
		setDefaultColor(105, 4, 1615693321);
		setDefaultColor(105, 5, 1751154956);
		setDefaultColor(105, 6, 1886616590);
		setDefaultColor(105, 7, 2022144016);
		setDefaultColor(106, 0, -2145432054);
		setDefaultColor(107, 0, -1061382046);
		setDefaultColor(108, 0, -6591135);
		setDefaultColor(109, 0, -8750470);

		setDefaultColor(110, 0, 0xAB8790, 0xFF);
		setDefaultColor(111, 0, 0x158003, 0xFF);
		setDefaultColor(112, 0, 0x40002F, 0xFF);
		setDefaultColor(113, 0, 0x40002F, 0xFF);
		setDefaultColor(114, 0, 0x40002F, 0xFF);
		setDefaultColor(115, 0, 0xA30321, 0xFF);
		setDefaultColor(116, 0, 0xF0DDE0, 0xFF);
		setDefaultColor(117, 0, -9934744);
		setDefaultColor(118, 0, -9934744);
		setDefaultColor(119, 0, 0x000000, 0xFF);
		setDefaultColor(120, 0, 0x376319, 0xFF);
		setDefaultColor(121, 0, 0xE4E8B5, 0xFF);
		setDefaultColor(122, 0, 0x24031E, 0xFF);
		setDefaultColor(123, 0, 0x784C10, 0xFF);
		setDefaultColor(124, 0, 0xE3DE54, 0xFF);
		setDefaultColor(125, 0, 0x9F824B);
		setDefaultColor(126, 0, 0x9F824B);
		setDefaultColor(127, 0, 0xAE6616);
		setDefaultColor(128, 0, 0xCBC594);
		setDefaultColor(129, 0, 0x46A56A);
		setDefaultColor(130, 0, 0x283A3C);
		setDefaultColor(131, 0, 0xAC8B54);
		setDefaultColor(132, 0, 0xD5D5D5);
		setDefaultColor(133, 0, 0x4CD473);
		setDefaultColor(134, 0, 0x6E512C);
		setDefaultColor(135, 0, 0xC4B87B);
		setDefaultColor(136, 0, 0x986C46);
		setDefaultColor(137, 0, 0xAD9D8F);
		setDefaultColor(138, 0, 0x64A6A2);
		setDefaultColor(139, 0, 0x929292);
		setDefaultColor(140, 0, 0x3A2017);
		setDefaultColor(141, 0, 0xC28600);
		setDefaultColor(142, 0, 0x9C931D);
		setDefaultColor(143, 0, 0xA4864F);

		// New 9/8/13
		//setDefaultColor(144, 0, 0xA4864F); // skull
		//setDefaultColor(145, 0, 0xA4864F); // anvil
		setDefaultColor(146, 0, -7378659); // trap chest
		setDefaultColor(147, 0, -66723); // gold plate
		setDefaultColor(148, 0, -1447447); // iron plate
		//setDefaultColor(149, 0, 0xA4864F); // redstone compare		
		//setDefaultColor(150, 0, 0xA4864F); // redstone compare
		//setDefaultColor(151, 0, 0xA4864F); // daylight sensor
		//setDefaultColor(152, 0, 0xA4864F); // block of redstone
		setDefaultColor(153, 0, 0x6D001C); // netherquartz ore
		//setDefaultColor(154, 0, 0xA4864F); // hopper
		setDefaultColor(155, 0, 0xE9F1B6); // quartz block
		setDefaultColor(155, 1, 0xE9F1B6); // quartz column
		setDefaultColor(155, 2, 0xE9F1B6); // quartz column 2
		setDefaultColor(156, 0, 0xADB388); // quartz stairs
		
		// New on 11/15/13
		//setDefaultColor(157, 0, 0xADB388); // activator rail
		setDefaultColor(158, 0, 0x8F8F8F); // dropper
		setDefaultColor(159, 0, 0xD1B1A1); // white stained clay
		setDefaultColor(159, 1, 0xA75929); // orange stained clay
		setDefaultColor(159, 2, 0x95576C); // magenta stained clay
		setDefaultColor(159, 3, 0x726E8A); // lite blue stained clay
		setDefaultColor(159, 4, 0xBC8726); // yellow stained clay
		setDefaultColor(159, 5, 0x647332); // lime stained clay
		setDefaultColor(159, 6, 0xA04E4D); // pink stained clay
		setDefaultColor(159, 7, 0x3A2A23); // gray stained clay
		setDefaultColor(159, 8, 0x856960); // light gray stained clay
		setDefaultColor(159, 9, 0x55585A); // cyan stained clay
		setDefaultColor(159, 10, 0x764656); // purple stained clay
		setDefaultColor(159, 11, 0x333366); // blue stained clay
		setDefaultColor(159, 12, 0x4C3121); // brown stained clay
		setDefaultColor(159, 13, 0x4E552C); // green stained clay
		setDefaultColor(159, 14, 0x993333); // red stained clay
		setDefaultColor(159, 15, 0x100A07); // block stained clay
		setDefaultColor(170, 0, 0x846C0E); // hay bale
		setDefaultColor(171, 0, 0xFFFFFF); // white carpet
		setDefaultColor(171, 1, 0xDA8836); // orange carpet
		setDefaultColor(171, 2, 0xBC60C4); // magenta carpet
		setDefaultColor(171, 3, 0x859FD3); // light blue carpet
		setDefaultColor(171, 4, 0xBFB32B); // yellow carpet
		setDefaultColor(171, 5, 0x50C244); // lime carpet
		setDefaultColor(171, 6, 0xDA9EAD); // pink carpet
		setDefaultColor(171, 7, 0x424242); // gray carpet
		setDefaultColor(171, 8, 0x979D9D); // light gray carpet
		setDefaultColor(171, 9, 0x2E6F8A); // cyan carpet
		setDefaultColor(171, 10, 0x8946C2); // purple carpet
		setDefaultColor(171, 11, 0x28327E); // blue carpet
		setDefaultColor(171, 12, 0x4C301E); // brown carpet
		setDefaultColor(171, 13, 0x3D5220); // green carpet
		setDefaultColor(171, 14, 0xA53A35); // red carpet
		setDefaultColor(171, 15, 0x1D1B1B); // black carpet
		setDefaultColor(172, 0, 0x965E45); // hardened clay
		setDefaultColor(173, 0, 0x0D0D0D); // block of coal
	}

	private static BlockColor instance(int argb, TintType tint) {
		return new BlockColor(argb & 0xFFFFFF, (argb >> 24) & 0xFF, tint);
	}

	private static void setDefaultColor(int id, int meta, int rgb, int alpha) {
		setDefaultColor(id, meta, alpha << 24 | rgb);
	}

	private static void setDefaultColor(int id, int meta, int argb) {
		TintType tint = TintType.NONE;
		switch (id) {
			case 2:
			case 106:
				tint = TintType.GRASS;
				break;
			case 8:
			case 9:
			case 79:
				tint = TintType.WATER;
				break;
			case 18:
				int m = meta & 0x3;
				if (m == 0) tint = TintType.FOLIAGE;
				if (m == 1) tint = TintType.PINE;
				if (m == 2) tint = TintType.BIRCH;
				if (m != 3) break; tint = TintType.FOLIAGE; break;
			case 20:
				tint = TintType.GLASS;
				break;
			case 31:
				if ((meta != 1) && (meta != 2)) break; tint = TintType.TALL_GRASS;
		}

		blockColors[blockColorID(id, meta)] = instance(argb, tint);
	}

	public final int color;
	public final short alpha;
	public final TintType tintType;
	public BlockColor(int color, int alpha, TintType tintType) {
		this.color=color;
		this.alpha=(short)alpha;
		this.tintType=tintType;
	}
}
