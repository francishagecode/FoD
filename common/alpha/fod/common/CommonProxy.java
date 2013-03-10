package alpha.fod.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.DimensionManager;
import alpha.fod.FOD;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	/**
	 * All of the FOD sub mods instances
	 */
	public static ArrayList<Object[]> modList = new ArrayList();
	
	/**
	 * Starts up all of FOD sub mods
	 */
	public void start(){
		for(int i = 0; i < FOD.proxy.modList.size(); i++){
			Field[] fields = FOD.proxy.modList.get(i)[0].getClass().getDeclaredFields();
			Object modClass = FOD.proxy.modList.get(i)[0];
			for(int j = 0; j < fields.length; j++){
				Field f = fields[j];
				No anno = f.getAnnotation(No.class);
				Dimension dim = f.getAnnotation(Dimension.class);
				try {
					if(f.getType().getName().contains("Block")){
						BuildBlock build = f.getAnnotation(BuildBlock.class);
						Block block = (Block)f.get(modClass);
						
						if(build != null){
							String dissectString = build.blockClass();
							if(dissectString.subSequence(0, 5).equals("CORE.")){
								Class desiredClass = Class.forName("alpha.fod.common." + dissectString.substring(5));
								block = getBlock(block, desiredClass, modClass, f, build);
							}
							else if(dissectString.subSequence(0, 4).equals("SUB.")){
								Class desiredClass = Class.forName(modClass.getClass().getPackage().getName() + ".block." + dissectString.substring(4));
								block = getBlock(block, desiredClass, modClass, f, build);
							}
						}
						
						buildBlockCode(modClass, f, block, anno);
						f.set(modClass, block);
					}
					if(f.getType().getName().contains("Item")){
						BuildItem build = f.getAnnotation(BuildItem.class);
						Item item = (Item)f.get(modClass);
						
						if(build != null){
							String dissectString = build.itemClass();
							if(dissectString.subSequence(0, 5).equals("CORE.")){
								Class desiredClass = Class.forName("alpha.fod.common." + dissectString.substring(5));
								item = getItem(item, desiredClass, modClass, f, build);
							}
							else if(dissectString.subSequence(0, 4).equals("SUB.")){
								Class desiredClass = Class.forName(modClass.getClass().getPackage().getName() + ".item." + dissectString.substring(4));
								item = getItem(item, desiredClass, modClass, f, build);
							}
						}
						
						buildItemCode(modClass, f, item, anno);
						f.set(modClass, item);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for(int i = 0; i < modList.size(); i++){
			Class callingClass = modList.get(i)[0].getClass();
			String[] methods = (String[]) modList.get(i)[1];
			try {
				for(int j = 0; j < methods.length; j++){
					Method m = callingClass.getMethod(methods[j], new Class[] {});
		        	m.invoke(modList.get(i)[0], new Object[] {});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Loads Blocks, Items, Dimensions, etc.
	 */
	public void postStart(){
		for(int i = 0; i < FOD.proxy.modList.size(); i++){
			Field[] fields = FOD.proxy.modList.get(i)[0].getClass().getDeclaredFields();
			Object modClass = FOD.proxy.modList.get(i)[0];
			for(int j = 0; j < fields.length; j++){
				Field f = fields[j];
				No anno = f.getAnnotation(No.class);
				Dimension dim = f.getAnnotation(Dimension.class);
				try {
					if(f.getType().getName().contains("Block")){
						BuildBlock build = f.getAnnotation(BuildBlock.class);
						Block block = (Block)f.get(modClass);
						
						buildBlockCode(modClass, f, block, anno);
						String nameOfBlock = block.getBlockName().substring(5);
						
						if(anno == null ||(anno != null && anno.autoRegister()))
							GameRegistry.registerBlock(block, block.getBlockName());
						
						if(anno == null || (anno != null && anno.autoName()))
							LanguageRegistry.addName(block, nameOfBlock);
						else if(build != null && build.name().length() > 0 && !(anno != null && anno.autoName()))
							LanguageRegistry.addName(block, build.name());
					}
					if(f.getType().getName().contains("Item")){
						BuildItem build = f.getAnnotation(BuildItem.class);
						Item item = (Item)f.get(modClass);
						
						buildItemCode(modClass, f, item, anno);
						String nameOfItem = item.getItemName().substring(5);
						
						if(anno == null || (anno != null && anno.autoName()))
							LanguageRegistry.addName(item, nameOfItem);
						else if(build != null && build.name().length() > 0 && !(anno != null && anno.autoName()))
							LanguageRegistry.addName(item, build.name());
					}
					if(f.getType().getName().equals("int") && dim != null){
						DimensionManager.registerProviderType(f.getInt(modClass), dim.worldProvider(), dim.keepLoaded());
						DimensionManager.registerDimension(f.getInt(modClass), f.getInt(modClass));
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Item getItem(Item item, Class desiredClass, Object modClass, Field f, BuildItem build) throws Exception{
		item = ((Item)desiredClass.getConstructors()[0].newInstance(this.getAnnoObjects(modClass, f, build.params())));
		item.setIconIndex(build.icon());
		item.setItemName(f.getName());
		if(build.damage() > 0)
			item.setMaxDamage(build.damage());
		if(build.stackSize() < 64)
			item.setMaxStackSize(build.stackSize());
		if(build.textFile().length() > 0)
			item.setTextureFile(build.textFile());
		return item;
	}
	
	public Block getBlock(Block block, Class desiredClass, Object modClass, Field f, BuildBlock build) throws Exception{
		block = ((Block)desiredClass.getConstructors()[0].newInstance(this.getAnnoObjects(modClass, f, build.params())));
		block.setBlockName(f.getName());
		if(build.textFile().length() > 0)
			block.setTextureFile(build.textFile());
		if(build.hardness() > 0)
			block.setHardness(build.hardness());
		if(build.opacity() > 0)
			block.setLightOpacity(build.opacity());
		if(build.light() > 0)
			block.setLightValue(build.light()/100F);
		if(build.resist() > 0)
			block.setResistance(build.resist());
		if(build.stepSound().length() > 0)
			block.setStepSound((StepSound)block.getClass().getField(build.stepSound()).get(modClass));
		return block;
	}

	public void buildBlockCode(Object modClass, Field f, Block block, No anno) throws Exception {
		String className = modClass.getClass().getSimpleName();
		if(anno == null || (anno != null && anno.autoCreative())){
			CreativeTabs tabUsed = (CreativeTabs) modClass.getClass().getField(className.toLowerCase() + "Tab").get(modClass);
			block.setCreativeTab(tabUsed);
		}
		if(anno == null || (anno != null && anno.autoName()))
			block.setBlockName(getName(f));
	}
	
	public void buildItemCode(Object modClass, Field f, Item item, No anno) throws Exception {
		String className = modClass.getClass().getSimpleName();
		if(anno == null || (anno != null && anno.autoCreative())){
			CreativeTabs tabUsed = (CreativeTabs) modClass.getClass().getField(className.toLowerCase() + "Tab").get(modClass);
			item.setCreativeTab(tabUsed);
		}
		if(anno == null || (anno != null && anno.autoName()))
			item.setItemName(getName(f));
	}
	
	public String getName(Field f){
		String fieldName = f.getName();
		String name = Character.toString(fieldName.charAt(0)).toUpperCase();
		for(int i = 1; i < fieldName.length(); i++){
			Character c = fieldName.charAt(i);
			if(c.isUpperCase(fieldName.charAt(i)))
				name = name + " " + c;
			else
				name = name + c;
		}
		return name;
	}
	
	public Object[] getAnnoObjects(Object modClass, Field f, String[] dissectParams) throws Exception{
		Object[] constructorParams = new Object[dissectParams.length];
		for(int k = 0; k < dissectParams.length; k++){
			Object param = null;
			if(dissectParams[k].equals("ID"))
				param = modClass.getClass().getField(f.getName()+"Id").getInt(modClass);
			
			else if(dissectParams[k].length() > 5 && dissectParams[k].substring(0, 5).equals("ENUM."))
				param = modClass.getClass().getField(dissectParams[k].substring(5)).get(modClass);
			
			else if(dissectParams[k].length() > 5 && dissectParams[k].substring(0, 5).equals("FILE."))
				param = modClass.getClass().getField(dissectParams[k].substring(5)).get(modClass);
			
			else if(dissectParams[k].length() > 8 && dissectParams[k].substring(0, 8).equals("BOOLEAN."))
				param = Boolean.parseBoolean(dissectParams[k].substring(8));
			
			else if(dissectParams[k].length() > 4 && dissectParams[k].substring(0, 4).equals("INT."))
				param = Integer.parseInt(dissectParams[k].substring(4));
			
			else if(dissectParams[k].length() > 6 && dissectParams[k].substring(0, 6).equals("FLOAT."))
				param = Float.parseFloat(dissectParams[k].substring(6));
			
			else if(dissectParams[k].length() > 5 && dissectParams[k].substring(0, 5).equals("LONG."))
				param = Long.parseLong(dissectParams[k].substring(5));
			
			else if(dissectParams[k].length() > 5 && dissectParams[k].substring(0, 5).equals("BYTE."))
				param = Byte.parseByte(dissectParams[k].substring(5));
			
			else if(dissectParams[k].length() > 6 && dissectParams[k].substring(0, 6).equals("SHORT."))
				param = Short.parseShort(dissectParams[k].substring(6));
			
			else if(dissectParams[k].length() > 7 && dissectParams[k].substring(0, 7).equals("DOUBLE."))
				param = Double.parseDouble(dissectParams[k].substring(7));
			else if(dissectParams[k].length() > 9 && dissectParams[k].substring(0, 9).equals("MATERIAL."))
				param = Class.forName("net.minecraft.block.material.Material").getField(dissectParams[k].substring(9)).get(modClass);
			else
				param = dissectParams[k];
			constructorParams[k] = param;
		}
		return constructorParams;
	}

}
