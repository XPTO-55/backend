import java.util.Date;

public class Boleto extends FormaPagamento{
    private Usuario sacado;
    private Date vencimento;
    private boolean juros;
    private double porcentagemJuros;

    public Boleto(Usuario sacado, Date vencimento, boolean juros, double porcentagemJuros) {
        this.sacado = sacado;
        this.vencimento = vencimento;
        this.juros = juros;
        this.porcentagemJuros = porcentagemJuros;
    }

    @Override
    public boolean processarPagamento(Double valor) {
        return false;
    }
}
