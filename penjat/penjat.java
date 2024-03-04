package penjat;

import java.io.IOException;
import java.util.Scanner;

public class penjat {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        final String[] paraules = {"patata","armari","bicicleta",
                            "advocat","ascensor","astronauta","autopista",
                            "avinguda","bigoti","carretera","castanya",
                            "cervell","civada","cultura","dentista","esquena",
                            "estrella","formatge","gendre","genoll",
                            "infermera","internet","maduixa","malaltia",
                            "maluc","mandarina","maquinista","motocicleta",
                            "nebot","pastanaga","patinet","perruqueria",
                            "pissarra","professor","quadrat","taronja",
                            "tramvia","trapezi","tricicle","violeta"};

        String[][] taulellFinal =
                            {{" "," ","_","_","_","_","_"," "},
                            {" "," ","|"," "," "," O"," "," "},
                            {" "," ","|"," "," /","|","\\"," "},
                            {" "," ","|"," "," /"," ","\\"," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" ","_","|","_","_"," "," "," "},
                            {"/"," "," "," "," ","\\"," "," "}};

        String[][] taulell =
                            {{" "," ","_","_","_","_","_"," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" ","_","|","_","_"," "," "," "},
                            {"/"," "," "," "," ","\\"," "," "}};

        int[] errors = {0};
        mostrarTaulell(taulell);
        String paraula = generarParaula(paraules);
        char[] paraulaSeparada = new char[paraula.length()];
        int[] contador = {0};
        codificarParaula(paraulaSeparada,paraula);

        while (contador[0] < paraula.length() && errors[0] < 6){
            mostrarParaula(paraulaSeparada);
            String input = sc.nextLine();
            netejaPantalla();

            mostrarTaulell(taulell);
            int resposta = comprovarLletra(input, paraulaSeparada, paraula, contador);
            if (resposta == 0){
                System.out.println("S'ha trobat la lletra " + input.charAt(0));
            }
            else {
                errors[0]++;
                actualitzarPenjat(taulell, errors);
                netejaPantalla();
                mostrarTaulell(taulell);
                System.out.println("No s'ha trobat la lletra " + input.charAt(0));
            }
        }
    }

    static void actualitzarPenjat(String[][] taulell, int[] errors){
        switch (errors[0]) {
            case 1:
                taulell[1][6] = "O";
                break;
            case 2:
                taulell[2][6] = "|";
                break;
            case 3:
                taulell[2][5] = "/";
                break;
            case 4:
                taulell[2][7] = "\\";
                break;
            case 5:
                taulell[3][5] = "/";
                break;
            case 6:
                taulell[3][7] = "\\";
                break;
        }
    }

    static void mostrarParaula(char[] paraulaSeparada){
        for (char lletra : paraulaSeparada){
            System.out.print(lletra + " ");
        }
    }

    static String generarParaula(String[] paraules){
        int random = (int)(Math.random()*((paraules.length+1)));
        return paraules[random];
    }

    static int comprovarLletra(String input, char[] paraulaSeparada, String paraula, int[] contador){
        boolean trobada = false;
        for (int  i = 0; i < paraula.length(); i++){
            if (input.charAt(0) == paraula.charAt(i)){
                paraulaSeparada[i] = input.charAt(0);
                trobada = true;
                contador[0]++;
            }
        }
        if (trobada)
            return 0;
        else
            return-1;
    }
    
    static void mostrarTaulell(String[][] taulell){
        for (String[] filas : taulell){
            for (String valor : filas){
                System.out.print(valor);
            }
            System.out.println();
        }
    }

    static void codificarParaula(char[] paraulaSeparada, String paraula){
        System.out.println(paraula);
        for (int i = 0; i < paraula.length(); i++){
            paraulaSeparada[i] = '*';
        }
    }
    
    static void netejaPantalla() {

        try {
            if (System.getProperty("os.name").contains("Windows")) {
    	        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
    		System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println();
        }
    }
                            
}
