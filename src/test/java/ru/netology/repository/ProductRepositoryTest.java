package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository repository = new ProductRepository();


    Product book1 = new Book(1, "Война и мир", 800, "Л.Н. Толстой");
    Product book2 = new Book(2, "Ванька", 450, "А.П. Чехов");
    Product smartphone1 = new Smartphone(3, "Redmi note 8", 20_000, "Xiaomi");
    Product book3 = new Book(4, "Dracula", 550, "Bram Stoker");
    Product smartphone2 = new Smartphone(5, "Galaxy A22", 20_000, "Samsung");

    @BeforeEach
    void addProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(smartphone1);
        repository.save(book3);
        repository.save(smartphone2);
    }

    @Test
    public void removeById() {

        repository.removeById(4);

        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdNotFoundIdException() {

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(9);
        });
    }
}