// CLASE GOBLIN QUE HEREDA DE ENEMIGO
public class Goblin extends Enemigo {
    private int velocidad;

    public Goblin(String nombre, int salud, int nivelDificultad, int velocidad) {
        super(nombre, salud, nivelDificultad);
        this.velocidad = velocidad;
    }
}
