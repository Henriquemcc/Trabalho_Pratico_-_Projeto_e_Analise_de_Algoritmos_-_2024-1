package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

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
    protected final double distanciaMinima;

    /**
     * Melhor solução encontrada para o problema.
     */
    protected Solucao melhorSolucao;

    /**
     * Constrói uma nova instância das classes herdeiras de Algoritmo.
     * @param listaPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinima Distância mínima permitida entre cada filial.
     */
    public Algoritmo(final List<PontoCandidato> listaPontosCandidatos, final double distanciaMinima) {
        this.listaPontosCandidatos = listaPontosCandidatos;
        this.distanciaMinima = distanciaMinima;
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
}
