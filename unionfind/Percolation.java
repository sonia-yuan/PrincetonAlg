package unionfind;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] openr;
	private WeightedQuickUnionUF uf;
	private int trial;
	private int N;
	private int nos;
		
	public Percolation(int n, int trials){
		openr = new int[n][n];
		trial = trials;
		N = n;
		nos = 0;
		uf = new WeightedQuickUnionUF(n*n);
	}
	
	public void open(int row, int col) {
		if (row <= 0 || row > N || col <= 0 || col > N ) throw new IndexOutOfBoundsException("row index i out of bounds");
		openr[row-1][col-1] = 1;
		nos ++;
		if (N < 2) return;
		if (row+1<= N && openr[row][col-1]==1) {
			uf.union((row-1)*N+(col-1), row*N+(col-1));
		}
		if (row-1 >= 1 && openr[row-2][col-1]==1) {
			uf.union((row-1)*N+(col-1), (row-2)*N+(col-1));
		}
		if (col-1 >= 1 && openr[row-1][col-2]==1) {
			uf.union((row-1)*N+(col-1), (row-1)*N+col-2);
		}
		if (col+1 <= N && openr[row-1][col]==1) {
			uf.union((row-1)*N+(col-1), (row-1)*N+col);
		}	
	}
	
	public boolean isOpen(int row, int col) {
		if (row <= 0 || row > N || col <= 0 || col > N ) throw new IndexOutOfBoundsException("row index i out of bounds");
		if (openr[row-1][col-1] == 0){
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isFull(int row, int col) {
		if (row <= 0 || row > N || col <= 0 || col > N ) throw new IndexOutOfBoundsException("row index i out of bounds");
		for (int i = 0; i < N; i++) {
			if (uf.connected((row-1)*N+col-1, i)){
				return true;
			}
		}
		return false;
	}
	
	public int numberOfOpenSites() {
		return nos;
	}
	
	public boolean percolates() {
		for (int i = 1; i<= N; i++ ) {
			if ( isFull(N,i)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {	
		Percolation perc = new Percolation(3,1);
		perc.open(1, 2);
		perc.open(2, 2);
		System.out.println(perc.isFull(2,2));
		perc.open(3, 2);
		System.out.println(perc.percolates());
		
	}
}
