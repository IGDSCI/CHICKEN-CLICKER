
public abstract class Upgrade {
    protected int custo;
    protected int nivel;

    public Upgrade() {
    }

    public abstract void funcaoUpgrade();

    public int getNivel() {
        return this.nivel;
    }

    public int getCusto() {
        return this.custo;
    }
}
