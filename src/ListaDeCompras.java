import java.util.ArrayList;

public class ListaDeCompras {

    private ArrayList<String> itens;
    private String nome;

    public ListaDeCompras (String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
    }

    public void adicionar(String item) {
        itens.add(item);
    }

    public void remover(int indice) {
        itens.remove(indice);
    }

    public ArrayList<String> getItens() {
        return itens;
    }

    public String getNome() {
        return nome;
    }
}
