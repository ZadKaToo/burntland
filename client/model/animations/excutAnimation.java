package net.com.burntland.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 5.0.3 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class excutAnimation {
	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-90.0F, -50.0F, 90.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-19.0F, 2.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(11.0F, 7.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(11.0F, 7.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightleg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.9317F, 2.682F, 0.9056F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(-0.2672F, 1.3942F, -27.0559F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.4658F, 1.341F, 2.9528F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.75F, KeyframeAnimations.posVec(-14.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.0F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.posVec(6.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-32.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.posVec(21.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -17.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(14.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(-131.8027F, 1.207F, 142.44F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(-0.5F, 0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(1.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.31F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition stand = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-90.0F, -45.0F, -90.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-4.0F, 91.0F, -36.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.posVec(-4.0F, 92.0F, -36.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.75F, KeyframeAnimations.posVec(-4.0F, 90.0F, -36.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(18.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 32.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-19.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -40.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(29.0F, 13.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.posVec(1.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.75F, KeyframeAnimations.posVec(1.0F, -0.83F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightleg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.0F, KeyframeAnimations.posVec(1.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition attack1 = AnimationDefinition.Builder.withLength(2.0F)
			.addAnimation("rightleg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833F, KeyframeAnimations.degreeVec(5.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(3.917F, -15.2288F, 3.702F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(3.8669F, 12.2102F, 5.5514F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(4.0144F, 19.6925F, 6.0871F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(8.4821F, 47.0496F, 9.4417F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(13.6703F, 46.0874F, 16.5815F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(8.2823F, 44.8912F, 16.0251F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(12.8943F, 43.695F, 15.4687F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 0.0F, -6.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.posVec(-2.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.posVec(-2.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0833F, KeyframeAnimations.posVec(3.0F, 0.0F, -6.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.posVec(7.0F, 0.0F, -3.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4167F, KeyframeAnimations.posVec(5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.5417F, KeyframeAnimations.posVec(4.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(3.6164F, -19.6835F, -0.6276F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7083F, KeyframeAnimations.degreeVec(3.432F, -7.2069F, 0.1609F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(4.3458F, -6.6975F, -7.3773F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(2.4592F, 32.6351F, 1.789F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-1.2467F, 37.69F, -4.2572F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-2.7614F, 50.1348F, -4.8604F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(-18.1409F, 52.627F, -2.0178F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(3.6003F, 57.6117F, 3.6678F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.posVec(-2.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9167F, KeyframeAnimations.posVec(-4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.posVec(-3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0417F, KeyframeAnimations.posVec(-1.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.1667F, KeyframeAnimations.posVec(1.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.2083F, KeyframeAnimations.posVec(3.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.posVec(-2.2F, 1.0F, -4.6F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4167F, KeyframeAnimations.posVec(2.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.5417F, KeyframeAnimations.posVec(2.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-2.3273F, -24.8984F, 5.5139F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.3233F, -47.4089F, -0.5595F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5417F, KeyframeAnimations.degreeVec(2.5092F, -36.8895F, 0.3952F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(7.1995F, -38.6957F, -5.8493F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(8.699F, -27.0039F, -6.4832F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7083F, KeyframeAnimations.degreeVec(2.9877F, -10.7315F, -8.3894F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(4.194F, -5.4027F, -14.3916F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(4.6965F, 11.8428F, -11.2318F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(3.2743F, 27.0095F, -11.9137F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(0.7933F, 40.2978F, -12.5073F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(10.1967F, 56.9628F, 12.7771F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(2.4976F, 44.9455F, 3.5333F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.625F, KeyframeAnimations.degreeVec(-1.7494F, 34.9618F, -3.051F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-1.6158F, 27.465F, -2.7937F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-1.5254F, 19.9679F, -2.5693F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.875F, KeyframeAnimations.degreeVec(-2.4312F, 19.8815F, -5.2269F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.9167F, KeyframeAnimations.degreeVec(-3.0268F, 14.7683F, -7.591F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.1667F, KeyframeAnimations.posVec(-0.19F, 1.6F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.2083F, KeyframeAnimations.posVec(-0.24F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2917F, KeyframeAnimations.posVec(-0.33F, -0.36F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.posVec(-0.76F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.875F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.625F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-12.2989F, -17.7909F, -21.2121F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.posVec(6.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.625F, KeyframeAnimations.posVec(9.71F, -2.79F, -10.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.posVec(18.42F, -7.71F, -9.7F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(-2.5676F, 10.0614F, -17.2964F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(12.0F, 2.8F, -1.4F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.625F, KeyframeAnimations.posVec(14.0F, 9.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 12.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-8.1225F, 1.0075F, -2.8419F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.posVec(-11.5F, -4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.posVec(2.87F, 0.27F, -7.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.posVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.0417F, KeyframeAnimations.degreeVec(-33.7414F, 22.5416F, 32.9867F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.0833F, KeyframeAnimations.degreeVec(-12.312F, 49.6803F, -277.7594F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.125F, KeyframeAnimations.degreeVec(-12.312F, 49.6803F, -277.7594F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-12.4241F, 42.3539F, -276.1501F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(-22.1202F, 56.7064F, -258.6732F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-7.8637F, 53.7831F, -282.9333F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-7.8938F, 56.2596F, -283.2773F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.375F, KeyframeAnimations.degreeVec(-7.8938F, 56.2596F, -283.2773F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4167F, KeyframeAnimations.degreeVec(-7.8938F, 56.2596F, -283.2773F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4583F, KeyframeAnimations.degreeVec(-7.8938F, 56.2596F, -283.2773F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(-7.6547F, 74.5931F, -278.1028F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(-9.7551F, 94.3706F, -281.7569F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(46.8948F, 132.3971F, -228.2129F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.degreeVec(20.2984F, 123.8322F, -255.2976F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(9.0154F, 101.9432F, -267.7342F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7917F, KeyframeAnimations.degreeVec(6.549F, 82.1192F, -271.3623F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.875F, KeyframeAnimations.degreeVec(5.5661F, 44.8128F, -275.3601F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9167F, KeyframeAnimations.degreeVec(5.7432F, 34.8612F, -276.3598F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9583F, KeyframeAnimations.degreeVec(7.2427F, 10.0145F, -279.5239F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(38.7797F, -7.6487F, -327.0914F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(-10.4139F, -3.8862F, -348.3505F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.0417F, KeyframeAnimations.posVec(3.0F, 6.0F, -9.83F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.0833F, KeyframeAnimations.posVec(-7.51F, 36.59F, -25.66F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.125F, KeyframeAnimations.posVec(-7.51F, 35.59F, -28.66F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.2083F, KeyframeAnimations.posVec(0.49F, 39.59F, -34.66F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.posVec(13.0F, 32.0F, -37.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.2917F, KeyframeAnimations.posVec(21.0F, 39.5F, -36.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.3333F, KeyframeAnimations.posVec(21.0F, 39.5F, -36.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.375F, KeyframeAnimations.posVec(21.0F, 39.5F, -36.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4167F, KeyframeAnimations.posVec(21.0F, 39.5F, -36.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4583F, KeyframeAnimations.posVec(21.0F, 39.5F, -36.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.posVec(40.0F, 43.5F, -31.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.625F, KeyframeAnimations.posVec(47.0F, 46.5F, -23.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.posVec(56.66F, 53.03F, -11.23F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083F, KeyframeAnimations.posVec(50.66F, 56.03F, -17.23F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.posVec(41.66F, 56.03F, -22.23F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7917F, KeyframeAnimations.posVec(26.66F, 56.03F, -29.23F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.posVec(1.84F, 52.79F, -26.25F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9167F, KeyframeAnimations.posVec(-11.16F, 54.79F, -23.25F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.posVec(-22.16F, 52.79F, -11.25F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.posVec(-39.16F, 33.79F, 19.75F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.posVec(-1.16F, -4.21F, 6.75F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7917F, KeyframeAnimations.degreeVec(1.0848F, -4.8812F, -12.5462F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7917F, KeyframeAnimations.posVec(11.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-39.4468F, 4.3379F, 21.9662F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.posVec(2.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.posVec(-9.0F, 20.0F, -20.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}
