package net.com.burntland.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.Comparator;

public class AbsoloncrossToolInHandTickProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null) return;

        if (entity.getPersistentData().getBoolean("AbsolonLeap")) {
            
            // --- PHASE 1: AIRBORNE (ขณะลอยกลางอากาศ) ---
            if (!entity.onGround()) {
                entity.getPersistentData().putBoolean("AbsolonAirborne", true);
                
                // เพิ่มแรงดึงดูดให้พุ่งลงพื้นเร็วขึ้นเล็กน้อย (Gravity Slam)
                if (entity.getDeltaMovement().y < 0) {
                     entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.1, 0));
                }

                if (world instanceof ServerLevel _level) {
                    // ทิ้งรอยหางสีดำและไฟวิญญาณ (Comet Trail)
                    _level.sendParticles(ParticleTypes.SQUID_INK, x, y + 0.5, z, 5, 0.2, 0.2, 0.2, 0);
                    _level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y + 0.5, z, 2, 0.2, 0.2, 0.2, 0.05);
                    _level.sendParticles(ParticleTypes.DRAGON_BREATH, x, y + 0.5, z, 1, 0, 0, 0, 0.05);
                }
            }
            
            // --- PHASE 2: IMPACT (เมื่อเท้าแตะพื้น) ---
            else if (entity.onGround() && entity.getPersistentData().getBoolean("AbsolonAirborne")) {
                
                // === 1. VISUAL FX (อลังการ) ===
                if (world instanceof ServerLevel _level) {
                    // A. ระเบิดตูมใหญ่ตรงกลาง
                    _level.sendParticles(ParticleTypes.SCULK_SOUL, x, y + 1, z, 10, 0.2, 0.2, 0.2, 0.05);
                    _level.sendParticles(ParticleTypes.FLASH, x, y + 1, z, 1, 0, 0, 0, 0); // แสงวาบ

                    // B. วงแหวนคลื่นกระแทก (Shockwave Ring)
                    // วนลูปสร้างวงกลม 2 วง (วงใน-วงนอก)
                    for (int i = 0; i <= 360; i += 10) {
                        double angle = Math.toRadians(i);
                        
                        // วงที่ 1: ไฟวิญญาณ (รัศมี 4 บล็อก)
                        double px1 = x + Math.cos(angle) * 4.0;
                        double pz1 = z + Math.sin(angle) * 4.0;
                        _level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, px1, y, pz1, 1, 0, 0.1, 0, 0.1);
                        
                        // วงที่ 2: ควันดำพุ่งออกข้าง (รัศมี 6 บล็อก)
                        double px2 = x + Math.cos(angle) * 6.0;
                        double pz2 = z + Math.sin(angle) * 6.0;
                        // คำนวณ Vector ให้ควันพุ่งออกจากจุดศูนย์กลาง
                        double vx = Math.cos(angle) * 0.3;
                        double vz = Math.sin(angle) * 0.3;
                        _level.sendParticles(ParticleTypes.SQUID_INK, px2, y, pz2, 0, vx, 0.1, vz, 1);
                    }

                    // C. เสาแห่งความมืด (Dark Pillar) พุ่งขึ้นฟ้า
                    for (int h = 0; h < 5; h++) {
                        _level.sendParticles(ParticleTypes.DRAGON_BREATH, x, y + h, z, 10, 0.5, 0.5, 0.5, 0.05);
                        _level.sendParticles(ParticleTypes.WITCH, x, y + h, z, 5, 0.5, 0.5, 0.5, 0);
                    }
                }

                // === 2. SOUND FX ===
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        // 1. เสียงกรีดร้องจากความมืด (Shrieker) - ปรับ Pitch ต่ำ (0.5) ให้เสียงเหมือนปีศาจคำรามลากยาว
                        _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.sculk_shrieker.shriek")), SoundSource.HOSTILE, 2.0f, 0.5f);

                        // 2. เสียงคำสาปโบราณ (Elder Guardian Curse) - เสียงวิ้งๆ หลอนๆ ที่ผู้เล่นได้ยินตอนติด Curse
                        _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.elder_guardian.curse")), SoundSource.HOSTILE, 1.0f, 1.0f);

                        // 3. เสียงวิญญาณโฉบ (Phantom Swoop) - เสียงลมหายใจแรงๆ ของผี
                        _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.phantom.swoop")), SoundSource.HOSTILE, 1.5f, 0.7f);
                    }
                }

                // === 3. DEVASTATING DAMAGE (ดาเมจล้างผลาญ) ===
                {
                    final Vec3 _center = new Vec3(x, y, z);
                    // ขยายระยะทำลายเป็น 10 บล็อก
                    List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
                    
                    for (Entity entityiterator : _entfound) {
                        if (entityiterator != entity) { // ไม่ทำตัวเอง
                             if (entityiterator instanceof LivingEntity _livEnt) {
                                // ดาเมจเวทย์รุนแรง (25 หน่วย)
                                _livEnt.hurt(new net.minecraft.world.damagesource.DamageSource(world.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getHolderOrThrow(net.minecraft.world.damagesource.DamageTypes.MAGIC), entity), 14);
                                
                                // สถานะ Wither รุนแรง (Level 2)
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 1));
                                // สถานะ Slow (หนืด)
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
                                // สถานะ Levitation (ยกศัตรูให้ลอยขึ้น 1 วิ แล้วตกลงมาเจ็บ Fall Damage)
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 15, 2));
                                
                            }
                        }
                    }
                }

                // === 4. USER SIDE EFFECTS (ผลกระทบต่อผู้ใช้) ===
                if (entity instanceof LivingEntity _user) {
                    // ถ้าเป็น Player ให้โดนดาเมจย้อนกลับ (Curse)
                    if (entity instanceof net.minecraft.world.entity.player.Player) {
                        _user.hurt(new net.minecraft.world.damagesource.DamageSource(world.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE).getHolderOrThrow(net.minecraft.world.damagesource.DamageTypes.MAGIC)), 4);
                        _user.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
                    } 
                    // ถ้าเป็น Mob (ForsinEntity) ไม่ต้องโดนดาเมจ แต่ให้หยุดนิ่งแป๊บนึง (Recover)
                    else {
                        _user.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4)); // หยุดเดิน 2 วิ
                    }
                }
                
                // รีเซ็ตสถานะ
                entity.getPersistentData().putBoolean("AbsolonLeap", false);
                entity.getPersistentData().putBoolean("AbsolonAirborne", false);
            }
        }
    }
}