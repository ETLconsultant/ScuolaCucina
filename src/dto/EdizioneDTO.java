package dto;

import java.util.ArrayList;
import java.util.List;
import entity.Edizione;
import entity.Feedback;
import entity.Utente;

public class EdizioneDTO {
	
	private Edizione edizione;
	private ArrayList<Feedback> feedbacks;
	private ArrayList<Utente> utentiIscritti;
	
	public EdizioneDTO(){}
	
	public EdizioneDTO(Edizione edizione, ArrayList<Feedback> feedbacks, ArrayList<Utente> utenti) {
	
		this.edizione = edizione;
		this.feedbacks = feedbacks;
		this.utentiIscritti = utenti;
	}

	public ArrayList<Utente> getUtentiIscritti() {
		return utentiIscritti;
	}

	public void setUtentiIscritti(ArrayList<Utente> utentiIscritti) {
		this.utentiIscritti = utentiIscritti;
	}

	public Edizione getEdizione() {
		return edizione;
	}

	public void setEdizione(Edizione edizione) {
		this.edizione = edizione;
	}

	public ArrayList<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(ArrayList<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "EdizioneDTO [edizione=" + edizione + ", feedbacks=" + feedbacks + ", utentiIscritti=" + utentiIscritti
				+ "]";
	}
}
