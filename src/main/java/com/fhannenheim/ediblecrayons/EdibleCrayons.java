package com.fhannenheim.ediblecrayons;

import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod("ediblecrayons")
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class EdibleCrayons
{
    public EdibleCrayons() {

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties properties = new Item.Properties()
                    .group(CrayonItemGroup.instance)
                    .food(new Food.Builder().saturation(10).hunger(10).build());

        for(DyeColor color : DyeColor.values()){
            registry.register(new Item(properties)
                .setRegistryName(color.getTranslationKey()+"_crayon"));
        }
    }

    public static class CrayonItemGroup extends ItemGroup{
        public static final CrayonItemGroup instance = new CrayonItemGroup(ItemGroup.GROUPS.length,"crayons");
        private CrayonItemGroup(int index, String label){
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation("ediblecrayons","red_crayon")));
        }
    }
}
