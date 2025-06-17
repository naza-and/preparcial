package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PedidoDAO {
    public void guardarPedido(Pedido pedido){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();

            Cliente cliente = (Cliente) session.merge(pedido.getCliente());
            Producto producto = (Producto) session.merge(pedido.getProducto());

            pedido.setCliente(cliente);
            pedido.setProducto(producto);

            session.persist(pedido);

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
