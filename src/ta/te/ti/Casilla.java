package ta.te.ti;

public class Casilla {

    private Marca marca;
    private int orden;

    public Casilla(Marca marca, int orden) {
        this.marca = marca;
        this.orden = orden;
    }

    public Casilla(Casilla c) {
        this.marca = c.getMarca();
        this.orden = c.getOrden();
    }
    
    public Marca getMarca() {
        return marca;
    }

    public int getOrden() {
        return orden;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    
}
