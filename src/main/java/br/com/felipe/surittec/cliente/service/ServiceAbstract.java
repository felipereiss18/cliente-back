package br.com.felipe.surittec.cliente.service;

import br.com.felipe.surittec.cliente.entity.AbstractEntity;
import br.com.felipe.surittec.cliente.exception.ClienteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@SuppressWarnings("unchecked")
public abstract class ServiceAbstract<T extends AbstractEntity, R extends JpaRepository> {

	protected static final Logger LOG = LoggerFactory.getLogger(ServiceAbstract.class);

	R repository;

	@Transactional (rollbackFor = ClienteException.class)
	public Page<T> listar(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional (rollbackFor = ClienteException.class)
	public List<T> listar() {
		return repository.findAll();
	}

	@Transactional (rollbackFor = ClienteException.class)
	public T buscarPorId(Object id) throws ClienteException {

		Optional<T> optional = repository.findById(id);

		return optional
				.orElseThrow(() -> new ClienteException("erro.naoencontrada:Informação", HttpStatus.NOT_FOUND));
	}

	@Transactional (rollbackFor = ClienteException.class)
	public T salvar(T t) {
		try {
			return (T) repository.save(t);
		} catch (DataIntegrityViolationException e){
			if (e.getMostSpecificCause().getMessage().contains("duplicate key")
					|| e.getMostSpecificCause().getMessage().contains("primary key")) {
				LOG.error("Erro ao salvar - ", e);
				throw new ClienteException("erro.duplicado");
			}

			LOG.error("Erro ao salvar - ", e);
			throw new ClienteException("erro.violacao");
		} catch (Exception e) {
			LOG.error("Erro ao salvar - ", e);
			throw new ClienteException("erro.persistir");
		}
	}

	@Transactional (rollbackFor = ClienteException.class)
	public void excluir(Object id) {
		try {
			repository.deleteById(id);
		} catch (UnexpectedRollbackException ue) {
			if (ue.getMostSpecificCause().getMessage().contains("conflicted with the REFERENCE")) {
				LOG.error("Erro ao excluir - ", ue);
				throw new ClienteException("erro.violacao");
			}
		} catch (Exception ex) {
			LOG.error("Erro ao excluir - ", ex);
			throw new ClienteException("erro.excluir");
		}
	}

	@Transactional (rollbackFor = ClienteException.class)
	public T editar(T t){

		this.buscarPorId(t.getId());

		return this.salvar(t);
	}


}
