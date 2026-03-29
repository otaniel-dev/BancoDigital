package Contas;
import Clientes.Cliente;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente){
        super(cliente);
    };

    public void imprimirExtrato(){
        System.out.println("=== Extrato da Conta Poupança ===");
        super.imprimirInformacoesComuns();
        System.out.println("=================================");
    }
}
