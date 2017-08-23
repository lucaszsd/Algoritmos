import java.lang.invoke.SwitchPoint;
import java.util.Scanner;


public class Main {
	
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int q, p, k;
		String instruction;
		
		int cont = 0;
		int aux = 0;
		int i_cont = 0;
		
		q = in.nextInt();
		p = in.nextInt();
	
		Memory m = new Memory();
		Buffer b = new Buffer(q);
		
		for(int i = 0; i < 32; ++i){
			
			instruction = in.next();
			k = in.nextInt();
			
				
			switch(instruction){
			
				case "RECV":
					for(int j = 0; j < k; ++j){	
						if(!b.isEmpty()){
							b.add(cont);
						}else{
							m.push(cont);
						}
						cont ++;
					}
					break;
					
					
				case "SEND":
					if(!b.isEmpty()){
						if(k >= q){
							while(!b.isEmpty()){
								aux = b.remove();
								System.out.print(aux +" ");
							}
						}else{
							for(int j = 0; j < k; ++j){
								aux = b.remove();
								System.out.print(aux +" ");
							}
						}
					}else{
						System.out.println();
					}
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
		
		
		
		
		
		
		
		in.close();
		
	}
	
	
}

class Memory{
	
	int elements[];
	int topo;
	
	public Memory(){
		elements = new int[500];
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