package main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SistemaFat Sistema = new SistemaFat(12);


		Sistema.formatear();
		Sistema.anadirArchivo("peli","C:",3);
		Sistema.anadirDirectorio("pelis", "C:");
		Sistema.anadirArchivo("otraPeli", "C:/pelis",2);
		Sistema.anadirDirectorio("mascosas", "C:");
		Sistema.anadirArchivo("otraPelimas", "C:/mascosas",2);
		Sistema.anadirDirectorio("mascosas2", "C:/mascosas");
		Sistema.anadirDirectorio("mascosas3", "C:/mascosas/mascosas2");
		Sistema.anadirArchivo("foto","C:/mascosas/mascosas2/mascosas3",1);
		
		
		
		
		//Sistema.mostrarFat();
		//Sistema.eliminarArchivo("C:/mascosas/mascosas2/mascosas3/foto");
		//Sistema.anadirArchivo("foto2","C:/mascosas/mascosas2/mascosas3",1);
		Sistema.eliminarDirectorio("C:/pelis");
		Sistema.mostrarDir("C:", 0);
		
	}

}
