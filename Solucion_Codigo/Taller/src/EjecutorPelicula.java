import java.util.ArrayList;
import java.util.Arrays;

public class EjecutorPelicula {
    public static void main(String[] args) {
        DVDs soporteDvd = new DVDs(4.5);
        VHSs soporteVhs = new VHSs("Estandar");
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>(Arrays.asList(
            new Pelicula(soporteDvd, "Intensamente", "Ricardo", "Español", "19 de Mayo"),
            new Pelicula(soporteVhs, "Game of Thrones", "Daniel", "Español", "19 de Mayo"),
            new Pelicula(soporteDvd, "Matrix", "Ricardo", "Español", "19 de Mayo")
        ));

        for (Pelicula p1 : listaPeliculas) {
            p1.calcularCostoAlquiler();
            System.out.println(p1);
        }
    }
}

class Pelicula {
    public Soporte soporte;
    public String titulo;
    public String autor;
    public String idioma;
    public String añoEdicion;
    public double costoAlquiler;
    

    public Pelicula(Soporte soporte, String titulo, String autor, String idioma, String añoEdicion) {
        this.soporte = soporte;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.añoEdicion = añoEdicion;
        this.costoAlquiler = 0.0;
    }

    public void calcularCostoAlquiler() {
        this.costoAlquiler = soporte.calcularCostoAlquiler();
    }

    @Override
    public String toString() {
        return "Pelicula{" + "soporte=" + soporte + ", titulo=" + titulo + ", autor=" + autor + ", idioma=" + idioma + ", añoEdicion=" + añoEdicion + ", costoAlquiler=" + costoAlquiler + '}';
    }
}

abstract class Soporte {
    public abstract double calcularCostoAlquiler();
}

class DVDs extends Soporte {
    public double capacidadGB;

    public DVDs(double capacidadGB) {
        this.capacidadGB = capacidadGB;
    }

    @Override
    public double calcularCostoAlquiler() {
        return 3.0; 
    }

    @Override
    public String toString() {
        return "DVDs{" + "capacidadGB=" + capacidadGB + '}';
    }
}

class VHSs extends Soporte {
    public String tipoCintaMagnetica;

    public VHSs(String tipoCintaMagnetica) {
        this.tipoCintaMagnetica = tipoCintaMagnetica;
    }

    @Override
    public double calcularCostoAlquiler() {
        return 2.0; 
    }

    @Override
    public String toString() {
        return "VHSs{" + "tipoCintaMagnetica=" + tipoCintaMagnetica + '}';
    }
}