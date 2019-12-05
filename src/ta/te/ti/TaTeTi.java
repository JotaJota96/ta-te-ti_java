package ta.te.ti;

public class TaTeTi {
    
    public static void main(String[] args) {
        //JugadorVirtual jv = new JugadorVirtual(Marca.X);
        jugarPartidaContraPC();
    }
    
    public static void jugarPartida(){
        Tablero t = new Tablero();
        Marca m = Marca.X;
        
        while (!t.estaLleno() && t.getGanador() == Marca.VACIO) {
            System.out.println("Turno de " + m);
            System.out.println(t.toString(1));
            String s = new java.util.Scanner(System.in).nextLine();
            try {
                int x = Integer.valueOf("" + s.charAt(0));
                int y = Integer.valueOf("" + s.charAt(1));
                t.set(x, y, m);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        System.out.println(t.toString(1));
        System.out.println("Ha ganado " + t.getGanador());
    }
    
    public static void jugarPartidaContraPC(){
        Tablero t = new Tablero();
        Marca m = Marca.X;
        Marca miMarca = m;
        JugadorVirtual jv = new JugadorVirtual(m.getOpuesta(), false);
        
        while (!t.estaLleno() && t.getGanador() == Marca.VACIO) {
            System.out.println("Turno de " + m);
            System.out.println(t.toString(1));
            
            if (m == Marca.X) {
                System.out.print("Ingrese coordenada: ");
                String s = new java.util.Scanner(System.in).nextLine();
                if (s.equalsIgnoreCase("salir")){
                    break;
                }
                try {
                    int x = Integer.valueOf("" + s.charAt(0));
                    int y = Integer.valueOf("" + s.charAt(1));
                    t.set(x, y, m);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else {
                System.out.println("Decidiendo jugada...");
                jv.jugarTurno(t);
            }
            m = m.getOpuesta();
        }
        System.out.println(t.toString(1));
        if (t.getGanador() == miMarca){
            System.out.println("Has ganado");
        }else if (t.getGanador() != Marca.VACIO){
            System.out.println("Has perdido");
        }else{
            System.out.println("Empate");
        }
    }
    
    
}
