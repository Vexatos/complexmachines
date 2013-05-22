package pixlepix.complexmachines.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import pixlepix.complexmachines.common.tileentity.MotorTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {
	@Override
	public void onPacketData (INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (packet.channel.equals("TConstruct"))
		{
			if (side == Side.SERVER)
				handleServerPacket(packet);
			else
				handleClientPacket(packet);
		}
	}

	void handleClientPacket (Packet250CustomPayload packet)
	{
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

		byte packetType;
		int dimension;
		byte packetID;

		try
		{
			packetID = inputStream.readByte();
			dimension = inputStream.readInt();

			World world = DimensionManager.getWorld(dimension);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
	}

	void handleServerPacket (Packet250CustomPayload packet)
	{
		System.out.println("Reading airship packet");
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

		byte packetID;

		try
		{
			packetID = inputStream.readByte();

			if (packetID == 1) 
			{
				int dimension = inputStream.readInt();

				World world = DimensionManager.getWorld(dimension);
				int x = inputStream.readInt();
				int y = inputStream.readInt();
				int z = inputStream.readInt();
				int direction = inputStream.readInt();
				TileEntity te = world.getBlockTileEntity(x, y, z);
				if(te instanceof MotorTileEntity){
					((MotorTileEntity)te).direction=direction;
				}
				String toolName = inputStream.readUTF();
				
			}
			

			

			
			/*else if (packetID == 11)
			{
				String user = inputStream.readUTF();
				float size = inputStream.readFloat();
				TConstruct.playerTracker.updateSize(user, size);
			}*/
		}
		catch (IOException e)
		{
			System.out.println("Failed at reading server packet for TConstruct.");
			e.printStackTrace();
			return;
		}
	}

}
