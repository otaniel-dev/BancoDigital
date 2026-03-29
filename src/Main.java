import Contas.*;
import Clientes.Cliente;
import Exceptions.*;

public class Main {
    public static void main(String[] args) throws ValorInvalidoException, SaldoInsuficiente {

        Cliente cliente = new Cliente();
        cliente.setNome("Otaniel");
        cliente.setCpf("123.456.789-00");

        Conta contaPoupanca = new ContaPoupanca(cliente);
        Conta contaCorrente = new ContaCorrente(cliente);

        contaPoupanca.depositar(100);
        contaPoupanca.sacar(25);
        contaPoupanca.transferir(30, contaCorrente );
        contaPoupanca.imprimirExtrato();

        contaCorrente.imprimirExtrato();


    }
}