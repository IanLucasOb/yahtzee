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
            this.escolhaDoUsuario = scanner.nextInt(); // O nextInt(): não consumia a linha do (Enter);
            scanner.nextLine(); // Consumir a quebra de linha pendente;
            switch (escolhaDoUsuario) {
                case 1:
                    break;
                // Aqui irá vir a função jogar;
                case 2:
                    this.cadastroDeJogador();
                    break;
                case 3:
                    break;
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

    private void cadastroDeJogador() {
        boolean cadastrando = true;
        while (cadastrando) {
            System.out.println("| =-=-=-=-=- Digite o nome do jogador =-=-=-=-=- |");
            String nomeJogador = scanner.nextLine();
            gerenciarArquivo.gravarJogador(nomeJogador); // Guardar os nomes no arquivo .txt;

            System.out.println(
                              "| Deseja adicionar outro jogador ? |\n"
                            + "| 1 - SIM                          |\n"
                            + "| 0 - NÃO                          |\n"
                            + "| =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- |");
            int escolhaJogador = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha;

            if (escolhaJogador == 1) {
                continue; // Continuar o loop caso a escolha for "SIM";
            } else {
                System.out.println("| Salvando... |");
                pausarPorUmS();
                System.out.println("| Salvo com sucesso! |");
                this.mostrarMenu(); // Voltar ao menu;
                cadastrando = false; // Alterar o valor da variavel para falsa para quebrar o loop;
            }
        }
    }

    private void pausarPorUmS(){
        try{
            Thread.sleep(1000); // Tempo de 1 segundo;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}