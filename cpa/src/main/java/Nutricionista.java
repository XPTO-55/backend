import java.time.LocalDate;
import java.util.Date;

public class Nutricionista extends Profissional{
private double peso;
private double altura;

    public Nutricionista(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                         String telefoneCelular, Endereco endereco, String indentificacao, boolean verificacao,
                         double peso, double altura) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco, indentificacao, verificacao);
        this.peso = peso;
        this.altura = altura;
    }

    public Nutricionista(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                         String telefoneCelular, String rua, String bairro, String cep, String numero, String complemento,
                         String cidade, String uf, String indentificacao, boolean verificacao, double peso, double altura) {
        super(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, rua, bairro, cep, numero, complemento,
                cidade, uf, indentificacao, verificacao);
        this.peso = peso;
        this.altura = altura;
    }

    public Cardapio emitirCardapio(){
        return emitirCardapio();
    }
}
