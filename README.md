# Projeto FiapRide - Daniel Silva de Castro

## 📊 Informações do Aluno

- **Nome:** Daniel Silva de Castro
- **RM:** 567894
- **Turma:** 2CCPS
- **Curso:** Ciência da Computação
- **GitHub:** [@daniels-decastro](https://github.com/daniels-decastro)

---

## 🎯 Descrição do Projeto

Este projeto é o resultado do aprendizado nas aulas 1-9 de Programação Orientada a Objetos, onde desenvolvemos o sistema **FiapRide**, um aplicativo de mobilidade urbana com passageiros, veículos (carro, moto e carro elétrico) e viagens. Já o projeto pessoal  é um **sistema de controle de teares de uma tecelagem**.

Cada aula corresponde a um commit, então o histórico do repositório mostra a evolução do código, desde a primeira classe com atributos públicos até as classes abstratas e interfaces.

### Estrutura

```
src/br/com/fiapride/
├── model/      FiapRide: Passageiro, Veiculo, Carro, Moto, CarroEletrico, Viagem, Recarregavel, Celular
├── pessoal/    Projeto pessoal: Tear, TearJatoDeAr, TearDePinca, OrdemDeProducao, Manutenivel, Compressor
└── main/       SistemaPrincipal (testes do FiapRide) e TesteMeuObjeto (testes do projeto pessoal)
diagramas/      Diagramas de classe em PNG
```

### Como executar

Na raiz do repositório (Java 11 ou superior):

```bash
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -cp bin br.com.fiapride.main.SistemaPrincipal
java -cp bin br.com.fiapride.main.TesteMeuObjeto
```

Cada `main` está dividido em métodos `demonstrar...()`, um por assunto, e os testes incluem entradas inválidas para mostrar as validações funcionando.

---

## ✅ Checklist de Implementação

- [x] Aula 1 - Classes e Objetos
- [x] Aula 2 - Métodos
- [x] Aula 3 - Encapsulamento
- [x] Aula 4 - Construtores
- [x] Aula 5 - Associação
- [x] Aula 6 - Herança
- [x] Aula 7 - Polimorfismo
- [x] Aula 8 - Classes Abstratas
- [x] Aula 9 - Interfaces

### O que cada aula acrescentou

| Aula | FiapRide | Projeto pessoal (tecelagem) |
|---|---|---|
| 1 | `Passageiro` com atributos e tipos | `Tear` com código, modelo, velocidade e produção |
| 2 | `adicionarSaldo`, `pagarViagem` e `temSaldoSuficiente` (com retorno) | `ajustarVelocidade`, `registrarProducao` e `atingiuMeta` (com retorno) |
| 3 | Atributos `private`, getters públicos e `setSaldo` privado | Atributos `private`, setters privados com validação |
| 4 | Construtores com `this()` e classe `Veiculo` sem `setModelo` | Construtor padrão e customizados com `this()`, `recadastrarCodigo()` |
| 5 | `Viagem` TEM UM `Passageiro` e TEM UM `Veiculo` | `OrdemDeProducao` TEM UM `Tear` |
| 6 | `Carro` e `Moto` herdam de `Veiculo` | `TearJatoDeAr` e `TearDePinca` herdam de `Tear` |
| 7 | `calcularAutonomia()` sobrescrito e `List<Veiculo>` | `calcularProducaoPorHora()` sobrescrito e `List<Tear>` |
| 8 | `Veiculo` abstrata com `exibirTipo()` | `Tear` abstrata com `exibirTipoDeInsercao()` e `calcularProducaoPorHora()` |
| 9 | Interface `Recarregavel`, `CarroEletrico` e `Celular` | Interface `Manutenivel`, implementada por `Tear` e `Compressor` |

### Diagramas de classe

Notação UML: `-` privado, `+` público, `#` protegido, *itálico* abstrato e <u>sublinhado</u> estático (constante).

**FiapRide**

![Diagrama de classes do FiapRide](diagramas/fiapride.png)

**Projeto pessoal: controle de teares**

![Diagrama de classes do projeto pessoal](diagramas/projeto-pessoal.png)

---

## 🤔 Perguntas de Reflexão

### Aula 1 - Classes e Objetos

**Pergunta:** Por que precisamos criar uma classe `Passageiro`? Não seria mais fácil apenas criar variáveis soltas no main, como `String nomeAna = "Ana"` e `double saldoAna = 50.0`?

**Pense:** E se o FiapRide tiver 1 milhão de usuários? Como a Classe ajuda a resolver isso?

**Sua Resposta:**
Variáveis soltas funcionam para dois ou três passageiros, mas não escalam, porque com 1 milhão de usuários seriam 2 milhões de variáveis com nomes inventados à mão (`nomeAna`, `saldoAna`, `nomeCarlos`...), sem garantia de que todo passageiro tenha os mesmos dados e sem como percorrer todos eles em um laço. A classe `Passageiro` define a estrutura uma única vez, e cada `new Passageiro(...)` cria um objeto com o seu próprio estado, então o milionésimo passageiro custa uma linha de código, e não duas variáveis novas. A classe também é o lugar das regras (Aula 2) e da proteção dos dados (Aula 3), o que variáveis soltas nunca oferecem. No meu projeto pessoal o raciocínio é o mesmo, porque uma tecelagem tem dezenas de teares com a mesma estrutura (código, modelo, velocidade e produção) e valores diferentes, que é exatamente a diferença entre classe e objeto. Apliquei também a abstração da aula e deixei de fora dados que existem na máquina, mas que o controle de produção não usa, como a cor da pintura ou o fabricante do motor.

---

### Aula 2 - Métodos

**Pergunta:** Se nós podemos simplesmente fazer `passageiro.saldo = passageiro.saldo + 100` diretamente no código principal, por que dá tanto trabalho criar um método específico chamado `adicionarSaldo(valor)` para fazer isso? Quais seriam os riscos para a nossa startup de mobilidade se deixássemos qualquer programador alterar o saldo diretamente?

**Sua Resposta:**
O método concentra a regra em um único lugar. Com o acesso direto, cada programador precisaria lembrar de validar o valor em todos os pontos do sistema que mexem no saldo, e basta um esquecimento para que `passageiro.saldo = passageiro.saldo + (-500)` passe sem ninguém perceber. Para uma startup de mobilidade os riscos são financeiros e de confiança, como saldo negativo, crédito criado do nada, corrida cobrada de quem não tinha saldo e nenhuma pista de onde o erro aconteceu. No meu código, `pagarViagem()` consulta `temSaldoSuficiente()` antes de descontar, então a regra "só paga quem tem saldo" é escrita uma vez e reaproveitada, inclusive pela classe `Viagem` na Aula 5. No `Tear` acontece o mesmo, porque `ajustarVelocidade()` impede que alguém coloque a máquina a 5000 rpm, o que no chão de fábrica significa quebra de peça e de fio, e `registrarProducao()` recusa produção negativa, que distorceria o controle de eficiência.

---

### Aula 3 - Encapsulamento

**Pergunta:** No nosso código, os atributos são `private`, mas os métodos `getSaldo()` e `getNome()` são `public`. Por que é seguro deixar o `get` público, mas perigoso deixar o atributo original público?

Pense bem: Qual a diferença entre dar a alguém uma CÓPIA de um documento seu, e entregar o documento ORIGINAL para a pessoa rasurar?

**Sua Resposta:**
O getter entrega uma cópia do valor. Quem chama `getSaldo()` recebe o número e pode usá-lo como quiser, mas nada do que fizer com essa cópia chega ao objeto. O atributo público é o documento original, que qualquer parte do sistema pode rasurar sem passar por regra nenhuma. Com `private`, o único caminho para mudar o saldo são os métodos que validam (`adicionarSaldo()` e `pagarViagem()`), e eu fui um passo além deixando o próprio `setSaldo()` privado, porque dinheiro não deve ser "editado", e sim recarregado ou gasto. No `Tear`, `setVelocidadeRpm()` e `setMetrosProduzidos()` também são privados, então a produção só cresce por `registrarProducao()`, como o contador de uma máquina real, que o operador não zera nem altera à mão. A linha `tear1.metrosProduzidos = -50.0;` deixou de compilar, e o próprio compilador passou a defender a regra. Para atributos que são objetos, como em `getSolicitante()`, o getter devolve a referência, e não uma cópia, mas o objeto devolvido continua protegido pelos próprios métodos dele.

---

### Aula 4 - Construtores

**Pergunta:** Na nossa classe `Veiculo`, nós tomamos duas decisões arquitetônicas muito importantes:

1. Nós **não** criamos o método `setModelo()`.
2. O `setPlaca()` foi criado como **privado**, e criamos um método público chamado `atualizarPlaca()` para acessá-lo.

Pensando no mundo real e no Clean Code: Por que é um erro gravíssimo clicar em 'Gerar Getters e Setters para tudo' automaticamente na sua IDE? Como as nossas duas decisões acima protegem o sistema de fraudes e falhas de lógica?

_Dica: Pense sobre o que pode ou não mudar fisicamente em um carro, e a diferença entre "alterar um dado no banco" e "executar um processo real no Detran"_

**Sua Resposta:**
Gerar getters e setters para tudo abre portas que não deveriam existir. Um `setModelo()` transformaria um Corolla em Fusca com uma linha de código, e um `setPlaca()` público permitiria trocar a placa sem processo nenhum, o que abre espaço para fraude, como esconder um veículo multado atrás de outra placa. As duas decisões seguem o mundo real. O modelo é definido na fábrica e nunca muda, então entra pelo construtor e não tem setter. A placa pode mudar, mas só por um processo oficial, então o setter é privado e o caminho público é `atualizarPlaca()`, que representa a solicitação ao Detran e é o lugar certo para validações e, no futuro, para registro e auditoria. É a diferença entre alterar um dado no banco e executar um processo de negócio. No projeto pessoal repeti o padrão, porque o modelo de um tear não muda e não tem `setModelo()`, enquanto o código de patrimônio só muda por `recadastrarCodigo()`. Nesta aula também criei construtores encadeados com `this()`, como `new Tear("TEAR-02", "Rapier 220")`, que delega para o construtor completo com a velocidade padrão, e um construtor padrão `Tear()` que também delegava para os outros, para que mesmo sem argumentos o objeto nascesse válido.

---

### Aula 5 - Associação

**Pergunta:** "No construtor da `Viagem`, nós exigimos o objeto inteiro (`Passageiro solicitante`). Se o nosso resumo só precisa imprimir o nome da pessoa, não seria mais fácil e mais leve pedir apenas a String do nome no construtor da Viagem (`String nomeDoPassageiro`) em vez do objeto todo?"

_Pense nas regras de negócio: O que acontece na hora que a Viagem acaba e o sistema precisa descontar o saldo? Se a Viagem tiver apenas a String "Ana Silva", ela consegue mexer no dinheiro dela?_

**Sua Resposta:**
Não seria mais fácil, porque a viagem não serve só para imprimir. Com a String "Ana Silva", a viagem não teria como descontar o saldo, consultar se a Ana pode pagar nem saber o saldo atual, porque um texto não tem comportamento. Guardando o objeto `Passageiro`, a `Viagem` chama `solicitante.temSaldoSuficiente()` e `solicitante.pagarViagem()` no método `finalizar()`, que foi o que implementei. Também não é mais pesado, porque a viagem guarda só uma referência para o objeto, e não uma cópia dele. Essa referência traz outra vantagem, que testei no `main`, já que a Ana recarrega depois de a viagem ser criada e `viagemDaAna.getSolicitante().getSaldo()` já mostra o valor novo, sem cópias desatualizadas. No projeto pessoal, a `OrdemDeProducao` guarda o objeto `Tear` pelo mesmo motivo, porque ao executar a ordem ela registra a produção no contador da própria máquina, e o `main` enxerga a mudança no mesmo objeto.

---

### Aula 6 - Herança

**Pergunta:** "No nosso código, a mãe `Veiculo` possui os atributos `placa` e `modelo` como `private`. Quando o `Carro` herda de `Veiculo`, ele recebe esses atributos, mas o código dentro de `Carro` NÃO consegue fazer `this.placa = "ABC"`. Ele é obrigado a usar o `super()` ou o `setPlaca()`.

Por que o Java não deixa a filha alterar as variáveis privadas da mãe diretamente? Qual o princípio das aulas passadas que isso está protegendo?"

**Sua Resposta:**
O Java protege o encapsulamento das Aulas 3 e 4. A validação da placa está no `setPlaca()` privado da mãe, e se a filha pudesse escrever `this.placa = "ABC"`, toda classe que estendesse `Veiculo` teria uma porta lateral para pular essa regra, e o encapsulamento valeria só para quem não usa herança. Com `private`, a filha é obrigada a passar pelo `super(placa, modelo)`, e quem valida é a mãe, em um único lugar. Isso também mantém a mãe livre para mudar a forma de guardar os dados sem quebrar as filhas. No meu projeto, `TearJatoDeAr` chama `super(codigo, modelo, VELOCIDADE_INICIAL_RPM)`, e quem garante que a velocidade está na faixa segura é o `Tear`. Usei `protected` em um único ponto, o método `estimarMetrosPorHora()`, que as filhas precisam para calcular a produção, mas que não faz sentido para o resto do sistema.

---

### Aula 7 - Polimorfismo

**Pergunta:** "No nosso loop `for (Veiculo veiculo : frota)`, a variável `veiculo` é do tipo genérico `Veiculo`. Se esquecêssemos de criar o método `calcularAutonomia()` lá na classe mãe `Veiculo`, nós conseguiríamos chamá-lo dentro do loop, mesmo sabendo que ele existe dentro do `Carro` e da `Moto`? Por que o contrato precisa existir na base da hierarquia?"

**Sua Resposta:**
Não conseguiríamos. O compilador decide quais métodos podem ser chamados pelo tipo da variável, e não pelo objeto guardado nela, então se `Veiculo` não tiver `calcularAutonomia()` a chamada não compila, mesmo que todos os objetos da lista sejam carros e motos. Só na execução o Java olha o objeto real e escolhe a versão sobrescrita. O contrato precisa existir na base para garantir ao compilador que todo `Veiculo` responde a essa mensagem, deixando para cada filha a decisão de como responder. Sem ele, a saída seria testar o tipo com `instanceof` e fazer cast dentro do laço, que é justamente o "if para descobrir o tipo" que a aula condena, porque cada tipo novo obrigaria a mexer no laço. No projeto pessoal, a `List<Tear>` chama `calcularProducaoPorHora()` sem saber se a máquina é a jato de ar (92% de eficiência) ou de pinças (85%), e um terceiro tipo de tear entraria no relatório sem mudar uma linha do laço.

---

### Aula 8 - Classes Abstratas

**Pergunta:** "Pense no mundo real: Faz sentido existir um objeto que é APENAS 'Veículo' sem ser um tipo específico? Você já entrou em uma concessionária e comprou "um veículo" genérico, sem ser carro, moto, caminhão ou nada disso?

Por que, então, no código, precisamos EXPLICITAMENTE dizer ao Java que `Veiculo` é `abstract`? Por que ele não deduz isso sozinho?

Pense: Se esquecermos de colocar `abstract`, qual o risco que corremos? Alguém pode criar `new Veiculo()` e quebrar a lógica do nosso sistema?"

**Sua Resposta:**
Não faz sentido, assim como na fábrica não existe "tear genérico", só teares a jato de ar, de pinças e assim por diante. O Java não deduz isso porque, para o compilador, uma classe concreta com construtor público é uma permissão para instanciar, e ele não conhece o mundo real. Declarar `abstract` é uma decisão de projeto que só quem modela o domínio pode tomar. O risco apareceu no meu próprio código, porque na Aula 7 a frota tinha um `new Veiculo("GEN-0000", "Veículo genérico")` que respondia "Autonomia não definida", e o parque de teares tinha um `new Tear()` que respondia "Produção não definida", objetos sem sentido que entram em relatórios e cálculos. Quando tornei as duas classes abstratas, o compilador apontou cada lugar que criava esses objetos, e precisei trocá-los por `Carro` e `TearJatoDeAr`. Aproveitei para mudar duas coisas no `Tear`. O construtor padrão `Tear()` saiu, porque não existe mais tear sem tipo para criar, e `calcularProducaoPorHora()` virou abstrato, porque a resposta genérica da mãe nunca mais seria usada.

---

### Aula 9 - Interfaces

**Pergunta:** "Por que Java permite herança simples (apenas uma mãe), mas múltipla implementação de interfaces (vários contratos)?

Pense: Se `CarroEletrico` pudesse herdar de `Veiculo` E de `Bateria` ao mesmo tempo (herança múltipla), o que aconteceria se AMBAS as mães tivessem um método chamado `ligar()`?

Como as interfaces resolvem esse problema?"

**Sua Resposta:**
Herança múltipla de classes cria ambiguidade. Se `CarroEletrico` herdasse de `Veiculo` e de `Bateria` e as duas tivessem `ligar()` com códigos diferentes, o Java não saberia qual executar, que é o chamado problema do diamante. As interfaces resolvem isso porque definem só a assinatura, sem implementação, então mesmo que duas interfaces exijam `ligar()`, a classe escreve uma única versão que atende aos dois contratos. Nos métodos `default` (do Java 8 em diante), que têm corpo, o compilador obriga a classe a resolver o conflito de forma explícita, então a ambiguidade continua impossível. Assim a classe herda código de uma só mãe e pode assinar quantos contratos precisar. No FiapRide, `CarroEletrico` É UM `Veiculo` e também É `Recarregavel`, e o `Celular`, que não é veículo, entra no mesmo laço de recarga. No meu projeto criei a interface `Manutenivel`, com a constante `INTERVALO_MANUTENCAO_HORAS = 2000`, e com ela os teares e o compressor de ar, que não é tear, entram no mesmo plano de manutenção preventiva. Sem a interface, a única forma de tratá-los juntos seria inventar uma mãe comum artificial.

---

## 🚀 Desafios Técnicos Implementados

### Desafio Pessoal (Seu Projeto)

**Qual foi o domínio que você escolheu para seu projeto pessoal?**
Controle de teares de uma tecelagem. Escolhi esse domínio porque tenho mais de dez anos de experiência na indústria têxtil, com operações e controle de produção, e conheço as regras reais das máquinas (faixa de velocidade, eficiência por tipo de tear, ordens de produção e manutenção preventiva).

**Quais classes você criou?**
- `Tear` (classe abstrata): código, modelo, velocidade em rpm, metros produzidos e horas de uso. Implementa `Manutenivel`.
- `TearJatoDeAr` e `TearDePinca` (filhas de `Tear`): pressão do ar e quantidade de cores, cada uma com sua eficiência no cálculo da produção.
- `OrdemDeProducao`: associação com `Tear` (TEM UM), registra a produção na própria máquina e não pode ser executada duas vezes.
- `Manutenivel` (interface): contrato de manutenção preventiva com a constante `INTERVALO_MANUTENCAO_HORAS`.
- `Compressor`: não é tear, mas implementa `Manutenivel`.
- `TesteMeuObjeto`: classe de testes, com um método por aula.

**Qual foi o maior desafio técnico que você enfrentou?**
O maior desafio foi transformar o `Tear` em classe mãe e depois em classe abstrata sem perder o que já funcionava. Até a Aula 5 o `Tear` era uma classe concreta usada em todo o código, inclusive na `OrdemDeProducao`, e tinha um construtor padrão `Tear()` que eu tinha criado na Aula 4. Na Aula 6 ele virou a mãe de `TearJatoDeAr` e `TearDePinca`, e o primeiro cuidado foi não duplicar regras, então as filhas chamam `super()` e quem valida código, modelo e velocidade continua sendo só o `Tear`. A `OrdemDeProducao` não precisou mudar, porque já recebia um `Tear`, e passou a aceitar qualquer tipo de máquina, o que mostrou na prática a vantagem de depender da mãe. Na Aula 7 precisei de uma conta de produção diferente para cada tipo de tear, e para não repetir a fórmula nas filhas criei o método `protected estimarMetrosPorHora(eficiencia)` na mãe, deixando para cada filha apenas a sua eficiência. Na Aula 8, ao declarar `Tear` como `abstract`, o compilador acusou todos os `new Tear(...)` dos testes, e tive que decidir o que fazer com o construtor padrão e com a resposta genérica de `calcularProducaoPorHora()`. Removi o construtor e tornei o método abstrato, porque os dois só existiam para um "tear genérico" que não existe na fábrica. Um limite que ficou claro nesse processo é que as validações só imprimem uma mensagem, então um `TearJatoDeAr` criado com pressão de 15 bar continua existindo com pressão zero. A solução correta é lançar exceções, conteúdo previsto para o próximo semestre.

---

## 🎓 Conclusão

**O que você aprendeu nestas 9 aulas?**
Aprendi a pensar o software como objetos que guardam dados e protegem as próprias regras, e não como uma lista de instruções no `main`. A evolução fica clara no histórico de commits, porque o projeto começou com atributos públicos que qualquer linha podia alterar, passou a ter métodos com validação, atributos privados e construtores que impedem objetos incompletos, e terminou com associação, herança, polimorfismo, classes abstratas e interfaces, em que adicionar um tipo novo de veículo ou de tear não exige mudar o código que já existe. Também aprendi as ferramentas do dia a dia, como Git com chave SSH, VS Code no Ubuntu (WSL) e diagramas UML no Astah. Modelar os teares mostrou que orientação a objetos se parece muito com o trabalho de controle de produção que eu já fazia, em que é preciso definir o que cada máquina é, o que ela faz e quais regras ninguém pode pular.

**Qual conceito foi mais difícil de entender?**
A associação e a forma de representá-la no diagrama. No código a ideia de "TEM UM" foi tranquila, mas no Astah, durante o Check Point 2, confundi o nome da associação com o papel (role) que fica na ponta da linha e coloquei `pacoteTransportado` no lugar errado, além de deixar os construtores com `: void`. Superei comparando o diagrama com o código linha a linha, até entender que o papel na ponta da linha corresponde ao atributo privado da classe, como `- solicitante` na `Viagem`. A diferença entre "É UM" (herança) e "TEM UM" (associação) também exigiu atenção, e o teste da frase em português foi o que mais me ajudou a decidir.

**O que você melhoraria no seu projeto se pudesse refazer?**
- **Exceções no lugar de mensagens:** hoje um objeto com dado inválido nasce mesmo assim, com valores zerados (por exemplo, um carro com capacidade inválida fica com 0 lugares). Com `throw new IllegalArgumentException(...)` no construtor, o objeto inválido nem chegaria a existir.
- **Combustível fora de `Veiculo`:** o `CarroEletrico` herda `abastecer()`, o que não faz sentido. Eu moveria o tanque para uma interface `Abastecivel`, implementada só por `Carro` e `Moto`.
- **Código repetido entre `Tear` e `Compressor`:** o controle de horas de uso está nas duas classes. Uma classe abstrata `Equipamento` acima delas eliminaria a repetição.
- **`calcularAutonomia()` abstrato:** fiz isso com `calcularProducaoPorHora()` no `Tear` e faria o mesmo no `Veiculo`, já que nenhum veículo concreto usa a resposta genérica.
- **Dinheiro em `BigDecimal`:** `double` acumula erros de arredondamento em valores monetários.
- **Testes automatizados com JUnit** no lugar de testes visuais com `System.out.println` no `main`.
