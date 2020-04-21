import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithms {
	public static Random random = new Random();

	/**
	 * Implémentation de l'algorithme d'Euclide étendu vu en cours.
	 *
	 * @param a int
	 * @param b int
	 * @return Un tableau d'entier : 0 --> pgcd de a et b / 1 et 2 --> coefficients de Bézout u et v tels que a*u+b*v=pgcd(a,b).
	 */
	public static long[] euclideEtendu(long a, long b) {
		return euclideEtenduRecursif(a, b);
	}

	private static long[] euclideEtenduRecursif(long a, long b) {
		long[] result = new long[3];
		if (b == 0) {
			result[0] = a;
			result[1] = 1;
		} else {
			long[] temp = euclideEtenduRecursif(b, a % b);
			result[0] = temp[0];
			result[1] = temp[2];
			result[2] = temp[1] - (a / b) * temp[2];
		}
		return result;
	}

	/**
	 * Affiche le résultat de l'éxecution de l'algorithme d'Euclide étendu donné en paramètre.
	 * @param a      int
	 * @param b      int
	 * @param result Resultat de l'éxecution de l'algorithme d'Euclide étendu définit dans "euclideEtendu" avec les paramètres a et b.
	 */
	public static void printResultEuclideEtendu(long a, long b, long[] result) {
		long resultMult = a * result[1] + b * result[2];
		System.out.println(String.format("Résultat de l'algo euclide étendu. Pgcd de %d et %d : %d. %d*%d + %d*%d = %d.", a, b, result[0], a, result[1], b, result[2], resultMult));
	}


	public static BigInteger[] euclideEtendu(BigInteger a, BigInteger b) {
		return euclideEtenduRecursif(a, b);
	}

	private static BigInteger[] euclideEtenduRecursif(BigInteger a, BigInteger b) {
		BigInteger[] result = new BigInteger[3];
		if (b.equals(BigInteger.ZERO)) {
			result[0] = a;
			result[1] = BigInteger.ONE;
			result[2] = BigInteger.ZERO;
		} else {
			BigInteger[] temp = euclideEtenduRecursif(b, a.mod(b));
			result[0] = temp[0];
			result[1] = temp[2];
			result[2] = temp[1].subtract((a.divide(b)).multiply(temp[2]));
		}
		return result;
	}

	public static void printResultEuclideEtendu(BigInteger a, BigInteger b, BigInteger[] result) {
		BigInteger resultMult = a.multiply(result[1]).add(b.multiply(result[2]));
		System.out.println(String.format("Résultat de l'algo euclide étendu. Pgcd de %d et %d : %d. %d*%d + %d*%d = %d.", a, b, result[0], a, result[1], b, result[2], resultMult));
	}

	/**
	 * Implémentation de l'algorithme de l'exponentiation modulaire : a^k mod n.
	 */
	public static long ExpMod(long a, long k, long n) {
		long p;
		for (p = 1; k > 0; k /= 2) {
			if (k % 2 != 0)
				p = (p * a) % n;
			a = (a * a) % n;
		}
		return p;
	}

	/**
	 * Test de Fermat sur n
	 */
	public static boolean testFermat(int n) {
		//double test=Math.pow(2,n-1);
		double test = ExpMod(2, n - 1, n);
		return test % n == 1;
	}

	/**
	 * Test de primalité naive sur un entier n.
	 */
	public static boolean testPrimaliteNaif(long n) {
		for (long i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Calcule le nombre d'entiers premiers entre 2^i et 2^(i+1)
	 */
	public static int nombrePremiersNaif(int i) {
		int compteur = 0;
		for (int j = (int) Math.pow(2, i); j < (int) Math.pow(2, i + 1); j++) {
			compteur += testPrimaliteNaif(j) ? 1 : 0;
		}
		return compteur;
	}



	/**
	 * Calcule le nombre d'entiers premiers entre 2^i et 2^(i+1) avec le test de Fermat. Pour la méthode naive mais exacte, utiliser nombrePremiersNaif
	 */
	public static int nombrePremiersFermat(int i) {
		int compteur = 0;
		for (int j = (int) Math.pow(2, i); j < (int) Math.pow(2, i + 1); j++) {
			compteur += testFermat(j) ? 1 : 0;
		}
		return compteur;
	}

	public static ArrayList<Integer> diffPremiersFermat(int i) {
		ArrayList<Integer> tab = new ArrayList<>();
		for (int j = (int) Math.pow(2, i); j < (int) Math.pow(2, i + 1); j++) {
			if (testFermat(j) && !testPrimaliteNaif(j))
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
		do {
			int max = (int) Math.pow(2, k) - 1;
			int min = (int) Math.pow(2, k - 1);
			result = random.nextInt(max - min) + min;
		} while (!testFermat(result));
		return result;
	}

	/**
	 * Retourne la factorisation d'un nombre n à partir du nombre d'euler correspondant phi.
	 *
	 * @return Une paire contenant la décomposition de n.
	 */
	public static Pair<Long, Long> phiToFact(long n, long phi) {
		long b= phi-n-1;
		double racine = Math.sqrt(b*b-4*n);
		return new Pair<>((long)(-b-racine)/2,(long) (-b+racine)/2);
	}


	/**
	 * Vérifie que phi(p*q) = (p-1)*(q-1).
	 */
	public static boolean verifPhi(int p, int q) {
		int n = p * q;
		int phi = 0;
		for (int i = 1; i < n; i++) {
			if (euclideEtendu(i, n)[0] == 1) phi++;
		}
		System.out.println(String.format("Verification de phi = %d / (%d-1)(%d-1) = %d",phi,p,q,(p-1)*(q-1)));
		return phi == (p - 1) * (q - 1);
	}


	/**
	 * Méthode naïve pour le calcul de phi(n).
	 */
	public static int calculPhi(long n) {
		int phi = 0;
		for (int j = 0; j < n; j++) {
			if (euclideEtendu(j, n)[0] == 1) phi++;
		}
		return phi;
	}

	public static Pair<Long, Long> verifRSA(long n, long e, long d) {
		long x;
		do {
			x= ThreadLocalRandom.current().nextLong(n*4);
		} while (euclideEtendu(x, n)[0] != 1);
		return new Pair<>(x, ExpMod(x, e * d, n));
	}


	/**
	 * Algorithme non polynomial A1 pour la question 11. Calcul de phi(n) avec une méthode naïve --> non polynomial.
	 */
	public static long a1(long n1,long n2,long n3,long e,long m1,long m2,long m3) {
		int phi1 = Algorithms.calculPhi(n1);
		long d=euclideEtendu(e,phi1)[1];
		if(d<0) d+=phi1;
		return ExpMod(m1,d,n1);
	}

	/**
	 * Algorithme polynomial A2 pour la question 12. Calcul de phi(ni) avec le pgcd.
	 */
	public static long a2(long n1,long n2,long n3,long e,long m1,long m2,long m3){
		long pgcd = euclideEtendu(n1,n2)[0];
		long phi1 = ((n1/pgcd)-1)*(pgcd-1);
		long d=euclideEtendu(e,phi1)[1];
		if(d<0) d+=phi1;
		return ExpMod(m1,d,n1);
	}

	/**
	 * Algorithme polynoiam A3 pour la question 13. Utilisisation du théorème des restes chinois.
	 */
	public static BigInteger a3(long n1,long n2,long n3,long e,long m1,long m2,long m3) {
		//Utilisation des BigInteger pour éviter le dépassement mémoire lors de la multiplication de 3 long
		BigInteger n = new BigInteger(String.valueOf(n1));
		n= n.multiply(BigInteger.valueOf(n2));
		n= n.multiply(BigInteger.valueOf(n3));

		BigInteger nc1 = n.divide(BigInteger.valueOf(n1));
		BigInteger nc2 = n.divide(BigInteger.valueOf(n2));
		BigInteger nc3 = n.divide(BigInteger.valueOf(n3));

		BigInteger e1 = (euclideEtendu(BigInteger.valueOf(n1), nc1)[2]).multiply(nc1);
		BigInteger e2 = (euclideEtendu(BigInteger.valueOf(n2), nc2)[2]).multiply(nc2);
		BigInteger e3 = (euclideEtendu(BigInteger.valueOf(n3), nc3)[2]).multiply(nc3);

		BigInteger m =(e1.multiply(BigInteger.valueOf(m1))).add(e2.multiply(BigInteger.valueOf(m2)).add(e3.multiply(BigInteger.valueOf(m3))));
		return m.mod(n);
	}


	/**
	 * Algorithme polynomial pour la question 14. Teste toutes les possibilités pour m puisque m est de la forme 100010100, 1000100 ou 100000. Complexité en temps : O(n^3). Complexité mémoire : O(n).
	 */
	public static long a4(long n1,long n2,long n3,long e,long m1,long m2,long m3) {
		long n = Math.min(Math.min(n1,n2),n3);

		char[] binaryString = Long.toBinaryString(n).toCharArray();

		int tempInt;
		for (int j=1;j<=binaryString.length;j++){
			int[] binaryTab = new int[j];
			binaryTab[binaryTab.length-1]=1;
			for(int i=0;i<j-1;i++){
				binaryTab[i]=1;
				for(int k=i;k<j-1;k++){
					binaryTab[k]=1;
					tempInt= getIntFromBit(binaryTab);
					if(euclideEtendu(tempInt,n1)[0]==1 && euclideEtendu(tempInt,n2)[0]==1 && euclideEtendu(tempInt,n3)[0]==1){
						if(ExpMod(tempInt,e,n1)==m1 && ExpMod(tempInt,e,n2)==m2 && ExpMod(tempInt,e,n3)==m3){
							return tempInt;
						}
					}
					if(k!=i)
						binaryTab[k]=0;
				}
				binaryTab[i]=0;
			}
		}
		return 0;
	}


	private static int getIntFromBit(int[] bitTab){
		int res=0;
		for(int i=0;i<bitTab.length;i++){
			if(bitTab[i]==1)res+=Math.pow(2,i);
		}
		return res;
	}

	public static int numberOfOnes(long n){
		String s = Long.toBinaryString(n);
		int compteur = 0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='1')compteur++;
		}
		return compteur;
	}


	public static BigDecimal NthRoot(BigInteger num, int n)
	{
		ArrayList<BigDecimal> arr = new ArrayList<BigDecimal>();
		Integer i = 1;
		Integer j = 0;
		BigDecimal[] tArray = new BigDecimal[5];
		BigDecimal N = new BigDecimal(n);
		BigDecimal A = new BigDecimal(num);
		arr.add(0,A);

		while(true)
		{
			tArray[0] = (arr.get(i-1).multiply(new BigDecimal(n-1)));
			tArray[1] = ((arr.get(i-1)).pow(n-1));
			tArray[2] = A.divide(tArray[1],6, RoundingMode.FLOOR);
			tArray[3] = tArray[0].add(tArray[2]);
			tArray[4] = tArray[3].divide(N,6,RoundingMode.FLOOR);

			arr.add(i,tArray[4]);
			i = i + 1;

			if ((arr.get(i-1)).compareTo(arr.get(i-2)) == 0)
			{
				break;
			}
		}

		return arr.get(i-1);
	}
	}
