//CLASE ELFO QUE HEREDA DE JUGADOR
public class Elfo extends Jugador {
    private int agilidad;

    public Elfo(String nombre, int salud, int nivel, String habilidadEspecial, int agilidad) {
        super(nombre, salud, nivel, habilidadEspecial);
        this.agilidad = agilidad;
    }
}
