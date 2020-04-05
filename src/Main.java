import java.util.Random;
import java.util.Timer;

public class Main {
	public static void main(String[] args) {
		final long nanoToSecond = (long) Math.pow(10,9);
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
		}

	}

}
