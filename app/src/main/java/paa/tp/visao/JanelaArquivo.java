/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.visao;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que armazena todos os métodos necessários para a abertura de uma janela para abrir ou salvar arquivos.
 */
public class JanelaArquivo {

    /**
     * Objeto JFileChooser que será utilizado para exibir janela de abertura de arquivos.
     */
    private final JFileChooser jFileChooser = new JFileChooser();

    /**
     * Exibe a caixa de diálogo para salvar um arquivo.
     * @param parent Componente pai.
     * @return Caminho completo do arquivo salvo.
     */
    public String salvar(final Component parent) {
        final int result = jFileChooser.showSaveDialog(parent);
        return (result == JFileChooser.APPROVE_OPTION) ? jFileChooser.getSelectedFile().getAbsolutePath() : null;
    }

    /**
     * Exibe a caixa de diálogo para abrir um arquivo.
     * @param parent Componente pai.
     * @return Caminho completo do arquivo aberto.
     */
    public String abrir(final Component parent) {
        final int result = jFileChooser.showOpenDialog(parent);
        return (result == JFileChooser.APPROVE_OPTION) ? jFileChooser.getSelectedFile().getAbsolutePath() : null;
    }
}
