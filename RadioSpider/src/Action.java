import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Action {
	
	private static String url_1 // =  "the root url"l;
	private static int limit =20;
	private static Set<String> visitedURLs = new HashSet<>();
	private static Set<String> foundURLs = new HashSet<>();
	private static Queue<String> pendingURLs = new LinkedList<>(); 
	

	
	
	public static boolean addURLs( String url ) {
		
		
		visitedURLs.add(url);
		pendingURLs.poll();
		System.out.println(url + "   " + visitedURLs.size());
		WPage myPage = new WPage(url);
		pendingURLs.addAll( myPage.getEditedURLs() );
		foundURLs.addAll( myPage.getEditedURLs() );
		
		


		try {
			
			if ( limit<=visitedURLs.size() ) {
				return false;
			}
			
			
			for ( String u: pendingURLs) {
				
				if ( !visitedURLs.contains(u) ) {
					
					addURLs(u);
					
				
				}
				
			}
		}catch (java.util.ConcurrentModificationException e) {
			return false;
			
		}
		
		return true;
	}
	
	
	
	 public static void main(String args[]){
		
		 addURLs(url_1);
		 
		 
		 System.out.println(visitedURLs.size());
		 System.out.println( foundURLs.size());
		 
	 
	 }
}
