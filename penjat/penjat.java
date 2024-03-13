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

        final String[][] taulell =
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

        boolean continuar = true;

        while (continuar) {
            netejaPantalla();
            reiniciarTaulell(taulell, taulellPartida);
            mostrarTaulell(taulellPartida);
            String paraula = generarParaula(paraules); //genero una paraula aleatoria de la llista de possibilitats
            char[] paraulaSeparada = new char[paraula.length()]; // la descomposo en chars
            codificarParaula(paraulaSeparada,paraula); // aquest metode assigna les incognites de la paraula amb *
            lletres = ""; //per inicialitzar a 0 la llista per defecte cada partida
            errors= 0;
            contador=0;
        
                while (contador < paraula.length() && errors < 8){ //el bucle seguira fins que superis el limit d'encerts o d'errors
                mostrarParaula(paraulaSeparada);
                String entrada = sc.nextLine();
                String input = entrada.toLowerCase();
                // String[] lletresUtilitzades = comprovarInput(input, abecedari, lletresRepetides);
                netejaPantalla();

                mostrarTaulell(taulellPartida);
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
                    actualitzarPenjat(taulellPartida, errors);
                    netejaPantalla();   //Segueixo el sistema, actualitza, neteja, mostra
                    mostrarTaulell(taulellPartida);
                    System.out.println(lletres);  //mostro la llista ordenada
                    System.out.println("No s'ha trobat la lletra " + input.charAt(0));
                    if (errors == 8){
                        System.out.println("Has perdut!! la paraula era: ");
                        System.out.println(paraula);
                    }
                } else {
                    System.out.println("Aquesta lletra ja l'has dit!!");
                }
            }
            continuarJugant(continuar); //metode per preguntra si vols seguir jugant
        }
    }

    static void reiniciarTaulell(String[][] taulell, String[][] taulellPartida){
        for(int i = 0; i < taulell[0].length; i++){
            for (int y = 0; y < taulell.length; y++){
                taulellPartida[y][i] = taulell[y][i];
            }
        }
    }

    static void continuarJugant(boolean continuar){
        System.out.println("Vols jugar un altre partida? [S-N]");
            String resposta = sc.nextLine();
            switch (resposta) {
                case "S":
                    netejaPantalla();
                    break;
                case "N":
                    continuar=false;
                    break;
                default:
                    continuar=false;
                    break;
            }
    }

    static void actualitzarPenjat(String[][] taulellPartida, int errors){
        switch (errors) {
            case 1:
                taulellPartida[1][6] = "|";
                break;
            case 2:
                taulellPartida[2][6] = "O";
                break;
            case 3:
                taulellPartida[3][6] = "|";
                break;
            case 4:
                taulellPartida[3][5] = "/";
                break;
            case 5:
                taulellPartida[3][7] = "\\";
                break;
            case 6:
                taulellPartida[4][6] = "|";
                break;
            case 7:
                taulellPartida[5][5] = "/";
                break;
            case 8:
                taulellPartida[5][7] = "\\";
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
        if (lletres.length()==0){ //la primera lletra introduïda sempre entrara a la llista
            lletres += input;
        }
        else
        {
            char[] frase = lletres.toCharArray();
            int feedback = cercaBinaria(frase, input.charAt(0));
            switch (feedback) {
                case 1:
                    repetida = true;
                    break;
                case -1:
                    lletres += input;
                    frase = lletres.toCharArray(); //utilitzo el metode de ficar la lletra, pasar-la a array de chars i ordenar-la
                    Arrays.sort(frase);
                    lletres=""; //una vegada guardada a l'array auxiliar reinicio la llista per sobreescriure-la
                    for (char lletra : frase){
                        lletres += lletra;
                    }
                    break;
            }
        }
        System.out.println(lletres);    //printo la llista

        if (!repetida){
            boolean trobada = false;
            for (int  i = 0; i < paraula.length(); i++){
                if (input.charAt(0) == paraula.charAt(i)){
                    paraulaSeparada[i] = input.charAt(0);   //buscar l'input a la paraula
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
    
    static void mostrarTaulell(String[][] taulellPartida){
        for (String[] filas : taulellPartida){
            for (String valor : filas){     //mostra el taulell
                System.out.print(valor);
            }
            System.out.println();
        }
    }

    static void codificarParaula(char[] paraulaSeparada, String paraula){
        for (int i = 0; i < paraula.length(); i++){ //transforma la paraula en * i la printa
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
