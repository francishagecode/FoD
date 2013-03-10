package alpha.fod.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used on Block/Item/Biomes for ids(ALL), Block Registry(BLOCK), Auto Name(ITEM, BLOCK), and
 * Auto Creative(BLOCK, ITEM)
 * @author Reas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface No {
	
	/**
	 * Does this Block/Item use a custom system of config Ids. If so 
	 * then add a custom base Id. Otherwise, keep it at 0.
	 * @return the Base Id
	 */
	int config() default 0;
	
	/**
	 * Does This register the block automatically?
	 * @return false if you want to GameRegistry.registerBlock(Block) manually
	 */
	boolean autoRegister() default true;
	
	/**
	 * Does this name the block/item automatically?
	 * @return false if you want to LanguageRegistry.addName(Object, String) manually
	 */
	boolean autoName() default true;
	
	/**
	 * Does this put the Creative Tab on the block/item automatically?
	 * @return false if want to manually type .setCreativeTab(CreativeTab)
	 */
	boolean autoCreative() default true;

}
