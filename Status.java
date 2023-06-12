import java.awt.Dimension;
import java.util.ArrayList;
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
    private JLabel upgradeCompradosLabel;
    private ArrayList<Upgrade> upgradesComprados;



        /*public void salvarDados() {
        String arquivoDados = "dados_usuario.txt";
        try {
            FileWriter writer = new FileWriter(arquivoDados);
            writer.write(getNomeJogador() + "\n");
            writer.write(getDinheiro() + "\n");
            writer.write(getDinheiroPorClique() + "\n");
            writer.write(getChancheGolpeDeSorte() + "\n");

            writer.close();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
    public void carregarDados() {
        String arquivoDados = "dados_usuario.txt";
        try {
            FileReader reader = new FileReader(arquivoDados);
            BufferedReader bufferedReader = new BufferedReader(reader);

            setNomeJogador(bufferedReader.readLine());
            setDinheiro(Integer.parseInt(bufferedReader.readLine()));
            setDinheiroPorClique(Integer.parseInt(bufferedReader.readLine()));
            setChanceDeGolpeDeSorte(Integer.parseInt(bufferedReader.readLine()));

            bufferedReader.close();
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }*/

    public Status() {

        nomeJogador = JOptionPane.showInputDialog("Digite o seu nome:");
        // Remova a chamada do método Save.carregarDados(this) daqui

        if (nomeJogador == null || nomeJogador.isEmpty()) {
            nomeJogador = JOptionPane.showInputDialog("Digite o seu nome:");
            Save.salvarDados(nomeJogador, dinheiro, dinheiroPorClique, chanceDeGolpeDeSorte, golpeDeSorte);
        } else {
            System.out.println("Nome do jogador carregado: " + nomeJogador);
        }

        this.createStatusPanel();
        this.createUpgradesCompradosLabel();

        // Mova a chamada do método Save.carregarDados(this) para cá
        Save.carregarDados(this);
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

    private void createUpgradesCompradosLabel() {
        this.upgradeCompradosLabel = new JLabel("Upgrades Comprados: 0");
        this.add(this.upgradeCompradosLabel);
    }

    public void atualizarContagemUpgrades(int totalUpgrades) {
        this.upgradeCompradosLabel.setText("Upgrades Comprados: " + totalUpgrades);
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

    public void setNomeJogador(String nomeJogador) {
    }
}