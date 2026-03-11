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
            ListaAuxiliares.limparTela();
            Menu.mostrar();

            while (!scanner.hasNextInt()) { // Verificação do input do usuário (é número inteiro?)
                System.out.print("Entrada inválida! Favor inserir uma opção: ");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer.

            // Menu.interacao();
            if (opcao == 1) { // 1 - Adicionar item OK
                ListaService.adicionarItem(lista, scanner);
                ListaAuxiliares.pausar(scanner);

            } else if (opcao == 2) { // 2 - Remover item OK
                if (lista.getItens().size() > 0) {
                    ListaService.listarItens(lista, scanner);
                    ListaService.removerItem(lista, scanner);
                } else {
                    ListaAuxiliares.estaVazio(lista);
                }
                ListaAuxiliares.pausar(scanner);

            } else if (opcao == 3) { // 3 - Listar itens OK
                if (lista.getItens().size() > 0) {
                    ListaService.listarItens(lista, scanner);
                } else {
                    ListaAuxiliares.estaVazio(lista);
                }
                ListaAuxiliares.pausar(scanner);

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
        scanner.close();
    }
}