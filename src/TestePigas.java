import java.util.Scanner;

public class TestePigas {
    public static Dado dado = new Dado();
    public static int vetorDado[] = new int[5];
    public static Menu menuTeste = new Menu();

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean validRolagem = false;
        String resposta = " ";
        
        dado.setVetor();
        mostrarCartelaDados();
        for (int cont = 2; cont > 0; cont--) {
            System.out.println("| Obs: " + cont + " ReRolagens restantes");
            
            while (resposta != "S" && resposta != "N" ) {
                System.out.print("| Deseja reRolar os dados? [S/N] ");
                resposta = teclado.next().split(" ")[0].toUpperCase();

                if (resposta.equals("S")) {
                    int contador = 0;
                    while (validRolagem != true) {
                        System.out.println("===============================================================");
                        System.out.print("|      Números equivalente dos indeices: [ 1°, 2°, 3°, 4°, 5°] "
                                        + "\n| Digite os Indices que deseja reRolar: ");
                        
                        if (contador == 0) {
                            teclado.nextLine();
                        }
                        contador = 1;
                        String indexString[] = teclado.nextLine().strip().split(" ");
                        validRolagem = dado.reRolarDados(indexString);
                    }
                    mostrarCartelaDados();
    
                } else {
                    System.out.println("===============================================================");
                }
            }
        }        
        teclado.close();
    }

    public static void mostrarDados() {
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
    }

    public static void mostrarCartelaDados() {
        Cartela cartelaUm = new Cartela();
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
            "Yahtzee;",
            "Pontuação Total."
        };
        
        System.out.println("===============================================================");
        System.out.print("| Pontuação:                   ");
        mostrarDados();
        System.out.println("===============================================================");
        
        cartelaUm.setVetorDados(vetorDado); 
        for (int i = 0; i < 16; i++) { 
            cartelaUm.setCartela(i);
        }

        for (int index = 0; index <= 15; index++) {
            int tamanhoString = ("" + cartelaUm.getCartela()[index] + "").length();
            System.out.print("             ");

            if (tamanhoString == 1) {
                System.out.println("  [   " + cartelaUm.getCartela()[index] + " ]  -  " + casaCartela[index]);
            } else if (tamanhoString == 2) {
                System.out.println("  [  " + cartelaUm.getCartela()[index] + " ]  -  " + casaCartela[index]);
            } else {
                System.out.println("  [ " + cartelaUm.getCartela()[index] + " ]  -  " + casaCartela[index]);
            }
        }
        System.out.println("===============================================================");
    }
}