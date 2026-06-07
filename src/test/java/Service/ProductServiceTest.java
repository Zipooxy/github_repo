package Service;

import Model.Electronic;
import Model.Fashion;
import Model.Living;
import Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductService Tests")
public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    @DisplayName("initializeProduct menambahkan produk awal")
    void testInitializeProduct() {
        productService.initializeProduct();
        // Produk ID 1 (Laptop) harus ada
        Product laptop = productService.findProductById(1);
        assertNotNull(laptop);
        assertEquals("Laptop Lenovo", laptop.getNama());
    }

    @Test
    @DisplayName("addProduct menambahkan produk baru")
    void testAddProduct() {
        Electronic e = new Electronic(10, "HP Xiaomi", 1500000.0, "Handphone");
        productService.addProduct(e);
        Product found = productService.findProductById(10);
        assertNotNull(found);
        assertEquals("HP Xiaomi", found.getNama());
    }

    @Test
    @DisplayName("findProductById mengembalikan null jika tidak ditemukan")
    void testFindProductByIdNotFound() {
        Product result = productService.findProductById(999);
        assertNull(result);
    }

    @Test
    @DisplayName("deleteProduct menghapus produk dari daftar")
    void testDeleteProduct() {
        Electronic e = new Electronic(5, "Keyboard Mechanical", 800000.0, "Aksesoris");
        productService.addProduct(e);
        assertNotNull(productService.findProductById(5));

        productService.deleteProduct(e);
        assertNull(productService.findProductById(5));
    }

    @Test
    @DisplayName("getProdukByKategori mengembalikan produk yang sesuai kategori")
    void testGetProdukByKategori() {
        productService.addProduct(new Electronic(1, "Monitor", 2000000.0, "Monitor"));
        productService.addProduct(new Fashion(2, "Kaos", 100000.0, "Putih", "M", "Pakaian"));
        productService.addProduct(new Living(3, "Lampu", 50000.0, "Dekorasi"));

        var electronics = productService.getProdukByKategori(Electronic.class);
        assertEquals(1, electronics.size());
        assertInstanceOf(Electronic.class, electronics.get(0));
    }

    @Test
    @DisplayName("Multiple produk bisa ditambahkan")
    void testMultipleProducts() {
        productService.addProduct(new Electronic(1, "Laptop", 5000000.0, "Laptop"));
        productService.addProduct(new Fashion(2, "Baju", 200000.0, "Merah", "L", "Baju"));
        productService.addProduct(new Living(3, "Sofa", 1000000.0, "Furnitur"));

        assertNotNull(productService.findProductById(1));
        assertNotNull(productService.findProductById(2));
        assertNotNull(productService.findProductById(3));
    }
}
