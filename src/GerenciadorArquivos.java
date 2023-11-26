import java.io.File; // Torna possível manipular o arquivo;
import java.io.FileNotFoundException; // Identificar erros nos arquivos;
import java.io.BufferedWriter; // Armazena um dado em um Buffer(Quantidade de memória);
import java.io.FileWriter; // Gravar os dados no arquivo .txt;
import java.io.IOException; // Identificar um erro na hora de gravar o arquivo;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private String caminhoDoArquivoDeJogadores = "jogadores.txt";
    private String caminhoArquivoResultados = "resultados.txt";
    private ArrayList<String> vetorJogadores = new ArrayList<>();
    private List<String> vetorResultados = new ArrayList<>();

    public void gravarJogador(String nome) {
        this.lerArquivoJogadores();

        if (nome.length() == 0) {
            System.out.println("| Nome nao pode ser vazio! |");
            return;
        }
        // verifica se o jogador já existe
        if (this.vetorJogadores.contains(nome)) { // Percore a lista e verifica se há um valor igual.
            System.out.println("| Jogador ja existe! |");
            return;
        }

        try {
            File arquivo = new File(caminhoDoArquivoDeJogadores);
            // Cria o arquivo caso não exista
            if (!arquivo.exists()) {
                arquivo.createNewFile();
                // throw new Error("O arquivo " + caminhoDoArquivoDeJogadores + "não existe!");
            }
            FileWriter escritorDeArquivo = new FileWriter(caminhoDoArquivoDeJogadores, true);
            BufferedWriter escritorBuffer = new BufferedWriter(escritorDeArquivo);

            escritorBuffer.write(nome);
            escritorBuffer.newLine();
            escritorBuffer.close();

            System.out.println(" | Jogador cadastrado com sucesso. |");
        } catch (IOException e) { // Verifica se há um erro;
            System.out.println("| Erro ao cadastrar jogador! |");
            e.printStackTrace(); // Vai printar o erro;
        }
    }

    public ArrayList<String> getJogadores() {
        this.lerArquivoJogadores();
        return this.vetorJogadores; // Retorna a lista de jogadores;
    }

    private void lerArquivoJogadores() {
        this.vetorJogadores.clear();
        try {
            File arquivo = new File(caminhoDoArquivoDeJogadores);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String data = leitor.nextLine();
                if (data.length() != 0) {
                    this.vetorJogadores.add(data);
                }
            }

            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("| Ocorreu um erro ao ler o arquivo de jogadores! |");
            e.printStackTrace(); // Retorna a lista de jogadores;
        }
    }

    // Grava os resultados de cada jogador e sua cartela
    public void gravarResultados(String resultado) {
        try {
            if (resultado.length() > 0) {
                File arquivo = new File(caminhoArquivoResultados);
                // Cria o arquivo caso não exista
                if (!arquivo.exists()) {
                    arquivo.createNewFile();
                }
                // Permite a escrita em um arquivo
                FileWriter escritorDeArquivo = new FileWriter(arquivo, true);
                // Armazenar os dados em um buffer
                BufferedWriter escritorBuffer = new BufferedWriter(escritorDeArquivo);

                // for (String string : resultados) {
                // Escreve a string no arquivo
                escritorBuffer.write(resultado);
                // Nova linha
                escritorBuffer.newLine();
                // }
                // Fechando a stream
                escritorBuffer.close();
                System.out.println("| Resultados gravados com sucesso! |");
            }
        } catch (IOException e) {
            // Trata exceções de IO, se ocorrerem
            e.printStackTrace();
        }
    }

    public List<String> getResultados() {
        this.lerResultados();
        return this.vetorResultados;
    }

    private void lerResultados() {
        this.vetorResultados.clear();
        try {
            File arquivo = new File(caminhoArquivoResultados);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) { // Itera sobre cada linha do arquivo
                String data = leitor.nextLine();
                if (data.length() != 0) {
                    this.vetorResultados.add(data);
                }
            }

            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("| Ocorreu um erro ao ler o arquivo Resultados! |");
            e.printStackTrace();
        }
    }

}
