package letscode.domain;

public enum TipoVeiculo {

    CARRO("C", 20.00d, 10.00d, 50.00d),
    MOTOCICLETA("M", 10.00d, 5.00d, 30.00d),
    CAMINHAO("T", 30.00d, 20.00d, 80.00d);

    private final String idTipo;
    private final double valorHoraInicial;
    private final double valorHora;
    private final double valorDiaria;

    private static final Double VALOR_DIARIA = 60.00d;
    private static final Double VALOR_HORA = 5.00d;
    private static final Double VALOR_HORA_INICIAL = 12.00d;

    TipoVeiculo(String idTipo, double valorHoraInicial,
                double valorHora, double valorDiaria) {
        this.idTipo = idTipo;
        this.valorHoraInicial = valorHoraInicial;
        this.valorHora = valorHora;
        this.valorDiaria = valorDiaria;
    }

    public String getTipoVeiculo() {
        return idTipo;
    }

    public double getValorHoraInicial(){
        return valorHoraInicial;
    }

    public double getValorHora(){
        return valorHora;
    }

    public double getValorDiaria(){
        return valorDiaria;
    }

    public static String identificaTipoSelecionado(String idTipoVeiculo){
        for (TipoVeiculo origem : TipoVeiculo.values()) {
            if (origem.getTipoVeiculo().equalsIgnoreCase(idTipoVeiculo)) {
                return origem.name();
            }
        }
        return null;
    }

    public static double valueOfDiaria(String veiculo){
        for (TipoVeiculo origem : TipoVeiculo.values()) {
            if (origem.name().equals(veiculo)) {
                return origem.getValorDiaria();
            }
        }
        return VALOR_DIARIA;
    }

    public static double valueOfHora(String veiculo){
        for (TipoVeiculo origem : TipoVeiculo.values()) {
            if (origem.name().equals(veiculo)) {
                return origem.getValorHora();
            }
        }
        return VALOR_HORA;
    }

    public static double valueOfHoraInicial(String veiculo){
        for (TipoVeiculo origem : TipoVeiculo.values()) {
            if (origem.name().equals(veiculo)) {
                return origem.getValorHoraInicial();
            }
        }
        return VALOR_HORA_INICIAL;
    }
}
