package ta.te.ti;

import java.util.ArrayList;

public class Tablero {

    private static final int TAMANIO = 3;
    private Casilla tablero[][];

    public Tablero() {
        tablero = new Casilla[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                this.tablero[i][j] = new Casilla(Marca.VACIO, 0);
            }
        }
    }
    public Tablero(Tablero t) {
        this.tablero = t.getTablero();
    }
    
    public Casilla[][] getTablero() {
        Casilla tab[][] = new Casilla[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                tab[i][j] = new Casilla(this.tablero[i][j]);
            }
        }
        return tab;
    }

    public ArrayList<String> getSecuencia() {
        ArrayList<String> ret = new ArrayList();
        int i = 1;
        while (true){
            String s = getSecuencia(i);
            if (s.equals("")) break;
            ret.add(s);
            i++;
        }
        return ret;
    }
    public String getSecuencia(int k) {
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (tablero[i][j].getOrden() == k){
                    return "" + i + j;
                }
            }
        }
        return "";
    }
    
    public void set(int x, int y, Marca m) {
        if (x < 0 || TAMANIO <= x || y < 0 || TAMANIO <= y) {
            throw new RuntimeException("Coordenada [x,y] = ["+ x +"," + y + "] fuera de rango");
        }
        if (m != Marca.VACIO && !esVacia(x, y)){
            throw new RuntimeException("Ya hay una marca en la posición ["+ x +"," + y + "]");
        }
        if (m == Marca.VACIO && esVacia(x, y)){
            return;
        }
        if (m == Marca.VACIO){
            int orden = tablero[x][y].getOrden();
            tablero[x][y].setOrden(0);
            tablero[x][y].setMarca(Marca.VACIO);

            for (int i = 0; i < TAMANIO; i++) {
                for (int j = 0; j < TAMANIO; j++) {
                    if (tablero[i][j].getOrden() > orden){
                        tablero[i][j].setOrden(tablero[i][j].getOrden() -1);
                    }
                }
            }
        }else{
            int orden = getSecuencia().size() + 1;
            tablero[x][y] = new Casilla(m, orden);
        }
    }
    
    public boolean esVacia(int x, int y){
        return this.tablero[x][y].getMarca() == Marca.VACIO;
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
    
    public Marca getGanador(){
        if (marcaGano(Marca.X)) return Marca.X;
        if (marcaGano(Marca.O)) return Marca.O;
        return Marca.VACIO;
    }
    
    public boolean marcaGano(Marca m){
        boolean hayCoincidencia;
        // verifica filas
        for (int i = 0; i < TAMANIO; i++) {
            hayCoincidencia = true;
            for (int j = 0; j < TAMANIO; j++) {
                if (tablero[i][j].getMarca() != m){
                    hayCoincidencia = false;
                    break;
                }
            }
            if (hayCoincidencia){
                return true;
            }
        }
        
        // verifica columnas
        // mismo codigo pero invierto el orden de los for, afuera 'j' y adentro 'i'
        for (int j = 0; j < TAMANIO; j++) {
            hayCoincidencia = true;
            for (int i = 0; i < TAMANIO; i++) {
                if (tablero[i][j].getMarca() != m) {
                    hayCoincidencia = false;
                    break;
                }
            }
            if (hayCoincidencia) {
                return true;
            }
        }
        // verifica diagonales: diagonal arriba-izquierda hacia abajo-derecha
        hayCoincidencia = true;
        for (int i = 0; i < TAMANIO; i++) {
            if (tablero[i][i].getMarca() != m) {
                hayCoincidencia = false;
                break;
            }
        }
        if (hayCoincidencia) {
            return true;
        }
        // verifica diagonales: diagonal arriba-derecha hacia abajo-izquierda
        hayCoincidencia = true;
        for (int i = 0; i < TAMANIO; i++) {
            if (tablero[i][TAMANIO-1 - i].getMarca() != m) {
                hayCoincidencia = false;
                break;
            }
        }
        if (hayCoincidencia) {
            return true;
        }
        
        
        return false;
    }
    
    //************ toString()'s.... *******************************************
    @Override
    public String toString() {
        String ret = "{";
        for (int i = 0; i < TAMANIO; i++) {
            ret += "{";
            for (int j = 0; j < TAMANIO; j++) {
                ret += this.tablero[i][j].getMarca();
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
                        ret += "| " + this.tablero[i][j].getMarca() + " ";
                    }
                    ret += "|\n";
                    ret += lineaHorizontal();
                    if (i+1 < TAMANIO){
                        ret += "\n";
                    }
                }
                break;
            case 2:
                for (int i = 0; i < TAMANIO; i++) {
                    for (int j = 0; j < TAMANIO; j++) {
                        ret += this.tablero[i][j].getMarca() + ":";
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
