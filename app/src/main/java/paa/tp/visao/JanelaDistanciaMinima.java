/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.visao;

import paa.tp.controlador.ControladorJanela;

import javax.swing.*;
import java.awt.*;

/**
 * Janela que exibe a distância mínima entre duas filiais.
 */
public class JanelaDistanciaMinima extends JFrame {

    /**
     * Campo de texto para a distância mínima entre duas filiais.
     */
    private final IncreaseDecreaseJTextField jTextFieldDistanciaMinima;

    /**
     * JButton OK.
     */
    private final JButton jButtonOk = new JButton("OK");

    /**
     * JButton Cancelar.
     */
    private final JButton jButtonCancelar = new JButton("Cancelar");

    /**
     * Constrói uma nova instância da classe JanelaDistanciaMinima.
     * @param controladorJanela Controlador de janela que instanciou esta classe.
     */
    public JanelaDistanciaMinima(final ControladorJanela controladorJanela) {

       jTextFieldDistanciaMinima = new IncreaseDecreaseJTextField("Distância mínima", controladorJanela.getDistanciaMinima());

        // Configurando a ação a ser executada pelo botão OK
        jButtonOk.addActionListener(actionEvent -> {
            try {
                controladorJanela.setDistanciaMinima(Double.parseDouble(jTextFieldDistanciaMinima.jTextField.getText()));
                dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, String.format("NumberFormatException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });

        // Configurando a ação a ser executada pelo botão Cancelar
        jButtonCancelar.addActionListener(actionEvent -> dispose());

        // Configurando o Layout
        setLayout(new GridLayout(3, 1));

        // Adicionando elementos
        add(jTextFieldDistanciaMinima);
        add(jButtonOk);
        add(jButtonCancelar);
    }
}
