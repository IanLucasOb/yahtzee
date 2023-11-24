import java.util.Random;

public class Dado {
    private int valorFace;

    private int vetorDados[] = new int[5];
    Random gerador = new Random();
    private int vetorIndex[];

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

    // valida a reRolagem de dados
    public boolean validReRolagem(String[] vetorIndexString) {
        boolean indexRepet = false, validReRolagem = false;
        this.vetorIndex = new int[vetorIndexString.length];

        for (int teste = 0; teste < vetorIndexString.length; teste++) {
            try {
                vetorIndex[teste] = Integer.parseInt(vetorIndexString[teste]) - 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // return false;
            }
        }

        for (int num = 0; num < vetorIndex.length; num++) {
            System.out.println(vetorIndex.length);
            if (vetorIndex[num] > 6 || vetorIndex[num] < 0) {
                System.out.println("| Erro: Dados informados fora da lista esperada e permitida.");
                break;
            } else if (vetorIndex.length > 1) {
                for (int num2 = 0; num2 < 4; num2++) {
                    if (num == num2) {
                        continue;
                    } else if (vetorIndex[num] == vetorIndex[num2]) {
                        indexRepet = false;
                        System.out.println("| Erro: Dados informados repetidamente.");
                        break;
                    } else {
                        validReRolagem = true;
                    }
                    if (indexRepet == false) {
                    break;
                    }
                }
            } else {
                validReRolagem = true; 
            }
        }
        return validReRolagem;
    }

    // reRola os dados de acordo com os Indexes dentro do vetor
    public boolean reRolarDados(String vetorIndexString[]) {
        boolean validReRolagem = new Dado().validReRolagem(vetorIndexString);

        if (validReRolagem) {
            for (int i = 0; i < this.vetorIndex.length; i++) {
                this.vetorDados[vetorIndex[i]] = this.gerador.nextInt(6) + 1;
            }                 
        }

        return validReRolagem;
    }
}