import java.util.Scanner;

public class Menu {
    private int escolhaDoUsuario;
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorArquivos gerenciarArqui = new GerenciadorArquivos();
    private int quantDeJogadores;

    // public Menu() {
    // this.scanner = new Scanner(System.in);
    // }

    public void mostrarMenu() {
        int verificarSaida = 1;

        while (verificarSaida != 0) {
            System.out.println(
                              "=-=-=-=-=-= Escolha uma das opções abaixo -=-=-=-=-=-\n"
                            + "| 1 - Iniciar Jogo                                  |\n"
                            + "| 2 - Ver histórico                                 |\n"
                            + "| 0 - Sair do jogo                                  |\n"
                            + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            this.escolhaDoUsuario = scanner.nextInt(); // O nextInt(): não consumia a linha do (Enter);
            scanner.nextLine(); // Consumir a quebra de linha pendente;
            switch (escolhaDoUsuario) {
                case 1:
                    System.out.println("| =-=-=-=-=- Digite a quantidade de jogadores -=-=-=-=-= |");
                    this.quantDeJogadores = scanner.nextInt(); // Variável para guardar a quantidade;

                    System.out.println("| =-=-=-=-=- Digite seu nome de jogador -=-=-=-=-= |");
                    String nomeDoJogador = scanner.nextLine(); // Variável para guardar nome; 
                    this.gerenciarArqui.gravarJogador(nomeDoJogador);
                    break; // Aqui irá vir a função jogar;

                case 2:
                    break; // Aqui irá vir a função ver histórico;
                case 0:
                    System.out.println("Saindo do jogo. Espero que tenha se divertido.");
                    verificarSaida = 0;
                    break;
                default:
                    System.out.println("Opção inválida.Tente novamente.");
                    continue;
            }
        }
    }

    public int getEscolhaDoUsuario() {
        return this.escolhaDoUsuario; // Retorna a escolha do usuario;
    }

    public int geQuantDeJogadores(){
        return this.quantDeJogadores;
    }
}