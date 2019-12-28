package br.edu.ifpb.mt.dac.yaggo.beans.dataGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import br.edu.ifpb.mt.dac.yaggo.DacRuntimeException;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;
import br.edu.ifpb.mt.dac.yaggo.services.dataGenerator.FuncionarioDataGeneratorService;

@Named
@Eager // Thanks, Omnifaces!!!
@ApplicationScoped
public class InitDatabaseIfNeeded {

	@Inject
	private FuncionarioDataGeneratorService funcionarioDataGeneratorService;

	@PostConstruct
	public void postConstruct() {

		try {
			funcionarioDataGeneratorService.generateData();
		} catch (ServiceDacException e) {
			throw new DacRuntimeException("Ocorreu algum erro ao tentar inicializar a base de dados.", e);
		}
	}

}
