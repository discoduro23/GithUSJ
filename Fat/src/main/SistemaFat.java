package main;

public class SistemaFat {
	Cluster[] Cluster;
	FAT Fat;
	Directorio DirRaiz;
	
	
	public SistemaFat(int capacidad) {
		Cluster = new Cluster[capacidad];
		Fat = new FAT(capacidad);
		DirRaiz = new Directorio();
		formatear();
	}
	
	
	
	public void formatear() {
		
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			Entrada.Disponible = true;
		}
		DirRaiz.ListaEntradasDirectorios.clear();
	}
	
	
	
	public void mostrar() {
		System.out.println("Sistema Fat: ");
		System.out.println("Fat: ");
		int i = 0, j = 0;
		for (Entrada_Fat Entrada: Fat.ListaEntradasFat) {
			System.out.println("\tEntrada_" + i + ": ");
			i++;
			System.out.println("\t\tDisponible = " + Entrada.Disponible + " | Siguiente = "  + Entrada.siguiente + " | Fin = " + Entrada.Fin);
		}
		System.out.println("Directorio Raiz: ");
		for (Entrada_Directorio Entrada: DirRaiz.ListaEntradasDirectorios) {
			System.out.println("Posicion_" + j + ": ");
			j++;
			String aux;
			if (Entrada.esDir) aux = "Directorio";
			else aux = "Archivo";
			System.out.println("Nombre: " + Entrada.nombre + " | Tipo: " + aux + " | Cluster Inicio: " + Entrada.ClusterInicio);
		}
	}
	
	
	
	public void anadirArchivo(String nombre, String ruta, int tamArchivo) {
		int[] listaClusters  = buscarClustersVacios(tamArchivo);
		for (int i = 0; i< listaClusters.length; i++) {
			System.out.println(listaClusters[i]);
		}
		actualizarFatArchivos(listaClusters);
		//Hacer desde aqui /!\ 
		introducirEnCluster(listaClusters, nombre);
		Entrada_Directorio aux = new Entrada_Directorio(tamArchivo);
		Parte_de_Archivo au2 = new Parte_de_Archivo(nombre);
		aux.nombre = au2.dato;
		DirRaiz.ListaEntradasDirectorios.add(aux);

	}
	
	
	
	public int[] buscarClustersVacios(int tam){
		int j = 0;
		
		if(tam <= 0) System.out.println("Estas buscando 0 o menos clusters vacios");
		
		int[] resultado = new int[tam];		
		for(int i = 0; j < tam && i < Fat.ListaEntradasFat.size(); i++) {
			if(Fat.ListaEntradasFat.get(i).Disponible) {
				resultado[j] = i;
				j++;
			}
		}
		return resultado;
	}
	
	
	public void actualizarFatArchivos(int[] clustersArchivos) {
		for(int i = 0; i < clustersArchivos.length; i++) {
			Entrada_Fat aux = Fat.ListaEntradasFat.get(clustersArchivos[i]);
			System.out.println("aaaaa" + aux.Disponible);
			aux.Disponible = false;
			
			if(i == clustersArchivos.length-1) aux.Fin = true;
			else aux.siguiente = clustersArchivos[i + 1];

			System.out.println(clustersArchivos[i]);
		}
	}
	
	
	public void introducirEnCluster(int[] idClusDis, String nombre) {
		for(int i=0; i<idClusDis.length; i++) {
			Cluster[idClusDis[i]] = new Parte_de_Archivo(nombre + " " + i);
		}
	}
	

	public String buscarDir(String ruta) {
		String rutaEncontrada = " ";
		System.out.println("entrada");

		for (Entrada_Directorio Entrada: DirRaiz.ListaEntradasDirectorios) {
			if (Entrada.nombre == ruta) {
				return Entrada.nombre;
			}
		}
		return rutaEncontrada;
	}
	
	
}

