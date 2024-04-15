package dao.impl;

import dao.ProductSaleDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.Product;
import models.ProductSale;
import util.HibernateUtil;

import java.util.List;

public class ProductSaleDaoImpl implements ProductSaleDao {
    private EntityManager em;

    public ProductSaleDaoImpl() {
        em = HibernateUtil.getInstance().getEntityManager();
    }
    @Override
    public List<ProductSale> getProductSale() {
        EntityTransaction tr = em.getTransaction();
        try {
            if (!tr.isActive()) {
                tr.begin();
            }
            List<ProductSale> productSales = em.createQuery("FROM ProductSale ", ProductSale.class).getResultList();
            tr.commit();
            return productSales;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addProductSale(ProductSale productSale) {
        EntityTransaction entityTransaction = em.getTransaction();
        try {
            entityTransaction.begin();
            em.persist(productSale);
            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProductSale(String id) {
        EntityTransaction tr = em.getTransaction();
        try {
            em.createQuery("DELETE FROM ProductSale ps WHERE ps.product.id= :id")
                    .setParameter("id", id)
                    .executeUpdate();
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductSale getProductSaleById(String id) {
        EntityTransaction tr = em.getTransaction();
        try {
            return em.createQuery("SELECT ps FROM ProductSale ps WHERE ps.product.id =:id ", ProductSale.class)
                .setParameter("id",   id ) // %text% for similarity
                .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}