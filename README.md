# *Cucumber* com *Java*


## Introdução

Testes escritos com linguagem de negócio é a ideia base do *Behaviour Driven Development* (BDD). No mundo *Java* a ferramenta que dá suporte ao BDD é o *[Cucumber](https://cucumber.io/)*. Este projeto apresenta exemplos simples do uso do *Cucumber*.

## Configuraões

É necessário configurar as dependências relacionadas aos *frameworks* (*Cucumber* e *JUnit*) no Maven. Além disso é necessário configurar o próprio *Cucumber*.

#### Maven

Utilizando *Maven* o projeto pode ser configurado com simplicidade adicionando-se algumas simples dependências no arquivo *pom.xml*:

- As dependêncis relacionadas ao *Cucumber*:

```
  ...
  <dependencies>
    ...
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>${cucumber.version}</version>
			<scope>test</scope>
		</dependency>
    ...
  <dependencies>
```

- As dependêncis relacionadas ao *[JUnit](https://junit.org/)*:

```
  ...
  <dependencies>
    ...
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
		</dependency>
		<dependency>
			<groupId>org.junit.vintage</groupId>
			<artifactId>junit-vintage-engine</artifactId>
			<scope>test</scope>
		</dependency>
    ...
  <dependencies>
```

#### Cucumber

As configurações referentes ao *Cucumber* são as seguintes:

 - Criar um pacote no qual serão adicionados os "critérios de aceitação" TDD, os quais são conhecidos como *features*. Neste vão os arquivos de TDD escritos em linguagem compreensível pelo pessoal de negócio. No presente projeto criamos o pacote *features* dentro de */src/test/java*. A extensão dos arquivo é *.feature*. Seu conteúdo é no formato:
 
```
Feature: ...

Scenario: ...

 Given ...
 When ...
 Then ...
```
 
 - Criar um outro pacote destinado as classes que executam os testes. Neste serão criadas as classes que implementam as ações dos testes. No presente projeto criamos o pacote *acceptance* dentro de */src/test/java*. Os arquivos de teste são classes *Java*. O arquivo principal, o *Runner* precisa de duas anotações na classe: *@RunWith* e *@CucumberOptions(features = "classpath:NOME_PACOTE_TDD")*

```
...

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features") // Nome do pacote onde estão os arquivos .feature
public class ...
...
```

Ao rodar os testes com *JUnit* com a configuração efetua até agora 
Com as configurações efetuadas até agora, se executarmos os testes *JUnit* ocorrerá um erro, dado que os testes ainda não foram implementados. Mas o pŕoprio erro ajuda muito, ele mostra a estrutura que a implementação dos testes deve seguir.


## Implementando os Steps

Ao terminar de configuara o projeto, executando pela primeira vez os testes, o retorno é um erro que nos ajuda, fornecendo a estrutura que devemos implmentar:

```
io.cucumber.junit.UndefinedStepException: The step 'Dada uma reserva valida de um quarto disponivel' and 2 other step(s) are undefined.
You can implement these steps using the snippet(s) below:

@Given("Dada uma reserva valida de um quarto disponivel")
public void dada_uma_reserva_valida_de_um_quarto_disponivel() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@When("Faz uma reserva")
public void faz_uma_reserva() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
@Then("A reserva e aceita")
public void a_reserva_e_aceita() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

```

Esta estrutura deve ser implementada em uma classe *Java*, pode ser criado um pacote dentro do destinado as classes que executam os testes. Tal pacote foi criado quando da configuração do *Cucumber*, lá vamos colocar as classes que implementam as ações dos testes (os *steps*).

No presente projeto criamos o pacote *acceptance* dentro de */src/test/java*, dentro dele vamos criar um novo pacote: *steps*. No referido pacote criaremos a primeira implementação *RevervandoSteps.java*.

Ao de criar a classe contendo a estrutura fornecida pela mensagem de erro podemos voltar ao *Runner* (classe com as anotações *@RunWith* e *@CucumberOptions*) e executar novamente os testes (Run as JUnit test), o erro muda, dizendo que agora falta a implementação. Ou seja, a estrutura está certa, só falta implementar.

A partir deste ponto é possível fazer qualquer teste no mesmo formato: cria-se o arquito TDD com a *feature* e depois a classe *Java* que implementa os testes.

## Usando Língua Portuguesa

É possível usar o Cucumber em português para tornar tudo ainda mais acessível. São dois passos simples:

 - No arquivo *features* adicionar a definição de linguagem, com o referente código, no caso da Língua Portuguesa, o *pt* e usar a estrutura base em português, conforme abaixo:
 
```
# language: pt

Funcionalidade: ...

Cenario: ...

 Dado ...
 Quando ...
 Entao ...
```

 - No arquivo que implementa os *steps* alterar as anotações em inglês (*@Given*, *@When* e *@Then*) para as de Língua Portuguesa (*@Dado*, *@Quando* e *@Entao*) tomando o cuidado de selecionar os imports corretos (*io.cucumber.java.pt.**). Além disso os nomes dos métodos devem refletir possíveis alterações nos nomes dos steps.


```
...
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
...
@Dado(...

@Quando(...

@Entao(...
...
```

## Plugin Cucumber nas IDEs

Algumas IDEs possuem plugins que ajudam na utilização do Cucumber. No *Eclipse* basta ir no *market place* e buscar por *Cucumber* e instalar. Isso fará com que o texto dos arquivos *.feature* apresentem cores diferentes destacando palavras chave e parâmetros.

## O Projeto - Reserva de Quartos

Com o principal intuito de ser simples e didático, este projeto traz o domínio de reserva de quartos. Não usa banco de dados, alguns dados são mocados (os referentes aos quartos), outros permanecem em memória apenas durante a execução do projeto (reservas e clientes).


#### Minimundo

Um cliente deseja reservar um quarto, para isso informa o período de estadia. Deve-se verificar se há quartos disponíveis para o período desejado e mostrar as opções ao cliente. O cliente deve selecionar uma das opções disponíveis, assim a reserva é efetuada.


## Referências

   - [Alura](https://www.alura.com.br/) - curso "BDD e Java: Behavior Driven Development com Cucumber", ministrado por Nico Steppat.
   - [Cucumber Docs](https://cucumber.io/docs/cucumber/)
