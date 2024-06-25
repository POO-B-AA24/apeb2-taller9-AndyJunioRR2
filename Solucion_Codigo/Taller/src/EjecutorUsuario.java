import java.util.ArrayList;
import java.util.List;


class Contacto {
    public String numMovil;
    public String nombre;

    public Contacto(String numeroMovil, String nombre) {
        this.numMovil = numeroMovil;
        this.nombre = nombre;
    }

    public String getNumeroMovil() {
        return numMovil;
    }

    public String getNombre() {
        return nombre;
    }
}

class Mensaje {
    public Contacto remitente;
    protected Contacto destinatario;

    public Mensaje(Contacto remitente, Contacto destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public void enviarMensaje(){
    }
    public void visualizarMensaje(){
    }
    
}

class SMS extends Mensaje {
    public String texto;

    public SMS(Contacto remitente, Contacto destinatario, String texto) {
        super(remitente, destinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando SMS de " + remitente.getNumeroMovil() + " a " + destinatario.getNumeroMovil() + ": " + texto);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("SMS de " + remitente.getNumeroMovil() + " a " + destinatario.getNumeroMovil() + ": " + texto);
    }
}

class MMS extends Mensaje {
public String nombreFicheroImagen;

    public MMS(Contacto remitente, Contacto destinatario, String nombreFicheroImagen) {
        super(remitente, destinatario);
        this.nombreFicheroImagen = nombreFicheroImagen;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando MMS de " + remitente.getNumeroMovil() + " a " + destinatario.getNumeroMovil() + ": " + nombreFicheroImagen);
    }

    @Override
    public void visualizarMensaje() {
        System.out.println("MMS de " + remitente.getNumeroMovil() + " a " + destinatario.getNumeroMovil() + ": " + nombreFicheroImagen);
    }
}

public class EjecutorUsuario {
    public static void main(String[] args) {
        Contacto contacto1 = new Contacto("0967856749", "Helton");
        Contacto contacto2 = new Contacto("0957483939", "Fabian");
        
        Mensaje mensajeTexto = new SMS(contacto1, contacto2, "\nQue tal Fabian, puedes jugar hoy noche?\n");
        Mensaje mensajeImagen = new MMS(contacto2, contacto1, "\nSintetica.jpg\n");
        
        mensajeTexto.enviarMensaje();
        mensajeTexto.visualizarMensaje();
        mensajeImagen.enviarMensaje();
        mensajeImagen.visualizarMensaje();
    }
}

