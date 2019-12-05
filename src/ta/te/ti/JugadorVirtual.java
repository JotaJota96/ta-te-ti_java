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
    
    public void jugarTurno(Tablero tablero){
        Map<String, Integer> posibilidades = new TreeMap();
        ArrayList<Integer> remover = new ArrayList();
        int idRemover = 0;
        for (Tablero t : tableros){
            boolean descartar = !empiezanIgual(tablero.getSecuencia(), t.getSecuencia());
            if (descartar){
                remover.add(idRemover);
            }else{
                try {
                    String prox = t.getSecuencia().get(tablero.getSecuencia().size());
                    if (t.getGanador() != marca.getOpuesta()) {
                        if (posibilidades.containsKey(prox)) {
                            int nuevoValor = posibilidades.get(prox) + 1;
                            posibilidades.replace(prox, nuevoValor);
                        } else {
                            posibilidades.put(prox, 1);
                        }
                    }
                } catch (Exception e) {
                }
            }
            idRemover++;
        }
        /*
        for (int i = remover.size()-1; i >= 0; i--){
            int borrar = remover.get(i);
            tableros.remove(borrar);
        }
        */
        int max = 0;
        String jugada = "??";
        for (Map.Entry<String, Integer> entry : posibilidades.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > max){
                jugada = key;
                max = value;
            }
        }
        int x = Integer.valueOf("" + jugada.charAt(0));
        int y = Integer.valueOf("" + jugada.charAt(1));
        tablero.set(x, y, marca);
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
