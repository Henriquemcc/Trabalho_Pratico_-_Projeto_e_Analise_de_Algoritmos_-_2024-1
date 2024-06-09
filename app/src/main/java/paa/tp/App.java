/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp;

import paa.tp.controlador.ControladorJanela;
import paa.tp.controlador.ControladorLinhaComando;

/**
 * Classe principal.
 */
public class App {
    /**
     * Função principal.
     * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) throws Exception {

        // Executando linha de comando
        if (args.length > 0)
            new ControladorLinhaComando(args);

        // Executando janela
        else
            new ControladorJanela();
    }
}
