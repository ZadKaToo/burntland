package net.com.burntland.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.server.level.ServerLevel;

public class AntiLybbPlayerFinishesUsingItemProcedure {
    public static void execute(Entity entity) {
        if (entity == null) return;

        double currentLevel = entity.getPersistentData().getDouble("lybb_mutation");
        double decreaseAmount = 10;
        double newLevel = Math.max(0, currentLevel - decreaseAmount);

        entity.getPersistentData().putDouble("lybb_mutation", newLevel);
        if (newLevel == 0) {
            entity.getPersistentData().putBoolean("useit", false);
        }

        if (entity instanceof LivingEntity _entity) {
            // ล้าง Effect (เหมือนเดิม)
            _entity.removeEffect(MobEffects.CONFUSION);
            _entity.removeEffect(MobEffects.WITHER);
            _entity.removeEffect(MobEffects.BLINDNESS);
            _entity.removeEffect(MobEffects.DIG_SLOWDOWN);
            _entity.removeEffect(MobEffects.WEAKNESS);
            _entity.removeEffect(MobEffects.HUNGER);
            _entity.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
            _entity.removeEffect(MobEffects.UNLUCK);
            _entity.removeEffect(MobEffects.MOVEMENT_SPEED);
            _entity.removeEffect(MobEffects.DIG_SPEED);
            _entity.removeEffect(MobEffects.DAMAGE_RESISTANCE);
            _entity.removeEffect(MobEffects.DAMAGE_BOOST);
            _entity.removeEffect(MobEffects.FIRE_RESISTANCE);
            _entity.removeEffect(MobEffects.REGENERATION);
            _entity.removeEffect(MobEffects.NIGHT_VISION);
            _entity.removeEffect(MobEffects.JUMP);
            _entity.removeEffect(MobEffects.GLOWING);

            // --- ระบบคิวเสียง (Queue System) ---
            if (!_entity.level().isClientSide()) {
                
                // 1. เสียง Totem (เล่นทันที หรือต่อคิวจากเสียงเก่า)
                // ความยาวเสียงประมาณ 1000ms (1 วินาที)
                // ใช้เสียง Warden Heartbeat
                queueSound(_entity, SoundEvents.WARDEN_HEARTBEAT.getLocation().toString(), 1000, 1.0f);

                // 2. เสียงไฟดับ (เล่นต่อจาก Totem)
                // ความยาวเสียงประมาณ 500ms
                queueSound(_entity, SoundEvents.GENERIC_EXTINGUISH_FIRE.getLocation().toString(), 500, 0.5f);

                if (entity instanceof Player _player) {
                    if (newLevel == 0) {
                        _player.displayClientMessage(Component.literal("§aThe whispers vanish. You are alone in your mind once more"), true);

                        // 3. เสียง Clear (เล่นต่อจากไฟดับ)
                        // ให้เวลาเสียงนี้นานหน่อย 3000ms
                        queueSound(_player, "burntland:clear", 3000, 100.0f); // Volume 100

                    } else {
                        _player.displayClientMessage(Component.literal("§bThe mutation recedes... (Current Level: " + (int)newLevel + ")"), true);
                    }
                }
            }
        }
    }

    /**
     * ระบบจัดการคิวเสียง (Smart Queue)
     * @param entity ตัวละครที่จะเล่นเสียง
     * @param soundId ID ของเสียง (เช่น "burntland:clear" หรือ "minecraft:item.totem.use")
     * @param durationMs ความยาวของเสียงนี้ (มิลลิวินาที) เพื่อกันที่ให้เสียงถัดไป
     * @param volume ความดัง
     */
    private static void queueSound(Entity entity, String soundId, int durationMs, float volume) {
        if (entity.level().isClientSide()) return; // ทำงานฝั่ง Server เท่านั้น

        long currentTime = System.currentTimeMillis();
        long queueEndTime = entity.getPersistentData().getLong("AudioQueueFinishTime");

        // ถ้าคิวเก่าจบไปนานแล้ว ให้เริ่มนับเวลาจากปัจจุบัน
        if (queueEndTime < currentTime) {
            queueEndTime = currentTime;
        }

        // คำนวณเวลาที่ต้องรอ (Delay)
        long delay = queueEndTime - currentTime;

        // อัปเดตเวลาจบของคิวใหม่ (เผื่อให้เสียงถัดไป)
        entity.getPersistentData().putLong("AudioQueueFinishTime", queueEndTime + durationMs);

        // สร้าง Thread แยกเพื่อนับเวลาถอยหลัง
        new Thread(() -> {
            try {
                if (delay > 0) Thread.sleep(delay);
            } catch (InterruptedException e) {}

            // ส่งคำสั่งกลับไปทำงานที่ Main Thread ของ Server
            if (entity.level() instanceof ServerLevel _serverLevel) {
                _serverLevel.getServer().execute(() -> {
                    // ตรวจสอบว่า Entity ยังอยู่ไหมก่อนเล่น
                    if (entity.isAlive()) {
                        SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse(soundId));
                        if (sound != null) {
                            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), 
                                sound, SoundSource.MASTER, volume, 1.0f);
                        }
                    }
                });
            }
        }).start();
    }
}