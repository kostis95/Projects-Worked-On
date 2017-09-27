
public class KnuthMorrisPratt {
	private String pat;
	private int[][] dfa;
	private int komtAantalKeerVoor = 0;
	private int match = 0;
	
	public int getAantal() {
		return komtAantalKeerVoor;
	}

	
	public KnuthMorrisPratt(String pat){ // Build DFA from pattern.
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < M; j++){ // Compute dfa[][j].
			for (int c = 0; c < R; c++){
				dfa[c][j] = dfa[c][X]; // Copy mismatch cases.
				dfa[pat.charAt(j)][j] = j+1; // Set match case.
				X = dfa[pat.charAt(j)][X]; // Update restart state.
			}
		}
	}
	
	//origineel
//	public int search(String txt)
//	{ // Simulate operation of DFA on txt.
//	int i, j, N = txt.length();
//	for (i = 0, j = 0; i < N && j < M; i++)
//	j = dfa[txt.charAt(i)][j];
//	if (j == M) return i - M; // found
//	else return N; // not found
//	}
	
	
	public int search(String txt) { // Simulate operation of DFA on txt.
		int i, j, N = txt.length(), M = pat.length();
		int komtAantalKeerVoor = 0;
		for (i = 0, j = 0; i < N && j < M; i++) { 
		     int a = dfa[txt.charAt(i)][j]; 
		     if(a > 0){
		         komtAantalKeerVoor ++;
		     }
		}
		return komtAantalKeerVoor;
	}
	
	


	
}



