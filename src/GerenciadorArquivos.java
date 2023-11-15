import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private String caminhoDoArquivoDeJogadores = "../db/jogadores.txt";
    private List<String> vetorJogadores = new ArrayList<>();

    public void gravarJogador(String nome) {
        try {
            File arquivo = new File(caminhoDoArquivoDeJogadores);
            if(!arquivo.exists()) {
                throw new Error("O arquivo " + caminhoDoArquivoDeJogadores + "não existe!");
            }
            FileWriter escritorDeArquivos = new FileWriter(caminhoDoArquivoDeJogadores, true);
            BufferedWriter escritorBuffer = new BufferedWriter(escritorDeArquivos);

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
}