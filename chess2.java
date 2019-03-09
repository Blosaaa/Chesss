import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class chess2 {
	
	public static void pause ( int ms) {
		try {
		Thread . sleep (ms);
		} catch ( InterruptedException e){}
	}

	public static int joueurBlanc(){
		return (3);
	}
	
	public static void main (String[] args) {
		
		int plat[][] = {{21,22,23,24,25,23,22,21},{26,26,26,26,26,26,26,26},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{16,16,16,16,16,16,16,16},{11,12,13,14,15,13,12,11}};
		
		Plateau plato = new Plateau();
		
		File f = new File("chese.txt");
		
		System.out.println("Chemin absolu du fichier : " + f.getAbsolutePath());

    System.out.println("Nom du fichier : " + f.getName());

    System.out.println("Est-ce qu'il existe ? " + f.exists());

    System.out.println("Est-ce un répertoire ? " + f.isDirectory());

    System.out.println("Est-ce un fichier ? " + f.isFile());

    



		
		for(File file : f.listRoots())
    {
      System.out.println(file.getAbsolutePath());
      try {
        int i = 1;	
        //On parcourt la liste des fichiers et répertoires
        for(File nom : file.listFiles()){
          //S'il s'agit d'un dossier, on ajoute un "/"
          System.out.print("\t\t" + ((nom.isDirectory()) ? nom.getName()+"/" : nom.getName()));
					
          if((i%4) == 0){
            System.out.print("\n");
          }
          i++;
        }
        System.out.println("\n");
      } catch (NullPointerException e) {
        //L'instruction peut générer une NullPointerException
        //s'il n'y a pas de sous-fichier !
      }
	}
		
		
		affichPlat(plat); //on affiche le plateau
		
		int win = 0; //pour savoir qui a gagné
		
		if (joueurBlanc() == 1){//si le joueur joue en 1er
			
			while(fin(plat)==false){// tant que c'est pas fini
				
				joueur(plat);// joueur joue
				win = 1; //si ya fin après il a gagné
				
				affichPlat(plat); //affich ce qu'il joue
				
				if (fin(plat)==false){// si c'est pas fini l'ordi joue
					ordi(plat); //l'ordi joue
					win = 0;
					affichPlat(plat); //on raffiche le truc
				}
		
			}
			
			if (win ==1){System.out.println("You win");}
			else{System.out.println("You lose");}
		}
		else if(joueurBlanc()==2){
			while(fin(plat)==false){
				
				ordi(plat);
				win = 0;
				affichPlat(plat);
				
				if (fin(plat)==false){
					joueur(plat);
					win = 1;
					affichPlat(plat);

				}
			}
		
			if (win ==1){System.out.println("You win");}
			else{System.out.println("You lose");}
		}	
		
		else if(joueurBlanc()==3){
			while(fin(plat)==false){
		
				//pause(500);
					
				ordi(plat);
				win = 1;
				affichPlat(plat);
				
				if (fin(plat)==false){
					//pause(500);
					ordi2(plat);
					win = 0;
					affichPlat(plat);
				}		
			}
			if (win ==1){System.out.println("Ordi win");}
			else{System.out.println("Ordi lose");}
		}
		
		else if(joueurBlanc()==4){
			while(fin(plat)==false){
				
				//pause(1000);
				
				ordi2(plat);
				win = 1;
				affichPlat(plat);
				
				if (fin(plat)==false){
					ordi(plat);
					win = 0;
					affichPlat(plat);
				}
		}		
			if (win ==1){System.out.println("Ordi2 win");}
			else{System.out.println("Ordi2 lose");}
		}
		
		}
			
	public static void affichPlat(int plat[][]){
		
		Affichage.afficherPlat(plat); //fenetre affichage
		
		for (int i = 0; i <plat.length; i++){
			
			System.out.println();
			for(int j = 0; j<plat[0].length; j++){
				System.out.print("-----");
			}
			
			System.out.println();
		
			for(int j = 0; j<plat[0].length; j++){
				
				switch((plat[i][j])%10){
					
					case 0: if (j==7){
								System.out.print("    | "+(i+1));
							}
							else{System.out.print("    |");}
					break;
					case 1: System.out.print(" T");
					break;
					case 2: System.out.print(" C");
					break;
					case 3: System.out.print(" F");
					break;
					case 4: System.out.print(" D");
					break;
					case 5: System.out.print(" R");
					break;
					case 6: System.out.print(" P");
					break;
					default:;
				}
				
				if (j==7){
					switch(col(plat, i, j)){
						
						case 1: System.out.print("b | "+(i+1));
						break;
						case 2: System.out.print("n | "+(i+1));
						break;
						default:;
					}
				}
				else{
					switch(col(plat, i, j)){
						
						case 1: System.out.print("b |");
						break;
						case 2: System.out.print("n |");
						break;
						default:;
					}
				}
			}
		}
		System.out.println();
		for(int j = 0; j<plat[0].length; j++){
			System.out.print("-----");
		}
		System.out.println();
		System.out.print("  1    2    3    4    5    6    7    8");
	}
	
	public static boolean fin (int plat[][]){
		
		boolean mat = false;
		boolean pat = false;
		
		/*for (int i = 0; i <plat.length; i++){
		
			for(int j = 0; j<plat[0].length; j++){
				
				for (int u =0; u<30; u++){
					
					if(listPossi(plat, i , j)[u][0]!=8){mat = false;}
				}
			}
		}*/
		
		return (mat);
	}

	public static void joueur(int plat[][]){
	
		int li = 8;
		int co = 8;
		boolean colBon = false;
		
		do {
			Scanner sc = new Scanner(System.in);

			System.out.println();

			System.out.println("Quelle piece voulez vous bouger?");
			
			do{
			System.out.println("Ligne");
			
			li = sc.nextInt();
			li=li-1;
			System.out.println("Colonne");
			
			co = sc.nextInt();
			co=co-1;
			}while(li<0||li>7||co<0||co>7);
			
			if(joueurBlanc()==1&&col(plat, li, co)==1){colBon = true;}
			if(joueurBlanc()==2&&col(plat, li, co)==2){colBon = true;}			
			
		} while (pieceBougeable(plat, li, co)==false||colBon==false);
		
		System.out.println();
		
		for(int i = 0; i <30; i++){
			
			if (listPossi(plat, li, co)[i][0]==(8)){}
			else{
			System.out.print((i+1)+"  ");
			System.out.print(listPossi(plat, li, co)[i][0]+1+" ");
			System.out.println(listPossi(plat, li, co)[i][1]+1);
			}	
			
		}
		
		int move =0;
		
		do {
			do{
			System.out.println("Quel mouvement?");
			Scanner sca = new Scanner(System.in);

			move = sca.nextInt();
			move = move-1;
			}while(move<0||move>29);
		} while (listPossi(plat, li, co)[move][0]==8);
		
		int proli = listPossi(plat, li, co)[move][0];
		int proco = listPossi(plat, li, co)[move][1];
		
		plat[proli][proco]=plat[li][co];
		plat[li][co]=0;
		if(col(plat, proli, proco)==1 && plat[proli][proco]%10 == 6 && proli == 0){
			plat[proli][proco] = plat[proli][proco] - 2;
		}
		if(col(plat, proli, proco)==2 && plat[proli][proco]%10 == 6 && proli == 7){
			plat[proli][proco] = plat[proli][proco] - 2;
		}
		
	}
	
	public static void ordi(int plat[][]){
		
		int co = 8;
		int li =8;
		boolean colBon = false;
		
		do {
			
		colBon = false;
			
		co = (int)(8*(Math.random()));
		li = (int)(8*(Math.random()));
		
		if(joueurBlanc()==2&&col(plat, li, co)==1){colBon = true;}
		if(joueurBlanc()==1&&col(plat, li, co)==2){colBon = true;}
		if(joueurBlanc()==3&&col(plat, li, co)==1){colBon = true;}
		if(joueurBlanc()==4&&col(plat, li, co)==2){colBon = true;}
		
		
		} while (pieceBougeable(plat, li, co)==false||li<0||li>7||co<0||co>7||colBon == false);
	
		int move = 0;
		
		do{
			
		move = (int)(30*(Math.random()));
		
		} while (listPossi(plat, li, co)[move][0]==8);
		
		int proli = listPossi(plat, li, co)[move][0];
		int proco = listPossi(plat, li, co)[move][1];
		
		plat[proli][proco]=plat[li][co];
		plat[li][co]=0;
		if(col(plat, proli, proco)==1 && plat[proli][proco]%10 == 6 && proli == 0){
			plat[proli][proco] = plat[proli][proco] - 2;
		}
		if(col(plat, proli, proco)==2 && plat[proli][proco]%10 == 6 && proli == 7){
			plat[proli][proco] = plat[proli][proco] - 2;
		}
	
	}
	
	public static void ordi2(int plat[][]){
		
		int co = 8;
		int li =8;
		boolean colBon = false;
		
		do {
			
		colBon = false;
			
		co = (int)(8*(Math.random()));
		li = (int)(8*(Math.random()));
		
		if(joueurBlanc()==3&&col(plat, li, co)==2){colBon = true;}
		if(joueurBlanc()==4&&col(plat, li, co)==1){colBon = true;}
		
		
		} while (pieceBougeable(plat, li, co)==false||li<0||li>7||co<0||co>7||colBon == false);
	
		int move = 0;
		
		do{
			
		move = (int)(30*(Math.random()));
		
		} while (listPossi(plat, li, co)[move][0]==8);
		
		
		int proli = listPossi(plat, li, co)[move][0];
		int proco = listPossi(plat, li, co)[move][1];
		
		plat[proli][proco]=plat[li][co];
		plat[li][co]=0;
		if(col(plat, proli, proco)==1 && plat[proli][proco]%10 == 6 && proli == 0){
			plat[proli][proco] = plat[proli][proco] - 2;
		}
		if(col(plat, proli, proco)==2 && plat[proli][proco]%10 == 6 && proli == 7){
			plat[proli][proco] = plat[proli][proco] - 2;
		}
	
	}
		
	public static int[][] listPossi(int plat[][], int li, int co){
		
		
		int list[][] = new int[30][2];
		
		for (int i = 0; i< list.length; i++){
			
			for (int j = 0; j<list[0].length; j ++){
				
				list[i][j] = 8;
			}
		}
		
		int u = 0;
				
		switch(plat[li][co]%10){
			
			case 1:
			if ((col(plat,li,(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li,(co+1))==0){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li,(co+2))==0){
				
				list[u][0] = li;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li,(co+3))==0){
				
						list[u][0] = li;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li,(co+4))==0){
				
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li,(co+5))==0){
				
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li,(co+6))==0){
				
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li,(co+7))==0){
				
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,li,(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li,(co-1))==0){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li,(co-2))==0){
				
				list[u][0] = li;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li,(co-3))==0){
				
						list[u][0] = li;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li,(co-4))==0){
				
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li,(co-5))==0){
				
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li,(co-6))==0){
				
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li,(co-7))==0){
				
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),co))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li-1),co)==0){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li-2),co))+col(plat, li, co)==3){
					
					list[u][0] = li-2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li-2),co)==0){
				
				list[u][0] = li-2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li-3),co))+col(plat, li, co)==3){
					
					list[u][0] = li-3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li-3),co)==0){
				
						list[u][0] = li-3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li-4),co))+col(plat, li, co)==3){
					
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li-4),co)==0){
				
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li-5),co))+col(plat, li, co)==3){
						
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li-5),co)==0){
				
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li-6),co))+col(plat, li, co)==3){
							
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li-6),co)==0){
				
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li-7),co))+col(plat, li, co)==3){
								
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li-7),co)==0){
				
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li+1),co))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li+1),co)==0){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li+2),co))+col(plat, li, co)==3){
					
					list[u][0] = li+2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li+2),co)==0){
				
				list[u][0] = li+2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li+3),co))+col(plat, li, co)==3){
					
					list[u][0] = li+3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li+3),co)==0){
				
						list[u][0] = li+3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li+4),co))+col(plat, li, co)==3){
					
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li+4),co)==0){
				
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li+5),co))+col(plat, li, co)==3){
						
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li+5),co)==0){
				
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li+6),co))+col(plat, li, co)==3){
							
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li+6),co)==0){
				
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li+7),co))+col(plat, li, co)==3){
								
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li+7),co)==0){
				
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			
			break;
			
			case 2:
			if(col(plat, (li+1), (co-2))==0||col(plat, (li+1), (co-2))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co-2;
				u++;
			}
			if(col(plat, (li+1), (co+2))==0||col(plat, (li+1), (co+2))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co+2;
				u++;
			}
			if(col(plat, (li-1), (co-2))==0||col(plat, (li-1), (co-2))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co-2;
				u++;
			}
			if(col(plat, (li-1), (co+2))==0||col(plat, (li-1), (co+2))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co+2;
				u++;
			}
			if(col(plat, (li+2), (co-1))==0||col(plat, (li+2), (co-1))+col(plat, li, co)==3){
				
				list[u][0] = li+2;
				list[u][1] = co-1;
				u++;
			}
			if(col(plat, (li+2), (co+1))==0||col(plat, (li+2), (co+1))+col(plat, li, co)==3){
				
				list[u][0] = li+2;
				list[u][1] = co+1;
				u++;
			}
			if(col(plat, (li-2), (co-1))==0||col(plat, (li-2), (co-1))+col(plat, li, co)==3){
				
				list[u][0] = li-2;
				list[u][1] = co-1;
				u++;
			}
			if(col(plat, (li-2), (co+1))==0||col(plat, (li-2), (co+1))+col(plat, li, co)==3){
				
				list[u][0] = li-2;
				list[u][1] = co+1;
				u++;
			}
			
			break;
			
			case 3:
			
			if ((col(plat,(li+1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li+1,(co+1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li+2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li+2,(co+2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li+3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li+3,(co+3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li+4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li+4,(co+4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li+5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li+5,(co+5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li+6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li+6,(co+6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li+7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li+7,(co+7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
		if ((col(plat,(li-1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li-1,(co+1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li-2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li-2,(co+2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li-3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li-3,(co+3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li-4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li-4,(co+4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li-5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li-5,(co+5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li-6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li-6,(co+6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li-7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li-7,(co+7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
		if ((col(plat,(li+1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li+1,(co-1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li+2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li+2,(co-2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li+3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li+3,(co-3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li+4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li+4,(co-4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li+5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li+5,(co-5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li+6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li+6,(co-6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li+7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li+7,(co-7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
		if ((col(plat,(li-1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li-1,(co-1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li-2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li-2,(co-2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li-3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li-3,(co-3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li-4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li-4,(co-4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li-5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li-5,(co-5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li-6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li-6,(co-6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li-7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li-7,(co-7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			
			break;
			
			case 4:
			if ((col(plat,(li+1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li+1,(co+1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li+2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li+2,(co+2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li+3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li+3,(co+3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li+4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li+4,(co+4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li+5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li+5,(co+5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li+6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li+6,(co+6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li+7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li+7,(co+7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li-1,(co+1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li-2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li-2,(co+2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li-3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li-3,(co+3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li-4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li-4,(co+4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li-5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li-5,(co+5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li-6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li-6,(co+6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li-7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li-7,(co+7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li+1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li+1,(co-1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li+2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li+2,(co-2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li+3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li+3,(co-3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li+4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li+4,(co-4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li+5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li+5,(co-5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li+6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li+6,(co-6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li+7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li+7,(co-7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li-1,(co-1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li-2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li-2,(co-2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li-3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li-3,(co-3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li-4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li-4,(co-4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li-5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li-5,(co-5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li-6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li-6,(co-6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li-7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li-7,(co-7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,li,(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li,(co+1))==0){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li,(co+2))==0){
				
				list[u][0] = li;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li,(co+3))==0){
				
						list[u][0] = li;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li,(co+4))==0){
				
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li,(co+5))==0){
				
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li,(co+6))==0){
				
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li,(co+7))==0){
				
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,li,(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li,(co-1))==0){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li,(co-2))==0){
				
				list[u][0] = li;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li,(co-3))==0){
				
						list[u][0] = li;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li,(co-4))==0){
				
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li,(co-5))==0){
				
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li,(co-6))==0){
				
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li,(co-7))==0){
				
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),co))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li-1),co)==0){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li-2),co))+col(plat, li, co)==3){
					
					list[u][0] = li-2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li-2),co)==0){
				
				list[u][0] = li-2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li-3),co))+col(plat, li, co)==3){
					
					list[u][0] = li-3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li-3),co)==0){
				
						list[u][0] = li-3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li-4),co))+col(plat, li, co)==3){
					
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li-4),co)==0){
				
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li-5),co))+col(plat, li, co)==3){
						
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li-5),co)==0){
				
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li-6),co))+col(plat, li, co)==3){
							
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li-6),co)==0){
				
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li-7),co))+col(plat, li, co)==3){
								
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li-7),co)==0){
				
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li+1),co))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li+1),co)==0){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li+2),co))+col(plat, li, co)==3){
					
					list[u][0] = li+2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li+2),co)==0){
				
				list[u][0] = li+2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li+3),co))+col(plat, li, co)==3){
					
					list[u][0] = li+3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li+3),co)==0){
				
						list[u][0] = li+3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li+4),co))+col(plat, li, co)==3){
					
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li+4),co)==0){
				
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li+5),co))+col(plat, li, co)==3){
						
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li+5),co)==0){
				
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li+6),co))+col(plat, li, co)==3){
							
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li+6),co)==0){
				
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li+7),co))+col(plat, li, co)==3){
								
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li+7),co)==0){
				
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			
			break;
			
			case 5:
			if ((col(plat,(li+1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li-1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li+1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,li,(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,li,(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),co))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			if ((col(plat,(li+1),co))+col(plat, li, co)==3){
			
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			if ((col(plat,(li+1),(co+1))==0)){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li-1),(co+1))==0)){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li+1),(co-1))==0)){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),(co-1))==0)){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,li,(co+1))==0)){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,li,(co-1))==0)){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),co))==0){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			if ((col(plat,(li+1),co))==0){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			
			break;
			
			case 6:
			if (col(plat, li, co)==1){
				
				
				if (col(plat, (li-1), co)==0){
					
					list[u][0] = li-1;
					list[u][1] = co;
					u++;
				}
				if (col(plat, (li-1), (co-1))+col(plat, li, co)==3){
				
					list[u][0] = li-1;
					list[u][1] = co-1;
					u++;
				}
				if (col(plat, (li-1), (co+1))+col(plat, li, co)==3){
						
					list[u][0] = li-1;
					list[u][1] = co+1;
					u++;
				}
				if(li==6&&col(plat, (li-1), co)==0&&col(plat, (li-2), co)==0){
					
					list[u][0] = li-2;
					list[u][1] = co;
					u++;
				}
				}
			if (col(plat, li, co)==2){
				
				
				if (col(plat, (li+1), co)==0){
					
					list[u][0] = li+1;
					list[u][1] = co;
					u++;
				}
				if (col(plat, (li+1), (co+1))+col(plat, li, co)==3){
				
					list[u][0] = li+1;
					list[u][1] = co+1;
					u++;
				}
				if (col(plat, (li+1), (co-1))+col(plat, li, co)==3){
						
					list[u][0] = li+1;
					list[u][1] = co-1;
					u++;
				}
				if(li==1&&col(plat, (li+1), co)==0&&col(plat, (li+2), co)==0){
					
					list[u][0] = li+2;
					list[u][1] = co;
					u++;
				}
				}
			
			break;
			
			default:;
		}
		
		int platCop[][] = {{21,22,23,24,25,23,22,21},{26,26,26,26,26,26,26,26},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{16,16,16,16,16,16,16,16},{11,12,13,14,15,13,12,11}};		 
		
		for ( u =0 ; u<30; u++){
			
			for (int i = 0; i <plat.length; i++){
		
				for(int j = 0; j<plat[0].length; j++){
						
					platCop[i][j]=plat[i][j];
				}
			}
				
			if (list[u][0]!=8){
				
				int proli = list[u][0];
				int proco = list[u][1];
				
				platCop[proli][proco]=platCop[li][co];
				platCop[li][co]=0;
				if(col(platCop, proli, proco)==1 && platCop[proli][proco]%10 == 6 && proli == 0){
					platCop[proli][proco] = platCop[proli][proco] - 2;
				}
				if(col(platCop, proli, proco)==2 && platCop[proli][proco]%10 == 6 && proli == 7){
					platCop[proli][proco] = platCop[proli][proco] - 2;
				}
					
				if(roiDanger(platCop, col(platCop, proli, proco))==true){
					list[u][0] = 8;
					list[u][1] = 8;
				}
			}
		}
						
		return (list);
	}
	
	public static boolean roiDanger(int[][] plat, int col){
		
		int in = 8;
		int jn = 8;
		int ib = 8;
		int jb = 8; 
		
		for (int i = 0; i <plat.length; i++){
		
			for(int j = 0; j<plat[0].length; j++){
				
				if (plat[i][j] == 25){in=i;
					jn= j;}
				if (plat[i][j] == 15){ib= i;
					jb=j;}
			}
		}
		
		boolean dang = false;
		
		for (int i = 0; i <plat.length; i++){
			
				for(int j = 0; j<plat[0].length; j++){
					
					if (col(plat, i , j)==3-col){
						for (int u = 0; u<30; u++){
							
							if(listPossi2(plat, i, j)[u][0]!=8){
								if (col == 1){
									if(listPossi2(plat, i, j)[u][0]==ib&&listPossi2(plat, i, j)[u][1]==jb){
										dang = true;
										}
								}
								else{
									if(listPossi2(plat, i, j)[u][0]==in&&listPossi2(plat, i, j)[u][1]==jn){
										dang = true;
										}
								}
							}
						}
					}
				}
			}
						
	return(dang);
		
	}
	
	public static int[][] listPossi2(int plat[][], int li, int co){
		
		
		int list[][] = new int[30][2];
		
		for (int i = 0; i< list.length; i++){
			
			for (int j = 0; j<list[0].length; j ++){
				
				list[i][j] = 8;
			}
		}
		
		int u = 0;
				
		switch(plat[li][co]%10){
			
			case 1:
			if ((col(plat,li,(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li,(co+1))==0){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li,(co+2))==0){
				
				list[u][0] = li;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li,(co+3))==0){
				
						list[u][0] = li;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li,(co+4))==0){
				
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li,(co+5))==0){
				
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li,(co+6))==0){
				
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li,(co+7))==0){
				
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,li,(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li,(co-1))==0){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li,(co-2))==0){
				
				list[u][0] = li;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li,(co-3))==0){
				
						list[u][0] = li;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li,(co-4))==0){
				
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li,(co-5))==0){
				
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li,(co-6))==0){
				
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li,(co-7))==0){
				
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),co))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li-1),co)==0){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li-2),co))+col(plat, li, co)==3){
					
					list[u][0] = li-2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li-2),co)==0){
				
				list[u][0] = li-2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li-3),co))+col(plat, li, co)==3){
					
					list[u][0] = li-3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li-3),co)==0){
				
						list[u][0] = li-3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li-4),co))+col(plat, li, co)==3){
					
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li-4),co)==0){
				
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li-5),co))+col(plat, li, co)==3){
						
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li-5),co)==0){
				
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li-6),co))+col(plat, li, co)==3){
							
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li-6),co)==0){
				
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li-7),co))+col(plat, li, co)==3){
								
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li-7),co)==0){
				
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li+1),co))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li+1),co)==0){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li+2),co))+col(plat, li, co)==3){
					
					list[u][0] = li+2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li+2),co)==0){
				
				list[u][0] = li+2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li+3),co))+col(plat, li, co)==3){
					
					list[u][0] = li+3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li+3),co)==0){
				
						list[u][0] = li+3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li+4),co))+col(plat, li, co)==3){
					
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li+4),co)==0){
				
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li+5),co))+col(plat, li, co)==3){
						
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li+5),co)==0){
				
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li+6),co))+col(plat, li, co)==3){
							
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li+6),co)==0){
				
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li+7),co))+col(plat, li, co)==3){
								
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li+7),co)==0){
				
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			
			break;
			
			case 2:
			if(col(plat, (li+1), (co-2))==0||col(plat, (li+1), (co-2))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co-2;
				u++;
			}
			if(col(plat, (li+1), (co+2))==0||col(plat, (li+1), (co+2))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co+2;
				u++;
			}
			if(col(plat, (li-1), (co-2))==0||col(plat, (li-1), (co-2))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co-2;
				u++;
			}
			if(col(plat, (li-1), (co+2))==0||col(plat, (li-1), (co+2))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co+2;
				u++;
			}
			if(col(plat, (li+2), (co-1))==0||col(plat, (li+2), (co-1))+col(plat, li, co)==3){
				
				list[u][0] = li+2;
				list[u][1] = co-1;
				u++;
			}
			if(col(plat, (li+2), (co+1))==0||col(plat, (li+2), (co+1))+col(plat, li, co)==3){
				
				list[u][0] = li+2;
				list[u][1] = co+1;
				u++;
			}
			if(col(plat, (li-2), (co-1))==0||col(plat, (li-2), (co-1))+col(plat, li, co)==3){
				
				list[u][0] = li-2;
				list[u][1] = co-1;
				u++;
			}
			if(col(plat, (li-2), (co+1))==0||col(plat, (li-2), (co+1))+col(plat, li, co)==3){
				
				list[u][0] = li-2;
				list[u][1] = co+1;
				u++;
			}
			
			break;
			
			case 3:
			
			if ((col(plat,(li+1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li+1,(co+1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li+2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li+2,(co+2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li+3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li+3,(co+3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li+4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li+4,(co+4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li+5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li+5,(co+5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li+6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li+6,(co+6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li+7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li+7,(co+7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
		if ((col(plat,(li-1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li-1,(co+1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li-2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li-2,(co+2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li-3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li-3,(co+3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li-4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li-4,(co+4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li-5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li-5,(co+5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li-6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li-6,(co+6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li-7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li-7,(co+7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
		if ((col(plat,(li+1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li+1,(co-1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li+2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li+2,(co-2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li+3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li+3,(co-3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li+4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li+4,(co-4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li+5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li+5,(co-5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li+6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li+6,(co-6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li+7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li+7,(co-7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
		if ((col(plat,(li-1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li-1,(co-1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li-2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li-2,(co-2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li-3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li-3,(co-3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li-4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li-4,(co-4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li-5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li-5,(co-5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li-6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li-6,(co-6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li-7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li-7,(co-7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			
			break;
			
			case 4:
			if ((col(plat,(li+1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li+1,(co+1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li+2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li+2,(co+2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li+3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li+3,(co+3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li+4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li+4,(co+4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li+5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li+5,(co+5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li+6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li+6,(co+6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li+7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li+7,(co+7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li-1,(co+1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li-2,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li-2,(co+2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li-3,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li-3,(co+3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li-4,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li-4,(co+4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li-5,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li-5,(co+5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li-6,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li-6,(co+6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li-7,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li-7,(co+7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li+1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li+1,(co-1))==0){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li+2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li+2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li+2,(co-2))==0){
				
				list[u][0] = li+2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li+3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li+3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li+3,(co-3))==0){
				
						list[u][0] = li+3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li+4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li+4,(co-4))==0){
				
							list[u][0] = li+4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li+5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li+5,(co-5))==0){
				
								list[u][0] = li+5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li+6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li+6,(co-6))==0){
				
									list[u][0] = li+6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li+7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li+7,(co-7))==0){
				
										list[u][0] = li+7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li-1,(co-1))==0){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li-2,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li-2;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li-2,(co-2))==0){
				
				list[u][0] = li-2;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li-3,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li-3;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li-3,(co-3))==0){
				
						list[u][0] = li-3;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li-4,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li-4,(co-4))==0){
				
							list[u][0] = li-4;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li-5,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li-5,(co-5))==0){
				
								list[u][0] = li-5;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li-6,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li-6,(co-6))==0){
				
									list[u][0] = li-6;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li-7,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li-7,(co-7))==0){
				
										list[u][0] = li-7;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,li,(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			else if (col(plat,li,(co+1))==0){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
				
				if ((col(plat,li,(co+2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+2;
					u++;
				}
				else if (col(plat,li,(co+2))==0){
				
				list[u][0] = li;
				list[u][1] = co+2;
				u++;
				
					if ((col(plat,li,(co+3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co+3;
					u++;
					}
					else if (col(plat,li,(co+3))==0){
				
						list[u][0] = li;
						list[u][1] = co+3;
						u++;
				
						if ((col(plat,li,(co+4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
						}
						else if (col(plat,li,(co+4))==0){
				
							list[u][0] = li;
							list[u][1] = co+4;
							u++;
					
							if ((col(plat,li,(co+5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
							}
							else if (col(plat,li,(co+5))==0){
				
								list[u][0] = li;
								list[u][1] = co+5;
								u++;
						
								if ((col(plat,li,(co+6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
								}
								else if (col(plat,li,(co+6))==0){
				
									list[u][0] = li;
									list[u][1] = co+6;
									u++;
							
									if ((col(plat,li,(co+7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
									}
									else if (col(plat,li,(co+7))==0){
				
										list[u][0] = li;
										list[u][1] = co+7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,li,(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			else if (col(plat,li,(co-1))==0){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
				
				if ((col(plat,li,(co-2))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-2;
					u++;
				}
				else if (col(plat,li,(co-2))==0){
				
				list[u][0] = li;
				list[u][1] = co-2;
				u++;
				
					if ((col(plat,li,(co-3))+col(plat, li, co))==3){
					
					list[u][0] = li;
					list[u][1] = co-3;
					u++;
					}
					else if (col(plat,li,(co-3))==0){
				
						list[u][0] = li;
						list[u][1] = co-3;
						u++;
				
						if ((col(plat,li,(co-4))+col(plat, li, co))==3){
					
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
						}
						else if (col(plat,li,(co-4))==0){
				
							list[u][0] = li;
							list[u][1] = co-4;
							u++;
					
							if ((col(plat,li,(co-5))+col(plat, li, co))==3){
						
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
							}
							else if (col(plat,li,(co-5))==0){
				
								list[u][0] = li;
								list[u][1] = co-5;
								u++;
						
								if ((col(plat,li,(co-6))+col(plat, li, co))==3){
							
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
								}
								else if (col(plat,li,(co-6))==0){
				
									list[u][0] = li;
									list[u][1] = co-6;
									u++;
							
									if ((col(plat,li,(co-7))+col(plat, li, co))==3){
								
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
									}
									else if (col(plat,li,(co-7))==0){
				
										list[u][0] = li;
										list[u][1] = co-7;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li-1),co))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li-1),co)==0){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li-2),co))+col(plat, li, co)==3){
					
					list[u][0] = li-2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li-2),co)==0){
				
				list[u][0] = li-2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li-3),co))+col(plat, li, co)==3){
					
					list[u][0] = li-3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li-3),co)==0){
				
						list[u][0] = li-3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li-4),co))+col(plat, li, co)==3){
					
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li-4),co)==0){
				
							list[u][0] = li-4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li-5),co))+col(plat, li, co)==3){
						
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li-5),co)==0){
				
								list[u][0] = li-5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li-6),co))+col(plat, li, co)==3){
							
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li-6),co)==0){
				
									list[u][0] = li-6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li-7),co))+col(plat, li, co)==3){
								
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li-7),co)==0){
				
										list[u][0] = li-7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			if ((col(plat,(li+1),co))+col(plat, li, co)==3){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			else if (col(plat,(li+1),co)==0){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
				
				if ((col(plat,(li+2),co))+col(plat, li, co)==3){
					
					list[u][0] = li+2;
					list[u][1] = co;
					u++;
				}
				else if (col(plat,(li+2),co)==0){
				
				list[u][0] = li+2;
				list[u][1] = co;
				u++;
				
					if ((col(plat,(li+3),co))+col(plat, li, co)==3){
					
					list[u][0] = li+3;
					list[u][1] = co;
					u++;
					}
					else if (col(plat,(li+3),co)==0){
				
						list[u][0] = li+3;
						list[u][1] = co;
						u++;
				
						if ((col(plat,(li+4),co))+col(plat, li, co)==3){
					
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
						}
						else if (col(plat,(li+4),co)==0){
				
							list[u][0] = li+4;
							list[u][1] = co;
							u++;
					
							if ((col(plat,(li+5),co))+col(plat, li, co)==3){
						
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
							}
							else if (col(plat,(li+5),co)==0){
				
								list[u][0] = li+5;
								list[u][1] = co;
								u++;
						
								if ((col(plat,(li+6),co))+col(plat, li, co)==3){
							
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
								}
								else if (col(plat,(li+6),co)==0){
				
									list[u][0] = li+6;
									list[u][1] = co;
									u++;
							
									if ((col(plat,(li+7),co))+col(plat, li, co)==3){
								
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
									}
									else if (col(plat,(li+7),co)==0){
				
										list[u][0] = li+7;
										list[u][1] = co;
										u++;
								
								}
							}
						}
									
					}
				
			}
		}
	
		
			
		}
			
			break;
			
			case 5:
			if ((col(plat,(li+1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li-1),(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li+1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,li,(co+1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,li,(co-1))+col(plat, li, co))==3){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),co))+col(plat, li, co)==3){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			if ((col(plat,(li+1),co))+col(plat, li, co)==3){
			
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			if ((col(plat,(li+1),(co+1))==0)){
				
				list[u][0] = li+1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li-1),(co+1))==0)){
				
				list[u][0] = li-1;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,(li+1),(co-1))==0)){
				
				list[u][0] = li+1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),(co-1))==0)){
				
				list[u][0] = li-1;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,li,(co+1))==0)){
				
				list[u][0] = li;
				list[u][1] = co+1;
				u++;
			}
			if ((col(plat,li,(co-1))==0)){
				
				list[u][0] = li;
				list[u][1] = co-1;
				u++;
			}
			if ((col(plat,(li-1),co))==0){
				
				list[u][0] = li-1;
				list[u][1] = co;
				u++;
			}
			if ((col(plat,(li+1),co))==0){
				
				list[u][0] = li+1;
				list[u][1] = co;
				u++;
			}
			
			break;
			
			case 6:
			if (col(plat, li, co)==1){
				
				
				if (col(plat, (li-1), co)==0){
					
					list[u][0] = li-1;
					list[u][1] = co;
					u++;
				}
				if (col(plat, (li-1), (co-1))+col(plat, li, co)==3){
				
					list[u][0] = li-1;
					list[u][1] = co-1;
					u++;
				}
				if (col(plat, (li-1), (co+1))+col(plat, li, co)==3){
						
					list[u][0] = li-1;
					list[u][1] = co+1;
					u++;
				}
				if(li==6&&col(plat, (li-1), co)==0&&col(plat, (li-2), co)==0){
					
					list[u][0] = li-2;
					list[u][1] = co;
					u++;
				}
				}
			if (col(plat, li, co)==2){
				
				
				if (col(plat, (li+1), co)==0){
					
					list[u][0] = li+1;
					list[u][1] = co;
					u++;
				}
				if (col(plat, (li+1), (co+1))+col(plat, li, co)==3){
				
					list[u][0] = li+1;
					list[u][1] = co+1;
					u++;
				}
				if (col(plat, (li+1), (co-1))+col(plat, li, co)==3){
						
					list[u][0] = li+1;
					list[u][1] = co-1;
					u++;
				}
				if(li==1&&col(plat, (li+1), co)==0&&col(plat, (li+2), co)==0){
					
					list[u][0] = li+2;
					list[u][1] = co;
					u++;
				}
				}
			
			break;
			
			default:;
		}
		
		return list;
	}
	
	public static boolean pieceBougeable(int plat[][], int li, int co){
		
		int som = 0;
		
		for (int i = 0; i< 30; i++){som = som + listPossi(plat, li, co)[i][0];}//
		
		return (som!=(30*8));
	}
	
	public static int col(int plat[][], int i, int j){
		
		int col = 10;
		if (i<0||i>7||j<0||j>7){col = 10;}
		else{col=(plat[i][j]-(plat[i][j])%10)/10;}
		
		return(col);
	}
	
}

