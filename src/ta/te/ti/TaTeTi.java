package ta.te.ti;

public class TaTeTi {
    
    static int contador = 0;
    
    public static void main(String[] args) {
        Tablero t = new Tablero();
        test(t, obtenerLaOtraMarca(' '));
    }

    public static void test(Tablero t, char c) {
        if (t.estaLleno()) {
            System.out.println(++contador + " -> " + t.toString(0));
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (t.esVacia(i, j)) {
                    t.set(i, j, c);
                    test(t, obtenerLaOtraMarca(c));
                    t.set(i, j, ' ');

                }
            }
        }
    }

    private static char obtenerLaOtraMarca(char c) {
        if (c == 'X') {
            return 'O';
        }
        return 'X';
    }

}
