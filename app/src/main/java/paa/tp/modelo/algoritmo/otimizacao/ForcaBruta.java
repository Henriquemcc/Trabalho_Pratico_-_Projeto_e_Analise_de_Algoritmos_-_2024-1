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

        // Pilha que será utilizada para transformar o problema recursivo em iterativo
        final Stack<Integer> pilhaIndice = new Stack<>();
        final Stack<List<PontoCandidato>> pilhaPontosEscolhidos = new Stack<>();

        // Adicionando primeiros elementos na pilha
        pilhaIndice.push(0);
        pilhaPontosEscolhidos.push(new ArrayList<>(listaPontosCandidatos));

        // Enquanto as pilhas não estiverem vazias serão geradas as combinações
        while (!pilhaIndice.isEmpty()) {
            final List<PontoCandidato> pontosEscolhidos = pilhaPontosEscolhidos.pop();
            final int indice = pilhaIndice.pop();
            final Solucao solucao = new Solucao(pontosEscolhidos);

            // Verificando a restrição
            if (verificarRestricao(solucao))
            {
                // Fim da (pseudo) recursão
                if (indice >= listaPontosCandidatos.size()) {
                    if (verificarOtimizacao(solucao)) {
                        melhorSolucao = solucao;
                    }
                }

                // Adicionando elementos
                else {
                    // Removendo elemento indice
                    final List<PontoCandidato> novosPontosEscolhidos = new ArrayList<>(pontosEscolhidos);
                    novosPontosEscolhidos.remove(listaPontosCandidatos.get(indice));
                    pilhaIndice.push(indice + 1);
                    pilhaPontosEscolhidos.push(novosPontosEscolhidos);

                    // Não removendo elemento indice
                    pilhaIndice.push(indice + 1);
                    pilhaPontosEscolhidos.push(pontosEscolhidos);
                }
            }
        }
    }


}
