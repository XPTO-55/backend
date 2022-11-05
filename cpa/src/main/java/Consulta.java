public class Consulta {

    private IformaPagamento formaPagamento;
    private Paciente paciente;
    private Profissional profissional;

    public Consulta(IformaPagamento formaPagamento, Paciente paciente, Profissional profissional) {
        this.formaPagamento = formaPagamento;
        this.paciente = paciente;
        this.profissional = profissional;
    }

    public void setFormaPagamento(IformaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
