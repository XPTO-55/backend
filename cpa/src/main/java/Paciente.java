import java.time.LocalDate;
import java.util.Date;

public class Paciente extends Usuario{

    private Usuario responsavel;
    private String observacao;

//    construtor completo -> 9 argumentos
    public Paciente(String nome,
                    String rg,
                    String cpf,
                    LocalDate dataDeNascimento,
                    String telefoneFixo,
                    String telefoneCelular,
                    String endereco,
                    Usuario responsavel,
                    String observacao) {
        super(nome, rg, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
        this.responsavel = responsavel;
        this.observacao = observacao;
    }

//    construtor com 7 argumentos
    public Paciente(String nome, String rg, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                    String telefoneCelular, String endereco) {
        super(nome, rg, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
    }

    public Consulta marcarConsulta(){
        return marcarConsulta();
    }
}



