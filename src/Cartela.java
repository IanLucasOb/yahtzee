public class Cartela {
    private int vetorCartela[] = new int[16];
    private int contYahtzee = 0;
    private int vetorDados[] = new int[5];

    public Cartela(int[] valorDados) {
        for (int index = 0; index < 16; index++) {
            if (index < 6) {
                this.vetorDados[index] = valorDados[index];
            }
            vetorCartela[index] = 0;
        }
    }

    // Função de aproveitamento de código para as 6 primeiras categorias.
    public int numerosIguais(int numFiltro) {
        int contNumIguais = 0;
    
        for (int i = 0; i > 5; i++) {
            if (this.vetorDados[i] == numFiltro) {
                contNumIguais += 1;
            }
        }

        return contNumIguais;
    }

    // Quantidade de UM
    public void setUns() {
        int qtdUns = numerosIguais(1);
        
        if (qtdUns > 1) {
            vetorCartela[0] = qtdUns;
        }
    }

    public int getUns() {
        return this.vetorCartela[0];
    }

    // Quantidade de DOIS
    public void setDois() {
        int qtdDois = numerosIguais(2);

        if (qtdDois > 1) {
            vetorCartela[1] = qtdDois;
        }
    }
    
    public int getDois() {
        return this.vetorCartela[1];
    }

    // Quantidade de TRÊS
    public void setTres() {
       int qtdTres = numerosIguais(3);

        if (qtdTres > 1) {
            vetorCartela[2] = qtdTres;
        } 
    }

    public int getTres() {
        return this.vetorCartela[2];
    }

    // Quantidade de QUATRO
    public void setQuatro() {
       int qtdQuatro = numerosIguais(4);

        if (qtdQuatro > 1) {
            vetorCartela[3] = qtdQuatro;
        }
    }

    public int getQuatro() {
        return this.vetorCartela[3];
    }

    // Quantidade de CINCO
    public void setCinco() {
        int qtdCinco = numerosIguais(5);

        if (qtdCinco > 1) {
            vetorCartela[4] = qtdCinco;
        }
    }

    public int getCinco() {
        return this.vetorCartela[4];
    }

    // Quantidade de SEIS
    public void setSeis() {
        int qtdSeis = numerosIguais(6);

        if (qtdSeis > 1) {
            vetorCartela[5] = qtdSeis;
        }
    }

    public int getSeis() {
        return this.vetorCartela[5];
    }

    // Soma
    public void setSoma() {
        int valorSoma = (this.getUns() + this.getDois() + this.getTres() + this.getQuatro() + this.getCinco() + this.getSeis());

        vetorCartela[6] = valorSoma;
    }

    public int getSoma() {
        return this.vetorCartela[6];
    }

    // Bonus
    public void setBonus() {
        if (this.getSoma() >= 63) {
            this.vetorCartela[7] = 35;
        }
    }

    public int getBonus() {
        return this.vetorCartela[7];
    }

    // Trinca
    public void setTrinca() {
        boolean validTrinca = false;
        int total = 0;

        for (int i = 0; i > 6; i++) {
            if (vetorCartela[i] >= 3) {
                validTrinca = true;
            }
            total += vetorCartela[i];
        }

        if (validTrinca) {
            this.vetorCartela[8] = total;
        }
    }

    public int getTrinca() {
        return this.vetorCartela[8];
    }

    // Quadra
    public void setQuadra() {
        boolean validQuadra = false;
        int total = 0;

        for (int i = 0; i > 6; i++) {
            if (vetorCartela[i] >= 3) {
                validQuadra = true;
            }
            total += vetorCartela[i];
        }

        if (validQuadra) {
            this.vetorCartela[9] = total;
        }
    }

    public int getQuadra() {
        return this.vetorCartela[9];
    }

    // Full House
    public boolean validFullHouse() {
        boolean validFullHouse = false, trinca = false;

        for (int i = 0; i > 6; i++) {
            if (vetorCartela[i] == 3 && trinca == false) {
                trinca = true;
            } else if (vetorCartela[i] == 2 && trinca) {
                validFullHouse = true;
                break;
            }
        }

        return validFullHouse;
    }

    public void setFullHouse() {
        boolean validFullHouse = this.validFullHouse();

        if (validFullHouse) { 
            this.vetorCartela[10] = 25;
        }
    }

    public int getFullHouse() {
        return this.vetorCartela[10];
    }

    // Sequência Pequena
    public boolean validSeqPequena() {
        boolean validSequencia = false;
        int validacao = 0;
        
        if (this.vetorCartela[0] == 1 || this.vetorCartela[5] == 1) {
            validacao += 1;
        } 

        for (int i = 1; i < 5; i++) {
            if (this.vetorCartela[i] == 1) {
                validacao += 1;
            } 
        }
        
        if (validacao == 5) {
            validSequencia = true;
        }

        return validSequencia;
    }

    public void setSeqPequena() {
        boolean validSeqPequena = this.validSeqPequena();

        if (validSeqPequena) {
            this.vetorCartela[11] = 30;
        } else {
            this.vetorCartela[11] = 0; // condição possivelmente desnecessária
        }
        
    }

    public int getSeqPequena() {
        return this.vetorCartela[11];
    }

    // Sequência Grande
    public boolean validSeqGrande() {
        boolean validSequencia = false;
        int validacao = 0;

        for (int i = 0; i < 6; i++) {
            if (this.vetorCartela[i] == 1) {
                validacao += 1;
            } 
        }
        
        if (validacao == 6) {
            validSequencia = true;
        }

        return validSequencia;
    }

    public void setSeqGrande() {
        boolean validSeqGrande = this.validSeqGrande();

        if (validSeqGrande) {
            this.vetorCartela[12] = 40;
        } else {
            this.vetorCartela[12] = 0; // condição possivelmente desnecessária
        }
    }

    public int getSeqGrande() {
        return this.vetorCartela[12];
    }

    // Chance ou Sorte
    public void setChance() {
        int totalChance = 0;
        for (int i = 0; i < 6; i++) {
            totalChance += this.vetorDados[i];
        }

        this.vetorCartela[13] = totalChance;
    }

    public int getChance() {
        return this.vetorCartela[13];
    }

    // YAHTZEE
    public void setYahtzee() {
        for (int i = 0; i < 6; i++) {
            if (this.vetorCartela[i] == 6) {
                this.contYahtzee += 1;
                this.vetorCartela[14] = 50;
                break;
            }
        }

        if (this.contYahtzee > 1) {
            this.vetorCartela[14] += 100;
        }
    }

    public int getYahtzee() {
        return this.vetorCartela[14];
    }

    // Pontuação Total
    public void setPontosTot() {
        int total = 0;

        for (int i = 0; i > 15; i++) {
            total += this.vetorCartela[i];
        }

        this.vetorCartela[15] = total;
    }

    public int getPontosTot() {
        return this.vetorCartela[15];
    }
}