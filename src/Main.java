import java.util.Random;

public class Main {
	public static void main(String[] args) {

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
		int borneNbPremiers = 10;
		for(int i=0;i<=borneNbPremiers;i++){
			int borneInf = (int)Math.pow(2,i);
			int borneSup = (int)Math.pow(2,i+1);
			int nombrePremiersResultat = Algorithms.nombrePremiersNaif(i);
			int nombrePermiersFermat = Algorithms.nombrePremiersFermat(i);
			System.out.println(String.format("Nombre de premiers pour i=%d (entre %d et %d) : %d",i,borneInf,borneSup,nombrePremiersResultat));
			System.out.println(String.format("Proportion de nombres premiers : %d %%",nombrePremiersResultat*100/(borneSup-borneInf)));
			System.out.println(String.format("Nombre de premiers pour i=%d avec Fermat (entre %d et %d) : %d",i,borneInf,borneSup,nombrePermiersFermat));
			System.out.println(String.format("Nombre de non premiers détectés avec Fermat : %d",nombrePermiersFermat-nombrePremiersResultat));
			System.out.println(String.format("Taux d'erreur de Fermat : %f %%",(double)((nombrePermiersFermat-nombrePremiersResultat)*100)/(borneSup-borneInf)));
			System.out.println("Faux positifs : "+Algorithms.diffPremiersFermat(i));
			System.out.println("\n");
		}

	}

}
