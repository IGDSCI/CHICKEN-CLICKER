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

    public static void carregarDados(Status status) {
        String arquivoDados = "dados_usuario.txt";
        try {
            FileReader reader = new FileReader(arquivoDados);
            BufferedReader bufferedReader = new BufferedReader(reader);

            status.setNomeJogador(bufferedReader.readLine());
            status.setDinheiro(Integer.parseInt(bufferedReader.readLine()));
            status.setDinheiroPorClique(Integer.parseInt(bufferedReader.readLine()));
            status.setChanceDeGolpeDeSorte(Double.parseDouble(bufferedReader.readLine()));
            status.setGolpeDeSorte(Integer.parseInt(bufferedReader.readLine()));

            bufferedReader.close();
            System.out.println("Dados carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados do jogador: " + e.getMessage());
        }
    }
}
