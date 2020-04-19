import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class Algorithms {
	public static Random random =  new Random();

	/**
	 * Implémentation de l'algorithme d'Euclide étendu vu en cours.
	 * @param a int
	 * @param b int
	 * @return Un tableau d'entier : 0 --> pgcd de a et b / 1 et 2 --> coefficients de Bézout u et v tels que a*u+b*v=pgcd(a,b).
	 */
	public static int[] euclideEtendu(int a,int b){
		return euclideEtenduRecursif(a,b);
	}

	private static int[] euclideEtenduRecursif(int a,int b) {
		int[] result = new int[3];
		if (b == 0) {
			result[0] = a;
			result[1] = 1;
		}
		else{
			int[] temp = euclideEtenduRecursif(b,a%b);
			result[0]=temp[0];
			result[1]=temp[2];
			result[2]=temp[1]-(a/b)*temp[2];
		}
		return result;
	}

	/**
	 * Affiche le résultat de l'éxecution de l'algorithme d'Euclide étendu donné en paramètre.
	 * @param a int
	 * @param b int
	 * @param result Resultat de l'éxecution de l'algorithme d'Euclide étendu définit dans "euclideEtendu" avec les paramètres a et b.
	 */
	public static void printResultEuclideEtendu(int a,int b,int[] result){
		int resultMult=a*result[1]+b*result[2];
		System.out.println(String.format("Résultat de l'algo euclide étendu. Pgcd de %d et %d : %d. %d*%d + %d*%d = %d.",a,b,result[0],a,result[1],b,result[2],resultMult));
	}


	/**
	 * Implémentation de l'algorithme de l'exponentiation modulaire : a^k mod n.
	 */
	public static long ExpMod(long a, long k, long n) {
		long p;
		for (p=1; k>0; k/=2) {
			if(k%2 != 0)
				p = (p*a)%n ;
			a = (a*a)%n;
		}
		return p;
	}

	/**
	 * Test de Fermat sur n
	 */
	public static boolean testFermat(int n){
		//double test=Math.pow(2,n-1);
		double test =  ExpMod(2,n-1,n);
		return test%n==1;
	}

	/**
	 * Test de primalité naive sur un entier n.
	 */
	public static boolean testPrimaliteNaif(int n){
		for(int i=2;i<=Math.sqrt(n);i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Calcule le nombre d'entiers premiers entre 2^i et 2^(i+1)
	 */
	public static int nombrePremiersNaif(int i){
		int compteur = 0;
		for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1);j++){
			compteur+=testPrimaliteNaif(j)?1:0;
		}
		return compteur;
	}

	/**
	 * Calcule le nombre d'entiers premiers entre 2^i et 2^(i+1) avec le test de Fermat. Pour la méthode naive mais exacte, utiliser nombrePremiersNaif
	 */
	public static int nombrePremiersFermat(int i){
		int compteur = 0;
		for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1);j++){
			compteur+=testFermat(j)?1:0;
		}
		return compteur;
	}

	public static ArrayList<Integer> diffPremiersFermat(int i){
		ArrayList<Integer> tab = new ArrayList<>();
		for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1);j++){
			if(testFermat(j) && !testPrimaliteNaif(j))
				tab.add(j);
		}
		return tab;
	}


	/**
	 * @param k Nombre de bits
	 * @return Un nombre entier de k bits premier (vérifié en utilisant le test de Fermat)
	 */
	public static int genPremiers(int k) {
		int result;
		do{
			int max = (int)Math.pow(2,k)-1;
			int min = (int)Math.pow(2,k-1);
			result = random.nextInt(max-min)+min;
		}while(!testFermat(result));
		return result;
	}

	public static Pair<Double,Double> phiToFact(int n,int phi){
		int x = n+1-phi;
		int delta = (int) (Math.pow(x,2)-4*n);
		double p = (-x-Math.sqrt(delta))/-2;
		double q = (-x+Math.sqrt(delta))/-2;
		return new Pair<>(p, q);
	}

}
