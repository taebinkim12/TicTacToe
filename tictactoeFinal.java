package BeforeUni;
import java.util.*;

public class tictactoeFinal {
	private static char board[][]={
			{' ', ' ', ' '},
			{' ', ' ', ' '},
			{' ', ' ', ' '}
	};

	private static char user = 'O';
	private static char computer = 'X';
	private static int numTurns = 0;

	public static void main(String[] args) {
		System.out.println("This is how spot number will work");
		System.out.println("[1] [2] [3]");
		System.out.println("[4] [5] [6]");
		System.out.println("[7] [8] [9]");
		
		System.out.println("You will get O and I(the bot) will get X as a marker");
		Random random = new Random();
		int coin = random.nextInt(2);
		System.out.println("Deciding who goes first...");
		System.out.println("");
		
		if(coin==0) {
			System.out.println("You start first!");
			while(numTurns<=9){
				userturn();
				aiturn();
			}
		}else if(coin==1) {
			System.out.println("I will go first");
			while(numTurns<=9){
				aiturn();
				userturn();
		}
		}
		
		if(isWinner()==true){
			System.out.println("Game over");
		}else{
			System.out.println("tie and Game over");
		}
	}

	public static int[] NumtoCoor(int input){
		if(input==1){
			return new int[] {0,0};
		}else if(input==2){
			return new int[] {0,1};
		}else if(input==3){
			return new int [] {0,2};
		}else if(input==4){
			return new int [] {1,0};   
		}else if(input==5){
			return new int [] {1,1};
		}else if(input==6){
			return new int [] {1,2};
		}else if(input==7){
			return new int [] {2,0};
		}else if(input==8){
			return new int[] {2,1};
		}else if(input==9){
			return new int[] {2,2};
		}
		return new int[]{-1, -1};
	}   

	public static int coortonum(int coor[]) {
		int num = 0;
		if(coor[0]==0&&coor[1]==0) {
			num=1;
		}else if(coor[0]==0&&coor[1]==1) {
			num=2;
		}else if(coor[0]==0&&coor[1]==2) {
			num=3;
		}else if(coor[0]==1&&coor[1]==0) {
			num=4;
		}else if(coor[0]==1&&coor[1]==1) {
			num=5;
		}else if(coor[0]==1&&coor[1]==2) {
			num=6;
		}else if(coor[0]==2&&coor[1]==0) {
			num=7;
		}else if(coor[0]==2&&coor[1]==1) {
			num=8;
		}else if(coor[0]==2&&coor[1]==2) {
			num=9;
		}
		return num;
	}

	public static boolean isWinner() {
		int horcounter = 0;
		int vercounter = 0;
		int diacounter = 0;

		for(int i=0;i<=2;i++){
			for(int j=0; j<=2; j++){
				if(board[i][j]=='O'){
					horcounter++;
				}else if(board[i][j]=='X'){
					horcounter--;
				}
			}
			if(horcounter==3 || horcounter==-3){
				return true;
			}else {
				horcounter=0;
			}
		}

		for(int a=0; a<=2;a++){
			for(int b=0; b<=2; b++){
				if(board[b][a]=='O'){
					vercounter++;
				}else if(board[b][a]=='X'){
					vercounter--;
				}
			}
			if(vercounter==3 || vercounter==-3){
				return true;
			}else {
				vercounter=0;
			}
		}

		if(board[0][0]=='O'&& board[1][1]=='O'&&board[2][2]=='O') {
			return true;
		}else if(board[0][0]=='X'&& board[1][1]=='X'&&board[2][2]=='X') {
			return true;
		}else if(board[0][2]=='O'&& board[1][1]=='O'&&board[2][0]=='O') {
			return true;
		}else if(board[0][2]=='X'&& board[1][1]=='X'&&board[2][0]=='X') {
			return true;
		}
		return false;
	} //method

	public static void printboard (){
		System.out.println("[" + board[0][0] + "] [" + board[0][1] + "] [" + board[0][2]+ "]");
		System.out.println("[" + board[1][0] + "] [" + board[1][1] + "] [" + board[1][2]+ "]");
		System.out.println("[" + board[2][0] + "] [" + board[2][1] + "] [" + board[2][2]+ "]");
		System.out.println(" ");
	}

	public static boolean isValid(int input) {

		int coor[]=NumtoCoor(input);
		int row = coor[0];
		int col = coor[1];

		if(row==-1) {
			return false;
		}
		
		if(board[row][col]==' ') {
			return true;
		}else {
			return false;
		}
	}

	public static void userturn(){
		numTurns++;

		if(numTurns<=9 && isWinner()==false){
			Scanner scan = new Scanner(System.in);
			System.out.println("your number?");
			int num=scan.nextInt();
			//int coor[]=NumtoCoor(num);
			//int row = coor[0];
			//int col = coor[1];
			
			if(isValid(num)==false) {
			while(isValid(num)==false) {
				System.out.println("try again");
				num=scan.nextInt();
			}
			}

			if(isValid(num)==true) {
				int coor[]=NumtoCoor(num);
				int row = coor[0];
				int col = coor[1];
				board[row][col]= user;
				printboard();
			}
		}



	}

	public static void aiturn() {
		numTurns++;
		if(numTurns<=9&&isWinner()==false){
			System.out.println("AI Turn");
			int num=AI();
			int coor[] = NumtoCoor(AI());

			int row = coor[0];
			int col = coor[1];

			if(isValid(num)==true) {
				board[row][col]= computer;
			}else if(isValid(num)==false) {
				System.out.println("oops");
			}
			printboard();
		}
	}

	public static int[] cangameover(){
		int vercounter=0;
		int horcounter=0;
		int diacounter1=0;
		int diacounter2=0;
		int rowmove=0;
		int colmove=0;
		for(int i=0;i<=2;i++){
			for(int j=0;j<=2;j++){
				if(board[i][j]=='O'){
					horcounter++;
				}else if(board[i][j]=='X'){
					horcounter--;
				}else if(board[i][j]==' '){
					rowmove=i;
					colmove=j;
				}
			}
			if(horcounter==2 || horcounter==-2){
				return new int[] {rowmove,colmove};
			}else{
				horcounter=0;
			}
		}


		for(int a=0; a<=2;a++){
			for(int b=0; b<=2; b++){
				if(board[b][a]=='O'){
					vercounter++;
				}else if(board[b][a]=='X'){
					vercounter--;
				}else if(board[b][a]==' '){
					rowmove=b;
					colmove=a;
				}
			}
			if(vercounter==2 || vercounter==-2){
				return new int[] {rowmove, colmove};
			}else{
				vercounter=0;
			}
		}

		for(int c=0; c<=2; c++){
			if(board[c][c]=='O'){
				diacounter1++;
			}else if(board[c][c]=='X'){
				diacounter1--;
			}else if(board[c][c]==' '){
				rowmove=c;
				colmove=c;
			}
		}
		if(diacounter1==2 || diacounter1==-2){
			return new int[] {rowmove, colmove};
		}

		for(int d=0; d<=2; d++){
			if(board[d][2-d]=='O'){
				diacounter2++;
			}else if(board[d][2-d]=='X'){
				diacounter2--;
			}else if(board[d][2-d]==' '){
				rowmove=d;
				colmove=2-d;
			}
		}
		if(diacounter2==2||diacounter2==-2){
			return new int[]{rowmove, colmove};
		}
		return new int[] {-1,-1};
	}

	public static int AI() {
		int aimove=0;
		int userspot[]=new int[2];
		int coor[]=new int[2];

		//going first
		if(numTurns%2==1){
			//3rd move	
			if(numTurns==1){
				aimove = 9;
			}else if(numTurns==3){

				for(int i=0; i<=2;i++){
					for(int j=0; j<=2; j++){
						if(board[i][j]=='O'){
							userspot[0]=i;
							userspot[1]=j;
							break;
						}
					}
				}
				if((userspot[0]==1&&userspot[1]==1) || (userspot[0]==2&&userspot[1]==0)||(userspot[0]==0&&userspot[1]==2) ){
					aimove=1;
				}else if(userspot[0]==0&&userspot[1]==0) {
					aimove=7;
				}else if((userspot[0]==1&&userspot[1]==0)||(userspot[0]==2&&userspot[1]==1)) {
					aimove = 3;
				}else if((userspot[0]==1&&userspot[1]==2)||(userspot[0]==0&&userspot[1]==1)) {
					aimove = 7;
				}
			} 
			//5th move
			else if(numTurns==5) {
				coor=cangameover();
				if(coor[0]==-1&&coor[1]==-1){
					aimove=5;
				}else{
					if(isValid(coortonum(coor))){
						aimove=coortonum(coor);
					}
				}
			} //end of 5th

			//7th move
			else if(numTurns==7) {
				coor=cangameover();
				if(coor[0]==-1&&coor[1]==-1) {
					for(int i=1;i<=9;i++) {
						if(isValid(i)==true) {
							aimove=i;
							return aimove;
						}
					}
				}else {
					aimove=coortonum(coor);
				}
			}

			else if(numTurns==9){
				for(int i=0;i<=2;i++){
					for(int j=0; j<=2; j++){
						if(board[i][j]==' '){
							int coor2[]={i,j};
							aimove=coortonum(coor2);
							return aimove;
						}
					}
				}
			}
		}
		else if(numTurns%2==0){
			//2nd move
			if(numTurns==2){
				for(int i=0; i<=2;i++){
					for(int j=0; j<=2; j++){
						if(board[i][j]=='O'){
							userspot[0]=i;
							userspot[1]=j;
							break;
						}
					}
				}

				if(userspot[0]==1&&userspot[1]==1){
					aimove=9;
				}else{
					aimove=5;
				}
			}
			//4th move
			else if(numTurns==4){
				coor=cangameover();
				if(coor[0]==-1&&coor[1]==-1){
					if(board[2][2]==computer) {
						aimove=3;
					}else if(board[0][0]==user) {
						if(board[1][2]==user || board[2][2]==user) {
							aimove=8;
						}else if(board[2][1]==user) {
							aimove=6;
						}
					}else if(board[0][2]==user) {
						if(board[1][0]==user) {
							aimove=8;
						}else if(board[2][0]==user||board[2][1]==user) {
							aimove=4;
						}
					}else if(board[2][0]==user) {
						if(board[1][2]==user||board[0][2]==user){
							aimove=2;
						}else if(board[1][0]==user) {
							aimove=3;
						}
					}else if(board[2][2]==user) {
						if(board[0][0]==user||board[1][0]==user) {
							aimove=2;
						}else if(board[0][1]==user) {
							aimove=4;
						}
					}else if(board[1][1]==computer) {
						if(board[0][1]==user||board[2][1]==user||board[1][2]==user) {
							aimove=1;
						}else if(board[1][0]==user) {
							if(board[2][1]==user) {
								aimove=4;
							}else if(board[0][1]==user||board[2][1]==user) {
								aimove=1;
							}
						}
					}
				}
				else {
					aimove=coortonum(coor);
				}
			} //end of 4th move
			
			else if(numTurns==6 || numTurns==8) {
				coor=cangameover();

				if(coor[0]==-1&&coor[1]==-1){
					for(int i=1;i<=9;i++) {
						if(isValid(i)==true) {
							aimove=i;
							return aimove;
						}
					}
				}else {
					aimove=coortonum(coor);
				}
			}
		}
		return aimove;
	}
}