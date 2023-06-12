import java.util.ArrayList;

public class Upgrade2 extends Upgrade {
    private Status status;
    private ArrayList<Upgrade> upgradesComprados;

    public Upgrade2(Status status, ArrayList<Upgrade> upgradesComprados) {
        this.status = status;
        this.upgradesComprados = upgradesComprados;
        this.custo = 20;
        this.nivel = 0;
    }

    public void funcaoUpgrade() {
        if (this.status.getDinheiro() > this.custo && this.status.getChancheGolpeDeSorte() != 1.0) {
            double novaChancheDeGolpeDeSorte = this.status.getChancheGolpeDeSorte() + 0.010;
            this.status.setChanceDeGolpeDeSorte(novaChancheDeGolpeDeSorte);
            int novaPontuacao = this.status.getDinheiro() - this.custo;
            this.status.setDinheiro(novaPontuacao);
            ++this.nivel;
            this.custo += 20;
            upgradesComprados.add(this); // Adiciona o próprio upgrade à lista
            playClickSoundUpgrade();
        }

    }
}
