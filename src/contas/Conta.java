package contas;

import clientes.Cliente;
import exceptions.*;
import transacoes.Transacoes;

public abstract class Conta implements iConta {

    private static int SEQUENCIAL = 1;
    private static final String agenciaPadrao = "001";

    protected String agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected Transacoes transacoes;

    public Conta(Cliente cliente){
        this.agencia = agenciaPadrao;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.transacoes = new Transacoes();
    }

    @Override
    public void depositar(double valor)throws ValorInvalidoException{
        if (valor <= 0){
            throw new ValorInvalidoException();
        }else {
            saldo += valor;
            transacoes.adicionarDeposito(valor, saldo);
        }

    }

    @Override
    public void sacar(double valor) throws ValorInvalidoException, SaldoInsuficiente{
        if (valor <= 0){
          throw new ValorInvalidoException();
        }else if (valor > saldo) {
          throw new SaldoInsuficiente();
        }else {
          saldo -= valor;
          transacoes.adicionarSaque(valor, saldo);
        }
    }

    @Override
    public void transferir(double valor, Conta contaDestino) throws ValorInvalidoException, SaldoInsuficiente {
        if (valor <= 0){
            throw new ValorInvalidoException();
        }else{
            sacar(valor);
            contaDestino.depositar(valor);
            transacoes.adicionarTransferencia(valor, saldo, contaDestino.getNumero());
        }
    }

    public Cliente getCliente() {
        return cliente;
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
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Agencia: " + agencia);
        System.out.println("Numero: " + numero);
        System.out.printf("Saldo: R$ %.2f\n\n", saldo);
        System.out.println("=========================");
        System.out.println("Transações:");
        System.out.println("=========================");
        transacoes.getTransacoes().forEach(t -> System.out.println(t + "\n-------------------------"));
    }

    @Override
    public String toString() {
        return "Conta{" +
                "agencia='" + agencia + "'" +
                ", numero=" + numero +
                ", saldo=R$ " + String.format("%.2f", saldo) +
                ", titular='" + cliente.getNome() + "'" +
                "}";
    }
}
