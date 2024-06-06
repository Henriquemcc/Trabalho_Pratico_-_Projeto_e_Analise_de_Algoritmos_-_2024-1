package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe abstrata do algoritmo para resolver o problema de otimização.
 */
public abstract class Algoritmo {

    /**
     * Lista de pontos candidatos a serem filiais.
     */
    protected final List<PontoCandidato> listaPontosCandidatos;

    /**
     * Distância mínima permitida entre cada filial.
     */
    protected final double distanciaMinimaPermitida;

    /**
     * Melhor solução encontrada para o problema.
     */
    protected Solucao melhorSolucao;

    /**
     * Constrói uma nova instância das classes herdeiras de Algoritmo.
     * @param listaPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinimaPermitida Distância mínima permitida entre cada filial.
     */
    public Algoritmo(final List<PontoCandidato> listaPontosCandidatos, final double distanciaMinimaPermitida) {
        this.listaPontosCandidatos = new ArrayList<>(listaPontosCandidatos);
        final Comparator<PontoCandidato> comparatorPontoCandidato = new Comparator<PontoCandidato>() {
            @Override
            public int compare(PontoCandidato pontoCandidato, PontoCandidato t1) {
                return Integer.compare(pontoCandidato.getCustoInstalacao(), t1.getCustoInstalacao());
            }
        };
        listaPontosCandidatos.sort(comparatorPontoCandidato);
        this.distanciaMinimaPermitida = distanciaMinimaPermitida;
    }

    /**
     * Executa o algoritmo.
     */
    public abstract void executar();

    /**
     * Obtém a melhor solução.
     * @return Melhor solução.
     */
    public Solucao getMelhorSolucao() {
        return melhorSolucao;
    }

    /**
     * Verifica se a restrição está sendo cumprida
     * @param solucao Solução a ser analisada.
     * @return Valor booleano indicando se a restrição está sendo cumprida.
     */
    protected boolean verificarRestricao(final Solucao solucao) {
        return solucao.getMenorDistancia() >= distanciaMinimaPermitida && solucao.contemApenasUmPontoCandidatoPorFranquia();
    }

    /**
     * Verifica se a solução é melhor que a melhor solução encontrada até o momento.
     * @param solucao Solução a ser analisada.
     * @return Valor booleano indicando se a solução é melhor que a melhor solução encontrada até o momento.
     */
    protected boolean verificarOtimizacao(final Solucao solucao) {
        return melhorSolucao == null || solucao.getQuantidadeFranquia() > melhorSolucao.getQuantidadeFranquia() || (solucao.getQuantidadeFranquia() == melhorSolucao.getQuantidadeFranquia() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal());
    }
}
