import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Menu {
    private int escolhaDoUsuario;
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorArquivos gerenciarArquivo = new GerenciadorArquivos();
    private ArrayList<String> vetorResultados = new ArrayList<String>();
    private ArrayList<String> vetorJogadores = new ArrayList<String>();

    public Menu() {
        vetorResultados.addAll(gerenciarArquivo.getResultados());
        vetorJogadores.addAll(gerenciarArquivo.getJogadores());
    }

    public void mostrarMenu() throws IOException, InterruptedException {
        int verificarSaida = 1;

        while (verificarSaida != 0) {
            System.out.println(
                              "-=-=-=-=-=-=-=-=- Escolha uma das opções abaixo -=-=-=-=-=-=-=-\n"
                            + "| 1 - Iniciar Jogo                                            |\n"
                            + "| 2 - Cadastrar jogadores                                     |\n"
                            + "| 3 - Ver histórico                                           |\n"
                            + "| 0 - Sair do jogo                                            |\n"
                            + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            // O nextInt(): não consumia a linha do (Enter);
            System.out.print("| Digite sua escolha: ");
            this.escolhaDoUsuario = scanner.nextInt();
             
            while (this.escolhaDoUsuario < 0 || this.escolhaDoUsuario > 3) {
                limparTerminal();
                System.out.println(
                              "-=-=-=-=-=-=-=-=- Escolha uma das opções abaixo -=-=-=-=-=-=-=-\n"
                            + "| 1 - Iniciar Jogo                                            |\n"
                            + "| 2 - Cadastrar jogadores                                     |\n"
                            + "| 3 - Ver histórico                                           |\n"
                            + "| 0 - Sair do jogo                                            |\n"
                            + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

                System.out.println("| Erro: digite apenas 1, 2, 3 ou 0.");
                System.out.print("| Digite sua escolha: ");
                this.escolhaDoUsuario = scanner.nextInt();
                delay();
            }

            // Consumir a quebra de linha pendente;
            scanner.nextLine();
            switch (this.escolhaDoUsuario) {
                case 0:
                    System.out.println("|        Saindo do jogo. Espero que tenha se divertido        |");
                    verificarSaida = 0;
                    this.escolhaDoUsuario = 0;
                    delay();
                    limparTerminal();
                    break;

                case 1:
                    limparTerminal();
                    // Aqui irá vir a função jogar;
                    this.escolhaDoUsuario = 1;
                    verificarSaida = 0;
                    break;

                case 2:
                    limparTerminal();
                    this.cadastroDeJogador();
                    break;

                case 3:
                    limparTerminal();
                    this.verHistorico();
                    break;

                default: // dps da verificação acima ficou inutil
                    System.out.println("|               Opção inválida, Tente novamente               |");
                    continue;
            }
        }
    }

    public int getEscolhaDoUsuario() {
        // Retorna a escolha do usuario;
        return this.escolhaDoUsuario;
    }

    public void cadastroDeJogador() throws IOException, InterruptedException {
        boolean cadastrando = true;
        while (cadastrando) {
            System.out.println("| -=-=-=-=-=-=-=-= Informe o nome do jogador =-=-=-=-=-=-=-=- |");
            String nomeJogador = scanner.nextLine();
            // Guardar os nomes no arquivo .txt;
            gerenciarArquivo.gravarJogador(nomeJogador);

            System.out.println(
                              "| Deseja adicionar outro jogador ?                            |\n"
                            + "| 1 - SIM                                                     |\n"
                            + "| 0 - NÃO                                                     |\n"
                            + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.print("| Sua escolha: ");
            int escolhaJogador = scanner.nextInt();
            // Consumir quebra de linha;
            scanner.nextLine();
            
            while (escolhaJogador > 1 || escolhaJogador < 0) {
                limparTerminal();
                System.out.println(
                              "| Deseja adicionar outro jogador ?                            |\n"
                            + "| 1 - SIM                                                     |\n"
                            + "| 0 - NÃO                                                     |\n"
                            + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println("| Erro: Digite apenas 1 ou 0.");
                System.out.print("| Sua escolha: ");
                escolhaJogador = scanner.nextInt();
                // Consumir quebra de linha;
                scanner.nextLine();
            }

            if (escolhaJogador == 1) {
                // Continuar o loop caso a escolha for "SIM";
                continue;
            } else {
                System.out.println("|                         Salvando...                         |");
                delay();
                System.out.println("|                      Salvo com sucesso                      |");
                // Voltar ao menu;
                // this.mostrarMenu();
                // Alterar o valor da variavel para falsa para quebrar o loop;
                cadastrando = false;
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                delay();
                limparTerminal();
            }
        }
    }

    public void delay() {
        try {
            // Tempo de 1 segundo;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verHistorico() throws IOException, InterruptedException {
        if (!gerenciarArquivo.getResultados().equals(vetorResultados)) {
            vetorResultados.clear();
            vetorResultados.addAll(gerenciarArquivo.getResultados());
        }

        if (vetorResultados.size() > 0) {
            System.out.println("| -=-=-=-=-=-=-=-=-= Histórico de partidas =-=-=-=-=-=-=-=-=- |");
            for (String resultados : vetorResultados) {
                System.out.println("| " + resultados + " |");
            }
        } else {
            System.out.println("|                Não há histórico de partidas!                |");
            delay();
            limparTerminal();

            mostrarMenu();
            return;
        }

        boolean voltar = false;
        while (!voltar) {
            System.out.println("| -=-=-=-=-=-=-=-=-= 1 Para Voltar Ao Menu =-=-=-=-=-=-=-=-=- |");
            int voltarAoMenu = scanner.nextInt();
            // Consumir linha;
            scanner.nextLine();
            if (voltarAoMenu == 1) {
                mostrarMenu();
                voltar = true;
            } else {
                System.out.println("|                       Opção inválida!                       |");
                continue;
            }
        }
    }

    public String escolhaDosJogadores() throws IOException, InterruptedException {
        if (!gerenciarArquivo.getJogadores().equals(vetorJogadores)) {
            vetorJogadores.clear();
            vetorJogadores.addAll(gerenciarArquivo.getJogadores());
        }

        if (vetorJogadores.size() > 0) {
            System.out.println("| -=-=-=-=-=-=-=-=-=- Escolha dos Players -=-=-=-=-=-=-=-=-=- |");
            for (int index = 0; index < vetorJogadores.size(); index++) {
                String jogador = vetorJogadores.get(index);
                System.out.println("| " + (index + 1) + " - " + jogador);
            }

            System.out.println("");
            System.out.println("| Digite 'S' para SAIR e voltar ao menu inicial.              |");
            System.out.println("| Digite os números dos jogadores SEPARADOS por ','           |");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            
            System.out.print("| ID dos jogadores: ");
            String usuarioEntrada = scanner.nextLine();

            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            limparTerminal();
            
            return usuarioEntrada;
        } else {
            System.out.println("|                  Sem jogadores cadastrados!                 |");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            limparTerminal();
        }

        return "";
    }

    // Limpa o terminal, ainda em teste - Thiago
    public void limparTerminal() throws IOException, InterruptedException {
        //Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime(); //.exec("clear"); Resto do código, porém o VS estava reclamando
        }
    }
}