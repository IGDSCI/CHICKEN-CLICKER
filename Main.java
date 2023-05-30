import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private int clickCount = 0;
    private int score = 0;
    private JLabel scoreLabel;
    Font font1, font2;
    JButton button1, button2, button3, button4;
    int dinheiro = 0;
    int contador = 1;
    private double multiplicadorSorte = 3.0;
    private double cancheDeGolpeDaSorte = 0.25;

    String nomeJogador = JOptionPane.showInputDialog("Digite o seu nome:");

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        createFont();
        createUI();
    }

    public void createFont() {
        font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 15);
    }

    public void createUI() {
        JFrame window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crie um painel para o fundo e defina o layout como nulo
        JPanel backgroundPanel = new JPanel(null) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carregue e desenhe a imagem de fundo
                ImageIcon backgroundImg = new ImageIcon(getClass().getClassLoader().getResource("fazenda.png"));
                Image image = backgroundImg.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        window.setContentPane(backgroundPanel);

        JPanel chickenPanel = new JPanel();
        chickenPanel.setBounds(300, 240, 370, 370);
        chickenPanel.setOpaque(false); // Tornar o painel transparente para mostrar o fundo
        backgroundPanel.add(chickenPanel);

        ImageIcon chicken = new ImageIcon(getClass().getClassLoader().getResource("chicken.png"));

        JButton chickenButton = new JButton();
        chickenButton.setContentAreaFilled(false); // Tornar o botão transparente para mostrar o fundo
        chickenButton.setFocusPainted(false);
        chickenButton.setBorder(null);
        chickenButton.setIcon(chicken);
        chickenPanel.add(chickenButton);

        JLabel labelNome = new JLabel("Jogador: " + nomeJogador);
        labelNome.setBounds(10, 15, 495, 50);
        labelNome.setBackground(Color.GREEN); // Definir cor de fundo verde
        labelNome.setOpaque(true); // Tornar o fundo visível
        labelNome.setFont(font1);
        backgroundPanel.add(labelNome);

        JLabel labelPoderClick = new JLabel("Dinheiro por clique: " + contador);
        labelPoderClick.setBounds(10, 65, 495, 40);
        labelPoderClick.setBackground(Color.GREEN); // Definir cor de fundo verde
        labelPoderClick.setOpaque(true); // Tornar o fundo visível
        labelPoderClick.setFont(font1);
        backgroundPanel.add(labelPoderClick);

        JLabel chanceGolpeSorte = new JLabel("Chanche golpe de sorte: " + cancheDeGolpeDaSorte);
        chanceGolpeSorte.setBounds(10, 105, 495, 50);
        chanceGolpeSorte.setBackground(Color.GREEN); // Definir cor de fundo verde
        chanceGolpeSorte.setOpaque(true); // Tornar o fundo visível
        chanceGolpeSorte.setFont(font1);
        backgroundPanel.add(chanceGolpeSorte);

        JLabel multiplicadorGolpeSorte = new JLabel("Multiplicador golpe de sorte: " + multiplicadorSorte);
        multiplicadorGolpeSorte.setBounds(10, 145, 495, 50);
        multiplicadorGolpeSorte.setBackground(Color.GREEN); // Definir cor de fundo verde
        multiplicadorGolpeSorte.setOpaque(true); // Tornar o fundo visível
        multiplicadorGolpeSorte.setFont(font1);
        backgroundPanel.add(multiplicadorGolpeSorte);

        scoreLabel = new JLabel("Pontuação: 0");
        scoreLabel.setBounds(10, 185, 495, 40);
        scoreLabel.setBackground(Color.GREEN); // Definir cor de fundo verde
        scoreLabel.setOpaque(true); // Tornar o fundo visível
        scoreLabel.setFont(font1);
        backgroundPanel.add(scoreLabel);

        chickenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playClickSound();
                // Verifique se ocorre um golpe de sorte
                double sorte = Math.random();
                if (sorte <= cancheDeGolpeDaSorte) {
                    dinheiro += contador * multiplicadorSorte;
                } else {
                    dinheiro = dinheiro + contador;
                }

                scoreLabel.setText("Pontuação: " + dinheiro);
            }
        });

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(680, 280, 300, 125);
        itemPanel.setBackground(Color.white);
        itemPanel.setLayout(new GridLayout(2, 1));
        backgroundPanel.add(itemPanel);

        JButton upgradeButton = new JButton("Upgrade (10 R$)");
        upgradeButton.setFont(font1);
        backgroundPanel.add(upgradeButton);
        itemPanel.add(upgradeButton);

        JButton upgradeButton2 = new JButton("Upgrade (50 R$)");
        upgradeButton2.setFont(font1);
        backgroundPanel.add(upgradeButton2);
        itemPanel.add(upgradeButton2);




        upgradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playClickSoundUpgrade();
                if (dinheiro >= 10) {
                    // Ação a ser executada quando o dinheiro for maior que 10
                    System.out.println("Upgrade realizado!");
                    dinheiro = dinheiro - 10;
                    contador = contador + 1;
                    scoreLabel.setText("Dinheiro: " + dinheiro); // Atualizar o texto do JLabel com o valor do contador
                    labelPoderClick.setText("Dinheiro por clique: " + contador);
                } else {
                    // Ação a ser executada quando o dinheiro for menor ou igual a 10
                    System.out.println("Dinheiro insuficiente para o upgrade!");
                }
            }
        });

        upgradeButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playClickSoundLucky();
                if (dinheiro >= 50) {
                    // Ação a ser executada quando o dinheiro for maior ou igual a 50
                    System.out.println("Upgrade realizado!");
                    cancheDeGolpeDaSorte = 1;
                    dinheiro -= 50;
                    scoreLabel.setText("Dinheiro: " + dinheiro);
                    chanceGolpeSorte.setText("Chanche golpe de sorte: " + cancheDeGolpeDaSorte);
                } else {
                    // Ação a ser executada quando o dinheiro for menor que 50
                    System.out.println("Dinheiro insuficiente para o upgrade!");
                }
            }
        });

        // Declaração das variáveis de tamanho original do ícone
        int larguraOriginal = chicken.getIconWidth();
        int alturaOriginal = chicken.getIconHeight();

        chickenButton.addActionListener(new ActionListener() {
            private boolean foiReduzido = false; // Indica se a imagem está reduzida

            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifique se ocorre um golpe de sorte
                double sorte = Math.random();
                if (sorte <= cancheDeGolpeDaSorte) {
                    dinheiro += contador * multiplicadorSorte;
                } else {
                    dinheiro = dinheiro + contador;
                }

                scoreLabel.setText("Pontuação: " + dinheiro);

                if (foiReduzido) {
                    // Restaurar o tamanho original do ícone
                    chickenButton.setIcon(chicken);
                } else {
                    // Reduzir o tamanho do ícone
                    int novaLargura = chicken.getIconWidth() - 10; // Diminua 10 pixels da largura atual
                    int novoTamanho = chicken.getIconHeight() - 10; // Diminua 10 pixels da altura atual
                    if (novaLargura > 0 && novoTamanho > 0) {
                        Image scaledImage = chicken.getImage().getScaledInstance(novaLargura, novoTamanho, Image.SCALE_DEFAULT);
                        chickenButton.setIcon(new ImageIcon(scaledImage));
                    }
                }

                // Inverter o estado de redução da imagem
                foiReduzido = !foiReduzido;
            }
        });
        window.setVisible(true);
    }

    public void playClickSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("chickenSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void playClickSoundLucky() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("luckySound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
