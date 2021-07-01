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

// 7. almostIncreasingSequence

// 8. matrixElementsSum





