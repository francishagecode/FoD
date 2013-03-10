package alpha.fod.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.minecraftforge.common.Configuration;
import alpha.fod.FOD;

public class CommonConfig {
	
	public static Configuration c = null;
	
	public void startConfig(Configuration config){
		c = config;
		c.load();
		for(int i = 0; i < FOD.proxy.modList.size(); i++){
			Field[] fields = FOD.proxy.modList.get(i)[0].getClass().getDeclaredFields();
			Class modClass = FOD.proxy.modList.get(i)[0].getClass();
			try{
				for(int j = 0; j < fields.length; j++){
					Field f = fields[j];
					No anno = f.getAnnotation(No.class);
					if(f.getType().getName().contains("Block")){
						Field usedField = modClass.getField(f.getName() + "Id");
						if(anno != null){
							if(anno.config() <= 256)
								usedField.setInt(modClass, c.getTerrainBlock(modClass.getSimpleName(), f.getName()+".id", anno.config(), "Block").getInt());
							else
								usedField.setInt(modClass, c.getBlock(modClass.getSimpleName(), f.getName()+".id", anno.config(), "Block").getInt());
						}
						else
							usedField.setInt(modClass, c.getBlock(modClass.getSimpleName(), f.getName()+".id", this.newBlockId(), "Block").getInt());
					}
					if(f.getType().getName().contains("Item")){
						Field usedField = modClass.getField(f.getName() + "Id");
						if(anno != null && anno.config() != 0)
							usedField.setInt(modClass, c.getItem(modClass.getSimpleName(), f.getName()+".id", anno.config(), "Item").getInt());
						else
							usedField.setInt(modClass, c.getItem(modClass.getSimpleName(), f.getName()+".id", newItemId(), "Item").getInt());
					}
					if(f.getType().getName().contains("Biome")){
						Field usedField = modClass.getField(f.getName() + "Id");
						if(anno != null && anno.config() != 0)
							usedField.setInt(modClass, c.get(modClass.getSimpleName(), f.getName()+".id", anno.config(), "Biome").getInt());
						else
							usedField.setInt(modClass, c.get(modClass.getSimpleName(), f.getName()+".id", this.newBiomeId(), "Biome").getInt());
					} 
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		c.save();
	}
	
	private int prevBiomeId = 40;
	public int newBiomeId(){
		this.prevBiomeId++;
		return this.prevBiomeId;
	}
	
	private int prevItemId = 6000;
	public int newItemId(){
		this.prevItemId++;
		return this.prevItemId;
	}
	
	private int prevBlockId = 700;
	public int newBlockId(){
		this.prevBlockId++;
		return this.prevBlockId;
	}

}
