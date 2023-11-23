import java.util.Scanner;

public class TestePigas {
    public static Dado dado = new Dado();
    public static int vetorDado[] = new int[5];

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        dado.setVetor();
        mostrarCartelaDados();
        for (int cont = 2; cont > 0; cont--) {

            System.out.println("===============================================================");
            System.out.println("| Obs: " + cont + " ReRolagens restantes");
            System.out.print("| Deseja reRolar os dados? [S/N] ");
            String resposta = teclado.next().split(" ")[0].toUpperCase();

            if (resposta.equals("S")) {
                System.out.print("|      Números equivalente dos indeices: [ 1°, 2°, 3°, 4°, 5°] "
                                + "\n===============================================================" 
                                + "\n| Digite os Indices que deseja reRolar: ");

                                
                // transforma a String digitada em um vetor com inteiros
                teclado.nextLine();
                String indexString[] = teclado.nextLine().strip().split(" ");
                int indexDados[] = new int[indexString.length];

                for (int i = 0; i < indexString.length; i++) {
                    indexDados[i] = Integer.parseInt(indexString[i]) - 1;
                }

                dado.reRolarDados(indexDados);
                mostrarCartelaDados();
            } else {
                break;
            }
        }        
        teclado.close();
    }

    public static void mostrarCartelaDados() {
        System.out.print("| Dados -> ");
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
                System.out.print(cartelaUm.getCartela()[index] + " ]\n");
            }       
        }
    }
}