package letscode.domain;

public class CalculoDiaria implements Calculo {
        private final double valorDiaria;

        public CalculoDiaria(String tipoVeiculo){
            valorDiaria = TipoVeiculo.valueOfDiaria(tipoVeiculo);
        }

        public double calculaTarifa(double qtdHoras){
            return valorDiaria * (int) (qtdHoras / 24);
            // return valorDiaria * (int) Math.ceil(qtdHoras / 24.0);
        }
}
