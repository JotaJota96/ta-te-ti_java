package ta.te.ti;

public enum Marca {
    X(),
    O(),
    VACIO();

    public Marca getOpuesta() {
        switch (this) {
            case X:
                return O;
            case O:
                return X;
            default:
                return VACIO;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case X:
                return "X";
            case O:
                return "O";
            default:
                return " ";
        }
    }
    
    
    
}
