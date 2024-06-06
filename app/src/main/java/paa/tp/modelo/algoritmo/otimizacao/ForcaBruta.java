package paa.tp.modelo.algoritmo.otimizacao;

import paa.tp.modelo.PontoCandidato;

import java.util.*;

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

        // Iterando sobre diferentes tamanho de soluções
        for (int tamanhoCombinacao = listaPontosCandidatos.size(); tamanhoCombinacao > 0 && melhorSolucao == null; tamanhoCombinacao--) {

            // Pilha que será utilizada para transformar o problema recursivo em iterativo
            final Stack<Integer> pilhaIndice = new Stack<>();
            final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();

            // Adicionando primeiros elementos na pilha
            pilhaIndice.push(0);
            pilhaPontosEscolhidos.push(new ArrayList<>());

            // Enquanto as pilhas não estiverem vazias serão geradas as combinações
            while (!pilhaIndice.isEmpty()) {
                final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();
                final int indice = pilhaIndice.pop();
                final Solucao solucao = new Solucao(pontosEscolhidos);

                // Adicionando à melhor solução
                // Verificando restrição
                if (indice >= listaPontosCandidatos.size()) {
                    if (solucao.getMenorDistancia() >= distanciaMinimaPermitida && solucao.contemApenasUmPontoCandidatoPorFranquia()) {
                        // Verificando otimização
                        if (melhorSolucao == null || solucao.getQuantidadePontos() > melhorSolucao.getQuantidadePontos() || (solucao.getQuantidadePontos() == melhorSolucao.getQuantidadePontos() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal()))
                            melhorSolucao = solucao;
                    }
                }

                // (pseudo) recursão
                else
                {
                    final ArrayList<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.add(listaPontosCandidatos.get(indice));

                    // Adicionando elemento
                    pilhaIndice.push(indice+1);
                    pilhaPontosEscolhidos.push(novosPontosEscolhidos);

                    // Não adicionando elemento
                    pilhaIndice.push(indice + 1);
                    pilhaPontosEscolhidos.push(pontosEscolhidos);
                }
            }
        }
    }


}
