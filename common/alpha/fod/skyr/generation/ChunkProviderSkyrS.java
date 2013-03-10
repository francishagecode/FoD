package alpha.fod.skyr.generation;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenVillage;

public class ChunkProviderSkyrS implements IChunkProvider
{
	public Block block = Block.stone;
	
	private MapGenVillage villageGenerator = new MapGenVillage();
	private Random random;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    private NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves noiseGen7;
    public NoiseGeneratorOctaves noiseGen8; //RandomPositionGenerator
    private World worldObj;
    private double[] noiseArray;
    private double[] unusedSandNoise = new double[256];
    private double[] unusedGravelNoise = new double[256];
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGen = new MapGenCaves();
    private BiomeGenBase[] biomesForGeneration;
    double[] d;
    double[] e;
    double[] f;
    double[] h;
    double[] noise7;
    int[][] field_28088_i = new int[32][32];

    public ChunkProviderSkyrS(World par1World, long par2)
    {
        this.worldObj = par1World;
        this.random = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.random, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.random, 4);
        this.a = new NoiseGeneratorOctaves(this.random, 4);
        this.b = new NoiseGeneratorOctaves(this.random, 10);
        this.noiseGen7 = new NoiseGeneratorOctaves(this.random, 16);
        this.noiseGen8 = new NoiseGeneratorOctaves(this.random, 8);
    }

    /**
     * Generates the shape of the terrain in the nether.
     */
    public void generateTerrain(int var1, int var2, byte[] var3, BiomeGenBase[] var4)
    {
        byte var5 = 2;
        int var6 = var5 + 1;
        this.worldObj.getClass();
        byte var7 = 33;
        int var8 = var5 + 1;
        this.noiseArray = this.initializeNoiseField(this.noiseArray, var1 * var5, 0, var2 * var5, var6, var7, var8);

        for (int var9 = 0; var9 < var5; ++var9)
        {
            int var10 = 0;

            while (var10 < var5)
            {
                int var11 = 0;

                while (true)
                {
                    this.worldObj.getClass();

                    if (var11 >= 32)
                    {
                        ++var10;
                        break;
                    }

                    double var12 = 0.25D;
                    double var14 = this.noiseArray[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var16 = this.noiseArray[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var18 = this.noiseArray[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
                    double var20 = this.noiseArray[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
                    double var22 = (this.noiseArray[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
                    double var24 = (this.noiseArray[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
                    double var26 = (this.noiseArray[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
                    double var28 = (this.noiseArray[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;

                    for (int var30 = 0; var30 < 4; ++var30)
                    {
                        double var31 = 0.125D;
                        double var33 = var14;
                        double var35 = var16;
                        double var37 = (var18 - var14) * var31;
                        double var39 = (var20 - var16) * var31;

                        for (int var41 = 0; var41 < 8; ++var41)
                        {
                            this.worldObj.getClass();
                            this.worldObj.getClass();
                            int var42 = var41 + var9 * 8 << 11 | 0 + var10 * 8 << 7 | var11 * 4 + var30;
                            this.worldObj.getClass();
                            short var43 = 128;
                            double var44 = 0.125D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int var50 = 0; var50 < 8; ++var50)
                            {
                                int var51 = 0;

                                if (var46 > 0.0D)
                                {
                                    var51 = block.blockID;
                                }

                                var3[var42] = (byte)var51;
                                var42 += var43;
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        var14 += var22;
                        var16 += var24;
                        var18 += var26;
                        var20 += var28;
                    }

                    ++var11;
                }
            }
        }
    }
    
    /**
     * name based on ChunkProviderGenerate
     */
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] var4)
    {
        double var5 = 0.03125D;
        this.unusedSandNoise = this.noiseGen4.generateNoiseOctaves(this.unusedSandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var5, var5, 1.0D);
        this.unusedGravelNoise = this.noiseGen4.generateNoiseOctaves(this.unusedGravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, var5, 1.0D, var5);
        this.stoneNoise = this.a.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);
        				//TODO a maybe is noisegen4
        
        for (int var7 = 0; var7 < 16; ++var7)
        {
            for (int var8 = 0; var8 < 16; ++var8)
            {
            	BiomeGenBase var9 = var4[var7 + var8 * 16];
                int var10 = (int)(this.stoneNoise[var7 + var8 * 16] / 3.0D + 3.0D + this.random.nextDouble() * 0.25D);
                int var11 = -1;
                byte var12 = var9.topBlock;
                byte var13 = var9.fillerBlock; //TODO
                this.worldObj.getClass();

                for (int var14 = 127; var14 >= 0; --var14)
                {
                    this.worldObj.getClass();
                    int var15 = (var8 * 16 + var7) * 128 + var14;
                    byte var16 = par3ArrayOfByte[var15];

                    if (var16 == 0)
                    {
                        var11 = -1;
                    }
                    else if (var16 == block.blockID)
                    {
                        if (var11 == -1)
                        {
                            if (var10 <= 0)
                            {
                                var12 = 0;
                                var13 = (byte)block.blockID;
                            }

                            var11 = var10;

                            if (var14 >= 0)
                            {
                            	par3ArrayOfByte[var15] = var12;
                            }
                            else
                            {
                            	par3ArrayOfByte[var15] = var13;
                            }
                        }
                        else if (var11 > 0)
                        {
                            --var11;
                            par3ArrayOfByte[var15] = var13;
                        }
                    }
                }
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int var1, int var2)
    {
    	this.random.setSeed((long)var1 * 341873128712L + (long)var2 * 132897987541L);
        this.worldObj.getClass();
        byte[] var3 = new byte[32768];
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, var1 * 16, var2 * 16, 16, 16);
        this.generateTerrain(var1, var2, var3, this.biomesForGeneration);
        this.replaceBlocksForBiome(var1, var2, var3, this.biomesForGeneration);
        this.caveGen.generate(this, this.worldObj, var1, var2, var3);
        Chunk var4 = new Chunk(this.worldObj, var3, var1, var2);
        byte[] var5 = var4.getBiomeArray();

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
        }
        /*for (int var7 = 0; var7 < var6.length; ++var7)
        {
            var6[var7] = (byte)var5[var7].biomeID;
        }*/

        var4.resetRelightChecks();
        return var4;
    }

    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
    
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7)
    {
        if (par1ArrayOfDouble == null)
        {
        	par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        double var8 = 684.412D;
        double var10 = 684.412D;
        this.h = this.b.generateNoiseOctaves(this.h, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
        this.noise7 = this.noiseGen7.generateNoiseOctaves(this.noise7, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
        var8 *= 2.0D;
        this.d = this.noiseGen3.generateNoiseOctaves(this.d, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
        this.e = this.noiseGen1.generateNoiseOctaves(this.e, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        this.f = this.noiseGen2.generateNoiseOctaves(this.f, par2, par3, par4, par5, par6, par7, var8, var10, var8);
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < par5; ++var14)
        {
            for (int var15 = 0; var15 < par7; ++var15)
            {
                double var16 = (this.h[var13] + 256.0D) / 512.0D;

                if (var16 > 1.0D)
                {
                    var16 = 1.0D;
                }

                double var18 = this.noise7[var13] / 8000.0D;

                if (var18 < 0.0D)
                {
                    var18 = -var18 * 0.3D;
                }

                var18 = var18 * 3.0D - 2.0D;

                if (var18 > 1.0D)
                {
                    var18 = 1.0D;
                }

                var18 /= 8.0D;
                var18 = 0.0D;

                if (var16 < 0.0D)
                {
                    var16 = 0.0D;
                }

                var16 += 0.5D;
                var18 = var18 * (double)par6 / 16.0D;
                ++var13;
                double var20 = (double)par6 / 2.0D;

                for (int var22 = 0; var22 < par6; ++var22)
                {
                    double var23 = 0.0D;
                    double var25 = ((double)var22 - var20) * 8.0D / var16;

                    if (var25 < 0.0D)
                    {
                        var25 *= -1.0D;
                    }

                    double var27 = this.e[var12] / 512.0D;
                    double var29 = this.f[var12] / 512.0D;
                    double var31 = (this.d[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var31 < 0.0D)
                    {
                        var23 = var27;
                    }
                    else if (var31 > 1.0D)
                    {
                        var23 = var29;
                    }
                    else
                    {
                        var23 = var27 + (var29 - var27) * var31;
                    }

                    var23 -= 8.0D;
                    byte var33 = 32;
                    double var34;

                    if (var22 > par6 - var33)
                    {
                        var34 = (double)((float)(var22 - (par6 - var33)) / ((float)var33 - 1.0F));
                        var23 = var23 * (1.0D - var34) + -30.0D * var34;
                    }

                    var33 = 8;

                    if (var22 < var33)
                    {
                        var34 = (double)((float)(var33 - var22) / ((float)var33 - 1.0F));
                        var23 = var23 * (1.0D - var34) + -30.0D * var34;
                    }

                    par1ArrayOfDouble[var12] = var23;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider par1IChunkProvider, int var2, int var3)
    {
    	
    	//ColorizerWater.waterBuffer = true; //TODO may needed
        int var4 = var2 * 16;
        int var5 = var3 * 16;
        BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.random.setSeed(this.worldObj.getSeed());
        long var7 = this.random.nextLong() / 2L * 2L + 1L;
        long var9 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)var2 * var7 + (long)var3 * var9 ^ this.worldObj.getSeed());
        double var11 = 0.25D;
        int var13;
        int var14;
        int var15;

        if (this.random.nextInt(4) == 0)
        {
            var13 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var14 = this.random.nextInt(128);
            var15 = var5 + this.random.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.random, var13, var14, var15);
        }

        if (this.random.nextInt(8) == 0)
        {
            var13 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var14 = this.random.nextInt(this.random.nextInt(120) + 8);
            var15 = var5 + this.random.nextInt(16) + 8;

            if (var14 < 64 || this.random.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)	).generate(this.worldObj, this.random, var13, var14, var15);
            }
        }

        int var16;

        for (var13 = 0; var13 < 8; ++var13)
        {
            var14 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(this.worldObj, this.random, var14, var15, var16);
        }
        
        
        //TODO i think this is for flowers and stuff
        /*for (var13 = 0; var13 < 10; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16);
            (new IStatStringFormat(32)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 20; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.posZ.AIMoveSpeed, 32)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 10; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.isCollidedHorizontally.AIMoveSpeed, 32)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 20; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.velocityChanged.AIMoveSpeed, 16)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 20; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(64);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.isCollided.AIMoveSpeed, 8)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 2; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(32);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.isCollidedVertically.AIMoveSpeed, 8)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 8; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(16);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.jumpMovementFactor.AIMoveSpeed, 7)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 1; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            var15 = this.random.nextInt(16);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.renderYawOffset.AIMoveSpeed, 7)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var13 = 0; var13 < 1; ++var13)
        {
            var14 = var4 + this.random.nextInt(16);
            this.worldObj.getClass();
            this.worldObj.getClass();
            var15 = this.random.nextInt(16) + this.random.nextInt(16);
            var16 = var5 + this.random.nextInt(16);
            (new NetHandler(EntityMooshroom.width.AIMoveSpeed, 6)).generate(this.worldObj, this.random, var14, var15, var16);
        }*/
        	//TODO
        /*
        var11 = 0.5D;
        var13 = 0;

        if (this.random.nextInt(10) == 0)
        {
            ++var13;
        }

        if (var6 == WorldGenLakes.f)
        {
            var13 += 5;
        }

        if (var6 == WorldGenLakes.d)
        {
            var13 -= 20;
        }*/

        //TODO 'a' is maybe generate 
        /*
        for (var14 = 0; var14 < var13; ++var14)
        {
            var15 = var4 + this.random.nextInt(16) + 8;
            var16 = var5 + this.random.nextInt(16) + 8;
            EntityDamageSource var17 = var6.a(this.random);
            var17.a(1.0D, 1.0D, 1.0D);
            var17.a(this.worldObj, this.random, var15, this.worldObj.e(var15, var16), var16);
        }

        int var19;

        for (var14 = 0; var14 < 2; ++var14)
        {
            var15 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var16 = this.random.nextInt(128);
            var19 = var5 + this.random.nextInt(16) + 8;
            (new ItemEnderEye(EntityMooshroom.inWater.AIMoveSpeed)).generate(this.worldObj, this.random, var15, var16, var19);
        }

        if (this.random.nextInt(2) == 0)
        {
            var14 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16) + 8;
            (new ItemEnderEye(EntityMooshroom.hurtResistantTime.AIMoveSpeed)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        if (this.random.nextInt(4) == 0)
        {
            var14 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16) + 8;
            (new ItemEnderEye(EntityMooshroom.isImmuneToFire.AIMoveSpeed)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        if (this.random.nextInt(8) == 0)
        {
            var14 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16) + 8;
            (new ItemEnderEye(EntityMooshroom.dataWatcher.AIMoveSpeed)).generate(this.worldObj, this.random, var14, var15, var16);
        }

        for (var14 = 0; var14 < 10; ++var14)
        {
            var15 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var16 = this.random.nextInt(128);
            var19 = var5 + this.random.nextInt(16) + 8;
            (new AnvilChunkLoaderPending()).generate(this.worldObj, this.random, var15, var16, var19);
        }

        if (this.random.nextInt(32) == 0)
        {
            var14 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var15 = this.random.nextInt(128);
            var16 = var5 + this.random.nextInt(16) + 8;
            (new BlockOreStorage()).generate(this.worldObj, this.random, var14, var15, var16);
        }*/
        
        //TODO
        /*
        var14 = 0;

        if (var6 == WorldGenLakes.d)
        {
            var14 += 10;
        }

        int var18;*/

        /*for (var15 = 0; var15 < var14; ++var15)
        {
            var16 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var19 = this.random.nextInt(128);
            var18 = var5 + this.random.nextInt(16) + 8;
            (new CallableLvl1()).generate(this.worldObj, this.random, var16, var19, var18);
        }

        for (var15 = 0; var15 < 50; ++var15)
        {
            var16 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var19 = this.random.nextInt(this.random.nextInt(120) + 8);
            var18 = var5 + this.random.nextInt(16) + 8;
            (new Container(EntityMooshroom.rotationPitch.AIMoveSpeed)).generate(this.worldObj, this.random, var16, var19, var18);
        }

        for (var15 = 0; var15 < 20; ++var15)
        {
            var16 = var4 + this.random.nextInt(16) + 8;
            this.worldObj.getClass();
            var19 = this.random.nextInt(this.random.nextInt(this.random.nextInt(112) + 8) + 8);
            var18 = var5 + this.random.nextInt(16) + 8;
            (new Container(EntityMooshroom.prevRotationPitch.AIMoveSpeed)).generate(this.worldObj, this.random, var16, var19, var18);
        }

        /*ColorizerWater.waterBuffer = false;
    	
        BlockSand.fallInstantly = true;
        int var4 = par2 * 16;
        int var5 = par3 * 16;
        this.genNetherBridge.generateStructuresInChunk(this.worldObj, this.hellRNG, par2, par3);
        int var6;
        int var7;
        int var8;
        int var9;

        for (var6 = 0; var6 < 8; ++var6)
        {
            var7 = var4 + this.hellRNG.nextInt(16) + 8;
            var8 = this.hellRNG.nextInt(120) + 4;
            var9 = var5 + this.hellRNG.nextInt(16) + 8;
            (new WorldGenHellLava(Block.lavaMoving.blockID)).generate(this.worldObj, this.hellRNG, var7, var8, var9);
        }

        var6 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1) + 1;
        int var10;

        for (var7 = 0; var7 < var6; ++var7)
        {
            var8 = var4 + this.hellRNG.nextInt(16) + 8;
            var9 = this.hellRNG.nextInt(120) + 4;
            var10 = var5 + this.hellRNG.nextInt(16) + 8;
            (new WorldGenFire()).generate(this.worldObj, this.hellRNG, var8, var9, var10);
        }

        var6 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1);

        for (var7 = 0; var7 < var6; ++var7)
        {
            var8 = var4 + this.hellRNG.nextInt(16) + 8;
            var9 = this.hellRNG.nextInt(120) + 4;
            var10 = var5 + this.hellRNG.nextInt(16) + 8;
            (new WorldGenGlowStone1()).generate(this.worldObj, this.hellRNG, var8, var9, var10);
        }

        for (var7 = 0; var7 < 10; ++var7)
        {
            var8 = var4 + this.hellRNG.nextInt(16) + 8;
            var9 = this.hellRNG.nextInt(128);
            var10 = var5 + this.hellRNG.nextInt(16) + 8;
            (new WorldGenGlowStone2()).generate(this.worldObj, this.hellRNG, var8, var9, var10);
        }

        if (this.hellRNG.nextInt(1) == 0)
        {
            var7 = var4 + this.hellRNG.nextInt(16) + 8;
            var8 = this.hellRNG.nextInt(128);
            var9 = var5 + this.hellRNG.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, this.hellRNG, var7, var8, var9);
        }

        if (this.hellRNG.nextInt(1) == 0)
        {
            var7 = var4 + this.hellRNG.nextInt(16) + 8;
            var8 = this.hellRNG.nextInt(128);
            var9 = var5 + this.hellRNG.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, this.hellRNG, var7, var8, var9);
        }

        BlockSand.fallInstantly = false;*/
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * Unloads the 100 oldest chunks from memory, due to a bug with chunkSet.add() never being called it thinks the list
     * is always empty and will not remove any chunks.
     */
    public boolean unload100OldestChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
    	BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);	
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void func_82695_e(int par1, int par2)
    {
    	this.villageGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
    }

	public void recreateStructures(int var1, int var2) {
		
	}
}

