package ta.te.ti;

public class TaTeTi {
    
    public static void main(String[] args) {
        Tablero t = new Tablero();
        int cont = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                t.set(i, j, String.valueOf(cont++).charAt(0));
            }
        }
        System.out.println(t.toString(0));
        System.out.println();
        System.out.println(t.toString(1));
        System.out.println();
        System.out.println(t.toString(2));
        System.out.println();
        
    }
    
}
