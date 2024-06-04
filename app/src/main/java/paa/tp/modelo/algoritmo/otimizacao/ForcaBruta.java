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
            final Stack<Hashtable<Integer,PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();
            final Stack<Integer> pilhaIndice = new Stack<>();

            // Adicionando tabela hash vazia
            pilhaPontosEscolhidos.push(new Hashtable<>());
            pilhaIndice.push(0);

            // Enquanto as pilhas não estiverem vazias. (As pilhas são manipuladas juntas)
            while (!pilhaIndice.isEmpty()) {

                // Desempilhando
                final int indice = pilhaIndice.pop();
                final Hashtable<Integer,PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();

                // Fim da (pseudo) recursão
                if (indice >= listaPontosCandidatos.size()) {
                    final Solucao solucao = new Solucao(pontosEscolhidos);

                    // Adicionando melhor solução
                    if (solucao.getDistanciaMinima() >= distanciaMinima && (melhorSolucao == null || solucao.getPontosCandidatosEscolhidos().size() > melhorSolucao.getPontosCandidatosEscolhidos().size() || (solucao.getPontosCandidatosEscolhidos().size() == melhorSolucao.getPontosCandidatosEscolhidos().size() && solucao.getCustoTotal() < melhorSolucao.getCustoTotal())))
                        melhorSolucao = solucao;
                }

                // Adicionando pontos
                else {
                    for (int i = indice; i < listaPontosCandidatos.size(); i++){

                        // Nova tabela hash
                        final Hashtable<Integer, PontoCandidato> novosPontosEscolhidos = new Hashtable<>(pontosEscolhidos);
                        novosPontosEscolhidos.put(listaPontosCandidatos.get(i).getNumeroFranquia(), listaPontosCandidatos.get(i));

                        // Adicionando elementos na pilha
                        pilhaIndice.push(indice+1);
                        pilhaPontosEscolhidos.push(novosPontosEscolhidos);
                    }
                }

            }

        }
    }


}
