package paa.tp.visao;

import paa.tp.controlador.ControladorJanela;

import javax.swing.*;
import java.awt.*;

public class JanelaGerarDados extends JFrame {

    /**
     * Campo de texto para a quantidade de franquias.
     */
    private final IntegerIncreaseDecreaseJTextField jTextFieldQuantidadeFranquias = new IntegerIncreaseDecreaseJTextField("Quantidade Franquias", 1);

    /**
     * Campo de texto para a quantidade de lojas por franquia.
     */
    private final IntegerIncreaseDecreaseJTextField jTextFieldQuantidadeLojasPorFranquia = new IntegerIncreaseDecreaseJTextField("Quantidade de Lojas por Franquia", 1);

    /**
     * JButton OK.
     */
    private final JButton jButtonOk = new JButton("OK");

    /**
     * Constrói uma nova instância da classe JanelaGerarDados.
     */
    public JanelaGerarDados(final ControladorJanela controladorJanela) {
        // Configurando a ação a ser executada pelo botão OK
        jButtonOk.addActionListener(actionEvent -> {
            try {
                controladorJanela.gerarDadosAleatorios(Integer.parseInt(jTextFieldQuantidadeFranquias.jTextField.getText()), Integer.parseInt(jTextFieldQuantidadeLojasPorFranquia.jTextField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, String.format("NumberFormatException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            dispose();
        });

        // Configurando o Layout
        setLayout(new GridLayout(3, 1));

        // Adicionando elementos
        add(jTextFieldQuantidadeFranquias);
        add(jTextFieldQuantidadeLojasPorFranquia);
        add(jButtonOk);
    }
}
