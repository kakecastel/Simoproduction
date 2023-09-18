package CodingGame;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Coding {
    int count,value;
    Coding left,right;
    long coin2 = 0, bill5 = 0, bill10 = 0;


public void Salmon() {
    count = 4;
         }
    public static int findLargest(int[] a) {

        if(a==null)
            return -1;
        int max = a[0];
        for (int i : a)
            if (max < i)
                max = i;
        return max;
    }
    public static int[] findSumPair(int[] numbers, int k) {

        int[] pairs = new int[2];
        if (numbers==null)
            return pairs;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == k) {
                    // if a pair is found, you could stop looking anymore
                    pairs = new int[] { i, j };

                    // unreachable statement
                    if (numbers[i] + numbers[j] != k) {
                        pairs = new int[] { 0, 0 };
                    }
                }
            }
        }
        return pairs;
    }
    public static List<String> nbPersons(List<String> ing, int k) {

        List<String> sortie=new ArrayList<>();
        if(ing==null)
            return sortie;
        for (String a : ing){
            StringBuilder buils=new StringBuilder();
            buils.append(String.valueOf(Integer.valueOf(a.split(" ")[0])*k)+" ");
            for (int i = 1; i < a.split(" ").length; i++) {
                buils.append(a.split(" ")[i]+" ");
            }
            sortie.add(buils.toString());
        }
        return sortie;
    }
    public static int computeSizeOnDisk(int  clusterSize, int fileSize) {
        if (clusterSize < 1 || clusterSize > 65536)
            return -1;
        if(fileSize < 0 || fileSize > 1000000)
            return -1;
        int val=(fileSize/clusterSize);
        if((fileSize % clusterSize) >0)
            val++;
            return clusterSize*val;
    }
    public static boolean isValidSudoku(char[][] board) {
        // check rows/cols
        HashSet<Character> rowSet = new HashSet<Character>();
        HashSet<Character> colSet = new HashSet<Character>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                // check row char
                char rowChar = board[i][j];
                if(rowSet.contains(rowChar)) return false;
                if(rowChar != '.') rowSet.add(rowChar);

                // check col char
                char colChar = board[j][i];
                if(colSet.contains(colChar)) return false;
                if(colChar != '.') colSet.add(colChar);
            }
            rowSet.clear();
            colSet.clear();
        }

        //check sub-boxes
        int rowMin = 0;
        int rowMax = 2;
        int colMin = 0;
        int colMax = 2;
        for(int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++) {
                for(int i = rowMin; i <= rowMax; i++) {
                    for(int j = colMin; j <= colMax; j++) {
                        char c = board[i][j];
                        if(rowSet.contains(c)) return false;
                        if(c != '.') rowSet.add(c);
                    }
                }
                rowSet.clear();
                colMin += 3;
                colMax += 3;
            }
            rowMin += 3;
            rowMax += 3;
            colMin = 0;
            colMax = 2;
        }
        return true;
    }
    public static int isValidSudoku(int [][] board) {
        // check rows/cols
        HashSet<Integer> rowSet = new HashSet<Integer>();
        HashSet<Integer> colSet = new HashSet<Integer>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                // check row char
                int rowChar = board[i][j];
                if(rowSet.contains(rowChar)) return 0;
                if(rowChar != 0) rowSet.add(rowChar);

                // check col char
                int colChar = board[j][i];
                if(colSet.contains(colChar)) return 0;
                if(colChar != 0) colSet.add(colChar);
            }
            rowSet.clear();
            colSet.clear();
        }

        //check sub-boxes
        int rowMin = 0;
        int rowMax = 2;
        int colMin = 0;
        int colMax = 2;
        for(int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++) {
                for(int i = rowMin; i <= rowMax; i++) {
                    for(int j = colMin; j <= colMax; j++) {
                        int c = board[i][j];
                        if(rowSet.contains(c)) return 0;
                        if(c != 0) rowSet.add(c);
                    }
                }
                rowSet.clear();
                colMin += 3;
                colMax += 3;
            }
            rowMin += 3;
            rowMax += 3;
            colMin = 0;
            colMax = 2;
        }
        return 1;
    }
    public static String addDrama(String val) {

        if(val==null)
            return "chaine vide";

            StringBuilder buils=new StringBuilder();
            int fin=val.length()-1;
            for (int i = 0; i < val.length(); i++) {
                if(i< fin)
                    if(val.charAt(i)==val.charAt(i+1))
                        buils.append("");
                else
                        buils.append(val.charAt(i));
            }
            buils.append(val.charAt(val.length()-1));

        return buils.toString().replace(".","!");
    }

    public static int[] boundingRectangle(int [] l1,int [] r1,int [] l2,int [] r2) {
        int [] retour={};

        // if rectangle has area 0, no overlap
        if (l1[0] == r1[0] || l1[1] == r1[1] || r2[0] == l2[0] || l2[1] == r2[1])

            return retour;

        // If one rectangle is on left side of other

        if (l1[0] > r2[0] || l2[0] > r1[0])

            return retour;


        // If one rectangle is above other


        if (r1[1] > l2[1] || r2[1] > l1[1])

            return retour;




        return new int[]{l1[0], l2[1],l1[0]-l2[0],l1[1]-l2[1]};
    }
    public static String compute( int bites ) {

        long bytes=new Long(bites);
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((bytes >= 0) && (bytes < kilobyte)) {
            return bytes + " B";

        } else if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return (bytes / kilobyte) + " KB";

        } else if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return (bytes / megabyte) + " MB";

        } else if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return (bytes / gigabyte) + " GB";

        } else if (bytes >= terabyte) {
            return (bytes / terabyte) + " TB";

        } else {
            return bytes + " Bytes";
        }
    }//spell
    public static StringBuilder work(StringBuilder a, StringBuilder b) {
        a = new StringBuilder("a");
        b.append("c");
        return a;
    }
    public static List<Integer> spell(List<Integer> liste) {

        List<Integer> retour=new ArrayList<>();
        if(liste.size()>200)
            return retour;
        Collections.reverse(liste);
        for (Integer elt : liste){
            if (elt % 2==0)
                retour.add(elt);
        }
        return retour;
    }
    public static List<String> functionLabel(int pi,int ni,int pa,int na) {
        List<String> retour=new ArrayList<>();
        if (pi<0 || pa< 0 || pi >=30 || pa>=30 ||ni<0 || na<0 || ni>=20 || na>=20)
            return retour;
        while(pi > pa || ni >na) {
            pi -= 2;
            ni -= 2;
            retour.add("ALPHA");
        }
        while(pi < pa) {
            pi += 1;
            retour.add("PROTON");
        }
        while(ni < na) {
            ni += 1;
            retour.add("NEUTRON");
        }

        return retour;
    }
    public synchronized static int Counterincrement() {
    int count =1;
    return count; }

    public Coding find(int v) {
        Coding current = this;
        while (current != null) {
            if (current.value == v) {
                return current;
            }
            // This will drop out of the loop naturally if there's no appropriate subnode
            current = v < current.value ? current.left : current.right;
        }
        return null;
    }
    public static int sumRange(int[] ints) {
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            int n = ints[i];
            if (n >= 10 && n <= 100)
                sum += n;
        }
        return sum;
    }
    public static int calc(int[] ints, int n1, int n2){
        int sum = 0;
        if (n1 < 0 || n2<n1 || ints.length < n2 )
            return sum;

            for (int i = 0; i < ints.length; i++) {
                int n = ints[i];
                if (n >= n1 && n <= n2)
                    sum += n;
            }
            return sum;

    }

    /**
     *
     * nombre de paire construire
     */
    public static int count(int n){
        if(n<2||n>10000)
            return -1;
        int i=1,count=0;
       while (i<n){
           count+=n-i;
           i++;
       }
        return count;

    }
    static boolean a(int i, int j) {
        if(i==1||j==1||i+j==1)
            return true;
        return false;

    }

    static boolean check(String str){
         if(str==null || str.isEmpty()||str.length() >10000)
                return false;
         else {
             if (str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']')
                 return true;
             else if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
                 return true;
             } else
                 return false;
         }
    }

    public static boolean isTwin(String a, String b) {
        char [] str1=a.toCharArray();
        char[] str2= b.toCharArray();
        if(str1.length != str2.length)
            return false;
        Arrays.sort(str1);
        Arrays.sort(str2);

        for(int i = 0;i <str1.length; i++)
            if (str1[i] != str2[i])
                return false;

        return true;
    }
    public static int getPositionAt( int iterationNumber )
    {   if(iterationNumber < 0 || iterationNumber > 2147483647 )
        return -2147483647;
        int position = 0;
        int step1 = 1;
        position = position + step1;
        iterationNumber = iterationNumber - 1;
        int step2 = -2;
        position = position + step2;
        iterationNumber -= 1;
        while( iterationNumber > 0 )
        {
            position = position + ( step2 - step1 );
            int buffer = step1;
            step1 = step2;
            step2 = step2 - buffer;
            iterationNumber -= 1;
        }
        return position;
    }
    public static String concat(String [] strings) {
       String sortie="";
       if(strings==null)
           return sortie;
       for(String elt : strings)
           sortie+=elt;
        return sortie;
    }

    public static int closestToZero(int [] a) {
        int currentValue = 0;
        int closestVal = a[0];
        Arrays.sort(a);

        for (int index = 0; index < a.length; index++) {
            currentValue = a[index] * a[index];
            if (currentValue <= (closestVal * closestVal)) {
                closestVal = a[index];
            }
        }
        return closestVal;
    }
    static Coding c = new Coding();
    public static Coding optimalChange(long s) {

        if (s < 2) {
            return s == 0 ? c : null;
        } else if (s < 5) {
            c.coin2++;
            return optimalChange(s - 2);
        } else if (s < 10) {
            c.bill5++;
            return optimalChange(s - 5);
        } else {
            c.bill10++;
            return optimalChange(s - 10);
        }

    }

    public static int computeMultiplesSum(int n) {
        int summ = 0;
        if(n>0 || n>=1000)
            return -1;
        for (int  i = 3; i <= n; i++){
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0){
                summ += i;
            }
        }
        return summ;

    }

    public static boolean isValidSudokus(char[][] board) {
        HashSet<Character> rowSet = new HashSet();
        HashSet<Character> colSet = new HashSet();

        int i;
        int rowMax;
        int colMin;
        int colChar;
        for(i = 0; i < 9; ++i) {
            for(rowMax = 0; rowMax < 9; ++rowMax) {
                colMin = board[i][rowMax];
                if (rowSet.contains(Character.valueOf((char)colMin))) {
                    return false;
                }

                if (colMin != 46) {
                    rowSet.add(Character.valueOf((char)colMin));
                }

                colChar = board[rowMax][i];
                if (colSet.contains(Character.valueOf((char)colChar))) {
                    return false;
                }

                if (colChar != 46) {
                    colSet.add(Character.valueOf((char)colChar));
                }
            }

            rowSet.clear();
            colSet.clear();
        }

        i = 0;
        rowMax = 2;
        colMin = 0;
        colChar = 2;

        for(int a = 0; a < 3; ++a) {
            for(int b = 0; b < 3; ++b) {
                for(int k = i; k <= rowMax; ++k) {
                    for(int j = colMin; j <= colChar; ++j) {
                        char c = board[k][j];
                        if (rowSet.contains(c)) {
                            return false;
                        }

                        if (c != '.') {
                            rowSet.add(c);
                        }
                    }
                }

                rowSet.clear();
                colMin += 3;
                colChar += 3;
            }

            i += 3;
            rowMax += 3;
            colMin = 0;
            colChar = 2;
        }

        return true;
    }

    public static int isValidSudokus(int[][] board) {
        HashSet<Integer> rowSet = new HashSet();
        HashSet<Integer> colSet = new HashSet();

        int i;
        int rowMax;
        int colMin;
        int colChar;
        for(i = 0; i < 9; ++i) {
            for(rowMax = 0; rowMax < 9; ++rowMax) {
                colMin = board[i][rowMax];
                if (rowSet.contains(colMin)) {
                    return 0;
                }

                if (colMin != 0) {
                    rowSet.add(colMin);
                }

                colChar = board[rowMax][i];
                if (colSet.contains(colChar)) {
                    return 0;
                }

                if (colChar != 0) {
                    colSet.add(colChar);
                }
            }

            rowSet.clear();
            colSet.clear();
        }

        i = 0;
        rowMax = 2;
        colMin = 0;
        colChar = 2;

        for(int a = 0; a < 3; ++a) {
            for(int b = 0; b < 3; ++b) {
                for(int k = i; k <= rowMax; ++k) {
                    for(int j = colMin; j <= colChar; ++j) {
                        int c = board[k][j];
                        if (rowSet.contains(c)) {
                            return 0;
                        }

                        if (c != 0) {
                            rowSet.add(c);
                        }
                    }
                }

                rowSet.clear();
                colMin += 3;
                colChar += 3;
            }

            i += 3;
            rowMax += 3;
            colMin = 0;
            colChar = 2;
        }

        return 1;
    }

    public static int computeDayGains(int nbSeats, int[] payingGuests, int[] guestMovements) {
        int availableSeats = nbSeats;
        ArrayList<Integer> guestSitting = new ArrayList();
        ArrayList<Integer> waitingList = new ArrayList();
        ArrayList<Integer> alreadyEntered = new ArrayList();
        int dayGains = 0;
        int[] var8 = guestMovements;
        int var9 = guestMovements.length;

        for(int var10 = 0; var10 < var9; ++var10) {
            int id = var8[var10];
            if (!guestSitting.contains(id) && availableSeats > 0) {
                if (!alreadyEntered.contains(id)) {
                    dayGains += payingGuests[id];
                }

                guestSitting.add(id);
                alreadyEntered.add(id);
                --availableSeats;
            } else if (guestSitting.contains(id)) {
                ++availableSeats;
                guestSitting.remove(guestSitting.indexOf(id));
            } else if (availableSeats == 0) {
                waitingList.add(id);
            }
        }

        return dayGains;
    }

    public static int findSmallestInterval(int[] n) {
        //int summ = false;
        ArrayList<Integer> garde = new ArrayList();
        int[] var3 = n;
        int var4 = n.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int elt = var3[var5];

            for(int i = 0; i < n.length; ++i) {
                if (elt - n[i] > 0) {
                    garde.add(elt - n[i]);
                }
            }
        }

        return (Integer)Collections.min(garde);
    }

    public static int findSmallestIntervale(int[] numbers) {
        Integer NO_RANGE_FOUND = -1;
        if (numbers == null) {
            return NO_RANGE_FOUND;
        } else if (numbers.length >= 2 && numbers.length <= 100000) {
            Arrays.sort(numbers);
            int diff = Integer.MAX_VALUE;
            int minRange = Integer.MAX_VALUE;

            for(int i = 0; i < numbers.length - 1; ++i) {
                diff = numbers[i + 1] - numbers[i];
                if (diff == 0) {
                    return diff;
                }

                if (minRange > diff) {
                    minRange = diff;
                }
            }

            return minRange;
        } else {
            return NO_RANGE_FOUND;
        }
    }


    public static int calculateTotalPrice1(int[] prices, int discount) {
        if (prices == null) {
            return -1;
        }
        else if (prices.length >= 0 && prices.length <= 100000) {
            if (discount >= 0 && discount <= 100) {
                int total = 0;
                int len = prices.length;
                Arrays.sort(prices);
                for(int i = 0; i < len - 1; ++i) {
                    total += prices[i];
                }
                return (int)Math.floor((double)((float)total + ((float)prices[len - 1] - (float)(prices[len - 1] * discount / 100))));
            } else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }
    public static int luckyMoney(int money, int giftees) {
       // int NO_RANGE_FOUND = true;
        if (giftees < 10 && giftees > 0) {
            if (money < 100 && money >= 0) {
                if (money == 4) {
                    return -1;
                } else if (money >= giftees * 8) {
                    return giftees;
                } else {
                    return money >= 8 + giftees - 1 && money != 12 ? 1 + luckyMoney(money - 8, giftees - 1) : 0;
                }
            } else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }
    public static List<Integer> maxProfit(List<Integer> prices) {
        int profit = 0;
        int gardeint = 0;

        for(int i = 1; i < prices.size(); ++i) {
            int diff = (Integer)prices.get(i) - (Integer)prices.get(i - 1);
            System.out.println("val diff  et les i et i-1  " + diff + "  " + i);
            if (diff > 0) {
                gardeint = i;
                profit += diff;
            }
        }
        ArrayList<Integer> sortie = new ArrayList();
        sortie.add(gardeint);
        sortie.add(profit);
        return sortie;
    }

    public static int maxScore(int [] prices) {
        int som = 0;
         if( prices==null)
             return -1;
         else if (prices.length%2 !=0) {
             return -1;
         }
         else {
             int i = prices.length - 1;
             while (i > 0) {
                 som += prices[i];
                 i = i - 2;
             }
         }
         return som;
    }

    public static int[] solve(int imageWidth, int imageHeight, int[][] image, int patternWidth, int patternHeight, int[][] pattern) {
        int[] result = {-1, -1};
        if ((patternWidth >= 1) && (patternWidth <= 400) &&
                (patternHeight >= 1) && (patternHeight <= 400) &&
                (imageWidth >= 1) && (imageWidth <= 400) &&
                (imageHeight >= 1) && (imageHeight <= 400) &&
                (patternWidth <= imageWidth) && (patternHeight <= imageHeight)) {
            int img_x, img_y, pattern_x, pattern_y;
            boolean pattern_not_found, pattern_is_matching;
            pattern_not_found = true;
            for (img_x = 0; (pattern_not_found && (img_x <= (imageWidth - patternWidth))); img_x++) {
                for (img_y = 0; (pattern_not_found && (img_y <= (imageHeight - patternHeight))); img_y++) {
                    pattern_is_matching = true;
                    for (pattern_x = 0; (pattern_is_matching && (pattern_x < patternWidth)); ++pattern_x) {
                        for (pattern_y = 0; (pattern_is_matching && (pattern_y < patternHeight)); ++pattern_y) {
                            if (image[img_x + pattern_x][img_y + pattern_y] != pattern[pattern_x][pattern_y]) {
                                pattern_is_matching = false;
                            }
                        }
                    }
                    if (pattern_is_matching) {
                        result[0] = img_x;
                        result[1] = img_y;
                        pattern_not_found = false;
                        break;
                    }
                }
                if (!pattern_not_found) {
                    break;
                }
            }
        }
        return result;
    }




    public static String encode(String text) {
        if(text==null)
            return "";
        if (text.isEmpty())
            return "";
        StringBuilder list=new StringBuilder();
        int position=0;
        int k=0;
        while(position< text.length()){
            int count=1;
            while (position<text.length()-1 && text.charAt(position)==text.charAt(position+1)){
                    count++;
                    position++;
                }
              list.append(String.valueOf(count));
              list.append(String.valueOf(text.charAt(position)));
              position++;
            }
        return list.toString();
    }
    public static int next(int N) {
        int smallestNumber = 0;
        if (N <= 1)
            return smallestNumber;

        int numberOfDigitsOriginalN = String.valueOf(N).length();
        System.out.println("la valeur en string    " + numberOfDigitsOriginalN);
        while (N > 0) {
            N--;
            if (String.valueOf(N).length() ==
                    (numberOfDigitsOriginalN -1)) {
                return ++N;
            }
        }
        return smallestNumber;
    }
    public static int smallest(int N) {
        int numberOfDigits = (int) String.valueOf(Math.abs(N)).length();
        System.out.println("la valeur en numberOfDigits    " + String.valueOf(N).split("").toString());
        if (N >= 0) {
            if (numberOfDigits == 1) {
                return 0;
            } else {
                return (int) Math.pow(10, numberOfDigits - 1);
            }
        } else
            return 1 - (int) Math.pow(10, numberOfDigits);
    }
    public static  List<Character>  nextBiggerNumber(long n) {
        //--permet de transformer un int en list de caractere
        List<Character> text = new ArrayList<>(String.valueOf(n).chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        return text;
    }

    public static int Next(int n) {
        int newNumber = -1;
        List<Character> startChars = new ArrayList<>();
        String nString = Integer.toString(n);
        for (char c : nString.toCharArray()) {
            startChars.add(c);
        }
        List<Character> newChars = new ArrayList<>();
        List<Character> commonChars = null;
        while (commonChars == null || !commonChars.isEmpty()) {
            if (n < Math.pow(2, 31) || n > 0) {
                newNumber = n + 1;
                newChars.clear();
                String newNumberString = Integer.toString(newNumber);
                for (char c : newNumberString.toCharArray()) {
                    newChars.add(c);
                }
                commonChars = new ArrayList<>(startChars);
                commonChars.retainAll(newChars);
                n++;
            }
        }
        return newNumber;
    }
    static String Calculator_sum(String... numbers) {
        float total = 0;
        List<Double> list=new ArrayList<>();
        for (String number : numbers)
            {   System.out.println("valeur de numbers   : " + Double.parseDouble(number));
                //list.add(Double.parseDouble(number));
                total = total + Float.parseFloat(number); }
            return String.valueOf(total);
    }

    static String rebuildMessage(String[] chaine) {
        StringBuilder sortie=new StringBuilder();
        if(chaine==null)
            return "";
        if (chaine[0].charAt(0)!='A' ||chaine[chaine.length-1].charAt(chaine[chaine.length-1].length()-1)!='Z')
        return "";
        for(int i=1;i<chaine.length;i++){
            String boite="";
           if(chaine[i-1].charAt(chaine[i-1].length()-1)==chaine[i].charAt(0)) {
               for (int j = 0; j < chaine[i - 1].toCharArray().length - 1; j++)
                   boite += chaine[i - 1].toCharArray()[j];
               sortie.append(boite);
           }
           else
               sortie.append(chaine[i-1]);
        }
        sortie.append(chaine[chaine.length-1]);
        return sortie.toString();

    }
  // static String followPath(List<String> liste )
    //public static void main(String[] args) { int valuel = 3; valeur int2 = 1 ; valeur int3 = 4 ; int computingValue = calculer (valuel, value2, value3); System.out.println (computedValue); }
    static String compute(int premierOp, int deuxiemeOp,int calcResult ) {
        String sortie="";
       if(calcResult==premierOp+deuxiemeOp)
           return "ok";
       else {
           String sum=String.valueOf(premierOp+deuxiemeOp);
           String calcResultString=String.valueOf(calcResult);
           int j=0;
           while(j < sum.length()){
               if(sum.charAt(j)!=calcResultString.charAt(j))
                   sortie=String.valueOf(j);
               j++;
           }
           return String.valueOf(sortie);
       }

    }
    static String followPath(List<String> listString ) {
        String sortie="";
        for(String elt : listString){
            for (int i = 0; i < elt.length(); i++)
            {
                char c = elt.charAt(i);
                if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {

                }
                else
                    sortie+=String.valueOf(c);

            }
        }
        return sortie;
    }

    public static int magic(List<Integer> list){
       // List<Integer> list = Arrays.stream(tab).boxed().collect(Collectors.toList());
        Collections.sort(list);
        int i = 0;
        while( i < list.size()-1){
            if(list.get(i)==list.get(i+1)){
                list.remove(i+1);
                int a = list.remove(i);
                list.add(a+1);
                Collections.sort(list);
                i=0;
            }
            else
                i++;

        }
        return list.size();
    }

    public static void main(String[] args) {
        //String numbers = "412345678";

        String aa="abcd";
        String boite="";
        for(int i=0;i<aa.toCharArray().length-1;i++)
            boite+=aa.toCharArray()[i];
        System.out.println("voila la chaine de valeur   : " + boite);
        List<Integer> testeprices=new ArrayList<>();
        testeprices.add(100);
        testeprices.add(100);
        testeprices.add(500);
        testeprices.add(400);
        testeprices.add(700);

        List<String> testString=new ArrayList<>();
        testString.add("***");
        testString.add("*");
        testString.add("*");
        testString.add("**c**od**i**");
        testString.add("*");
        testString.add("*");
        testString.add("e");
        testString.add("*");
        testString.add("n");
        testString.add("**m*a*g***");

        List<Integer> valeursortie=maxProfit(testeprices);
        for(Integer val : valeursortie)
            System.out.println("prix et index: " + val);

        Coding m = optimalChange(19L);

        if(m == null){
            System.out.println("no change possible ...");
            return;
        }
        String [] tabDeString={ "Ab","bcZ"};
        System.out.println("Coin  2E: " + m.coin2);
        System.out.println("bill  5E: " + m.bill5);
        System.out.println("bill 10E: " + m.bill10);

        long result = m.coin2 * 2 + m.bill5 * 5 + m.bill10 * 10;
        int[] num = null ;//{1,9,10,5,6,4};
        List<Character> text=nextBiggerNumber(654321);
        for(Character c : text)
            System.out.println("la valeur de chaque caractere        " + c);
        int[] data = {2,3,-2,5,-5};
        int[] array = {10,-5,5,2,7,-4,28,65,95,85,12,45};
        String [] listees={"f", "o", "o", "bar"};
       System.out.println("la voila la valeur de stroing de retour         "+followPath(testString));
       List<Integer> test=new ArrayList<>();
        List<String> teste=new ArrayList<>();
        teste=functionLabel(29,3,8,4);
        List<Integer> retour=new ArrayList<>();
       test.add(1);test.add(2);test.add(3);test.add(4);test.add(5);test.add(6);
       retour=spell(test);
       for (String a :teste)
           System.out.println("la valeur en sortie est   = " + a);
        StringBuilder s1 = new StringBuilder("s1");
        StringBuilder s2 = new StringBuilder("s2");
        StringBuilder s3 = work(s1, s2);
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s3 = " + s3);

        //System.out.println(numbers.substring(2,1));
        StringBuilder b = new StringBuilder("rumble");
        b.append(4).deleteCharAt(3).delete(3, b.length() - 1);
        System.out.println(b);
        //System.out.println(numbers.substring(7, 7));
        //System.out.println(numbers.substring(7));
        int [] [] board = {
                {1, 4, 7, 7, 0, 9, 0, 0, 3},
                {2, 5, 0, 6, 0, 1, 0, 0, 0},
                {3, 0, 9, 5, 0, 0, 0, 0, 0},
                {0, 8, 0, 4, 2, 0, 0, 0, 4},
                {0, 0, 0, 3, 1, 0, 0, 2, 0},
                {9, 0, 0, 2, 0, 0, 6, 0, 0},
                {0, 0, 3, 2, 0, 0, 0, 0, 9},
                {4, 0, 0, 1, 0, 2, 0, 0, 0},
                {0, 0, 1, 9, 0, 8, 0, 0, 7},
        };
        int[] arr = { 10,11,12};
        int i1 = 1_2_4;
        int x = 5;
        for(int i=0; i<10 ;i++) {
            //i = i++;
          //  System.out.println("Hello World");
        }
        do {
            int y = 1;
            x++;
            System.out.print(y++ + " ");
            } while(x <= 10);
           // System.out.println(x > 4 ? x > 2 ? 10 : 8 : 7);
            //d/ouble d1 = 1_234_.0;
         //double d2 = 1_234._0;
          //double d3 = 1_234.0_;
        String s = "purr";
        String a= new String("tete");
        a=a.substring(1,2);
        int[] random = { 6, -4, 12, 0, -10 };
        List<String> hex = Arrays.asList("30", "8", "3A", "FF");
        Collections.sort(hex);
        System.out.println("liste sorted  "+hex);
        int xx = Collections.binarySearch(hex, "8");
        int y = Collections.binarySearch(hex, "3A");
        int z = Collections.binarySearch(hex, "4F");
        System.out.println(xx+ " " + y + " " + z);
        List<String> listes=new ArrayList<>();

        LocalDate date = LocalDate.parse("2018-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
        date.plusDays(2);
        //date.plus(3);
        System.out.println(date.getYear() + " " + date.getMonth() + " "
                + date.getDayOfMonth());

        listes=null;
        String valueur="je suis!!!!!!! dans ! dieux!!.";

        //System.out.println("la valeur des elements en bytes    "+compute(1999999999));
       // listes.add("2 eggs");  listes.add("200 grams of flour");  listes.add("150 grams of sugar");  listes.add("1 liter(s) of milk");
        System.out.println("la valeur de string      "+addDrama(valueur));
        System.out.println("la valeur de la somme      "+calc(arr,2,4));
       // for (int i = 0; i < nbPersons(listes,3).size(); i++) {
           // System.out.println("la valeur du tableau    "+nbPersons(listes,3).get(i));
      //  }
    }
}
