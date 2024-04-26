package paa.tp.visao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Painel que exibe um JTextField com um número e botões: incrementar e decrementar.
 */
public class IntegerIncreaseDecreaseJTextField extends JPanel {

    /**
     * Título do painel.
     */
    private final String title;

    /**
     * Valor inicial do número no painel.
     */
    private final int initialValue;

    /**
     * JTextField com o número.
     */
    public final JTextField jTextField;

    /**
     * Botão de incrementar.
     */
    private final JButton jButtonIncrease = new JButton("+");

    /**
     * Botão de decrementar.
     */
    private final JButton jButtonDecrease = new JButton("-");

    /**
     * Constrói uma nova instância da classe IncreaseDecreaseJTextField.
     * @param title Título do painel.
     * @param initialValue Valor inicial.
     */
    public IntegerIncreaseDecreaseJTextField(final String title, final int initialValue)
    {
        // Setando os valores das variáveis
        this.title = title;
        this.initialValue = initialValue;

        // Configurando o layout e a borda
        setLayout(new FlowLayout());
        setBorder(new TitledBorder(title));

        // Configurando o JTextField
        jTextField = new JTextField(Integer.toString(initialValue));

        // Adicionando os componentes
        add(jTextField);
        add(jButtonIncrease);
        add(jButtonDecrease);

        // Configurando a ação a ser executada pelo botão de incremento
        jButtonIncrease.addActionListener(actionEvent -> {
            int valorAtual = 0;
            try {
                valorAtual = Integer.parseInt(jTextField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, String.format("NumberFormatException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            valorAtual++;
            jTextField.setText(Integer.toString(valorAtual));
        });

        // Configurando a ação a ser executada pelo botão de decremento
        jButtonDecrease.addActionListener(actionEvent -> {
            int valorAtual = 0;
            try {
                valorAtual = Integer.parseInt(jTextField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, String.format("NumberFormatException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            valorAtual--;
            jTextField.setText(Integer.toString(valorAtual));
        });
    }

}
