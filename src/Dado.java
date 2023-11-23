import java.util.Random;

public class Dado {
    private int valorFace;

    private int vetorDados[] = new int[5];
    Random gerador = new Random();

    // public Dado() {
    //     this.rolar();
    // } seria o certo fazer no construtor? Thiago: Acho que melhor não
    
    public void rolar() {
        Random gerador = new Random();
        this.valorFace = gerador.nextInt(6) + 1;
    }

    public int getValor() {
        return this.valorFace;
    }

    // Ja gera um vetor com 5 dados
    public void setVetor() {
        for (int i = 0; i < 5; i++) {
            this.vetorDados[i] = gerador.nextInt(6) + 1;
        }
    }

    // Pega o vetor 5 dados;
    public int[] getVetor() {
        return this.vetorDados;
    }

    // reRola os dados de acordo com os Indexes dentro do vetor
    public void reRolarDados(int[] vetorIndex) {
        for (int i = 0; i < vetorIndex.length; i++) {
            this.vetorDados[vetorIndex[i]] = this.gerador.nextInt(6) + 1;
        }
    }
}