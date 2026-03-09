package net.com.burntland.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal; // เพิ่ม Goal พื้นฐาน
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType; // เพิ่ม
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand; 
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundSource; // เพิ่ม
import net.minecraft.sounds.SoundEvents; // เพิ่ม
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel; // เพิ่ม
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.particles.ParticleTypes; // เพิ่ม
import net.minecraft.core.BlockPos;

import net.minecraft.world.entity.TamableAnimal;

import net.com.burntland.procedures.ExecutessplusThisEntityKillsAnotherOneProcedure;
import net.com.burntland.init.BurntLandModEntities;

import java.util.EnumSet; // เพิ่ม

public class ExecutessplusEntity extends PathfinderMob implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(ExecutessplusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(ExecutessplusEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(ExecutessplusEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Boolean> DATA_near = SynchedEntityData.defineId(ExecutessplusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> DATA_range = SynchedEntityData.defineId(ExecutessplusEntity.class, EntityDataSerializers.INT);
    
    // Sync Attack Timer
    public static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(ExecutessplusEntity.class, EntityDataSerializers.INT);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public String animationprocedure = "empty";
    
    // Cooldown สำหรับสกิลเรียกพวก
    private int summonCooldown = 0;

    public ExecutessplusEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(BurntLandModEntities.CARNIFEX.get(), world);
    }

    public ExecutessplusEntity(EntityType<ExecutessplusEntity> type, Level world) {
        super(type, world);
        xpReward = 125;
        setNoAi(false);
        setMaxUpStep(2f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "executea-geckover");
        this.entityData.define(DATA_near, true);
        this.entityData.define(DATA_range, 1);
        this.entityData.define(ATTACK_STATE, 0); 
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int time) {
        this.entityData.set(ATTACK_STATE, time);
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        
        // --- 1. SUMMON GOAL (Priority สูงสุดเพื่อให้หยุดเดินแล้วร่าย) ---
        this.goalSelector.addGoal(1, new SummonThrallsGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractGolem.class, true, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Monster.class, true, true));
        
        // --- CUSTOM ATTACK GOAL ---
        this.goalSelector.addGoal(5, new CarnifexAttackGoal(this, 1.2, false));
        
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(7, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new FloatGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("burntland:vice_exe"));
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("burntland:more2"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("ambient.soul_sand_valley.additions"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_FIRE)) return false;
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud) return false;
        return super.hurt(source, amount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
        compound.putBoolean("Datanear", this.entityData.get(DATA_near));
        compound.putInt("Datarange", this.entityData.get(DATA_range));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture"))
            this.setTexture(compound.getString("Texture"));
        if (compound.contains("Datanear"))
            this.entityData.set(DATA_near, compound.getBoolean("Datanear"));
        if (compound.contains("Datarange"))
            this.entityData.set(DATA_range, compound.getInt("Datarange"));
    }

    @Override
    public void awardKillScore(Entity entity, int score, DamageSource damageSource) {
        super.awardKillScore(entity, score, damageSource);
        ExecutessplusThisEntityKillsAnotherOneProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
        // ลดเวลา Attack Timer
        if (this.getAttackState() > 0) {
            this.setAttackState(this.getAttackState() - 1);
        }
        // ลดเวลา Summon Cooldown
        if (this.summonCooldown > 0) {
            this.summonCooldown--;
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose p_33597_) {
        return super.getDimensions(p_33597_).scale((float) 1);
    }

    public static void init() {
        SpawnPlacements.register(BurntLandModEntities.CARNIFEX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 200);
        builder = builder.add(Attributes.ARMOR, 15);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 15);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 3);
        return builder;
    }

    private PlayState movementPredicate(AnimationState event) {
        if (this.animationprocedure.equals("empty")) {
            // ถ้ากำลังโจมตี (Timer > 0) ให้หยุดเดินขา
            if (this.getAttackState() > 0) {
                return PlayState.STOP;
            }

            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))
                    && !this.isAggressive() && !this.isSprinting()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("realwalk"));
            }
            if (this.isDeadOrDying()) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("death"));
            }
            if (this.isSprinting()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("foundtarget"));
            }
            if (this.isAggressive() && event.isMoving()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("realwalk"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
        return PlayState.STOP;
    }

    private PlayState attackingPredicate(AnimationState event) {
        if (this.getAttackState() > 0) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("1att"));
        }
        event.getController().forceAnimationReset();
        return PlayState.STOP;
    }

    String prevAnim = "empty";

    private PlayState procedurePredicate(AnimationState event) {
        if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
            if (!this.animationprocedure.equals(prevAnim))
                event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                this.animationprocedure = "empty";
                event.getController().forceAnimationReset();
            }
        } else if (animationprocedure.equals("empty")) {
            prevAnim = "empty";
            return PlayState.STOP;
        }
        prevAnim = this.animationprocedure;
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 21) {
            this.remove(ExecutessplusEntity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    public String getSyncedAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(String animation) {
        this.entityData.set(ANIMATION, animation);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 0, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 0, this::attackingPredicate));
        data.add(new AnimationController<>(this, "procedure", 0, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    // ==========================================
    // CARNIFEX DASH ATTACK (FIXED: CLOSE RANGE HIT)
    // แก้ปัญหายืนชิดแล้วตีไม่โดน โดยใช้ Hitbox แบบกวาด
    // ==========================================
    class CarnifexAttackGoal extends MeleeAttackGoal {
            
            public CarnifexAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
                super(mob, speedModifier, followingTargetEvenIfNotSeen);
            }

            @Override
            public void tick() {
                LivingEntity target = this.mob.getTarget();
                if (target == null) {
                    super.tick();
                    return;
                }

                int currentTimer = ExecutessplusEntity.this.getAttackState();

                if (currentTimer > 0) {
                    // 1. หมุนตัวมองเป้าหมายตลอดเวลา
                    this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

                    // =======================================================
                    // จุดที่แก้ให้ 1: สั่งให้เวลานับถอยหลัง (สำคัญมาก ไม่งั้นเวลาค้างที่ 20 ตลอด)
                    // =======================================================
                    ExecutessplusEntity.this.setAttackState(currentTimer - 1);

                    // 2. ช่วงพุ่งเข้าหา (Dash)
                    if (currentTimer > 5) {
                        this.mob.getNavigation().moveTo(target, 1.8); 
                    } 
                    
                    // จังหวะฟาด (Tick 5)
                    if (currentTimer == 5) {
                        this.mob.getNavigation().stop();
                        this.mob.swing(InteractionHand.MAIN_HAND);

                        if (!this.mob.level().isClientSide()) {
                            
                            // หาทิศทางด้านหน้าจากมุมหัน (Yaw)
                            float yRot = this.mob.getYRot() * ((float)Math.PI / 180F);
                            double lookX = -Math.sin(yRot);
                            double lookZ = Math.cos(yRot);

                            // ดึงกล่องตัวละครมาพองออก 
                            net.minecraft.world.phys.AABB hitArea = this.mob.getBoundingBox().inflate(3.0D, 2.0D, 3.0D);
                            
                            // ขยับกล่องไปข้างหน้า 1.5 บล็อก
                            hitArea = hitArea.move(lookX * 1.5, 0, lookZ * 1.5);

                            java.util.List<LivingEntity> victims = this.mob.level().getEntitiesOfClass(LivingEntity.class, hitArea);

                            for (LivingEntity victim : victims) {
                                if (victim == this.mob || victim instanceof ExecutessplusEntity) continue;
                                
                                ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(victim.getType());
                                if (id != null && "burntland".equals(id.getNamespace())) continue;

                                // ฟาดทำดาเมจ!
                                boolean isHit = this.mob.doHurtTarget(victim);
                                
                                if (isHit && victim.getY() <= this.mob.getY() + 1.0D) {
                                    victim.setDeltaMovement(victim.getDeltaMovement().add(0, 0.4, 0)); 
                                }
                            }
                        }
                    }
                } else {
                    super.tick();
                }
            }

            @Override
            protected void checkAndPerformAttack(LivingEntity target, double distance) {
                double reach = this.getAttackReachSqr(target);

                // เริ่มท่าเมื่ออยู่ในระยะ
                if (distance <= reach && this.getTicksUntilNextAttack() <= 0) {
                    ExecutessplusEntity.this.setAttackState(20); 
                    this.resetAttackCooldown(); 
                }
            }
            
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                // =======================================================
                // จุดที่แก้ให้ 2: ใช้สูตรระยะตีตามขนาดตัวบอส ป้องกันการเดินชนพุงแล้วไม่ยอมตี
                // =======================================================
                return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + entity.getBbWidth());
            }
        }

    // ==========================================
    // FIXED: SUMMON THRALLS GOAL (Animation Fixed)
    // ==========================================
    class SummonThrallsGoal extends Goal {
        private final ExecutessplusEntity mob;
        private int castTime;

        public SummonThrallsGoal(ExecutessplusEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.mob.getTarget() != null && this.mob.summonCooldown <= 0 && this.mob.getRandom().nextInt(100) == 0;
        }

        @Override
        public boolean canContinueToUse() {
            return this.castTime > 0;
        }

        @Override
        public void start() {
            this.castTime = 40; 
            this.mob.summonCooldown = 600; 
            this.mob.getNavigation().stop();
            
            // --- FIX: สั่งเล่น Animation ---
            this.mob.animationprocedure = "bbsummon"; 
            
            // ส่งคำสั่งไปยัง Server เพื่อบอก Client ให้เล่นท่า
            if (!this.mob.level().isClientSide()) {
                this.mob.setAnimation("bbsummon");
            }
            
            this.mob.level().playSound(null, this.mob.blockPosition(), SoundEvents.BELL_BLOCK, SoundSource.HOSTILE, 2.0f, 0.5f);
        }

        @Override
        public void stop() {
            // --- FIX: จบแล้วกลับมายืนปกติ ---
            this.mob.animationprocedure = "empty";
            if (!this.mob.level().isClientSide()) {
                this.mob.setAnimation("empty");
            }
        }

        @Override
        public void tick() {
            this.castTime--;
            
            // หมุนตัวหาเป้าหมายขณะร่าย (ป้องกันการร่ายใสกำแพง)
            if (this.mob.getTarget() != null) {
                this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
            }

            // Particle Effects
            if (this.mob.level() instanceof ServerLevel _level) {
                 double radius = 1.5;
                 double angle = (this.castTime * 10) * (Math.PI / 180); 
                 double px = this.mob.getX() + Math.cos(angle) * radius;
                 double pz = this.mob.getZ() + Math.sin(angle) * radius;
                 _level.sendParticles(ParticleTypes.SOUL, px, this.mob.getY() + 0.5, pz, 1, 0, 0, 0, 0);
            }

            if (this.castTime == 10) {
                 spawnThralls();
            }
        }
        
        private void spawnThralls() {
             Level world = this.mob.level();
             if (world instanceof ServerLevel _level) {
                 for (int i = 0; i < 2; i++) {
                    double angleOffset = (i == 0) ? -1.5 : 1.5;
                    double spawnX = this.mob.getX() + Math.cos(this.mob.getYRot() * (Math.PI / 180) + angleOffset) * 2.5;
                    double spawnZ = this.mob.getZ() + Math.sin(this.mob.getYRot() * (Math.PI / 180) + angleOffset) * 2.5;
                    BlockPos spawnPos = BlockPos.containing(spawnX, this.mob.getY(), spawnZ);
                    
                    _level.sendParticles(ParticleTypes.POOF, spawnX, this.mob.getY() + 1, spawnZ, 10, 0.3, 0.3, 0.3, 0.1);
                    
                    // เรียก Entity ของคุณ
                    Entity entityToSpawn = BurntLandModEntities.SOUL_THRALLS.get().spawn(_level, spawnPos, MobSpawnType.MOB_SUMMONED);
                    
                    // --------------------------------------------------
                    // ระบบ Tame Entity ทันทีที่เกิด
                    // --------------------------------------------------
                    if (entityToSpawn instanceof TamableAnimal tamableThrall) {
                        // บังคับให้สถานะเป็น "เชื่อง"
                        tamableThrall.setTame(true);
                        
                        // แบบที่ 1: ให้ตัวที่ร่ายเวท (this.mob) เป็นเจ้าของลูกน้องตัวนี้
                        tamableThrall.setOwnerUUID(this.mob.getUUID());
                        
                        // แบบที่ 2: ถ้าตัวที่ร่ายเวท (this.mob) มีเจ้าของเป็นผู้เล่นอยู่แล้ว 
                        // และอยากส่งต่อให้ผู้เล่นคนนั้นเป็นเจ้าของลูกน้องตัวนี้ด้วย ให้ใช้โค้ดด้านล่างแทน:
                        /*
                        if (this.mob instanceof TamableAnimal summoner && summoner.isTame()) {
                            tamableThrall.setOwnerUUID(summoner.getOwnerUUID());
                        } else if (this.mob instanceof OwnableEntity ownable && ownable.getOwnerUUID() != null) {
                            tamableThrall.setOwnerUUID(ownable.getOwnerUUID());
                        }
                        */
                    }
                 }
                 world.playSound(null, this.mob.blockPosition(), SoundEvents.BELL_RESONATE, SoundSource.HOSTILE, 2.0f, 0.5f);
             }
        }
    }
}