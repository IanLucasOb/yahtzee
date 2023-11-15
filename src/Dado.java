import java.util.Random;

public class Dado {
    private int valorFace;

    // public Dado() {
    //     this.rolar();
    // } seria o certo fazer no construtor?
    
    public void rolar() {
        Random gerador = new Random();
        this.valorFace = gerador.nextInt(6) + 1;
    }

    public int getValor() {
        return this.valorFace;
    }
}
