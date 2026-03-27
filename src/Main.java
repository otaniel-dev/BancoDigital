import Contas.Conta;
import Contas.ContaPoupanca;

public class Main {
    public static void main(String[] args){

        Conta contaPoupanca = new ContaPoupanca();

        contaPoupanca.depositar(100);
        contaPoupanca.imprimirExtrato();
    }
}