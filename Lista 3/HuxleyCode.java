import java.util.ArrayList;

public class HuxleyCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



class directory{
	
	char name;
	directory parent;
	ArrayList<Object> bst = new ArrayList();
	
	public directory(char _name,directory _parent){
		this.name = _name;
		this.parent = _parent;
	}
	
	public void add(Object _append){
		bst.add(_append);
	}

}


class file{
	
	char name;
	file left;
	file right;
	
	
	public file(char _name){
		if((int)_name <= (int)this.name){
			if(this.left = null){
				this.left = new file(_name)
			}else{
				this.left.add(_name);
			}
		}else{
			if(this.right = null){
				this.right = new file(_name)
			}else{
				this.right.add(_name);
			}
		
		}
		
	
	
	public void add(String name){
		
	}
	
}

class filesystem{
	
	directory actualdirectory;
	directory root;
	
	public filesystem() {
		//root serve como referencia para a raiz do sistema de arquivos
		root = new directory('/', null);
		this.actualdirectory = root;
	}
	
	
	public void instruction(String _inst){
		String inst = _inst.split(" ")[0];
		String target = "";
		
		switch(inst){
		
			//entra no diretorio
			case "cd":
				target = _inst.split(" ")[1];
				break;
			//lista elementos do diretório
			case "ls":
				break;
			//cria novo arquivo no diretorio atual
			case "touch":
				target = _inst.split(" ")[1];
				break;
			//cria diretório
			case "mkdir":
				target = _inst.split(" ")[1];
				break;
			//imprime nome do diretorio
			case "pwd":
				System.out.println(this.actualdirectory.name);
				break;
			//imprime a cadeia de diretorios conforme pedido
			case "tree":
				target = _inst.split(" ")[1];
				break;
			//deleta arquivo ou diretorio
			case "rm":
				target = _inst.split(" ")[1];
				break;
			default:
				break;
			
		}
		
		
		
	}
	
}
