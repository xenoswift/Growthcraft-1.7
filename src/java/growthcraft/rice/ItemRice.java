package growthcraft.rice;

import growthcraft.core.GrowthCraftCore;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRice extends Item 
{
	public ItemRice() 
	{
		super();
		this.setUnlocalizedName("grc.rice");
		this.setCreativeTab(GrowthCraftCore.tab);
	}

	/************
	 * MAIN
	 ************/	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int dir, float par8, float par9, float par10)
	{
		if (dir != 1)
		{
			return false;
		}
		else if (player.canPlayerEdit(x, y, z, dir, stack) && player.canPlayerEdit(x, y + 1, z, dir, stack))
		{
			Block soil = world.getBlock(x, y, z);

			if (soil != null && soil == GrowthCraftRice.paddyField && world.isAirBlock(x, y + 1, z) && world.getBlockMetadata(x, y, z) > 0)
			{
				world.setBlock(x, y + 1, z, GrowthCraftRice.riceBlock);
				--stack.stackSize;
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	/************
	 * TEXTURES
	 ************/
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg)
	{
		this.itemIcon = reg.registerIcon("grcrice:rice");
	}

}
