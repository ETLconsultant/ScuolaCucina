package dto;

import java.util.ArrayList;
import java.util.List;

import entity.Corso;

public class CorsoDTO {
	private Corso corso;
	private ArrayList<EdizioneDTO> listaEdizioni;
	
	public CorsoDTO(){}
	
	public CorsoDTO(Corso corso, ArrayList<EdizioneDTO> listaEdizioni) {
		this.corso = corso;
		this.listaEdizioni = listaEdizioni;
	}

	public Corso getCorso() {
		return corso;
	}

	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	public ArrayList<EdizioneDTO> getListaEdizioni() {
		return listaEdizioni;
	}

	public void setListaEdizioni(ArrayList<EdizioneDTO> listaEdizioni) {
		this.listaEdizioni = listaEdizioni;
	}

	@Override
	public String toString() {
		return "CorsoDTO [corso=" + corso + ", listaEdizioni=" + listaEdizioni + "]";
	}

}
