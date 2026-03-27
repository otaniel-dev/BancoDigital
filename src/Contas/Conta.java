package Contas;
import Contas.iConta;

public abstract class Conta implements iConta {

    private static int SEQUENCIAL = 1;
    private static final String agenciaPadrao = "001";

    protected String agencia;
    protected int numero;
    protected double saldo;

    public Conta(){
        this.agencia = agenciaPadrao;
        this.numero = SEQUENCIAL++;
    }

    @Override
    public void depositar(double valor){
        saldo += valor;
    }

    public void sacar(double valor){
        saldo -= valor;
    }

    public void transferir(double valor, Conta contaDestino){
         saldo -= valor;
         contaDestino.depositar(valor);
    }

    public String getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInformacoesComuns(){
        System.out.println("Agencia: " + agencia);
        System.out.println("Numero: " + numero);
        System.out.printf("Saldo: R$ %.2f", saldo);
    }

}
