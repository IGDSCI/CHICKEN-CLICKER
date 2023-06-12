import java.util.Timer;
import java.util.TimerTask;

public abstract class Ajudante {
    protected Status status;
    protected int pontosPorSegundo;
    protected Timer timer;

    public Ajudante(Status status) {
        this.status = status;
    }

    public abstract void comprar();

    public void iniciarProducao() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                status.adicionarPontos(pontosPorSegundo);
            }
        }, 1000, 1000);
    }

    public void pararProducao() {
        if (timer != null) {
            timer.cancel();
        }
    }
}