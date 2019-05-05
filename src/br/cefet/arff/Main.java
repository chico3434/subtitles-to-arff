package br.cefet.arff;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        for(int i = 0; i < args.length; i++) {
            if (args[i].contains("--path=")) {
                Utils.path = args[i].replace("--path=", "");
            }
            if (args[i].contains("--ratingFilePath=")) {
                Utils.ratingFilePath = args[i].replace("--ratingFilePath=", "");
            }
            if (args[i].contains("--out=")) {
                Utils.outPath = args[i].replace("--out=", "");
            }
        }

        if (Utils.path == null) {
            Utils.getPath();
        } else {
            if (Utils.ratingFilePath == null) {
                System.out.println("Entre com o caminho do arquivo de classificações: ");
                Scanner sc = new Scanner(System.in);
                Utils.ratingFilePath = sc.next();
            }
            if (Utils.outPath == null) {
                System.out.println("Digite o diretório onde serão criados os arquivos de saída: ");
                Scanner sc = new Scanner(System.in);
                Utils.outPath = sc.nextLine();
            }
        }

        System.out.println("Caminho: " + Utils.path);
        System.out.println("Lendo legendas...");
        Utils.readSubtitles();
        System.out.println("Gerando ARFF...");
        Utils.generateArff();
        System.out.println("ARFF gerado");
        System.out.println("Gerando lista de filmes não usados...");
        Utils.writeUnusedInFile();
        System.out.println("Lista gerada");
        System.out.println("Copiando legendas...");
        Utils.copyUsedSubtitles();
        System.out.println("Legendas copiadas");
    }
}
