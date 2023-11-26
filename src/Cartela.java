public class Cartela {
    private int vetorCartela[] = new int[16];
    private int categoriasSelecionadas[] = new int[16];
    // private int vetorTempory[] = new int[16]; // ainda pensando na utilidade de
    // um vetor temporario
    private int contYahtzee = 0;
    private int vetorDados[] = new int[5];

    public Cartela() {
        for (int index = 0; index < 16; index++) {
            vetorCartela[index] = 0;
        }
    }

    // Atribui os dados
    public void setVetorDados(int valorDado[]) {
        for (int i = 0; i < 5; i++) {
            this.vetorDados[i] = valorDado[i];
        }
    }

    // Pega a cartela completa
    public int[] getCartela() {
        return vetorCartela;
    }

    // seta a cartela completa
    public void setCartela(int indice) { // utilizado ára testar em um código separado
        switch (indice) {
            case 0:
                this.setUns();
                break;

            case 1:
                this.setDois();
                break;

            case 2:
                this.setTres();
                break;

            case 3:
                this.setQuatro();
                break;

            case 4:
                this.setCinco();
                break;

            case 5:
                this.setSeis();
                break;

            case 6:
                this.setSoma();
                break;

            case 7:
                this.setBonus();
                break;

            case 8:
                this.setTrinca();
                break;

            case 9:
                this.setQuadra();
                break;

            case 10:
                this.setFullHouse();
                break;

            case 11:
                this.setSeqPequena();
                break;

            case 12:
                this.setSeqGrande();
                break;

            case 13:
                this.setChance();
                break;

            case 14:
                this.setYahtzee();
                break;

            case 15:
                this.setPontosTot();
                break;

            default:
                break;
        }
    }

    // Função de aproveitamento de código para as 6 primeiras categorias.
    private int numerosIguais(int numFiltro) {
        int contNumIguais = 0;

        for (int i = 0; i <= 4; i++) {
            if (this.vetorDados[i] == numFiltro) {
                contNumIguais += 1;
            }
        }

        return contNumIguais;
    }

    public void setCategoriaSelecionada(int indexCategoria) {
        this.categoriasSelecionadas[indexCategoria] = 1;
    }

    public boolean categoriaJaSelecionada(int indexCategoria) {
        return this.categoriasSelecionadas[indexCategoria] == 1;
    }

    // Quantidade de UM - 1
    public void setUns() {
        int qtdUns = this.numerosIguais(1);
        vetorCartela[0] = (qtdUns * 1);
    }

    public int getUns() {
        return this.vetorCartela[0];
    }

    // Quantidade de DOIS - 2
    public void setDois() {
        int qtdDois = this.numerosIguais(2);
        vetorCartela[1] = (qtdDois * 2);
    }

    public int getDois() {
        return this.vetorCartela[1];
    }

    // Quantidade de TRÊS - 3
    public void setTres() {
        int qtdTres = this.numerosIguais(3);
        vetorCartela[2] = (qtdTres * 3);
    }

    public int getTres() {
        return this.vetorCartela[2];
    }

    // Quantidade de QUATRO - 4
    public void setQuatro() {
        int qtdQuatro = this.numerosIguais(4);
        vetorCartela[3] = (qtdQuatro * 4);
    }

    public int getQuatro() {
        return this.vetorCartela[3];
    }

    // Quantidade de CINCO - 5
    public void setCinco() {
        int qtdCinco = this.numerosIguais(5);
        vetorCartela[4] = (qtdCinco * 5);
    }

    public int getCinco() {
        return this.vetorCartela[4];
    }

    // Quantidade de SEIS - 6
    public void setSeis() {
        int qtdSeis = this.numerosIguais(6);
        vetorCartela[5] = (qtdSeis * 6);
    }

    public int getSeis() {
        return this.vetorCartela[5];
    }

    // Soma - 7
    public void setSoma() {
        int valorSoma = (this.getUns() + this.getDois() + this.getTres() + this.getQuatro() + this.getCinco()
                + this.getSeis());

        vetorCartela[6] = valorSoma;
    }

    public int getSoma() {
        return this.vetorCartela[6];
    }

    // Bonus - 8
    public void setBonus() {
        if (this.getSoma() >= 63) {
            this.vetorCartela[7] = 35;
        }
    }

    public int getBonus() {
        return this.vetorCartela[7];
    }

    // Trinca - 9
    public boolean validTrinca() {
        boolean validTrinca = false;

        if (vetorCartela[0] >= 3 || vetorCartela[1] >= 6 || vetorCartela[2] >= 9 || vetorCartela[3] >= 12
                || vetorCartela[4] >= 15 || vetorCartela[5] >= 13) {
            validTrinca = true;
        }

        return validTrinca;
    }

    public void setTrinca() {
        int total = 0;
        boolean validTrinca = this.validTrinca();

        for (int i = 0; i < 6; i++) {
            total += vetorCartela[i];
        }

        if (validTrinca) {
            this.vetorCartela[8] = total;
        }
    }

    public int getTrinca() {
        return this.vetorCartela[8];
    }

    // Quadra - 10
    public void setQuadra() {
        int total = 0;

        for (int i = 0; i < 6; i++) {
            total += vetorCartela[i];
        }

        if (vetorCartela[0] >= 4 || vetorCartela[1] >= 8 || vetorCartela[2] >= 12 || vetorCartela[3] >= 16
                || vetorCartela[4] >= 20 || vetorCartela[5] >= 24) {
            this.vetorCartela[9] = total;
        } else {
            this.vetorCartela[9] = 0;
        }
    }

    public int getQuadra() {
        return this.vetorCartela[9];
    }

    // Full House - 11
    private boolean validFullHouse() {
        boolean validFullHouse = false;
        boolean trinca = this.validTrinca();

        for (int i = 0; i <= 5; i++) {
            int qtdNum = this.numerosIguais(i + 1);

            if (qtdNum == 2 && trinca) {
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
        } else {
            this.vetorCartela[10] = 0;
        }
    }

    public int getFullHouse() {
        return this.vetorCartela[10];
    }

    // Sequência Pequena - 12
    private boolean validSeqPequena() {
        boolean validSequencia = false;
        boolean condicao1 = (this.vetorCartela[0] == 1 && this.vetorCartela[1] == 2 && this.vetorCartela[2] == 3
                && this.vetorCartela[3] == 4);
        boolean condicao2 = (this.vetorCartela[1] == 2 && this.vetorCartela[2] == 3 && this.vetorCartela[3] == 4
                && this.vetorCartela[4] == 5);
        boolean condicao3 = (this.vetorCartela[2] == 3 && this.vetorCartela[3] == 4 && this.vetorCartela[4] == 5
                && this.vetorCartela[5] == 6);

        if (condicao1 || condicao2 || condicao3) {
            validSequencia = true;
        }

        return validSequencia;
    }

    public void setSeqPequena() {
        boolean validSeqPequena = this.validSeqPequena();

        if (validSeqPequena) {
            this.vetorCartela[11] = 30;
        } else {
            this.vetorCartela[11] = 0;
        }

    }

    public int getSeqPequena() {
        return this.vetorCartela[11];
    }

    // Sequência Grande - 13
    private boolean validSeqGrande() {
        boolean validSequencia = false;
        boolean condicao1 = (this.vetorCartela[0] == 1 || this.vetorCartela[5] == 6);
        boolean condicao2 = (this.vetorCartela[1] == 2 && this.vetorCartela[2] == 3 && this.vetorCartela[3] == 4
                && this.vetorCartela[4] == 5);

        if (condicao1 && condicao2) {
            validSequencia = true;
        }

        return validSequencia;
    }

    public void setSeqGrande() {
        boolean validSeqGrande = this.validSeqGrande();

        if (validSeqGrande) {
            this.vetorCartela[12] = 40;
        } else {
            this.vetorCartela[12] = 0;
        }
    }

    public int getSeqGrande() {
        return this.vetorCartela[12];
    }

    // Chance ou Sorte - 14
    public void setChance() {
        int totalChance = 0;
        for (int i = 0; i <= 4; i++) {
            totalChance += this.vetorDados[i];
        }

        this.vetorCartela[13] = totalChance;
    }

    public int getChance() {
        return this.vetorCartela[13];
    }

    // YAHTZEE - 15
    public void setYahtzee() {
        if (this.vetorCartela[0] == 5 || this.vetorCartela[1] == 10 || this.vetorCartela[2] == 15
                || this.vetorCartela[3] == 20 || this.vetorCartela[4] == 25 || this.vetorCartela[5] == 30) {
            this.vetorCartela[14] = 50;
            this.contYahtzee += 1;
        }

        if (this.contYahtzee > 1) {
            this.vetorCartela[14] += 100;
        }
    }

    public int getYahtzee() {
        return this.vetorCartela[14];
    }

    // Pontuação Total - 16
    public void setPontosTot() {
        System.out.println("passou aqui");
        int total = 0;

        for (int i = 0; i <= 14; i++) {
            total += this.vetorCartela[i];
        }

        this.vetorCartela[15] = total;
    }

    public int getPontosTot() {
        return this.vetorCartela[15];
    }

    /*
     * Retorna uma pontuação específica
     * com base no índice recebido
     */
    public int getPontuacao(int indice) {
        return this.vetorCartela[indice];
    }

    /*
     * Adiciona a pontuação que o jogador selecionou à pontuação total
     */
    public void setPontuacao(int indice) {
        this.vetorCartela[15] += this.getPontuacao(indice);
    }
}