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
        executar(0, new Solucao(new ArrayList<>()));
    }

    /**
     * Executa o algoritmo Branch and Bound recursivamente.
     * @param indice Índice da Lista de pontos candidatos a ser adicionado á lista dos pontos escolhidos.
     * @param solucao Solução anterior.
     */
    private void executar(final int indice, final Solucao solucao) {

        // Lançando exceção se o valor de solucao for nulo
        if (solucao == null)
            throw new NullPointerException("O valor do parâmetro 'Solucao' não pode ser nulo");

        // Alterando o maior elemento
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

        /**
         * IF
         * Condição de parada da recursão.
         */
        if (indice < listaPontosCandidatos.size())
        {
            // Adicionando o elemento atual
            final List<PontoCandidato> pontosCandidatosSolucao = solucao.getPontosCandidatosEscolhidos();
            final ArrayList<PontoCandidato> pontosCandidatosNovaSolucao = new ArrayList<>(pontosCandidatosSolucao.size());
            pontosCandidatosNovaSolucao.addAll(pontosCandidatosSolucao);
            pontosCandidatosNovaSolucao.add(listaPontosCandidatos.get(indice));
            final Solucao novaSolucao = new Solucao(pontosCandidatosNovaSolucao);
            executar(indice+1, novaSolucao);

            // Pulando o elemento atual
            executar(indice+1, new Solucao(solucao.getPontosCandidatosEscolhidos()));
        }

    }


}
