import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private String caminhoDoArquivoDeJogadores = "jogadores.txt";
    private String caminhoArquivoResultados = "resultados.txt";

    private List<String> vetorJogadores = new ArrayList<>();

    public void gravarJogador(String nome) {
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

            System.out.println("Jogador cadastrado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar jogador!");
            e.printStackTrace();
        }
    }

    public List<String> getJogadores() {
        this.lerArquivoJogadores();
        return this.vetorJogadores;
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
            e.printStackTrace();
        }
    }

    // Grava os resultados de cada jogador e sua cartela
    public void gravarResultados(String[] resultados) {
        try {
            if (resultados.length > 0) {
                File arquivo = new File(caminhoArquivoResultados);
                // Cria o arquivo caso não exista
                if (!arquivo.exists()) {
                    arquivo.createNewFile();
                }
                // Permite a escrita em um arquivo
                FileWriter escritorDeArquivo = new FileWriter(arquivo, true);
                // Armazenar os dados em um buffer
                BufferedWriter escritorBuffer = new BufferedWriter(escritorDeArquivo);

                for (String string : resultados) {
                    // Escreve a string no arquivo
                    escritorBuffer.write(string);
                    // Nova linha
                    escritorBuffer.newLine();
                }
                // Fechando a stream
                escritorBuffer.close();
                System.out.println("Resultados gravados com sucesso!");
            }
        } catch (IOException e) {
            // Trata exceções de IO, se ocorrerem
            e.printStackTrace();
        }
    }
}
