package letscode.domain;

public class TarifaEstacionamento {

    private static final Integer LIMITE_HORAS_EXCENDENTE = 1;

    public static double determinaValorCobranca(long tempoMinutos, String tipoVeiculo) {
        double valorTarifa = 0d;
        double qtdHoras = Math.ceil(tempoMinutos / 60d);

        if (qtdHoras >= 24) { // cobrança por Diária
            Calculo calculo = new CalculoDiaria(tipoVeiculo);

            valorTarifa = calculo.calculaTarifa(qtdHoras);
            int numDiarias = (int) qtdHoras / 24;
            double horasExcedente = qtdHoras - numDiarias * 24;

            if (horasExcedente > 0) {
                calculo = new CalculoHora(tipoVeiculo);
                valorTarifa += calculo.calculaTarifa(horasExcedente);
            }
        }
        else {
            if (tempoMinutos > 5) {  // tolerância de 5 minutos entre a saída e entrada
                Calculo calculo = new CalculoHoraValorInicial(tipoVeiculo, LIMITE_HORAS_EXCENDENTE);
                valorTarifa = calculo.calculaTarifa(qtdHoras);
            }
        }
        return valorTarifa;
    }
}
