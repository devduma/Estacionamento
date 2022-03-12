package letscode.domain;

public class CalculoHoraValorInicial implements Calculo{
    private final double valorInicial;
    private final double valorHoraExcedente;
    private final int limiteHorasInicial;

    public CalculoHoraValorInicial(String tipoVeiculo, int limiteHorasInicial){
        valorInicial = TipoVeiculo.valueOfHoraInicial(tipoVeiculo);
        this.limiteHorasInicial = limiteHorasInicial;
        valorHoraExcedente = TipoVeiculo.valueOfHora(tipoVeiculo);
    }

    public double calculaTarifa(double qtdHoras){
        double valor = valorInicial;
        if (qtdHoras > limiteHorasInicial) {
            valor += (qtdHoras - limiteHorasInicial) * valorHoraExcedente;
        }
        return valor;
    }
}
