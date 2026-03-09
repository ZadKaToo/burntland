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

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.entity.MoverType;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import java.util.List;

import net.com.burntland.init.BurntLandModEntities;

import net.com.burntland.BurntLandConfig;

public class TrueformthecollectorEntity extends PathfinderMob implements GeoEntity {
    public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(TrueformthecollectorEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(TrueformthecollectorEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(TrueformthecollectorEntity.class, EntityDataSerializers.STRING);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);

    public String animationprocedure = "empty"; 
    private boolean hasSpawned = false;
    private int spawnTimer = 0;
    private int actionTimer = 0;
    // ใส่ไว้แถวๆ ตัวแปร swinging, lastloop ด้านบน
    private boolean hasSeenPlayer = false;
    
    // ตัวแปรใหม่สำหรับคุมจังหวะ Loop การโจมตี
    private int attackCycleTimer = 0; 

    public TrueformthecollectorEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(BurntLandModEntities.TRUEFORMTHECOLLECTOR.get(), world);

        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    public TrueformthecollectorEntity(EntityType<TrueformthecollectorEntity> type, Level world) {
        super(type, world);
        xpReward = 150;
        setNoAi(false);
        setMaxUpStep(1f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHOOT, false);
        this.entityData.define(ANIMATION, "empty");
        this.entityData.define(TEXTURE, "true_collectioner_back");
    }

    public void setTexture(String texture) {
        this.entityData.set(TEXTURE, texture);
    }

    public String getTexture() {
        return this.entityData.get(TEXTURE);
    }

    public String getSyncedAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(String animation) {
        this.entityData.set(ANIMATION, animation);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        
        // Attack Goal: เก็บไว้เพื่อให้มันหันหน้าหาผู้เล่นเวลาโจมตีระยะประชิด
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 9.0 + entity.getBbWidth();
            }
            // เราย้าย Logic การสั่งโจมตีไปคุมเองใน customServerAiStep เพื่อให้เป๊ะตาม Loop
        });
        
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, false, false));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }
    
    public boolean isBusy() {
        String anim = this.getSyncedAnimation();
        return (anim.equals("warp") || anim.equals("summon") || anim.equals("summonsoul")) 
                || !this.hasSpawned 
                || this.isDeadOrDying();
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("burntland:collectlive"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.wool.hit"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("burntland:collecthurt"));
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 50) { 
            this.remove(RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    // =========================================================
    //         HURT LOGIC (รวมร่าง: กันตาย + วาร์ปหนี)
    // =========================================================
    @Override
    public boolean hurt(DamageSource source, float amount) {
        // 1. เช็คกันตาย (ไฟ, ตก, จมน้ำ) -> ถ้าโดนพวกนี้ให้ Return false ทันที
        if (source.is(DamageTypes.IN_FIRE) || source.is(DamageTypes.FALL) || source.is(DamageTypes.DROWN)) {
            return false;
        }

        // 2. รับดาเมจจริง
        boolean success = super.hurt(source, amount);

        // 3. ระบบวาร์ปหนีเมื่อโดนตี (เฉพาะตอนสู้กันแล้ว)
        if (success && !this.level().isClientSide && source.getEntity() instanceof LivingEntity) {
            
            // ถ้าอยู่ใน Phase ต่อสู้ (หลัง 150 tick) โดนตีแล้วให้หนี
            if (this.attackCycleTimer > 150) {
                LivingEntity attacker = (LivingEntity) source.getEntity();
                
                // วาร์ปหนี
                teleportFar(attacker);
                
                // รีเซ็ตลูปกลับไปเริ่ม Phase 1 ใหม่
                this.attackCycleTimer = 0;
                
                // เล่นเสียงวาร์ป
                this.level().playSound(null, this.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 1.0f, 1.0f);
            }
        }
        return success;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Texture", this.getTexture());
        compound.putBoolean("HasSpawned", this.hasSpawned);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Texture")) this.setTexture(compound.getString("Texture"));
        if (compound.contains("HasSpawned")) this.hasSpawned = compound.getBoolean("HasSpawned");
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    // =========================================================
    //         AI CYCLE: SUMMON -> JUMPSCARE -> CHASE -> (HURT->RETREAT)
    // =========================================================
    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());

        if (this.isDeadOrDying()) return;

        // --- 1. Spawn Logic (เหมือนเดิม) ---
        if (!this.hasSpawned) {
            spawnTimer++;
            if (spawnTimer == 1) {
                this.animationprocedure = "summon"; 
                this.setAnimation("summon"); 
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.VEX_CHARGE, SoundSource.HOSTILE, 1.0f, 0.5f);
            }
            if (this.level() instanceof ServerLevel _level) {
                _level.sendParticles(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 2, 0.3, 0.5, 0.3, 0.05);
            }
            if (spawnTimer >= 50) { 
                this.hasSpawned = true;
                this.animationprocedure = "empty";
                this.setAnimation("empty"); 
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), 
                    ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.evoker.prepare_summon")), 
                    SoundSource.HOSTILE, 1.5f, 0.5f); // Pitch 0.5 ให้เสียงต่ำลง ดูเป็นบอสตัวใหญ่

                // 2. เสียงกรีดร้อง (Eldritch/Stress Effect) ***ไฮไลท์สำคัญ***
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), 
                    ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.sculk_shrieker.shriek")), 
                    SoundSource.HOSTILE, 1.2f, 0.8f); // Pitch 0.8 ให้เสียงโหยหวนขึ้นนิดนึง

                // 3. เสียงกระดูกกระทบกัน (สื่อถึง Collection หัวกะโหลก)
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), 
                    ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.wither_skeleton.death")), 
                    SoundSource.HOSTILE, 1.0f, 0.8f);
            }
            return; 
        }

        LivingEntity target = this.getTarget();
        
        // ถ้าไม่มีเป้าหมาย ให้เดินเล่นปกติ
        if (target == null) {
            attackCycleTimer = 0;
            return;
        }

        // เพิ่มตัวนับเวลา (1 วินาที = 20 ticks)
        attackCycleTimer++;
        
        // --- PHASE 1: รักษาดูระยะห่าง & เรียกลูกน้อง (0 - 150 ticks / 0-7.5 วินาที) ---
        if (attackCycleTimer < 150) {
            // หยุดเดินเข้าหาผู้เล่น (ยืนร่ายเวทย์เฉยๆ)
            this.getNavigation().stop(); 
            this.getLookControl().setLookAt(target, 30.0F, 30.0F); // หันหน้ามอง

            // ถ้าผู้เล่นเข้าใกล้เกิน 8 บล็อก ให้วาร์ปหนี (Kiting)
            if (this.distanceToSqr(target) < 64 && attackCycleTimer % 20 == 0) {
                 teleportFar(target);
            }

            // ที่วินาทีที่ 3 (Tick 60): เรียกลูกสมุน
            if (attackCycleTimer == 60) {
                 this.animationprocedure = "summonsoul";
                 this.setAnimation("summonsoul");
                 this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.HOSTILE, 1.0f, 0.7f);
                 summonMinions();
            }
        }
        
        // --- PHASE 2: JUMPSCARE WARP (Tick 150 / 7.5 วินาที) ---
        else if (attackCycleTimer == 150) {
            // วาร์ปไปหาผู้เล่น (ข้างหลัง)
            teleportToPlayer(target);
            
            // เริ่มท่าโจมตีทันที
            this.animationprocedure = "attack";
            this.setAnimation("attack");
            this.actionTimer = 20; 
            
            // แก้ไขเสียง: เรียกผ่าน Registry โดยตรง
            this.level().playSound(null, this.blockPosition(), 
                ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.warden.agitated")), 
                SoundSource.HOSTILE, 1.5f, 0.8f);
                
            this.level().playSound(null, this.blockPosition(), 
                ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.wither.break_block")), 
                SoundSource.HOSTILE, 1.0f, 0.5f);
        }

        // --- PHASE 3: ATTACK EXECUTION (Action Timer ทำงาน) ---
        if (this.actionTimer > 0) {
            this.actionTimer--;
            
            // จังหวะดาเมจ (Swing)
            if (this.animationprocedure.equals("attack") && this.actionTimer == 10) {
                // AoE Attack Logic
                AABB box = this.getBoundingBox().inflate(5.0D, 2.0D, 5.0D);
                List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, box);
                
                this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0f, 0.5f);
                this.swing(InteractionHand.MAIN_HAND);
                
                if (this.level() instanceof ServerLevel _level) {
                     _level.sendParticles(ParticleTypes.SQUID_INK, this.getX(), this.getY() + 1, this.getZ() + 1, 20, 0.5, 0.5, 0.5, 0.1);
                     _level.sendParticles(ParticleTypes.SONIC_BOOM, this.getX(), this.getY() + 1.5, this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                }

                for (LivingEntity entity : entities) {
                    if (entity != this && !(entity instanceof Monster)) { 
                        this.doHurtTarget(entity);
                        entity.knockback(1.5, this.getX() - entity.getX(), this.getZ() - entity.getZ());
                    }
                }
            }

            // --- PHASE 4: END ACTION -> CHASE (จบ Action แล้วไล่ต่อ) ---
            if (this.actionTimer == 0) {
                if (this.animationprocedure.equals("attack")) {
                    // *** แก้ไข: ไม่หนีแล้ว แต่เดินไล่ตาม (รอโดนตีสวน) ***
                    this.getNavigation().moveTo(target, 1.2D);
                }
                
                this.animationprocedure = "empty";
                this.setAnimation("empty"); 
            }
        }
        
        // --- PHASE 5: FIGHT (ช่วงรอโดนตี) ---
        // ถ้าเวลาเกิน 170 (ตีเสร็จแล้ว) ให้พยายามเดินเข้าหาผู้เล่นเรื่อยๆ
        if (attackCycleTimer > 170) {
             this.getNavigation().moveTo(target, 1.2D);
        }
    }
    
    // ฟังก์ชั่นวาร์ปไปหาผู้เล่น (Jumpscare)
    private void teleportToPlayer(LivingEntity target) {
        if (this.level() instanceof ServerLevel _level) {
             _level.sendParticles(ParticleTypes.POOF, this.getX(), this.getY() + 1, this.getZ(), 10, 0.5, 1, 0.5, 0.1);
        }
        
        // คำนวณจุดข้างหลังผู้เล่น
        double dist = 2.0; 
        Vec3 view = target.getViewVector(1.0F);
        double tx = target.getX() - (view.x * dist);
        double tz = target.getZ() - (view.z * dist);
        
        this.teleportTo(tx, target.getY(), tz);
        
        // เปลี่ยนเป็นเสียง Beacon Activate จะดู "แพง" และเหมือนเสียงมิติบิดเบี้ยวมากกว่า Portal ธรรมดา
        this.level().playSound(null, this.blockPosition(), 
            ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.beacon.activate")), 
            SoundSource.HOSTILE, 0.5f, 1.5f); // Pitch 1.5 ให้เสียงแหลมสูงเหมือนเสียงวิ้งๆ ในหัว
    }

    // ฟังก์ชั่นวาร์ปหนี (Retreat)
    private void teleportFar(LivingEntity target) {
        if (this.level() instanceof ServerLevel _level) {
             _level.sendParticles(ParticleTypes.SQUID_INK, this.getX(), this.getY() + 1, this.getZ(), 10, 0.5, 1, 0.5, 0.1);
        }
        
        // วาร์ปหนีไปในระยะ 15-20 บล็อก
        double r = 15.0 + this.random.nextDouble() * 10.0;
        double angle = this.random.nextDouble() * 2 * Math.PI;
        double tx = target.getX() + r * Math.cos(angle);
        double tz = target.getZ() + r * Math.sin(angle);
        
        this.randomTeleport(tx, target.getY(), tz, true);
        
        // แก้ไขเสียง: Respawn Anchor Deplete
        this.level().playSound(null, this.blockPosition(), 
            ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("block.respawn_anchor.deplete")), 
            SoundSource.HOSTILE, 1.0f, 0.5f);
    }

    // เก็บรายชื่อมอนสเตอร์ที่ "ผ่านการตรวจสอบขั้นต้นแล้ว"
    // ประกาศตัวแปร List สำหรับเก็บรายชื่อมอนสเตอร์ที่สแกนเจอแล้ว
    private static java.util.List<EntityType<?>> cachedMonsterList = new java.util.ArrayList<>();

    @SuppressWarnings("unchecked")
    private void summonMinions() {
        if (!this.level().isClientSide) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            
            // เล่นเสียงตอนเสก
            this.level().playSound(null, this.blockPosition(),
                    SoundEvents.SCULK_SHRIEKER_SHRIEK,
                SoundSource.HOSTILE, 1.0f, 0.5f);

            // --- 1. สแกนครั้งแรก (ถ้า List ยังว่างอยู่) ---
            if (cachedMonsterList.isEmpty()) {
                net.minecraft.resources.ResourceLocation myId = net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getKey(this.getType());

                // ดึงรายชื่อตัวที่แบนจาก Config File
                // แก้ตรงดึง List
                java.util.List<String> bannedList = BurntLandConfig.DATA.banned_minions;

                try {
                    for (EntityType<?> type : net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES) {
                        // กรองเบื้องต้น: ต้องเป็น Monster และเสกได้
                        if (type == null || type.getCategory() != net.minecraft.world.entity.MobCategory.MONSTER || !type.canSummon()) continue;

                        net.minecraft.resources.ResourceLocation typeId = net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getKey(type);
                        
                        // กรอง: ไม่ใช่ตัวมันเอง
                        if (typeId != null && myId != null && typeId.equals(myId)) continue;

                        String idName = (typeId != null) ? typeId.toString().toLowerCase() : "";     // เช่น minecraft:wither
                        String pathName = (typeId != null) ? typeId.getPath().toLowerCase() : "";    // เช่น wither

                        // *** เช็คจาก Config Blacklist ***
                        if (bannedList.contains(idName) || bannedList.contains(pathName)) continue;

                        // กรองคำทั่วไป (Hardcode กันเหนียวไว้นิดหน่อย)
                        if (pathName.contains("wither") || pathName.contains("ender_dragon") || pathName.contains("warden")) continue;

                        // สแกนเลือดเบื้องต้น (จาก Attributes)
                        try {
                            if (net.minecraft.world.entity.LivingEntity.class.isAssignableFrom(type.getBaseClass())) {
                                EntityType<? extends net.minecraft.world.entity.LivingEntity> livingType = (EntityType<? extends net.minecraft.world.entity.LivingEntity>) type;
                                if (net.minecraft.world.entity.ai.attributes.DefaultAttributes.hasSupplier(livingType)) {
                                    double maxHealth = net.minecraft.world.entity.ai.attributes.DefaultAttributes.getSupplier(livingType)
                                            .getValue(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH);
                                    
                                    if (maxHealth > 100.0) continue; // ถ้าเลือดเยอะเกิน ตัดออก
                                } else {
                                    continue; 
                                }
                            }
                            // ผ่านทุกด่าน! เพิ่มเข้า List
                            cachedMonsterList.add(type);
                        } catch (Exception e) {
                            continue;
                        }
                    }
                } catch (Exception e) {
                    // Ignore error
                }

                // Backup: ถ้าสแกนไม่เจออะไรเลย ให้ใส่ตัวพื้นฐาน
                if (cachedMonsterList.isEmpty()) {
                    cachedMonsterList.add(EntityType.ZOMBIE);
                    cachedMonsterList.add(EntityType.SKELETON);
                    cachedMonsterList.add(EntityType.CREEPER);
                }
            }

            // --- 2. สุ่มเสก พร้อมระบบ "ตรวจจับของปลอม" (Double Check) ---
            if (!cachedMonsterList.isEmpty()) {
                int count = 1 + this.random.nextInt(3); // เสก 1-3 ตัว

                for (int i = 0; i < count; i++) {
                    // ให้โอกาสลองสุ่มใหม่ 5 ครั้ง ถ้าเจอตัวเลือดเยอะเกิน
                    for (int attempt = 0; attempt < 5; attempt++) {
                        if (cachedMonsterList.isEmpty()) break;

                        int randomIndex = this.random.nextInt(cachedMonsterList.size());
                        EntityType<?> randomType = cachedMonsterList.get(randomIndex);
                        
                        try {
                            Entity entity = randomType.create(serverLevel);
                            
                            // *** จุดสำคัญที่สุด: เช็คเลือดจาก "ตัวจริง" ที่เพิ่งสร้าง ***
                            if (entity instanceof net.minecraft.world.entity.LivingEntity living) {
                                // ถ้าเลือดจริงเกิน 100 (เช่น Mod บางตัวตั้งเลือดใน Constructor)
                                if (living.getMaxHealth() > 100.0f) {
                                    cachedMonsterList.remove(randomIndex); // ลบออกจาก List ถาวร
                                    continue; // ข้ามไป loop attempt ถัดไป
                                }
                            }

                            if (entity instanceof Mob mob) {
                                double sx = this.getX() + (Math.random() - 0.5) * 5;
                                double sz = this.getZ() + (Math.random() - 0.5) * 5;
                                int groundY = serverLevel.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int)sx, (int)sz);
                                
                                mob.moveTo(sx, groundY, sz, 0, 0);
                                serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, sx, groundY + 1, sz, 10, 0.2, 0.5, 0.2, 0.1);
                                
                                if (this.getTarget() != null) mob.setTarget(this.getTarget());
                                
                                serverLevel.addFreshEntity(mob);
                                break; // เสกสำเร็จแล้ว หยุด Loop attempt ตัวนี้
                            } else {
                                // ถ้าไม่ใช่ Mob (เกิดผิดพลาด) ให้ลบออก
                                cachedMonsterList.remove(randomIndex);
                            }
                        } catch (Exception e) {
                            // ถ้า Error ให้ลบตัวปัญหานั้นทิ้ง
                            if (cachedMonsterList.size() > randomIndex) {
                                cachedMonsterList.remove(randomIndex);
                            }
                        }
                    }
                }
            }
        }
    }

    // *** ฟังก์ชั่นเช็ค Boss Bar (แก้ Error cannot find symbol) ***
    private boolean hasBossBar(Class<?> clazz) {
        // เช็คชื่อ Class ตรงๆ 
        String className = clazz.getSimpleName().toLowerCase();
        if (className.contains("boss") || className.contains("dragon") || className.contains("wither")) return true;

        // เช็ค Package 
        if (clazz.getPackage() != null && clazz.getPackage().getName().contains(".boss")) return true;

        // วนลูปเช็คตัวแปร (Reflection)
        try {
            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                if (field.getType().getSimpleName().contains("BossEvent")) {
                    return true; 
                }
            }
        } catch (Exception e) {
            // ignore
        }
        
        // เช็คคลาสแม่
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class && clazz.getSuperclass() != net.minecraft.world.entity.Mob.class) {
             return hasBossBar(clazz.getSuperclass());
        }

        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3); 
        builder = builder.add(Attributes.MAX_HEALTH, 600);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 18);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.5); 
        return builder;
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource source) {
        super.die(source);

        if (!this.level().isClientSide) {
            
            // --- ส่วนที่ 1: Rare Collection (ของสะสมหายาก) ---
            // สแกนหาของ Rare จากทั้งเกม แต่ไม่เอาแผ่นเพลง
            java.util.List<net.minecraft.world.item.ItemStack> rareCollection = new java.util.ArrayList<>();
            
            for (net.minecraft.world.item.Item item : net.minecraftforge.registries.ForgeRegistries.ITEMS) {
                // ข้ามอากาศ
                if (item == net.minecraft.world.item.Items.AIR) continue;
                
                // ข้ามแผ่นเพลง (Music Discs) ทุกชนิด
                if (item instanceof net.minecraft.world.item.RecordItem) continue;

                net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(item);

                try {
                    // เอาเฉพาะของระดับ RARE (สีฟ้า) หรือ EPIC (สีม่วง)
                    net.minecraft.world.item.Rarity rarity = item.getRarity(stack);
                    if (rarity == net.minecraft.world.item.Rarity.RARE) {
                        rareCollection.add(stack);
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            // สุ่มดรอปของ Rare 3 ชิ้น (เหมือนสะสมหัว/ของแปลก)
            if (!rareCollection.isEmpty()) {
                for (int i = 0; i < 3; i++) {
                    net.minecraft.world.item.ItemStack randomRare = rareCollection.get(this.random.nextInt(rareCollection.size()));
                    this.spawnAtLocation(randomRare.copy());
                }
            } else {
                // ถ้าหาไม่เจอจริงๆ ให้ดรอปหัว Creeper แทน (ของสะสม)
                this.spawnAtLocation(new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.CREEPER_HEAD));
            }

            // --- ส่วนที่ 2: Puzzling Trapezohedron (แร่มูลค่าสูง) ---
            // การันตีการดรอปแร่มีค่า (แทน Puzzling Trapezohedron / Jute Tapestry)
            
            // รายชื่อแร่มีค่า
            net.minecraft.world.item.Item[] valuables = {
                net.minecraft.world.item.Items.DIAMOND,         // แทน Blue Gem
                net.minecraft.world.item.Items.EMERALD,         // แทน Blue Gem
                net.minecraft.world.item.Items.GOLD_INGOT,      // แทน Gold Bar
                net.minecraft.world.item.Items.NETHERITE_INGOT, // แทน Puzzling Trapezohedron (แพงสุด)
                net.minecraft.world.item.Items.NETHERITE_SCRAP
            };

            // สุ่มเลือกมา 1 ชนิด
            net.minecraft.world.item.Item chosenGem = valuables[this.random.nextInt(valuables.length)];
            
            // สุ่มจำนวน (2 ถึง 5 ก้อน)
            int count = 2 + this.random.nextInt(4);
            
            // ถ้าดวงดีได้ Netherite Ingot ให้ลดจำนวนลงหน่อย (เดี๋ยวรวยเกิน)
            if (chosenGem == net.minecraft.world.item.Items.NETHERITE_INGOT) {
                count = 1 + this.random.nextInt(2); // 1-2 ก้อน
            }

            this.spawnAtLocation(new net.minecraft.world.item.ItemStack(chosenGem, count));
        }
    }

    @Override
    public boolean canDrownInFluidType(net.minecraftforge.fluids.FluidType type) {
        // บอสเทพขนาดนี้ ไม่ควรจมน้ำตาย
        if (type == net.minecraftforge.common.ForgeMod.WATER_TYPE.get()) return false;
        return super.canDrownInFluidType(type);
    }

    @Override
    public void travel(Vec3 vec3) {
        // เช็คว่าอยู่ในน้ำไหม และไม่ได้กำลังบิน (ถ้าบอสบินได้)
        if (this.isInWater() && this.isEffectiveAi()) {
            this.moveRelative(0.1F, vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            
            // ลดความเร็วเล็กน้อย (แรงเสียดทานผิวน้ำ)
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            
            // *** ไฮไลท์: ถ้าตัวจมลงไป ให้เด้งขึ้นมาอยู่ที่ผิวน้ำ ***
            if (this.getDeltaMovement().y < 0.0D) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.5D, 1.0D)); // ลดแรงตก
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.01D, 0.0D));   // ดันขึ้นเบาๆ
            }
            
            // ถ้าอยากให้เดินเร็วบนน้ำเหมือนบนบกเป๊ะๆ ให้ลบบรรทัด scale(0.9D) ออก
        } else {
            super.travel(vec3);
        }
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new GroundPathNavigation(this, world) {
            @Override
            public boolean isStableDestination(BlockPos pos) {
                // แก้ตรงนี้: ใช้ world แทน this.level เพื่อป้องกัน error
                return world.getFluidState(pos).is(FluidTags.WATER) || super.isStableDestination(pos);
            }
        };
    }

    public static void init() {}

    private PlayState movementPredicate(AnimationState event) {
        String playingAnim = this.getSyncedAnimation();
        
        if (!playingAnim.equals("empty") && !playingAnim.equals("undefined")) {
            return PlayState.STOP; 
        }
        
        if (this.isDeadOrDying()) {
             return event.setAndContinue(RawAnimation.begin().thenPlay("die"));
        }
        
        if (event.isMoving()) {
             return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
        }
        
        return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
    }

    String prevAnim = "empty";

    private PlayState procedurePredicate(AnimationState event) {
        String playingAnim = this.getSyncedAnimation();

        if (!playingAnim.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!playingAnim.equals(prevAnim) && !playingAnim.equals("empty"))) {
            if (!playingAnim.equals(prevAnim))
                event.getController().forceAnimationReset();
                
            event.getController().setAnimation(RawAnimation.begin().thenPlay(playingAnim));
            prevAnim = playingAnim;
            return PlayState.CONTINUE;
        } 
        else if (playingAnim.equals("empty")) {
            prevAnim = "empty";
            return PlayState.STOP;
        }
        
        return PlayState.CONTINUE;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        // --- ส่วนระบบ Advancement: เมื่อบอสเกิดและเจอผู้เล่นในระยะ 20 บล็อก ---
        if (!this.level().isClientSide && !this.hasSeenPlayer && this.tickCount % 20 == 0) { // เช็คทุกๆ 1 วินาที
            // หาผู้เล่นในระยะ 20 บล็อก
            List<ServerPlayer> players = this.level().getEntitiesOfClass(ServerPlayer.class, this.getBoundingBox().inflate(20.0D));

            for (ServerPlayer player : players) {
                // ดึง Advancement มา
                Advancement advancement = player.server.getAdvancements().getAdvancement(ResourceLocation.parse("burntland:facethegreed")); // เช็คชื่อไฟล์ดีๆ นะครับ

                if (advancement != null) {
                    AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
                    if (!progress.isDone()) {
                        // แจก Advancement ให้ผู้เล่นทุกคนรอบๆ
                        for (String criterion : progress.getRemainingCriteria()) {
                            player.getAdvancements().award(advancement, criterion);
                        }
                    }
                }
            }

            if (!players.isEmpty()) {
                this.hasSeenPlayer = true; // ทำงานครั้งเดียวพอ
            }
        }

        this.updateSwingTime();
        // ... (โค้ดเดิมของคุณ)
    }
    
    @Override
    public void swing(InteractionHand hand, boolean update) {
         super.swing(hand, update);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
        data.add(new AnimationController<>(this, "actions", 0, this::procedurePredicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}