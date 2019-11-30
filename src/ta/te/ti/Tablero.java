package ta.te.ti;

public class Tablero {

    private static final int TAMANIO = 3;
    char tablero[][];

    public Tablero() {
        tablero = new char[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                this.tablero[i][j] = ' ';
            }
        }
    }
    
    public char[][] getTablero() {
        char tab[][] = new char[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                tab[i][j] = this.tablero[i][j];
            }
        }
        return tab;
    }

    public void set(int x, int y, char c) {
        if (x < 0 || TAMANIO <= x || y < 0 || TAMANIO <= y) {
            throw new RuntimeException("Coordenada [x,y] = ["+ x +"," + y + "] fuera de rango");
        }
        tablero[x][y] = c;
    }
    
    public boolean esVacia(int x, int y){
        return this.tablero[x][y] == ' ';
    }
    
    public boolean estaLleno(){
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (esVacia(i, j)){
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        String ret = "{";
        for (int i = 0; i < TAMANIO; i++) {
            ret += "{";
            for (int j = 0; j < TAMANIO; j++) {
                ret += this.tablero[i][j];
                if (j + 1 < TAMANIO) {
                    ret += ", ";
                }
            }
            ret += "}";
            if (i + 1 < TAMANIO) {
                ret += ", ";
            }
        }
        ret += "}";
        return ret;
    }

    public String toString(int modo) {
        String ret = "";

        switch (modo) {
            case 0:
                ret = this.toString();
                break;
            case 1:
                ret += lineaHorizontal() + "\n";
                for (int i = 0; i < TAMANIO; i++) {
                    for (int j = 0; j < TAMANIO; j++) {
                        ret += "| " + this.tablero[i][j] + " ";
                    }
                    ret += "|\n";
                    ret += lineaHorizontal() + "\n";
                }
                break;
            case 2:
                for (int i = 0; i < TAMANIO; i++) {
                    for (int j = 0; j < TAMANIO; j++) {
                        ret += this.tablero[i][j] + ":";
                    }
                    ret += ";";
                }
                break;
        }
        return ret;
    }

    private String lineaHorizontal() {
        String ret = "";
        for (int i = 0; i < TAMANIO; i++) {
            ret += "+---";
        }
        ret += "+";
        return ret;
    }
}
