package br.cefet.arff;

import java.io.*;
import java.util.*;

public class Utils {

    public static Set<String> words;

    public static List<Movie> movies;

    static {
        words = new HashSet<>();
        movies = new ArrayList<>();
    }

    public static String path;

    public static String ratingFilePath;

    public static void getPath() {
        System.out.println("Digite o caminho da pasta em que estão as legendas: ");
        Scanner sc = new Scanner(System.in);
        path = sc.nextLine();
        ratingFilePath = path + "/rating.csv";
        File test = new File(ratingFilePath);
        if (!test.exists()) {
            System.out.println("Entre com o caminho do arquivo de classificações: ");
            ratingFilePath = sc.nextLine();
        }
    }

    public static void oneArg(String path) {
        Utils.path = path;
        ratingFilePath = Utils.path + "/rating.csv";
        File test = new File(ratingFilePath);
        if (!test.exists()) {
            System.out.println("Entre com o caminho do arquivo de classificações: ");
            Scanner sc = new Scanner(System.in);
            ratingFilePath = sc.nextLine();
        }
    }

    public static String tranformRating(String rating) {
        if (rating.equals("0") || rating.equals("00")) {
            rating = "Livre";
        }
        if (rating.equals("10")) {
            rating = "Dez";
        }
        if (rating.equals("12")) {
            rating = "Doze";
        }
        if (rating.equals("14")) {
            rating = "Quatorze";
        }
        if (rating.equals("16")) {
            rating = "Dezesseis";
        }
        if (rating.equals("18")) {
            rating = "Dezoito";
        }
        return rating;
    }

    public static void readSubtitles() {
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".txt");
            }
        };

        File ratingFile = new File(ratingFilePath);

        if (!ratingFile.exists()) {
            System.out.println("Arquivo " + ratingFile.getAbsoluteFile() + " com as informações de classificação não existe!");
            System.out.println("Abortando");
            System.exit(1);
        }

        File dir = new File(path);

        File[] files = dir.listFiles(filter);

        for(File f : files){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String title = f.getName().replace(".txt","");
                System.out.println("Lendo " + title);
                // Pegar classificação indicativa
                String rating = "";
                BufferedReader br2 = new BufferedReader(new FileReader(ratingFile));
                Iterator i = br2.lines().iterator();
                while (i.hasNext()) {
                    String movie = (String) i.next();
                    if (movie.toLowerCase().contains(title)) {
                        rating = movie.split(",")[1];
                    }
                }
                rating = tranformRating(rating);
                if (rating.equals("")) {
                    System.out.println("Filme " + title + "não foi encontrado em " + ratingFile.getPath());
                    System.out.println("Por isso não será inserido no arff");
                } else {
                    Movie movie = new Movie(title, rating);
                    Iterator it = br.lines().iterator();
                    while (it.hasNext()){
                        String line = (String) it.next();
                        String[] words = line.split(" ");
                        for (String word : words){
                            if (cleanWord(word)) {
                                word = replaceWord(word);
                                if(!word.equals("")) {
                                    Utils.words.add(word);
                                    movie.addWord(word);
                                }
                            }
                        }
                    }
                    movies.add(movie);
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String replaceWord(String word) {
        word = word.replace("\'", "");
        word = word.replace(",", "");
        word = word.replace("-", "");
        word = word.replace("\"", "");
        word = word.replace("}", "");
        word = word.replace("{", "");
        word = word.replace("[", "");
        word = word.replace("]", "");
        word = word.replace("!", "");
        word = word.replace("?", "");
        word = word.replace("´", "");
        word = word.replace("`", "");
        return word;
    }

    // Passar tudo para replaceWord depois
    public static boolean cleanWord(String word) {
        if (word.equals(".")) {
            return false;
        }
        if (word.equals(",")) {
            return false;
        }
        if (word.equals("!")) {
            return false;
        }
        if (word.equals("?")) {
            return false;
        }
        if (word.equals("-")) {
            return false;
        }
        if (word.equals("--")) {
            return false;
        }
        if (word.equals("\"")) {
            return false;
        }
        if (word.equals("\'")) {
            return false;
        }
        if (word.equals("...")) {
            return false;
        }
        if (word.equals(":")) {
            return false;
        }
        if (word.equals(" ")) {
            return false;
        }
        if (word.equals("")) {
            return false;
        }
        if (word.equals("#")) {
            return false;
        }
        if (word.equals("$")) {
            return false;
        }
        if (word.equals("%")) {
            return false;
        }
        if (word.equals("&")) {
            return false;
        }
        if (word.equals("(")) {
            return false;
        }
        if (word.equals(")")) {
            return false;
        }
        if (word.equals("*")) {
            return false;
        }
        if (word.equals("+")) {
            return false;
        }
        if (word.equals("/")) {
            return false;
        }
        if (word.equals(";")) {
            return false;
        }
        if (word.equals("=")) {
            return false;
        }
        if (word.equals(">")) {
            return false;
        }
        if (word.equals("[")) {
            return false;
        }
        if (word.equals("]")) {
            return false;
        }
        if (word.equals("{")) {
            return false;
        }
        if (word.equals("}")) {
            return false;
        }
        if (word.equals("|")) {
            return false;
        }
        if (word.equals("~")) {
            return false;
        }
        if (word.equals("¡")) {
            return false;
        }
        if (word.equals("´")) {
            return false;
        }
        if (word.equals("¿")) {
            return false;
        }
        if (word.equals("..")) {
            return false;
        }
        if (word.equals("!!")) {
            return false;
        }

        return true;
    }

    public static void generateArff() {
        try {
            FileWriter writer = new FileWriter(path + "/movie.arff");
            writer.append("@RELATION Movie\n\n");
            Iterator<String> it = words.iterator();
            while(it.hasNext()) {
                writer.append("@ATTRIBUTE " + it.next() + " NUMERIC\n");
            }
            writer.append("@ATTRIBUTE class {Livre, Dez, Doze, Quatorze, Dezesseis, Dezoito}\n\n@DATA\n");
            for (Movie movie: movies) {
                it = words.iterator();
                Map words = movie.getWords();
                while (it.hasNext()) {
                    String p = it.next();
                    if (words.containsKey(p)) {
                        writer.append(words.get(p) + ",");
                    } else {
                        writer.append("0,");
                    }
                }
                writer.append(movie.getRating() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

