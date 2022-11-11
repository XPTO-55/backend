import java.time.LocalDate;
import java.util.Date;

public class Paciente extends Usuario {

    private Usuario responsavel;


    private String observacao;

    //    construtor completo -> 9 argumentos
    public Paciente(String nome,
                    String email,
                    String cpf,
                    LocalDate dataDeNascimento,
                    String telefoneFixo,
                    String telefoneCelular,
                    Usuario responsavel,
                    String observacao,
                    Endereco endereco) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
        this.responsavel = responsavel;
        this.observacao = observacao;
    }


    //    construtor com 7 argumentos
    public Paciente(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                    String telefoneCelular, Endereco endereco) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
    }

    public Paciente(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                    String telefoneCelular, String rua, String bairro, String cep, String numero, String complemento,
                    String cidade, String uf) {
        super(nome,email,cpf,dataDeNascimento,telefoneFixo,telefoneCelular,rua,bairro,cep,numero,complemento,cidade,uf);
    }

    public Paciente(String nome,
                    String email,
                    String cpf,
                    LocalDate dataDeNascimento,
                    String telefoneFixo,
                    String telefoneCelular) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular,new Endereco());
        this.responsavel = null;
        this.observacao = null;
    }

    public Consulta marcarConsulta() {
        return marcarConsulta();
    }
}



