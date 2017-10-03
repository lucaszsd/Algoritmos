import java.util.ArrayList;
import java.util.Scanner;

public class HuxleyCode {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int id = 0;
		int priority = 0;
		char state = ' ';
		
		queue list = new queue();
		String instruction = in.next();
		cpu[] CPU = new cpu[N];
		int[][] stats = new int[10][3];
		int[] CPU_stats = new int[N];

		
		//Cria as CPU's
		for(int i = 0; i < N; ++i){
			CPU[i] = new cpu();
		}
		
		
		
		while(!instruction.equals("END")){
			
			if(instruction.equals("NEW")){
				id = in.nextInt();
				priority = in.nextInt();
				
				System.out.println(instruction + " " + id +" " + priority);
				
				list.add(new process(id, priority));
				
				stats[priority][0] ++;
				
			}else if(instruction.equals("STA")){
				id = in.nextInt();
				state = in.next().charAt(0);
			
				System.out.println(instruction + " " + id +" " + state);
				
				if(state == 'A'){
					if(CPU[id].process != null){
						CPU[id].queue();
						list.add(CPU[id].process);
						CPU[id].cleaner();
					}
				}else if(state == 'O'){
					CPU[id].cleaner();
				}
				
			}
			
			
			
			
			for(int i = 0; i < N; ++i){
		
				if(!CPU[i].active){
					process aux = list.search();
					if(aux!= null){
						CPU[i].run(aux);
						list.cleaner();
						
						// total gasto em processos cujas prioridades originais eram o_priority
						stats[aux.o_priority][1] = 20 - aux.priority;
						// total de rodadas de execução para processos cujas prioridades originais eram o_priority
						stats[aux.o_priority][2] ++;
						// tempo total de processamento realizado pela CPU
						CPU_stats[i] = 20 - aux.priority;
					}
					break;
				}
				
			}
			
			
			
			
			//Se for END para o escalonador
			instruction = in.next();
		}
		
		System.out.println("STATS BY PRIORITY/n");
		
		for(int i = 0; i < 10; ++i){
			System.out.println(i + ": "+ stats[0] + " " + stats[1] + " " + stats[2]);
			
		}
		
		System.out.println("\nSTATS BY CPU\n");
		
		for(int i = 0; i < N; ++i){
			System.out.println(i + ": "+ CPU_stats[i]);
		}
		
		
		
		list.print();
		
		
		in.close();
	}

}

class process{
	
	int id;
	int priority;
	int o_priority;
	process next;
	
	public process(int _id, int _priority){
		this.id = _id;
		this.o_priority = _priority;
		this.priority = o_priority;
		this.next = null;
	}
	
	
	public void add(process _process){
		if(this.next != null){
			if(this.next.id < _process.id){
				this.next.add(_process);
			}else{
				_process.next = this.next;
				this.next = _process;
			}
		}else{
			this.next = _process;
		}
	}
	
	
	public void print(){
		if(this.next != null){
			System.out.print("  ->  ");
			System.out.print(" | " + this.next.id + " " + this.next.priority);
			this.next.print();
		}
	}
	
	
}

class cpu{
	
	boolean active;
	process process;
	
	public cpu(){
		this.active = false;
		this.process = null;
	}
	
	public void run(process _process){
		this.process = _process;
		this.active = true;
	}
	
	public process queue(){
		if(this.process != null){
			if(this.process.priority < 9){
				this.process.priority ++;
			}
		}
		
		return this.process;
	}
	
	public void cleaner(){
		this.process = null;
		this.active = false;
	}
	
}

class queue{
	
	process[] elements = new process[10];
	
	
	public void add(process _process){
		if(elements[_process.priority] == null){
			elements[_process.priority] = _process;
		}else{
			if(_process.next.id < elements[_process.priority].id){
				_process.next = elements[_process.priority];
				elements[_process.priority] = _process.next;
			}else{
				elements[_process.priority].next.add(_process);
			}
		}
	}
	
	

	public process search(){		
		process _process = null;
		for(int i = 0; i < 10; ++i){
			if(elements[i] != null){
				_process = elements[i];
				break;
			}
		}
		return _process;
	}
	
	public void cleaner(){
		for(int i = 0; i < 10; ++i){
			if(elements[i] != null){
				if(elements[i].next == null){
					elements[i] = null;
				}else{
					elements[i] = elements[i].next;
				}
				break;
			}
		}
	}
	
	public void print(){
		
		for(int i = 0; i < 10; ++i){
			
			if(elements[i] != null){
				System.out.println(" " + i + " || " +elements[i].id + " " + elements[i].priority);
				
				if(elements[i].next != null){
					elements[i].next.print();
				}
				
			}else{
				System.out.println(" " + i + "  - - -  ");
			}
			
			
			
		}
	}
	
	
}

