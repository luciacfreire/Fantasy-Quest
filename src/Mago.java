// CLASE MAGO QUE HEREDA DE PERSONAJE
public class Mago extends Personaje {
    private int hechizos;
    private String habilidadEspecial;

    public Mago(String nombre, int salud, int nivel,int energia, int hechizos,String habilidadEspecial) {
        super();
        this.hechizos = hechizos;
        this.habilidadEspecial = habilidadEspecial;
    }

    public Mago() {

    }
}


