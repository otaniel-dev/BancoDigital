import Contas.Conta;
import Contas.ContaCorrente;
import Contas.ContaPoupanca;

public class Main {
    public static void main(String[] args){

        Conta contaPoupanca = new ContaPoupanca();
        Conta contaCorrente = new ContaCorrente();

        contaPoupanca.depositar(100);
        contaPoupanca.sacar(25);
        contaPoupanca.transferir(25,contaCorrente);
        contaPoupanca.imprimirExtrato();

        contaCorrente.imprimirExtrato();

    }
}