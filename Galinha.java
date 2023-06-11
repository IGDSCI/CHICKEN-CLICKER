import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Galinha extends JLabel {
    private Status status;

    public Galinha(Status status) {
        this.status = status;
        ImageIcon chickenIcon = new ImageIcon(this.getClass().getResource("chicken.png"));
        this.setIcon(chickenIcon);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Galinha.this.aumentarPontuacao();
                Save.salvarNomeJogador(status.getNomeJogador(), status.getDinheiro(), status.getDinheiroPorClique());
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
