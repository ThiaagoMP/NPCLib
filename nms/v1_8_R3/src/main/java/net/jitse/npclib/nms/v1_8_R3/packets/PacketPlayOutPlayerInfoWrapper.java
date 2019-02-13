/*
 * Copyright (c) 2018 Jitse Boonstra
 */

package net.jitse.npclib.nms.v1_8_R3.packets;

import com.comphenix.tinyprotocol.Reflection;
import com.mojang.authlib.GameProfile;
import net.jitse.npclib.api.wrapper.GameProfileWrapper;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.WorldSettings;

import java.util.List;

/**
 * @author Jitse Boonstra
 */
public class PacketPlayOutPlayerInfoWrapper {

    public PacketPlayOutPlayerInfo create(PacketPlayOutPlayerInfo.EnumPlayerInfoAction action, GameProfileWrapper gameProfileWrapper, String name) {
        GameProfile gameProfile = (GameProfile) gameProfileWrapper.getGameProfile();

        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo();
        Reflection.getField(packetPlayOutPlayerInfo.getClass(), "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.class)
                .set(packetPlayOutPlayerInfo, action);

        PacketPlayOutPlayerInfo.PlayerInfoData playerInfoData = packetPlayOutPlayerInfo.new PlayerInfoData(gameProfile, 1,
                WorldSettings.EnumGamemode.NOT_SET, IChatBaseComponent.ChatSerializer.a(name));

        Reflection.FieldAccessor<List> fieldAccessor = Reflection.getField(packetPlayOutPlayerInfo.getClass(),
                "b", List.class);

        List<PacketPlayOutPlayerInfo.PlayerInfoData> list = fieldAccessor.get(packetPlayOutPlayerInfo);
        list.add(playerInfoData);
        fieldAccessor.set(packetPlayOutPlayerInfo, list);

        return packetPlayOutPlayerInfo;
    }
}
