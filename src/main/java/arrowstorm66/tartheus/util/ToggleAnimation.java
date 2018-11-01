package arrowstorm66.tartheus.util;

import net.minecraft.nbt.*;

public class ToggleAnimation
{
    private final int length;
    private int speed;
    private boolean state;
    private int timer;
    
    public ToggleAnimation(final int length) {
        this.speed = 1;
        this.length = length;
    }
    
    public void update() {
        if (this.state) {
            this.timer = Math.min(this.timer + this.speed, this.length);
        }
        else {
            this.timer = Math.max(this.timer - this.speed, 0);
        }
    }
    
    public void set(final boolean state) {
        this.state = state;
    }
    
    public boolean get() {
        return this.state;
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    public void setTimer(final int timer) {
        this.timer = timer;
    }
    
    public int getTimer() {
        return this.timer;
    }
    
    public float getScale() {
        return this.timer / this.length;
    }
    
    public NBTTagCompound serialize(final NBTTagCompound compound) {
        compound.setBoolean("state", this.state);
        compound.setShort("timer", (short)this.timer);
        compound.setByte("speed", (byte)this.speed);
        return compound;
    }
    
    public void deserialize(final NBTTagCompound compound) {
        this.state = compound.getBoolean("state");
        this.timer = compound.getShort("timer");
        this.speed = compound.getByte("speed");
    }
    
    @Override
    public String toString() {
        return "ToggleAnimation{state=" + this.state + ", timer=" + this.timer + '}';
    }
}
