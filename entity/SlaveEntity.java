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
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.com.burntland.init.BurntLandModEntities;

public class SlaveEntity extends TamableAnimal implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(SlaveEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(SlaveEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(SlaveEntity.class, EntityDataSerializers.STRING);
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private long lastSwing;
    public String animationprocedure = "empty";
    private String prevAnim = "empty";

    public SlaveEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(BurntLandModEntities.SOUL_THRALLS.get(), world);
    }

    public SlaveEntity(EntityType<SlaveEntity> type, Level world) {
        super(type, world);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setMaxUpStep(1f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "slave");
    }

    public void setTexture(String texture) { this.entityData.set(TEXTURE, texture); }
    public String getTexture() { return this.entityData.get(TEXTURE); }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

	@Override
    protected void registerGoals() {
        super.registerGoals();
        
        // Priority 0: ว่ายน้ำไม่ให้จม
        this.goalSelector.addGoal(0, new FloatGoal(this));
        
        // ==========================================
        // 1. Action Goals (การกระทำ)
        // ==========================================
        // โจมตีระยะประชิด - ตั้งความเร็วเป็น 1.5 ให้มันวิ่งเข้าหาศัตรูเร็วขึ้น
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5, true) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) { 
                // เพิ่มระยะโจมตีเล็กน้อย (ตีถึงไวขึ้น)
                return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + entity.getBbWidth()); 
            }
        });
        
        // เดินตามเจ้าของ (ไม่ว่าจะเป็น Player หรือมอนสเตอร์)
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.2D, 10.0F, 2.0F, false));
        
        // เดินเล่นและมองรอบๆ เมื่อว่าง
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        // ==========================================
        // 2. Target Goals (การเลือกเป้าหมาย)
        // ==========================================
        // 1. ถ้าใครมาตีเจ้านาย ให้ไปตีมัน!
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        
        // 2. ถ้าเจ้านายกำลังตีใคร ให้ไปช่วยตีด้วย!
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        
        // 3. ถ้าตัวเองโดนตี ให้ตีสวน! (และร้องเรียกเพื่อน SlaveEntity ตัวอื่นให้มาช่วย)
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this, SlaveEntity.class).setAlertOthers());
        
        // 4. (ถ้ายังไม่มีเป้าหมายจากข้อ 1-3) ให้พุ่งเป้าไปที่มอนสเตอร์รอบตัว ยกเว้นพวกเดียวกัน
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Monster.class, false, (target) -> {
            // ไม่ตีพวกเดียวกัน และไม่ตีเจ้านายตัวเอง
            return !(target instanceof SlaveEntity) && !(this.getOwner() != null && target.equals(this.getOwner()));
        }));
    }

    @Override
    public MobType getMobType() { return MobType.UNDEFINED; }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(Items.POISONOUS_POTATO));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.husk.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.pillager.death"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_FIRE) || source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud) {
            return false;
        }
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
        if (compound.contains("Texture")) this.setTexture(compound.getString("Texture"));
    }

	// ==========================================
    // ทำให้ยอมรับเจ้านายที่ไม่ใช่ผู้เล่นได้ (มอนสเตอร์ด้วยกัน)
    // ==========================================
    @javax.annotation.Nullable
    @Override
    public LivingEntity getOwner() {
        java.util.UUID uuid = this.getOwnerUUID();
        if (uuid == null) {
            return null;
        }
        
        // ค้นหา Entity ทุกชนิดบน Server ที่มี UUID ตรงกัน (ไม่จำกัดแค่ผู้เล่น)
        if (this.level() instanceof ServerLevel serverLevel) {
            net.minecraft.world.entity.Entity ownerEntity = serverLevel.getEntity(uuid);
            if (ownerEntity instanceof LivingEntity livingOwner) {
                return livingOwner;
            }
        }
        
        // หากหาไม่เจอ ให้ใช้ระบบปกติของเกมสำรองไว้
        return super.getOwner();
    }

    // ==========================================
    // LOGIC: Taming, Healing, and Interaction
    // ==========================================
    @Override
    public boolean isFood(ItemStack stack) {
        // FIXME: Change Items.ROTTEN_FLESH to whatever item you want to use to tame/heal this mob!
        return stack.is(Items.ROTTEN_FLESH); 
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        if (itemstack.getItem() instanceof SpawnEggItem) {
            return super.mobInteract(player, hand);
        }

        if (this.level().isClientSide()) {
            return (this.isTame() && this.isOwnedBy(player)) || this.isFood(itemstack) ? InteractionResult.CONSUME : InteractionResult.PASS;
        }

        if (this.isTame()) {
            // Healing
            if (this.isOwnedBy(player) && this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.usePlayerItem(player, hand, itemstack);
                this.heal(item.isEdible() ? (float) item.getFoodProperties().getNutrition() : 4f);
                return InteractionResult.SUCCESS;
            }
        } 

        InteractionResult superResult = super.mobInteract(player, hand);
        if (superResult.consumesAction()) this.setPersistenceRequired();
        return superResult;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
        SlaveEntity retval = BurntLandModEntities.SOUL_THRALLS.get().create(serverWorld);
        if (retval != null) {
            retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
        }
        return retval;
    }

    public static void init() {
        SpawnPlacements.register(BurntLandModEntities.SOUL_THRALLS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR, 5)
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
    }

    // ==========================================
    // LOGIC: Animations (Geckolib)
    // ==========================================
    private PlayState movementPredicate(AnimationState<SlaveEntity> event) {
        if (this.animationprocedure.equals("empty")) {
            if (this.isDeadOrDying()) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("die"));
            }
            if (event.isMoving() || event.getLimbSwingAmount() > 0.15F || event.getLimbSwingAmount() < -0.15F) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walkk"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
        return PlayState.STOP;
    }

    private PlayState attackingPredicate(AnimationState<SlaveEntity> event) {
        if (this.getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = this.level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 13L <= this.level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("atta"));
        }
        return PlayState.CONTINUE;
    }

    private PlayState procedurePredicate(AnimationState<SlaveEntity> event) {
        if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty"))) {
            if (!this.animationprocedure.equals(this.prevAnim)) event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                this.animationprocedure = "empty";
                event.getController().forceAnimationReset();
            }
        } else if (this.animationprocedure.equals("empty")) {
            this.prevAnim = "empty";
            return PlayState.STOP;
        }
        this.prevAnim = this.animationprocedure;
        return PlayState.CONTINUE;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 25) {
            this.remove(SlaveEntity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    public String getSyncedAnimation() { return this.entityData.get(ANIMATION); }
    public void setAnimation(String animation) { this.entityData.set(ANIMATION, animation); }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
        data.add(new AnimationController<>(this, "attacking", 4, this::attackingPredicate));
        data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return this.cache; }
}