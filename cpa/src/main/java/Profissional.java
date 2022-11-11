import java.time.LocalDate;
import java.util.Date;

public abstract class Profissional extends Usuario{
    private String indentificacao;
    private boolean verificacao;

    public Profissional(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                        String telefoneCelular, Endereco endereco, String indentificacao, boolean verificacao) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
        this.indentificacao = indentificacao;
        this.verificacao = verificacao;
    }

    public Profissional(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                        String telefoneCelular, String rua, String bairro, String cep, String numero, String complemento
            , String cidade, String uf, String indentificacao, boolean verificacao) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, rua, bairro, cep, numero,
                complemento, cidade, uf);
        this.indentificacao = indentificacao;
        this.verificacao = verificacao;
    }


    public Atestado emitirAtestado(){
        return emitirAtestado();
    }
}
