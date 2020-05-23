
import edu.duke.*;
import java.io.File;

public class FindMultipleGenesStorage {
    
   
    
     public void testStorageFinder() {
        FileResource dnaFile = new FileResource();   // from edu package
        StorageResource genesFound = storeAllGenes( dnaFile.asString() ); //.asString since edu.duke cannot convert file resource to string
        System.out.println( "Number of genes found: "+genesFound.size() );
        printGenes( genesFound );
        
    }
    
    public StorageResource storeAllGenes(String dna) {
        
        
        int start = 0;
        
        StorageResource genes = new StorageResource();
        
        while (true) {
            
            int CurrentLoc = dna.indexOf( "atg", start );
            
            if ( CurrentLoc == -1 ) {
                break;
            }
            
            int stop = findStopIndex( dna, CurrentLoc+3 );
            
            if ( stop != dna.length() ) {
                genes.add( dna.substring(CurrentLoc, stop+3) );
                start = stop + 3;
            } else {
                start = start + 3;
            }
               
        }
        
        return genes;
        
    }
    
    public int findStopIndex(String dna, int index) {
        
        int stop1 = dna.indexOf("tga", index);
        if ( stop1 == -1 || ( stop1-index ) % 3 != 0 ) {
            stop1 = dna.length();
        }
        
        int stop2 = dna.indexOf("taa", index);
        if ( stop2 == -1 || ( stop2-index ) % 3 != 0 ) {
            stop2 = dna.length();
        }
        
        int stop3 = dna.indexOf("tag", index);
        if ( stop3 == -1 || ( stop3-index ) % 3 != 0 ) {
            stop3 = dna.length();
        }
        
        return Math.min( stop1, Math.min(stop2, stop3) );  // minimum of all 3 stopindex
    }
    
    public void printGenes( StorageResource sr ) {
        
        int sixtyChar = 0;
        for ( String s : sr.data() ) {
            
             if ( s.length() > 60 ) {
                 System.out.println( "String longer than 60 characters are : "+s );
                 sixtyChar++;
             }

        }
        
        System.out.println( "String of 60 characters : "+sixtyChar );
        
    }

}