package paa.tp.modelo.algoritmo.otimizacao;

import junit.framework.TestCase;
import paa.tp.modelo.PontoCandidato;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Testa ambos os algoritmos Branch and Bound e Força Bruta.
 */
public class AlgoritmoTest extends TestCase {

    /**
     * Realiza caso de teste aleatório comparando os algoritmos Branch and Bound e Força Bruta.
     */
    public void testAlgoritmosAleatoriamente() {
        testarAleatoriamente(new Random());
    }

    /**
     * Realiza caso de teste completamente aleatório (Secure Random) comparando os algoritmos Branch and Bound e Força Bruta.
     */
    public void testAlgoritmosAleatoriamenteSeguro() {
        testarAleatoriamente(new SecureRandom());
    }

    /**
     * Teste aleatoriamente, comparando os algoritmos Branch and Bound e Força Bruta.
     * @param random Random a ser utilizado para gerar os casos de teste.
     */
    private void testarAleatoriamente(final Random random) {
        final List<PontoCandidato> pontosCandidatos = new ArrayList<>();

        final int numeroFranquias = 10;
        final int numeroFiliaisPorFranquia = 2;
        for (int franquia = 0; franquia < numeroFranquias; franquia++)
            for (int filial = 0; filial < numeroFiliaisPorFranquia; filial++)
                pontosCandidatos.add(new PontoCandidato(franquia + 1, random.nextInt(1000), random.nextInt(1000), random.nextInt(1000000)));
        final int distanciaMinima = random.nextInt(1000);
        final ForcaBruta forcaBruta = new ForcaBruta(pontosCandidatos, distanciaMinima);
        forcaBruta.executar();
        final BranchAndBound branchAndBound = new BranchAndBound(pontosCandidatos, distanciaMinima);
        branchAndBound.executar();
        assertEquals(forcaBruta.getMelhorSolucao(), branchAndBound.getMelhorSolucao());
    }

    /**
     * Realiza o caso de teste da entrada passada pelo professor com meia restrição (distância mínima).
     */
    public void testExemploProfessorMeiaRestricao() {

        // Definindo os dados de entrada
        final List<PontoCandidato> pontosCandidatos = getPontoCandidatosExemploProfessor();
        final int distanciaMinima = 150;

        // Executando o Branch and Bound
        final BranchAndBound branchAndBound = new BranchAndBound(pontosCandidatos, distanciaMinima);
        final long tempoInicioBranchAndBound = System.nanoTime();
        branchAndBound.executar();
        final long tempoFimBranchAndBound = System.nanoTime();

        // Executando o Força Bruta
        final ForcaBruta forcaBruta = new ForcaBruta(pontosCandidatos, distanciaMinima);
        final long tempoInicioForcaBruta = System.nanoTime();
        forcaBruta.executar();
        final long tempoFimForcaBruta = System.nanoTime();

        // Verificando se a solução dos dois algoritmos são iguais
        assertEquals(forcaBruta.getMelhorSolucao().getPontosCandidatosEscolhidos(), branchAndBound.getMelhorSolucao().getPontosCandidatosEscolhidos());

        // Verificando se o tempo de execução do Branch and Bound é menor que o Força Bruta
        final long tempoGastoBranchAndBound = tempoFimBranchAndBound - tempoInicioBranchAndBound;
        final long tempoGastoForcaBruta = tempoFimForcaBruta - tempoInicioForcaBruta;
        assertTrue(tempoGastoForcaBruta > tempoGastoBranchAndBound);
    }

    /**
     * Realiza o caso de teste da entrada passada pelo professor com a máxima restrição (distância mínima).
     */
    public void testExemploProfessorRestricaoMaxima() {

        // Definindo os dados de entrada
        final List<PontoCandidato> pontosCandidatos = getPontoCandidatosExemploProfessor();
        final int distanciaMinima = 300;

        // Executando o Branch and Bound
        final BranchAndBound branchAndBound = new BranchAndBound(pontosCandidatos, distanciaMinima);
        final long tempoInicioBranchAndBound = System.nanoTime();
        branchAndBound.executar();
        final long tempoFimBranchAndBound = System.nanoTime();

        // Executando o Força Bruta
        final ForcaBruta forcaBruta = new ForcaBruta(pontosCandidatos, distanciaMinima);
        final long tempoInicioForcaBruta = System.nanoTime();
        forcaBruta.executar();
        final long tempoFimForcaBruta = System.nanoTime();

        // Verificando se a solução dos dois algoritmos são iguais
        assertEquals(forcaBruta.getMelhorSolucao().getPontosCandidatosEscolhidos(), branchAndBound.getMelhorSolucao().getPontosCandidatosEscolhidos());

        // Verificando se o tempo de execução do Branch and Bound é menor que o Força Bruta
        final long tempoGastoBranchAndBound = tempoFimBranchAndBound - tempoInicioBranchAndBound;
        final long tempoGastoForcaBruta = tempoFimForcaBruta - tempoInicioForcaBruta;
        assertTrue(tempoGastoForcaBruta > tempoGastoBranchAndBound);
    }

    /**
     * Realiza o caso de teste da entrada passada pelo professor sem nenhuma restrição (distância mínima).
     */
    public void testExemploProfessorNenhumaRestricao() {
        final List<PontoCandidato> pontosCandidatos = getPontoCandidatosExemploProfessor();
        final int distanciaMinima = 0;
        final BranchAndBound branchAndBound = new BranchAndBound(pontosCandidatos, distanciaMinima);
        branchAndBound.executar();
        assertEquals(branchAndBound.getMelhorSolucao().getPontosCandidatosEscolhidos(), pontosCandidatos);
        final ForcaBruta forcaBruta = new ForcaBruta(pontosCandidatos, distanciaMinima);
        forcaBruta.executar();
        assertEquals(forcaBruta.getMelhorSolucao().getPontosCandidatosEscolhidos(), pontosCandidatos);
    }

    /**
     * Obtém os pontos candidatos do exemplo do professor.
     * @return Pontos candidatos do exemplo do professor.
     */
    private static List<PontoCandidato> getPontoCandidatosExemploProfessor() {
        final List<PontoCandidato> pontosCandidatos = new ArrayList<>();
        pontosCandidatos.add(new PontoCandidato(1, 50, 150, 2000));
        pontosCandidatos.add(new PontoCandidato(1, 150, 150, 1000));
        pontosCandidatos.add(new PontoCandidato(2, 200, 180, 500));
        pontosCandidatos.add(new PontoCandidato(2, 220, 200, 800));
        pontosCandidatos.add(new PontoCandidato(2, 300, 150, 1000));
        pontosCandidatos.add(new PontoCandidato(3, 100, 250, 800));
        pontosCandidatos.add(new PontoCandidato(3, 180, 220, 500));
        pontosCandidatos.add(new PontoCandidato(3, 150, 300, 700));
        pontosCandidatos.add(new PontoCandidato(4, 220, 220, 400));
        pontosCandidatos.add(new PontoCandidato(4, 300, 300, 1000));
        return pontosCandidatos;
    }

}