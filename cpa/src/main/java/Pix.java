public class Pix extends FormaPagamento{
    @Override
    public boolean processarPagamento(Double valor) {
        return false;
    }
}
