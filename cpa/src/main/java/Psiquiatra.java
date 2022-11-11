import java.time.LocalDate;
import java.util.Date;

public class Psiquiatra extends Profissional{

    public Psiquiatra(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                      String telefoneCelular, Endereco endereco, String indentificacao, boolean verificacao) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco, indentificacao, verificacao);
    }

    public Psiquiatra(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                      String telefoneCelular, String rua, String bairro, String cep, String numero, String complemento,
                      String cidade, String uf, String indentificacao, boolean verificacao) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, rua, bairro, cep, numero, complemento,
                cidade, uf, indentificacao, verificacao);
    }

    public Receita emitirReceita(){
        return emitirReceita();
    }

}
