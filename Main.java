import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int q, p, k;
		String instruction;
		
		int cont = -1;
		
		q = in.nextInt();
		p = in.nextInt();
		
		int aux;
		int i_cont = 0;
	
		Memory m = new Memory();
		Buffer b = new Buffer(q);
			
		//for(int j = 0; j < 9; ++j){
		while(in.hasNext()){
		
			instruction = in.next();
			k = in.nextInt();
			
			i_cont++;
			
			switch(instruction){
				case "RECV":
					for(int i = 0; i < k; ++i){
						cont++;
						if(!b.isFull()){
							b.add(cont);
						}else{
							m.push(cont);
						}
					}
					break;
					
				case "SEND":
					if(!b.isEmpty()){
						for(int i = 0; i < k; ++i){
							if(!b.isEmpty()){
								aux = b.remove();
								System.out.print(aux + " ");
							}
						}						
					}
					System.out.println();
					break;				
				default:
					break;	
			}
			
			
			if(i_cont == p){
				i_cont = 0;
				while(!b.isFull() && !m.isEmpty()){
					b.add(m.pop());
				}
			}
		}
		
		
		while(!b.isEmpty()){
			aux = b.remove();
			System.out.print(aux + " ");
		}
		
		System.out.println();
		
		while(!m.isEmpty()){
			aux = m.pop();
			System.out.print(aux + " ");
		}
		
		
		
		
		in.close();
		
	}
}


class Memory{
	
	int elements[];
	int topo;
	
	public Memory(){
		elements = new int[1500];
		topo = -1; //posicao invalida do vetor
	}
	
	public void push(int _value){
		if(!isFull()){
			topo ++;
			elements[topo] = _value;
		}else{
			throw new RuntimeException("Stack Overflow");
		}	
	}
	
	public int pop(){
		if(!isEmpty()){
			int e;
			e = elements[topo];
			topo--;
			return e;
		}else{
			throw new RuntimeException("It's alredy empty");
		}	
	}
	
	public boolean isEmpty(){
		return(topo == -1);
	}
	
	public boolean isFull(){
		return (topo== elements.length);
	}
	
	public int top(){
		if(!isEmpty()){
			return elements[topo];
		}else{
			throw new RuntimeException("It's empty");
		}	
	}
	
}


class Buffer{
	
	private int[] values;
	private int first;
	private int last;
	private int total;
	
	public Buffer(int _size){
		values = new int[_size];
		first = 0;
		last = 0;
		total = 0;
	}
	
	public void add(int _value){
		if(!isFull()){
			values[last] = _value;
			last = (last+1) % values.length;
			total++;
		}else{
			throw new RuntimeException("It's already full");
		}
	}
	
	public int remove(){
		if(!isEmpty()){
			int e = values[first];
			first = (first + 1)% values.length;
			total--;
			return e;
		}else{
			throw new RuntimeException("It's already empty");
		}
	}
			
	public boolean isEmpty(){
		return (total == 0);
	}
	
	public boolean isFull(){
		return (total == values.length);
	}

}