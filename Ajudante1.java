public class Ajudante1 extends Ajudante {
    public Ajudante1(Status status, int custo) {
        super(status);
        this.pontosPorSegundo = 3;
        this.custo = custo;
    }

    @Override
    public void comprar() {
        iniciarProducao();
    }
}