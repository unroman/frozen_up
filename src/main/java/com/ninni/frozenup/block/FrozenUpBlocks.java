package com.ninni.frozenup.block;

import com.ninni.frozenup.FrozenUp;
import com.ninni.frozenup.block.vanilla.PublicStairsBlock;
import com.ninni.frozenup.item.FrozenUpItems;
import com.ninni.frozenup.sound.FrozenUpBlockSoundGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

public class FrozenUpBlocks {
    public static final Block CHILLOO_FEATHER_BLOCK = register("chilloo_feather_block", new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.1f).sounds(FrozenUpBlockSoundGroups.CHILLOO_FEATHER_BLOCK)));
    public static final Block CHILLOO_FEATHER_COVERING = register("chilloo_feather_covering", new FiberCoveringBlock(FabricBlockSettings.copyOf(CHILLOO_FEATHER_BLOCK)));
    public static final Block CHILLOO_FEATHER_LAMP = register("chilloo_feather_lamp", new FeatherLampBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.3F).sounds(FrozenUpBlockSoundGroups.CHILLOO_FEATHER_BLOCK).luminance(createLightLevelFromLitBlockState(10))));

    public static final Block TRUFFLE_CAKE = register("truffle_cake", new TruffleCakeBlock(FabricBlockSettings.of(Material.CAKE).strength(0.5F).sounds(BlockSoundGroup.WOOL)));

    public static final Block CUT_ICE = register("cut_ice", new Block(FabricBlockSettings.copyOf(Blocks.PACKED_ICE)));
    public static final Block CUT_ICE_STAIRS = register("cut_ice_stairs", new PublicStairsBlock(CUT_ICE.getDefaultState(), FabricBlockSettings.copyOf(CUT_ICE)));
    public static final Block CUT_ICE_SLAB = register("cut_ice_slab", new SlabBlock(FabricBlockSettings.copyOf(CUT_ICE)));
    public static final Block CUT_ICE_CUBES = register("cut_ice_cubes", new Block(FabricBlockSettings.copyOf(Blocks.PACKED_ICE)));
    public static final Block CUT_ICE_CUBE_STAIRS = register("cut_ice_cube_stairs", new PublicStairsBlock(CUT_ICE_CUBES.getDefaultState(), FabricBlockSettings.copyOf(CUT_ICE_CUBES)));
    public static final Block CUT_ICE_CUBE_SLAB = register("cut_ice_cube_slab", new SlabBlock(FabricBlockSettings.copyOf(CUT_ICE_CUBES)));
    public static final Block CUT_ICE_CUBE_WALL = register("cut_ice_cube_wall", new WallBlock(FabricBlockSettings.copyOf(CUT_ICE_CUBES)));

    public static final Block EMPTY_MUG = register("empty_mug", new MugBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).nonOpaque()));
    public static final Block MUG_OF_MILK = register("mug_of_milk", new MugBlock(() -> FrozenUpItems.MUG_OF_MILK, FabricBlockSettings.copyOf(EMPTY_MUG)));
    public static final Block MUG_OF_CHOCOLATE_MILK = register("mug_of_chocolate_milk", new MugBlock(() -> FrozenUpItems.MUG_OF_CHOCOLATE_MILK, FabricBlockSettings.copyOf(EMPTY_MUG)));
    public static final Block MUG_OF_TRUFFLE_HOT_CHOCOLATE = register("mug_of_truffle_hot_chocolate", new MugBlock(() -> FrozenUpItems.MUG_OF_TRUFFLE_HOT_CHOCOLATE, FabricBlockSettings.copyOf(EMPTY_MUG)));

    public static final Block COMPACTED_SNOW_BRICKS = register("compacted_snow_bricks", new Block(AbstractBlock.Settings.of(Material.SNOW_BLOCK).requiresTool().strength(0.4F).sounds(FrozenUpBlockSoundGroups.COMPACTED_SNOW)));
    public static final Block COMPACTED_SNOW_BRICK_STAIRS = register("compacted_snow_brick_stairs", new StairsBlock(COMPACTED_SNOW_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));
    public static final Block COMPACTED_SNOW_BRICK_SLAB = register("compacted_snow_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));
    public static final Block COMPACTED_SNOW_FOUNDATION = register("compacted_snow_foundation", new Block(FabricBlockSettings.copyOf(COMPACTED_SNOW_BRICKS)));

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> (Boolean)state.get(Properties.LIT) ? litLevel : 0;
    }

    private static Block register(String id, Block block, boolean registerItem) {
        Block registered = Registry.register(Registry.BLOCK, new Identifier(FrozenUp.MOD_ID, id), block);
        if (registerItem) {
            Registry.register(Registry.ITEM, new Identifier(FrozenUp.MOD_ID, id), new BlockItem(registered, new FabricItemSettings().group(FrozenUp.ITEM_GROUP)));
        }
        return registered;
    }
    private static Block register(String id, Block block) {
        return register(id, block, false);
    }
}
