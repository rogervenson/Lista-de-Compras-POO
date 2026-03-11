import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;


// Essa é a segunda versão do programa, para adaptar e começar a utilizar e aprender conceitos de POO de fato.

public class Main {
    public static void main(String[] args) {
        ListaDeCompras lista = new ListaDeCompras("<sem nome>");
        Scanner scanner = new Scanner(System.in);
        String nomeArquivoAtual = "<sem nome>";
        int opcao = 0;

        Menu.interacao(lista, nomeArquivoAtual, opcao, scanner);
        scanner.close();
    }
}