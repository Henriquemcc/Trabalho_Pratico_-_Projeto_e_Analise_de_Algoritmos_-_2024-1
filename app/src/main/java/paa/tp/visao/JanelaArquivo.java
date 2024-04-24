package paa.tp.visao;

import javax.swing.*;
import java.awt.*;

public class JanelaArquivo {

    private final JFileChooser jFileChooser = new JFileChooser();

    /**
     * Exibe a caixa de diálogo para salvar um arquivo.
     * @param parent Componente pai.
     * @return Caminho completo do arquivo salvo.
     */
    public String salvar(Component parent) {
        final int result = jFileChooser.showSaveDialog(parent);
        return (result == JFileChooser.APPROVE_OPTION) ? jFileChooser.getSelectedFile().getAbsolutePath() : null;
    }

    /**
     * Exibe a caixa de diálogo para abrir um arquivo.
     * @param parent Componente pai.
     * @return Caminho completo do arquivo aberto.
     */
    public String abrir(Component parent) {
        final int result = jFileChooser.showOpenDialog(parent);
        return (result == JFileChooser.APPROVE_OPTION) ? jFileChooser.getSelectedFile().getAbsolutePath() : null;
    }
}
