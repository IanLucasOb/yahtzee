import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    public static Scanner entrada = new Scanner(System.in);
    public static Menu menu = new Menu();
    public static ArrayList<Integer> jogadoresEscolhidos = new ArrayList<Integer>();
    public static Boolean jogando;

    public static void main(String[] args) throws Exception {
        // executa o primeiro loop
        mostrarMenuInicial();
    }

    public static void jogar() {
        jogando = true;
        while (jogando) {
            jogadoresEscolhidos.clear();
            String escolhaUsuario = menu.escolhaDosJogadores();

            if (usuarioSaiuOuNaoDigitou(escolhaUsuario)) {
                sairDaPartida();
            }

            jogadoresEscolhidos.addAll(pegarIndexDosJogadores(escolhaUsuario));

            if (menosDeDoisJogadores(jogadoresEscolhidos.size())) {
                System.out.println("| Selecione no mininimo 2 jogadores para jogar!");
                sairDaPartida();
            }
            // continua...
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
}
