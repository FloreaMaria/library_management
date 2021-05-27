
### Classes


------

- Library
- Person
- Librarian
- Author
- Client
- Book
- BookItem
- LibraryCard
- Rent
- Section
- BookAuthorService
- RentClientService
- Service
- Main



-------

----------


### Tables


books||book_items||authors||clients||rents||librarians||sections||libraries||library_cards|
------------- || ------------- || ------------- || ------------- || ------------- || -------------
book_id|(pk)|book_item_id|(pk)|author_id|(pk)|client_id|(pk)|rent_id|(pk)|librarian_id|(pk)|section_id|(pk)|library_id|(pk)|library_card_id|(pk)
author_id|(fk)|author_id|(fk)|first_name|attr|library_id|(fk)|book_item_id|(fk)|section_id|(fk)|library_id|(fk)|name|attr|release_date|attr
section_id|(fk)|status|attr|last_name|attr|library_card_id|(fk)|client_id|(fk)|first_name|attr|name|attr|address|attr|expire_date|attr
pages|attr|pages|attr|||first_name|attr|rent_date|attr|last_name|attr|location|attr||||
length|attr|length|attr|||last_name|attr|actual_return_date|attr|hire_date|attr||||||
width|attr|width|attr|||address|attr|penalty|attr||||||||
release_year|attr|release_year|attr||||||||||||||
title|attr|title|attr||||||||||||||
publishing_house|attr|publishing_house|attr||||||||||||||
category|attr|category|attr||||||||||||||
description|attr|description|attr||||||||||||||


-----

-----

### Actions/Interrogations


- Display all clients/books/authors
- Cancel membership of client
- Search client by first name/last name
- Create new rent for client
- Display clients who have penalties
- As a client, return a borrowed book 
- Search book by author title/name/id
- Display books ordered by title asc/desc
- Find book from specific section
- Find book by title
- Search author by name/id










