package Contas;

import Exceptions.*;

public interface iConta {
    void depositar(double valor) throws ValorInvalidoException;
    void sacar(double valor) throws ValorInvalidoException, SaldoInsuficiente;
    void transferir(double valor, Conta contaDestino) throws ValorInvalidoException, SaldoInsuficiente;
    void imprimirExtrato();
}
