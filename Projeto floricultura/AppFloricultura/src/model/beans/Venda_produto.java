package model.beans;

public class Venda_produto
{
    private Venda venda;
    private Produto produto;
    private int qtd_prod_venda;

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtd_prod_venda() {
        return qtd_prod_venda;
    }

    public void setQtd_prod_venda(int qtd_prod_venda) {
        this.qtd_prod_venda = qtd_prod_venda;
    }   
}
