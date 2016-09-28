/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Entidades.CadeteEntity;
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
public class CadeteBean {

  @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    private void init() {
        System.out.println("INSTANCIA CADETE BEAN");
    }
       
    public CadeteEntity agregar(CadeteEntity u) {
        return agregar(u.getNombre());
    }
 
    public CadeteEntity agregar(String nombre) {
        
        CadeteEntity u = new CadeteEntity();
        u.setNombre(nombre);
        
        
        em.persist(u);
        
        return u;
    }
    
    public CadeteEntity modificar(Long id, String nombreNuevo) {
        
        CadeteEntity u = em.find(CadeteEntity.class, id);
        
        u.setNombre(nombreNuevo);
        
        em.merge(u);
        
        return u;
    }
    
    public boolean eliminar(Long id) {
        CadeteEntity u = em.find(CadeteEntity.class, id);
        
        em.remove(u);
        
        return true;
    }
    
    public List<CadeteEntity> listar() {
        
        List<CadeteEntity> list = 
                em
                    .createQuery("select c.* from CadeteEntity c")
                    .getResultList();
        
        return list;
    }
    
    public Cadete buscar(Long id) {
        CadeteEntity ent = em.find(CadeteEntity.class, id);
        Cadete u = new Cadete();
        u.setId(ent.getId());
        u.setNombre(ent.getNombre());
        return u;
    }
    
    public List<CadeteEntity> buscar(String nombre) {
        List<CadeteEntity> list = em.createQuery("select c.* from CadeteEntity c "
        + "where u.nombre = :nombre").setParameter("nombre", nombre).getResultList();
        return list;
    }

}
