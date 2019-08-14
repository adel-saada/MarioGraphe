package jeu;

import java.io.*;

class Saisie
 {
	
	
	
	
	/************************************************************************
	 * **********************************************************************
	 * @return
	 */	
 static char readChar()
   {
    boolean erreur;
    String s;

    do {
          erreur=false;
          s=readString();
          if(s.length()!=1)
            {
             System.out.println("Taper 1 caractere, puis entree");
             erreur=true;
            }
         }while(erreur);
    return s.charAt(0);
   }
/************************************************************************
 * **********************************************************************
 * @return
 */
  static int readInt()
   {
    boolean erreur;
    int n=0;

    do {
          erreur=false;
          try {
                n=Integer.parseInt(readString());
               }
         catch(NumberFormatException e)
              {
               System.out.println("Erreur de saisie, recommencez");
              erreur=true;
              }
        }while(erreur);
    return n;
   }
  
  /************************************************************************
   * **********************************************************************
   * @return
   */
  static double readDouble()
   {
    boolean erreur;
    double nd=0.0;

    do {
          erreur=false;
          try {
        	  	nd= Double.parseDouble(readString());
               // n=new Double(readString()).doubleValue();
               }
         catch(NumberFormatException e)
              {
               System.out.println("Erreur de saisie, recommencez");
              erreur=true;
              }
        }while(erreur);
    return nd;
   }
  
  
  
  /************************************************************************
   * **********************************************************************
   * @return
   */
 static String readString()
  {
	 
	 String ligne_lue= null;
	 try
	 {
	 InputStreamReader lecteur = new InputStreamReader(System.in);
	 BufferedReader entree = new BufferedReader(lecteur);
	 ligne_lue= entree.readLine();
	 }
	 catch(IOException ex)
	 {
	 System.exit(0);
	 }
	 
	 
	 return ligne_lue; 
	 
  } 
 }
