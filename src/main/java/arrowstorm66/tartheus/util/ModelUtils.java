package arrowstorm66.tartheus.util;

import net.minecraftforge.registries.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.item.*;
import net.minecraftforge.client.model.*;
import net.minecraft.block.properties.*;
import java.util.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.renderer.block.statemap.*;

public class ModelUtils
{
    private static final Map<IRegistryDelegate<Block>, IStateMapper> stateMappers;
    private static final IStateMapper defaultStateMapper;
    
    public static void registerToState(final Block b, final int itemMeta, final IBlockState state) {
        final ModelResourceLocation mrl = ModelUtils.stateMappers.getOrDefault(state.getBlock().delegate, ModelUtils.defaultStateMapper).putStateModelLocations(state.getBlock()).get(state);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), itemMeta, mrl);
    }
    
    public static <T extends Comparable<T>> void registerToStateSingleVariant(final Block b, final IProperty<T> variant) {
        final List<T> variants = new ArrayList<T>(variant.getAllowedValues());
        for (int i = 0; i < variants.size(); ++i) {
            registerToState(b, i, b.getDefaultState().withProperty((IProperty)variant, (Comparable)variants.get(i)));
        }
    }
    
    static {
        stateMappers = (Map)ReflectionHelper.getPrivateValue((Class)ModelLoader.class, (Object)null, new String[] { "customStateMappers" });
        defaultStateMapper = (IStateMapper)new DefaultStateMapper();
    }
}