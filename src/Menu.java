import java.util.Scanner;

public class Menu {
    public static void mostrar() {
        System.out.print("\n1 - Adicionar item\n2 - Remover item\n3 - Listar itens\n4 - Salvar lista\n5 - Carregar lista\n6 - Sair\n\nDigite a opção desejada: ");
    }

    public static void interacao(ListaDeCompras lista, String nomeArquivoAtual, int opcao, Scanner scanner) {
        while (true) {
            ListaAuxiliares.limparTela();
            mostrar();

            while (!scanner.hasNextInt()) { // Verificação do input do usuário (é número inteiro?)
                System.out.print("Entrada inválida! Favor inserir uma opção: ");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer.

            if (opcao == 1) { // 1 - Adicionar item OK
                menuAdicionar(lista, scanner);
            } else if (opcao == 2) { // 2 - Remover item OK
                menuRemover(lista, scanner);
            } else if (opcao == 3) { // 3 - Listar itens OK
                menuListarItens(lista, scanner);
            } else if (opcao == 4) { // 4 - Salvar lista OK?
                System.out.print("Digite o nome da lista a ser salva: ");
                nomeArquivoAtual = scanner.nextLine();
                ListaService.salvar(lista, nomeArquivoAtual, scanner);
                ListaAuxiliares.pausar(scanner);

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
                ListaAuxiliares.pausar(scanner);

            } else if (opcao == 6) { // 6 - Sair
                System.out.print("Deseja salvar antes de sair? [s/n] ");
                boolean antesSair = ListaAuxiliares.validarEscolha(scanner);
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
                ListaAuxiliares.pausar(scanner);
            }
        }
    }

    public static void menuAdicionar(ListaDeCompras lista, Scanner scanner) {
        ListaService.adicionarItem(lista, scanner);
        ListaAuxiliares.pausar(scanner);
    }

    public static void menuRemover(ListaDeCompras lista, Scanner scanner) {
        if (lista.getItens().size() > 0) {
            ListaService.listarItens(lista, scanner);
            ListaService.removerItem(lista, scanner);
        } else {
            ListaAuxiliares.estaVazio(lista);
        }
        ListaAuxiliares.pausar(scanner);
    }
    public static void menuListarItens(ListaDeCompras lista, Scanner scanner) {
        if (lista.getItens().size() > 0) {
            ListaService.listarItens(lista, scanner);
        } else {
            ListaAuxiliares.estaVazio(lista);
        }
        ListaAuxiliares.pausar(scanner);
    }


}
