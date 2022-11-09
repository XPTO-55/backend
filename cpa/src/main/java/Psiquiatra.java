import java.time.LocalDate;
import java.util.Date;

public class Psiquiatra extends Profissional{

    public Psiquiatra(String nome,
                      String rg,
                      String cpf,
                      LocalDate dataDeNascimento,
                      String telefoneFixo,
                      String telefoneCelular,
                      String endereco,
                      String indentificacao,
                      boolean verificacao) {
        super(nome, rg, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco, indentificacao, verificacao);
    }

    public Receita emitirReceita(){
        return emitirReceita();
    }

}
