/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.visao;

import paa.tp.modelo.PontoCandidato;
import paa.tp.modelo.algoritmo.otimizacao.Solucao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static paa.tp.visao.GerarCor.gerarCor;

/**
 * Janela que mostra os resultados da execução dos algoritmos.
 */
public class JanelaResultados extends JFrame {

    /**
     * Cria a área de exibição de resultados.
     *
     * @param solucao    Solução a ser mostrada.
     * @param tempoGasto Tempo gasto para resolver o problema
     * @return Um JPanel com a solução e o tempo gasto.
     */
    private JPanel criarAreaExibicaoResultado(final Solucao solucao, final long tempoGasto) {

        // Criando o JPanel para ser a área de exibição do resultado
        final JPanel areaExibicaoResultado = new JPanel();

        // Configurando o LayOut
        areaExibicaoResultado.setLayout(new BoxLayout(areaExibicaoResultado, BoxLayout.Y_AXIS));

        // Criando a tabela da solução
        final String[] nomesColunas = {"Número franquia", "Coordenada X", "Coordenada Y", "Custo", "Cor"};
        final List<PontoCandidato> pontosCandidatosEscolhidos = solucao.getPontosCandidatosEscolhidos();
        final Object[][] dados = new Object[pontosCandidatosEscolhidos.size()][5];
        for (int i = 0; i < pontosCandidatosEscolhidos.size(); i++) {
            dados[i][0] = String.valueOf(pontosCandidatosEscolhidos.get(i).getNumeroFranquia());
            dados[i][1] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCoordenadaX());
            dados[i][2] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCoordenadaY());
            dados[i][3] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCustoInstalacao());

            // Gerando ícone da cor
            final BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
            final Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setColor(gerarCor(pontosCandidatosEscolhidos.get(i).getNumeroFranquia()));
            graphics2D.fillRect(0, 0, 16, 16);
            graphics2D.dispose();
            dados[i][4] = new ImageIcon(bufferedImage);
        }
        final DefaultTableModel model = new DefaultTableModel(dados, nomesColunas) {
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        final JTable jTable = new JTable(model);

        // Adicionando tabela
        areaExibicaoResultado.add(new JScrollPane(jTable));

        // Criando a área de texto para exibir o tempo gasto
        final JTextArea jTextAreaTempoGasto = new JTextArea(String.format("Tempo gasto: %dns", tempoGasto));
        jTextAreaTempoGasto.setEditable(false);

        // Adicionando texto
        areaExibicaoResultado.add(jTextAreaTempoGasto);

        return areaExibicaoResultado;
    }

    /**
     * Constrói uma nova instância da classe JanelaResultados.
     *
     * @param solucao    Solução a ser exibida.
     * @param tempoGasto Tempo gasto para resolver o problema (em nano segundo).
     */
    public JanelaResultados(final Solucao solucao, final long tempoGasto) {

        // Configurando a janela
        super("Resultado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Criando a área de exibição de resultado e a adicionando ao JScrollPane este ao JFrame
        add(new JScrollPane(criarAreaExibicaoResultado(solucao, tempoGasto)));
    }
}
