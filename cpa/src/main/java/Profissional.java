import java.time.LocalDate;
import java.util.Date;

public abstract class Profissional extends Usuario{
    private String indentificacao;
    private boolean verificacao;

    public Profissional(String nome,
                        String rg,
                        String cpf,
                        LocalDate dataDeNascimento,
                        String telefoneFixo,
                        String telefoneCelular,
                        String endereco,
                        String indentificacao,
                        boolean verificacao) {
        super(nome, rg, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
        this.indentificacao = indentificacao;
        this.verificacao = verificacao;
    }

    public Atestado emitirAtestado(){
        return emitirAtestado();
    }
}
