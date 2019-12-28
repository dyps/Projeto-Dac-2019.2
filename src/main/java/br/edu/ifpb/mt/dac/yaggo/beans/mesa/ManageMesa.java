package br.edu.ifpb.mt.dac.yaggo.beans.mesa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.filters.MesaFilter;
import br.edu.ifpb.mt.dac.yaggo.services.MesaService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class ManageMesa extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MesaFilter mesaFilter;

	@Inject
	private MesaService mesaService;

	private List<Mesa> mesas;

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}
	public String delete(Mesa mesa) {
		try {
			mesaService.delete(mesa);
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Mesa id='" + mesa.getId() + "' deleted");
		
		return EnderecoPaginas.PAGINA_PRINCIPAL_MESA;

	}

	public String filtrar() {
		try {
			mesas = mesaService.findBy(getMesaFilter());
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.mesaFilter = new MesaFilter();
		return null;
	}

	public MesaFilter getMesaFilter() {
		return mesaFilter;
	}

	public void setMesaFilter(MesaFilter mesaFilter) {
		this.mesaFilter = mesaFilter;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	
}
