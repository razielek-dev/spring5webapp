package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book notEJB = new Book("J2EE Development without EJB", "23444", "Worx");
        rod.getBooks().add(notEJB);

        authorRepository.save(rod);
        bookRepository.save(notEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
