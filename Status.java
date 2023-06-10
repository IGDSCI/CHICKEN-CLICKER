import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class Status extends JPanel {
    private int dinheiroPorClique = 1;
    private int golpeDeSorte = 10;
    private double chanceDeGolpeDeSorte = 0.25;
    private int dinheiro = 1;
    private JLabel dinheiroLabel;
    private JLabel golpeDeSorteLabel;
    private JLabel chanceDeGolpeDeSorteLabel;
    private JLabel pontuacaoLabel;
    private String nomeJogador = JOptionPane.showInputDialog("Digite o seu nome:");

    public Status() {
        this.createStatusPanel();
    }

    private void createStatusPanel() {
        this.setLayout(new BoxLayout(this, 1));
        this.add(new JLabel("Nome: " + this.nomeJogador));
        this.dinheiroLabel = new JLabel("Dinheiro por clique: " + this.dinheiroPorClique);
        this.golpeDeSorteLabel = new JLabel("Golpe de Sorte: " + this.golpeDeSorte);
        this.chanceDeGolpeDeSorteLabel = new JLabel("Chance de Golpe de Sorte: " + this.chanceDeGolpeDeSorte);
        this.pontuacaoLabel = new JLabel("Dinheiro: " + this.dinheiro);
        this.add(this.nomeJogador);
        this.add(this.dinheiroLabel);
        this.add(this.golpeDeSorteLabel);
        this.add(this.chanceDeGolpeDeSorteLabel);
        this.add(this.pontuacaoLabel);
    }

    private void add(String nomeJogador) {
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public int getDinheiro() {
        return this.dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
        this.pontuacaoLabel.setText("Dinheiro: " + dinheiro);
    }

    public int getDinheiroPorClique() {
        return this.dinheiroPorClique;
    }

    public void setDinheiroPorClique(int dinheiroPorClique) {
        this.dinheiroPorClique = dinheiroPorClique;
        this.dinheiroLabel.setText("Dinheiro por clique: " + dinheiroPorClique);
    }

    public double getChancheGolpeDeSorte() {
        return this.chanceDeGolpeDeSorte;
    }

    public void setChanceDeGolpeDeSorte(double chancheDeGolpeDeSorte) {
        this.chanceDeGolpeDeSorte = chancheDeGolpeDeSorte;
        this.chanceDeGolpeDeSorteLabel.setText("Chance de Golpe de Sorte: " + chancheDeGolpeDeSorte);
    }

    public int getGolpeDeSorte() {
        return this.golpeDeSorte;
    }

    public void setGolpeDeSorte(int golpeDeSorte) {
        this.golpeDeSorte = golpeDeSorte;
        this.golpeDeSorteLabel.setText("Golpe de Sorte: " + golpeDeSorte);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180, 100);
    }
}
