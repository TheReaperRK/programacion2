package penjat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class penjat {

    static Scanner sc = new Scanner(System.in);
    static int errors = 0;
    static int contador = 0;
    static String lletres = "";

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
                            {" "," ","|"," "," "," "," "," "},
                            {" ","_","|","_","_","_","_"," "},
                            {"/"," "," "," "," "," "," ","\\"}};

        String[][] taulellPartida =
                            {{" "," ","_","_","_","_","_"," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" "," ","|"," "," "," "," "," "},
                            {" ","_","|","_","_","_","_"," "},
                            {"/"," "," "," "," "," "," ","\\"}};

        taulellPartida = taulell; // per tal de reiniciar el taulell cada partida
        mostrarTaulell(taulell);
        String paraula = generarParaula(paraules); //genero una paraula aleatoria de la llista de possibilitats
        char[] paraulaSeparada = new char[paraula.length()]; // la descomposo en chars
        codificarParaula(paraulaSeparada,paraula); // aquest metode assigna les incognites de la paraula amb *
        lletres = ""; //per inicialitzar a 0 la llista per defecte cada partida

        while (contador < paraula.length() && errors < 8){ //el bucle seguira fins que superis el limit d'encerts o d'errors
            mostrarParaula(paraulaSeparada);
            String entrada = sc.nextLine();
            String input = entrada.toLowerCase();
           // String[] lletresUtilitzades = comprovarInput(input, abecedari, lletresRepetides);
            netejaPantalla();

            mostrarTaulell(taulell);
            int resposta = comprovarLletra(input, paraulaSeparada, paraula);
            if (resposta == 0){
                System.out.println("S'ha trobat la lletra " + input.charAt(0));
                if (contador == paraula.length()){
                    System.out.println("Has guanyat el joc!! la paraula era: ");
                    System.out.println(paraula);
                }
            }
            else if (resposta == -1){
                errors++;
                actualitzarPenjat(taulell, errors);
                netejaPantalla();   //Segueixo el sistema, actualitza, neteja, mostra
                mostrarTaulell(taulell);
                System.out.println(lletres);  //mostro la llista ordenada
                System.out.println("No s'ha trobat la lletra " + input.charAt(0));
                if (errors == 8){
                    System.out.println("Has perdut!! la paraula era: ");
                    System.out.println(paraula);
                }
            }
        }
    }


    static void actualitzarPenjat(String[][] taulell, int errors){
        switch (errors) {
            case 1:
                taulell[1][6] = "|";
                break;
            case 2:
                taulell[2][6] = "O";
                break;
            case 3:
                taulell[3][6] = "|";
                break;
            case 4:
                taulell[3][5] = "/";
                break;
            case 5:
                taulell[3][7] = "\\";
                break;
            case 6:
                taulell[4][6] = "|";
                break;
            case 7:
                taulell[5][5] = "/";
                break;
            case 8:
                taulell[5][7] = "\\";
                break;
        }
    }

    static void mostrarParaula(char[] paraulaSeparada){
        for (char lletra : paraulaSeparada){    //mostra la paraula codificada
            System.out.print(lletra + " ");
        }
    }

    static String generarParaula(String[] paraules){
        int random = (int)(Math.random()*((paraules.length+1)));    //random per generar paraula
        return paraules[random];
    }

    static int comprovarLletra(String input, char[] paraulaSeparada, String paraula){
        boolean repetida = false;
        if (lletres.length()==0){
            lletres += input;
        }
        else
        {
            char[] frase = lletres.toCharArray();
            int feedback = cercaBinaria(frase, input.charAt(0));
            switch (feedback) {
                case 1:
                    repetida = true;
                    System.out.println("Aquesta lletra ja l'has dit!!");
                    //System.out.println(lletres);
                    break;
                case -1:
                    lletres += input;
                    frase = lletres.toCharArray();
                    Arrays.sort(frase);
                    lletres="";
                    for (char lletra : frase){
                        lletres += lletra;
                    }
                    break;
            }
        }
        System.out.println(lletres);

        if (!repetida){
            boolean trobada = false;
            for (int  i = 0; i < paraula.length(); i++){
                if (input.charAt(0) == paraula.charAt(i)){
                    paraulaSeparada[i] = input.charAt(0);
                    trobada = true;
                    contador++;
                }
            }
            if (trobada)
                return 0;
            else
                return-1;
        } else {
            return 1;
        }
    }

    static int cercaBinaria(char[] lletres, char valor){
        int esquerra = 0, dreta = lletres.length-1;
        while (esquerra <= dreta) {
            int index = (esquerra+dreta)/2;
            
            if (lletres[index] == valor) {
                return 1; //lletra repetida
            } else if (lletres[index] < valor) {
                esquerra = index + 1;
            }
            else {  // lletres[index] > valor
                dreta = index - 1;
            }
        }
        return -1; //lletra nova
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
