import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Galinha extends JLabel {
    private Status status;

    public void playClickSoundGalinha() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("chickenSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Galinha(Status status) {
        this.status = status;
        ImageIcon chickenIcon = new ImageIcon(this.getClass().getResource("chicken.png"));
        this.setIcon(chickenIcon);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playClickSoundGalinha();
                Galinha.this.aumentarPontuacao();
                Save.salvarDados(status.getNomeJogador(), status.getDinheiro(), status.getDinheiroPorClique(), status.getChancheGolpeDeSorte(), status.getGolpeDeSorte());
            }
        });
    }

    public void aumentarPontuacao() {
        double sorte = Math.random();
        if (sorte <= this.status.getChancheGolpeDeSorte()) {
            this.status.setDinheiro(this.status.getDinheiro() + this.status.getDinheiroPorClique() * this.status.getGolpeDeSorte());
        } else {
            this.status.setDinheiro(this.status.getDinheiro() + this.status.getDinheiroPorClique());
        }

    }
}
