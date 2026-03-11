import java.util.Scanner;

public class ListaAuxiliares {
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
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
