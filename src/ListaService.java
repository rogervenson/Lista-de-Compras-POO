import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListaService {
    // Adicionar item à lista:
    public static void adicionarItem(ListaDeCompras lista, Scanner scanner) {
        System.out.print("Digite o item que deseja adicionar: ");
        String item = scanner.nextLine();
        lista.adicionar(item);
        System.out.printf("Item '%s' adicionado com sucesso.\n", item);
    }

    // Remover item da lista:
    public static void removerItem(ListaDeCompras lista, Scanner scanner) {
        System.out.print("\nDigite o número do item que deseja remover: ");

        int item = 0;

        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida! Favor inserir o número do item a ser removido: ");
            scanner.next();
        }
        item = scanner.nextInt();

        while (item > lista.getItens().size() || item <= 0) {
            System.out.printf("Não existe item de número '%d' na lista. Digite corretamente o número item a ser removido: ", item);
            while (!scanner.hasNextInt()) {
                System.out.print("Entrada inválida! Favor inserir o número do item a ser removido: ");
                scanner.next();
            }
            item = scanner.nextInt();
        }
        scanner.nextLine(); // Limpar buffer

        System.out.printf("\nItem '%s' removido com sucesso.\n", lista.getItens().get(item - 1));
        lista.remover(item - 1);
    }

    // Listar itens:
    public static void listarItens(ListaDeCompras lista, Scanner scanner) {
        System.out.printf("\nLista de Compras [%s]:\n\n", lista.getNome());
        int i = 0;
        for (String item : lista.getItens()) {
            i++;
            System.out.printf("%d - %s\n", i, item);
        }
    }

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
