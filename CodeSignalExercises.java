// Here I will show the codesignal exercises I have made recently

// 2. centuryFromYear
// Given a year, return the century it is in. 
// The first century spans from the year 1 up to and including the year 100, the second - from the year 101 up to and including the year 200, etc. 

int centuryFromYear(int year) {

    if (year % 100 == 0){
        year = year - 1;
    }
      
    String stringYear = Integer.toString(year);
     if(stringYear.length() == 3 ){
        stringYear = stringYear.substring(0,1);
    } else if(stringYear.length() > 3){
        stringYear = stringYear.substring(0,2);
    } else {
        return 1;
    }
    
  return Integer.parseInt(stringYear) + 1;
}

// 3. checkPalindrome
// Given the string, checks if it is a palindrome.

boolean checkPalindrome(String inputString) {

    for (int i = 0; i < inputString.length(); i++){
        char string1 = inputString.charAt(i);
        char string2 = inputString.charAt(inputString.length()-i-1);
            if(string1 != string2){
                return false;
            }
        }
    return true;
}

// 4. adjacentElementsProduct
// Given an array of integers, find the pair of adjacent elements that has the largest product and return that product.
int adjacentElementsProduct(int[] inputArray) {
    int highestProduct = inputArray[0] * inputArray[1];
    for (int i=1; i < (inputArray.length - 1); i++){
        int newProduct = inputArray[i] * inputArray[i+1];
        if(newProduct > highestProduct){
            highestProduct = newProduct;
        }
    }
    return highestProduct;
}

// 5. shapeAre
// The task is to find the area of a polygon for a given n. A 1-interesting polygon is just a square with a side of length 1.
// An n-interesting polygon is obtained by taking the n - 1-interesting polygon and appending 1-interesting polygons to its rim, side by side. 
int shapeArea(int n) {
    return ((int) Math.pow(n, 2) + (int)Math.pow(n-1, 2));
}

// 6. Make Array Consecutive 2
// Ratiorg got statues of different sizes as a present from CodeMaster for his birthday, each statue having an non-negative integer size. 
// Since he likes to make things perfect, he wants to arrange them from smallest to largest so that each statue will be bigger than the previous one exactly by 1. 
// He may need some additional statues to be able to accomplish that. Help him figure out the minimum number of additional statues needed.
int makeArrayConsecutive2(int[] statues) {

    int highest = 0;
    int lowest = statues[0];

    for (int i=0; i < statues.length; i++){
        if (statues[i] > highest){
            highest = statues[i];
        }
        if (statues[i] < lowest){
            lowest = statues[i];
        }
    }
    return highest - lowest - (statues.length - 1);
}

// 8. matrixElementsSum
// After becoming famous, the CodeBots decided to move into a new building together. Each of the rooms has a different cost, and some of them are free,
// but there's a rumour that all the free rooms are haunted! Since the CodeBots are quite superstitious, they refuse to stay in any of the free rooms,
// or any of the rooms below any of the free rooms. Given matrix, a rectangular matrix of integers, where each value represents the cost of the room,
// your task is to return the total sum of all rooms that are suitable for the CodeBots.
int matrixElementsSum(int[][] matrix) {
    int totalrooms = 0;
    boolean[] hasGhosts = new boolean[matrix[0].length];

  
    for (int i = 0; i < matrix.length; i++){
        for (int j = 0; j < matrix[i].length; j++) {
            if (hasGhosts[j] == true){
            continue;
            }
            if(matrix[i][j] == 0){
            hasGhosts[j] = true;
            }
            totalrooms += matrix[i][j];
        }  
    }
    return totalrooms;
}

// 9. All Longest Strings
// Given an array of strings, return another array containing all of its longest strings.
String[] allLongestStrings(String[] inputArray) {
    
    int longestLength = 0;
    int numberOfLongestStrings = 0;
    for (int i = 0; i < inputArray.length; i++){
        
        if (inputArray[i].length() > longestLength){
            longestLength = inputArray[i].length();
            numberOfLongestStrings = 0;
        };
        if (inputArray[i].length() == longestLength){
                    numberOfLongestStrings += 1;
        }
    }
    
    String[] longestStringsArray = new String[numberOfLongestStrings];
    int count = 0;
    for (int i = 0; i < inputArray.length; i++){
        if (inputArray[i].length() == longestLength){
            longestStringsArray[count] = inputArray[i];
            count ++;
        }
    }
    return longestStringsArray;
}

// 11. isLucky
// Ticket numbers usually consist of an even number of digits. A ticket number is considered lucky if the sum of the first half of the digits is equal to the sum of the second half.
// Given a ticket number n, determine if it's lucky or not.
boolean isLucky(int n) {

    String stringInteger = String.valueOf(n);
    int intSize = stringInteger.length();
    int halfSize = intSize / 2;
    int firstHalfSize = 0;
    int secondHalfSize = 0;
    char[] digits = stringInteger.toCharArray();
    
    for (int i = 0; i < halfSize; i++){
        firstHalfSize += digits[i];
    }
    for (int i = halfSize; i < intSize; i++){
        secondHalfSize += digits[i];
    }
    if (firstHalfSize == secondHalfSize){
        return true;
    }
    return false;
}

// 14. alternatingSums
// Several people are standing in a row and need to be divided into two teams. The first person goes into team 1, the second goes into team 2, 
// the third goes into team 1 again, the fourth into team 2, and so on. You are given an array of positive integers - the weights of the people. 
// Return an array of two integers, where the first element is the total weight of team 1, and the second element is the total weight of team 2 after the division is complete.
int[] alternatingSums(int[] a) {

    int[] sums = new int [2];
    int firstHalf = 0;
    int secondHalf = 0;

    for (int i = 0; i < a.length; i++){
        if (i % 2 == 0){
            firstHalf += a[i];
        } else {
            secondHalf += a[i];
        }
    }

    sums[0] = firstHalf;
    sums[1] = secondHalf;
    return sums;
}

// 15. Add Border
// Given a rectangular matrix of characters, add a border of asterisks(*) to it.
String[] addBorder(String[] picture) {

    int newLength = picture.length + 2;
    String[] newPicture = new String[newLength];

    for (int i = 0; i < picture.length; i++){
        newPicture[i + 1] = "*" + picture[i] + "*";
    }

    newPicture[0] =  newPicture[1].replaceAll(".","*");
    newPicture[newLength - 1] = newPicture[newLength - 2].replaceAll(".","*");
    
    return newPicture;
}

// 16. Are Similar?
// Two arrays are called similar if one can be obtained from another by swapping at most one pair of elements in one of the arrays.
// Given two arrays a and b, check whether they are similar.
boolean areSimilar(int[] a, int[] b) {

    int missOneA = 0;
    int missOneB = 0;
    int missTwoA = 0;
    int missTwoB = 0;

    for (int i = 0; i < a.length; i++){
        if (a[i] != b[i]){
            if (missOneA == 0){
                missOneA = a[i];
                missOneB = b[i];
            }
            else if (missTwoA == 0){
                missTwoA = a[i];
                missTwoB = b[i];
            } else {
                return false;
            }
        }
    }

    if (missOneA == missTwoB && missOneB == missTwoA){
        return true;
    } else {
        return false;
    }
}




