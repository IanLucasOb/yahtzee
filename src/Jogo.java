import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    public static Scanner entrada = new Scanner(System.in);
    public static Menu menu = new Menu();
    public static ArrayList<Integer> jogadoresEscolhidos = new ArrayList<Integer>();
    public static GerenciadorArquivos gerenciadorArquivos = new GerenciadorArquivos();
    public static boolean jogando = true;
    public static int rodada = 1;
    public static int qtdRodadas = 13;

    public static void main(String[] args) throws Exception {
        // executa o primeiro loop
        mostrarMenuInicial();
    }

    public static void jogar() {
        jogando = true;
        ArrayList<String> jogadoresCadastrados = gerenciadorArquivos.getJogadores();
        Dado dado = new Dado();

        while (jogando && rodada <= 13) {
            jogadoresEscolhidos.clear();
            String escolhaUsuario = menu.escolhaDosJogadores();

            jogadoresEscolhidos.addAll(pegarIndexDosJogadores(escolhaUsuario));

            if (menosDeDoisJogadores(jogadoresEscolhidos.size())) {
                System.out.println("| Selecione no mininimo 2 jogadores para jogar!");
                sairDaPartida();
            }

            if (rodada < 13) {
                // ? Buscar jogadores que o usuário selecionou
                // Pegar o que o menu retornou
                List<Jogador> jogadores = new ArrayList<>();
                List<Cartela> cartelas = new ArrayList<>();

                if (jogadores.size() == 0) {
                    // Criando jogadores e as cartelas
                    for (int indice : jogadoresEscolhidos) {
                        if(indice <= jogadoresEscolhidos.size()) {
                            Jogador jogador = new Jogador();
                            // Com base no índice de cada jogador que o usuário selecionou
                            // busca o nome dele na lista de jogadores cadastrados
                            String nome = jogadoresCadastrados.get(indice);
                            jogador.setNome(nome);
                            jogadores.add(jogador);
    
                            // Cartela do jogador -> a chave é o índice
                            Cartela cartela = new Cartela();
                            cartelas.add(cartela);
                        } else {
                            System.out.println("\n| Números dos jogadores inválidos! Escolha somente os números dos jogadores que contém na lista.\n");
                            sairDaPartida();
                            break;
                        }
                    }
                    System.out.println("| -=-=-=-=-= YATZHEE =-=-=-=-=|");
                }

                for (int i = 0; i < qtdRodadas; i++) {
                    System.out.println("Rodada - " + rodada);
                    for (int indice = 0; indice < jogadores.size(); indice++) {
                        Jogador jogador = jogadores.get(indice);
                        Cartela cartela = cartelas.get(indice);

                        dado.setVetor();
                        int[] dados = dado.getVetor();

                        // Atribui cada tipo de pontuação na cartela
                        cartela.setVetorDados(dados);
                        for (int j = 0; j < 15; j++) {
                            cartela.setCartela(j);
                        }

                        System.out.println("Jogador: " + jogador.getNome());

                        mostrarDados(dados);
                        mostrarCartela(cartela);

                        System.out.println("Selecione a categoria em que deseja pontuar: ");
                        int categoria = entrada.nextInt();

                        // Validar se a categoria é válida
                        while (categoria > 15 || categoria < 1) {
                            System.out.println("Digite uma categoria válida:");
                            categoria = entrada.nextInt();
                        }

                        while (cartela.getCartela()[categoria - 1] == 0) {
                            System.out.println("| Está categoria não pode ser marcada pois não há pontuação!");
                            System.out.println("Digite outra categoria: ");
                            categoria = entrada.nextInt();
                        }

                        while (cartela.categoriaJaSelecionada(categoria - 1)) {
                            System.out.println("| Está categoria já foi selecionada!");
                            System.out.println("Digite outra categoria: ");
                            categoria = entrada.nextInt();
                        }

                        cartela.setCategoriaSelecionada(categoria - 1);
                        cartela.setPontuacao(categoria - 1);
                        System.out.println("Pontuação Total: " + cartela.getPontosTot());
                        System.out.println("\n\n\n");

                        // Adicionar a funçao de rolar novamente - ver cm thiago
                        // Armazenar resultados

                    }
                    rodada++;
                }
                // Adicionando as pontuações de cada categoria + total aos jogadores
                for (int i = 0; i < jogadores.size(); i++) {
                    Jogador jogador = jogadores.get(i);
                    Cartela cartela = cartelas.get(i);
                    jogador.setPontuacao(cartela.getCartela());
                }

                // ? Fim do jogo aqui
                System.out.println("Fim de jogo!!!");

                int maiorPontuacao = 0;
                String nomeVencedor = "";
                String resultado = "";

                // Encontrar a maior pontuação e o nome do jogador que a fez
                for (Jogador jogador : jogadores) {
                    if (maiorPontuacao < jogador.getPontuacaoJogador()[15]) {
                        maiorPontuacao = jogador.getPontuacaoJogador()[15];
                        nomeVencedor = jogador.getNome();
                    }
                    resultado += jogador.getNome() + ": " + jogador.getPontuacaoJogador()[15] + " X ";
                }

                gerenciadorArquivos.gravarResultados(dataAtual() + " - " + resultado);
                System.out.println("\n");
                System.out.println("Vencedor: " + nomeVencedor);
                System.out.println("Pontuação Total: " + maiorPontuacao);
                System.out.println("Até a proxima pártida!\n");
                sairDaPartida();
            }

        }
    }

    public static void mostrarMenuInicial() {
        // mostra o menu enquato a escolha do jogar gor diferente de 1 (jogo iniciou) ou
        // diferente de zero (fecha o programa)
        do {
            menu.mostrarMenu();
        } while (menu.getEscolhaDoUsuario() != 1 && menu.getEscolhaDoUsuario() != 0);
        // só chama a função jogar se a opção do úsuário for 1 (jogar)
        if (menu.getEscolhaDoUsuario() == 1) {
            jogar();
        } else {
            entrada.close();
        }
    }

    public static ArrayList<Integer> pegarIndexDosJogadores(String str) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        String indexDosJogadores[] = str.split(",");

        for (String indexString : indexDosJogadores) {
            int convertParaInt = Integer.parseInt(indexString.trim()) - 1;
            indexes.add(convertParaInt);
        }

        return indexes;
    }

    public static Boolean usuarioSaiuOuNaoDigitou(String str) {
        return str.toLowerCase().equals("s") || str.isEmpty();
    }

    public static Boolean menosDeDoisJogadores(int numJogadores) {
        return numJogadores < 2;
    }

    public static void sairDaPartida() {
        jogando = false;
        mostrarMenuInicial();
    }

    /*
     * Mostrar a cartela e a pontuação de cada casa
     */
    public static void mostrarCartela(Cartela cartela) {
        String casaCartela[] = {
                "Soma dos Uns;",
                "Soma dos Dois;",
                "Soma dos Três;",
                "Soma dos Quatros;",
                "Soma dos Cincos;",
                "Soma dos Seis;",
                "Soma;",
                "Bônus;",
                "Trinca;",
                "Quadra;",
                "Full House;",
                "Sequência Pequena",
                "Sequência Grande",
                "Sorte/Chance;",
                "Yahtzee;"
                // "Pontuação Total."
        };

        System.out.println("===============================================================");
        System.out.print("| Pontuação: \n");

        for (int index = 0; index <= 14; index++) {
            int tamanhoString = ("" + cartela.getCartela()[index] + "").length();
            System.out.print("             ");

            if (tamanhoString == 1) {
                System.out.println(
                        "  [  " + cartela.getCartela()[index]
                                + " ]  -  " + (index + 1) + ". " + casaCartela[index]
                                + (cartela.categoriaJaSelecionada(index) ? " [X]" : ""));
            } else if (tamanhoString == 2) {
                System.out.println(
                        "  [ " + cartela.getCartela()[index]
                                + " ]  -  " + (index + 1) + ". " + casaCartela[index]
                                + (cartela.categoriaJaSelecionada(index) ? " [X]" : ""));
            } else {
                System.out.println(
                        "  [ " + cartela.getCartela()[index]
                                + " ]  -  " + (index + 1) + ". " + casaCartela[index]
                                + (cartela.categoriaJaSelecionada(index) ? " [X]" : ""));
            }
        }
        System.out.println("===============================================================");
    }

    /*
     * Mostar os dados que o jogador rolou
     */
    public static void mostrarDados(int[] dados) {
        System.out.print("| Dados -> ");
        for (int index = 0; index < 5; index++) {
            if (index == 0) {
                System.out.print("[ " + dados[index] + ", ");
            } else if (index > 0 && index != 4) {
                System.out.print("" + dados[index] + ", ");
            } else {
                System.out.println("" + dados[index] + " ]");
            }
        }
    }

    public static String dataAtual() {
        String[] formatarData = LocalDate.now().toString().split("T")[0].split("-");
        return formatarData[2] + "/" + formatarData[1] + "/" + formatarData[0];
    }
}
