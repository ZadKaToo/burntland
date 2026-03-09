package net.com.burntland.entity;

import net.com.burntland.procedures.AbsoloncrossToolInHandTickProcedure;
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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.com.burntland.procedures.ExecutessplusThisEntityKillsAnotherOneProcedure;
import net.com.burntland.procedures.AbsoloncrossPlayerFinishesUsingItemProcedure;
import net.com.burntland.init.BurntLandModEntities;

public class ForsinEntity extends PathfinderMob implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(ForsinEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(ForsinEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(ForsinEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(ForsinEntity.class, EntityDataSerializers.INT);
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public String animationprocedure = "empty";
    
    // --- เพิ่มตัวแปร Cooldown ตรงนี้ (เพื่อให้ baseTick มองเห็น) ---
    public int skillCooldown = 0; 

    public ForsinEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(BurntLandModEntities.ABSOLON.get(), world);
    }

    public ForsinEntity(EntityType<ForsinEntity> type, Level world) {
        super(type, world);
        xpReward = 50;
        setNoAi(false);
        setMaxUpStep(1f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "crosssintex");
        this.entityData.define(ATTACK_STATE, 0); 
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int time) {
        this.entityData.set(ATTACK_STATE, time);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
        
        // --- ลด Attack Timer (Melee) ---
        if (this.getAttackState() > 0) {
            this.setAttackState(this.getAttackState() - 1);
        }

        // --- ลด Skill Cooldown (Skill) ---
        // ใส่ตรงนี้ เพื่อให้มันลดตลอดเวลา ไม่ว่า Goal ไหนจะทำงานอยู่
        if (this.skillCooldown > 0) {
            this.skillCooldown--;
        }

        AbsoloncrossToolInHandTickProcedure.execute(
            this.level(), this.getX(), this.getY(), this.getZ(), this
        );
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
        
        // *** FIX PRIORITY: สลับลำดับกัน ***
        // ให้ Skill (Priority 1) สำคัญกว่าการเดินตี (Priority 2)
        // ถ้า Skill พร้อมใช้ มันจะหยุดเดินแล้วชาร์จ ถ้าไม่พร้อม มันจะเดินไปตี
        this.goalSelector.addGoal(1, new ForsinChargeSkillGoal(this));
        this.goalSelector.addGoal(2, new ForsinAttackGoal(this, 1.2, false));
        
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractGolem.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Monster.class, false, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Player.class, false, false));
        
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
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("burntland:forlive"));
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
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture"))
            this.setTexture(compound.getString("Texture"));
    }

    @Override
    public void awardKillScore(Entity entity, int score, DamageSource damageSource) {
        super.awardKillScore(entity, score, damageSource);
        ExecutessplusThisEntityKillsAnotherOneProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
    }

    @Override
    public EntityDimensions getDimensions(Pose p_33597_) {
        return super.getDimensions(p_33597_).scale((float) 1);
    }

    public static void init() {
        SpawnPlacements.register(BurntLandModEntities.ABSOLON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 100);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 20);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.5);
        return builder;
    }

    // --- Animation Predicates ---
    private PlayState movementPredicate(AnimationState event) {
        if (this.animationprocedure.equals("empty")) {
            if (this.getAttackState() > 0) {
                return PlayState.STOP;
            }

            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("bbwalk"));
            }
            if (this.isDeadOrDying()) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("bbdie"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idlereal"));
        }
        return PlayState.STOP;
    }

    private PlayState attackingPredicate(AnimationState event) {
        if (this.getAttackState() > 0) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("bbattack"));
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
        if (this.deathTime == 20) {
            this.remove(ForsinEntity.RemovalReason.KILLED);
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
        data.add(new AnimationController<>(this, "movement", 1, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 1, this::attackingPredicate));
        data.add(new AnimationController<>(this, "procedure", 1, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    // ==========================================
    // CUSTOM ATTACK GOAL (HARD MODE: DASH & CHASE)
    // ==========================================
    class ForsinAttackGoal extends MeleeAttackGoal {
        public ForsinAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            int currentTimer = ForsinEntity.this.getAttackState();

            if (currentTimer > 0) {
                // 1. Aimbot Tracking
                this.mob.getLookControl().setLookAt(target, 180.0F, 180.0F); 
                
                // 2. Dash Mechanic
                if (currentTimer > 5) { 
                      this.mob.getNavigation().moveTo(target, 1.6);
                } else {
                      this.mob.getNavigation().stop();
                }

                // 3. Hit Logic (Tick 5)
                if (currentTimer == 5) { 
                    this.mob.swing(InteractionHand.MAIN_HAND);
                    double range = (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + target.getBbWidth()) + 5.0D;
                    
                    if (this.mob.distanceToSqr(target) <= range) {
                        this.mob.doHurtTarget(target);
                    }
                }
            } else {
                super.tick();
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity target, double distance) {
            double reach = this.getAttackReachSqr(target);

            if (distance <= reach && this.getTicksUntilNextAttack() <= 0) {
                ForsinEntity.this.setAttackState(25); 
                this.resetAttackCooldown(); 
            }
        }
        
        @Override
        protected double getAttackReachSqr(LivingEntity entity) {
            return 20.0D; 
        }
    }

    // ==========================================
    // SKILL GOAL: CHARGE & RELEASE (With Animation)
    // ==========================================
    class ForsinChargeSkillGoal extends net.minecraft.world.entity.ai.goal.Goal {
        private final ForsinEntity mob;
        private int chargeTimer;
        private final int MAX_CHARGE = 30; // 1.5 วินาที (ควรปรับ Animation ให้ยาวประมาณนี้)

        public ForsinChargeSkillGoal(ForsinEntity mob) {
            this.mob = mob;
            this.setFlags(java.util.EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP)); 
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            // เช็คเงื่อนไขตามเดิม
            return target != null && target.isAlive() 
                    && this.mob.skillCooldown <= 0 
                    && this.mob.getAttackState() == 0 
                    && this.mob.distanceToSqr(target) > 25.0D && this.mob.distanceToSqr(target) < 450.0D;
        }

        @Override
        public boolean canContinueToUse() {
             return this.canUse() && this.chargeTimer < MAX_CHARGE;
        }

        @Override
        public void start() {
            this.chargeTimer = 0;
            this.mob.getNavigation().stop(); // หยุดเดิน
            
            // --- เริ่มเล่น Animation "bbskill" ---
            // สั่งให้ตัวแปร animationprocedure เป็นชื่ออนิเมชั่น
            this.mob.animationprocedure = "bbskill";
            
            // ส่ง Packet บอก Server ว่าอนิเมชั่นเปลี่ยนแล้ว (เพื่อให้คนอื่นเห็นด้วย)
            Level world = this.mob.level();
            if (!world.isClientSide()) {
                this.mob.setAnimation("bbskill"); // ต้องมี Method setAnimation ใน Entity หลัก
            }
        }

        @Override
        public void stop() {
            this.chargeTimer = 0;
            this.mob.skillCooldown = 200; // Cooldown 10 วินาที
            this.mob.setAttackState(0);
            
            // --- จบการทำงาน รีเซ็ต Animation ---
            // กลับไปเป็น empty เพื่อให้กลับไปเดิน/ยืนปกติ
            this.mob.animationprocedure = "empty";
            
            Level world = this.mob.level();
            if (!world.isClientSide()) {
                this.mob.setAnimation("empty");
            }
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            // หันหน้าหาเป้า (สำคัญมาก เพื่อให้ท่าปล่อยสกิลตรงเป้า)
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            
            this.chargeTimer++;

            Level world = this.mob.level();
            
            // Particle Effects
            if (world instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                if (this.chargeTimer % 4 == 0) {
                    serverLevel.sendParticles(ParticleTypes.SMOKE, 
                        this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ(), 
                        5, 0.4, 0.4, 0.4, 0.05); 
                }
                if (this.chargeTimer > 20 && this.mob.getRandom().nextBoolean()) {
                     serverLevel.sendParticles(ParticleTypes.PORTAL,
                        this.mob.getX(), this.mob.getY() + 1.0, this.mob.getZ(),
                        2, 0.5, 0.5, 0.5, 0.1);
                }
            }

            // Sound Effects
            if (this.chargeTimer % 5 == 0) {
                float pitch = 0.5f + ((float)this.chargeTimer / 60f);
                if (pitch > 2.0f) pitch = 2.0f;
                this.mob.playSound(SoundEvents.BEACON_AMBIENT, 1.0f, pitch);
            }
            
            if (this.chargeTimer == 40) { // บรรทัดนี้อาจจะไม่ได้ทำงานถ้า MAX_CHARGE = 30 (ปรับ logic ได้ตามต้องการ)
                this.mob.playSound(SoundEvents.RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
            }

            if (this.chargeTimer >= MAX_CHARGE) {
                performSkill();
            }
        }

        private void performSkill() {
            this.mob.getPersistentData().putDouble("AbsolonPower", this.chargeTimer);

            // เรียก Procedure ยิงสกิล
            AbsoloncrossPlayerFinishesUsingItemProcedure.execute(
                this.mob.level(), 
                this.mob.getX(), this.mob.getY(), this.mob.getZ(), 
                this.mob, 
                this.mob.getMainHandItem()
            );

            // สั่งหยุด Goal ทันทีเมื่อยิงเสร็จ (จะไปเรียก stop() อัตโนมัติ)
            this.stop();
        }
    }
}