package net.com.burntland.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

// Save this class in your mod and generate all required imports
/**
 * Made with Blockbench 4.12.6 Exported for Minecraft version 1.19 or later with
 * Mojang mappings
 * 
 * @author Author
 */
public class executeAnimation {
	public static final AnimationDefinition walk = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, -1.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
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
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(6.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.75F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(6.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 25.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.posVec(21.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(-12.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(8.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(-131.8027F, 1.207F, 142.44F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(50.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(-0.5F, 0.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(1.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.31F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition stand = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0241F, 4.9245F, -9.1339F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(4.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.posVec(4.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 2.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 40.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(6.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(7.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(8.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down4", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -2.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(3.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition attack1 = AnimationDefinition.Builder.withLength(2.5F)
			.addAnimation("rightleg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833F, KeyframeAnimations.degreeVec(5.0F, -27.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(3.917F, -15.2288F, 3.702F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(3.8669F, 12.2102F, 5.5514F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.degreeVec(4.0144F, 19.6925F, 6.0871F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(8.4821F, 47.0496F, 9.4417F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(13.6703F, 46.0874F, 16.5815F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(12.8943F, 43.695F, 15.4687F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833F, KeyframeAnimations.posVec(0.0F, 0.0F, -6.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.posVec(-2.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.posVec(-2.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0833F, KeyframeAnimations.posVec(3.0F, 0.0F, -6.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4167F, KeyframeAnimations.posVec(5.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5417F, KeyframeAnimations.posVec(4.0F, 0.0F, -4.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 10.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(3.6164F, -19.6835F, -0.6276F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7083F, KeyframeAnimations.degreeVec(3.432F, -7.2069F, 0.1609F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(4.3458F, -6.6975F, -7.3773F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(2.4592F, 32.6351F, 1.789F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-1.2467F, 37.69F, -4.2572F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-2.7614F, 50.1348F, -4.8604F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(3.6003F, 57.6117F, 3.6678F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.posVec(-2.0F, 0.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9167F, KeyframeAnimations.posVec(-4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.posVec(-3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0417F, KeyframeAnimations.posVec(-1.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.1667F, KeyframeAnimations.posVec(1.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.2083F, KeyframeAnimations.posVec(3.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4167F, KeyframeAnimations.posVec(2.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5417F, KeyframeAnimations.posVec(2.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-2.3273F, -24.8984F, 5.5139F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.3233F, -47.4089F, -0.5595F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5417F, KeyframeAnimations.degreeVec(2.5092F, -36.8895F, 0.3952F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5833F, KeyframeAnimations.degreeVec(7.1995F, -38.6957F, -5.8493F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(11.1641F, -26.1408F, -11.9904F), AnimationChannel.Interpolations.LINEAR),
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
							new Keyframe(0.875F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, -12.5F, -10.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.posVec(6.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.degreeVec(-2.5886F, -12.4164F, -16.2906F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.625F, KeyframeAnimations.posVec(5.0F, 1.0F, -3.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.posVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(-73.0224F, -29.2168F, -7.1634F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(-64.2214F, -37.5338F, -13.8953F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-70.0988F, -1.1037F, -9.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.875F, KeyframeAnimations.degreeVec(-72.4738F, 27.463F, -6.6847F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-73.1994F, 32.3822F, -7.0743F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-72.8958F, 39.6358F, -6.1421F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(-74.4313F, 75.7594F, 16.0019F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-56.0593F, 44.3294F, -2.6039F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.625F, KeyframeAnimations.degreeVec(-44.5445F, 33.2471F, -1.9529F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-40.9589F, 32.4239F, -4.1946F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-34.5342F, 25.4096F, -2.6869F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.875F, KeyframeAnimations.degreeVec(-18.3718F, 23.3434F, -1.9451F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.9167F, KeyframeAnimations.degreeVec(-22.3972F, 14.8882F, -0.4254F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-10.5244F, 7.4202F, -5.2457F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.posVec(26.0F, 1.0F, -47.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5F, KeyframeAnimations.posVec(34.0F, 1.0F, -37.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.625F, KeyframeAnimations.posVec(21.83F, 2.83F, -34.33F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7917F, KeyframeAnimations.posVec(4.81F, 5.28F, -30.58F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.9583F, KeyframeAnimations.posVec(-4.97F, 5.56F, -23.27F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.25F, KeyframeAnimations.posVec(-27.0F, 12.0F, -9.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(-14.0F, -2.0F, -12.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.7083F, KeyframeAnimations.posVec(-4.74F, -2.75F, -9.02F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.75F, KeyframeAnimations.posVec(-2.28F, -3.5F, -8.43F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.7917F, KeyframeAnimations.posVec(-0.83F, -3.25F, -9.83F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.875F, KeyframeAnimations.posVec(2.7F, -1.75F, -0.03F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.9167F, KeyframeAnimations.posVec(1.47F, -1.5F, -3.13F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.9583F, KeyframeAnimations.posVec(3.74F, -2.25F, -0.57F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition attack2 = AnimationDefinition.Builder.withLength(2.0F)
			.addAnimation("rightleg", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(5.4669F, -35.8914F, -9.2718F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -72.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -67.5F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(1.7494F, -34.9618F, -3.051F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-2.5F, -70.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(-1.7101F, -60.0065F, -0.8682F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 17.5F, -2.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(2.1557F, -39.6291F, -7.254F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(14.3113F, -79.2582F, -14.5081F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4167F, KeyframeAnimations.degreeVec(17.8009F, -73.3628F, -18.4123F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.degreeVec(-5.0F, -75.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(2.0F, KeyframeAnimations.degreeVec(-7.4738F, -60.0279F, 2.6383F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(6.0F, 0.0F, -23.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.4167F, KeyframeAnimations.posVec(4.0F, 0.0F, -18.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.75F, KeyframeAnimations.posVec(6.0F, 0.0F, -28.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(2.0F, KeyframeAnimations.posVec(2.0F, 0.0F, -31.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
	public static final AnimationDefinition attack3 = AnimationDefinition.Builder.withLength(1.9583F)
			.addAnimation("rightleg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.posVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9583F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.1667F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -7.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 7.5F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftleg",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.875F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.0417F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -12.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -3.75F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0417F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.125F, KeyframeAnimations.degreeVec(-0.1091F, -2.4976F, 2.5024F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-0.218F, -2.4905F, 5.0047F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(-0.3265F, -2.4786F, 7.5071F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("hslfbody", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, -2.5F, -12.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, -17.5F, -12.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.7879F, -17.4828F, -15.1212F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.2083F, KeyframeAnimations.degreeVec(0.8134F, -22.4823F, -15.1955F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.8291F, -24.9821F, -15.2346F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-1.5005F, -24.9527F, -9.7189F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(0.9995F, -24.9527F, -9.7189F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("leftarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4583F, KeyframeAnimations.posVec(9.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.3333F, KeyframeAnimations.posVec(7.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4167F, KeyframeAnimations.posVec(7.0F, -2.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.4369F, -4.9809F, -10.019F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4583F, KeyframeAnimations.degreeVec(1.2972F, -4.8292F, -20.0547F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.625F, KeyframeAnimations.degreeVec(1.7139F, -4.6978F, -25.0703F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(4.6175F, -4.5305F, -30.0838F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.125F, KeyframeAnimations.degreeVec(12.1379F, -7.0224F, -30.2865F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down3",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.posVec(2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4167F, KeyframeAnimations.posVec(5.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4583F, KeyframeAnimations.posVec(9.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.625F, KeyframeAnimations.posVec(12.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.posVec(12.0F, 5.0F, 2.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0417F, KeyframeAnimations.posVec(12.0F, 5.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0833F, KeyframeAnimations.posVec(15.0F, 6.0F, 2.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.125F, KeyframeAnimations.posVec(12.0F, 7.0F, 6.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-0.6543F, 4.9571F, -12.5283F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.375F, KeyframeAnimations.degreeVec(1.6296F, 4.9238F, -15.0374F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4167F, KeyframeAnimations.degreeVec(1.1933F, 5.0471F, -20.0549F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.7478F, 5.1318F, -25.074F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0697F, 5.1854F, -32.6043F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-11.3754F, 44.212F, -46.3442F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.7083F, KeyframeAnimations.degreeVec(-10.3812F, 52.0478F, -44.7926F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightarm",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2917F, KeyframeAnimations.posVec(1.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.3333F, KeyframeAnimations.posVec(8.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.4167F, KeyframeAnimations.posVec(13.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4583F, KeyframeAnimations.posVec(18.0F, 2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.posVec(22.0F, 5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.7083F, KeyframeAnimations.posVec(22.0F, 4.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.375F, KeyframeAnimations.degreeVec(-1.4644F, -1.8818F, -61.7241F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.4167F, KeyframeAnimations.degreeVec(1.5939F, 2.0868F, -58.7307F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-20.9071F, -0.4122F, -58.8002F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(2.8231F, -3.0702F, -13.8753F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.7917F, KeyframeAnimations.degreeVec(2.9543F, -2.9441F, -16.3756F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0417F, KeyframeAnimations.degreeVec(2.5448F, -3.3044F, -8.8731F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(2.247F, -3.5136F, -3.8686F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.125F, KeyframeAnimations.degreeVec(-0.253F, -3.5136F, -3.8686F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-0.5586F, -3.4782F, 1.1406F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(-0.7099F, -3.4505F, 3.6451F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-0.8599F, -3.4162F, 6.1493F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-1.0083F, -3.3755F, 8.6534F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("down2",
					new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.0833F, KeyframeAnimations.posVec(-4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.125F, KeyframeAnimations.posVec(-3.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.2083F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.3333F, KeyframeAnimations.posVec(4.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.375F, KeyframeAnimations.posVec(26.5F, 12.0F, -1.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.4167F, KeyframeAnimations.posVec(25.0F, 9.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5417F, KeyframeAnimations.posVec(22.0F, 14.0F, -11.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.posVec(6.0F, 1.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.9167F, KeyframeAnimations.posVec(5.0F, 1.0F, 2.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.9583F, KeyframeAnimations.posVec(6.0F, 1.0F, 2.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0417F, KeyframeAnimations.posVec(5.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0833F, KeyframeAnimations.posVec(2.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.125F, KeyframeAnimations.posVec(2.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.1667F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.posVec(-2.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.3333F, KeyframeAnimations.posVec(-3.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4167F, KeyframeAnimations.posVec(-5.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(-27.4921F, 0.422F, -11.0776F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25F, KeyframeAnimations.degreeVec(-71.5798F, -4.437F, -16.9572F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5F, KeyframeAnimations.degreeVec(-71.8478F, 7.5432F, -38.9416F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-69.7059F, -15.0916F, -51.7146F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75F, KeyframeAnimations.degreeVec(-69.1345F, -23.1641F, -56.1558F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-76.4535F, 14.2032F, -52.5714F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.0F, KeyframeAnimations.degreeVec(-92.8178F, 33.7112F, -69.0199F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-51.3213F, 82.5866F, -34.875F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-19.0968F, 83.7754F, 2.3639F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.25F, KeyframeAnimations.degreeVec(-4.7626F, 84.8441F, 22.3197F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.2917F, KeyframeAnimations.degreeVec(26.7163F, 84.8253F, 44.0316F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.4583F, KeyframeAnimations.degreeVec(68.8857F, 74.4692F, 80.2544F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1.5F, KeyframeAnimations.degreeVec(78.8031F, 71.8802F, 89.31F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("axe", new AnimationChannel(AnimationChannel.Targets.POSITION, new Keyframe(0.0F, KeyframeAnimations.posVec(6.0F, -7.0F, -14.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.25F, KeyframeAnimations.posVec(12.0F, 0.0F, -39.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.6667F, KeyframeAnimations.posVec(37.5F, 20.0F, -43.17F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.75F, KeyframeAnimations.posVec(40.0F, 23.0F, -37.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(0.8333F, KeyframeAnimations.posVec(29.33F, 13.33F, -43.33F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.875F, KeyframeAnimations.posVec(27.0F, 12.5F, -44.5F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.0F, KeyframeAnimations.posVec(20.0F, 6.0F, -40.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.1667F, KeyframeAnimations.posVec(25.0F, 0.67F, -19.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2083F, KeyframeAnimations.posVec(22.0F, -0.66F, -17.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.25F, KeyframeAnimations.posVec(20.0F, -2.0F, -16.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(1.2917F, KeyframeAnimations.posVec(23.67F, -1.0F, -12.83F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.5F, KeyframeAnimations.posVec(30.0F, 0.0F, -9.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}
