import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Upgrade {
    protected int custo;
    protected int nivel;

    public Upgrade() {
    }

    public abstract void funcaoUpgrade();

    public int getNivel() {
        return this.nivel;
    }

    public int getCusto() {
        return this.custo;
    }

    public void playClickSoundUpgrade() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("upgradeSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
