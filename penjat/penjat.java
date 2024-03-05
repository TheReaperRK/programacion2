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

        String[][] taulell =
                            {{" "," ","_","_","_","_","_"," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" ","_","|","_","_"," "," "," "},
                            {"/"," "," "," "," ","\\"," "," "}};



        int[] errors = {0}; // aquest es el comptador de errades que serveix per construir el penjat
        int[] contador = {0}; // aquest es el contador d'encerts per saber quan completem la paraula
        int[] lletresRepetides = {0};// intent de proposit de no poder introduir mes de 27 paraules
        mostrarTaulell(taulell);
        String paraula = generarParaula(paraules); //genero una paraula aleatoria de la llista de possibilitats
        char[] paraulaSeparada = new char[paraula.length()]; // la descomposo en chars
        codificarParaula(paraulaSeparada,paraula); // aquest metode assigna les incognites de la paraula amb *
        String abecedari = ""; //intent d'assignar una llista de lletres que ja hem introduit de manera ordenada
        //String[] abecedari = {"h","a","x","b","z","u"};

        while (contador[0] < paraula.length() && errors[0] < 6){ //el bucle seguira fins que superis el limit d'encerts o d'errors
            mostrarParaula(paraulaSeparada);
            String input = sc.nextLine();
            String[] lletresUtilitzades = comprovarInput(input, abecedari, lletresRepetides);
            netejaPantalla();

            mostrarTaulell(taulell);
            int resposta = comprovarLletra(input, paraulaSeparada, paraula, contador);
            if (resposta == 0){
                System.out.println("S'ha trobat la lletra " + input.charAt(0));
                //mostrarAbecedari(abecedari);
                if (contador[0] == paraula.length()){
                    System.out.println("Has guanyat el joc!! la paraula era: ");
                    System.out.println(paraula);
                }
            }
            else {
                errors[0]++;
                actualitzarPenjat(taulell, errors);
                netejaPantalla();
                mostrarTaulell(taulell);
                System.out.println("No s'ha trobat la lletra " + input.charAt(0));
                if (errors[0] == 6){
                    System.out.println("Has perdut!! la paraula era: ");
                    System.out.println(paraula);
                }
            }
        }
    }

    static void mostrarAbecedari(String[] abecedari){
        for (String lletra: abecedari){
            System.out.print(lletra);
        }
    }

    static String[] comprovarInput(String input, String abecedari, int[] lletresUtilitzades){
        String[] lletresRepetides = new String[abecedari.length()+1];
        if (abecedari.length() == 0 && input.length() < 2)
            lletresRepetides[0] = input;
        else{
            for (int i = 0; i < abecedari.length())
            }
        return lletresRepetides;
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
