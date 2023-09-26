// CLASE MAGO QUE HEREDA DE JUGADOR
public class Mago extends Jugador {
    private int mana;

    public Mago(String nombre, int salud, int nivel, String habilidadEspecial, int mana) {
        super(nombre, salud, nivel, habilidadEspecial);
        this.mana = mana;
    }
}


