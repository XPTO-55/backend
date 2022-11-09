public class Debito extends Cartao{
    @Override
    public boolean processarPagamento(Double valor) {
        return false;
    }
}
