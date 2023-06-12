import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Galinha extends JLabel {
    private Status status;
    private int tamanho;

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
        this.tamanho = 300; // Define o tamanho inicial da galinha

        ImageIcon chickenIcon = new ImageIcon(this.getClass().getResource("chicken.png"));
        this.setIcon(chickenIcon);
        Image scaledImage = chickenIcon.getImage().getScaledInstance(tamanho, tamanho, Image.SCALE_SMOOTH);
        ImageIcon scaledChickenIcon = new ImageIcon(scaledImage);
        this.setIcon(scaledChickenIcon);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                playClickSoundGalinha();
                Galinha.this.aumentarTamanho();
                Galinha.this.aumentarPontuacao();
                Save.salvarDados(status.getNomeJogador(), status.getDinheiro(), status.getDinheiroPorClique(), status.getChancheGolpeDeSorte(), status.getGolpeDeSorte());
            }
        });
    }

    public void aumentarTamanho() {

        int tamanhoOriginal = 300; // Define o tamanho original da galinha

        if (tamanho < tamanhoOriginal + 10) {
            tamanho += 20; // Incrementa o tamanho da galinha
        } else {
            tamanho = tamanhoOriginal; // Volta ao tamanho original
        }

        // Redimensiona a imagem da galinha com base no novo tamanho
        ImageIcon chickenIcon = new ImageIcon(this.getClass().getResource("chicken.png"));
        Image scaledImage = chickenIcon.getImage().getScaledInstance(tamanho, tamanho, Image.SCALE_SMOOTH);
        ImageIcon scaledChickenIcon = new ImageIcon(scaledImage);
        this.setIcon(scaledChickenIcon);

        // Atualiza o tamanho do componente
        Dimension size = new Dimension(tamanho, tamanho);
        setPreferredSize(size);
        setSize(size);

        // Redesenha o componente
        revalidate();
        repaint();
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