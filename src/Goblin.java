// CLASE GOBLIN QUE HEREDA DE PERSONAJE
public class Goblin extends Personaje {
    private int velocidad;
    private String habilidadEspecial;

    public Goblin(String nombre, int salud, int nivel, int energia, int velocidad, String habilidadEspecial) {
        super(nombre, salud, nivel, energia);
        this.velocidad = velocidad;
        this.habilidadEspecial = habilidadEspecial;
    }
}
