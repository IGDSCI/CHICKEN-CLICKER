import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Tela extends JFrame {
    private int width;
    private int height;
    private Status status;
    private Galinha galinha;
    private int upgrade1;
    private int upgrade2;
    private ArrayList<Upgrade> upgradesComprados = new ArrayList<>();
    private JLabel upgradeCountLabel;
    private boolean upgradesVisiveis = false;


    public Tela(int width, int height) throws IOException, FontFormatException {
        this.width = width;
        this.height = height;
        this.status = new Status();
        try {
            this.createScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void salvarDados() {
        String arquivoDados = "dados_usuario.txt";
        try {
            FileWriter writer = new FileWriter(arquivoDados);

            writer.write(upgrade1 + "\n");
            writer.write(upgrade2 + "\n");

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

            upgrade1 = Integer.parseInt(bufferedReader.readLine());

            upgrade2 = Integer.parseInt(bufferedReader.readLine());

            bufferedReader.close();
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }*/

    private void createScreen() throws IOException, FontFormatException {


        this.setTitle("Meu Jogo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImg = new ImageIcon(this.getClass().getResource("fazenda.png"));
                Image image = backgroundImg.getImage();
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };


        backgroundPanel.setLayout(null);
        backgroundPanel.setPreferredSize(new Dimension(this.width, this.height));
        this.getContentPane().add(backgroundPanel);
        this.status = new Status();
        backgroundPanel.add(this.status);
        this.status.setBounds(10, 10, 200, 120);
        this.updateStatusPanelSize(); // Atualiza a dimensão preferida do painel de status
        this.galinha = new Galinha(this.status);
        this.galinha.setBounds(230, 85, 355, 385);
        backgroundPanel.add(this.galinha);
        Upgrade1 upgrade1 = new Upgrade1(this.status, this.upgradesComprados);
        Upgrade2 upgrade2 = new Upgrade2(this.status, this.upgradesComprados);
        Upgrade3 upgrade3 = new Upgrade3(this.status, this.upgradesComprados);




        JButton upgrade1Button = new JButton("Upgrade 1 (" + upgrade1.getNivel() + ") R$" + upgrade1.getCusto());
        upgrade1Button.setBounds(10, 200, 170, 30);
        upgrade1Button.setVisible(false);
        upgrade1Button.setFont(upgrade1Button.getFont().deriveFont(14f)); // Define o tamanho da fonte para 14
        backgroundPanel.add(upgrade1Button);

        JButton upgrade2Button = new JButton("Upgrade 2 (" + upgrade2.getNivel() + ") R$" + upgrade2.getCusto());
        upgrade2Button.setBounds(10, 250, 170, 30);
        upgrade2Button.setVisible(false);
        upgrade2Button.setFont(upgrade2Button.getFont().deriveFont(14f)); // Define o tamanho da fonte para 14
        backgroundPanel.add(upgrade2Button);

        JButton upgrade3Button = new JButton("Upgrade 3 (" + upgrade3.getNivel() + ") R$" + upgrade3.getCusto());
        upgrade3Button.setBounds(10, 300, 170, 30);
        upgrade3Button.setVisible(false);
        upgrade3Button.setFont(upgrade3Button.getFont().deriveFont(14f)); // Define o tamanho da fonte para 14
        backgroundPanel.add(upgrade3Button);

        JToggleButton upgradeButton = new JToggleButton("Upgrade");
        upgradeButton.setBounds(10, 150, 170, 30);
        backgroundPanel.add(upgradeButton);

        this.upgradeCountLabel = new JLabel("Upgrades Comprados: 0");
        this.upgradeCountLabel.setBounds(10, 350, 200, 20);
        backgroundPanel.add(this.upgradeCountLabel);

        upgrade1Button.addActionListener((e) -> {
            upgrade1.funcaoUpgrade();
            upgrade1Button.setText("Upgrade 1 (" + upgrade1.getNivel() + ") R$" + upgrade1.getCusto());
            atualizarContagemUpgrades();
        });

        upgrade2Button.addActionListener((e) -> {
            upgrade2.funcaoUpgrade();
            upgrade2Button.setText("Upgrade 2 (" + upgrade2.getNivel() + ") R$" + upgrade2.getCusto());
            atualizarContagemUpgrades();
        });

        upgrade3Button.addActionListener((e) -> {
            upgrade3.funcaoUpgrade();
            upgrade3Button.setText("Upgrade 3 (" + upgrade3.getNivel() + ") R$" + upgrade3.getCusto());
            atualizarContagemUpgrades();
        });


        upgradeButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                upgradesVisiveis = upgradeButton.isSelected();

                // Define a visibilidade dos botões de upgrade com base no estado do JToggleButton
                upgrade1Button.setVisible(upgradesVisiveis);
                upgrade2Button.setVisible(upgradesVisiveis);
                upgrade3Button.setVisible(upgradesVisiveis);
            }
        });
        JButton ajudanteButton = new JButton("Ajudante 1 (3 pontos/s)");
        ajudanteButton.setBounds(10, 400, 170, 30);
        ajudanteButton.setVisible(false);
        ajudanteButton.setFont(ajudanteButton.getFont().deriveFont(14f)); // Define o tamanho da fonte para 14
        backgroundPanel.add(ajudanteButton);

        ajudanteButton.addActionListener((e) -> {
            Ajudante1 ajudante1 = new Ajudante1(this.status);
            ajudante1.comprar();
            ajudanteButton.setEnabled(false);
        });
        upgradeButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                upgradesVisiveis = upgradeButton.isSelected();

                // Define a visibilidade dos botões de upgrade e do botão do ajudante com base no estado do JToggleButton
                upgrade1Button.setVisible(upgradesVisiveis);
                upgrade2Button.setVisible(upgradesVisiveis);
                upgrade3Button.setVisible(upgradesVisiveis);
                ajudanteButton.setVisible(upgradesVisiveis);
            }
        });
        Font robotoFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/res/Roboto.ttf"));
        upgradeButton.setFont(robotoFont.deriveFont(14f));
        ajudanteButton.setFont(robotoFont.deriveFont(14f));
        upgrade1Button.setFont(robotoFont.deriveFont(14f));
        upgrade2Button.setFont(robotoFont.deriveFont(14f));
        upgrade3Button.setFont(robotoFont.deriveFont(14f));

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void updateStatusPanelSize() {
        Dimension preferredSize = this.status.getPreferredSize();
        this.status.setSize(preferredSize);
    }

    private void atualizarContagemUpgrades() {
        int totalUpgrades = upgradesComprados.size();
        upgradeCountLabel.setText("Upgrades Comprados: " + totalUpgrades);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        int screenWidth = 800;
        int screenHeight = 600;
        new Tela(screenWidth, screenHeight);
    }
}
