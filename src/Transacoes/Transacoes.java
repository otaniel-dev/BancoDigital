package Transacoes;

import java.util.*;

public class Transacoes {

    List <String> transacoes = new ArrayList<>();

    public void adicionarDeposito(double valor, double saldoAtual){

        transacoes.add(String.format("Saldo anterior : R$ %.2f" +
                                    "\nTipo de operação : Deposito" +
                                    "\nValor : R$ %.2f" +
                                    "\nSaldo após operação : R$ %.2f",saldoAtual - valor, valor , saldoAtual));
    }

    public void adicionarSaque(double valor, double saldoAtual){

        transacoes.add(String.format("Saldo anterior : R$ %.2f" +
                                    "\nTipo de operação : Saque" +
                                    "\nValor : R$ %.2f" +
                                    "\nSaldo após operação : R$ %.2f",saldoAtual + valor, valor , saldoAtual));
    }

    public void adicionarTransferencia(double valor, double saldoAtual, int contaDestino){

        transacoes.add(String.format("Saldo anterior : R$ %.2f" +
                                    "\nTipo de operação : Transferência" +
                                    "\nValor : R$ %.2f" +
                                    "\nConta destino : %d" +
                                    "\nSaldo após operação : R$ %.2f",saldoAtual + valor, valor , contaDestino, saldoAtual));

    }

    public List<String> getTransacoes() {
        return transacoes;
    }
}
