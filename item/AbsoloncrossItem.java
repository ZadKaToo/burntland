package net.com.burntland.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;

import java.util.List;

import net.com.burntland.procedures.AbsoloncrossToolInHandTickProcedure;
import net.com.burntland.procedures.AbsoloncrossLivingEntityIsHitWithToolProcedure;
import net.com.burntland.procedures.AbsoloncrossPlayerFinishesUsingItemProcedure;

public class AbsoloncrossItem extends SwordItem {

    public AbsoloncrossItem() {
        super(new Tier() {
            public int getUses() { return 2500; }
            public float getSpeed() { return 4f; }
            public float getAttackDamageBonus() { return 8f; }
            public int getLevel() { return 3; }
            public int getEnchantmentValue() { return 15; }
            public Ingredient getRepairIngredient() { return Ingredient.of(); }
        }, 3, -3f, new Item.Properties().fireResistant());
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        AbsoloncrossLivingEntityIsHitWithToolProcedure.execute(entity.level(), entity.getX(), entity.getY(), entity.getZ(), entity);
        return retval;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        
        if (Screen.hasShiftDown()) {
            list.add(Component.literal(""));
            list.add(Component.literal("Ability: Fallen Angel")); 
            list.add(Component.literal("§7Hold Shift and Right-Click to charge."));
            list.add(Component.literal("§7Release to launch into the air."));
            list.add(Component.literal("§7Landing creates a massive explosion."));
            list.add(Component.literal(""));
            list.add(Component.literal("§7Penalty: Users take recoil damage."));
        } else {
            list.add(Component.literal("§8Press Shift for details"));
        }
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected)
            AbsoloncrossToolInHandTickProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
    }

    // --- ส่วนที่แก้ 1: เริ่มกดปุ่ม + จดเวลาเริ่มต้น (Timestamp) ---
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        
        if (player.isShiftKeyDown()) {
            player.startUsingItem(hand);
            // จดเวลาปัจจุบันของโลกไว้ในตัวผู้เล่น เพื่อใช้คำนวณตอนปล่อย
            player.getPersistentData().putLong("AbsolonStartTime", world.getGameTime());
            return InteractionResultHolder.consume(itemstack);
        }
        
        // ใช้ pass เพื่อให้ Epic Fight ทำงานได้ปกติถ้าไม่ได้กด Shift
        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 72000;
    }

    // --- ส่วนที่แก้ 2: ปล่อยปุ่ม + คำนวณเวลาเอง (ไม่ใช้ timeLeft ที่บั๊ก) ---
    @Override
    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int timeLeft) {
        // ดึงเวลาเริ่มออกมา
        long startTime = entity.getPersistentData().getLong("AbsolonStartTime");
        long currentTime = world.getGameTime();
        
        // คำนวณระยะเวลาที่กดค้าง (ปัจจุบัน - เริ่มต้น)
        int power = (int)(currentTime - startTime);
        if (power < 0) power = 0;
        
        // ฝากค่า Power ไว้ให้ Procedure ใช้
        entity.getPersistentData().putDouble("AbsolonPower", power);

        // เรียก Procedure
        AbsoloncrossPlayerFinishesUsingItemProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
    }

    // --- ส่วนที่แก้ 3: Effect/Sound ฝั่ง Client + บังคับปล่อยเมื่อเต็ม ---
    @Override
    public void onUseTick(Level world, LivingEntity entity, ItemStack itemstack, int count) {
        // คำนวณเวลาแบบเดียวกัน (เพื่อให้ตรงกับ Logic การปล่อย)
        long startTime = entity.getPersistentData().getLong("AbsolonStartTime");
        long ticksUsed = world.getGameTime() - startTime;

        // 1. ถ้าชาร์จเกิน 60 tick (3 วินาที) ให้บังคับปล่อยทันที
        if (ticksUsed >= 60) {
             if (entity instanceof Player player) {
                 player.releaseUsingItem();
             }
             return;
        }

        // 2. ทำงานฝั่ง Client เท่านั้น (เพื่อความลื่นและเสียงไม่ดีเลย์)
        if (world.isClientSide) {
            
            // --- Particles ---
            if (ticksUsed % 4 == 0) {
                double radius = 0.6;
                double offsetX = (Math.random() - 0.5) * 2 * radius;
                double offsetZ = (Math.random() - 0.5) * 2 * radius;

                world.addParticle(ParticleTypes.SMOKE,
                    entity.getX() + offsetX, entity.getY() + 0.1, entity.getZ() + offsetZ,
                    0, 0.05, 0);

                if (ticksUsed > 20 && Math.random() < 0.5) {
                    world.addParticle(ParticleTypes.PORTAL,
                        entity.getX() + offsetX * 1.5, entity.getY() + 1, entity.getZ() + offsetZ * 1.5,
                        -offsetX, -0.5, -offsetZ);
                }
            }
            
            // --- Sound Effects (ย้ายมานี่ ได้ยิน 100%) ---
            if (ticksUsed % 5 == 0) {
                float pitch = 0.5f + ((float)ticksUsed / 60f);
                if (pitch > 2.0f) pitch = 2.0f;
                
                // ใช้ entity.playSound ดังกว่าและชัวร์กว่า
                entity.playSound(SoundEvents.BEACON_AMBIENT, 1.0f, pitch);
            }
            
            if (ticksUsed == 40) { // เสียงเตือนเมื่อใกล้เต็ม
                 entity.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
            }
        }
    }
}