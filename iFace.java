import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;


public class iFace {
	public static int findConta(ArrayList<Conta> l, String login) {
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).login.equals(login)) {
				return i;
			}
		}
		System.out.printf("Login nao encontrado\n");
		return -1;
	}
	
	public static int findComun(ArrayList<Comunidade> l, String nome) {
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).nome.equals(nome)) {
				return i;
			}
		}
		System.out.printf("Comunidade  nao  encontrada\n");
		return -1;
	}
	
	public static void printaAmigos(ArrayList<String> amigos) {
		System.out.print("Amigos:");
		for(int i = 0; i < amigos.size(); i++) {
			System.out.print(" || " + amigos.get(i));
		}
		System.out.print(" ||\n");
	}
	
	public static void printaComun(ArrayList<Comunidade> comun, Conta conta) {
		System.out.print("Comunidades:");
		for (int i = 0; i < comun.size(); i++) {
			ArrayList<Conta> membros = comun.get(i).membros;
			for (int j = 0; j<membros.size(); j++) {
				if(membros.get(j).login.equals(conta.login)) {
					System.out.print(" || " + comun.get(i).nome);
				}
			}
		}
		System.out.print(" ||\n");
	}
	
	public static void printaMsg(ArrayList<Mensagem> msg) {
		System.out.print("Mensagens:");
		for(int i = 0; i < msg.size(); i++) {
			System.out.print(" || " + msg.get(i).remetente.login + ": ");
			System.out.print(msg.get(i).msg);
		}
		System.out.print(" ||\n");
	}
	
	
	
	public static void main(String[] args) {
		String login1, login2, nome1, senha1, nomeComun;
		Conta conta1, conta2;
		Comunidade comun1;
		int indice;
		Scanner input = new Scanner(System.in);
		Scanner inputInt = new Scanner(System.in);
		ArrayList<Conta> contas = new ArrayList<Conta>();
		ArrayList<Comunidade> comun = new ArrayList<Comunidade>();
		
		while(true) {
			System.out.println("1- Criar conta");
			System.out.println("2- Editar conta");
			System.out.println("3- Adicionar amigo");
			System.out.println("4- Enviar mensagem");
			System.out.println("5- Criar comunidade");
			System.out.println("6- Entrar para uma comunidade");
			System.out.println("7- Ver status da minha conta");
			System.out.println("8- Remover conta");
			System.out.println("9- Enviar mensagem no Feed de Noticias");
			System.out.println("10- Controlar privacidade do Feed de Noticias");
			System.out.println("11- Sair");
			System.out.println("Indique o que voce deseja fazer: ");
			int n = inputInt.nextInt();
		
			if(n==1) {
				System.out.print("Login: ");
				login1 = input.nextLine();
			
				System.out.print("Nome: ");
				nome1 = input.nextLine();
			
				System.out.print("Senha: ");
				senha1 = input.nextLine();
				
				contas.add(new Conta(nome1, login1, senha1));
			}
		
			if(n==2) {
				//ajeitar para permitir adicionar atributos
				System.out.printf("Qual login da conta que voce deseja editar: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				
				if(indice != -1) {
					System.out.printf("Novo login: ");
					login1 = input.nextLine();
				
					System.out.printf("Novo nome: ");
					nome1 = input.nextLine();
				
					System.out.printf("Nova senha: ");
					senha1 = input.nextLine();
					
					contas.set(indice, new Conta(nome1, login1, senha1));
				}
			}
			
			if(n==3) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						if(conta1.convite.size()>0) {
							System.out.println("Voce possui os seguintes convites de amizade: ");
							for (int i = 0; i < conta1.convite.size(); i++) {
								System.out.print("Voce aceita o convite de ");
								login2 = conta1.convite.get(i);
								System.out.print(login2);
								System.out.println("? (S / N)");
								String c1 = input.nextLine();
								if(c1.equals("S")) {
									conta1.amigos.add(login2);
									
									if(findConta(contas,login2)!=-1) {
										conta2 = contas.get(findConta(contas, login2));
										conta2.amigos.add(login1);
									}
								}
								conta1.convite.remove(i);
							}
						}
						System.out.println("Voce gostaria de adicionar um amigo? (S / N)");
						String c1 = input.nextLine();
						if(c1.equals("S")) {
							System.out.println("Digite o login do amigo que voce deseja adicionar: ");
							login2 = input.nextLine();
							
							if(findConta(contas,login2)!=-1) {
								conta2 = contas.get(findConta(contas, login2));
								conta2.convite.add(login1);
								System.out.println(conta2.convite);
							}
						}
					}
				}
				
			}
			if(n==4) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						System.out.println("Enviara mensagem para um usuario ou comunidade? (U / C)");
						String c1 = input.nextLine();
						if(c1.equals("U")) {
							System.out.println("Digite o login do destinatario: ");
							login2 = input.nextLine();
							if(findConta(contas,login2)!=-1) {
								conta2 = contas.get(findConta(contas, login2));
								System.out.println("Digite a mensagem que voce deseja enviar: ");
								String msg = input.nextLine();
								conta2.mensagens.add(new Mensagem(msg, conta1));
							}
						}
						else {
							//todos podem enviar msg para a comunidade
							System.out.println("Digite o nome da comunidade: ");
							nomeComun = input.nextLine();
							System.out.println(nomeComun);
							if(findComun(comun,nomeComun)!=-1) {
								comun1 = comun.get(findComun(comun, nomeComun));
								System.out.println("Digite a mensagem que voce deseja enviar: ");
								String msg = input.nextLine();
								System.out.println(msg);
								comun1.mensagens.add(new Mensagem(msg, conta1));
							}
						}
						
					}
				}
			}
			
			if(n==5) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						System.out.println("Digite o nome da comunidade: ");
						String nomeCom = input.nextLine();
						System.out.println("Digite a descricao da comunidade: ");
						String descricaoCom = input.nextLine();
						Comunidade comun3 = new Comunidade(nomeCom, descricaoCom, conta1);
						comun3.membros.add(conta1);
						comun.add(comun3);
						
					}
				}
			}
			
			if(n==6) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						System.out.println("Digite o nome da comunidade"
								+ " que voce deseja entrar: ");
						nomeComun = input.nextLine();
						if(findComun(comun,nomeComun)!=-1) {
							comun1 = comun.get(findComun(comun, nomeComun));
							comun1.membros.add(conta1);
						}
					}
				}
			}
			
			if(n==7) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						System.out.println("Nome: " + conta1.nome);
						System.out.println("Login: " + conta1.login);
						printaAmigos(conta1.amigos);
						printaComun(comun, conta1);
						printaMsg(conta1.mensagens);
					}
				}
				
			}
			
			if(n==8) {
				System.out.printf("Qual login da conta que voce deseja remover: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				
				if(indice != -1) {
					contas.remove(indice);
				}
			}
			
			if(n==9) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						System.out.println("Digite o que voce gostaria de postar no Feed de Noticias: ");
						String msg = input.nextLine();
						conta1.feed.add(msg);
					}
				}
			}
			
			if(n==10) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				indice = findConta(contas, login1);
				if(indice != -1) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					conta1 = contas.get(indice);
					if(conta1.Autenticador(senha1)) {
						System.out.println("Voce deseja que o seu Feed de Noticias seja: ");
						System.out.println("0 - Privado (apenas amigos)");
						System.out.println("1 - Aberto (todos)");
						conta1.privFeed = inputInt.nextInt();
					}
				}
			}
			
			if(n==11) {
				break;
			}
		}
	}
}
