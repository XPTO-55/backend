public class Credito extends Cartao{

    @Override
    public boolean processarPagamento(Double valor) {
        return false;
    }
}
