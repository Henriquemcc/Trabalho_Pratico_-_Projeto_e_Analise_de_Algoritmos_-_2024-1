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
            final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();
            final Stack<Integer> pilhaPosicaoAnalisada = new Stack<>();

            // Adicionando primeiros elementos na pilha
            pilhaPontosEscolhidos.add(new ArrayList<>(listaPontosCandidatos));
            pilhaPosicaoAnalisada.add(0);

            // Enquanto as pilhas não estiverem vazias serão geradas as combinações
            while (!pilhaPosicaoAnalisada.isEmpty() || !pilhaPontosEscolhidos.isEmpty()) {
                final int posicaoAnalisada = pilhaPosicaoAnalisada.pop();
                final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();

                // Fim da recursão
                if (pontosEscolhidos.size() == tamanhoCombinacao) {
                    final Solucao solucao = new Solucao(pontosEscolhidos);
                    if (solucao.getDistanciaMinima() >= distanciaMinima && (melhorSolucao == null || solucao.getPontosCandidatosEscolhidos().size() > melhorSolucao.getPontosCandidatosEscolhidos().size() || (solucao.getPontosCandidatosEscolhidos().size() == melhorSolucao.getPontosCandidatosEscolhidos().size() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal())))
                        melhorSolucao = solucao;
                }

                // Removendo pontos
                else for (int i = posicaoAnalisada; i < listaPontosCandidatos.size(); i++) {
                    final List<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.remove(listaPontosCandidatos.get(i));
                    pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                    pilhaPosicaoAnalisada.push(posicaoAnalisada+1);
                }
            }
        }
    }


}
