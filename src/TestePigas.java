import java.util.Scanner;

public class TestePigas {
    public static Dado dado = new Dado();
    public static int vetorDado[] = new int[5];

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int escolha = 1;


        while (escolha != 0) {
            System.out.println("======================== Menu do Pigas ========================\n"
                               + "[ 1 ] - Mostra 5 dados e a cartela de pontuação(13 vezes);\n"
                               + "[ 2 ] - Rola 5 dados e permite alteração de valores;\n"
                               + "[ 0 ] - Sair do Teste.\n"
                               + "===============================================================");
            System.out.print("| Digite sua escolha: ");
            escolha = teclado.nextInt();

            switch (escolha) {
                case 1:
                    for (int i = 0; i <= 13; i++) {
                        mostrarCartelaDados();
                    }
                    break;

                case 2:
                    mostrarCartelaDados();
                    System.out.print("| Deseja reRolar os dados? [S/N] ");
                    String resposta = teclado.next().split(" ")[0].toUpperCase();

                    if (resposta.equals("S")) {
                        int indexes[] = new int[5];
                        
                        for (int i = 0; i < 5; i++) {
                            System.out.println("| Digite os Indexes que deseja reRolar: ");
                            indexes[i] = teclado.nextInt() - 1;
                            dado.reRolarDados(indexes[i]);
        
                            System.out.print("| Continuar? [S/N] ");
                            String continuar = teclado.next().split(" ")[0].toUpperCase();
        
                            if (continuar.equals("S")) {
                                continue;
                            } else {
                                vetorDado = dado.getVetor();
                                getDados();
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        teclado.close();
    }

    public static void getDados() {
        System.out.print("| Dados -> ");
        for (int index = 0; index < 5; index++) {
            if (index == 0) {
                System.out.print("[ " + dado.getVetor()[index] + ", ");
            } else if (index == 4) {
                System.out.println("" + dado.getVetor()[index] + " ]");
            } else {
                System.out.print("" + dado.getVetor()[index] + ", ");
            }
        }
    }
    
    public static void cartela() {
        Cartela cartelaUm = new Cartela();        
        
        cartelaUm.setVetorDados(vetorDado);
        cartelaUm.setUns();
        cartelaUm.setDois();
        cartelaUm.setTres();
        cartelaUm.setQuatro();
        cartelaUm.setCinco();
        cartelaUm.setSeis();
        cartelaUm.setSoma();
        cartelaUm.setBonus();
        cartelaUm.setTrinca();
        cartelaUm.setQuadra();
        cartelaUm.setFullHouse();
        cartelaUm.setSeqPequena();
        cartelaUm.setSeqGrande();
        cartelaUm.setChance();
        cartelaUm.setYahtzee();
        cartelaUm.setPontosTot();

        System.out.print("| Pontuação -> ");
        for (int index = 0; index <= 15; index++) {
            if (index == 0) {
                System.out.print("[ " + cartelaUm.getCartela()[index] + ", ");
            } else if (index > 0 && index < 15) {
                System.out.print("" + cartelaUm.getCartela()[index] + ", ");
            } else {
                System.out.print(cartelaUm.getCartela()[index] + " ]");
            }       
        }
        System.out.println("\n");
    }

    public static void mostrarCartelaDados() {
        System.out.print("| Dados -> ");
        dado.setVetor();
        vetorDado = dado.getVetor();

        for (int index = 0; index < 5; index++) {
            if (index == 0) {
                System.out.print("[ " + vetorDado[index] + ", ");
            } else if (index > 0 && index != 4) {
                System.out.print("" + vetorDado[index] + ", ");
            } else {
                System.out.println("" + vetorDado[index] + " ]");
            }
        }
        cartela();
    }
}