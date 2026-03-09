package net.com.burntland.item;

// --- Imports ---
import net.com.burntland.init.BurntLandModEntities;
import net.com.burntland.entity.SlaveEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.client.gui.screens.Screen;
import java.util.List;

public class ExecutioneraxeItem extends AxeItem {
    public ExecutioneraxeItem() {
        super(new Tier() {
            public int getUses() { return 2031; }
            public float getSpeed() { return 9f; }
            public float getAttackDamageBonus() { return 13f; }
            public int getLevel() { return 4; }
            public int getEnchantmentValue() { return 10; }
            public Ingredient getRepairIngredient() { return Ingredient.of(); }
        }, 1, -3.3f, new Item.Properties().fireResistant());
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected && !world.isClientSide() && entity instanceof LivingEntity livingEntity) {
            if (!livingEntity.hasEffect(MobEffects.DAMAGE_BOOST) || livingEntity.getEffect(MobEffects.DAMAGE_BOOST).getDuration() < 5) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0, false, false));
            }
            if (!livingEntity.hasEffect(MobEffects.HUNGER) || livingEntity.getEffect(MobEffects.HUNGER).getDuration() < 5) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 1, false, false));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (Screen.hasShiftDown()) {
            list.add(Component.literal(""));
            // ชื่อสกิลเป็นสีขาวปกติ (ไม่ใส่สี)
            list.add(Component.literal("Ability: Death Knell"));
            
            // คำอธิบายเป็นสีเทา (§7) ทั้งหมด
            list.add(Component.literal("§7Right-click targets with <30% HP"));
            list.add(Component.literal("§7to execute them instantly."));
            list.add(Component.literal("§7Success kills summon a Soul Thrall."));
            list.add(Component.literal(""));
            
            // ข้อมูล Cost ก็ใช้สีเทาเช่นกัน
            list.add(Component.literal("§7Cost: 6 Hunger | Cooldown: 5s"));
        } else {
            // ข้อความบอกปุ่มกด ใช้สีเทาเข้ม (§8)
            list.add(Component.literal("§8Press Shift for details"));
        }
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && !player.level().isClientSide()) {
            
            if (target instanceof net.minecraft.world.entity.decoration.ArmorStand) {
                return InteractionResult.PASS;
            }

            if (target.getType().is(net.minecraft.tags.EntityTypeTags.FALL_DAMAGE_IMMUNE) || target.getMaxHealth() >= 150) { 
                player.displayClientMessage(Component.literal("§cThis entity is too powerful to execute!"), true);
                return InteractionResult.FAIL;
            }

            if (player.getCooldowns().isOnCooldown(this)) return InteractionResult.FAIL;

            float threshold = target.getMaxHealth() * 0.30f;
            
            if (target.getHealth() <= threshold) {
                if (player.getFoodData().getFoodLevel() >= 6) {
                    
                    // จ่ายค่าร่ายไปก่อน 6 หน่วย
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 6);
                    player.getCooldowns().addCooldown(this, 100);
                    player.swing(hand, true);

                    if (player.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, target.getX(), target.getEyeY(), target.getZ(), 1, 0, 0, 0, 0);
                        serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                                target.getX(), target.getEyeY(), target.getZ(), 80, 0.4, 0.4, 0.4, 0.3);
                        serverLevel.sendParticles(ParticleTypes.SQUID_INK, target.getX(), target.getEyeY(), target.getZ(), 10, 0.5, 0.5, 0.5, 0.1);
                        
                        player.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.BELL_BLOCK, SoundSource.PLAYERS, 2.0f, 0.5f);
                        player.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.BELL_RESONATE, SoundSource.PLAYERS, 1.0f, 1.0f);
                    }

                    target.hurt(player.level().damageSources().playerAttack(player), 99999f);

                    if (target.isDeadOrDying()) {
                         spawnSlave(player, target.getX(), target.getY(), target.getZ());
                         
                         // --- [NEW] ฆ่าสำเร็จ -> กินวิญญาณ (เพิ่มเลือดอาหาร) ---
                         // เพิ่มอาหาร 10 หน่วย (5 น่องไก่) และ Saturation 0.8
                         // (หักลบกับที่จ่ายไป 6 เท่ากับกำไร 4 หน่วย)
                         player.getFoodData().eat(2, 0.4f);
                         player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0f, 1.0f);
                         // ----------------------------------------------------

                         player.displayClientMessage(Component.literal("§4§lJUDGMENT SERVED."), true);
                         return InteractionResult.SUCCESS;
                    } else {
                         player.displayClientMessage(Component.literal("§eTarget survived the execution!"), true);
                         return InteractionResult.FAIL;
                    }

                } else {
                    player.displayClientMessage(Component.literal("§cYou are too hungry..."), true);
                    return InteractionResult.FAIL;
                }
            } else {
                player.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.5f, 1.5f);
                player.displayClientMessage(Component.literal("§7Target is too strong! (<30% HP needed)"), true);
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.PASS;
    }

    private void spawnSlave(Player owner, double x, double y, double z) {
        if (owner.level() instanceof ServerLevel serverLevel) {
            
            int maxSlaves = 3;
            
            java.util.List<SlaveEntity> mySlaves = serverLevel.getEntities(
                BurntLandModEntities.SOUL_THRALLS.get(), 
                owner.getBoundingBox().inflate(100), 
                e -> e.isOwnedBy(owner)
            );

            if (mySlaves.size() >= maxSlaves) {
                Entity oldSlave = mySlaves.get(0); 
                
                serverLevel.sendParticles(ParticleTypes.POOF, oldSlave.getX(), oldSlave.getY(), oldSlave.getZ(), 10, 0.2, 0.5, 0.2, 0.05);
                oldSlave.remove(Entity.RemovalReason.DISCARDED);
                
                owner.displayClientMessage(Component.literal("§8Old thrall dispelled..."), true);
            }

            Entity slave = BurntLandModEntities.SOUL_THRALLS.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
            
            if (slave != null) {
                if (slave instanceof TamableAnimal tamable) {
                    tamable.tame(owner);
                    tamable.setOwnerUUID(owner.getUUID());
                }
                
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, x, y + 1, z, 15, 0.5, 0.5, 0.5, 0.1);
                serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 20, 0.5, 1.0, 0.5, 0.1);
            }
        }
    }
}