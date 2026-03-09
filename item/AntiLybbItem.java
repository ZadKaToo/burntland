package net.com.burntland.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.com.burntland.procedures.AntiLybbPlayerFinishesUsingItemProcedure;

public class AntiLybbItem extends Item {
    public AntiLybbItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 40;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        entity.startUsingItem(hand);
        return ar;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        // เรียกใช้ Super ก่อนเพื่อให้ระบบเกมจัดการเรื่องพื้นฐาน
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        
        // รันระบบล้างคำสาป
        AntiLybbPlayerFinishesUsingItemProcedure.execute(entity);
        
        // ================================================================
        // แก้ไข: ลดจำนวนไอเทมลง 1 ชิ้น (ถ้าไม่ใช่ Creative Mode)
        // ================================================================
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }
        
        return itemstack; // คืนค่า itemstack ที่ลดจำนวนแล้ว (หรือว่างเปล่า)
    }
}