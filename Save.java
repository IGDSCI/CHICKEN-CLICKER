import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static void salvarDados(String nomeJogador, int dinheiro, int dinheiroPorClique, double chanceDeGolpeDeSorte, int golpeDeSorte) {
        String arquivoDados = "dados_usuario.txt";
        try {
            FileWriter writer = new FileWriter(arquivoDados);
            writer.write(nomeJogador + "\n");
            writer.write(dinheiro + "\n");
            writer.write(dinheiroPorClique + "\n");
            writer.write(chanceDeGolpeDeSorte + "\n");
            writer.write(golpeDeSorte + "\n");

            writer.close();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do jogador: " + e.getMessage());
        }
    }

    public static String carregarDados() {
        try {
            String arquivoDados = "dados_usuario.txt";
            FileReader reader = new FileReader(arquivoDados);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String nomeJogador = bufferedReader.readLine();
            int dinheiro = Integer.parseInt(bufferedReader.readLine());
            int dinheiroPorClique = Integer.parseInt(bufferedReader.readLine());
            double chanceDeGolpeDeSorte = Double.parseDouble((bufferedReader.readLine()));
            int golpeDeSorte = Integer.parseInt(bufferedReader.readLine());

            bufferedReader.close();
            System.out.println("Dados carregados com sucesso!");
            return nomeJogador;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o nome do jogador: " + e.getMessage());
        }
        return null;
    }

    public static void salvarUpgrade(int upgrade1, int upgrade2){

    }
}
