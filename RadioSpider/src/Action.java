import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Action {
	
	private static String url_1 // =  "the root url";
	private static int limit = // the limit of the urls that we want to store;
	private static Set<String> visitedURLs = new HashSet<>();
	private static Set<String> foundURLs = new HashSet<>();
	private static Queue<String> pendingURLs = new LinkedList<>(); 
	

	
	
	public static boolean addURLs( String url ) {
		
		
		visitedURLs.add(url);						 //implementing a Breadth First Search algorithm
		pendingURLs.poll();						//since we have made a call for the current url
		System.out.println(url + "   " + visitedURLs.size());		//we add it to the visitedURLs and delete it from
		WPage myPage = new WPage(url);					//the pendingURLs queue
		pendingURLs.addAll( myPage.getEditedURLs() );			//now we fetch the urls contained in the current page
		foundURLs.addAll( myPage.getEditedURLs() );			//and add them to the pending queue,and the found urls
		
		


		try {
			
			if ( limit<= foundURLs.size() ) {
				return false;				//stopping the method when the set reaches the limit
			}
			
			
			for ( String u: pendingURLs) {				//for each url in the pending queue
				
				if ( !visitedURLs.contains(u) ) {		//making sure we don't visit the same url twice
					
					addURLs(u);						//recursive call
					
				
				}
				
			}
		}catch (java.util.ConcurrentModificationException e) {
			return false;
			
		}
		
		return true;
	}
	
	
	
	 public static void main(String args[]){
		
		 addURLs(url_1);							
		 
		 
		 System.out.println(visitedURLs.size());				//printing the sizes
		 System.out.println( foundURLs.size());
		 
	 
	 }
}
