public class Jogador {
    private String nome;
    private int pontuacaoJogador[] = new int[16];

    public Jogador(){
        for(int i = 0; i < 16; i++){
            pontuacaoJogador[i] = 0;
        }
    }
    
    public void setNome(String nome){ // Atribui um nome ao jogador.
        this.nome = nome;
    }

    public String getNome(){ // Pega o nome do jogador.
        return this.nome;
    }

    public void setPontuacaoJogador(int indice, int valor){ // Atribui um valor ao indice indicado na lista de pontuação do jogador. 
        this.pontuacaoJogador[indice] = valor;
    }
    
    public int[] getPontuacaoJogador(){ // Pega a lista de pontuação do jogador
        return this.pontuacaoJogador;
    }
}
