
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int contador = 0;
		
		int k = 0;
		int m = 0;
		int n = 0;
		int q = 0;
		
		int foundA = 0;
		int foundB = 0;
		int foundC = 0;
		int p = 0;
		
		int aux = 0;
		int cont = 0;
		
		//while(in.hasNext()){
			
			
			aux = 0;
			cont = 0;
			
			
		
			//----------------------------------
			
			k = in.nextInt();
			m = in.nextInt();
			n = in.nextInt();
			q = in.nextInt();
			
//			HashA ha = new HashA(m);
			HashB hb = new HashB(m);
//			HashC hc = new HashC(m);
			
			//quantidade de senhas a serem inseridas
			for(int i = 0; i < n; ++i){
				
				//recebe o valor à ser adicionado nos respectivos hashs
				aux = in.nextInt();
				
				//Adiciona nos hashs
//				ha.add(aux);
				hb.add(aux);
//				hc.add(aux);
				
			}
			
			hb.order();
			
			//quantidade de consultas a serem realizadas
			for(int i = 0; i < q; ++i){
				
				//recebe os valor à ser buscado
				aux = in.nextInt();
				
//				cont = ha.search(aux);
//				if(cont > 0){
//					foundA += cont;
//					cont = 0;
//				}else{
//					p++;
//				}
//				
				cont = hb.search(aux);
				if(cont > 0){
					foundB += cont;
					cont = 0;
				}
				
//				cont = hc.search(aux);
//				if(cont > 0){
//					foundC += cont;
//					cont = 0;
//				}


			}
			
			
			System.out.println("caso "+contador+": "+p+" "+foundA+" "+foundB+" "+foundC);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			contador++;
		//}
		
		
		
		
		

		in.close();
		
	}

}


class HashA{
	
	int size;
	int senhas;
	Integer[] elements;
	
	public HashA(int _size){
		
		this.size = _size;
		this.senhas = 0;
		this.elements = new Integer[_size];
		
	}
	
	public void add(int _senha){
		
		if(carga()){
			rehash();
		}
		
		elements[senhas] = _senha;
		
		this.senhas ++;
		
	}
	
	public boolean carga(){
		
		return (this.senhas/(this.size * 1.0)) >= 0.75;
		
	}
	
	
	public void rehash(){
		
		Integer[] aux = new Integer[2 * size];
		
		//realoca no vetor auxiliar os valores armazenados
		for(int i = 0; i < senhas; ++i){
			
			aux[i] = elements[i];
		
		}
		
		elements = aux;
		this.size *= 2;
		
	}
	
	/**
	 * @param _search
	 * @return
	 */
	public int search(int _search){
		
		int indice = 0;
		boolean found = false;
		
		
		//procura o indice do elemento buscado
		while(indice < size && !found && elements[indice] != null && elements[indice] != _search){
			found = true;
			indice++;
		}
		
		//caso encontrado no array, retorna o numero de comparações
		if(found){
			return indice + 1;
		}else{
			return 0;
		}
	
	}
	
}


class HashB{
	
	int size;
	int senhas;
	Integer[] elements;
	
	int cont;
	
	public HashB(int _size){
		
		this.size = _size;
		this.senhas = 0;
		this.elements = new Integer[_size];
		
		cont = 0;
		
	}
	
	public void add(int _senha){
		
		if(carga()){
			rehash();
		}
		
		elements[senhas] = _senha;
		
		this.senhas ++;
		
	}
	
	public boolean carga(){
		
		return (this.senhas/(this.size * 1.0)) >= 0.75;
		
	}
	
	
	public void order(){
		
		Arrays.sort(elements);
	
	}
	
	public void rehash(){
		
		Integer[] aux = new Integer[2 * size];
		
		//realoca no vetor auxiliar os valores armazenados
		for(int i = 0; i < senhas; ++i){
			
			aux[i] = elements[i];
		
		}
		
		elements = aux;
		this.size *= 2;
		
	}
	
	/**
	 * @param _search
	 * @return
	 */
	public int search(int _search){
		
		int comparison  = 0;
		boolean found = false;
		
		
		if(Arrays.asList(elements).contains(_search)){
			found = true;
			comparison = cont;
		}
		
		
		//caso encontrado no array, retorna o numero de comparações
		if(found){
			return comparison;
		}else{
			return 0;
		}
	
	}
	
	
	// Returns index of x if it is present in arr[l..r], else
    // return -1
    public int binarySearch(int arr[], int l, int r, int x){
    	
    	cont ++;
    	
        if (r>=l){
            int mid = l + (r - l)/2;
 
            // If the element is present at the middle itself
            if (arr[mid] == x)
               return mid;
 
            // If element is smaller than mid, then it can only
            // be present in left subarray
            if (arr[mid] > x)
               return binarySearch(arr, l, mid-1, x);
 
            // Else the element can only be present in right
            // subarray
            return binarySearch(arr, mid+1, r, x);
        }
 
        // We reach here when element is not present in array
        return -1;
    }
	
}



class HashC{
	
	int size;
	int senhas;
	Integer[] elements;
	
	
	
	public HashC(int _size){
		
		this.size = _size;
		this.senhas = 0;
		this.elements = new Integer[_size];	
		
	}
	
	public int h0(int _key, int _size){
		
		return _key%2;
		
	}
	
	public int h1(int _key, int _size){
		
		return  (2 * (_key % (this.size / 2))) + 1;
		
	}
	
	
	public void add(int _senha){
		
		int index = 0;
		
		//Só faz o rehash caso tenha dado a carga máxima
		if(carga()){
			rehash();
		}
		
		//chama o hash para determinar a posição no array
		index = h0(_senha, size);
		//caso haja choque, é feito o rehash até que não haja
		while(elements[index] != null){
			index += h1(_senha, size);
			
		}
		//adiciona o item na posição correta do array
		System.out.println(index);
		elements[index] = _senha;
		this.senhas++;
		
	}
	
	
	public boolean carga(){
		
		return (this.senhas/(this.size * 1.0)) >= 0.75;
		
	}
	
	
	public void rehash(){
		
		int index = 0;
		Integer[] aux = new Integer[this.size * 2];
		
		//percorre todo o array
		for(int i = 0; i < this.size; ++i){
			//só verifica caso o valor não seja nulo
			if(elements[i] != null){
				//chama o hash para determinar a posição no no array
				index = h0(elements[i], size * 2);
				//caso haja choque, é feito o rehash até que não haja
				while(aux[index] != null){
					index += h1(elements[i], size * 2);
				}
				//adiciona o item na posição correta do novo array
				aux[index] = elements[i];
				
			}
			
		}
		
		elements = aux;
		this.size *= 2;

	}
	
	public int search(int _senha){
		
		int index = 0;
		int comparison = 0;
		boolean found = false;
		
		index = h0(_senha, size);
		
		//vai adicionando o indice, procurando até chegar em algum null ou encontrar o valor
		//Se chegar em null, o elemento não está no hash
		while(index < size && elements[index] != null && !found){
			comparison++;
			if(elements[index] == _senha){
				found = true;
			}
			index += h1(_senha, size);
		}
		
		
		//Só retorna as comparações caso tenha encontrado o valor
		if(found){
			return comparison;
		}else{
			return 0;
		}
		
	}
	
	
	
}
