package paa.tp.modelo.algoritmo.otimizacao;

import junit.framework.TestCase;
import org.junit.Test;
import paa.tp.modelo.PontoCandidato;

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
    @Test
    public void testAlgoritmos() {
        final List<PontoCandidato> pontosCandidatos = new ArrayList<>();
        final Random random = new Random();
        final int numeroFranquias = 10;
        final int numeroFiliaisPorFranquia = 2;
        for (int franquia = 0; franquia < numeroFranquias; franquia++)
            for (int filial = 0; filial < numeroFiliaisPorFranquia; filial++)
                pontosCandidatos.add(new PontoCandidato(franquia + 1, random.nextInt(1000), random.nextInt(1000), random.nextInt(1000000)));
        final int distanciaMinima = 10;
        final ForcaBruta forcaBruta = new ForcaBruta(pontosCandidatos, distanciaMinima);
        forcaBruta.executar();
        final BranchAndBound branchAndBound = new BranchAndBound(pontosCandidatos, distanciaMinima);
        branchAndBound.executar();
        assertEquals(forcaBruta.getMelhorSolucao(), branchAndBound.getMelhorSolucao());
    }

}