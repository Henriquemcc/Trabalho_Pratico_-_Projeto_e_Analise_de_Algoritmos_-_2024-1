package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por executar o algoritmo da força bruta.
 */
public class ForcaBruta extends Algoritmo {

    /**
     * Constrói uma nova instância da classe ForcaBruta.
     * @param listaPontosCandidatos Lista de pontos candidatos a serem filiais.
     * @param distanciaMinima Distância mínima permitida entre cada filial.
     */
    public ForcaBruta(List<PontoCandidato> listaPontosCandidatos, double distanciaMinima) {
        super(listaPontosCandidatos, distanciaMinima);
    }

    /**
     * Executa o algoritmo de força bruta.
     */
    @Override
    public void executar() {
        // Obtendo todas as soluções
        final Map<Integer, List<Solucao>> solucoes = gerarSolucoes();

        // Obtendo a melhor solução
        melhorSolucao = null;
        for (int quantidadeElementosCombinados = listaPontosCandidatos.size(); quantidadeElementosCombinados > 0; quantidadeElementosCombinados--) {

            // Saindo do laço caso já tenha sido encontrado uma melhor solução
            if (melhorSolucao != null)
                break;

            // Obtendo todas as soluções possíveis para esta quantidade de elementos combinados
            final List<Solucao> solucoesCujaQuantidadeElementosCombinadosSejaI = solucoes.get(quantidadeElementosCombinados);

            // Identificando qual dessas soluções é a melhor solução
            if (solucoesCujaQuantidadeElementosCombinadosSejaI != null) for (final Solucao solucao: solucoesCujaQuantidadeElementosCombinadosSejaI) {

                // Verificando se atende a restrição de distância mínima, e se é melhor que a melhor solução encontrada anteriormente
                if (solucao.getDistanciaMinima() >= distanciaMinima && (melhorSolucao == null || solucao.getCustoTotal() < melhorSolucao.getCustoTotal()))
                    melhorSolucao = solucao;
            }
        }
    }

    /**
     * Gera todas as soluções possíveis.
     * @return Map das soluções para a quantidade de elementos combinados.
     */
    private Map<Integer, List<Solucao>> gerarSolucoes() {
        final HashMap<Integer, List<Solucao>> mapQuantidadeElementosCombinadosSolucoes = new HashMap<>();
        for (int i = 1; i <= listaPontosCandidatos.size(); i++) {
            final Combinacoes<PontoCandidato> combinacoes = new Combinacoes<PontoCandidato>(listaPontosCandidatos, i);
            final ArrayList<Solucao> solucoes = new ArrayList<>();
            for (final List<PontoCandidato> combinacao: combinacoes.getCombinacoes())
                solucoes.add(new Solucao(combinacao));
            mapQuantidadeElementosCombinadosSolucoes.put(i, solucoes);
        }
        return mapQuantidadeElementosCombinadosSolucoes;
    }


}
