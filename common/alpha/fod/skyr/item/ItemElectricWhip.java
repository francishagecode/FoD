package alpha.fod.skyr.item;

import java.awt.List;

import alpha.fod.skyr.SkyrRef;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElectricWhip extends Item {
	
	private int weaponDamage;
	private final EnumToolMaterial toolMaterial;
	
	public ItemElectricWhip(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1);
		this.toolMaterial = par2EnumToolMaterial;
		this.maxStackSize = 1;
		this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
		this.weaponDamage = par2EnumToolMaterial.getDamageVsEntity();
	}
	
	public EnumRarity getRarity (ItemStack par1){
        return EnumRarity.rare;
	}
	
	public int func_82803_g() {
		return this.toolMaterial.getDamageVsEntity();
	}
	
	public boolean shouldRotateAroundWhenRendering()
    {
        return false; //Maybe
    }
	

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		stack.damageItem(1, ((EntityLiving) entity));
		if(((EntityLiving) entity).getHealth() == 1)
			((EntityLiving) entity).setFire(1);
		else ((EntityLiving) entity).attackEntityFrom(DamageSource.magic, 1);
		((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 60, 2));
		((EntityLiving) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 20));
        return true;
    }
	
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
			EntityLiving par7EntityLiving) {
		if ((double) Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			par1ItemStack.damageItem(2, par7EntityLiving);
		}
		
		return true;
	}
	
	/**
	 * Returns the damage against a given entity.
	 */
	public int getDamageVsEntity(Entity par1Entity) {
		return this.weaponDamage;
	}
	
	@SideOnly(Side.CLIENT)
	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	public boolean isFull3D() {
		return true;
	}
	
	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
	
	public String func_77825_f() {
		return this.toolMaterial.toString();
	}
	
	public String getTextureFile(){
		return SkyrRef.item;
	}
}
