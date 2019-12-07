package ta.te.ti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JugadorVirtual {
    private List<Tablero> tableros;
    private Marca marca;

    public JugadorVirtual(Marca marca, boolean empiezaoYo) {
        this.marca = marca;
        tableros = new LinkedList();
        //tableros = new ArrayList();
        Marca m = marca;
        if (!empiezaoYo){
            m = m.getOpuesta();
        }
        crearArbolDeJugadas(new Tablero(), m);
    }
    
    private void crearArbolDeJugadas(Tablero t, Marca m) {
        if (t.estaLleno() || t.getGanador() != Marca.VACIO) {
            tableros.add(t);
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (t.esVacia(i, j)) {
                    t.set(i, j, m);
                    crearArbolDeJugadas(new Tablero(t), m.getOpuesta());
                    t.set(i, j, Marca.VACIO);
                }
            }
        }
    }
    
    public String jugarTurno(Tablero tablero){
        if (tablero.estaLleno() || !tablero.marcaGano(Marca.VACIO)){
            throw new RuntimeException("El jugador virtual no puede hacer su jugada porque el tablero está lleno o ya hay un ganador");
        }
        Map<String, Integer> posibilidades = new TreeMap();
        ArrayList<Integer> remover = new ArrayList();
        int idRemover = 0;
        for (Tablero t : tableros){
            boolean descartar = !empiezanIgual(tablero.getSecuencia(), t.getSecuencia());
            if (descartar){
                //remover.add(idRemover);
            }else{
                try {
                    String prox = t.getSecuencia().get(tablero.getSecuencia().size());
                    Marca tGanador = t.getGanador();
                    int pesoDesicion = 0;
                    
                    if (posibilidades.containsKey(prox)) {
                        pesoDesicion = posibilidades.get(prox);
                    } else {
                        pesoDesicion = 0;
                    }
                    
                    if (tGanador == marca) {
                        // Si el ganador soy yo
                        pesoDesicion += 2;
                    } else if (tGanador == Marca.VACIO) {
                        // si empataramos
                        pesoDesicion += 1;
                    } else {
                        // si pierdo
                        pesoDesicion -= 1;
                    }
                    
                    posibilidades.put(prox, pesoDesicion);
                    
                } catch (Exception e) {
                }
            }
            idRemover++;
        }
        /*
        // el siguiente for deberia liberar memoria que no se va a usar mas,
        // pero sale mas a cuenta desperdiciarla que liberarla porque demora mucho en hacerlo
        // si se quiere utilizar, descomentar la linea "remover.add(idRemover);" 
        for (int i = remover.size()-1; i >= 0; i--){
            int borrar = remover.get(i);
            tableros.remove(borrar);
        }
        */
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : posibilidades.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        
        
        for (Map.Entry<String, Integer> entry : new TreeMap<>(posibilidades).entrySet()) {
            if (entry.getValue() < max) {
                posibilidades.remove(entry.getKey());
            }
        }

        int random = (int) (Math.random() * posibilidades.size());
        String jugada = "??";
        for (Map.Entry<String, Integer> entry : posibilidades.entrySet()) {
            if (random == 0) {
                jugada = entry.getKey();
                break;
            }
            random--;
        }
        int x = Integer.valueOf("" + jugada.charAt(0));
        int y = Integer.valueOf("" + jugada.charAt(1));
        tablero.set(x, y, marca);
        return jugada;
    }
    
    private boolean empiezanIgual(List<String> a1, List<String> a2){
        if (a1.size() > a2.size()) return false;
        for (int i = 0; i < a1.size(); i++){
            if (!a1.get(i).equals(a2.get(i))){
                return false;
            }
        }
        return true;
    }
    
}
