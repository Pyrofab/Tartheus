package arrowstorm66.tartheus.util;

import net.minecraft.client.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.client.audio.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.init.*;

public class CustomMusicTicker extends MusicTicker
{
    private static final ArrayList<MusicEntry> musicEntries;
    private static final MusicEntry MENU;
    private static final MusicEntry GAME;
    private static final MusicEntry CREATIVE;
    private static final MusicEntry NETHER;
    private static final MusicEntry END_BOSS;
    private static final MusicEntry END;
    private final Random rand;
    private final Minecraft mc;
    private MusicEntry selectedMusic;
    private ISound currentMusic;
    private int timeUntilNextMusic;
    
    public CustomMusicTicker(final Minecraft minecraftIn) {
        super(minecraftIn);
        this.rand = new Random();
        this.selectedMusic = null;
        this.timeUntilNextMusic = 100;
        this.mc = minecraftIn;
    }
    
    public static void register(final MusicEntry entry) {
        CustomMusicTicker.musicEntries.add(entry);
    }
    
    private MusicEntry updateSelectedMusic() {
        if (this.mc.player != null) {
            for (final MusicEntry entry : CustomMusicTicker.musicEntries) {
                if (entry.shouldSelect(this.mc)) {
                    return entry;
                }
            }
            return this.getVanillaMusic();
        }
        return CustomMusicTicker.MENU;
    }
    
    private MusicEntry getVanillaMusic() {
        return (this.mc.player != null) ? ((this.mc.player.world.provider instanceof WorldProviderHell) ? CustomMusicTicker.NETHER : ((this.mc.player.world.provider instanceof WorldProviderEnd) ? (this.mc.ingameGUI.getBossOverlay().shouldPlayEndBossMusic() ? CustomMusicTicker.END_BOSS : CustomMusicTicker.END) : ((this.mc.player.capabilities.isCreativeMode && this.mc.player.capabilities.allowFlying) ? CustomMusicTicker.CREATIVE : CustomMusicTicker.GAME))) : CustomMusicTicker.MENU;
    }
    
    public void update() {
        final MusicEntry entry = this.updateSelectedMusic();
        if (entry != this.selectedMusic) {
            this.stopMusic();
            this.selectedMusic = entry;
        }
        if (this.currentMusic != null) {
            if (!this.selectedMusic.getMusic().getSoundName().equals((Object)this.currentMusic.getSoundLocation())) {
                this.mc.getSoundHandler().stopSound(this.currentMusic);
                this.timeUntilNextMusic = MathHelper.getInt(this.rand, 0, this.selectedMusic.getMinDelay() / 2);
            }
            if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic)) {
                this.currentMusic = null;
                this.timeUntilNextMusic = Math.min(MathHelper.getInt(this.rand, this.selectedMusic.getMinDelay(), this.selectedMusic.getMaxDelay()), this.timeUntilNextMusic);
            }
        }
        this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, this.selectedMusic.getMaxDelay());
        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
            this.playMusic(this.selectedMusic);
        }
    }
    
    @Deprecated
    public void playMusic(final MusicTicker.MusicType requestedMusicType) {
    }
    
    public void playMusic(final MusicEntry requestedMusicType) {
        this.currentMusic = (ISound)PositionedSoundRecord.getMusicRecord(requestedMusicType.getMusic());
        this.mc.getSoundHandler().playSound(this.currentMusic);
        this.timeUntilNextMusic = Integer.MAX_VALUE;
    }
    
    public void stopMusic() {
        if (this.currentMusic != null) {
            if (this.mc.player == null) {
                this.mc.getSoundHandler().stopSounds();
            }
            this.mc.getSoundHandler().stopSound(this.currentMusic);
            this.currentMusic = null;
            this.timeUntilNextMusic = 0;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isMusicPlaying(final SoundEvent music) {
        return music != null && music.getSoundName().toString().equals(this.currentMusic.getSoundLocation().toString()) && this.mc.getSoundHandler().isSoundPlaying(this.currentMusic);
    }
    
    static {
        musicEntries = new ArrayList<MusicEntry>();
        MENU = new MusicEntry(SoundEvents.MUSIC_MENU, 20, 600) {
            @Override
            public boolean shouldSelect(final Minecraft mc) {
                return false;
            }
        };
        GAME = new MusicEntry(SoundEvents.MUSIC_GAME, 12000, 24000) {
            @Override
            public boolean shouldSelect(final Minecraft mc) {
                return false;
            }
        };
        CREATIVE = new MusicEntry(SoundEvents.MUSIC_CREATIVE, 1200, 3600) {
            @Override
            public boolean shouldSelect(final Minecraft mc) {
                return false;
            }
        };
        NETHER = new MusicEntry(SoundEvents.MUSIC_NETHER, 1200, 3600) {
            @Override
            public boolean shouldSelect(final Minecraft mc) {
                return false;
            }
        };
        END_BOSS = new MusicEntry(SoundEvents.MUSIC_DRAGON, 0, 0) {
            @Override
            public boolean shouldSelect(final Minecraft mc) {
                return false;
            }
        };
        END = new MusicEntry(SoundEvents.MUSIC_END, 6000, 24000) {
            @Override
            public boolean shouldSelect(final Minecraft mc) {
                return false;
            }
        };
    }
    
    public abstract static class MusicEntry
    {
        private final SoundEvent music;
        private final int minDelay;
        private final int maxDelay;
        
        public MusicEntry(final SoundEvent musicIn, final int minIn, final int maxIn) {
            this.music = musicIn;
            this.minDelay = minIn;
            this.maxDelay = maxIn;
        }
        
        public abstract boolean shouldSelect(final Minecraft p0);
        
        public SoundEvent getMusic() {
            return this.music;
        }
        
        public int getMinDelay() {
            return this.minDelay;
        }
        
        public int getMaxDelay() {
            return this.maxDelay;
        }
    }
}
