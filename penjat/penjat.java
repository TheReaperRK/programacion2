package penjat;

import java.io.IOException;

public class penjat {

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

        mostrarTaulell(taulell);
        
        String paraula = generarParaula(paraules);
        char[] paraulaSeparada = new char[paraula.length()];

        mostrarParaula(paraulaSeparada,paraula);
    }

    static String generarParaula(String[] paraules){
        int random = (int)(Math.random()*((paraules.length+1)));
        return paraules[random];
    }
    
    static void mostrarTaulell(String[][] taulell){
        for (String[] filas : taulell){
            for (String valor : filas){
                System.out.print(valor);
            }
            System.out.println();
        }
    }

    static void mostrarParaula(char[] paraulaSeparada, String paraula){
        System.out.println(paraula);
        for (int i = 0; i < paraula.length(); i++){
            paraulaSeparada[i] = '*';
            System.out.print(paraulaSeparada[i] + " ");
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
