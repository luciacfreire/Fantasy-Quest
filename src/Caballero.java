// CLASE CABALLERO QUE HEREDA DE PERSONAJE
public class Caballero extends Personaje {
    private int armadura;
    private String habilidadEspecial;

    public Caballero(String nombre, int salud, int nivel, int energia, int armadura, String habilidadEspecial) {
        super(nombre, salud, nivel, energia);
        this.armadura = armadura;
        this.habilidadEspecial = habilidadEspecial;
    }
}
