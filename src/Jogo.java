import java.util.Scanner;

public class Jogo {
    public static Scanner entrada = new Scanner(System.in);
    public static Menu menu = new Menu();

    public static void main(String[] args) throws Exception {
        // executa o primeiro loop
        mostrarMenuInicial();
    }

    public static void jogar() {
        boolean jogando = true;
        while (jogando) {
            // aqui vai executar o jogo..
            System.out.println("Jogo rolando..."); // simulando...
            System.out.println("Sair");
            String dec = entrada.nextLine();
            if (dec.equals("s")) {
                // seta a condição como false para parar o loop do jogo
                jogando = false;
                // chama o menu novamente
                mostrarMenuInicial();
            }
        }
    }

    public static void mostrarMenuInicial() {
        // mostra o menu enquato a escolha do jogar gor diferente de 1 (jogo iniciou) ou diferente de zero (fecha o programa)
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
}
