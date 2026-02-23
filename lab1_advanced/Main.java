import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        Scanner scanIntrare = new Scanner(System.in);
        int dimensiune = scanIntrare.nextInt();

        int maxLatime = 0;
        int maxAdancime = 0;
        int minLatime = dimensiune;
        int minAdancime = dimensiune;
        int[][] matrice = new int[dimensiune][dimensiune];

        for(int i = 0; i < dimensiune; i++){
            for(int j = 0; j < dimensiune; j++){
                matrice[i][j] = scanIntrare.nextInt();
            }
        }

        for(int i = 0; i < dimensiune; i++){
            for(int j = 0; j < dimensiune; j++){
                if(matrice[i][j] == 1){
                    if(i < minAdancime){
                        minAdancime = i;
                    }
                    if(i > maxAdancime){
                        maxAdancime = i;
                    }
                    if(j < minLatime){
                        minLatime = j;
                    }
                    if(j > maxLatime){
                        maxLatime = j;
                    }
                }
            }

        }

        System.out.println(minLatime);
        System.out.println(maxLatime);
        System.out.println(minAdancime);
        System.out.println(maxAdancime);

    }
}
