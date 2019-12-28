package br.edu.ifpb.mt.dac.yaggo.beans.mesa;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.yaggo.beans.AbstractBean;
import br.edu.ifpb.mt.dac.yaggo.beans.EnderecoPaginas;
import br.edu.ifpb.mt.dac.yaggo.entities.Mesa;
import br.edu.ifpb.mt.dac.yaggo.services.MesaService;
import br.edu.ifpb.mt.dac.yaggo.services.ServiceDacException;

@Named
@ViewScoped
public class MesaEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582989267229820432L;
	private Mesa mesa;
	@Inject
	private MesaService mesaService;

	public void init() {

		try {
			if (mesa == null) {
				mesa = new Mesa();
			} else {
				mesa = mesaService.getByID(mesa.getId());
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String saveMesa() {
		try {
			if (isEdicaoDeMesa()) {
				mesaService.update(mesa);
			} else {
				mesaService.save(mesa);
			}
		} catch (ServiceDacException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Mesa '" + mesa.getNumero() + "' saved");

		return EnderecoPaginas.PAGINA_PRINCIPAL_MESA;

	}

	public boolean isEdicaoDeMesa() {
		return mesa != null && mesa.getId() != null;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

//	public List<Mesa> getAllMesas() {
//		try {
//			List<Mesa> cat = mesaService.getAll();
//			if (isEdicaoDeMesa()) {
//				cat.remove(mesa);
//			}
//			return cat;
//		} catch (ServiceDacException e) {
//			throw new DacRuntimeException("Ocorreu algum problema ao tentar recuperar as mesas.", e);
//		}
//	}
}
