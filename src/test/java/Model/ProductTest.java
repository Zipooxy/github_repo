package Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Model Tests")
public class ProductTest {

    @Test
    @DisplayName("Electronic dibuat dengan data yang benar")
    void testElectronicCreation() {
        Electronic e = new Electronic(1, "Laptop Lenovo", 5000000.0, "Laptop");
        assertEquals(1, e.getId());
        assertEquals("Laptop Lenovo", e.getNama());
        assertEquals(5000000.0, e.getHarga());
    }

    @Test
    @DisplayName("Fashion dibuat dengan data yang benar")
    void testFashionCreation() {
        Fashion f = new Fashion(2, "T-shirt Band", 150000.0, "Hitam", "M", "Pakaian");
        assertEquals(2, f.getId());
        assertEquals("T-shirt Band", f.getNama());
        assertEquals(150000.0, f.getHarga());
    }

    @Test
    @DisplayName("Living dibuat dengan data yang benar")
    void testLivingCreation() {
        Living l = new Living(3, "Kursi Rotan", 300000.0, "Furnitur");
        assertEquals(3, l.getId());
        assertEquals("Kursi Rotan", l.getNama());
        assertEquals(300000.0, l.getHarga());
    }

    @Test
    @DisplayName("Electronic adalah instance dari Product")
    void testElectronicIsProduct() {
        Electronic e = new Electronic(1, "HP Samsung", 2000000.0, "Handphone");
        assertInstanceOf(Product.class, e);
    }

    @Test
    @DisplayName("Fashion adalah instance dari Product")
    void testFashionIsProduct() {
        Fashion f = new Fashion(2, "Jaket Denim", 250000.0, "Biru", "L", "Jaket");
        assertInstanceOf(Product.class, f);
    }

    @Test
    @DisplayName("Living adalah instance dari Product")
    void testLivingIsProduct() {
        Living l = new Living(3, "Meja Kayu", 400000.0, "Meja");
        assertInstanceOf(Product.class, l);
    }
}
