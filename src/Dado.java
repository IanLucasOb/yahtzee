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
        boolean indexRepet = false, validReRolagem = false, foraDoLimite = false, naoEhNumero = false;
        this.vetorIndex = new int[vetorIndexString.length];

        for (int teste = 0; teste < vetorIndexString.length; teste++) {
            try {
                this.vetorIndex[teste] = Integer.parseInt(vetorIndexString[teste]) - 1;
            } catch (NumberFormatException e) {
                // e.printStackTrace();
                System.out.println("| Erro: Os dados informados, não são números.");
                naoEhNumero = true;
                break;
            }
        }

        if (vetorIndexString.length < 6 && !naoEhNumero) {
            for (int num = 0; num < this.vetorIndex.length; num++) {
                if (this.vetorIndex[num] > 6 || this.vetorIndex[num] < 0) {
                    System.out.println("| Erro: Dados informados fora da lista esperada e permitida.");
                    foraDoLimite = true;
                    break;

                } else if (this.vetorIndex.length > 1) {
                    for (int k = num+1; k < this.vetorIndex.length; k++) {
                        if (k != num && this.vetorIndex[k] == this.vetorIndex[num]) {
                            indexRepet = true;
                            break;
                        }
                    }
    
                    if (indexRepet == true) {
                        System.out.println("| Erro: Dados informados repetidamente.");
                        break;
                    } else {
                        validReRolagem = true;
                    }

                } else if (!naoEhNumero){
                    validReRolagem = true; 
                }
            }

        } else if (naoEhNumero) {
            validReRolagem = false;

        } else {
            System.out.println("| Erro: Quantidade de dados informados maior que 5.");
        }

        // reRola os dados de acordo com os Indexes dentro do Vetor
        if (!foraDoLimite && !indexRepet && !naoEhNumero && validReRolagem) {
            for (int i = 0; i < this.vetorIndex.length; i++) {
                this.vetorDados[this.vetorIndex[i]] = this.gerador.nextInt(6) + 1;
            }                 
        }

        return validReRolagem;
    }
}