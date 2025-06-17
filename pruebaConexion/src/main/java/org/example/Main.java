package org.example;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente(2, "Naza", "naza@naza.com");
        Producto producto = new Producto(2, "Pantalon", 100000, 7);
        guardarEntidad(cliente);
        guardarEntidad(producto);

        PedidoDAO pedidoDAO = new PedidoDAO();
        PedidoThread pedidoThread = new PedidoThread(producto, cliente, pedidoDAO);

        pedidoThread.start();
        try {
            pedidoThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pedido realizo con exito");
    }

    private static void guardarEntidad(Object entidad) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.merge(entidad);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
