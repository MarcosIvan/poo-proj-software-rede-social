import java.util.Scanner;
import java.util.ArrayList;


public class iFace {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Conta> c = new ArrayList<Conta>();
		
		while(true) {
			System.out.printf("Indique o que você deseja fazer: \n");
			System.out.printf("1- Criar conta\n");
			System.out.printf("2- Editar conta\n");
			System.out.printf("8- Remover conta\n");
			System.out.printf("11- Sair\n");
			int n = input.nextInt();
			
		
			if(n==1) {
				System.out.printf("Login: ");
				String login1 = input.next();
				input.nextLine();
			
				System.out.printf("Nome: ");
				String nome1 = input.next();
				input.nextLine();
			
				System.out.printf("Senha: ");
				String senha1 = input.next();
				input.nextLine();
				
				c.add(new Conta(nome1, login1, senha1));
			}
		
			if(n==2) {
				System.out.printf("Qual login da conta que voce deseja editar: ");
				String aux1 = input.next();
				
				if(c.indexOf(aux1) != -1) {
					System.out.printf("Novo login: ");
					String login1 = input.next();
					input.nextLine();
				
					System.out.printf("Novo nome: ");
					String nome1 = input.next();
					input.nextLine();
				
					System.out.printf("Nova senha: ");
					String senha1 = input.next();
					input.nextLine();
					
					c.set(c.indexOf(aux1), new Conta(nome1, login1, senha1));
				}
				else {
					System.out.printf("Login nao encontrado\n");
				}
			}
			if(n==8) {
				System.out.printf("Qual login da conta que voce deseja remover: ");
				String aux1 = input.next();
				
				if(c.indexOf(aux1) != -1) {
					c.remove(c.indexOf(aux1));
				}
				else {
					System.out.printf("Login nao encontrado\n");
				}
			}
			if(n==11) {
				break;
			}
		}
	}
}
