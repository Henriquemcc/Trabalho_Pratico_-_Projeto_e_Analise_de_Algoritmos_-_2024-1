package paa.tp.modelo.algoritmo.otimizacao;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa as 'quantidadeElementosCombinados' combinações obtidas a partir de 'entrada'.
 * @param <T> Tipo dos elementos combinados.
 */
public class Combinacoes<T>{

    /**
     * Elementos de entrada a serem combinados.
     */
    private final List<T> entrada;

    /**
     * Combinações obtidas a partir dos elementos de entrada.
     */
    private final List<List<T>> combinacoes = new ArrayList<>();

    /**
     * Quantidade de elementos combinados entre si.
     */
    private final int quantidadeElementosCombinados;

    /**
     * Gera as combinações recursivamente
     * @param inicio Posição inicial.
     * @param atual Elementos combinados atualmente.
     */
    private void gerarCombinacao(final int inicio, List<T> atual) {
        if (atual.size() == quantidadeElementosCombinados) {
            combinacoes.add(new ArrayList<T>(atual));
        } else for (int i = inicio; i < entrada.size(); i++) {
            atual.add(entrada.get(i));
            gerarCombinacao(i+1, atual);
            atual.remove(atual.size() - 1);
        }
    }

    /**
     * Constrói uma nova instância da classe Combinacoes.
     * @param entrada Lista de elementos de entrada.
     * @param quantidadeElementosCombinados Quantidade de elementos a serem combinados entre si.
     */
    public Combinacoes(final List<T> entrada, final int quantidadeElementosCombinados){
        this.entrada = entrada;
        this.quantidadeElementosCombinados = quantidadeElementosCombinados;
        gerarCombinacao(0, new ArrayList<>());
    }

    /**
     * Obtém a lista de combinações.
     * @return Lista de combinações.
     */
    public List<List<T>> getCombinacoes() {
        return combinacoes;
    }
}
