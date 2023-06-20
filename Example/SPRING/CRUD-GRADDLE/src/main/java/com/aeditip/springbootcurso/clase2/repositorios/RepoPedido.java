package com.aeditip.springbootcurso.clase2.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeditip.springbootcurso.clase2.entidades.Pedido;

public interface RepoPedido extends JpaRepository<Pedido, Integer> {
    @Query(value="SELECT * FROM scm_pedido WHERE id_cliente = ?1", nativeQuery = true)
    List<Pedido> findAllByClient(int idCliente);

    @Query(value="SELECT * FROM scm_pedido WHERE id_cliente = ?1 AND estado = ?2", nativeQuery = true)
    List<Pedido> findAllByClientAndStatus(int idCliente, int estado);
}
