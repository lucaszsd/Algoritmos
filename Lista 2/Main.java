import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
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
		int penetras = 0;
		
		int aux = 0;
		int cont = 0;
		
		while(in.hasNext()){
			
			
			aux = 0;
			cont = 0;
			
			
		
			//----------------------------------
			
			k = in.nextInt();
			m = in.nextInt();
			n = in.nextInt();
			q = in.nextInt();
			
			HashA ha = new HashA(m);
			HashB hb = new HashB(m);
			HashC hc = new HashC(m);
			
			//quantidade de senhas a serem inseridas
			for(int i = 0; i < n; ++i){
				
				//recebe o valor à ser adicionado nos respectivos hashs
				aux = in.nextInt();
				
				//Adiciona nos hashs
				ha.add(aux);
				foundB += hb.add(aux);
				hc.add(aux);
				
			}
			
			//hb.order();
			
			//quantidade de consultas a serem realizadas
			for(int i = 0; i < q; ++i){
				
				//recebe os valor à ser buscado
				aux = in.nextInt();
				
				cont = ha.search(aux);
				if(cont > 0){
					foundA += cont;
					cont = 0;
				}else{
					penetras++;
				}
				
				cont = hb.search(aux);
				if(cont > 0){
					foundB += cont;
					cont = 0;
				}
				
				cont = hc.search(aux);
				if(cont > 0){
					foundC += cont;
					cont = 0;
				}


			}
			
			
			System.out.println("caso "+contador+": "+penetras+" "+foundA+" "+foundB+" "+foundC);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			contador++;
		}
		
		
		
		
		

		in.close();
		
	}

}

class HashA{
	
	double size;
	ArrayList<Integer> elements = new ArrayList<>();
	int senhas;
	
	public HashA(int _size){
		this.size = _size;
		senhas = 0;
	}
	
	public void add(int _senha){
		this.elements.add(_senha);
		this.senhas++;
	}
	
	public boolean carga(){
		return this.senhas/this.size >= 0.75;
	}
	
	public void rehash(){
		if(carga()){
			this.size *= 2;
		}
	}
	
	public int search(int _search){
		
		boolean found = false;
		int comparison = 0;
		int index = 0;
		
		while(index < this.senhas && !found){
			
			if(elements.get(index) == _search){
				comparison = index + 1;
				found = true;
			}
			
			index++;
			
		}
		
		return comparison;
		
	}
}

class HashB{
	
	int size;
	ArrayList<Integer> elements = new ArrayList<>();
	int senhas;
	
	public HashB(int _size){
		this.size = _size;
		senhas = 0;
	}
	
	public int add(int _senha){
	
		this.elements.add(_senha); 
		this.senhas++;
		
		this.order();
	
		return elements.indexOf(_senha);
	}
	
	public boolean carga(){
		return this.senhas/this.size >= 0.75;
	}
	
	public void order(){
		Collections.sort(this.elements);
	}
	
	public int search(int _search){
		boolean found = false;
		int comparison = 0;
		int index = 0;
		
		while(index < this.senhas && !found){
			
			if(elements.get(index) == _search){
				comparison = index + 1;
				found = true;
			}
			
			index++;
			
		}
		
		return comparison;
		
	}
}


class HashC{
	
	double size;
	Integer[][] elements;
	int senhas;
	
	public HashC(int _size){
		this.size = _size;
		senhas = 0;
		elements = new Integer[_size][_size];
	}
	
	public void add(
			int _senha){
		if(carga()){
			rehash();
		}
		
		if(this.elements[h0(_senha, (int)this.size)][0] == null){
			this.elements[h0(_senha, (int)this.size)][0] = _senha;
		}else{
			this.elements[h0(_senha, (int)this.size)][h1(_senha, (int) this.size)] = _senha;	
		}
		
		this.senhas++;
		
	}
	
	public int h0(int _senha, int _size){
		return (int) (_senha % this.size);
	}
	
	public int h1(int _senha, int _size){
		return ((2 * (_senha % (_size / 2))) + 1);
	}
	
	public boolean carga(){
		double carga = this.senhas/this.size; 
		//System.out.println(carga);
		return carga >= 0.75;
	}
	
	public void rehash(){
		
		Integer[][] aux = new Integer[(int) this.size * 2][(int) this.size * 2];
		
		for(int i = 0 ; i < this.size; ++i){
			for(int j = 0; j < this.size; ++j){
				
				if(elements[i][j] != null){
					if(aux[h0(elements[i][j], (int) this.size * 2)][0] == null){
						aux[h0(elements[i][j], (int) this.size * 2)][0] = elements[i][j];
					}else{
						aux[h0(elements[i][j], (int) this.size * 2)][h1(elements[i][j], (int) this.size * 2)] = elements[i][j];						
					}
				}
				
			}
		}
		
		this.elements = aux;
		this.size *= 2;
		
	}
	
	public int search(int _search){
		
		boolean found = false;
		int comparison = 0;
		
		for(int i = 0 ; i < this.size; ++i){
			for(int j = 0; j < this.size; ++j){
				
				if(this.elements[i][j] != null && !found){
					comparison++;
					if(this.elements[i][j] == _search){
						found = true;
					}
				}
				
			}
		}
		
		if(found){
			return comparison;
		}else{
			return 0;
		}
		
		
	}

}


