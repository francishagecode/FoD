package alpha.fod.skyr.generation;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenSkyr extends BiomeGenBase
{
    public BiomeGenSkyr(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.topBlock = (byte) Block.grass.blockID;
        //this.fillerBlock = (byte)Skyr.granite.blockID;
    }
}
