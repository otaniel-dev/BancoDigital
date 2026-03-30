# Sistema Bancário em Java

Projeto desenvolvido para praticar os principais conceitos de **Orientação a Objetos** em Java, modelando um sistema bancário simples com clientes, contas, transações e validações.

---

## Sobre o projeto

O objetivo não foi só fazer funcionar — foi fazer direito. Cada decisão de design foi pensada: onde colocar a validação, como comunicar erros, como separar responsabilidades entre as classes.

O projeto evoluiu de 7 classes simples sem relacionamento para um sistema com banco gerenciador, histórico de transações e exceções customizadas.

---

## Funcionalidades

- Criar clientes e associá-los a contas
- Abrir contas correntes e poupança
- Realizar depósitos, saques e transferências com validação
- Consultar extrato com histórico completo de movimentações
- Buscar contas por CPF ou por objeto cliente
- Banco central que gerencia clientes e contas

---

## Conceitos aplicados

| Conceito | Onde aparece |
|---|---|
| Classe abstrata | `Conta` — define comportamento base para todos os tipos de conta |
| Herança | `ContaCorrente` e `ContaPoupanca` estendem `Conta` |
| Interface | `iConta` define o contrato que toda conta deve cumprir |
| Encapsulamento | Validações dentro dos métodos protegem o estado da conta |
| Exceções customizadas | `SaldoInsuficiente` e `ValorInvalidoException` |
| Separação de responsabilidades | Classe `Transacoes` dedicada ao histórico |
| Coleções e Streams | `Banco` usa `List` e Stream API para buscar contas |
| Pacotes | Organização por `contas`, `clientes`, `exceptions`, `transacoes` |

---

## Estrutura do projeto

```
src/
├── Banco.java
├── Main.java
├── Clientes/
│   └── Cliente.java
├── Contas/
│   ├── iConta.java
│   ├── Conta.java
│   ├── ContaCorrente.java
│   └── ContaPoupanca.java
├── Exceptions/
│   ├── SaldoInsuficiente.java
│   └── ValorInvalidoException.java
└── Transacoes/
    └── Transacoes.java
```

---

## Diagrama de classes

```
iConta (interface)
    └── Conta (abstract)
            ├── ContaCorrente
            └── ContaPoupanca

Conta ──► Cliente  (toda conta tem um titular)
Conta ──► Transacoes  (histórico de movimentações)
Banco ──► Conta[]  (gerencia todas as contas)
Banco ──► Cliente[]  (gerencia todos os clientes)
```

---

## Como executar

**Pré-requisitos:** Java 11 ou superior.

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/sistema-bancario-java.git

# Entre na pasta
cd sistema-bancario-java

# Compile
javac -d out src/**/*.java src/*.java

# Execute
java -cp out Main
```

---

## Exemplo de saída

```
=== Extrato da Conta Poupança ===
Titular: Otaniel
Agencia: 001
Numero: 1
Saldo: R$ 45,00

=========================
Transações:
=========================
Saldo anterior : R$ 0,00
Tipo de operação : Deposito
Valor : R$ 100,00
Saldo após operação : R$ 100,00
-------------------------
Saldo anterior : R$ 100,00
Tipo de operação : Saque
Valor : R$ 25,00
Saldo após operação : R$ 75,00
-------------------------
Saldo anterior : R$ 75,00
Tipo de operação : Transferência
Valor : R$ 30,00
Conta destino : 2
Saldo após operação : R$ 45,00
=================================
```

---

## Cenários de teste cobertos

- Depósito com valor válido
- Depósito com valor zero ou negativo → `ValorInvalidoException`
- Saque com saldo suficiente
- Saque com saldo insuficiente → `SaldoInsuficiente`
- Saque com valor inválido → `ValorInvalidoException`
- Transferência entre contas
- Transferência com saldo insuficiente → `SaldoInsuficiente`
- Busca de contas por CPF
- Busca de contas por objeto Cliente
- Extrato completo com histórico

---

## Próximos passos

- [ ] Implementar `equals()` e `hashCode()` em `Cliente`
- [ ] Adicionar rendimento de juros em `ContaPoupanca`
- [ ] Adicionar limite de cheque especial em `ContaCorrente`
- [ ] Converter nomes de pacotes para letras minúsculas (convenção Java)

---

## Autor

Feito por **Otaniel** como projeto de estudo de Orientação a Objetos em Java.
