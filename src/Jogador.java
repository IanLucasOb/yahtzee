public class Jogador {
    private String nome;
    private int pontuacaoJogador[] = new int[16];
    private GerenciadorArquivos geranciador = new GerenciadorArquivos();

    public Jogador() {
        for (int i = 0; i < 16; i++) {
            this.pontuacaoJogador[i] = 0;
        }
    }

    public void setNome(String nome) { // Atribui um nome ao jogador e o cadastra permanentemente.
        this.nome = nome;
        geranciador.gravarJogador(nome);
    }

    public String getNome() { // Pega o nome do jogador.
        return this.nome;
    }

    public void setPontuacaoJogador(int indice, int valor) { // Atribui um valor ao indice indicado na lista de pontuação do jogador.
        this.pontuacaoJogador[indice] = valor;
    }

    public int[] getPontuacaoJogador() { // Pega a lista de pontuação do jogador.
        return this.pontuacaoJogador;
    }

    public String getResultado() {
        String resultado = "";
        for (int i = 0; i < 16; i++) {
            resultado += this.pontuacaoJogador[i] + " ";
        }
        return this.nome + " | " + resultado;
    }
}
