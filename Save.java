import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    public static void salvarNomeJogador(String nomeJogador, int dinheiro, int dinheiroPorClique) {
        String arquivoDados = "dados_usuario.txt";
        try {
            FileWriter writer = new FileWriter(arquivoDados);
            writer.write(nomeJogador + "\n");
            writer.write(dinheiro + "\n");
            writer.write(dinheiroPorClique + "\n");

            writer.close();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados do jogador: " + e.getMessage());
        }
    }

    public static String carregarNomeJogador() {
        try {
            String arquivoDados = "dados_usuario.txt";
            FileReader reader = new FileReader(arquivoDados);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String nomeJogador = bufferedReader.readLine();
            int dinheiro = Integer.parseInt(bufferedReader.readLine());
            int dinheiroPorClique = Integer.parseInt(bufferedReader.readLine());

            bufferedReader.close();
            System.out.println("Nome do jogador carregado com sucesso!");
            return nomeJogador;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o nome do jogador: " + e.getMessage());
        }
        return null;
    }
}
