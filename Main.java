import java.util.Scanner;

public class Main {
	
	public int[] send(int[] _buffer, int _k){
		
		
		if(_k > _buffer.length){
			
			for(int i = 0; i < _buffer.length; ++i){
				System.out.print(_buffer[i] + " ");
			}
			
		}else{
			
			for(int i = 0; i < _k; ++i){
				System.out.print(_buffer[i] + " ");
			}
			
			for(int i = _k; i < _buffer.length; ++i){
				_buffer[i - _k] = _buffer[i];
				_buffer[i] = -1;
			}
			
		}
			

		return _buffer;
	}
	
	
	public void recv(int[] _buffer, int _bufferCont, int[] memory, int[] memoryCont){
		
		
	}
	
	
	
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		
		int q = 0;
		int p = 0;
		int k = 0;
	
		String instructions = "";
		
		q = in.nextInt();
		p = in.nextInt();
		
		//Pilha P = new Pilha();
		
		
		
		
		while(in.hasNext()){
			
			
			instructions = in.next().toUpperCase();
			k = in.nextInt();
			
			
			switch (instructions) {
				case "RECV":
					
					break;
				case "SEND":
				
					break;
				default:
					break;
			}
			
			
			
		}
		
		
		
		
		in.close();
		
	}
	
	
	public class buffer{
		
		private int pack;
		private buffer next;
		
		buffer(){
			pack = -1;
			this.next = null;
		}
		
		public void setPack(int _pack){
			this.pack = _pack;
		}
		
		public int getPack(){
			return this.pack;
		}
		
		public void setNext(int _pack){
			if(this.next == null){
				this.next = new buffer();
				this.next.setPack(_pack);
			}else{
				this.next.setNext(_pack);
			}
		}
		
		public buffer getNext(){
			return this.next;
		}
	}
	
	
	public class memory{
		
		public int ponteiro;
		public int value;
		
		public memory(){
			this.ponteiro = -1;
			this.value = -1;
		}
		
		public boolean isEmpty(){
			if(this.ponteiro == -1){
				return true;
			}else{
				return false;
			}
		}
		
		
		public int size(){
			if(this.isEmpty()){
				return 0;
			}else{
				return this.ponteiro + 1;
			}
		}
		
		public int top(){
			return this.value;
		}
		
		public void pop(){
			  
			
			
		}
		
		
		
		
	}
	
	
	public class Pilha{
		
		int elements[];
		int topo;
		
		public Pilha(){
			elements = new int[10];
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
			return (topo== 9);
		}
		
		
		public int top(){
			if(!isEmpty()){
				return elements[topo];
			}else{
				throw new RuntimeException("It's empty");
			}
			
		}
		
	}
	
	
	public class Fila{
		
		private int[] values;
		private int first;
		private int last;
		private int total;
		
		public Fila(){
			values = new int[10];
			first = 0;
			last = 0;
			total = 0;
		}
		
		
		public void add(int _value){
			if(!isFull()){
				values[last] = _value;
				last = last++%values.length;
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
	
	

}
