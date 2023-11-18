import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private int escolhaDoUsuario;
    private Scanner scanner = new Scanner(System.in);
    private GerenciadorArquivos gerenciarArquivo = new GerenciadorArquivos();

    // public Menu() {
    // this.scanner = new Scanner(System.in);
    // }

    public void mostrarMenu() {
        int verificarSaida = 1;

        while (verificarSaida != 0) {
            System.out.println(
                    "=-=-=-=-=-= Escolha uma das opções abaixo -=-=-=-=-=-\n"
                            + "| 1 - Iniciar Jogo                                  |\n"
                            + "| 2 - Cadastrar jogadores                           |\n"
                            + "| 3 - Ver histórico                                 |\n"
                            + "| 0 - Sair do jogo                                  |\n"
                            + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            // O nextInt(): não consumia a linha do (Enter);
            this.escolhaDoUsuario = scanner.nextInt();
            // Consumir a quebra de linha pendente;
            scanner.nextLine();
            switch (escolhaDoUsuario) {

                case 1:
                    // Aqui irá vir a função jogar;
                    this.escolhaDoUsuario = 1;
                    verificarSaida = 0;
                    break;
                case 2:
                    this.cadastroDeJogador();
                    break;
                case 3:    
                    this.verHistorico();
                    break;
                case 0:
                    System.out.println("| Saindo do jogo. Espero que tenha se divertido.| ");
                    verificarSaida = 0;
                    this.escolhaDoUsuario = 0;
                    break;
                default:
                    System.out.println("| Opção inválida.Tente novamente. |");
                    continue;
            }
        }
    }
    
    public int getEscolhaDoUsuario() {
        // Retorna a escolha do usuario;
        return this.escolhaDoUsuario;
    }

    public void cadastroDeJogador() {
        boolean cadastrando = true;
        while (cadastrando) {
            System.out.println("| =-=-=-=-=- Digite o nome do jogador =-=-=-=-=- |");
            String nomeJogador = scanner.nextLine();
            // Guardar os nomes no arquivo .txt;
            gerenciarArquivo.gravarJogador(nomeJogador);

            System.out.println(
                    "| Deseja adicionar outro jogador ? |\n"
                            + "| 1 - SIM                          |\n"
                            + "| 0 - NÃO                          |\n"
                            + "| =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- |");
            int escolhaJogador = scanner.nextInt();
            // Consumir quebra de linha;
            scanner.nextLine();

            if (escolhaJogador == 1) {
                // Continuar o loop caso a escolha for "SIM";
                continue;
            } else {
                System.out.println("| Salvando... |");
                delay();
                System.out.println("| Salvo com sucesso! |");
                // Voltar ao menu;
                // this.mostrarMenu();
                // Alterar o valor da variavel para falsa para quebrar o loop;
                cadastrando = false;
            }
        }
    }

    private void delay() {
        try {
            // Tempo de 1 segundo;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verHistorico() {
        List<String> vetorResultados = new ArrayList<>();
        vetorResultados.addAll(gerenciarArquivo.getResultados());
        System.out.println("| =-=-=-=-=- Histórico das partidas =-=-=-=-=- |");
        for(String resultados : vetorResultados){
            System.out.println(resultados);
        }
    }

}