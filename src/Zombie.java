// CLASE ZOMBIE QUE HEREDA DE ENEMIGO
public class Zombie extends Enemigo {
    private int fuerza;

    public Zombie(String nombre, int salud, int nivelDificultad, int fuerza) {
        super(nombre, salud, nivelDificultad);
        this.fuerza = fuerza;
    }
}
