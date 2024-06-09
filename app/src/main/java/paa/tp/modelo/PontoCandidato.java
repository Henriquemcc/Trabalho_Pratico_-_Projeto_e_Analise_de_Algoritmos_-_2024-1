/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.modelo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Classe que armazena todos os dados de um ponto candidato.
 */
public class PontoCandidato implements Serializable, Comparable {

    /**
     * Compara duas instâncias de PontoCandidato.
     */
    public static final Comparator<PontoCandidato> comparadorPontoCandidato = Comparator.comparing(PontoCandidato::getCustoInstalacao).thenComparing(PontoCandidato::getNumeroFranquia).thenComparing(PontoCandidato::getCoordenadaX).thenComparing(PontoCandidato::getCoordenadaY);

    /**
     * Número da franquia.
     */
    private final int numeroFranquia;

    /**
     * Coordenada X.
     */
    private final int coordenadaX;

    /**
     * Coordenada Y.
     */
    private final int coordenadaY;

    /**
     * Custo de instalação.
     */
    private final int custoInstalacao;

    /**
     * Constrói uma nova instância de PontoCandidato.
     * @param numeroFranquia Número da franquia.
     * @param coordenadaX Coordenada X.
     * @param coordenadaY Coordenada Y.
     * @param custoInstalacao Custo de instalação.
     */
    public PontoCandidato(final int numeroFranquia, final int coordenadaX, final int coordenadaY, final int custoInstalacao)
    {
        this.numeroFranquia = numeroFranquia;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.custoInstalacao = custoInstalacao;
    }

    /**
     * Obtém o número da franquia.
     * @return Número da franquia.
     */
    public int getNumeroFranquia() {
        return numeroFranquia;
    }

    /**
     * Obtém a coordenada X.
     * @return Coordenada X.
     */
    public int getCoordenadaX() {
        return coordenadaX;
    }

    /**
     * Obtém a coordenada Y.
     * @return Coordenada Y.
     */
    public int getCoordenadaY() {
        return coordenadaY;
    }

    /**
     * Obtém o custo de instalação.
     * @return Custa de instalação.
     */
    public int getCustoInstalacao() {
        return custoInstalacao;
    }

    /**
     * Converte o ponto candidato em uma string.
     * @return String com as informações do ponto candidato.
     */
    @Override
    public String toString() {
        return "PontoCandidato{" +
                "numeroFranquia=" + numeroFranquia +
                ", coordenadaX=" + coordenadaX +
                ", coordenadaY=" + coordenadaY +
                ", custoInstalacao=" + custoInstalacao +
                '}';
    }

    /**
     * Verifica se dois pontos candidatos são iguais.
     * @param o Outro ponto candidato a ser comparado com este.
     * @return Valor booleano indicando se os dois pontos são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PontoCandidato that = (PontoCandidato) o;
        return numeroFranquia == that.numeroFranquia && coordenadaX == that.coordenadaX && coordenadaY == that.coordenadaY && custoInstalacao == that.custoInstalacao;
    }

    /**
     * Obtém o código hash do Ponto Candidato.
     * @return Código hash do Ponto Candidato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeroFranquia, coordenadaX, coordenadaY, custoInstalacao);
    }

    /**
     * Compara este objeto com outro.
     * @param outroPontoCandidato Objeto a ser comparado.
     * @return Valor inteiro indicando a ordem dos objetos.
     */
    @Override
    public int compareTo(Object outroPontoCandidato) {
        if (outroPontoCandidato == null)
            return Integer.MAX_VALUE;
        if (outroPontoCandidato instanceof PontoCandidato)
            return this.compareTo((PontoCandidato)outroPontoCandidato);
        return Integer.MAX_VALUE;
    }

    /**
     * Compara esta instância de PontoCandidato com outra.
     * @param outroPontoCandidato Outra instância da Pontocandidato a ser comparada.
     * @return Valor inteiro indicando a ordem dos objetos.
     */
    public int compareTo(PontoCandidato outroPontoCandidato) {
        return comparadorPontoCandidato.compare(this, outroPontoCandidato);
    }
}
