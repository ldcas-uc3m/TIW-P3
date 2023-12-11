package es.uc3m.tiw.chatWS.domains;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import java.beans.ConstructorProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@SuppressWarnings("serial")
@Document(collection = "mensajes")
@JsonPropertyOrder({"emailori", "emaildest", "mensaje"})
public class Mensaje implements Serializable{

	@Id
	private String id;

	private String emailori;
    private String emaildest;
    private String mensaje;


	public Mensaje() {

	}

	@ConstructorProperties ({"emailori", "emaildest", "mensaje" })
	public Mensaje(String emailori, String emaildest, String mensaje) {
		this.emailori = emailori;
		this.emaildest = emaildest;
		this.mensaje = mensaje;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailori() {
		return emailori;
	}

	public void setEmailori(String emailori) {
		this.emailori = emailori;
	}

	public String getEmaildest() {
		return emaildest;
	}

	public void setEmaildest(String emaildest) {
		this.emaildest = emaildest;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		return "Mensaje [emailori=" + emailori + ", emaildest=" + emaildest + ", mensaje=" + mensaje + "]";
	}

}