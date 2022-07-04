import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class iFace {
	
	public static Conta fazerLogin(Lista<Conta> contas) {
		String login, senha;
		Conta conta;
		Scanner input = new Scanner(System.in);
		System.out.printf("Login: ");
		login = input.nextLine();
		conta = contas.find(login);
		
		if(conta != null) {
			System.out.printf("Senha: ");
			senha = input.nextLine();
			try {
				conta.Autenticador(senha);
			}catch (Exception e) {
				System.out.print("Erro ao fazer login!\n");
				return null;
			}
		}
		return conta;
	}
	
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
				
				login1 = null;
				nome1 = null;
				senha1 = null;
				conta1 = null;
				
				System.out.print("Login: ");
				login1 = input.nextLine();
				
				System.out.print("Nome: ");
				nome1 = input.nextLine();
				
				System.out.print("Senha: ");
				senha1 = input.nextLine();
					
				try {
					conta1 = new Conta(nome1, login1, senha1);
					
					contas.add(conta1);
				} catch(Exception e) {
					System.out.print("Erro na criacao da conta: " + e.getMessage() + "\n");
				}
			}
		
			if(n==2) {
				System.out.printf("Qual login da conta que voce deseja editar: " + "\n");
				try {
					conta1 = fazerLogin(contas);
					if(conta1 != null) {
						System.out.printf("Novo login: ");
						login1 = input.nextLine();
					
						System.out.printf("Novo nome: ");
						nome1 = input.nextLine();
					
						System.out.printf("Nova senha: ");
						senha1 = input.nextLine();
						
						try {
							conta1.nome = nome1;
							conta1.login = login1;
							conta1.senha = senha1;
						} catch(Exception e) {
							System.out.print("Nao foi possivel editar a conta");
						}
					}
				} catch(Exception e) {
					System.out.print("Conta nula");
				}
				
			}
			
			if(n==3) {
				try {
					conta1 = fazerLogin(contas);
					if (conta1 != null) {
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
									try {
										conta2 = contas.find(login2);
										conta2.amigos.add(conta1.login);
									}catch(Exception e) {
										System.out.print("Erro ao buscar conta!\n");
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
							try {
								conta2 = contas.find(login2);
								conta2.convite.add(conta1.login);
								System.out.println(conta2.convite);
								}catch(Exception e) {
									System.out.print("Erro ao buscar conta!\n");
								}
							}
					}
				}catch(Exception e) {
					System.out.print("Erro ao adicionar amigo: " + e.getMessage() + "\n");
				}
			}
				
			if(n==4) {
				try {
					conta1 = fazerLogin(contas);
					if (conta1 != null) {
						System.out.println("Enviara mensagem para um usuario ou comunidade? (U / C)");
						String c1 = input.nextLine();
						Lista<? extends Entidade> l;
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
						
						try {
							Entidade e = l.find(nomeEntidade);
							sendMessage(e, msg, conta1);	
						}catch(Exception e) {
								System.out.print("Erro ao buscar conta!\n");
						}	
					}
						//tratar sendMessage()
				}catch(Exception e) {
					System.out.print("Erro ao enviar mensagem: " + e.getMessage() + "\n");
				}
			}
			
			if(n==5) {
				try {
					conta1 = fazerLogin(contas);
					if (conta1 != null) {
						System.out.println("Digite o nome da comunidade: ");
						String nomeCom = input.nextLine();
						System.out.println("Digite a descricao da comunidade: ");
						String descricaoCom = input.nextLine();
						Comunidade comun3 = new Comunidade(nomeCom, descricaoCom, conta1);
						comun3.membros.add(conta1);
						comunidades.add(comun3);
					}
				}catch(Exception e) {
					System.out.print("Erro ao criar comunidade: " + e.getMessage() + "\n");
				}
			}
			
			if(n==6) {
				try {
					conta1 = fazerLogin(contas);
					if (conta1 != null) {
						System.out.println("Digite o nome da comunidade"
										+ " que voce deseja entrar: ");
						nomeComun = input.nextLine();
						comunidadeAux = comunidades.find(nomeComun);
						if(comunidadeAux != null) {
							comunidadeAux.membros.add(conta1);
						}
					}
				}catch(Exception e) {
					System.out.print("Erro ao entrar em comunidade: " + e.getMessage() + "\n");
				}
			}

			if(n==7) {
				try {
					conta1 = fazerLogin(contas);
					if (conta1 != null) {
						System.out.println("Nome: " + conta1.nome);
						System.out.println("Login: " + conta1.login);
						printaAmigos(conta1.amigos);
						printaComun(comunidades, conta1);
						printaMsg(conta1.mensagens);
					}
				}catch(Exception e) {
					System.out.print("Erro ao consultar conta: " + e.getMessage() + "\n");
				}
			}
			
			if(n==8) {
				try {
					conta1 = fazerLogin(contas);
					contas.remove(conta1);
				}catch(Exception e) {
					System.out.print("Erro ao buscar conta!\n");
				}
			}
			
			if(n==9) {
				try {
					conta1 = fazerLogin(contas);
					System.out.println("Digite o que voce gostaria de postar no Feed de Noticias: ");
					String msg = input.nextLine();
					if (conta1 != null) {
						conta1.feed.add(msg);
					}
				}catch(Exception e) {
					System.out.print("Erro ao postar no Feed: " + e.getMessage() + "\n");
				}
			}
			
			if(n==10) {
				try {
					conta1 = fazerLogin(contas);
					if(conta1 != null) {
						System.out.println("Voce deseja que o seu Feed de Noticias seja: ");
						System.out.println("0 - Privado (apenas amigos)");
						System.out.println("1 - Aberto (todos)");
						conta1.privFeed = inputInt.nextInt();
					}
				}catch(Exception e) {
					System.out.print("Erro ao alterar privacidade: " + e.getMessage() + "\n");
				}
			}
			
			if(n==11) {
				break;
			}
		}
	}
}
