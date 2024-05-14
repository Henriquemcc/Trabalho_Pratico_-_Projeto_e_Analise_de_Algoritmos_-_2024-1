package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
import java.util.List;

/**
 * Algoritmo Branch and Bound.
 */
public class BranchAndBound extends Algoritmo{
    /**
     * Constrói uma nova instância de Branch and Bound de Algoritmo.
     * @param listaPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinima Distância mínima permitida entre cada filial.
     */
    public BranchAndBound(List<PontoCandidato> listaPontosCandidatos, double distanciaMinima) {
        super(listaPontosCandidatos, distanciaMinima);
    }

    @Override
    public void executar() {
        final ArrayList<PontoCandidato> copiaPontoCandidatos = new ArrayList<>(listaPontosCandidatos.size());
        copiaPontoCandidatos.addAll(listaPontosCandidatos);
        executar(0, new Solucao(copiaPontoCandidatos));
    }

    private void executar(final int indice, final Solucao solucao) {

        // Adicionando o elemento atual à melhor solução
        /**
         * IF
         * - Verificando se o elemento avaliado atende as restrições.
         *   Inserindo se:
         *    - não houver melhor solução,
         *    - a quantidade de pontos do elemento avaliado é maior que a quantidade de pontos da melhor solução,
         *    - o custo do elemento avaliado é menor que o custo da melhor solução (e a quantidade de elementos da solução avaliada for igual da melhor solução).
         */
        if (solucao.getDistanciaMinima() >= distanciaMinima && (melhorSolucao == null || solucao.getPontosCandidatosEscolhidos().size() > melhorSolucao.getPontosCandidatosEscolhidos().size() || (solucao.getPontosCandidatosEscolhidos().size() == melhorSolucao.getPontosCandidatosEscolhidos().size() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal())))
            melhorSolucao = solucao;

        // Removendo elementos
        else if (indice < listaPontosCandidatos.size())
        {
            // Removendo o elemento atual
            final ArrayList<PontoCandidato> copiaPontosCandidatos = new ArrayList<>(solucao.getPontosCandidatosEscolhidos().size());
            copiaPontosCandidatos.addAll(solucao.getPontosCandidatosEscolhidos());
            copiaPontosCandidatos.remove(listaPontosCandidatos.get(indice));
            executar(indice+1, new Solucao(copiaPontosCandidatos));

            // Não removendo o elemento atual
            executar(indice+1, solucao);
        }
    }
}
