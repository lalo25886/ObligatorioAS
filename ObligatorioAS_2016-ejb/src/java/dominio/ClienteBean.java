///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Dominio;
//
//import Entidades.ClienteEntity;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.ejb.Stateless;
//import javax.ejb.LocalBean;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
///**
// *
// * @author alvaro
// */
//@Stateless
//@LocalBean
//public class ClienteBean {
//
//   
//    @PersistenceContext
//    private EntityManager em;
//    
//    @PostConstruct
//    private void init() {
//        System.out.println("INSTANCIA CLIENTE BEAN");
//    }
//       
//    public ClienteEntity agregar(ClienteEntity u) {
//        return agregar(u.getNombre());
//    }
// 
//    public ClienteEntity agregar(String nombre) {
//        
//        ClienteEntity u = new ClienteEntity();
//        u.setNombre(nombre);
//        
//        
//        em.persist(u);
//        
//        return u;
//    }
//    
//    public ClienteEntity modificar(Long id, String nombreNuevo) {
//        
//        ClienteEntity u = em.find(ClienteEntity.class, id);
//        
//        u.setNombre(nombreNuevo);
//        
//        em.merge(u);
//        
//        return u;
//    }
//    
//    public boolean eliminar(Long id) {
//        ClienteEntity u = em.find(ClienteEntity.class, id);
//        
//        em.remove(u);
//        
//        return true;
//    }
//    
//    public List<ClienteEntity> listar() {
//        
//        List<ClienteEntity> list = 
//                em
//                    .createQuery("select u from Cliente u")
//                    .getResultList();
//        
//        return list;
//    }
//    
//    public Cliente buscar(Long id) {
//        ClienteEntity ent = em.find(ClienteEntity.class, id);
//        Cliente u = new Cliente();
//        u.setId(ent.getId());
//        u.setNombre(ent.getNombre());
//        return u;
//    }
//    
//    public List<ClienteEntity> buscar(String nombre) {
//        List<ClienteEntity> list = em.createQuery("select u from Cliente u "
//        + "where u.nombre = :nombre").setParameter("nombre", nombre).getResultList();
//        return list;
//    }
//
//}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import Entidades.ClienteEntity;
import com.google.gson.Gson;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvaro
 */
@Stateless
@LocalBean
public class ClienteBean {

   
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CLIENTE BEAN");
    }
       
    public ClienteEntity agregar(ClienteEntity u) {
         em.persist(u);
         return u;

    }
 
    public ClienteEntity agregar(String body) {
       Gson gson = new Gson();
       ClienteEntity u = gson.fromJson(body, ClienteEntity.class);
        
        em.persist(u);
        
        return u;
    }
    
    public ClienteEntity modificar(ClienteEntity u) {
        
        em.merge(u);
        
        return u;
    }
    
    public boolean eliminar(ClienteEntity u) {
       ClienteEntity aBorrar = em.find(ClienteEntity.class, u.getId());
        em.remove(aBorrar);
        
        return true;
    }
    
    public List<ClienteEntity> listar() {
        
        List<ClienteEntity> list = 
                em
                    .createQuery("select u from ClienteEntity u")
                    .getResultList();
        
        return list;
    }
    
    public Cliente buscar(Long id) {
        ClienteEntity ent = em.find(ClienteEntity.class, id);
        Cliente u = new Cliente();
        u.setId(ent.getId());
        u.setNombre(ent.getNombre());
        return u;
    }
    
    public List<ClienteEntity> buscar(String nombre) {
        List<ClienteEntity> list = em.createQuery("select u from Cliente u "
        + "where u.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return list;
    }

}
