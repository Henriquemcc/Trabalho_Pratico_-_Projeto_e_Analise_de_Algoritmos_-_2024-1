/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.modelo;

import java.util.*;

/**
 * Classe com método para trabalhar com diciionários de pontos candidatos.
 */
public class DictionaryPontosCandidatos {

    /**
     * Clona um dicionário de pontos candidatos.
     * @param source Dicionário de pontos candidatos a ser clonado.
     * @return Dicionário de pontos candidatos clonado.
     */
    public static Dictionary<Integer, List<PontoCandidato>> clone(final Dictionary<Integer, List<PontoCandidato>> source) {
        final Dictionary<Integer, List<PontoCandidato>> clone = new Hashtable<>();
        final Enumeration<Integer> keys = source.keys();
        while (keys.hasMoreElements()) {
            final Integer key = keys.nextElement();
            final List<PontoCandidato> pontosCandidatos = new ArrayList<>(source.get(key));
            clone.put(key, new ArrayList<>(pontosCandidatos));
        }
        return clone;
    }
}
