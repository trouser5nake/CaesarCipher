import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Cypher {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    public static void encrypt(String path, int key){

        final List<Character> sourceText = new ArrayList<>();

        try(FileReader input = new FileReader(path);) {
            while (input.ready()) {
                char ch = (char) (input.read());
                sourceText.add(ch);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        char[] encryptedText = new char[sourceText.size()];
        int index = 0;
        for (char ch:sourceText) {
            int i = 0;
            while (true){
                i++;
                if (i == ALPHABET.length){
                    break;
                }
                if (ch == ALPHABET[i]){
                    encryptedText[index] = ALPHABET[(i + key)%40];
                    index++;
                    break;
                }
            }
        }
        Path sourceFile = Path.of(path);
        String inputFileName = sourceFile.getFileName().toString();
        String outputFileName = inputFileName.substring(0, inputFileName.length() - 4) +  "encrypted.txt";
        String outputPath = sourceFile.getParent().toString() + "\\" + outputFileName;
        try(FileWriter output = new FileWriter(outputPath);){
        output.write(encryptedText);}
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void decrypt(String path, int key){

        final List<Character> sourceText = new ArrayList<>();

        try(FileReader input = new FileReader(path);) {
            while (input.ready()) {
                char ch = (char) (input.read());
                sourceText.add(ch);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        char[] decryptedText = new char[sourceText.size()];
        int index = 0;
        for (char ch:sourceText) {
            int i = 0;
            while (ch != ALPHABET[i]){
                i++;
                if (i == ALPHABET.length){
                    System.out.println("Символа '" + ch + "' нет в алфавите");
                    break;
                }
            }
            decryptedText[index] = ALPHABET[(40 + i - key)%40];
            index++;
        }
        Path sourceFile = Path.of(path);
        String inputFileName = sourceFile.getFileName().toString();
        String outputFileName = inputFileName.substring(0, inputFileName.length() - 4) +  "decrypted.txt";
        String outputPath = sourceFile.getParent().toString() + "\\" + outputFileName;
        try(FileWriter output = new FileWriter(outputPath);){
            output.write(decryptedText);}
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        encrypt("C:\\Distrib\\JAVA\\sourceee\\123.txt", 1);
        decrypt("C:\\Distrib\\JAVA\\sourceee\\123encrypted.txt", 1);
    }

}