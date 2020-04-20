import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Main {
	public static void main(String[] args) {
		final long nanoToSecond = (long) Math.pow(10,9);
		Random random = new Random();
		/*int a=120;
		int b=12;
		int[] tab= Algorithms.euclideEtendu(a,b);
		Algorithms.printResultEuclideEtendu(a,b,tab);

		int p = Algorithms.ExpMod(4,13,497);
		System.out.println("Résultat de l'exp modulaire de "+4+"^"+13+" mod "+497+" = "+p);

		Random r = new Random();
		int borne = 65536;
		for(int i=0;i<100;i++){
			int alea = r.nextInt(borne)+borne;
			boolean test = Algorithms.testPrimaliteNaif(alea);
			if(test) {
				System.out.println("Résultat du test de Fermat sur " + alea + " : " + Algorithms.testFermat(alea));
				System.out.println("Résultat du test de primalité naïve sur " + alea + " : " + Algorithms.testPrimaliteNaif(alea) + "\n");
			}
		}
*/
		/*
		int borneNbPremiers = 25;
		for(int i=0;i<=borneNbPremiers;i++){
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
			System.out.println("Faux positifs : "+Algorithms.diffPremiersFermat(i));
			System.out.println("\n");
		}*/

		//Question 7 : GenPremiers
		/*int k=25;
		int generatedInt = Algorithms.genPremiers(k);
		System.out.println("Premier généré : "+generatedInt);
		System.out.println("Avec la méthode naive, ce nombre est "+ (Algorithms.testPrimaliteNaif(generatedInt)?"premier":"non premier"));
	*/
		//Question 8 : PhiToFact
		/*int n = 35;
		int phi = 24;
		Pair<Integer, Integer> phiToFact = Algorithms.phiToFact(n,phi);
		System.out.println(String.format("Resultat de PhiToFact avec n=%d et phi(n)=%d --> %d*%d=%d",n,phi,phiToFact.getKey(),phiToFact.getValue(),phiToFact.getKey()*phiToFact.getValue()));

		//Question 9 : VerifPhi
		int p=7;
		int q=5;
		System.out.println(String.format("Resultat de verifPhi avec p=%d et q=%d (n=%d) : %b",p,q,p*q,Algorithms.verifPhi(p,q)));*/

		//Question 10 : verifRSA
		/*Random random = new Random();
		int e;
		int[] res;
		do{
			e = random.nextInt(n);
			res = Algorithms.euclideEtendu(e,phi);
		} while (res[0]!=1 || res[1]<2);
		int d=res[1];
		Pair<Integer, Long> pair=Algorithms.verifRSA(n,e,d);
		System.out.println(String.format("Verification de la question 10. e*d mod phi = %d * %d mod %d = %d",e,d,phi,(e*d)%phi));
		System.out.println(String.format("Résultat : %d",pair.getValue()));
		System.out.println(String.format("Verification : x(=%d) mod n(=%d) = %d",pair.getKey(),n,pair.getKey()%n));*/

		//Question 11 : A1
		int k=10;
		System.out.println("Résultat de la question 11");
		int p1 = Algorithms.genPremiers(k);
		int p2 = Algorithms.genPremiers(k);
		int p3 = Algorithms.genPremiers(k);
		int p4 = Algorithms.genPremiers(k);
		int p5 = Algorithms.genPremiers(k);
		int p6 = Algorithms.genPremiers(k);
		System.out.println(String.format("Paramètres : p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d", p1, p2, p3, p4, p5, p6));

		int n1 = p1 * p2;
		int n2 = p3 * p4;
		int n3 = p5 * p6;

		int n = Math.min(Math.min(n1, n2), n3);
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));

		int phi1 = (p1-1)*(p2-1);
		int phi2 = (p3-1)*(p4-1);
		int phi3 = (p5-1)*(p6-1);

		int m;
		do {
			m = random.nextInt(n - 2) + 1;
		} while (Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		int e;
		do {
			e = random.nextInt(n - 2) + 1;
		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println("e=" + e);

		int m1 = (int) Algorithms.ExpMod(m, e, n1);
		int m2 = (int) Algorithms.ExpMod(m, e, n2);
		int m3 = (int) Algorithms.ExpMod(m, e, n3);
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
			m = random.nextInt(n - 2) + 1;
		} while (Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		do {
			e = random.nextInt(n - 2) + 1;
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
				m = random.nextInt(n - 2) + 1;
			} while (Algorithms.euclideEtendu(m, n1)[0] != 1 || Algorithms.euclideEtendu(m, n2)[0] != 1 || Algorithms.euclideEtendu(m, n3)[0] != 1);

		} while (Algorithms.euclideEtendu(e, phi1)[0] != 1 || Algorithms.euclideEtendu(e, phi2)[0] != 1 || Algorithms.euclideEtendu(e, phi3)[0] != 1);
		System.out.println(String.format("Paramètres : p1=%d, p2=%d, p3=%d, p4=%d, p5=%d, p6=%d", p1, p2, p3, p4, p5, p6));
		System.out.println(String.format("n1=%d, n2=%d, n3=%d --> n=%d", n1, n2, n3, n));
		/*Algorithms.printResultEuclideEtendu(n1,n2,Algorithms.euclideEtendu(n1, n2));
		Algorithms.printResultEuclideEtendu(n2,n3,Algorithms.euclideEtendu(n2, n3));
		Algorithms.printResultEuclideEtendu(n1,n3,Algorithms.euclideEtendu(n1, n3));*/

		System.out.println("e=" + e);

		m1 = (int) Algorithms.ExpMod(m, e, n1);
		m2 = (int) Algorithms.ExpMod(m, e, n2);
		m3 = (int) Algorithms.ExpMod(m, e, n3);
		System.out.println(String.format("M1=%d, M2=%d, M3=%d", m1, m2, m3));

		long res = Algorithms.a3(n1,n2,n3,e,m1,m2,m3);
		System.out.println("m=" + m);
		System.out.println("A3 --> m = "+((long)Math.pow(res,1.0/3)+1));

	}

}
