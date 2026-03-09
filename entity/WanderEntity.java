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

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.MerchantMenu;
import java.util.OptionalInt;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
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
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import net.com.burntland.init.BurntLandModBlocks;
import net.com.burntland.init.BurntLandModItems;
import net.com.burntland.init.BurntLandModEntities;

import javax.annotation.Nullable;
import java.util.Random;

// 1. เพิ่ม implements Merchant
public class WanderEntity extends PathfinderMob implements GeoEntity, Merchant {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(WanderEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(WanderEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(WanderEntity.class, EntityDataSerializers.STRING);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean swinging;
    private boolean lastloop;
    private long lastSwing;
    public String animationprocedure = "empty";

    // 2. ตัวแปรสำหรับการแลกเปลี่ยน
    @Nullable
    private Player tradingPlayer;
    @Nullable
    private MerchantOffers offers;

    public WanderEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(BurntLandModEntities.WANDER.get(), world);
    }

    public WanderEntity(EntityType<WanderEntity> type, Level world) {
        super(type, world);
        xpReward = 5;
        setNoAi(false);
        setMaxUpStep(0.6f);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "undefined");
        this.entityData.define(TEXTURE, "wander_te");
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
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, CollectorEntity.class, (float) 10, 1, 1.2));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, TrueformthecollectorEntity.class, (float) 10, 1, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, ForsinEntity.class, (float) 10, 1, 1.2));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, ExecutessplusEntity.class, (float) 10, 1, 1.2));
		this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Monster.class, (float) 10, 1, 1.2));
        this.targetSelector.addGoal(6, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 4;
            }
        });
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new FloatGoal(this));
    }

	// ==========================================
    // MISSING MERCHANT METHOD (FIX)
    // ==========================================
    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }

	// แก้ไขเมธอด updateTrades ให้สุ่มของเหมือน Vanilla
    private void updateTrades() {
        MerchantOffers merchantoffers = new MerchantOffers();
        Random random = new Random();

        // ==========================================
        // 1. สร้างบ่อรวมไอเทมระดับ "ทั่วไป" (Common Pool)
        // ==========================================
        List<ItemStack> commonPool = new ArrayList<>();
        
        // --- ดอกไม้ & พืช ---
        commonPool.add(new ItemStack(Items.ALLIUM));
        commonPool.add(new ItemStack(Items.AZURE_BLUET));
        commonPool.add(new ItemStack(Items.BLUE_ORCHID));
        commonPool.add(new ItemStack(Items.CORNFLOWER));
        commonPool.add(new ItemStack(Items.DANDELION));
        commonPool.add(new ItemStack(Items.LILY_OF_THE_VALLEY));
        commonPool.add(new ItemStack(Items.ORANGE_TULIP));
        commonPool.add(new ItemStack(Items.PINK_TULIP));
        commonPool.add(new ItemStack(Items.POPPY));
        commonPool.add(new ItemStack(Items.RED_TULIP));
        commonPool.add(new ItemStack(Items.WHITE_TULIP));
        commonPool.add(new ItemStack(Items.VINE));
        commonPool.add(new ItemStack(Items.BROWN_MUSHROOM));
        commonPool.add(new ItemStack(Items.RED_MUSHROOM));
        commonPool.add(new ItemStack(Items.LILY_PAD));
        commonPool.add(new ItemStack(Items.SMALL_DRIPLEAF));
        commonPool.add(new ItemStack(Items.MOSS_BLOCK));
        
        // --- ต้นอ่อน (Saplings) ---
        commonPool.add(new ItemStack(Items.ACACIA_SAPLING));
        commonPool.add(new ItemStack(Items.BIRCH_SAPLING));
        commonPool.add(new ItemStack(Items.CHERRY_SAPLING));
        commonPool.add(new ItemStack(Items.DARK_OAK_SAPLING));
        commonPool.add(new ItemStack(Items.JUNGLE_SAPLING));
        commonPool.add(new ItemStack(Items.OAK_SAPLING));
        commonPool.add(new ItemStack(Items.SPRUCE_SAPLING));
        commonPool.add(new ItemStack(Items.MANGROVE_PROPAGULE));

        // --- สีย้อม (Dyes) ---
        commonPool.add(new ItemStack(Items.ORANGE_DYE, 3)); // ขายทีละ 3 อัน
        commonPool.add(new ItemStack(Items.WHITE_DYE, 3));
        commonPool.add(new ItemStack(Items.YELLOW_DYE, 3));
        commonPool.add(new ItemStack(Items.GREEN_DYE, 3));
        commonPool.add(new ItemStack(Items.RED_DYE, 3));
        commonPool.add(new ItemStack(Items.BLUE_DYE, 3));
        
        // --- ของทั่วไปอื่นๆ ---
        commonPool.add(new ItemStack(Items.SAND, 8));
        commonPool.add(new ItemStack(Items.RED_SAND, 8));
        commonPool.add(new ItemStack(Items.PUMPKIN_SEEDS));
        commonPool.add(new ItemStack(Items.MELON_SEEDS));
        commonPool.add(new ItemStack(Items.BEETROOT_SEEDS));
        commonPool.add(new ItemStack(Items.WHEAT_SEEDS));
        commonPool.add(new ItemStack(Items.GLOW_BERRIES));
        commonPool.add(new ItemStack(Items.FERN));
        commonPool.add(new ItemStack(Items.SUGAR_CANE));
        commonPool.add(new ItemStack(Items.KELP));

        // ==========================================
        // 2. สร้างบ่อรวมไอเทมระดับ "หายาก" (Rare Pool)
        // ==========================================
        List<ItemStack> rarePool = new ArrayList<>();
        
        rarePool.add(new ItemStack(Items.SEA_PICKLE));
        rarePool.add(new ItemStack(Items.SLIME_BALL));
        rarePool.add(new ItemStack(Items.GLOWSTONE));
        rarePool.add(new ItemStack(Items.NAUTILUS_SHELL));
        rarePool.add(new ItemStack(Items.PUFFERFISH_BUCKET));
        rarePool.add(new ItemStack(Items.TROPICAL_FISH_BUCKET));
        rarePool.add(new ItemStack(Items.BLUE_ICE));
        rarePool.add(new ItemStack(Items.PACKED_ICE));
        rarePool.add(new ItemStack(Items.PODZOL));
        rarePool.add(new ItemStack(Items.MYCELIUM));
        rarePool.add(new ItemStack(Items.GUNPOWDER));

        // ==========================================
        // 3. ขั้นตอนการสุ่ม (Shuffling & Selection)
        // ==========================================
        
        // สลับตำแหน่งของในลิสต์ให้มั่ว (เหมือนสับไพ่)
        Collections.shuffle(commonPool);
        Collections.shuffle(rarePool);

        // --- เลือก Common Items มาขาย 5 อย่าง ---
        // ราคา: 1 Ambrosia
        int commonTradeCount = 5;
        for (int i = 0; i < commonTradeCount && i < commonPool.size(); i++) {
            ItemStack itemToSell = commonPool.get(i);
            
            merchantoffers.add(new MerchantOffer(
                new ItemStack(BurntLandModItems.AMBROSIA.get(), 1), // จ่าย 1 Ambrosia
                itemToSell,                                         // ได้ของ
                8,                                                  // แลกได้ 8 ครั้ง
                2,                                                  // ได้ XP 2
                0.05f
            ));
        }

        // --- เลือก Rare Item มาขาย 1 อย่าง ---
        // ราคา: สุ่มระหว่าง 3 ถึง 5 Ambrosia
        if (!rarePool.isEmpty()) {
            ItemStack rareItem = rarePool.get(0); // หยิบตัวบนสุดหลังสับไพ่
            
            // สุ่มราคา (3 + 0 ถึง 2) -> 3, 4, หรือ 5
            int price = 3 + random.nextInt(3); 

            merchantoffers.add(new MerchantOffer(
                new ItemStack(BurntLandModItems.AMBROSIA.get(), price), 
                rareItem, 
                4,  // ของแรร์แลกได้แค่ 4 ครั้ง
                10, // ได้ XP เยอะหน่อย
                0.05f
            ));
        }

        this.offers = merchantoffers;
    }

    // ==========================================
    // MERCHANT IMPLEMENTATION METHODS
    // ==========================================
    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.tradingPlayer = player;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
        }
        return this.offers;
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers offers) {
        this.offers = offers;
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        
        // เล่นเสียงเมื่อมีการแลกเปลี่ยน (เลือกเสียง Villager yes หรือ custom ก็ได้)
        if (!this.level().isClientSide) {
             this.playSound(SoundEvents.VILLAGER_YES, this.getSoundVolume(), this.getVoicePitch());
        }
    }

	// ==========================================
    // FIX: MANUALLY OPEN TRADING GUI
    // แก้ไข: สร้างหน้าต่างแลกของด้วยตัวเอง
    // ==========================================
    @Override
    public void openTradingScreen(Player player, net.minecraft.network.chat.Component name, int level) {
        // 1. สั่งให้ผู้เล่นเปิดหน้าต่าง Menu แบบ Merchant
        OptionalInt containerId = player.openMenu(new SimpleMenuProvider((id, inventory, p) -> {
            return new MerchantMenu(id, inventory, this); // 'this' คือตัว WanderEntity ที่เป็น Merchant
        }, name));

        // 2. ถ้าเปิดหน้าต่างสำเร็จ -> ส่งข้อมูลรายการสินค้า (Offers) ไปให้ผู้เล่น
        if (containerId.isPresent()) {
            MerchantOffers offers = this.getOffers();
            if (!offers.isEmpty()) {
                // ส่ง Packet ข้อมูลการขาย: (ContainerId, รายการของ, เลเวลร้าน, XP, โชว์หลอดเวลไหม, เติมของได้ไหม)
                player.sendMerchantOffers(
                    containerId.getAsInt(), 
                    offers, 
                    level, 
                    this.getVillagerXp(), 
                    this.showProgressBar(), 
                    false // false = ไม่มีการ Restock ของ (ขายหมดแล้วหมดเลยเหมือน Wandering Trader)
                );
            }
        }
    }

	@Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (this.isAlive() && !this.trading() && !this.isBaby()) {
            if (hand == InteractionHand.MAIN_HAND) {
                // เช็คว่าทำที่ฝั่ง Server เท่านั้น (สำคัญมาก! ถ้าทำฝั่ง Client จะเปิดไม่ได้)
                if (!this.level().isClientSide) {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                }
                // ต้อง return sidedSuccess เพื่อบอกเกมว่า "ฉันรับรู้นะว่ามีการคลิก"
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void notifyTradeUpdated(ItemStack stack) {
        // ไม่ต้องทำอะไรสำหรับมอนสเตอร์ทั่วไป
    }

    @Override
    public int getVillagerXp() {
        return 0; // มอนสเตอร์ตัวนี้ไม่มีเลเวลการค้าขาย
    }

    @Override
    public void overrideXp(int xp) {}

    @Override
    public boolean showProgressBar() {
        return false; // ไม่โชว์แถบเลเวลพ่อค้า
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.VILLAGER_YES;
    }
    
    public boolean trading() {
        return this.tradingPlayer != null;
    }

    // ==========================================
    // STANDARD METHODS (เดิม)
    // ==========================================

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(BurntLandModItems.AMBROSIA.get()));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.evoker.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.pillager.death"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_FIRE))
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
        // เซฟข้อมูลการค้าขายด้วย ถ้าต้องการให้ของเหมือนเดิมเมื่อโหลดแมพใหม่
        if (this.offers != null) {
            compound.put("Offers", this.offers.createTag());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture"))
            this.setTexture(compound.getString("Texture"));
        if (compound.contains("Offers")) {
            this.offers = new MerchantOffers(compound.getCompound("Offers"));
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
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
        SpawnPlacements.register(BurntLandModEntities.WANDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
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
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 30);
        builder = builder.add(Attributes.ARMOR, 2);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        return builder;
    }

    private PlayState movementPredicate(AnimationState event) {
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

            ) {
                return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }
            if (this.isDeadOrDying()) {
                return event.setAndContinue(RawAnimation.begin().thenPlay("die"));
            }
            return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }
        return PlayState.STOP;
    }

    private PlayState attackingPredicate(AnimationState event) {
        double d1 = this.getX() - this.xOld;
        double d0 = this.getZ() - this.zOld;
        float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 9L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("attack"));
        }
        return PlayState.CONTINUE;
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
            this.remove(WanderEntity.RemovalReason.KILLED);
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
}