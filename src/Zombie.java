// CLASE ZOMBIE QUE HEREDA DE PERSONAJE
public class Zombie extends Personaje {
    private int fuerza;
    private String habilidadEspecial;


    public Zombie(String nombre, int salud, int nivel, int energia, int fuerza,String habilidadEpecial) {
        super(nombre, salud, nivel,energia);
        this.fuerza = fuerza;
        this.habilidadEspecial = habilidadEspecial;
    }
}
