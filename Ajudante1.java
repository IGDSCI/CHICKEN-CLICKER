public class Ajudante1 extends Ajudante {
    public Ajudante1(Status status) {
        super(status);
        this.pontosPorSegundo = 3;
    }

    @Override
    public void comprar() {
        iniciarProducao();
    }
}