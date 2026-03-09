package net.com.burntland.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class AbsoloncrossPlayerFinishesUsingItemProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity != null) {
            // ดึงค่าพลัง AbsolonPower
            double power = entity.getPersistentData().getDouble("AbsolonPower");
            
            if (power > 20.0D) {
                // หาพิกัดการมองและตั้งค่าแรงผลักให้พุ่งตัว (Leap)
                Vec3 look = entity.getLookAngle();
                entity.setDeltaMovement(look.x * 3.0D, 1.2D, look.z * 3.0D);
                entity.hasImpulse = true;
                
                // อัปเดตสถานะของ Entity
                entity.getPersistentData().putBoolean("AbsolonLeap", true);
                entity.getPersistentData().putBoolean("AbsolonAirborne", false);
                
                // เล่นเสียงเมื่อทำงานบนฝั่ง Server
                if (world instanceof Level _level && !_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(x, y, z), 
                        ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.wither.shoot")), 
                        SoundSource.HOSTILE, 1.5F, 0.5F);
                    _level.playSound(null, BlockPos.containing(x, y, z), 
                        ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.warden.sonic_boom")), 
                        SoundSource.HOSTILE, 1.0F, 1.0F);
                }

                // สร้างเอฟเฟกต์อนุภาค (Particles)
                if (world instanceof ServerLevel _level) {
                    _level.sendParticles(ParticleTypes.EXPLOSION, x, y + 1.0D, z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                    _level.sendParticles(ParticleTypes.CLOUD, x, y + 1.0D, z, 30, 0.5D, 0.5D, 0.5D, 0.2D);
                    _level.sendParticles(ParticleTypes.SMOKE, x, y + 1.0D, z, 20, 0.5D, 0.5D, 0.5D, 0.1D);

                    // สร้างเอฟเฟกต์เป็นวงกลม 360 องศารอบตัว
                    for(int i = 0; i < 360; i += 20) {
                        double angle = Math.toRadians((double)i);
                        double px = x + Math.cos(angle) * 1.5D;
                        double pz = z + Math.sin(angle) * 1.5D;
                        _level.sendParticles(ParticleTypes.FLAME, px, y, pz, 1, 0.0D, 0.0D, 0.0D, 0.1D);
                    }
                }

                // ตั้งค่าคูลดาวน์ให้ไอเทมที่ผู้เล่นใช้ (100 Ticks = 5 วินาที)
                if (entity instanceof Player _player) {
                    _player.getCooldowns().addCooldown(itemstack.getItem(), 100);
                }
            }
        }
    }
}