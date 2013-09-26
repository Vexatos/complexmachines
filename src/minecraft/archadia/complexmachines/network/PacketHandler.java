package archadia.complexmachines.network;

import ljdp.easypacket.EasyPacketDispatcher;
import ljdp.easypacket.EasyPacketHandler;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import archadia.complexmachines.core.common.ComplexMachines;
import archadia.complexmachines.network.packet.PacketWireMill;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author Archadia
 *
 */
public class PacketHandler implements IPacketHandler {

    private static PacketHandler instance;

    public static final PacketHandler instance() {
        return instance;
    }

    private EasyPacketDispatcher dispatcher;
    public EasyPacketHandler wiremillUpdateHandler;
    
    public PacketHandler() {
        instance = this;
        dispatcher = new EasyPacketDispatcher(ComplexMachines.CHANNEL);
        wiremillUpdateHandler = EasyPacketHandler.registerEasyPacket(PacketWireMill.class, dispatcher);
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.data != null) {
            dispatcher.onPacketData(manager, packet, player);
        }
    }
}
