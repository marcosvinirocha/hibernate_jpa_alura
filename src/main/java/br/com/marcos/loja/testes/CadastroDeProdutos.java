package br.com.marcos.loja.testes;

import br.com.marcos.loja.dao.CategoriaDao;
import br.com.marcos.loja.dao.ProdutoDao;
import br.com.marcos.loja.modelo.Categoria;
import br.com.marcos.loja.modelo.Produto;
import br.com.marcos.loja.util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutos {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeCategoria("CELULARES");
        todos.forEach(p2 -> System.out.println(p.getNome()));

    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
