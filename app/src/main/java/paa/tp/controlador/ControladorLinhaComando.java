/**
 * @author Henrique Mendonça Castelar Campos
 * @author Thiago Gomes Martins
 * */

package paa.tp.controlador;

import paa.tp.modelo.ListaPontosCandidatos;
import paa.tp.modelo.algoritmo.otimizacao.Algoritmo;
import paa.tp.modelo.algoritmo.otimizacao.BranchAndBound;
import paa.tp.modelo.algoritmo.otimizacao.ForcaBruta;

import java.io.File;

/**
 * Controlador de Linha de Comando. Permite que o programa seja utilizado no modo linha de comando.
 */
public class ControladorLinhaComando {

    /**
     * Caminho do arquivo de dados.
     */
    private String caminhoArquivoDados;

    /**
     * Distância mínima.
     */
    private double distanciaMinima;

    /**
     * Algoritmo a ser utilizado.
     */
    private Class algoritmo;

    /**
     * Operação a ser executada.
     */
    private Operacao operacao;

    /**
     * Lista de pontos candidatos a serem processados.
     */
    private final ListaPontosCandidatos listaPontosCandidatos = new ListaPontosCandidatos();

    /**
     * Constrói uma nova instância de ControladorLinhaComando.
     * @param args Argumentos de linha de comando para ser processado.
     */
    public ControladorLinhaComando(final String[]args) throws Exception {

        // Processando argumentos
        processarArgumentos(args);

        // Executando operação escolhida
        if (operacao == Operacao.CALCULAR_TEMPO)
            calcularTempoExecucao();
        else if (operacao == Operacao.AJUDA)
            menuAjuda();
    }

    /**
     * Calcula o tempo de execução do algoritmo.
     * @throws Exception Exceção lançada caso ocorra um erro de abertura de arquivo ou caso o algoritmo selecionado não exista.
     */
    private void calcularTempoExecucao() throws Exception {
        // Abrindo o arquivo
        listaPontosCandidatos.abrirArquivo(new File(caminhoArquivoDados));

        // Criando a classe do algoritmo
        Algoritmo algoritmoEscolhido = null;
        if (algoritmo == BranchAndBound.class) {
            algoritmoEscolhido = new BranchAndBound(listaPontosCandidatos.getPontosCandidatos(), distanciaMinima);
        } else if (algoritmo == ForcaBruta.class) {
            algoritmoEscolhido = new ForcaBruta(listaPontosCandidatos.getPontosCandidatos(), distanciaMinima);
        } else {
            throw new Exception("Algoritmo Inválido");
        }

        // Executando o algoritmo
        final long tempoInicioAlgoritmo = System.nanoTime();
        algoritmoEscolhido.executar();
        final long tempoFimAlgoritmo = System.nanoTime();

        // Calculando o tempo de execução
        final long tempoGasto = tempoFimAlgoritmo - tempoInicioAlgoritmo;

        // Mostrando o tempo gasto
        System.out.printf("%dns\n", tempoGasto);
    }

    /**
     * Processa os argumentos da linha de comando.
     * @param args Argumentos a serem processados.
     */
    private void processarArgumentos(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().contains("--calcular-tempo")) {
                operacao = Operacao.CALCULAR_TEMPO;
            }
            else if (args[i].toLowerCase().contains("--help") || args[i].toLowerCase().contains("?") || args[i].toLowerCase().contains("-h")) {
                operacao = Operacao.AJUDA;
            }
            else if (args[i].toLowerCase().contains("--arquivo-dados")) {
                caminhoArquivoDados = args[++i];
            }
            else if (args[i].toLowerCase().contains("--distancia-minima"))
            {
                distanciaMinima = Double.parseDouble(args[++i]);
            }
            else if (args[i].toLowerCase().contains("--algoritmo"))
            {
                if (args[++i].toLowerCase().contains("branch-and-bound"))
                    algoritmo = BranchAndBound.class;
                else if (args[i].toLowerCase().contains("forca-bruta"))
                    algoritmo = ForcaBruta.class;
            }
        }
    }

    /**
     * Exibe o menu de ajuda.
     */
    private void menuAjuda() {
        final String jarFileName = new File(ControladorLinhaComando.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Uso: java -jar ");
        stringBuilder.append(jarFileName);
        stringBuilder.append(" [COMANDO]... [OPÇÃO]...\n");
        stringBuilder.append("Trabalho Prático - Projeto e Análise de Algoritmos\n");
        stringBuilder.append("\n");
        stringBuilder.append("Comandos:\n");
        stringBuilder.append("\t\t?, -h, --help\n");
        stringBuilder.append("\t\tExibe este menu de ajuda.\n");
        stringBuilder.append("\n");
        stringBuilder.append("\t\t--calcular-tempo\n");
        stringBuilder.append("\t\tCalcula o tempo de execução de um algoritmo (Branch and Bound ou Força Bruta).\n");
        stringBuilder.append("\n\n");
        stringBuilder.append("Opções:\n");
        stringBuilder.append("\t\t--arquivo-dados <nome-arquivo-dados>\n");
        stringBuilder.append("\t\tAbre um arquivo de dados.\n");
        stringBuilder.append("\n");
        stringBuilder.append("\t\t--distancia-minima <distancia-minima>\n");
        stringBuilder.append("\t\tDefine o valor da distância mínima.\n");
        stringBuilder.append("\n");
        stringBuilder.append("\t\t--algoritmo <nome-algoritmo>\n");
        stringBuilder.append("\t\tDefine o algoritmo a ser utilizado.\n");
        stringBuilder.append("\t\tValores possíveis: 'branch-and-bound' e 'forca-bruta'.\n");
        stringBuilder.append("\n");
        System.out.println(stringBuilder);
    }

    /**
     * Operações que podem ser realizadas.
     */
    private static enum Operacao {
        CALCULAR_TEMPO,
        AJUDA,
    }
}
