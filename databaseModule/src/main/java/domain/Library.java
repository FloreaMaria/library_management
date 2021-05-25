package domain;

import java.util.Set;

public class Library extends Entity<Integer> {

    private static int libraryId = 0;
    private String libraryName, libraryAddress;
//    private List<BookItem> books;
////    private Set<Librarian> librarianSet;

    private Set<Client> clientSet;
    private Set<Section> sectionSet;

    public Library(){
        libraryId++;
        this.setId(libraryId);

    }

    public Library(String libraryName, String libraryAddress, Set<Section> sectionSet, Set<Client> clients) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        this.sectionSet = sectionSet;
        this.clientSet  = clients;
        libraryId++;
        this.setId(libraryId);
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }


    public Set<Client> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<Client> clientSet) {
        this.clientSet = clientSet;
    }

    public Set<Section> getSectionSet() {
        return sectionSet;
    }

    public void setSectionSet(Set<Section> sectionSet) {
        this.sectionSet = sectionSet;
    }

}
