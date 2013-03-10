package alpha.fod.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * This annotation builds Block dynamically.
 * @author Reas
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BuildBlock {
	/**
	 * The block class' name.
	 * Currently used prefix's.
	 * CORE. (Gets alpha.fod.common)
	 * SUB. (Gets alpha.fod.common.MODLOC.item)
	 * @return
	 */
	String blockClass();
	/**
	 * Parameters for the constructor.
	 * @return
	 */
	String[] params();
	/**
	 * The texture file to be loaded.
	 * @return
	 */
	String textFile() default "";
	/**
	 * Custom name for the block.
	 * @return
	 */
	String name() default "";
	/**
	 * Custom Hardness to the block.
	 * @return
	 */
	float hardness();
	/**
	 * Custom Light Opacity to the block.
	 * @return
	 */
	int opacity() default 0;
	/**
	 * Custom Light Value to the block.
	 * @return
	 */
	int light() default 0;
	/**
	 * Custom Resistance to the block.
	 * @return
	 */
	float resist() default 0;
	/**
	 * Custom StepSound to the block.
	 * @return
	 */
	String stepSound() default "";
	/**
	 * Does this block have a random tick.
	 * @return
	 */
	boolean randomTick() default false;

}
