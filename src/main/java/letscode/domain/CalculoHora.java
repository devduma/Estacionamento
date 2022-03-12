package letscode.domain;

public class CalculoHora implements Calculo{
    private final double valorHora;

    public CalculoHora(String tipoVeiculo){
        valorHora = TipoVeiculo.valueOfHora(tipoVeiculo);
    }

    public double calculaTarifa(double qtdHoras){
        return qtdHoras * valorHora;
    }
}
