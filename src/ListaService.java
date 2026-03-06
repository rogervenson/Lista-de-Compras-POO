import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListaService {
    // Salvar lista:
    public static void salvar(ListaDeCompras lista, String nomeArquivo, Scanner scanner) {
        File arquivo = new File(nomeArquivo + ".txt");
        boolean deveSalvar = true;
        if (arquivo.exists()) {
            System.out.printf("Já existe uma lista com nome '%s'. Deseja sobrescrevê-la? [s/n] ", nomeArquivo);
            String opcao = scanner.nextLine().toLowerCase();
            while (!opcao.equals("s") && !opcao.equals("n")) {
                System.out.printf("Opção inválida.\nJá existe uma lista com nome '%s'. Deseja sobrescrevê-la? [s/n] ", nomeArquivo);
                opcao = scanner.nextLine().toLowerCase();
            }
            if (opcao.equals("s")) {
                deveSalvar = true;
            } else if (opcao.equals("n")) {
                deveSalvar = false;
            }
        }
        if (deveSalvar) {
            try (FileWriter writer = new FileWriter(nomeArquivo + ".txt")) {
                for (String item : lista.getItens()) {
                    writer.write(item + "\n");
                }
                System.out.printf("Salvou a lista '%s' com sucesso.\n", nomeArquivo);
                lista.setNome(nomeArquivo);
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo: " + e.getMessage());
            }
        } else {
            return;
        }
    }

    // Carregar lista:
    public static ListaDeCompras carregar(String nomeArquivo) {
        File arquivo = new File(nomeArquivo + ".txt");
        if (!arquivo.exists()) {
            return null;
        }
        ListaDeCompras lista = new ListaDeCompras(nomeArquivo);
        try (Scanner scanner = new Scanner(arquivo)) {
            while(scanner.hasNextLine()) {
                lista.adicionar(scanner.nextLine());
            }
            System.out.printf("Lista '%s' importada com sucesso!\n", nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
            return null;
        }
        return lista;
    }
}
