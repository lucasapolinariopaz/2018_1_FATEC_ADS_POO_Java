package model.beans;

public class Lista_prod_venda
{
    private Produto produto;
    private int quantidade_vendida;
    private double total_parcial;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade_vendida() {
        return quantidade_vendida;
    }

    public void setQuantidade_vendida(int quantidade_vendida) {
        this.quantidade_vendida = quantidade_vendida;
    }

    public double getTotal_parcial() {
        return total_parcial;
    }

    public void setTotal_parcial(double total_parcial) {
        this.total_parcial = total_parcial;
    }
}
