package alpha.fod.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraft.world.WorldProvider;

/**
 * This is the annotation that auto registers the dimension
 * @author Reas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dimension {
	/**
	 * The world provider.class (REQUIRED)
	 * @return
	 */
	Class<? extends WorldProvider> worldProvider();
	
	/**
	 * Should this dimension be kept loaded
	 * @return
	 */
	boolean keepLoaded() default false;

}
