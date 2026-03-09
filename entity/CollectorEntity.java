package net.com.burntland.entity; // แก้ไข Package ให้ตรงกับของคุณถ้าต่างกัน

import net.com.burntland.init.BurntLandModSounds;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.tags.BlockTags;
import net.com.burntland.init.BurntLandModBlocks;

import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationController;

import net.com.burntland.init.BurntLandModEntities;
import net.com.burntland.init.BurntLandModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraftforge.registries.ForgeRegistries;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import net.minecraftforge.network.PlayMessages;

public class CollectorEntity extends PathfinderMob implements GeoEntity, net.minecraft.world.item.trading.Merchant {
    
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(CollectorEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(CollectorEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(CollectorEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Boolean> TRADING = SynchedEntityData.defineId(CollectorEntity.class, EntityDataSerializers.BOOLEAN);
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private boolean lastloop;
    private long lastSwing;
    public String animationprocedure = "empty";
    private Player tradingPlayer;
    
    // ตัวแปรเก็บรายการสินค้า
    private static List<Item> cachedCommonItems = null;
    private MerchantOffers myOffers = null;

    // Helper Method เช็คของกิน
    private static boolean isValidItem(Item item) {
        if (item == Items.AIR) return false;
        return item.isEdible(); 
    }

    // Constructor เดิมที่คุณมีอยู่แล้ว
    public CollectorEntity(EntityType<? extends CollectorEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setMaxUpStep(0.6f);
        setPersistenceRequired();
    }

    
    public CollectorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(BurntLandModEntities.COLLECTOR.get(), world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "collector_tra_tex_fixed");
        this.entityData.define(TRADING, false);
    }

    public void setTrading(Player player) {
        this.tradingPlayer = player;
        // เช็คก่อนเซ็ตค่า เพื่อไม่ให้แอนิเมชันรีเซ็ตเวลากดคลิกรัวๆ
        if (!this.entityData.get(TRADING)) {
            this.entityData.set(TRADING, true);
        }
    }

    public void stopTrading() {
        this.tradingPlayer = null;
        this.entityData.set(TRADING, false);
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.2));
        this.targetSelector.addGoal(5, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new FloatGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("wandering_trader_ambient"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("burntland:collecthurt"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
        
        // เซฟข้อมูลร้านค้าลงไปในตัว
        if (this.myOffers != null) {
            compound.put("Offers", this.myOffers.createTag());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture")) {
            this.setTexture(compound.getString("Texture"));
        }
        
        // โหลดข้อมูลร้านค้ากลับมา
        if (compound.contains("Offers", 10)) {
            this.myOffers = new MerchantOffers(compound.getCompound("Offers"));
        }
    }

    @Override
    public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
        InteractionResult superResult = super.mobInteract(sourceentity, hand);

        // เช็ค Server, เป็นผู้เล่น และ ต้องเป็นการคลิกด้วย "มือขวา (MAIN_HAND)" เท่านั้น
        if (!this.level().isClientSide() && sourceentity instanceof ServerPlayer _player && hand == InteractionHand.MAIN_HAND) {
            
            // ==========================================
            // LOGIC: แปลงร่างเป็น True Form
            // ==========================================
            int ambrosiaTraded = this.getPersistentData().getInt("AmbrosiaTraded");
            double transformChance = ambrosiaTraded * 0.001; // 1 ชิ้น = 0.1% โอกาส

            if (ambrosiaTraded > 0 && Math.random() < transformChance) {
                if (this.level() instanceof ServerLevel _level) {
                    BlockPos pos = this.blockPosition();

                    _level.sendParticles(ParticleTypes.SQUID_INK, this.getX(), this.getY() + 1, this.getZ(), 60, 0.8, 1.0, 0.8, 0.1);
                    _level.sendParticles(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1, this.getZ(), 40, 1.0, 1.5, 1.0, 0.05);
                    _level.sendParticles(ParticleTypes.WITCH, this.getX(), this.getY() + 1, this.getZ(), 80, 0.6, 1.5, 0.6, 0.2);
                    _level.sendParticles(ParticleTypes.PORTAL, this.getX(), this.getY() + 1, this.getZ(), 100, 1.0, 1.0, 1.0, 0.5);
                    _level.sendParticles(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY() + 1, this.getZ(), 30, 0.5, 0.5, 0.5, 0.05);

                    _level.playSound(null, pos, SoundEvents.END_PORTAL_SPAWN, SoundSource.HOSTILE, 0.8f, 0.6f);

                    SoundEvent customChant = BurntLandModSounds.COLLECTHURT.get();
                    if (customChant != null) {
                        _level.playSound(null, pos, customChant, SoundSource.HOSTILE, 1.0f, 0.5f); 
                        _level.playSound(null, pos, customChant, SoundSource.HOSTILE, 1.0f, 0.65f); 
                        _level.playSound(null, pos, customChant, SoundSource.HOSTILE, 1.0f, 0.8f);  
                        _level.playSound(null, pos, customChant, SoundSource.HOSTILE, 1.0f, 0.95f); 
                    }

                    Entity trueForm = BurntLandModEntities.TRUEFORMTHECOLLECTOR.get().spawn(_level, pos, MobSpawnType.MOB_SUMMONED);
                    if (trueForm != null && this.hasCustomName()) {
                        trueForm.setCustomName(this.getCustomName());
                    }

                    _player.closeContainer();
                    this.discard(); 
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }

            // ==========================================
            // LOGIC: ระบบร้านค้า
            // ==========================================
            this.setTradingPlayer(_player);

            if (this.myOffers == null) {
                this.myOffers = new MerchantOffers();

                this.myOffers.add(new MerchantOffer(new ItemStack(Items.GOLD_INGOT, 3), new ItemStack(BurntLandModItems.AMBROSIA.get(), 3), 16, 2, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(BurntLandModItems.AMBROSIA.get(), 2), 16, 2, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(BurntLandModItems.AMBROSIA.get(), 2), new ItemStack(Items.IRON_INGOT, 1), 12, 5, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(BurntLandModItems.AMBROSIA.get(), 4), new ItemStack(Items.COOKED_BEEF, 2), 12, 1, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(BurntLandModItems.AMBROSIA.get(), 2), new ItemStack(Items.GLOWSTONE_DUST, 8), 8, 5, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(BurntLandModItems.AMBROSIA.get(), 20), new ItemStack(Items.DIAMOND, 1), 5, 10, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(BurntLandModItems.AMBROSIA.get(), 6), new ItemStack(Items.EMERALD, 1), new ItemStack(Items.EXPERIENCE_BOTTLE, 1), 8, 10, 0.05f));
                this.myOffers.add(new MerchantOffer(new ItemStack(BurntLandModItems.AMBROSIA.get(), 32), new ItemStack(Items.DIAMOND, 5), new ItemStack(Items.TOTEM_OF_UNDYING, 1), 3, 20, 0.05f));

                if (cachedCommonItems == null) {
                    cachedCommonItems = new ArrayList<>();
                    for (Item item : ForgeRegistries.ITEMS) {
                        if (item.getRarity(new ItemStack(item)) == Rarity.COMMON && isValidItem(item)) {
                            cachedCommonItems.add(item);
                        }
                    }
                }

                List<Item> randomPool = new ArrayList<>(cachedCommonItems);
                Collections.shuffle(randomPool);
                for (int i = 0; i < 5 && i < randomPool.size(); i++) {
                    Item selectedItem = randomPool.get(i);
                    int count = 1 + (int) (Math.random() * 4);
                    this.myOffers.add(new MerchantOffer(
                        new ItemStack(BurntLandModItems.AMBROSIA.get(), 5), 
                        new ItemStack(selectedItem, count),                 
                        8, 2, 0.05f
                    ));
                }
            }

            // *** ส่วนที่เปลี่ยน: ใช้ this แทน merchant ที่ลบทิ้งไป ***
            OptionalInt containerId = _player.openMenu(new SimpleMenuProvider((id, inventory, player) -> {
                return new MerchantMenu(id, inventory, this);
            }, Component.literal("The Collector")));

            if (containerId.isPresent()) {
                _player.sendMerchantOffers(containerId.getAsInt(), this.myOffers, 1, 0, false, false);
            }
            
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }

        return superResult;
    }

    // ==========================================
    // IMPLEMENTS: Merchant (ระบบร้านค้าของเอนทิตี้)
    // เอาวางไว้ล่างสุดของคลาสได้เลยครับ
    // ==========================================
    @Override
    public void setTradingPlayer(@javax.annotation.Nullable Player player) {
        this.tradingPlayer = player;
    }

    @Override
    @javax.annotation.Nullable
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.myOffers == null) {
            this.myOffers = new MerchantOffers();
        }
        return this.myOffers;
    }

    @Override
    public void overrideOffers(MerchantOffers newOffers) {
        this.myOffers = newOffers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses(); 

        int ambrosiaPerTrade = 0;
        Item ambrosiaItem = BurntLandModItems.AMBROSIA.get();
        
        if (offer.getBaseCostA().getItem() == ambrosiaItem) ambrosiaPerTrade += offer.getBaseCostA().getCount();
        if (offer.getCostB().getItem() == ambrosiaItem) ambrosiaPerTrade += offer.getCostB().getCount();
        if (offer.getResult().getItem() == ambrosiaItem) ambrosiaPerTrade += offer.getResult().getCount();

        if (ambrosiaPerTrade > 0) {
            int currentTotal = this.getPersistentData().getInt("AmbrosiaTraded");
            this.getPersistentData().putInt("AmbrosiaTraded", currentTotal + ambrosiaPerTrade);
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {}

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int xp) {}

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.VILLAGER_YES;
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide();
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();

        if (!this.level().isClientSide) {
            if (this.entityData.get(TRADING)) {
                if (this.tradingPlayer == null || !this.tradingPlayer.isAlive() || 
                    this.tradingPlayer.distanceToSqr(this) > 16 || 
                    this.tradingPlayer.containerMenu == this.tradingPlayer.inventoryMenu) { 
                    
                    this.stopTrading(); 
                }
            }
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose p_33597_) {
        return super.getDimensions(p_33597_).scale((float) 1);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.updateSwingTime();
    }

    public static void init() {
        SpawnPlacements.register(BurntLandModEntities.COLLECTOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> {
                    net.minecraft.world.level.block.state.BlockState blockBelow = world.getBlockState(pos.below());
                    return (
                        blockBelow.is(BurntLandModBlocks.BURNTGRASS_BLOCKS.get()) || 
                        blockBelow.is(BurntLandModBlocks.BURNTDIRT_BLOCKS.get()) ||
                        blockBelow.is(BlockTags.ANIMALS_SPAWNABLE_ON)
                    ) && world.getRawBrightness(pos, 0) > 8; 
                });
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.27);
        builder = builder.add(Attributes.MAX_HEALTH, 50);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.2);
        return builder;
    }

    private PlayState movementPredicate(AnimationState event) {
        
        // --- Animation Priority 1: Trading ---
        if (this.entityData.get(TRADING)) {
             // thenPlayAndHold = เล่นจนจบแล้วค้างเฟรมสุดท้ายไว้
            return event.setAndContinue(RawAnimation.begin().thenPlayAndHold("trader"));
        }

        // --- Animation Priority 2: Procedures ---
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))
                    && !this.isSprinting()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("run"));
            }
            if (this.isSprinting()) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("run"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("sit"));
        }
        return PlayState.STOP;
    }

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

    String prevAnim = "empty";

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            this.remove(CollectorEntity.RemovalReason.KILLED);
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
        data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
        data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}