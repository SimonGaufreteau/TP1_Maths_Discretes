import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {
		final long nanoToSecond = (long) Math.pow(10,9);
		System.out.println("\nRésultat de la question 1");
		int a=ThreadLocalRandom.current().nextInt();
		int b=ThreadLocalRandom.current().nextInt();
		long[] tab= Algorithms.euclideEtendu(a,b);
		Algorithms.printResultEuclideEtendu(a,b,tab);

		System.out.println("\nRésultat de la question 2");
		long resultat = Algorithms.ExpMod(4,13,497);
		System.out.println("Résultat de l'exp modulaire de "+4+"^"+13+" mod "+497+" = "+resultat);

		System.out.println("\nRésultat de la question 3");
		Random r = new Random();
		int borne1 = 65536;
		for(int i=0;i<100;i++){
			int alea = r.nextInt(borne1)+borne1;
			boolean test = Algorithms.testPrimaliteNaif(alea);
			if(test) {
				System.out.println("Résultat du test de Fermat sur " + alea + " : " + Algorithms.testFermat(alea));
				System.out.println("Résultat du test de primalité naïve sur " + alea + " : " + true + "\n");
			}
		}

		System.out.println("\nRésultat de la question 4 à 6");

		int borneNbPremiers = 20;
		for(int i=1;i<=borneNbPremiers;i++){
			System.out.print("----- i = "+i+" -----\n");
			int borneInf = (int)Math.pow(2,i);
			int borneSup = (int)Math.pow(2,i+1);
			long time = System.nanoTime();
			int nombrePremiersResultat = Algorithms.nombrePremiersNaif(i);
			long tempsNaif = System.nanoTime()-time;
			System.out.println("Temps d'éxecution méthode naive : "+(double)tempsNaif/nanoToSecond+" s");
			time = System.nanoTime();
			int nombrePermiersFermat = Algorithms.nombrePremiersFermat(i);
			long tempsFermat = System.nanoTime()-time;
			System.out.println("Temps d'éxecution méthode Fermat : "+(double)tempsFermat/nanoToSecond+" s");
			System.out.println("Différence de temps d'éxecution : "+(double)(tempsNaif-tempsFermat)/nanoToSecond+" s");
			System.out.println(String.format("Nombre de premiers pour i=%d (entre %d et %d) : %d",i,borneInf,borneSup,nombrePremiersResultat));
			System.out.println(String.format("Proportion de nombres premiers : %f %%",	(double)nombrePremiersResultat*100/(borneSup-borneInf)));
			System.out.println(String.format("Nombre de premiers pour i=%d avec Fermat (entre %d et %d) : %d",i,borneInf,borneSup,nombrePermiersFermat));
			System.out.println(String.format("Nombre de non premiers détectés avec Fermat : %d",nombrePermiersFermat-nombrePremiersResultat));
			System.out.println(String.format("Taux d'erreur de Fermat : %f %%",(double)((nombrePermiersFermat-nombrePremiersResultat)*100)/(borneSup-borneInf)));
			//System.out.println("Faux positifs : "+Algorithms.diffPremiersFermat(i));
			System.out.println("\n");
		}

		//Question 7 : GenPremiers
		System.out.println("\nRésultat de la question 7");
		int k=25;
		long generatedInt = Algorithms.genPremiers(k);
		System.out.println("Premier généré : "+generatedInt);
		System.out.println("Avec la méthode naive, ce nombre est "+ (Algorithms.testPrimaliteNaif(generatedInt)?"premier":"non premier"));

		//Question 8 : PhiToFact
		System.out.println("\nRésultat de la question 8");
		k=15;
		int p=Algorithms.genPremiers(k);
		int q=Algorithms.genPremiers(k);
		long n = p*q;
		long phi = (p-1)*(q-1);
		Pair<Long, Long> phiToFact = Algorithms.phiToFact(n,phi);
		System.out.println(String.format("Resultat de PhiToFact avec n=%d et phi(n)=%d --> %d*%d=%d",n,phi,phiToFact.getKey(),phiToFact.getValue(),phiToFact.getKey()*phiToFact.getValue()));

		//Question 9 : VerifPhi
		System.out.println("\nRésultat de la question 9");
		k=10;
		p = Algorithms.genPremiers(k);
		q = Algorithms.genPremiers(k);
		System.out.println(String.format("Resultat de verifPhi avec p=%d et q=%d (n=%d) : %b",p,q,p*q,Algorithms.verifPhi(p,q)));

		//Question 10 : verifRSA
		System.out.println("\nRésultat de la question 10");

		long e;
		long[] res;
		k=10;
		//int borne=Math.abs(random.nextInt());
		int premierEntier = Algorithms.genPremiers(k);
		int deuxiemeEntier = Algorithms.genPremiers(k);
		long borne = premierEntier*deuxiemeEntier;
		phi = (premierEntier-1)*(deuxiemeEntier-1);
		//Algorithms.verifPhi(premierEntier,deuxiemeEntier);
		System.out.println("n="+borne);
		do{
			e= ThreadLocalRandom.current().nextLong(borne);
			res = Algorithms.euclideEtendu(e,phi);
		} while (res[0]!=1 || res[1]<2);
		long d=res[1];
		Pair<Long, Long> pair=Algorithms.verifRSA(borne,e,d);
		System.out.println(String.format("Verification de la question 10. e*d mod phi = %d * %d mod %d = %d",e,d,phi,(e*d)%phi));
		System.out.println(String.format("Résultat : x^(e*d) mod n = %d",pair.getValue()));
		System.out.println(String.format("Verification : x(=%d) mod n(=%d) = %d",pair.getKey(),borne,pair.getKey()%borne));

		//Question 11 : A1
		k=15;
		System.out.println("\nRésultat de la question 11");
		int p1 = Algorithms.genPremiers(k);
		int p2 = Algorithms.genPremiers(k);
		int p3 = Algorithms.genPremiers(k);
		int p4 = Algorithms.genPremiers(k);
		int p5 = Algorithms.genPremiers(k);
		int p6 = Algorithms.genPremiers(k);
		System.out.println(String.format("Paramètres : p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d", p1, p2, p3, p4, p5, p6));

		long n1 = p1 * p2;
		long n2 = p3 * p4;
		long n3 = p5 * p6;

		n = Math.min(Math.min(n1, n2), n3);
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		long phi1 = (p1-1)*(p2-1);
		long phi2 = (p3-1)*(p4-1);
		long phi3 = (p5-1)*(p6-1);

		long m;
		do {
			m = ThreadLocalRandom.current().nextLong(n-1);
		} while (Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		do {
			e = ThreadLocalRandom.current().nextLong(n-1);
		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println("e=" + e);

		long m1 = Algorithms.ExpMod(m, e, n1);
		long m2 = Algorithms.ExpMod(m, e, n2);
		long m3 = Algorithms.ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));

		System.out.println("m=" + m);
		System.out.println(String.format("A1 --> m = %d",Algorithms.a1(n1,n2,n3,e,m1,m2,m3)));


		//Question 12
		System.out.println("\nRésultat de la question 12");
		p1 = Algorithms.genPremiers(k);
		p2 = Algorithms.genPremiers(k);
		p4 = Algorithms.genPremiers(k);
		p6 = Algorithms.genPremiers(k);
		System.out.println(String.format("Paramètres : p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d", p1, p2, p1, p4, p1, p6));

		n1 = p1 * p2;
		n2 = p1 * p4;
		n3 = p1 * p6;

		n = Math.min(Math.min(n1, n2), n3);
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		phi1 = (p1-1)*(p2-1);
		phi2 = (p1-1)*(p4-1);
		phi3 = (p1-1)*(p6-1);

		do {
			m = ThreadLocalRandom.current().nextLong(n-1);
		} while (Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		do {
			e = ThreadLocalRandom.current().nextLong(n-1);
		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println("e=" + e);

		m1 = (int) Algorithms.ExpMod(m, e, n1);
		m2 = (int) Algorithms.ExpMod(m, e, n2);
		m3 = (int) Algorithms.ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));
		System.out.println("m=" + m);
		System.out.println("A2 --> m = "+Algorithms.a2(n1,n2,n3,e,m1,m2,m3));


		//Question 13
		System.out.println("\nRésultat de la question 13");

		e=3;
		do {
			do{
				p1 = Algorithms.genPremiers(k);
				p2 = Algorithms.genPremiers(k);
				p3 = Algorithms.genPremiers(k);
				p4 = Algorithms.genPremiers(k);
				p5 = Algorithms.genPremiers(k);
				p6 = Algorithms.genPremiers(k);
				n1 = p1 * p2;
				n2 = p3 * p4;
				n3 = p5 * p6;
			}while(Algorithms.euclideEtendu(n1, n2)[0] != 1 || Algorithms.euclideEtendu(n2, n3)[0] != 1 || Algorithms.euclideEtendu(n1, n3)[0] != 1);

			n = Math.min(Math.min(n1, n2), n3);
			phi1 = (p1-1)*(p2-1);
			phi2 = (p3-1)*(p4-1);
			phi3 = (p5-1)*(p6-1);

			do {
				m = ThreadLocalRandom.current().nextLong(n-1);
			} while (Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println(String.format("Paramètres : p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d", p1, p2, p3, p4, p5, p6));
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		System.out.println("e=" + e);

		m1 = Algorithms.ExpMod(m, e, n1);
		m2 = Algorithms.ExpMod(m, e, n2);
		m3 = Algorithms.ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));

		BigInteger resBig = Algorithms.a3(n1,n2,n3,e,m1,m2,m3);
		System.out.println("A3 --> m = "+Algorithms.NthRoot(resBig,3).longValue());
		System.out.println("m=" + m);


		// Question 14
		System.out.println("\nRésultat de la question 14");
		p1 = Algorithms.genPremiers(k);
		p2 = Algorithms.genPremiers(k);
		p3 = Algorithms.genPremiers(k);
		p4 = Algorithms.genPremiers(k);
		p5 = Algorithms.genPremiers(k);
		p6 = Algorithms.genPremiers(k);
		System.out.println(String.format("Paramètres : p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d", p1, p2, p3, p4, p5, p6));

		n1 = p1 * p2;
		n2 = p3 * p4;
		n3 = p5 * p6;

		n = Math.min(Math.min(n1, n2), n3);
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		phi1 = (p1-1)*(p2-1);
		phi2 = (p3-1)*(p4-1);
		phi3 = (p5-1)*(p6-1);

		do {
			m = ThreadLocalRandom.current().nextLong(n-1);
		} while (Algorithms.numberOfOnes(m)>3 || Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		do {
			e = ThreadLocalRandom.current().nextLong(n-1);
		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println("e=" + e);

		m1 = Algorithms.ExpMod(m, e, n1);
		m2 = Algorithms.ExpMod(m, e, n2);
		m3 = Algorithms.ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));

		System.out.println(String.format("A4 --> m = %d",Algorithms.a4(n1,n2,n3,e,m1,m2,m3)));
		System.out.println("m=" + m +", nombre de 1 non-nuls = "+Algorithms.numberOfOnes(m));

	}

}
