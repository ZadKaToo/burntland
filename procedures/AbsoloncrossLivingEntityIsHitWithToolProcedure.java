package net.com.burntland.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.particles.ParticleTypes;

public class AbsoloncrossLivingEntityIsHitWithToolProcedure {
    // บรรทัดนี้สำคัญ! ต้องมีตัวแปรในวงเล็บให้ครบตามนี้
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;

        if (entity instanceof LivingEntity _entity) {
            // ทำให้ศัตรูตาบอด 3 วินาที (60 ticks)
            _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
        }
        
        // สร้าง Effect ควันดำ (Ash) ฟุ้งออกมาตอนตี
        if (world instanceof Level _level) {
            _level.addParticle(ParticleTypes.LARGE_SMOKE, x, y + 1, z, 0, 0.1, 0);
            _level.addParticle(ParticleTypes.ASH, x, y + 1, z, 0, 0, 0);
        }
    }
}