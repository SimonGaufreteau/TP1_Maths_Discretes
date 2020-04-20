import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class Algorithms {
	public static Random random = new Random();

	/**
	 * Implémentation de l'algorithme d'Euclide étendu vu en cours.
	 *
	 * @param a int
	 * @param b int
	 * @return Un tableau d'entier : 0 --> pgcd de a et b / 1 et 2 --> coefficients de Bézout u et v tels que a*u+b*v=pgcd(a,b).
	 */
	public static int[] euclideEtendu(int a, int b) {
		return euclideEtenduRecursif(a, b);
	}

	private static int[] euclideEtenduRecursif(int a, int b) {
		int[] result = new int[3];
		if (b == 0) {
			result[0] = a;
			result[1] = 1;
		} else {
			int[] temp = euclideEtenduRecursif(b, a % b);
			result[0] = temp[0];
			result[1] = temp[2];
			result[2] = temp[1] - (a / b) * temp[2];
		}
		return result;
	}

	/**
	 * Affiche le résultat de l'éxecution de l'algorithme d'Euclide étendu donné en paramètre.
	 *
	 * @param a      int
	 * @param b      int
	 * @param result Resultat de l'éxecution de l'algorithme d'Euclide étendu définit dans "euclideEtendu" avec les paramètres a et b.
	 */
	public static void printResultEuclideEtendu(int a, int b, int[] result) {
		int resultMult = a * result[1] + b * result[2];
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
	public static boolean testPrimaliteNaif(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
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
	public static Pair<Integer, Integer> phiToFact(int n, int phi) {
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (i * j == n && (i - 1) * (j - 1) == phi) return new Pair<>(i, j);
			}
		}
		return new Pair<>(1, n);
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
		System.out.println(phi);
		return phi == (p - 1) * (q - 1);
	}


	/**
	 * Méthode naïve pour le calcul de phi(n).
	 */
	public static int calculPhi(int n) {
		int phi = 0;
		for (int j = 0; j < n; j++) {
			if (euclideEtendu(j, n)[0] == 1) phi++;
		}
		return phi;
	}

	//TODO : Explication de la question 10 (voir papier)

	public static Pair<Integer, Long> verifRSA(int n, int e, int d) {
		int x;
		do {
			x = random.nextInt(n * n);
		} while (euclideEtendu(x, n)[0] != 1);
		return new Pair<>(x, ExpMod(x, e * d, n));
	}


	/**
	 * Algorithme non polynomial A1 pour la question 11. Calcul de phi(n) avec une méthode naïve --> non polynomial.
	 */
	public static int a1(int n1,int n2,int n3) {
		int n = Math.min(Math.min(n1, n2), n3);
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		int phi1 = Algorithms.calculPhi(n1);
		System.out.println("phi1=" + phi1);
		int phi2 = Algorithms.calculPhi(n2);
		System.out.println("phi2=" + phi2);
		int phi3 = Algorithms.calculPhi(n3);
		System.out.println("phi3=" + phi3);

		int e;
		do {
			e = random.nextInt(n - 2) + 1;
		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println("e=" + e);

		int m;
		do {
			m = random.nextInt(n - 2) + 1;
		} while (euclideEtendu(m, n1)[0] != 1 || euclideEtendu(m, n2)[0] != 1 || euclideEtendu(m, n3)[0] != 1);

		long m1 = ExpMod(m, e, n1);
		long m2 = ExpMod(m, e, n2);
		long m3 = ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));

		return m;
	}

	/**
	 * ALgotithme polynomial A2 pour la question 12. Calcul de phi(ni) avec le pgcd.
	 */
	public static int a2(int n1,int n2,int n3){
		int n = Math.min(Math.min(n1, n2), n3);
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		int pgcd = euclideEtendu(n1,n2)[0];
		System.out.println("pgcd = " + pgcd);

		int phi1 = ((n1/pgcd)-1)*(pgcd-1);
		System.out.println("phi1=" + phi1);
		int phi2 = ((n2/pgcd)-1)*(pgcd-1);
		System.out.println("phi2=" + phi2);
		int phi3 = ((n3/pgcd)-1)*(pgcd-1);
		System.out.println("phi3=" + phi3);


		// On utilise la même méthode de calcul pour e, m et les Mi
		int e;
		do {
			e = random.nextInt(n - 2) + 1;
		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println("e=" + e);

		int m;
		do {
			m = random.nextInt(n - 2) + 1;
		} while (euclideEtendu(m, n1)[0] != 1 || euclideEtendu(m, n2)[0] != 1 || euclideEtendu(m, n3)[0] != 1);

		long m1 = ExpMod(m, e, n1);
		long m2 = ExpMod(m, e, n2);
		long m3 = ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));

		return m;
	}
}
