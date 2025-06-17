package org.example;

import java.time.LocalDate;

public class PedidoThread extends Thread{
    private Producto producto;

    private Cliente cliente;

    private PedidoDAO pedidoDAO;

    public PedidoThread(Producto producto, Cliente cliente, PedidoDAO pedidoDAO) {
        this.producto = producto;
        this.cliente = cliente;
        this.pedidoDAO = pedidoDAO;
    }

    @Override
    public void run(){
        System.out.println("El cliente " + cliente.getNombre() + " ordeno el producto: " + producto.getNombre());

        if(producto.getStock() <= 0){
            System.out.println("No hay stock del producto "+producto.getNombre());
            return;
        }
        producto.setStock(producto.getStock()-1);
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        pedido.setProducto(producto);
        pedido.setCantidad(1);
        pedido.setFecha(LocalDate.now());

        pedidoDAO.guardarPedido(pedido);




        System.out.println("Nuevo stock del producto: "+producto.getStock());

    }
}
