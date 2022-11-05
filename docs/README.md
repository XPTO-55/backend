## Requirements
___
Java 11 <br>
Maven <br>
docker e docker-compose (Caso não tenha o mysql na sua máquina e não queira instalá-lo) <br>

## Explicação dos conceitos
___

**Models/Entities** => São as classes que contém a representação das tabelas do banco de dados

**Controllers** => Camada do sistema responsável por manipular os dados, validá-los e capturar o necessário (Exemplo se o email é valido e se todos os campos obrigatórios foram preenchidos)

**Services** => Camada responsável por validar a regra de negócio da aplicação (Exemplo se o email informado já existe) 

**DTO** => (Data Transfer Object) É a classe que contém o que o service precisa para criar o recurso (Exemplo, para se criar um usuário, precisamos informar o email e a senha, o id n será necessário, essa sera uma classe que conteria apenas o email e senha)

**Repositories** => É a camada responsável por manipular o banco de dados, contém os métodos que irão fazer consultas e registros no banco (Exemplo, findById)

**DRIVER DE BD** => É uma "API" que cada banco de dados fornece para que outras aplicações utilizem dos recursos do banco, necessário usá-la se qusiermos utilizar qualquer tipo de banco (Cada driver de banco pode ter uma sintaxe diferente de uso) 

**ORM** => (Object Relationnal Mapper) Biblioteca que mapeia os modelos e abstrai o uso do driver do banco de dados, você apenas configura qual o nome do banco que quer utilizar(o termo seria dialeto) e faz uso das funções que o ORM fornece (Exemplos: findAll(), findByPk(), save(), delete()), dessa forma ele observa a função que você executou e converte para a sintaxe que o driver do banco que você configurou utiliza. (Estaremos utilizando o JPA para essa função)


## Explicação das anotações
___

**@Repository** => Indica que a classe é um repository <br>
**@Service** => Indica que a classe é um Service <br>
**@Entity** => Indica que a classe é uma entidade/modelo <br>
**@RestController** => Indica que a classe é uma controller <br>


### A Biblioteca lombok fornece algumas anotações úteis para a aplicação

**@Data** => Notação que cria os getters, setters, toString e contrutor com os argumentos obrigatórios da classe <br>
**@Getter** => Notação que cria os getters da classe <br>
**@Setter** => Notação que cria os Setter da classe <br>
**@NoArgsConstructor** => Notação que cria um contrutor vazio da classe <br>
**@AllArgsConstructor** => Notação que cria um contrutor cheio da classe <br>
**@RequiredArgsConstructor** => Notação que cria um contrutor com os campos obrigatórios da classe <br>

[Mais informações do lombok](https://projectlombok.org/features/)
