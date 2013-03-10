package alpha.fod.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * This annotation builds Item dynamically.
 * @author Reas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BuildItem {
	/**
	 * The item class' name.
	 * Currently used prefix's.
	 * CORE. (Gets alpha.fod.common)
	 * SUB. (Gets alpha.fod.common.MODLOC.item)
	 * @return
	 */
	String itemClass();
	
	/**
	 * Parameters for the constructor.
	 * @return
	 */
	String[] params();
	/**
	 * The iconIndex of the item.
	 * @return
	 */
	int icon();
	/**
	 * The texture file to be loaded.
	 * @return
	 */
	String textFile() default "";
	/**
	 * The item's maxdamage (USED ONLY FOR TOOLS)
	 * @return
	 */
	int damage() default 0;
	/**
	 * The max stack size of the item.
	 * @return
	 */
	int stackSize() default 64;
	/**
	 * Custom name for the item.
	 * @return
	 */
	String name() default "";

}
