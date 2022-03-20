package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "Война и мир", 800, "Л.Н. Толстой");
    Product book2 = new Book(2, "Ванька", 450, "А.П. Чехов");
    Product smartphone1 = new Smartphone(3, "Redmi note 8", 20_000, "Xiaomi");
    Product book3 = new Book(4, "Dracula", 550, "Bram Stoker");
    Product smartphone2 = new Smartphone(5, "Galaxy A22", 20_000, "Samsung");

    @BeforeEach
    void add() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(book3);
        manager.add(smartphone2);
    }

    @Test
    public void shouldFindBookRusName() {
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("Война и мир");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBookEngName() {
        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Dracula");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneName() {
        Product[] expected = {smartphone2};
        Product[] actual = manager.searchBy("Galaxy A22");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindName() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Байрон");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindFirstLiterName() {
        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("В");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindFNull() {

        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(3);
        repository.removeById(4);
        repository.removeById(5);

        Product[] expected = {};
        Product[] actual = manager.searchBy(null);
        assertArrayEquals(expected, actual);
    }
}