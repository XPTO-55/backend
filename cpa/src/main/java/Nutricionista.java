import java.time.LocalDate;
import java.util.Date;

public class Nutricionista extends Profissional{
private double peso;
private double altura;

    public Nutricionista(String nome,
                         String rg,
                         String cpf,
                         LocalDate dataDeNascimento,
                         String telefoneFixo,
                         String telefoneCelular,
                         String endereco,
                         String indentificacao,
                         boolean verificacao,
                         double peso,
                         double altura) {

        super(nome, rg, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco, indentificacao, verificacao);
        this.peso = peso;
        this.altura = altura;
    }
    
    public Cardapio emitirCardapio(){
        return emitirCardapio();
    }
}
