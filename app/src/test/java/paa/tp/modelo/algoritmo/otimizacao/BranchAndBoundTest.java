package paa.tp.modelo.algoritmo.otimizacao;

import junit.framework.TestCase;
import org.junit.Test;
import paa.tp.modelo.PontoCandidato;

import java.util.ArrayList;
import java.util.List;

/**
 * Testa o algoritmo BranchAndBound.
 */
public class BranchAndBoundTest extends TestCase {

    /**
     * Realiza o caso de teste da entrada passada pelo professor sem nenhuma restrição (distância mínima).
     */
    @Test
    public void testExemploProfessor() {
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
        final int distanciaMinima = 0;
        final BranchAndBound branchAndBound = new BranchAndBound(pontosCandidatos, distanciaMinima);
        branchAndBound.executar();
        assertEquals(branchAndBound.getMelhorSolucao().getPontosCandidatosEscolhidos(), pontosCandidatos);
    }
}