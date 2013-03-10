package alpha.fod;

import alpha.fod.common.CommonConfig;
import alpha.fod.common.CommonProxy;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "FOD", name = "Field of Dimensions: Core", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class FOD {
	
	@SidedProxy(clientSide = "alpha.fod.client.ClientProxy", serverSide = "alpha.fod.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("FOD")
	public static FOD instance;
	
	public static CommonConfig baseConfig = new CommonConfig();
	
	public static Configuration baseConfigFile;
	
	@PreInit
	public void preStart(FMLPreInitializationEvent fml){
		this.baseConfigFile = new Configuration(fml.getSuggestedConfigurationFile());
		try{
			if(Class.forName("alpha.fod.skyr.Skyr") != null){
				this.addNewModToList(Class.forName("alpha.fod.skyr.Skyr").newInstance(), new String[]{"RegisterBiomes", "RegisterRecipes", "RegisterEntities", "Render"});
			}
		}
		catch(Exception e){
			
		}
	}
	
	@Init
	public void start(FMLInitializationEvent fml){
		baseConfig.startConfig(baseConfigFile);
		proxy.start();
	}
	
	@PostInit
	public void postStart(FMLPostInitializationEvent fml){
		proxy.postStart();
	}
	
	/**
	 * To start up any sub mods, call this in pre Init
	 * @param instance = the instance of the mod being registered(use this keyword)
	 * @param methodToCall = the methods to call when it hits Init stage
	 */
	public static void addNewModToList(Object instance, String[] methodsToCall){
		proxy.modList.add(new Object[]{instance, methodsToCall});
	}
	
	/**
	 * To start up any sub mods, call this in pre Init
	 * @param instance = the instance of the mod being registered(use this keyword)
	 * @param methodToCall = the method to call when it hits Init stage
	 */
	public static void addNewModToList(Object instance, String methodToCall){
		addNewModToList(instance, new String[]{methodToCall});
	}

}
