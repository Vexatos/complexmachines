package pixlepix.complexmachines.client;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationManager {

	
	
	
	//Why the hell do I have this
	//Probably never gonna touch this class again
	//I HATE 1.6 Textures
	 public static List<String> modelNames = new ArrayList<String>();

	    public static final String MODEL_LOCATION = "/assets/ComplexMachines/models/";

	    public static final String MODEL_LETTERS = MODEL_LOCATION + "letters.obj";

	    public static final String GUI_SHEET_LOCATION = "/textures/gui/";
	    public static final String BLOCKS_SHEET_LOCATION = "/textures/blocks/";

	    public static final String ITEMS_SHEET_LOCATION = "/textures/items/";
	    public static final String MOBS_SHEET_LOCATION = "/textures/mobs/";

	    public static final String MODELS_SHEET_LOCATION = "/textures/models/";
	    public static final ResourceLocation GUI_COMPANION = new ResourceLocation(
	            "mito", GUI_SHEET_LOCATION + "companion.png");
	
	
	
	
}
