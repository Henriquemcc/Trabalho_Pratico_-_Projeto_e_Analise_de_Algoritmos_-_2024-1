/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.visao;

import paa.tp.controlador.ControladorJanela;

import javax.swing.*;
import java.awt.*;

/**
 * Janela que exibe opções para gerar dados aleatóriamente.
 */
public class JanelaGerarDados extends JFrame {

    /**
     * Campo de texto para a quantidade de franquias.
     */
    private final IncreaseDecreaseJTextField jTextFieldQuantidadeFranquias = new IncreaseDecreaseJTextField("Quantidade Franquias", 1);

    /**
     * Campo de texto para a quantidade de lojas por franquia.
     */
    private final IncreaseDecreaseJTextField jTextFieldQuantidadeLojasPorFranquia = new IncreaseDecreaseJTextField("Quantidade de Lojas por Franquia", 1);

    /**
     * JButton OK.
     */
    private final JButton jButtonOk = new JButton("OK");

    /**
     * JButton Cancelar.
     */
    private final JButton jButtonCancelar = new JButton("Cancelar");

    /**
     * Constrói uma nova instância da classe JanelaGerarDados.
     * @param controladorJanela Controlador da janela que instanciou esta classe.
     */
    public JanelaGerarDados(final ControladorJanela controladorJanela) {

        // Configurando a ação a ser executada pelo botão OK
        jButtonOk.addActionListener(actionEvent -> {
            try {
                controladorJanela.gerarDadosAleatorios(Integer.parseInt(jTextFieldQuantidadeFranquias.jTextField.getText()), Integer.parseInt(jTextFieldQuantidadeLojasPorFranquia.jTextField.getText()));
                dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, String.format("NumberFormatException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });

        // Configurando a ação a ser executada pelo botão Cancelar
        jButtonCancelar.addActionListener(actionEvent -> dispose());

        // Configurando o Layout
        setLayout(new GridLayout(4, 1));

        // Adicionando elementos
        add(jTextFieldQuantidadeFranquias);
        add(jTextFieldQuantidadeLojasPorFranquia);
        add(jButtonOk);
        add(jButtonCancelar);
    }
}
