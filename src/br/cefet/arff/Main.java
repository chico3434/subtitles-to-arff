package br.cefet.arff;

import java.io.File;
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
        }

        if (Utils.path == null) {
            Utils.getPath();
        } else {
            Utils.ratingFilePath += "/rating.csv";
            File test = new File(Utils.ratingFilePath);
            if (!test.exists()) {
                System.out.println("Entre com o caminho do arquivo de classificações: ");
                Scanner sc = new Scanner(System.in);
                Utils.ratingFilePath = sc.next();
            }
        }

        System.out.println("Caminho: " + Utils.path);
        System.out.println("Lendo legendas");
        Utils.readSubtitles();
        System.out.println("Gerando ARFF");
        Utils.generateArff();
        System.out.println("ARFF gerado");
    }
}
