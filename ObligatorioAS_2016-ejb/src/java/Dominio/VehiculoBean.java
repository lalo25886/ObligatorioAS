/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Entidades.VehiculoEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gonzalo
 */
@Stateless
@LocalBean
public class VehiculoBean {

   
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA VEHICULO BEAN");
    }
       
    public VehiculoEntity agregar(VehiculoEntity v) {
        return agregar(v.getMatricula());
    }
 
    public VehiculoEntity agregar(String matricula) {
        
        VehiculoEntity v = new VehiculoEntity();
        v.setMatricula(matricula);
        
        
        em.persist(v);
        
        return v;
    }
    
    public VehiculoEntity modificar(Long id, String matriculaNueva) {
        
        VehiculoEntity v = em.find(VehiculoEntity.class, id);
        
        v.setMatricula(matriculaNueva);
        
        em.merge(v);
        
        return v;
    }
    
    public boolean eliminar(Long id) {
        VehiculoEntity v = em.find(VehiculoEntity.class, id);
        
        em.remove(v);
        
        return true;
    }
    
    public List<VehiculoEntity> listar() {
        
        List<VehiculoEntity> list = 
                em
                    .createQuery("select u from Vehiculo u")
                    .getResultList();
        
        return list;
    }
    
    public Vehiculo buscar(Long id) {
        VehiculoEntity ent = em.find(VehiculoEntity.class, id);
        Vehiculo v = new Vehiculo();
        v.setId(ent.getId());
        v.setMatricula(ent.getMatricula());
        return v;
    }
    
    public List<VehiculoEntity> buscar(String nombre) {
        List<VehiculoEntity> list = em.createQuery("select u from Vehiculo u "
        + "where u.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return list;
    }

}

