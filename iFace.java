import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class iFace {
	
	public static void printaAmigos(ArrayList<String> amigos) {
		System.out.print("Amigos:");
		for(int i = 0; i < amigos.size(); i++) {
			System.out.print(" || " + amigos.get(i));
		}
		System.out.print(" ||\n");
	}
	
	public static void printaComun(Lista<Comunidade> comun, Conta conta) {
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
	
	public static void sendMessage(Entidade e, String mensagem, Conta remetente) {
		Mensagem m = new Mensagem(mensagem, remetente);
		e.receberMensagem(m);
	}
	
	public static void main(String[] args) {
		String login1, login2, nome1, senha1, nomeComun;
		Conta conta1, conta2;
		Comunidade comunidadeAux;
		int indice;
		Scanner input = new Scanner(System.in);
		Scanner inputInt = new Scanner(System.in);
		Lista<Conta> contas = new ListaConta();
		Lista<Comunidade> comunidades = new ListaComunidade();
		
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
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Novo login: ");
					login1 = input.nextLine();
				
					System.out.printf("Novo nome: ");
					nome1 = input.nextLine();
				
					System.out.printf("Nova senha: ");
					senha1 = input.nextLine();
					
					conta1.nome = nome1;
					conta1.login = login1;
					conta1.senha = senha1;
				}
			}
			
			if(n==3) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();

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
									conta2 = contas.find(login2);
									
									if(conta2 != null) {
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
							conta2 = contas.find(login2);
							
							if(conta2 != null) {
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
				conta1 = contas.find(login1);

				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();

					if(conta1.Autenticador(senha1)) {
						System.out.println("Enviara mensagem para um usuario ou comunidade? (U / C)");
						String c1 = input.nextLine();
						Lista l;
						String nomeEntidade;
						
						if(c1.equals("U")) {
							System.out.println("Digite o login do destinatario: ");
							nomeEntidade = input.nextLine();
							l = contas;
						}
						else {
							//todos podem enviar msg para a comunidade
							System.out.println("Digite o nome da comunidade: ");
							nomeEntidade = input.nextLine();
							l = comunidades;
						}
						System.out.println("Digite a mensagem que voce deseja enviar: ");
						String msg = input.nextLine();
						Entidade e = l.find(nomeEntidade);
						sendMessage(e, msg, conta1);	
					}
				}
			}
			
			if(n==5) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();

					if(conta1.Autenticador(senha1)) {
						System.out.println("Digite o nome da comunidade: ");
						String nomeCom = input.nextLine();
						System.out.println("Digite a descricao da comunidade: ");
						String descricaoCom = input.nextLine();
						Comunidade comun3 = new Comunidade(nomeCom, descricaoCom, conta1);
						comun3.membros.add(conta1);
						comunidades.add(comun3);
						
					}
				}
			}
			
			if(n==6) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();
					
					if(conta1.Autenticador(senha1)) {
						System.out.println("Digite o nome da comunidade"
								+ " que voce deseja entrar: ");
						nomeComun = input.nextLine();
						comunidadeAux = comunidades.find(nomeComun);
						if(comunidadeAux != null) {
							comunidadeAux.membros.add(conta1);
						}
					}
				}
			}
			
			if(n==7) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();

					if(conta1.Autenticador(senha1)) {
						System.out.println("Nome: " + conta1.nome);
						System.out.println("Login: " + conta1.login);
						printaAmigos(conta1.amigos);
						printaComun(comunidades, conta1);
						printaMsg(conta1.mensagens);
					}
				}
				
			}
			
			if(n==8) {
				System.out.printf("Qual login da conta que voce deseja remover: ");
				login1 = input.nextLine();
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					contas.remove(conta1);
				}
			}
			
			if(n==9) {
				System.out.printf("Login: ");
				login1 = input.nextLine();
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();

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
				conta1 = contas.find(login1);
				
				if(conta1 != null) {
					System.out.printf("Senha: ");
					senha1 = input.nextLine();

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
