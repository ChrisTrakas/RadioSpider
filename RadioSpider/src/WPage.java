import java.io.IOException;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.util.HashSet;
	import java.util.Set;
	import java.util.stream.Collectors;
	import java.util.stream.Stream;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;


	
	

	public class WPage {

		private String url;
		private Document doc;
		Set<String> rawURLs = new HashSet<>();
		Set<String> editedURLs = new HashSet<>();
		Set<String> extentions =Stream.of("mng", "pct", "bmp",
				"gif", "jpg", "jpeg", "png", "pst", "psp", "tif",
			    "tiff", "ai", "drw", "dxf", "eps", "ps", "svg",
			    "mp3", "wma", "ogg", "wav", "ra", "aac", "mid", "au", "aiff",
			    "3gp", "asf", "asx", "avi", "mov", "mp4", "mpg", "qt", "rm", "swf", "wmv",
			    "m4a", "css", "pdf", "doc", "exe", "bin", 
			     "rss", "zip", "rar").collect(Collectors.toSet());
		
		
		public WPage(String url) {
			
			
			
			this.url  = url;
			
			try {
				
				this.doc = Jsoup.connect(url).get(); 
				
				findRawURLs();
				editURLs();
				
			}catch (Exception e) {
				
	            
	        }

		}

		public String getUrl() {
			return url;
		}

		public Document getDoc() {
			return doc;
		}
		
		
		public void findRawURLs(){
			Elements linkElements = doc.select("a[href]");
			
			
			for (Element e: linkElements) {
					
				rawURLs.add( e.attr("href") );
					
					}	

		}
		
		
		
		
		public void editURLs() {
			boolean isFile;
			try {
				String authority = (new URL(url) ).getAuthority();
				String protocol = (new URL(url) ).getProtocol();
				
				for (String link : rawURLs) {
					isFile = false;
					if (link.contains(".")){
						if ( extentions.contains( link.substring(link.lastIndexOf(".")+1 ) )) {
							link = "null";
							
						}
						
					}
					
					
					if ( link.startsWith("/") && link.length()>1  && link.length()<200 && !isFile   ) {
						editedURLs.add( protocol + "://"+ authority + link);
						

					}else if (!isFile) {
						try {
							if ( new URL(link).getAuthority().equals(authority) && link.length()<200 ) {       //thelei ki allo elegxo
								editedURLs.add(link);
							}
							
						}catch(Exception e) {
							
						}
						
					}
				
					
				}			
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}		
			
		}
		
		
		
		

		public Set<String> getRawURLs() {
			return rawURLs;
		}

		
		
		public Set<String> getEditedURLs() {
			return editedURLs;
		}
		
		
	}





