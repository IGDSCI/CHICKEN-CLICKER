import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.Timer;
import java.util.Scanner;

public class Main {
    private int clickCount = 0;
    private int score = 0;
    private JLabel scoreLabel;
    Font font1, font2;
    JButton button1, button2, button3, button4;
    static int dinheiro = 1;
    int contador = 1;
    private double multiplicadorSorte = 3.0;
    private double cancheDeGolpeDaSorte = 0.25;
    private int upgrade1 = 10;
    private int upgrade2 = 50;
    private Timer timer;
    private int pontosPorSegundo = 0;
    private boolean pintinhoComprado = false;
    private int precoPintinho = 100;



    static String nomeJogador = JOptionPane.showInputDialog("Digite o seu nome:");

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        carregarDados();
        createFont();
        createUI();
        salvarDados();

    }

    public void salvarDados() {
        String arquivoDados = "dados_usuario.txt";

        try {
            FileWriter writer = new FileWriter(arquivoDados);
            writer.write(nomeJogador + "\n");
            writer.write(dinheiro + "\n");
            writer.write(contador + "\n");
            writer.write(cancheDeGolpeDaSorte + "\n");
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

            nomeJogador = bufferedReader.readLine();
            dinheiro = Integer.parseInt(bufferedReader.readLine());
            contador = Integer.parseInt(bufferedReader.readLine());
            cancheDeGolpeDaSorte = Double.parseDouble(bufferedReader.readLine());
            upgrade1 = Integer.parseInt(bufferedReader.readLine());
            upgrade2 = Integer.parseInt(bufferedReader.readLine());
            
            bufferedReader.close();
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
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
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dinheiro += pontosPorSegundo;
                scoreLabel.setText("Pontuação: " + dinheiro);
                salvarDados();
            }
        });
        timer.setInitialDelay(0); // Inicia o temporizador imediatamente
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



        scoreLabel = new JLabel("Pontuação: "+ dinheiro);
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
                    dinheiro += contador;
                }

                scoreLabel.setText("Pontuação: " + dinheiro);
                salvarDados();
            }
        });

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(680, 280, 300, 125);
        itemPanel.setBackground(Color.white);
        itemPanel.setLayout(new GridLayout(2, 1));
        backgroundPanel.add(itemPanel);

        JButton  upgradeButton = new JButton("Upgrade (R$" + upgrade1 + ")");
        upgradeButton.setFont(font1);
        upgradeButton.setVisible(false); // Esconder o botão inicialmente
        backgroundPanel.add(upgradeButton);
        itemPanel.add(upgradeButton);

        JButton upgradeButton2 = new JButton("Upgrade (R$" + upgrade2 + ")");
        upgradeButton2.setFont(font1);
        upgradeButton2.setVisible(false); // Esconder o botão inicialmente
        backgroundPanel.add(upgradeButton2);
        itemPanel.add(upgradeButton2);

        JPanel itemPanel2 = new JPanel();
        itemPanel2.setBounds(680, 460, 300, 125); // Defina as coordenadas corretas para ficar abaixo do itemPanel
        itemPanel2.setBackground(Color.white);
        itemPanel2.setLayout(new GridLayout(2, 1));
        backgroundPanel.add(itemPanel2);

// Crie os botões de upgrade para o itemPanel2
        JButton pintinhoButton = new JButton("Pintinho R$ " + precoPintinho);
        pintinhoButton.setFont(font1);
        pintinhoButton.setVisible(false);
        itemPanel2.add(pintinhoButton);
        if (dinheiro >= precoPintinho) {
            pintinhoButton.setEnabled(true);
        } else {
            pintinhoButton.setEnabled(false);
        }

        JButton upgradeButton4 = new JButton(" Ajudante 2");
        upgradeButton4.setFont(font1);
        upgradeButton4.setVisible(false); // Esconder o botão inicialmente
        itemPanel2.add(upgradeButton4);






        upgradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playClickSoundUpgrade();
                if (dinheiro >= upgrade1) {
                    System.out.println("Upgrade realizado!");
                    dinheiro -= upgrade1;
                    contador++;
                    upgrade1 += 60;
                    scoreLabel.setText("Pontuação: " + dinheiro);
                    labelPoderClick.setText("Dinheiro por clique: " + contador);
                    upgradeButton.setText("Upgrade (R$" + upgrade1 + ")");
                    salvarDados();
                } else {
                    System.out.println("Dinheiro insuficiente para o upgrade!");
                }
            }
        });

        upgradeButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playClickSoundLucky();
                if (dinheiro >= upgrade2) {
                    System.out.println("Upgrade realizado!");
                    dinheiro -= upgrade2;
                    cancheDeGolpeDaSorte = 1.0;
                    upgrade2 += 80;
                    scoreLabel.setText("Dinheiro: " + dinheiro);
                    chanceGolpeSorte.setText("Chanche golpe de sorte: " + cancheDeGolpeDaSorte);
                    upgradeButton2.setText("Upgrade (R$" + upgrade2 + ")");
                    salvarDados();
                } else {
                    System.out.println("Dinheiro insuficiente para o upgrade!");
                }
            }

            public void playClickSoundPintinho() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("pintinhoSound.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        JButton upgradeMenuButton = new JButton("Melhorias");
        upgradeMenuButton.setBounds(680, 240, 300, 40);
        upgradeMenuButton.setFont(font2);
        backgroundPanel.add(upgradeMenuButton);

        JButton upgradeMenuButton2 = new JButton("Ajudantes");
        upgradeMenuButton2.setBounds(680, 420, 300, 40);
        upgradeMenuButton2.setFont(font2);
        backgroundPanel.add(upgradeMenuButton2);

        pintinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dinheiro >= precoPintinho) {
                    dinheiro -= precoPintinho;
                    pintinhoButton.setEnabled(false);
                    pintinhoButton.setText("Pintinho (Comprado)");
                    pontosPorSegundo += 3;
                    timer.start();
                    pintinhoComprado = true;
                    scoreLabel.setText("Pontuação: " + dinheiro);
                    salvarDados();
                } else {
                    JOptionPane.showMessageDialog(null, "Dinheiro insuficiente para comprar o pintinho!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        upgradeMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradeButton.setVisible(true); // Mostrar o botão de upgrade 1
                upgradeButton2.setVisible(true); // Mostrar o botão de upgrade 2
                pintinhoButton.setVisible(false); // Esconder o botão de ajudante 1
                upgradeButton4.setVisible(false); // Esconder o botão de ajudante 2
            }
        });
        upgradeMenuButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradeButton.setVisible(false); // Esconder o botão de upgrade 1
                upgradeButton2.setVisible(false); // Esconder o botão de upgrade 2
                pintinhoButton.setVisible(true); // Mostrar o botão de ajudante 1
                upgradeButton4.setVisible(true); // Mostrar o botão de ajudante 2
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

                if (pintinhoComprado) {
                    pintinhoButton.setEnabled(false); // Desabilita o botão se o pintinho já foi comprado
                    pintinhoButton.setText("Pintinho (Comprado)");
                } else {
                    pintinhoButton.setEnabled(true); // Habilita o botão se o pintinho ainda não foi comprado
                    pintinhoButton.setText("Pintinho");
                }

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
