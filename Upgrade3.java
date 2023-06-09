import java.util.ArrayList;

public class Upgrade3 extends Upgrade {
    private Status status;
    private ArrayList<Upgrade> upgradesComprados;

    public Upgrade3(Status status, ArrayList<Upgrade> upgradesComprados) {
        this.status = status;
        this.upgradesComprados = upgradesComprados;
        this.custo = 20;
        this.nivel = 0;
    }

    public void funcaoUpgrade() {
        if (this.status.getDinheiro() > this.custo) {
            int novoGolpeDeSorte = this.status.getGolpeDeSorte() + 1;
            this.status.setGolpeDeSorte(novoGolpeDeSorte);
            int novaPontuacao = this.status.getDinheiro() - this.custo;
            this.status.setDinheiro(novaPontuacao);
            ++this.nivel;
            this.custo += 20;
            upgradesComprados.add(this); // Adiciona o próprio upgrade à lista
        }

    }
}
