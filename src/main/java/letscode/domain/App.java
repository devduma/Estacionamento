package letscode.domain;

import letscode.view.ConsoleUtils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * <b>Entrada:</b> Placa do carro
 * <ul>
 *   <li>Verifica se já houve entrada </li>
 *   <ul>
 *       <li>
 *           Se não entrou 'Entrada do veículo de placa: <b><placa></b>'
 *           <ul><li>Verificar se existe vaga disponível</li></ul>
 *       </li>
 *   <li>Se já entrou 'Saída do veículo de placa  <b><placa></b> . Tempo
 *       no estabelecimento . Valor a ser cobrado:'
 *   </li>
 *   </ul>
 * <p>
 *   Tempo a ser Cobrado:
 *
 *   <ul>
 *
 *       <li>0 - 5 minutos - 0 reais</li>
 *       <li>5 - 60 minutos - 4 reais</li>
 *       <li> - Acima de 60 - cobrado o valor de 6 reais por hora adicional</li>
 *       <li> - Diaria - cobrado o valor de VALOR_DIARIA e horas adicional de 2 reais</li>
 *   </ul>
 *
 *   <em>Verificar vaga disponível (V2).</em>
 *   <em>Adicionar número de vagas  (V2).</em>
 * </ul>
 */

public class App
{
    public static final Integer NUMERO_VAGAS = 5;
    public static final Integer TOLERANCIA = 2;

    public static void main( String[] args )
    {

        try (Scanner ler = new Scanner(System.in)) {
            StringBuilder saidas = new StringBuilder();
            List<String> placas = new ArrayList<>();
            List<String> veiculos = new ArrayList<>();
            List<Long> tempos = new ArrayList<>();
            int option;
            do {
                System.out.println("Options \n\t 1 - Adicionar número da placa \n\t 0 - Sair do programa");
                option = ler.nextInt();

                if (option != 0) {
                    // Entrada: Placa do carro e tipo de veículo
                    // array das placas
                    // array das entradas

                    System.out.println("Informe o número de placa");
                    String placaVeiculo = ler.next();
                    // Verifica se já houve entrada
                    int indice;

                    if ((indice = verificaPlaca(placas, placaVeiculo)) >= 0) {
                        //placa existe
                        saidas.append(liberarVaga(placas, tempos, veiculos, indice));
                        // Se já entrou 'Saída do veículo de placa <placa> . Tempo
                        //no estabelecimento . Valor a ser cobrado:'
                    } else {
                        // placa não existe
                        //Se não houve entrada
                        //Verificar vaga disponível (V1)
                        String tipoVeiculo = validaTipoVeiculo();

                        if (placas.size() < NUMERO_VAGAS) {
                            alocaVaga(placas, veiculos, tempos, placaVeiculo, tipoVeiculo);
                        } else {
                            System.err.println("Não tem mais vaga");
                        }

                    }
                    imprimirRelatorio(placas, tempos, veiculos, saidas);
                }
            } while (option != 0);
        }
    }

    private static int verificaPlaca(List<String> placas, String placaVeiculo) {
        for (int i = 0; i < placas.size(); i++) {
            String placa = placas.get(i);
            if (null != placa && placa.equals(placaVeiculo)) {
                return i;
            }
        }
        return -1;
    }

    private static String validaTipoVeiculo(){
        System.out.println("Tipo de Veículo: ");
        TipoVeiculo[] tipoVeiculos = TipoVeiculo.values();
        String[] options = new String[tipoVeiculos.length];

        for (int i = 0; i < tipoVeiculos.length; i++) {
            options[i] = tipoVeiculos[i].getTipoVeiculo();
            System.out.printf("%s - %s%n", tipoVeiculos[i].getTipoVeiculo(), tipoVeiculos[i].name());
        }

        String opTipoVeiculo = ConsoleUtils.getUserOption("%nEscolha a opção", options);

        String nomeTipoVeiculo = TipoVeiculo.identificaTipoSelecionado(opTipoVeiculo);

        System.out.println("Tipo Veiculo: " + nomeTipoVeiculo);
        return nomeTipoVeiculo;
    }

    private static void alocaVaga(List<String> placas, List<String> veiculos, List<Long> tempos,
                                  String placaVeiculo, String tipoVeiculo) {
        placas.add(placaVeiculo);
        veiculos.add(tipoVeiculo);
        tempos.add(System.currentTimeMillis());
        System.out.printf("Entrada do veículo de placa: %s %n", placaVeiculo);
    }

    private static String liberarVaga(List<String> placas, List<Long> tempos, List<String> veiculos, int indice) {
        Long tempoInicial = tempos.get(indice);
        Long tempoFinal = System.currentTimeMillis();
        Long tempoMinutos = TimeUnit.MILLISECONDS
                .toMinutes(tempoFinal - tempoInicial) - TOLERANCIA;
        String tipoVeiculo = veiculos.get(indice);

        double valorCobrado = TarifaEstacionamento.determinaValorCobranca(tempoMinutos, tipoVeiculo);

        // Se não entrou 'Entrada do veículo de placa: <placa>'
        System.out.printf("Saída do veículo de placa %s . " +
                "Tempo no estabelecimento . Valor a ser cobrado: %.2f %n", placas.get(indice), valorCobrado);

        String retorno = String.format(" Placa %s (%s) - tempo permanencia: %d minutos - valor cobrado: %.2f %n",
                placas.get(indice), tipoVeiculo, tempoMinutos, valorCobrado);
        placas.remove(indice);
        veiculos.remove(indice);
        tempos.remove(indice);
        return retorno;
    }

    private static void imprimirRelatorio(List<String> placas, List<Long> tempos,
                                          List<String> veiculos, StringBuilder saida) {
        System.out.println("Veiculos no patio:");
        GregorianCalendar gc = new GregorianCalendar();
        for (int i = 0; i < placas.size(); i++) {

            gc.setTimeInMillis(tempos.get(i));
            System.out.printf(" Placa %s \t %s \t Hora de entrada: %tD %tl:%tM %n", placas.get(i), veiculos.get(i), gc, gc, gc);

        }
        System.out.printf("%nRelatório de veículos: %n%s ", saida.toString());
    }
}
