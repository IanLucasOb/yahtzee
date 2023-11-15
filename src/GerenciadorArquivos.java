import java.io.File; // Torna possível manipular o arquivo;
import java.io.FileNotFoundException; // Identificar erros nos arquivos;
import java.io.BufferedWriter; // Armazena um dado em um Buffer(Quantidade de memória);
import java.io.FileWriter; // Gravar os dados no arquivo .txt;
import java.io.IOException; // Identificar um erro na hora de gravar o arquivo;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private String caminhoDoArquivoDeJogadores = "../db/jogadores.txt"; // Caminho para acessar o arquivo
    private List<String> vetorJogadores = new ArrayList<>();

    public void gravarJogador(String nome) {
        try {   
            File arquivo = new File(caminhoDoArquivoDeJogadores);
            if(!arquivo.exists()) {
                throw new Error("O arquivo " + caminhoDoArquivoDeJogadores + "não existe!");
            } // Se o arquivo não existe irá retornar, ERRO!
            FileWriter escritorDeArquivos = new FileWriter(caminhoDoArquivoDeJogadores, true);
            BufferedWriter escritorBuffer = new BufferedWriter(escritorDeArquivos);

            escritorBuffer.write(nome); 
            escritorBuffer.newLine();
            escritorBuffer.close();

            System.out.println("Jogador cadastrado com sucesso.");
        } catch (IOException e) { // Verifica se há um erro;
            System.out.println("Erro ao cadastrar jogador!");
            e.printStackTrace(); // Vai printar o erro;
        }
    }

    public List<String> getJogadores() {
        this.lerArquivoJogadores();
        return this.vetorJogadores; // Retorna a lista de jogadores;
    }

    private void lerArquivoJogadores() {
        try {
            File arquivo = new File(caminhoDoArquivoDeJogadores);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String data = leitor.nextLine();
                this.vetorJogadores.add(data);
            }

            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro ao ler os jogadores!");
            e.printStackTrace(); // Retorna a lista de jogadores;
        }
    }
}