package br.cefet.arff;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            Utils.oneArg(args[0]);
        } else if (args.length == 2) {
            Utils.path = args[0];
            Utils.ratingFilePath = args[1];
        } else {
            Utils.getPath();
        }
        System.out.println("Caminho: " + Utils.path);
        System.out.println("Lendo legendas");
        Utils.readSubtitles();
        System.out.println("Gerando ARFF");
        Utils.generateArff();
    }
}
