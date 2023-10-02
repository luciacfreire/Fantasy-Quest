//CLASE ELFO QUE HEREDA DE PERSONAJE
public class Elfo extends Personaje {
    private int agilidad;
    private String habilidadEspecial;

    public Elfo(String nombre, int salud, int nivel, int energia, String habilidadEspecial, int agilidad) {
        super(nombre, salud, nivel, energia);
        this.agilidad = agilidad;
        this.habilidadEspecial = habilidadEspecial;
    }
}
