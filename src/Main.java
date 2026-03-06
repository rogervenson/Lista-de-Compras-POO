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
        while (true) {
            clearScreen();
            Menu.mostrar();

            while (!scanner.hasNextInt()) { // Verificação do input do usuário (é número inteiro?)
                System.out.print("Entrada inválida! Favor inserir uma opção: ");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer.

            // Menu.interacao();
            if (opcao == 1) { // 1 - Adicionar item OK
                adicionarItem(lista, scanner);
                pausar(scanner);

            } else if (opcao == 2) { // 2 - Remover item OK
                if (lista.getItens().size() > 0) {
                    listarItens(lista, scanner);
                    removerItem(lista, scanner);
                } else {
                    estaVazio(lista);
                }
                pausar(scanner);

            } else if (opcao == 3) { // 3 - Listar itens OK
                if (lista.getItens().size() > 0) {
                    listarItens(lista, scanner);
                } else {
                    estaVazio(lista);
                }
                pausar(scanner);

            } else if (opcao == 4) { // 4 - Salvar lista OK?
                System.out.print("Digite o nome da lista a ser salva: ");
                nomeArquivoAtual = scanner.nextLine();
                ListaService.salvar(lista, nomeArquivoAtual, scanner);
                pausar(scanner);

            } else if (opcao == 5) { // 5 - Carregar lista
                System.out.print("Digite o nome da lista a ser carregada: ");
                String tentarCarregar = scanner.nextLine();

                ListaDeCompras listaCarregada = ListaService.carregar(tentarCarregar);
                if (listaCarregada != null) {
                    lista = listaCarregada;
                    nomeArquivoAtual = tentarCarregar;
                } else {
                    System.out.printf("A lista '%s' não existe.", tentarCarregar);
                    nomeArquivoAtual = "<sem nome>";
                }
                pausar(scanner);

            } else if (opcao == 6) { // 6 - Sair
                System.out.print("Deseja salvar antes de sair? [s/n] ");
                boolean antesSair = validarEscolha(scanner);
                if (antesSair) {
                    System.out.print("Digite o nome da lista a ser salva: ");
                    nomeArquivoAtual = scanner.nextLine();
                    ListaService.salvar(lista, nomeArquivoAtual, scanner);
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Saindo...");
                    break;
                }

            } else {
                System.out.printf("\n%d: Opção inválida!\n", opcao);
                pausar(scanner);
            }
        }
        scanner.close();
    }

    // MÉTODOS BÁSICOS:
    // Mostrar menu inicial:

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

    // MÉTODOS DE PERSISTÊNCIA:



    // MÉTODOS AUXILIARES ESPECÍFICOS:
    // Verificar se a lista já existe:
    public static void estaVazio(ListaDeCompras lista) {
        System.out.printf("\nA lista '%s' está vazia.\n",
                (lista.getNome().equals("<sem nome>") ? "sem nome" : lista.getNome()));
    }

    // MÉTODOS AUXILIARES GERAIS:
    // Validar escolha [s/n]:
    public static boolean validarEscolha(Scanner scanner) {
        String escolha;
        do {
            escolha = scanner.nextLine().trim().toLowerCase();
            if (escolha.equals("s")) return true;
            if (escolha.equals("n")) return false;
            System.out.print("Opção inválida, apenas [s/n]. ");
        } while (true);
    }
    // Pausar programa até que usuário aperte Enter:
    public static void pausar(Scanner scanner) {
        System.out.print("\nPressione Enter para voltar ao menu inicial... ");
        scanner.nextLine();
    }
    // Limpar a tela:
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}