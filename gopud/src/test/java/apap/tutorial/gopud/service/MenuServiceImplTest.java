package apap.tutorial.gopud.service;
import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

// menentukan library yang digunakan untuk menjalankan testing
@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
    @InjectMocks
    MenuService menuService = new MenuServiceImpl();
    @Mock
    MenuDb menuDb;

    // memberitahukan kepada TestRunner bahwa method tersebut adalah Test
    @Test
    public void whenAddValidMenuItShouldCallMenuRepositorySave() {
        MenuModel newMenu = new MenuModel();
        newMenu.setNama("ayam goreng");
        newMenu.setDeskripsi("ayam goreng dengan kremes");
        newMenu.setHarga(BigInteger.valueOf(17000));
        newMenu.setDurasiMasak(5);
        menuService.addMenu(newMenu);
        verify(menuDb, times(1)).save(newMenu);
    }

    @Test
    public void whenGetMenuListCalledItShouldReturnAllMenu() {
        List<MenuModel> allMenuInRestoran = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            allMenuInRestoran.add(new MenuModel());
        }
        when (menuService.findAllMenuByIdRestoran(1L)).thenReturn(allMenuInRestoran);
        List<MenuModel> dataFromServiceCall = menuService.findAllMenuByIdRestoran(1L);
        assertEquals(3, dataFromServiceCall.size());
        verify(menuDb, times(1)).findByRestoranIdRestoran(1L);
    }

    @Test
    public void whenGetMenuByIdCalledByExistingDataItShouldReturnCorrectData() {
        MenuModel returnedData = new MenuModel();
        returnedData.setNama("ikan");
        returnedData.setDeskripsi("bakar");
        returnedData.setHarga(BigInteger.valueOf(25000));
        returnedData.setDurasiMasak(7);
        when(menuService.getMenuByIdMenu(1L)).thenReturn(Optional.of(returnedData));
        Optional<MenuModel> dataFromServiceCall =
                menuService.getMenuByIdMenu(1L);
        verify(menuDb, times(1)).findById(1L);
        assertTrue(dataFromServiceCall.isPresent());
        MenuModel dataFromOptional = dataFromServiceCall.get();
        assertEquals("ikan", dataFromOptional.getNama());
        assertEquals("bakar", dataFromOptional.getDeskripsi());
        assertEquals(BigInteger.valueOf(25000), dataFromOptional.getHarga());
        assertEquals(Integer.valueOf(7), dataFromOptional.getDurasiMasak());
    }

    //belum berhasil
    @Test
    public void whenChangeMenuCalledItShouldChangeMenuData() {
        MenuModel updatedData = new MenuModel();
        updatedData.setNama("cumi");
        updatedData.setId(Long.valueOf(1));
        updatedData.setDeskripsi("goreng tepung");
        updatedData.setHarga(BigInteger.valueOf(20000));
        updatedData.setDurasiMasak(10);
        when(menuDb.findById(1L)).thenReturn(Optional.of(updatedData));
        when(menuService.changeMenu(updatedData)).thenReturn(updatedData);
        MenuModel dataFromServiceCall = menuService.changeMenu(updatedData);
        assertEquals("cumi", dataFromServiceCall.getNama());
        assertEquals(Long.valueOf(1), dataFromServiceCall.getId());
        assertEquals("goreng tepung", dataFromServiceCall.getDeskripsi());
        assertEquals(BigInteger.valueOf(20000), dataFromServiceCall.getHarga());
        assertEquals(Integer.valueOf(10), dataFromServiceCall.getDurasiMasak());
    }

    @Test
    public void whenDeleteMenuCalledItShouldDeleteMenu() {
        MenuModel existingMenu = new MenuModel();
        existingMenu.setNama("bebek");
        existingMenu.setDeskripsi("bakar madu");
        existingMenu.setHarga(BigInteger.valueOf(17000));
        existingMenu.setDurasiMasak(5);
        menuService.deleteMenu(existingMenu);
        verify(menuDb, times(1)).delete(existingMenu);
    }
}
