import banco.Banco;
import contas.*;
import clientes.Cliente;
import exceptions.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       SISTEMA BANCÁRIO - TESTES      ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ─────────────────────────────────────────
        // SETUP: clientes e banco
        // ─────────────────────────────────────────
        Cliente otaniel = new Cliente();
        otaniel.setNome("Otaniel");
        otaniel.setCpf("123.456.789-00");

        Cliente paula = new Cliente();
        paula.setNome("Paula");
        paula.setCpf("987.654.321-00");

        Banco banco = new Banco("OTO Bank");
        banco.adicionarCliente(otaniel);
        banco.adicionarCliente(paula);

        Conta poupancaOtaniel  = new ContaPoupanca(otaniel);
        Conta correnteOtaniel  = new ContaCorrente(otaniel);
        Conta correntePaula    = new ContaCorrente(paula);

        banco.adicionarConta(poupancaOtaniel);
        banco.adicionarConta(correnteOtaniel);
        banco.adicionarConta(correntePaula);

        // ─────────────────────────────────────────
        // CENÁRIO 1: Depósito normal
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 1: Depósito normal ══");
        try {
            poupancaOtaniel.depositar(500);
            correntePaula.depositar(300);
            System.out.println("✔ Depósitos realizados com sucesso.\n");
        } catch (ValorInvalidoException e) {
            System.out.println("✘ Erro: " + e.getMessage());
        }

        // ─────────────────────────────────────────
        // CENÁRIO 2: Depósito com valor inválido (zero e negativo)
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 2: Depósito com valor inválido ══");
        try {
            poupancaOtaniel.depositar(0);
            System.out.println("✘ Deveria ter lançado exceção!");
        } catch (ValorInvalidoException e) {
            System.out.println("✔ Valor zero bloqueado: " + e.getMessage());
        }
        try {
            poupancaOtaniel.depositar(-100);
            System.out.println("✘ Deveria ter lançado exceção!");
        } catch (ValorInvalidoException e) {
            System.out.println("✔ Valor negativo bloqueado: " + e.getMessage() + "\n");
        }

        // ─────────────────────────────────────────
        // CENÁRIO 3: Saque normal
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 3: Saque normal ══");
        try {
            poupancaOtaniel.sacar(100);
            System.out.println("✔ Saque de R$ 100,00 realizado.\n");
        } catch (ValorInvalidoException | SaldoInsuficiente e) {
            System.out.println("✘ Erro: " + e.getMessage());
        }

        // ─────────────────────────────────────────
        // CENÁRIO 4: Saque com saldo insuficiente
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 4: Saque com saldo insuficiente ══");
        try {
            poupancaOtaniel.sacar(9999);
            System.out.println("✘ Deveria ter lançado exceção!");
        } catch (SaldoInsuficiente e) {
            System.out.println("✔ Saldo insuficiente bloqueado: " + e.getMessage() + "\n");
        } catch (ValorInvalidoException e) {
            System.out.println("✘ Exceção errada: " + e.getMessage());
        }

        // ─────────────────────────────────────────
        // CENÁRIO 5: Saque com valor inválido
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 5: Saque com valor inválido ══");
        try {
            poupancaOtaniel.sacar(-50);
            System.out.println("✘ Deveria ter lançado exceção!");
        } catch (ValorInvalidoException e) {
            System.out.println("✔ Valor negativo no saque bloqueado: " + e.getMessage() + "\n");
        } catch (SaldoInsuficiente e) {
            System.out.println("✘ Exceção errada: " + e.getMessage());
        }

        // ─────────────────────────────────────────
        // CENÁRIO 6: Transferência normal
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 6: Transferência normal ══");
        try {
            poupancaOtaniel.transferir(200, correnteOtaniel);
            System.out.println("✔ Transferência de R$ 200,00 realizada.\n");
        } catch (ValorInvalidoException | SaldoInsuficiente e) {
            System.out.println("✘ Erro: " + e.getMessage());
        }

        // ─────────────────────────────────────────
        // CENÁRIO 7: Transferência com saldo insuficiente
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 7: Transferência com saldo insuficiente ══");
        try {
            correnteOtaniel.transferir(9999, correntePaula);
            System.out.println("✘ Deveria ter lançado exceção!");
        } catch (SaldoInsuficiente e) {
            System.out.println("✔ Transferência bloqueada: " + e.getMessage() + "\n");
        } catch (ValorInvalidoException e) {
            System.out.println("✘ Exceção errada: " + e.getMessage());
        }

        // ─────────────────────────────────────────
        // CENÁRIO 8: Busca de contas por CPF
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 8: Busca de contas por CPF ══");
        List<Conta> contasOtaniel = banco.getContasPorCPF(otaniel.getCpf());
        System.out.println("✔ Contas de Otaniel encontradas: " + contasOtaniel.size());
        contasOtaniel.forEach(c -> System.out.println("   → " + c));
        System.out.println();

        // ─────────────────────────────────────────
        // CENÁRIO 9: Busca de contas por objeto Cliente
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 9: Busca de contas por objeto Cliente ══");
        List<Conta> contasPaula = banco.getContasPorCliente(paula);
        System.out.println("✔ Contas de Paula encontradas: " + contasPaula.size());
        contasPaula.forEach(c -> System.out.println("   → " + c));
        System.out.println();

        // ─────────────────────────────────────────
        // CENÁRIO 10: Extratos finais
        // ─────────────────────────────────────────
        System.out.println("══ CENÁRIO 10: Extratos finais ══\n");
        poupancaOtaniel.imprimirExtrato();
        System.out.println();
        correnteOtaniel.imprimirExtrato();
        System.out.println();
        correntePaula.imprimirExtrato();
    }
}