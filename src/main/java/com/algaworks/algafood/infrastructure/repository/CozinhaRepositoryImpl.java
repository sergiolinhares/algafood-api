package com.algaworks.algafood.infrastructure.repository;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> listar(){
		TypedQuery<Cozinha>query =  manager.createQuery("from Cozinha", Cozinha.class);
		
		return query.getResultList();
	}
	
	@Override
	public Cozinha porId(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public Cozinha adicionar(Cozinha cozinha){
		return manager.merge(cozinha);
		
	}
	
	@Transactional
	@Override
	public void remover(Long cozinhaId) {
		Cozinha cozinha = porId(cozinhaId);
		
		if (Objects.isNull(cozinha)){
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cozinha);
	}

}


